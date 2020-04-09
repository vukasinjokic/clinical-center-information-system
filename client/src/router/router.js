import Vue from 'vue';
import VueRouter from 'vue-router'
import HelloWorld from '../components/HelloWorld.vue'
import Home from '../components/Home'
import Doctors from '../components/Doctors'
import Login from '../components/Login'
import Appointments from '../components/Appointments'


Vue.use(VueRouter);

const router = new VueRouter({
    mode : 'hash',
    routes: [
      {
        path: '/',
        name: Login,
        component: Login
      },
      {
        path: '/helloWorld',
        name: 'HelloWorld',
        component: HelloWorld
      },
      {
        path: '/home',
        name: 'Home',
        component: Home
      },
      {
        path: '/doctors',
        name: 'Doctors',
        component: Doctors
      },
      {
        path: '/appointments',
        name: 'Appointments',
        component : Appointments

      }
    ]
  })

export default router;