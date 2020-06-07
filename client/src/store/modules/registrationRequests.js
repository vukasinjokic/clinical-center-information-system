import Vue from 'vue';

const getDefaultState = () => {
    return {
        registrationRequests: []
    }
} ;

const state = getDefaultState();

const getters = {
    
    getregistrationRequests : (state) => () => {
        return state.registrationRequests;
    },

}

const actions = {
    async fetchRegRequests({commit}){
        const response = await Vue.$axios.get("http://localhost:8081/registrationRequests/getRegistrationRequests");
        commit('setregistrationRequests', response.data);
    },

    async deleteRequest({commit}, id){
        try{
            await Vue.$axios.delete('http://localhost:8081/registrationRequests/deleteRequest/' + id);
            commit('deletedRequest', id);
        }catch(error){
            alert(error.response);
        }
    },

    resetregistrationRequests({commit}) {
        commit("resetState");
    }
}

const mutations = {
    setregistrationRequests: (state, requests) => {
        state.registrationRequests = requests;
    },

    deletedRequest(state,id) {
        const index = state.registrationRequests.findIndex(type => type.id === id);
        state.registrationRequests.splice(index,1);
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