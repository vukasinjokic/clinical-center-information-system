import Vue from 'vue'

const state ={

};

const getters = {

};

const actions = {
    async sendRequest({commit},req){
        const response = await Vue.$axios.post("http://localhost:8081/doctors/sendVacationRequest",req);
        commit('successfullyRequest',response);
    }
};

const mutations ={
    successfullyRequest(response){
        alert(response.status);
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