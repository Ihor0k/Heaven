<template>
    <div id="app">
        <Admin/>
    </div>
</template>

<script>
    import Admin from "@/components/Admin";
    import {API_LOCATION} from '../config'
    import axios from 'axios'

    axios.defaults.baseURL = API_LOCATION

    export default {
        name: 'App',
        components: {
            Admin
        }
    }
</script>

<style>
    #app {
        font-family: Avenir, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        font-size: 1em;
        width: 100vw;
    }

    input:not([type]), input[type=text], textarea {
        padding: 8px;
        border-radius: 2px;
        font-family: inherit;
        font-size: 1em;
        border: 1px #2c3e50 solid;
        width: 100%;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
    }

    button {
        line-height: 1;
        cursor: pointer;
        outline: none;
    }

    button:disabled {
        cursor: default;
    }

    .btn {
        text-transform: uppercase;
        border-radius: 4px;
        border: 1px #2c3e50 solid;
        background-color: #42b983;
        color: #fdfbef;
        padding: 0.5em 1em;
    }

    .icon-btn {
        font-size: 1em;
        background-size: 100% 100%;
        background-repeat: no-repeat;
        background-color: unset;
        height: 2em;
        width: 2em;
        border: 0;
    }

    .ghost {
        opacity: 0.5;
    }

    .hidden {
        display: none;
    }
</style>
