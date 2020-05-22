
const state = {
    doctors: []
};

const getters = {
    getDoctors: (state) => state.doctors
};

const actions = {

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