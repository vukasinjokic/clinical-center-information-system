import Vue from 'vue'

const getDefaultState = () => {
    return {
        patients: [],
        userProfile: null,
        medicalRecord: {
            reports: [],
            prescriptions: []
        },
        recordBackup: {}
    }
};

const state = getDefaultState();

const getters = {
    allPatients: (state) => state.patients,
    getUserProfile: (state) => state.userProfile,
    getMedicalRecord: (state) => state.medicalRecord,
    getRecordBackup: (state) => state.recordBackup
};

const actions = {
    async fetchPatients({commit}) {
        var email = localStorage.getItem("user_email");
        // var email = rootState.userDetails.user.email; // kaze da je email=""
     
        const response = await Vue.$axios.get('http://localhost:8081/patients/getPatients/' + email);
        commit('setPatients', response.data);
    },

    async fetchMedicalRecord({commit}, email) {
        try {    
            const response = await Vue.$axios.get('http://localhost:8081/patients/medicalRecord/' + email);
            commit("setMedicalRecord", JSON.parse(JSON.stringify(response.data)));
            commit("setRecordBackup", JSON.parse(JSON.stringify(response.data)));
        } catch(error) {
            this.$store.dispatch('snackbar/showError', error.response.data, {root: true});
        }
    },

    
    
    async fetchUserProfile({commit}){
        const response = await Vue.$axios.get('http://localhost:8081/auth/userDetails');
        commit('setUserProfile', response.data);
    },

    

    resetMedicalRecord({commit}, record){
        commit("setMedicalRecord", JSON.parse(JSON.stringify(record)));
    },

    resetPatient({commit}) {
        commit("resetState");
    }
};

const mutations = {
    setPatients: (state, patients) => state.patients = patients,
    setUserProfile: (state, user) => state.userProfile = user,
    setMedicalRecord: (state, medicalRecord) => {
        state.medicalRecord = medicalRecord
    },
    setRecordBackup: (state, backup) => state.recordBackup = backup,

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