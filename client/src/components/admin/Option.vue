<template>
    <div class="option">
        <div class="header" :class="{active: active}" @click="toggle">
            <span class="name">{{ value.name }}</span>
            <button class="icon-btn remove-btn" @click.stop="$emit('remove')"/>
        </div>
        <div class="content" :style="{ maxHeight: contentHeight }" ref="content">
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
                    <input v-uni-id="'price'" type="number" :value="value.price"
                           @input="update('price', $event.target.value)">
                </div>
            </div>
            <div class="image-container">
                <div class="image">
                    <input ref="imgInput" class="image-input" v-uni-id="'image'" type="file" accept="image/png"
                           @change="imageChange">
                    <img ref="img" class="image-preview" alt
                         :src="value.image ? imageUrl + '/' + value.image : 'data://,'">
                    <label class="image-label" :class="{transparent: hasImage}" v-uni-for="'image'">Pick an
                        image</label>
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
                imageUrl: API_LOCATION + '/admin/image',
                hasImage: false
            }
        },
        mounted() {
            if (this.value.image) {
                this.hasImage = true
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
                    this.hasImage = true
                } else {
                    this.value.image = null;
                    this.hasImage = false
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
        display: flex;
        overflow: hidden;
        transition: max-height 0.2s ease-out;
    }

    .fields {
        height: 100%;
        padding: 1em;
        width: 50%;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
    }

    .remove-btn {
        background-image: url("../../assets/remove.png");
        position: absolute;
        right: 0;
    }

    .remove-btn:hover {
        background-color: #e5ca49;
    }

    .image-container {
        padding: 1em 1em 1em 0;
        width: 50%;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
    }

    .image {
        height: 100%;
        border: 2px dashed #617c8b;
        overflow: hidden;
        text-align: center;
        position: relative;
    }

    .image-input {
        display: none;
    }

    .image-label {
        display: flex;
        height: 100%;
        align-items: center;
        margin: auto 0;
        justify-content: center;
        width: 100%;
        cursor: pointer;
        z-index: 1;
    }

    .image-preview {
        left: 0;
        right: 0;
        top: 0;
        bottom: 0;
        max-height: 100%;
        max-width: 100%;
        margin: auto;
        position: absolute;
    }

    .transparent {
        opacity: 0;
    }
</style>