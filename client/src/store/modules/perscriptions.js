import Vue from 'vue';

const getDefaultState = () => {
    return {
        perscriptions: []
    }
} ;

const state = getDefaultState();

const getters = {
    
    getPerscriptions : (state) => () => {
        return state.perscriptions;
    },

}

const actions = {
    async fetchPerscriptions({commit}){
        const response = await Vue.$axios.get("http://localhost:8081/clinics/getPerscriptions");
        commit('setPerscriptions', response.data);
    },

    async handleDenyingPerscription({commit}, id){
        try{
            await Vue.$axios.post('http://localhost:8081/perscriptions/deletePerscription/' + id);
            commit('deletedPerscription', id);
            alert("Successfuly deleted perscription");
        }catch(error){
            alert(error.response);
        }
    },

    async handleAcceptingPerscription({commit}, id){
        try{
            await Vue.$axios.post("http://localhost:8081/perscriptions/handleAcceptingPerscription/" + id);
            commit('deletedPerscription', id);
            // dispatch('snackBar/showSuccess', "Successfuly regitered user", {root : true});
            alert("Successfuly registered user");
        }
        catch(error){
            // dispatch('snackbar/showError', error.response, {root : true});
            alert("Perscription doesn't exist");
        }
    },

    resetperscriptions({commit}) {
        commit("resetState");
    }
}

const mutations = {
    setPerscriptions: (state, perscriptions) => {
        state.perscriptions = perscriptions;
    },

    deletedPerscription(state,id) {
        const index = state.perscriptions.findIndex(type => type.id === id);
        state.perscriptions.splice(index,1);
    },

    resetState (state) {
        Object.assign(state, getDefaultState())
    }
}
const namespaced = true;

export default {
    namespaced,
    state,
    getters,
    actions,
    mutations
}