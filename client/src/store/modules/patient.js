import Vue from 'vue'

const state = {
    patients: [],
    userProfile: null
};

const getters = {
    allPatients: (state) => state.patients,
    getUserProfile: (state) => state.userProfile,
};

const actions = {
    async fetchPatients({commit, rootState}) {
        var email = rootState.userDetails.user.email; // kaze da je email=""
        console.log(email);
        const response = await Vue.$axios.get('http://localhost:8081/patients/getPatients/' + email);
        commit('setPatients', response.data);
    },
    async fetchUserProfile({commit}){
        const response = await Vue.$axios.get('http://localhost:8081/auth/userDetails');
        commit('setUserProfile', response.data);
    }
};

const mutations = {
    setPatients: (state, patients) => state.patients = patients,
    setUserProfile: (state, user) => state.userProfile = user,
};

const namespaced = true;

export default{
    namespaced,
    state,
    getters,
    actions,
    mutations
}