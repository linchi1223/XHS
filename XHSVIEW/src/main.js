import Vue from 'vue'
import App from './App.vue'
import router from './router'
import waterfall from 'vue-waterfall2'


Vue.use(waterfall)

console.log(Vue.prototype)

new Vue({
  router,
  el: '#app',
  render: h => h(App)
})
