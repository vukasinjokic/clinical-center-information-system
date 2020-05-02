import Vue from 'vue';
import VueRouter from 'vue-router'
import HelloWorld from '../components/HelloWorld.vue'
import Home from '../views/Home'
import Room from '../views/Rooms'
import Login from '../views/Login'
import Appointments from '../views/Appointments'
import Patients from '../views/Patients'
import Calendar from '../views/Calendar'
import Clinics from '../views/clinic/Clinics.vue'
import Clinic from '../views/clinic/Clinic.vue'
import Register from '../views/Register.vue'


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
        path: '/register',
        name: Register,
        component: Register
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
      },
      {
        path: '/calendar',
        name: 'Calendar',
        component : Calendar
      },
      {
        path: '/clinics',
        name: 'Clinics',
        component : Clinics
      },
      {
        path: '/clinic/:id',
        name: 'Clinic',
        component: Clinic
      }
    ]
  })

export default router;