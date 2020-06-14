import Vue from 'vue'

const getDefaultState = () => {
    return {
        requests: []
    }    
};

const state = getDefaultState();

const getters = {
    getRequest: (state) => state.requests,
};

const actions = {
    async fetchRequests({commit}){
        const response = await Vue.$axios.get('http://localhost:8081/clinicAdmins/getVacationRequests');
        commit('setRequests', response.data);
    },
    async acceptVacationRequest({commit, dispatch}, id){
        try{
            await Vue.$axios.delete('http://localhost:8081/clinicAdmins/acceptRequest/' + id);
            commit('successfullyAccepted', id);
            dispatch("snackbar/showSuccess", "Request accepted", {root: true});
        }catch(error){
            dispatch("snackbar/showError", "Doctor has scheduled appointment in that period.", {root: true});
        }
    },
    async declineRequest({commit, dispatch}, object){
        try{
            await Vue.$axios.post('http://localhost:8081/clinicAdmins/declineRequest', object);
            commit('successfullyAccepted', object.id);
            dispatchEvent("snackbar/showSuccess", "Successfully declined", {root: true});
        }catch(error){
            dispatch('snackbar/showError', "Some error", {root: true});
        }
    },

    resetClinicAdmin({commit}) {
        commit("resetState");
    }
};  

const mutations = {
    setRequests: (state, req) => state.requests = req,
    successfullyAccepted(state,id){
        const index = state.requests.findIndex(req => req.id === id);
        state.requests.splice(index, 1);
    },


    resetState (state) {
        Object.assign(state, getDefaultState())
    }
};

const namespaced = true;

export default{
    namespaced,
    state,
    getters,
    actions,
    mutations
}