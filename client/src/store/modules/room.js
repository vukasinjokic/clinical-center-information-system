import axios from "axios";
import Vue from 'vue';

const getDefaultState = () => {
    return {
        rooms : [],
        filteredRooms : [],
        availableTimes : null,
        clinicDoctors: [],
        //Key: doctors name, Value: doctor
        clinicDoctorsDict: {}
    }
};

const state = getDefaultState();

const getters = {
    getAllRooms: (state) => state.rooms,

    getFilteredRooms: (state) => () => {
        return state.filteredRooms;
    },

    getAvailableTimes: (state) => () =>{
        return state.availableTimes;
    },

    getClinicDoctorsDict: (state) => () =>{
        return state.clinicDoctorsDict;
    }
};

const actions = {
    

    filterRooms: ({commit}, payload) => {
        let search = payload.search;
        let duration = payload.duration;
        console.log(new Date());
        let date = payload.date == "" ? new Date() : payload.date;
        let availableTimes = {};
        let filteredRooms = state.rooms.filter(room => {
            let first = room.name.toUpperCase().match(search.toUpperCase()) || room.number.toUpperCase().match(search.toUpperCase());
            let firstAvailable = null;
            let eventStartDates = room.calendar.eventStartDates.slice();
            let eventEndDates = room.calendar.eventEndDates;

            let startSelectedDay = (new Date(date));
            startSelectedDay.setHours(7,0,0,0);
            let endSelectedDay = new Date(date);
            endSelectedDay.setHours(14,0,0,0);

            eventStartDates.unshift(startSelectedDay);
            
            let hourMinutes = duration.split(":");
            let durationMilliseconds = hourMinutes[0] * 1000 * 60 * 60 + hourMinutes[1] * 1000 * 60;
            let showRoom = false;
            for(var i = 1; i != eventStartDates.length; i++){
                let startAppDate = new Date(eventStartDates[i]);
                let endAppDate = new Date(eventEndDates[i-1]);
                if(endAppDate.getTime() <= startSelectedDay.getTime()){
                    continue;
                }

                //can the appointment be set before the first already set appointment 
                if(i == 1 && new Date(eventStartDates[i-1]).getTime() + durationMilliseconds <= startAppDate.getTime()){
                    showRoom = true;
                    firstAvailable = startSelectedDay;
                    break;
                }
                
                //can the appointment be set INBETWEEN already set appointments
                if(i < eventStartDates.length && endAppDate.getTime() + durationMilliseconds  <= new Date(eventStartDates[i + 1]).getTime()){
                    showRoom = true;
                    firstAvailable = endAppDate;
                    break;
                }
                //can the appontment be set AFTER the last already set appointment
                if(i == eventStartDates.length - 1 && firstAvailable == null && new Date(endAppDate).getTime() + durationMilliseconds <= endSelectedDay.getTime()){
                    showRoom = true;
                    firstAvailable = endAppDate;
                    break;
                }
                
            }
            if(firstAvailable == null){
                firstAvailable = startSelectedDay;
                showRoom = true;
            }
            showRoom = room.type.toUpperCase().match(payload.type.toUpperCase()) ? true : false;
            if(date == "") showRoom = true;
            availableTimes[room.id] = firstAvailable;
            return first  && showRoom;
        });
        if(duration == "00:00") availableTimes = null;   
        commit('setFilteredRooms', filteredRooms);
        commit('setAvailableTimes', availableTimes);        
    },

    async handleReservation({dispatch, commit}, payload){
        
        Vue.$axios.post("http://localhost:8081/clinicAdmins/handleReservation", payload)
        .then(response => {
            dispatch("snackbar/showSuccess", response.data, {root:true});
            dispatch('appointmentRequests/deleteRequest',  payload.requestId, {root : true});
        })
        .catch(err => {
            dispatch("snackbar/showError", err.data, {root: true});
        })
        const response = await Vue.$axios.get("http://localhost:8081/rooms/getRoom/" +  payload.room.id);
        commit('updatedRoom', response.data);        
        commit('resetTable');



    },

    async fetchRooms({commit}){
      
        const response = await  Vue.$axios.get("http://localhost:8081/rooms/getRooms");

        commit('setRooms', response.data);
        commit('setFilteredRooms', response.data);

    },

    async fetchClinicDoctors({commit}){
        
        let config = {
            headers: {
                Authorization: "Bearer " + localStorage.getItem("JWT"),
            }
          }
        const response = await axios.get('http://localhost:8081/clinicAdmins/getClinicDoctors/' + localStorage.getItem('user_email'), config);
        commit('setClinicDoctors', response.data);
        commit('setClinicDoctorsDict');

    },

    async deleteRoom({commit, dispatch}, id){
        try{
            await Vue.$axios.delete('http://localhost:8081/rooms/deleteRoom/' + id);
            commit('deletedRoom', id);
            dispatch('snackbar/showSuccess', "Successfully deleted.", {root: true});
        }catch(error){
            dispatch('snackbar/showWarning', "Can't delete. Room is scheduled.", {root: true});
        }
    },
    async addRoom({commit}, room){
        try{
            const response = await Vue.$axios.post('http://localhost:8081/rooms/addRoom', room);
            commit('addedRoom', response.data); 
        }catch(error){
            alert(error.response);
        }
    },
    async updateRoom({commit,dispatch}, room){
        try{
            const response = await Vue.$axios.post('http://localhost:8081/rooms/updateRoom', room);
            commit('updatedRoom', response.data);
            dispatch("snackbar/showSuccess", "Successfully updated", {root: true});
        }catch(error){
            dispatch("snackbar/showWarning", "Can't update. Room is scheduled.", {root:true});
        }
    },

    resetRoom({commit}) {
        commit("resetState");
    }

};

const mutations = {
    setRooms: (state, rooms) => {
        state.rooms = rooms
    },
    setFilteredRooms: (state, frooms) => {
        state.filteredRooms = frooms;
    },
    setAvailableTimes: (state, times) => {
        state.availableTimes = times;
    },
    setClinicDoctors: (state, doctors) =>{
        state.clinicDoctors = doctors;
    },
    setClinicDoctorsDict: (state) => {
        state.clinicDoctorsDict = {};
        state.clinicDoctors.forEach(doctor => {
            state.clinicDoctorsDict[doctor.firstName + ' ' + doctor.lastName] = doctor;
        });
    },
    deletedRoom(state,id) {
        const index = state.rooms.findIndex(type => type.id === id);
        state.rooms.splice(index,1);
    },
    addedRoom: (state,room) => state.rooms.push(room),

    updatedRoom (state,room){
        const index = state.rooms.findIndex(t => t.id === room.id);
        Object.assign(state.rooms[index], room);
    },

    resetTable: (state) => {
        state.filteredRooms = state.rooms;
        state.availableTimes = null;
    },
    resetState(state) {
        Object.assign(state, getDefaultState());
    }
};


const namespaced = true;

export default {
    namespaced,
    state,
    getters,
    actions,
    mutations
};