import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import lazy from '@/components/evaluateList'

import Login from '@/components/login'
import Index from '@/components/index'
import User from '@/components/user'
import Comment from '@/components/comment'
import Register from '@/components/register'
import Upcontent from '@/components/upcontent'
import Userchange from '@/components/userchange'
import VueCropper from 'vue-cropper'

Vue.use(VueCropper)

Vue.use(Router)

const router = new Router({
    routes: [{
            path: '/',
            redirect: '/index'
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
        },
        {
            path: '/user',
            name: 'User',
            component: User
        },
        {
            path: '/upcontent',
            name: 'Upcontent',
            component: Upcontent
        },
        {
            path: '/comment',
            name: 'Comment',
            component: Comment
        },
        {
            path: '/userchange',
            name: 'Userchange',
            component: Userchange
        },
        {
            path: '/HelloWorld',
            name: 'HelloWorld',
            component: HelloWorld
        },
        {
            path: '/lazy',
            name: 'lazy',
            component: lazy
        }
    ]
})

// 挂载路由导航守卫
router.beforeEach((to, from, next) => {
    if (to.path === '/login') return next()
    if (to.path === '/index') return next()
    if (to.path === '/register') return next()
    const tokenStr = window.sessionStorage.getItem('userid')
    if (!tokenStr) return next('/login')
    next()
})
export default router