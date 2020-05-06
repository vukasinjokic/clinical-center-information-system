import axios from "axios";

const state = {
    rooms : [] 
};

const getters = {
    getAllRooms: (state) => state.rooms
    
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