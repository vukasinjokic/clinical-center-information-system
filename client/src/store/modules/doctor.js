import Vue from 'vue'


const getDefaultState = () => {
    return {
        doctor_list: [],
        errorMessage: ""
    }
};

const state = getDefaultState();

const getters = {
    getDoctorList: (state) => state.doctor_list,
};

const actions = {
    async fetchDoctors({commit, dispatch}){
        try{
            const response = await Vue.$axios.get('http://localhost:8081/doctors');
            commit('setDoctors', response.data);
        }catch(error){
            console.log(error.response.status);
            dispatch('snackbar/showError', "Nesto se cudno desava", {root: true});
        }
    },

    async saveDoctor({commit, dispatch}, doctor){
        try{
            const response = await Vue.$axios.post('http://localhost:8081/doctors/saveDoctor', doctor);
            commit('doctorAdded', response.data);
            dispatch('snackbar/showSuccess', "Uspesno dodat doktor", {root: true});
        }catch(error){
            dispatch('snackbar/showError',"Neki error", {root: true});
        }
    },

    async deleteDoctor({commit, dispatch}, id){
        try{
            const response = await Vue.$axios.delete('http://localhost:8081/doctors/deleteDoctor'+id);
            commit('onDeleteDoctor', id);
            dispatch('snackbar/showSuccess', response.data, {root: true});
        }catch(error){
            dispatch('snackbar/showError', error.response.data, {root: true});
        }
    },

    async sendRequest({dispatch},req){
        await Vue.$axios.post("http://localhost:8081/doctors/sendVacationRequest",req);
        dispatch('snackbar/showSuccess',"Uspesno poslat zahtev",{ root: true });
    },
    async scheduleAppointment({dispatch}, appo){
        const response = await Vue.$axios.post('http://localhost:8081/doctors/scheduleAppointment', appo);
        dispatch('snackbar/showSuccess',response.data, {root: true});
    },

    async updateRecord({dispatch}, record){
        Vue.$axios.post('http://localhost:8081/doctors/updateMedicalRecord', record)
        .then(response => {
            dispatch("snackbar/showSuccess", response.data, {root:true});
        })
        .catch(err => {
            dispatch("snackbar/showError", err.data, {root:true});
        })
    },

    resetDoctor({commit}) {
        commit("resetState");
    }
};

const mutations ={
    doctorAdded: (state, doctor) => state.doctor_list.push(doctor),

    successfullyRequest(response){
        console.log(response.data);
        alert("Uspesno poslat zahtev");
    },

    setDoctors: (state, doctors) => state.doctor_list = doctors,

    alertError(state, text){ //ovo cemo prebaciti u errorHandler
        state.errorMessage = text;
        alert(text);
    },
    onDeleteDoctor(state, id){
        const index = state.doctor_list.findIndex(doc => doc.id === id);
        state.doctor_list.splice(index,1);
    },

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