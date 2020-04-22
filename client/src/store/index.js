import Vuex from 'vuex';
import Vue from 'vue';
import appointements from './modules/appointments';

// load vuex
Vue.use(Vuex);

// create store
export default new Vuex.Store({
    modules: {
        appointements
    }
});