import Vue from 'vue';

const getDefaultState = () => {
    return {
        rating: 0,
        series: [{
            name: "Desktops", 
            data: [{x:"Jan", y:22}, {x:"Jana", y:41}, {x:"Jadn", y:35}, {x:"Jan", y:51},
             {x:"Jafn", y:45}, {x:"Jgan", y:62}, {x:"Jaaan", y:69}, {x:"Jan", y:91}, {x:"Jan", y:148}]
          }],
    }
};

const state = getDefaultState();

const getters = {
    getRating: (state) => state.rating,
    getSeries: (state) => state.series
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