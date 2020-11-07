import Vue from 'vue'
import router from './router'
import waterfall from 'vue-waterfall2'
import $ from 'jquery';
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'
import App from './App.vue'

axios.defaults.baseURL = "http://192.168.46.171"
Vue.prototype.axios = axios
Vue.prototype.$ajax = axios
Vue.use(waterfall)
Vue.use(ElementUI);

console.log(Vue.prototype)

new Vue({
  router,
  el: '#app',
  render: h => h(App)
})
