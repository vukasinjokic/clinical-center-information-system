import Vue from 'vue';

const getDefaultState = () => {
    return {
        appointmentId : null,
        appointment : null,
        codebook : {
            diagnoses : [],
            medications : []
        }
    }
};

const state = getDefaultState();

const getters = {
    getAppointment : (state) => () => {
        return state.appointment;
    },
    getCodebook : (state) => () => {
        return state.codebook;
    },
    getAppointmentId : (state) => () => {
        return state.appointmentId;
    }
};

const actions = {
    resetAppointment({commit}) {
        commit("resetState");
    },

    async fetchAppointment({commit}, appId){
        const response = await Vue.$axios.get("http://localhost:8081/appointments/getAppointment/" + (appId ? appId : this.getAppointmentId));
        commit('setAppointment', response.data);
    },

    async fetchCodebook({commit}, appId){
        const response = await Vue.$axios.get("http://localhost:8081/appointments/getCodebook/" +  (appId ? appId : this.getAppointmentId));
        commit('setCodebook', response.data);
    },

    async handleFinishAppointment({commit}, appointmentToFinish){
        const response = await Vue.$axios.post("http://localhost:8081/appointments/handleAppointmentFinish", appointmentToFinish);
        console.log(commit);
        console.log(response);
    },

    setInitialAppointmentId({commit}, initialAppointmentId){
        commit('setAppointmentId', initialAppointmentId);
    }

    
};

const mutations = {

    resetState (state) {
        Object.assign(state, getDefaultState())
    },
    setAppointment : (state, appointment) => (state.appointment = appointment),
    setCodebook : (state, codebook) => (state.codebook = codebook),
    setAppointmentId : (state, id) => (state.appointmentId = id),
};

const namespaced = true;

export default {
    namespaced,
    state,
    getters,
    actions,
    mutations
}