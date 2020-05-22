import Vue from 'vue'

const state ={
    doctor_list: [],
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
            commit('alertError', error.response.status);
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
    successfullyRequest(response){
        console.log(response.data);
        alert("Uspesno poslat zahtev");
    },
    setDoctors: (state, doctors) => state.doctor_list = doctors,

    alertError(error){
        alert(error);
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