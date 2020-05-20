import axios from 'axios'

const state = {
    appointmentRequests : []
}

const getters = {
    
    getAppointmentRequests : (state) => () => {
        return state.appointmentRequests;
    },

}

const config = {
    headers: {Authorization: "Bearer " + localStorage.getItem("JWT")}
}

const actions = {
    async fetchAppRequests({commit}){
        const response = await axios.get("http://localhost:8081/appointmentRequests/getAppointmentRequests", config);
        commit('setAppointmentRequests', response.data);
    }
}

const mutations = {
    setAppointmentRequests: (state, requests) => {
        state.appointmentRequests = requests;
    }
}
const namespaced = true;

export default {
    namespaced,
    state,
    getters,
    actions,
    mutations
}