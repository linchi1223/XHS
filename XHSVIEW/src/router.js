import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/login.vue'
import Index from './views/index.vue'
import Register from './views/register.vue'
// import App from 'App.vue'
Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/login'
        },
        {
            path: '/login',
            name: 'Login',
            component: Login
        },
        {
            path: '/index',
            name: 'Index',
            component: Index
        }, 
        {
            path: '/register',
            name: 'Register',
            component: Register
        }
    ]
})