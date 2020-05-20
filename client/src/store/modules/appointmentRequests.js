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
    },

    async deleteRequest({commit}, id){
        try{
            await axios.delete('http://localhost:8081/appointmentRequests/deleteRequest/' + id);
            commit('deletedRequest', id);
        }catch(error){
            alert(error.response);
        }
    },
}

const mutations = {
    setAppointmentRequests: (state, requests) => {
        state.appointmentRequests = requests;
    },

    deletedRequest(state,id) {
        const index = state.appointmentRequests.findIndex(type => type.id === id);
        state.appointmentRequests.splice(index,1);
    },
}
const namespaced = true;

export default {
    namespaced,
    state,
    getters,
    actions,
    mutations
}