import axios from "axios";

const state = {
    rooms : [] 
};

const getters = {
    getAllRooms: (state) => state.rooms,

    getFiltered: (state) => (search,date) => {
        return state.rooms.filter(room => { 
            let first = room.name.match(search) || room.number.match(search);
            let th = false;
            room.calendar.eventStartDates.forEach(el =>{
                if(el.substring(0,10).includes(date))
                    th = true;
                
            });
            if(date == "")
                th = true;
            return first && th;
            // return room.name.match(search) || room.number.match(search)
            //    && room.calendar.eventStartDates.filter(x=>{
            //        console.log(x.substring(0,9).includes(date))
            //        return x.substring(0,9).includes(date);
               });
        
    }
};

const actions = {
    async fetchRooms({commit}){
        const response = await axios.get("http://localhost:8081/operationRooms/getOperationRooms");

        commit('setRooms', response.data);
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