import Vue from 'vue';

const getDefaultState = () => {
    return {
        appointments: [],
        freeAppointmentRooms: [],
        examinationTypes: [],
        doctors: []
    }
};

const state = getDefaultState();

const getters = {
    allAppointments: (state) => state.appointments,
    allRooms: (state) => state.freeAppointmentRooms,
    allRoomsNumber: (state) => {
        var numbers = [];
        state.freeAppointmentRooms.forEach(room => numbers.push(room.number));
        return numbers;
    },
    getExaminationTypes: (state) => state.examinationTypes,
    getExaminationTypeNames: (state)=>{
        var names = [];
        state.examinationTypes.forEach(type => names.push({text: type.name, value: type.id}));
        return names;
    },
    getExaminationTypeOnlyNames: (state)=>{
        var names = [];
        state.examinationTypes.forEach(type => names.push(type.name));
        return names;
    },
    getTypeDuration: (state) => (id) => {
        return state.examinationTypes.find(type => type.id == id);
    },
    getDoctors: (state) => {
        var doctorsEmail = [];
        state.doctors.forEach(doc => doctorsEmail.push(doc.email));
        return doctorsEmail;
    }
};

const actions = {
    async fetchAppointments({commit}){
        const response = await Vue.$axios.get('http://localhost:8081/appointments/getAppointments');
        commit('setAppo', response.data);
    },

    async fetchPatientFinishedAppointments({commit}, mail){
        const response = await Vue.$axios.post('http://localhost:8081/appointments/getPatientAppointments', {mail});
        var finishedAppointments = response.data.filter(appointment => {
            return appointment.finished;
        })
        commit('setAppo', finishedAppointments);
    },

    async fetchRooms({commit}){
        const response = await Vue.$axios.get('http://localhost:8081/appointments/getRooms');
        commit('setFreeAppointmentsRooms', response.data);
    },
    async fetchTypes({commit}){
        const response = await Vue.$axios.get('http://localhost:8081/appointments/getTypes');
        commit('setTypes', response.data);
    },
    async fetchDoctors({commit}, ex_type){
        const response = await Vue.$axios.get('http://localhost:8081/appointments/getDoctors/'+ex_type);
        commit('setDoctors',response.data);
    },
    async saveAppointment({commit,dispatch}, appo){
        try{
            const response = await Vue.$axios.post('http://localhost:8081/appointments/addAppointment', appo);
            commit('newApp', response.data);
            dispatch('snackbar/showSuccess', 'Uspesno dodat slobodan pregled', {root: true});
        }catch(error){
            dispatch('snackbar/showWarning',"Doktor ili soba su zauzeti u zadato vreme.",{root:true}); // moze i da je doktor na odmoru
        }
    },
   

    resetAppointments({commit}) {
        commit("resetState");
    }
};

const mutations = {
    setAppo: (state, appointments) => (state.appointments = appointments),
    setFreeAppointmentsRooms: (state, rooms) => (state.freeAppointmentRooms = rooms),
    setTypes: (state, types) => (state.examinationTypes = types),
    setDoctors: (state,doctors) => (state.doctors = doctors),
    newApp: (state, newAppointment) => state.appointments.unshift(newAppointment),

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