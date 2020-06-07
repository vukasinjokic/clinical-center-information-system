import Vue from 'vue';

const getDefaultState = () => {
    return {
        rating: 0,
    }
};

const state = getDefaultState();

const getters = {
    getRating: (state) => state.rating,
};

const actions = {
    async fetchClinicRating({commit}){
        const email = localStorage.getItem('user_email');
        const response = await Vue.$axios.get('http://localhost:8081/clinics/getRating/'+email);
        commit('setRating', response.data);
    },

    resetBusinessReport({commit}) {
        commit("resetState");
    }
};

const mutations = {
    setRating(state, rating){
        state.rating = rating;
    },
    resetState (state) {
        Object.assign(state, getDefaultState())
    }
};

const namespaced = true;

export default {
    namespaced,
    state,
    getters,
    actions,
    mutations
}