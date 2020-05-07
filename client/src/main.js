import Vue from 'vue'
import App from './App.vue'
import router from './router/router.js'
import vuetify from './plugins/vuetify';
import store from './store'
import axios from 'axios'

Vue.config.productionTip = false;
Vue.$axios = axios;
Vue.$axios.defaults.headers['Authorization'] = localStorage.getItem("JWT");


//v-calendar ima bag koji ispisuje warn u konzoli.Ovo samo sprecava ispis.
const ignoreWarnMessage = 'The .native modifier for v-on is only valid on components but it was used on <div>.';
Vue.config.warnHandler = function (msg) {
  // `trace` is the component hierarchy trace
  if (msg === ignoreWarnMessage) {
    msg = null;
  }
}


new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')


