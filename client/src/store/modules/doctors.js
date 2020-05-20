import Vue from 'vue';

const state = {
    doctors: []
};

const getters = {
    getDoctors: (state) => state.doctors
};

const actions = {
    async fetchDoctors({commit}){
        await Vue.$axios.get('http://localhost:8081/doctors')
        .then(response => {
            commit('setDoctors', response.data);
        })
        .catch(() => { alert("Nemate pravo pregleda svih doktora") });
    },

    doctorsSetter({commit}, doctors) {
        commit('setDoctors', doctors);
    }
    
};

const mutations = {
    setDoctors: (state, doctors) => state.doctors = doctors
};

const namespaced = true;

export default {
    namespaced,
    state,
    getters,
    actions,
    mutations
}