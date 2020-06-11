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
        let names = response.data.eventNames;
        console.log(response.data);
        if(response.data.vacationDates.length != 0){
            let vacationStart = new Date(response.data.vacationDates[0]);
            let vacationEnd = new Date(response.data.vacationDates[1]);
            let formatted_start = vacationStart.getFullYear() + "-" + (vacationStart.getMonth() + 1) + "-" + vacationStart.getDate() + " " + vacationStart.getHours() + ":" + vacationStart.getMinutes();
            let formatted_end = vacationEnd.getFullYear() + "-" + (vacationEnd.getMonth() + 1) + "-" + vacationEnd.getDate() + " " + vacationEnd.getHours() + ":" + vacationEnd.getMinutes();
            eventStartDates.push(formatted_start);
            eventEndDates.push(formatted_end);
            names.push("Vacation/Leave");
        }
        let payload = {eventStartDates : eventStartDates, eventEndDates : eventEndDates, names: names, appointmentIds : response.data.appointmentIds};
        dispatch('formatEvents', payload);
        
    },

    formatEvents({commit}, payload){
        let events = [];

        for (let i = 0; i < payload.names.length; i++) {

            events.push({
            name: payload.names[i],
            start: payload.eventStartDates[i],
            end: payload.eventEndDates[i],
            color: 'blue',
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