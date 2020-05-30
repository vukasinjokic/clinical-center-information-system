import Vue from 'vue'

const getDefaultState = () => {
    return {
        userProf: null,
        message: ""
    }
};

const state = getDefaultState();

const getters = {
    getUserProf: (state) => state.userProf,
};

const actions = {
    async fetchUserProf({commit}){
        const response = await Vue.$axios.get('http://localhost:8081/auth/userDetails');
        commit('setUserProf', response.data);
    },
    async updateProfile({commit}, editItem){
        const response = await Vue.$axios.post('http://localhost:8081/auth/updateProfile',editItem);
        commit('setUserProf', response.data);
    },
    async changePassword({commit}, passForm){
        try{
            await Vue.$axios.post('http://localhost:8081/auth/changePassword', passForm);
            commit('alertPasswordChange', "Successfully.");
            //promeni localstorage.
            localStorage.setItem('is_password_changed', true);
        }catch(error){
            console.log(error);
            commit('alertPasswordChange', "Netacan stari password");
        }
    },
    // changeRoute({commit}){
    //     //this.$router.push('doctor');
    //     commit('alertPasswordChange',"DObrodosli")
    // },

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