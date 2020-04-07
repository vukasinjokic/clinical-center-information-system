import Vue from 'vue';
import VueRouter from 'vue-router'
import HelloWorld from '../components/HelloWorld.vue'
import Home from '../components/Home'

Vue.use(VueRouter);

const router = new VueRouter({
    mode : 'hash',
    routes: [
      {
        path: '/helloWorld',
        name: 'HelloWorld',
        component: HelloWorld
      },
      {
        path: '/home',
        name: 'Home',
        component: Home
      }
    ]
  })

export default router;