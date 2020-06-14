import Vue from 'vue'

const getDefaultState = () => {
    return {
        appointmentRequests: []
    }
} ;

const state = getDefaultState();

const getters = {
    
    getAppointmentRequests : (state) => () => {
        return state.appointmentRequests;
    },

}

const actions = {
    async fetchAppRequests({commit}){
        const response = await Vue.$axios.get("http://localhost:8081/appointmentRequests/getAppointmentRequests");
        commit('setAppointmentRequests', response.data);
    },

    async deleteRequest({commit}, id){
        try{
            commit('deletedRequest', id);
        }catch(error){
            alert(error.response);
        }
    },

    resetAppointmentRequests({commit}) {
        commit("resetState");
    }
}

const mutations = {
    setAppointmentRequests: (state, requests) => {
        state.appointmentRequests = requests;
    },

    deletedRequest(state,id) {
        const index = state.appointmentRequests.findIndex(type => type.id === id);
        state.appointmentRequests.splice(index,1);
    },

    resetState (state) {
        Object.assign(state, getDefaultState())
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