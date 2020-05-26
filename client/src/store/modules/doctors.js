import Vue from 'vue';

const getDefaultState = () => {
    return {
        doctors: []    
    }
};

const state = getDefaultState();

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
    },
    
    resetDoctors({commit}) {
        commit("resetState");
    }
};

const mutations = {
    setDoctors: (state, doctors) => state.doctors = doctors,

    resetState(state) {
        Object.assign(state, getDefaultState());
    }
};

const namespaced = true;

export default {
    namespaced,
    state,
    getters,
    actions,
    mutations
}