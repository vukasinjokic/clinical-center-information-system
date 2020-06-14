import Vue from 'vue'

const getDefaultState = () => {
    return {
        codeBook: {
            medications : [],
            diagnoses : []
        }
    }    
};

const state = getDefaultState();

const getters = {
    getCodeBook: (state) => state.codeBook,
};

const actions = {
    async fetchCodeBook({commit}){
        const response = await Vue.$axios.get('http://localhost:8081/clinicCenterAdmin/getCodeBook');
        commit('setCodeBook', response.data);
    },

    async addMedication({commit, dispatch}, medication){
        Vue.$axios.post('http://localhost:8081/clinicCenterAdmin/addMedication', medication)
        .then(() =>{
            let medicationObj = {};
            medicationObj[medication.code] = medication.name;
            commit('addedMedication', medicationObj);
            dispatch('snackbar/showSuccess', "Successfuly added medication", {root: true});
        })
        .catch(() => {
            dispatch('snackbar/showError',"Medication with that code already exists", {root:true});
        })
    },

    async addDiagnosis({commit, dispatch}, diagnosis){
        Vue.$axios.post('http://localhost:8081/clinicCenterAdmin/addDiagnosis', diagnosis)
        .then(() =>{
            let diagnosisObj = {};
            diagnosisObj[diagnosis.code] = diagnosis.name
            commit('addedDiagnosis',diagnosisObj);
            dispatch('snackbar/showSuccess', "Successfuly added diagnosis", {root: true});
        })
        .catch(() => {
            dispatch('snackbar/showError',"Diagnosis with that code already exists", {root:true});
        })
    },

    resetClinicCenterAdmin({commit}) {
        commit("resetState");
    }
};  

const mutations = {
    setCodeBook: (state, codebook) => state.codeBook = codebook,
    
    addedMedication: (state, med) => {
        state.codeBook.medications[Object.keys(med).pop()] = Object.values(med).pop();
    },
    addedDiagnosis: (state, diagnosis) => state.codeBook.diagnoses[Object.keys(diagnosis).pop()] = Object.values(diagnosis).pop(),
    resetState (state) {
        Object.assign(state, getDefaultState())
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