//import axios from 'axios';

const state = {
    appointments: []
};

const getters = {
    allAppointments: (state) => state.appointments
};

const actions = {
    async fetchAppointments({commit}){
       // const response = await axios.get('http://localhost:8081/appointments/getAppointments');
  
       commit('setAppo', [{
            id : "1",
            price : "1000",
            discount : "12" 
        }])
    }   
};

const mutations = {
    setAppo: (state, appointments) => (state.appointments = appointments)
};

const namespaced = true;

export default {
    namespaced,
    state,
    getters,
    actions,
    mutations
}