import Vuex from 'vuex';
import Vue from 'vue';
import appointments from './modules/appointments';
import room from './modules/room';
import clinics from './modules/clinics.js';
import patient from './modules/patient'
import examination_type from './modules/examination_type'
import doctor from './modules/doctor'
import appointmentRequests from './modules/appointmentRequests'
import userProfile from './modules/userProfile'
import clinicAdmin from './modules/clinicAdmin'
import startAppointment from './modules/startAppointment'
import calendar from './modules/calendar'
import registrationRequests from './modules/registrationRequests'

// load vuex
Vue.use(Vuex);

// create store
const store = new Vuex.Store({
    modules: {
        appointments,
        room,
        clinics,
        patient,
        examination_type,
        doctor,
        appointmentRequests,
        userProfile,
        clinicAdmin,
        startAppointment,
        calendar,
        registrationRequests
    },

    actions: {
        clearStore() {
            this.dispatch("appointmentRequests/resetAppointmentRequests");
            this.dispatch("appointments/resetAppointments");
            this.dispatch("clinicAdmin/resetClinicAdmin");
            this.dispatch("clinics/resetClinics");
            this.dispatch("doctor/resetDoctor");
            this.dispatch("examination_type/resetExaminationType");
            this.dispatch("patient/resetPatient");
            this.dispatch("room/resetRoom");
            this.dispatch("userProfile/resetUserProfile");

            this.dispatch("startAppointment/resetAppointment");
            this.dispatch("calendar/resetCalendar");
            this.dispatch("registrationReqeusts/resetRegistrationRequests");

            localStorage.clear();
            sessionStorage.clear();
            Vue.$axios.defaults.headers['Authorization'] = "Bearer null";
        }
    }
});
export default store;
