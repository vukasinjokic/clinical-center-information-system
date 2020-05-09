import Vue from 'vue'

const state = {
    patients: [],
    clinic: "a"
};

const getters = {
    allPatients: (state) => state.patients,
};

const actions = {
    async fetchPatients({commit, rootState}) {
        var email = rootState.userDetails.user.email; // kaze da je email=""
        console.log(email);
        const response = await Vue.$axios.get('http://localhost:8081/patients/getPatients/' + email);
        commit('setPatients', response.data);
    }
};

const mutations = {
    setPatients: (state, patients) => state.patients = patients,
};

const namespaced = true;

export default{
    namespaced,
    state,
    getters,
    actions,
    mutations
}