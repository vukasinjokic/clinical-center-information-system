import Vuex from 'vuex';
import Vue from 'vue';
import appointments from './modules/appointments';
import room from './modules/room'

// load vuex
Vue.use(Vuex);

// create store
export default new Vuex.Store({
    modules: {
        appointments,
        room
    }
});