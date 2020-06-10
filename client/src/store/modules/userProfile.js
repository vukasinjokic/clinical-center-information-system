import Vue from 'vue'

const getDefaultState = () => {
    return {
        userProf: {},
        message: ""
    }
};

const state = getDefaultState();

const getters = {
    getUserProf: (state) => state.userProf,
};  

const actions = {
    async fetchUserProf({commit}){
        try {
            const response = await Vue.$axios.get('http://localhost:8081/auth/userDetails');
            commit('setUserProf', response.data);
        } catch(error) {
            this.$store.dispatch('snackbar/showError', error.response.data, {root: true});
        }
    },
    async updateProfile({commit}, editItem){
        try {
            const response = await Vue.$axios.post('http://localhost:8081/auth/updateProfile',editItem);
            commit('setUserProf', response.data);
        } catch(error) {
            this.$store.dispatch('snackbar/showError', error.response.data, {root: true});
        }
    },
    async changePassword({dispatch}, passForm){
        try{
            await Vue.$axios.post('http://localhost:8081/auth/changePassword', passForm);
            dispatch('snackbar/showSuccess', "Successfully changed.", {root:true});
            localStorage.setItem('is_password_changed', true);
        }catch(error){
            dispatch('snackbar/showError', "Netacan stari password", {root:true});
            return Promise.reject(new Error('fail'));
        }
    },

    resetUserProfile({commit}) {
        commit("resetState");
    }
};

const mutations = {
    setUserProf: (state, user) => state.userProf = user, 
    alertPasswordChange(state, message){
        state.message = message;
        alert(message);
    },

    resetState(state) {
        Object.assign(state, getDefaultState());
    }
};

const namespaced = true;

export default{
    namespaced,
    state,
    getters,
    actions,
    mutations
}