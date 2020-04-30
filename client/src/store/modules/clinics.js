import axios from 'axios';

const state = {
    clinics: [],
};

const getters = {
    allClinics: (state) => state.clinics
};

const actions = {
    async fetchClinics({commit}){
        const response = await axios.get('http://localhost:8081/clinics');
        commit('setClinics', response.data);
    },
};

const mutations = {
    setClinics: (state, clinics) => (state.clinics = clinics)
};

const namespaced = true;

export default {
    namespaced,
    state,
    getters,
    actions,
    mutations
}