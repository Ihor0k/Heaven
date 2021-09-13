import Vue from 'vue'
import {createUniqIdsMixin} from 'vue-uniq-ids'
import VueRouter from 'vue-router'
import {routes} from './routes'

Vue.mixin(createUniqIdsMixin())
Vue.use(VueRouter)

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: routes
})

new Vue({
    router
}).$mount('#app')
