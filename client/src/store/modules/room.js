import axios from "axios";

const state = {
    rooms : [],
    filteredRooms : [],
    availableTimes : null,
    clinicDoctors: [],
    //Key: doctors name, Value: doctor
    clinicDoctorsDict: {}
};

const getters = {
    getAllRooms: (state) => () =>{
        return state.rooms; 
    },

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

    async alertDoctors({commit}, doctorsNames){
        
//         let config = {
//             headers: {
//                 Authorization: "Bearer " + localStorage.getItem("JWT"),
//             }
//         }
        let doctors = [];
        doctorsNames.forEach(name => {
            doctors.push(state.clinicDoctorsDict[name]);
        });
        const response = await axios.post("http://localhost:8081/clinicAdmins/alertDoctorsOperation", doctors);
        console.log(commit);
        console.log(response);

    },

    async fetchRooms({commit}){
      
        let config = {
            headers: {
                Authorization: "Bearer " + localStorage.getItem("JWT"),
            }
          }
        const response = await axios.get("http://localhost:8081/rooms/getRooms", config);

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
    
};


const namespaced = true;

export default {
    namespaced,
    state,
    getters,
    actions,
    mutations
};