import Vue from 'vue'

const state = {
    clinic: {
        id:"",
        name:"",
        address:"",
        description: "",
    }
};

const getters = {
    getClinic: (state) => state.clinic,
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

    async updateClinic({commit}, clinic_obj){
        try{
            const response = await Vue.$axios.post('http://localhost:8081/clinics/updateClinic', clinic_obj);
            commit('setClinic', response.data);
            alert("Uspesno azurirano.");

        }catch(error){
            //commit('setClinic', state.clinic);
            alert(error.response.status);
        }
    }
};

const mutations ={
    setClinic: (state, clinic) => state.clinic = clinic,
};

const namespaced = true;

export default {
    namespaced,
    state,
    getters,
    actions,
    mutations
}