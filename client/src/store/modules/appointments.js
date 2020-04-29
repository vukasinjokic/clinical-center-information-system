import axios from 'axios';

const state = {
    appointments: []
};

const getters = {
    allAppointments: (state) => state.appointments
};

const actions = {
    async fetchAppointments({commit}){
        const response = await axios.get('http://localhost:8081/appointments/getAppointments');
  
<<<<<<< HEAD
       commit('setAppo', response.data);
=======
    //    commit('setAppo', [{id : "Hello from appoint view", price : "1000"}]);
        commit('setAppo', response.data);
>>>>>>> 54e103cf5e364b8f34f67adf741cf52101471d8e
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