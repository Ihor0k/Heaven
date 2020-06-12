<template>
    <div class="parameter">
        <div class="header" :class="{active: active}" @click="toggle">
            <span class="name">{{ value.name }}</span>
            <span class="actions">
                <button class="icon-btn save-btn" :disabled="isSaved" @click.stop="save"/>
                <button class="icon-btn remove-btn" @click.stop="$emit('remove')"/>
            </span>
        </div>
        <div class="content"
             :style="{ maxHeight: maxHeight }"
             ref="content"
        >
            <div class="fields">
                <div class="field">
                    <label v-uni-for="'name'">Name</label>
                    <input v-uni-id="'name'" :value="value.name" @input="update('name', $event.target.value)">
                </div>
                <div class="field">
                    <label v-uni-for="'description'">Description</label>
                    <textarea rows="4" maxlength="800" v-uni-id="'description'" :value="value.description"
                              @input="update('description', $event.target.value)"></textarea>
                </div>
                <draggable class="field options"
                           ghost-class="ghost"
                           handle=".header"
                           :list="value.options"
                >
                    <Option
                            v-for="(option, index) in value.options"
                            :key="option.key"
                            ref="options"
                            v-model="value.options[index]"
                            @expand="expandOption(index)"
                            @remove="removeOption(index)"
                    />
                </draggable>
            </div>
            <button class="icon-btn add-btn" @click="addOption"/>
        </div>
    </div>
</template>

<script>
    import {isEqual, KeyGenerator} from "@/utils"
    import Vue from 'vue'
    import Option from "@/components/admin/Option"
    import {createUniqIdsMixin} from 'vue-uniq-ids'
    import draggable from 'vuedraggable'

    export default {
        name: "Parameter",
        props: ['value'],
        mixins: [createUniqIdsMixin()],
        saved: null,
        keyGenerator: null,
        components: {
            Option,
            draggable
        },
        created() {
            if (this.value) {
                this.$options.keyGenerator = new KeyGenerator();
                this.value.options.forEach(option => Vue.set(option, 'key', this.$options.keyGenerator.next()));
                this.$options.saved = JSON.parse(JSON.stringify(this.value));
            }
        },
        data() {
            return {
                active: false,
                maxHeight: "0",
                isSaved: true
            }
        },
        mounted: function () {
            this.$refs.content.addEventListener('transitionend', () => {
                if (this.active) {
                    this.maxHeight = this.scrollHeight();
                } else {
                    this.$refs.content.classList.add('hidden');
                }
            });
        },
        watch: {
            value: {
                handler: function (val) {
                    this.isSaved = isEqual(val, this.$options.saved);
                },
                deep: true
            },
            active: function (active) {
                if (active) {
                    this.$refs.content.classList.remove('hidden');
                    this.maxHeight = this.scrollHeight();
                } else {
                    this.maxHeight = '0';
                }
            }
        },
        methods: {
            addOption: function () {
                const option = {
                    key: this.$options.keyGenerator.next(),
                    name: '',
                    description: '',
                    price: '',
                    image: ''
                };
                this.value.options.push(option);
                Vue.nextTick(() => {
                    this.expandOption(this.value.options.length - 1);
                });
            },
            removeOption: function (index) {
                this.value.options.splice(index, 1);
            },
            update: function (key, value) {
                this.$emit('input', {...this.value, [key]: value});
            },
            scrollHeight: function () {
                return this.$refs.content.scrollHeight + 1 + "px";
            },
            expandOption: function (index) {
                this.maxHeight = "100%";
                const key = this.value.options[index].key;
                this.$refs.options.forEach((option) => {
                    if (option.value.key === key) {
                        option.expand();
                    } else {
                        option.collapse();
                    }
                });
            },
            toggle: function () {
                if (this.active) {
                    this.collapse();
                } else {
                    this.expand();
                    this.$emit('expand');
                }
            },
            expand: function () {
                this.active = true;
            },
            collapse: function () {
                this.active = false;
            },
            save: function () {
                this.$options.saved = JSON.parse(JSON.stringify(this.value));
                this.isSaved = true;
                this.$emit('save')
            }
        }
    }
</script>

<style>
    .fields {
        margin: 1em 0;
    }

    .field:not(:last-child) {
        margin-bottom: 1em;
    }

    .field label {
        display: block;
        margin-bottom: 4px;
    }

    .field textarea {
        resize: none;
    }
</style>

<style scoped>
    .parameter {
        border: 2px solid #2c3e50;
        margin: 1.5em;
    }

    .header {
        position: relative;
        cursor: pointer;
        height: 2em;
        line-height: 2em;
        padding: 0 0.5em;
    }

    .active, .header:hover {
        background-color: #e8e8e8;
    }

    .actions {
        position: absolute;
        right: 0;
    }

    .actions > *:not(:last-child) {
        margin-right: 0.5em;
    }

    .actions > .icon-btn:hover:enabled {
        background-color: #d0d0d0;
    }

    .actions > .icon-btn:disabled {
        opacity: 0.3;
    }

    .content {
        padding: 0 1em;
        overflow: hidden;
        transition: max-height 0.2s ease-out;
    }

    .options > *:not(:last-child) {
        margin-bottom: 1em;
    }

    .save-btn {
        background-image: url("../../assets/save.png");
    }

    .remove-btn {
        background-image: url("../../assets/remove.png");
    }

    .add-btn {
        background-image: url("../../assets/add.png");
        margin-bottom: 1em;
    }

</style>