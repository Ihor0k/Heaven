<template>
    <div class="option">
        <div class="header" :class="{active: active}" @click="toggle">
            <span class="name">{{ value.name }}</span>
            <button class="icon-btn remove-btn" @click.stop="$emit('remove')"/>
        </div>
        <div class="content"
             :style="{ maxHeight: contentHeight }"
             ref="content"
        >
            <div class="fields">
                <div class="field">
                    <label v-uni-for="'name'">Name</label>
                    <input v-uni-id="'name'" v-model="value.name">
                </div>
                <div class="field">
                    <label v-uni-for="'description'">Description</label>
                    <textarea v-uni-id="'description'" rows="4" maxlength="800" :value="value.description"
                              @input="update('description', $event.target.value)"></textarea>
                </div>
                <div class="field">
                    <label v-uni-for="'price'">Price</label>
                    <input v-uni-id="'price'" :value="value.price" @input="update('price', $event.target.value)">
                </div>
                <div class="field image-field">
                    <input ref="imgInput" class="image-input" v-uni-id="'image'" type="file"
                           @change="imageChange">
                    <label class="image-label" v-uni-for="'image'">Pick an image</label>
                    <img ref="img" class="image-preview" alt
                         :src="value.image ? imageUrl + '/' + value.image : 'data://,'">
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import {API_LOCATION} from '../../../config'
    import {createUniqIdsMixin} from 'vue-uniq-ids'
    import axios from 'axios'

    export default {
        name: "Option",
        props: ['value'],
        mixins: [createUniqIdsMixin()],
        data() {
            return {
                active: false,
                imageName: null,
                imageUrl: API_LOCATION + '/admin/image'
            }
        },
        computed: {
            contentHeight: function () {
                return this.active ? this.$refs.content.scrollHeight + "px" : "0";
            }
        },
        watch: {
            imageName: function (newName, oldName) {
                if (oldName) {
                    axios
                        .delete(`${this.imageUrl}/${oldName}`);
                }
                this.update('image', this.imageName)
                if (newName) {
                    this.value.image = newName;
                } else {
                    this.value.image = null;
                }
            }
        },
        methods: {
            update: function (key, value) {
                this.$emit('input', {...this.value, [key]: value});
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
            imageChange: function () {
                const images = this.$refs.imgInput.files;
                if (images.length === 0) {
                    return;
                }
                const formData = new FormData();
                formData.append('image', images[0])
                axios
                    .post(this.imageUrl, formData, {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    })
                    .then(response => this.imageName = response.data)
            }
        }
    }
</script>

<style scoped>
    .option {
        border: 1px #cdcdcd solid;
        border-radius: 2px;
        background: #fdfbef;
    }

    .header {
        position: relative;
        background-color: #ffeaad;
        cursor: pointer;
        height: 2em;
        line-height: 2em;
        padding: 0 0.5em;
    }

    .active, .header:hover {
        background-color: #f5db72;
    }

    .content {
        padding: 0 1em;
        overflow: hidden;
        transition: max-height 0.2s ease-out;
    }

    .remove-btn {
        background-image: url("../../assets/remove.png");
        position: absolute;
        right: 0;
    }

    .remove-btn:hover {
        background-color: #e5ca49;
    }

    .image-field {
        border: 2px dashed #617c8b;
        overflow: hidden;
        text-align: center;
        position: relative;
    }

    .image-input {
        display: none;
    }

    .image-label {
        width: 100%;
        padding: 4em 0;
        display: inline-block;
        cursor: pointer;
    }

    .image-preview {
        right: 0;
        height: 100%;
        position: absolute;
    }
</style>