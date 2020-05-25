import Vue from 'vue'

const getDefaultState = () => {
    return {

    }
};

const state = getDefaultState();

const getters = {

};

const actions = {
    async sendRequest({commit},req){
        const response = await Vue.$axios.post("http://localhost:8081/doctors/sendVacationRequest",req);
        commit('successfullyRequest',response);
    },
    async scheduleAppointment({commit}, appo){
        console.log("schedule");
        const response = await Vue.$axios.post('http://localhost:8081/doctors/scheduleAppointment', appo);
        commit('successfullyRequest',response.data);
        console.log(response.data)
    },

    resetDoctor({commit}) {
        commit("resetState");
    }
};

const mutations ={
    successfullyRequest(response){
        console.log(response.data);
        alert("Uspesno poslat zahtev");
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