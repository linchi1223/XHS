import Vue from 'vue'
import App from './App.vue'
import router from './router'
import waterfall from 'vue-waterfall2'
import $ from 'jquery';
import 'bootstrap/dist/css/bootstrap.min.css'
import axios from 'axios'

axios.defaults.baseURL = "http://192.168.31.121"

Vue.use(waterfall)

console.log(Vue.prototype)

new Vue({
  router,
  el: '#app',
  render: h => h(App)
})
