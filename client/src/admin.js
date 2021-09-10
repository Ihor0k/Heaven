import Vue from 'vue'
import Admin from "./components/admin/Admin"
import {createUniqIdsMixin} from 'vue-uniq-ids'

Vue.mixin(createUniqIdsMixin())

new Vue({
    render: h => h(Admin),
}).$mount('#app')
