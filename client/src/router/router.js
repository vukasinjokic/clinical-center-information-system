import Vue from 'vue';
import VueRouter from 'vue-router'
import UserProfile from '../components/UserProfile.vue'
import VacationReqReview from '../components/VacationReqReview'
import Room from '../views/Rooms'
import Login from '../views/Login'
import Appointments from '../views/Appointments'
import ExaminationTypeReview from '../components/examinationType/ExaminationTypeReview.vue'
import Calendar from '../views/Calendar'
import Clinics from '../views/clinic/Clinics.vue'
import Doctors from '../views/Doctors.vue'
import Register from '../views/Register.vue'
import DoctorPage from '../views/DoctorPage'
import PatientReview from '../components/PatientReview.vue'
import VacationRequest from '../components/VacationRequest'
import ClinicAdminPage from '../views/ClinicAdminPage'
import AppointmentRequests from '../components/AppointmentRequests'
import ScheduleAppointment from '../components/ScheduleAppointment'
import NursePage from '../views/NursePage'


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
        name: "Register",
        component: Register
      },
      
      {
        path: '/vacRequests',
        name: 'Vacation Requests',
        component: VacationReqReview
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
        path: '/ex_type',
        name: 'ExaminationType',
        component : ExaminationTypeReview
      },
      {
        path: '/doctor',
        name: 'DoctorPage',
        component: DoctorPage,
        children: [
            {path: 'patient', name: 'PatientReview', component: PatientReview},
            {path: 'profile', name: 'UserProfile', component: UserProfile},
            {path: ':16/calendar', name: 'Calendar', component : Calendar},
            {path: 'vacationRequest', name: 'VacationRequest', component: VacationRequest},
            {path: 'scheduleApp', name:'ScheduleAppointment', component: ScheduleAppointment}
        ]
      },
      {
        path: '/nurse',
        name: 'NursePage',
        component: NursePage,
        children: [
            {path: 'patient', name: 'PatientReview', component: PatientReview},
            {path: 'profile', name: 'UserProfile', component: UserProfile},
            {path: ':16/calendar', name: 'Calendar', component : Calendar},
            {path: 'vacationRequest', name: 'VacationRequest', component: VacationRequest}
        ]
      },
      {
        path: '/clinicAdmin',
        name: 'ClinicAdminPage',
        component: ClinicAdminPage,
        children: [
          {path: 'appointmentRequests', name: 'AppointmentRequests', component: AppointmentRequests},
        ]
      },
      {
        path: '/clinics',
        name: 'Clinics',
        component : Clinics
      },
      {
        path: '/doctors',
        name: 'Doctors',
        component: Doctors
      }
    ]
  })

export default router;