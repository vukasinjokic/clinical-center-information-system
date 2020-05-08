import Vuex from 'vuex';
import Vue from 'vue';
import appointments from './modules/appointments';
import room from './modules/room';
import clinics from './modules/clinics.js';
import userDetails from './modules/userDetails.js';

// load vuex
Vue.use(Vuex);

// create store
export default new Vuex.Store({
    modules: {
        appointments,
        room,
        clinics,
        userDetails
    }
});
