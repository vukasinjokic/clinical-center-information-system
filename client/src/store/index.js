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
import appointmentRequests from './modules/appointmentRequests'
import userProfile from './modules/userProfile'
import clinicAdmin from './modules/clinicAdmin'
import startAppointment from './modules/startAppointment'

// load vuex
Vue.use(Vuex);

// create store
const store = new Vuex.Store({
    modules: {
        appointments,
        room,
        clinics,
        userDetails,
        patient,
        examination_type,
        doctors,
        doctor,
        appointmentRequests,
        userProfile,
        clinicAdmin,
        startAppointment
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
            this.dispatch("userDetails/resetUserDetails");
            this.dispatch("userProfile/resetUserProfile");

            this.dispatch("startAppointment/resetAppointment")

            localStorage.clear();
            sessionStorage.clear();
        }
    }
});
export default store;
