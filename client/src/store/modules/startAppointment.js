import Vue from 'vue';

const getDefaultState = () => {
    return {
        appointment : null
    }
};

const state = getDefaultState();

const getters = {
    getAppointment : (state) => () => {
        return state.appointment;
    }
};

const actions = {
    resetAppointment({commit}) {
        commit("resetState");
    },

    async fetchAppointment({commit}, appointmentId){
        const response = await Vue.$axios.get("http://localhost:8081/appointments/getAppointment/" + appointmentId);
        commit('setAppointment', response.data);
    }
};

const mutations = {

    resetState (state) {
        Object.assign(state, getDefaultState())
    },
    setAppointment : (state, appointment) => (state.appointment = appointment),
};

const namespaced = true;

export default {
    namespaced,
    state,
    getters,
    actions,
    mutations
}