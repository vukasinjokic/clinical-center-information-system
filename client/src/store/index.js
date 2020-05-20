import Vuex from 'vuex';
import Vue from 'vue';
import appointments from './modules/appointments';
import room from './modules/room';
import clinics from './modules/clinics.js';
import userDetails from './modules/userDetails.js';
import patient from './modules/patient'
import examination_type from './modules/examination_type'
import doctors from './modules/doctors';
import doctor from './modules/doctor'
<<<<<<< HEAD
import appointmentRequests from './modules/appointmentRequests'
=======
import userProfile from './modules/userProfile'
import clinicAdmin from './modules/clinicAdmin'
>>>>>>> feature/vacationRequest

// load vuex
Vue.use(Vuex);

// create store
export default new Vuex.Store({
    modules: {
        appointments,
        room,
        clinics,
        userDetails,
        patient,
        examination_type,
        doctors,
        doctor,
<<<<<<< HEAD
        appointmentRequests

=======
        userProfile,
        clinicAdmin
>>>>>>> feature/vacationRequest
    }
});
