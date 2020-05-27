import Vuex from 'vuex';
import Vue from 'vue';
import appointments from './modules/appointments';
import room from './modules/room';
import clinics from './modules/clinics.js';
import patient from './modules/patient'
import examination_type from './modules/examination_type'
import doctors from './modules/doctors';
import doctor from './modules/doctor'
import appointmentRequests from './modules/appointmentRequests'
import userProfile from './modules/userProfile'
import clinicAdmin from './modules/clinicAdmin'
import clinicProfile from './modules/clinicProfile'

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
        doctors,
        doctor,
        appointmentRequests,
        userProfile,
        clinicAdmin,
        clinicProfile
    },

    actions: {
        clearStore() {
            this.dispatch("appointmentRequests/resetAppointmentRequests");
            this.dispatch("appointments/resetAppointments");
            this.dispatch("clinicAdmin/resetClinicAdmin");
            this.dispatch("clinics/resetClinics");
            this.dispatch("doctor/resetDoctor");

            // TODO: Delete
            this.dispatch("doctors/resetDoctors");

            this.dispatch("examination_type/resetExaminationType");
            this.dispatch("patient/resetPatient");
            this.dispatch("room/resetRoom");
            this.dispatch("userProfile/resetUserProfile");

            localStorage.clear();
            sessionStorage.clear();
            Vue.$axios.defaults.headers['Authorization'] = "Bearer null";
        }
    }
});
export default store;
