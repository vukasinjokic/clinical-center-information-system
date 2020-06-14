import Vue from 'vue';

const getDefaultState = () => {
    return {
        clinics: []
    }
};

const state = getDefaultState();

const getters = {
    allClinics: (state) => state.clinics
};

const actions = {
    async fetchClinicsPatient({commit, dispatch}){
        await Vue.$axios.get('http://localhost:8081/clinics/getClinicsPatient')
        .then(response => {
            for (let i = 0; i < response.data.length; i++) {
                var clinic = response.data[i];
                for (let j = 0; j < clinic.doctors.length; j++) {
                    const doctor = clinic.doctors[j];
                    var examinationTypeName = doctor.examinationType.name;
                    doctor.price = clinic.priceList[examinationTypeName];
                }
                clinic["filteredDoctors"] = clinic.doctors;

            }
            commit('setClinics', response.data);
        })
        .catch(() => { dispatch('snackbar/showError', "Nemate pravo pregleda svih klinika", {root: true}); });
    },

    async fetchClinics({commit, dispatch}){
        await Vue.$axios.get('http://localhost:8081/clinics/getClinics')
        .then(response => {
            commit('setClinics', response.data);
        })
        .catch(() => { dispatch('snackbar/showError', "Nemate pravo pregleda svih klinika", {root: true}); });
    },

    clinicsSetter({commit}, clinics) {
        commit('setClinics', clinics);
    },

    async saveClinic({commit}, clinic){
        const response = await Vue.$axios.post('http://localhost:8081/clinics/addClinic', clinic)
        .catch(error => {
            this.$store.dispatch('snackbar/showError', error.response.data, {root: true});
        });
        commit('newClinic', response.data);
    },

    resetClinics({commit}) {
        commit("resetState");
    }

};

const mutations = {
    setClinics: (state, clinics) => (state.clinics = clinics),
    newClinic: (state, newClinic) => state.clinics.unshift(newClinic),

    resetState (state) {
        Object.assign(state, getDefaultState())
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