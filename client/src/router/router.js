import Vue from 'vue';
import VueRouter from 'vue-router'
import HelloWorld from '../components/HelloWorld.vue'
import Home from '../views/Home'
import Room from '../views/Rooms'
import Login from '../components/Login'
import Appointments from '../views/Appointments'
import Patients from '../views/Patients'


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
        path: '/rooms',
        name: 'Rooms',
        component: Room
      },
      {
        path: '/appointments',
        name: 'Appointments',
        component : Appointments

      },
      {
        path: '/patients',
        name: 'Patients',
        component : Patients
      }
    ]
  })

export default router;