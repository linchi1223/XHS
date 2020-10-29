import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/login.vue'
import Index from './views/index.vue'
import User from './views/user.vue'
import Comment from './views/comment.vue'
import Register from './views/register.vue'
// import App from 'App.vue'
Vue.use(Router)

const router = new Router({
    routes: [
        {
            path: '/',
            redirect: '/user'
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
            path: '/comment',
            name: 'Comment',
            component: Comment
        },
    ]
})

// // 挂载路由导航守卫
// router.beforeEach((to, from, next) => {
//     // ${//to and from are Route Object,next() must be called to resolve the hook}
//     if(to.path ==='/login') return next()
//     if(to.path ==='/index') return next()
//     if(to.path ==='/register') return next()
//     const tokenStr =window.sessionStorage.getItem('token')
//     if(!tokenStr) return next('/login')
//     next()
// })


export default router