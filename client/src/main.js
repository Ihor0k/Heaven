import Vue from 'vue'
import Main from "./components/main/Main"
import {createUniqIdsMixin} from 'vue-uniq-ids'

Vue.mixin(createUniqIdsMixin())

new Vue({
    render: h => h(Main),
}).$mount('#app')
