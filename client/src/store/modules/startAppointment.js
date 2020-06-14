import Vue from 'vue';

const getDefaultState = () => {
    return {
        appointmentId : null,
        appointment : null,
        codebook : {
            diagnoses : [],
            medications : []
        },
        patientEmail : '',
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
    },
    getPatientEmail : (state) => () => {
        return state.patientEmail;
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

    async fetchPatientEmail({commit}, appId){
        const response = await Vue.$axios.get("http://localhost:8081/appointments/getPatientEmail/" +  (appId ? appId : this.getAppointmentId));
        commit('setPatientEmail', response.data);
    },

    async startAppointmentForPatient({commit, dispatch}, patient_email){
        await Vue.$axios.get("http://localhost:8081/appointments/getAppointmentForPatient/"+ patient_email)
        .then(response => {
            commit('setAppointment', response.data);
            commit('setPatientEmail', patient_email);
            commit('setAppointmentId', response.data.id);
        })
        .catch(err => {
            dispatch('snackbar/showError', err.response.data, {root:true});
            return Promise.reject();
        })
        
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
    setPatientEmail: (state, email) => (state.patientEmail = email)
};

const namespaced = true;

export default {
    namespaced,
    state,
    getters,
    actions,
    mutations
}