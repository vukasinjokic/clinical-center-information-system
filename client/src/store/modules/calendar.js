import Vue from 'vue';

const getDefaultState = () => {
    return {
        events : []
    }
};

const state = getDefaultState();

const getters = {
    getEvents : (state) => () => {return state.events},
};

const actions = {
    
    async fetchCalendar({dispatch}){
        const response = await Vue.$axios.get("http://localhost:8081/doctors/calendar/"+localStorage.getItem('user_email'));
        let eventStartDates = [];
        response.data.eventStartDates.forEach(date => {
            let eventStartDate= new Date(date)
            let formatted_date = eventStartDate.getFullYear() + "-" + (eventStartDate.getMonth() + 1) + "-" + eventStartDate.getDate() + " " + eventStartDate.getHours() + ":" + eventStartDate.getMinutes();
            eventStartDates.push(formatted_date)
        }); 
        let eventEndDates = [];
        response.data.eventEndDates.forEach(date => {
            let eventEndDate= new Date(date)
            let formatted_date = eventEndDate.getFullYear() + "-" + (eventEndDate.getMonth() + 1) + "-" + eventEndDate.getDate() + " " + eventEndDate.getHours() + ":" + eventEndDate.getMinutes();
            eventEndDates.push(formatted_date)
        }); 
        let payload = {eventStartDates : eventStartDates, eventEndDates : eventEndDates, names : response.data.eventNames, appointmentIds : response.data.appointmentIds};
        dispatch('formatEvents', payload);
        
    },

    formatEvents({commit}, payload){
        let events = [];
        let colors = ['blue', 'indigo', 'deep-purple', 'cyan', 'green', 'orange', 'grey darken-1'];

        for (let i = 0; i < payload.names.length; i++) {

            events.push({
            name: payload.names[i],
            start: payload.eventStartDates[i],
            end: payload.eventEndDates[i],
            color: colors[Math.floor((colors.length) * Math.random())],
            appointmentId : payload.appointmentIds[i]
            })
        }
        commit('setEvents', events);    
    },

    startAppointment({dispatch}, initialAppointmentId){
        dispatch('startAppointment/setInitialAppointmentId',  initialAppointmentId, {root : true});
    },

    resetCalendar({commit}) {
        commit("resetState");
    }

    //     round (a, b) {
        
//         return Math.floor((b - a + 1) * Math.random()) + a
//     },
};

const mutations = {
    setEvents: (state, events) => (state.events = events),
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