import Vue from 'vue'

const getDefaultState = () => {
    return {
        patients: [],
        userProfile: null  
    }
};

const state = getDefaultState();

const getters = {
    allPatients: (state) => state.patients,
    getUserProfile: (state) => state.userProfile,
};

const actions = {
    async fetchPatients({commit, rootState}) {
        console.log(rootState);
        var email = localStorage.getItem("user_email");
        // var email = rootState.userDetails.user.email; // kaze da je email=""
        console.log(email);
        const response = await Vue.$axios.get('http://localhost:8081/patients/getPatients/' + email);
        commit('setPatients', response.data);
    },
    async fetchUserProfile({commit}){
        const response = await Vue.$axios.get('http://localhost:8081/auth/userDetails');
        commit('setUserProfile', response.data);
    },

    resetPatient({commit}) {
        commit("resetState");
    }
};

const mutations = {
    setPatients: (state, patients) => state.patients = patients,
    setUserProfile: (state, user) => state.userProfile = user,

    resetState(state) {
        Object.assign(state, getDefaultState());
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