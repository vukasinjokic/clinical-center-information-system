import axios from "axios";
import Vue from 'vue';
const state = {
    rooms : [],
    filteredRooms : [],
    availableTimes : null,
    clinicDoctors: [],
    //Key: doctors name, Value: doctor
    clinicDoctorsDict: {}
};

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
        let date = payload.date == "" ? new Date() : payload.date;
        let availableTimes = {};
        let filteredRooms = state.rooms.filter(room => {
            let first = room.name.toUpperCase().match(search.toUpperCase()) || room.number.toUpperCase().match(search.toUpperCase());
            let firstAvailable = null;
            let eventStartDates = room.calendar.eventStartDates.slice();
            let eventEndDates = room.calendar.eventEndDates;

            let startSelectedDay = (new Date(date));
            startSelectedDay.setHours(8,0,0,0);
            let endSelectedDay = new Date(date);
            endSelectedDay.setHours(15,0,0,0);

            eventStartDates.unshift(startSelectedDay);
            
            let hourMinutes = duration.split(":");
            let durationMilliseconds = hourMinutes[0] * 1000 * 60 * 60 + hourMinutes[1] * 1000 * 60;
            let showRoom = false;
            for(var i = 1; i != eventStartDates.length; i++){
                let startAppDate = new Date(eventStartDates[i]);
                let endAppDate = new Date(eventEndDates[i-1]);
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
            showRoom = room.type.toUpperCase().match(payload.type.toUpperCase()) ? true : false;
            if(date == "") showRoom = true;
            availableTimes[room.id] = firstAvailable;
            return first  && showRoom;
        });
        if(duration == "00:00") availableTimes = null;   
        commit('setFilteredRooms', filteredRooms);
        commit('setAvailableTimes', availableTimes);        
    },

    async handleReservation({commit}, payload){
        
        const response = await Vue.$axios.post("http://localhost:8081/clinicAdmins/handleReservation", payload);
        
        console.log(commit);
        console.log(response);

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

    async deleteRoom({commit}, id){
        try{
            await Vue.$axios.delete('http://localhost:8081/rooms/deleteRoom/' + id);
            commit('deletedRoom', id);
        }catch(error){
            alert(error.response);
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
    async updateRoom({commit}, room){
        try{
            const response = await Vue.$axios.post('http://localhost:8081/rooms/updateRoom', room);
            commit('updatedRoom', response.data);
        }catch(error){
            alert(error.response.status);
        }
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