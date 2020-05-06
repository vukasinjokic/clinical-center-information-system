import axios from "axios";

const state = {
    rooms : [] 
};

const getters = {
    getAllRooms: (state) => () =>{
        return state.rooms;
      
    },

    getFiltered: (state) => (search,date,duration) => {
        let availableAppointments = {};
        let rooms = state.rooms.filter(room => {
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
            if(date == "") showRoom = true;
            availableAppointments[room.id] = firstAvailable;
            return first  && showRoom;
        });
        return {'availableAppointments' : availableAppointments, 'rooms': rooms};
        
    }
};

const actions = {
    async fetchRooms({commit}){
      
        let config = {
            headers: {
                Authorization: "Bearer " + localStorage.getItem("JWT"),
            }
          }
        const response = await axios.get("http://localhost:8081/operationRooms/getOperationRooms", config);

        commit('setRooms', response.data);
    },

    getRooms(){
        return state.rooms;
    }
};

const mutations = {
    setRooms: (state, rooms) => state.rooms = rooms
};


const namespaced = true;

export default {
    namespaced,
    state,
    getters,
    actions,
    mutations
};