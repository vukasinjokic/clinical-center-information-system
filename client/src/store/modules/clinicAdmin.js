import Vue from 'vue'

const state = {
    requests: []
};

const getters = {
    getRequest: (state) => state.requests,
};

const actions = {
    async fetchRequests({commit}){
        const response = await Vue.$axios.get('http://localhost:8081/clinicAdmins/getVacationRequests');
        commit('setRequests', response.data);
    },
    async acceptVacationRequest({commit}, id){
        try{
            await Vue.$axios.delete('http://localhost:8081/clinicAdmins/acceptRequest/' + id);
            commit('successfullyAccepted', id);
        }catch(error){
            commit('alertMessage', "Bad request");
        }
    },
    async declineRequest({commit}, object){
        try{
            await Vue.$axios.post('http://localhost:8081/clinicAdmins/declineRequest', object);
            commit('successfullyAccepted', object.id);
        }catch(error){
            commit('alertMessage', "Bad request");
        }
    }
};  

const mutations = {
    setRequests: (state, req) => state.requests = req,
    successfullyAccepted(state,id){
        const index = state.requests.findIndex(req => req.id === id);
        state.requests.splice(index, 1);
    },
    alertMessage(message){
        alert(message);
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