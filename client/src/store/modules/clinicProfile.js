import Vue from 'vue'

const state = {
    clinic: {
        id:"",
        name:"",
        address:"",
        description: "",
    },
    priceList: {
        id:"",
        items:[]
    }
};

const getters = {
    getClinic: (state) => state.clinic,
    getPriceList: (state) => state.priceList,
    getPriceListItems: (state) => state.priceList.items,
};

const actions = {
    async fetchClinic({commit}){
        try{
            const response = await Vue.$axios.get('http://localhost:8081/clinics/getClinic')
            commit('setClinic', response.data);
        }catch(error){
            alert(error.response.status);
        }
    },

    async fetchPriceList({commit}){
        try{
            const response = await Vue.$axios.get('http://localhost:8081/clinics/getPriceList');
            commit('setPriceList', response.data);
        }catch(error){
            alert(error.response.status);
        }
    },

    async updateClinic({commit}, clinic_obj){
        try{
            const response = await Vue.$axios.post('http://localhost:8081/clinics/updateClinic', clinic_obj);
            commit('setClinic', response.data);
            alert("Uspesno azurirano.");

        }catch(error){
            //commit('setClinic', state.clinic);
            alert(error.response.status);
        }
    },

    async updatePriceList({commit}, priceListItem){
        try{
            const response = await Vue.$axios.post('http://localhost:8081/clinics/updatePriceListItem', priceListItem);
            commit('commitListItem', response.data);
        
        }catch(error){
            alert("Vec postoji stavka cenovnika");
        }
    },

    async addPriceList({commit}, priceListItem){
        try{
            const response = await Vue.$axios.post('http://localhost:8081/clinics/addPriceListItem', priceListItem);
            commit('setNewItem', response.data);
        }catch(error){
            alert("Vec postoji stavka cenovnika");
        }
    }

};

const mutations ={
    setClinic: (state, clinic) => state.clinic = clinic,
    setPriceList: (state, priceList) => state.priceList = priceList,
    setNewItem: (state, priceListItem) => state.priceList.items.push(priceListItem),

    commitListItem: (state, priceListItem) => {
        const index = state.priceList.items.findIndex(it => it.id === priceListItem.id);
        Object.assign(state.priceList.items[index], priceListItem);
    },
};

const namespaced = true;

export default {
    namespaced,
    state,
    getters,
    actions,
    mutations
}