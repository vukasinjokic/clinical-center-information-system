import Vue from 'vue'

const state ={
    doctor_list: [],
    errorMessage: ""
};

const getters = {
    getDoctorList: (state) => state.doctor_list,
};

const actions = {
    async fetchDoctors({commit}){
        try{
            const response = await Vue.$axios.get('http://localhost:8081/doctors');
            commit('setDoctors', response.data);
        }catch(error){
            console.log(error.response.status);
            commit('alertError', error.response.status);
        }
    },

    async saveDoctor({commit}, doctor){
        try{
            const response = await Vue.$axios.post('http://localhost:8081/doctors/saveDoctor', doctor);
            commit('doctorAdded', response.data);
        }catch(error){
            commit('alertError',error.response.data.status);
        }
    },

    async deleteDoctor({commit}, id){
        try{
            const response = await Vue.$axios.delete('http://localhost:8081/doctors/deleteDoctor'+id);
            commit('onDeleteDoctor', id);
            commit('alertError', response.data);
        }catch(error){
            commit('alertError', error.response.data);
        }
    },

    async sendRequest({commit},req){
        const response = await Vue.$axios.post("http://localhost:8081/doctors/sendVacationRequest",req);
        commit('successfullyRequest',response);
    },
    async scheduleAppointment({commit}, appo){
        console.log("schedule");
        const response = await Vue.$axios.post('http://localhost:8081/doctors/scheduleAppointment', appo);
        commit('successfullyRequest',response.data);
        console.log(response.data)
    },
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