import axios from "axios";

const state = {
    rooms : [] 
};

const getters = {
    getAllRooms: (state) => state.rooms
    
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