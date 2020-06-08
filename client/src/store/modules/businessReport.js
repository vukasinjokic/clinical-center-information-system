import Vue from 'vue';

const getDefaultState = () => {
    return {
        rating: 0,
        profit: 0
    }
};

const state = getDefaultState();

const getters = {
    getRating: (state) => state.rating,
    getProfit: (state) => state.profit
};

const actions = {
    async fetchClinicRating({commit}){
        const email = localStorage.getItem('user_email');
        const response = await Vue.$axios.get('http://localhost:8081/clinics/getRating/'+email);
        commit('setRating', response.data);
    },

    async getClinicProfit({commit}, dates){
        const email = localStorage.getItem('user_email');
        const response = await Vue.$axios
            .get('http://localhost:8081/clinicAdmins/getProfit?email='+ email +
                    '&dateFrom='+dates.dateFrom+'&dateTo='+dates.dateTo);
        commit('setProfit', response.data);
    },

    resetBusinessReport({commit}) {
        commit("resetState");
    }
};

const mutations = {
    setRating(state, rating){
        state.rating = rating;
    },
    setProfit(state, profit){
        state.profit = profit;
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