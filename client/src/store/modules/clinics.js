import Vue from 'vue';

const state = {
    clinics: [],
};

const getters = {
    allClinics: (state) => state.clinics
};

const actions = {
    async fetchClinics({commit}){
        await Vue.$axios.get('http://localhost:8081/clinics')
        .then(response => {
            for (let i = 0; i < response.data.length; i++) {
                var clinic = response.data[i];
                clinic["filteredDoctors"] = clinic.doctors;
            }
            commit('setClinics', response.data);
        })
        .catch(() => { alert("Nemate pravo pregleda svih klinika") });
    },

    clinicsSetter({commit}, clinics) {
        commit('setClinics', clinics);
    },

    async saveClinic({commit}, clinic){
        const response = await Vue.$axios.post('http://localhost:8081/clinics/addClinic', clinic);
        commit('newClinic', response.data);
    }

};

const mutations = {
    setClinics: (state, clinics) => (state.clinics = clinics),
    newClinic: (state, newClinic) => state.clinics.unshift(newClinic)
};

const namespaced = true;

export default {
    namespaced,
    state,
    getters,
    actions,
    mutations
}