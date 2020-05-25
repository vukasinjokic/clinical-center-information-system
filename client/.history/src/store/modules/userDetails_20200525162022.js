import Vue from 'vue';

const state = {
    user: {
        email: "",
        authorities: []   //role je ovde
    }
};

const getters = {
    getUser: (state) => state.user,
    getRole: (state) => state.user.authorities.length === 0 ? null : state.user.authorities[0]
};

const actions = {
    logIn({ commit }, data) {
        localStorage.setItem('JWT', data.accessToken);
        localStorage.setItem('Duration', data.expiresIn);
        localStorage.setItem('user_email', data.email);
        Vue.$axios.defaults.headers['Authorization'] = "Bearer " + localStorage.getItem("JWT");
        commit('setUser', {
            email: data.email,
            authorities: data.authorities
        });
    },

    isLogedIn() {
        return state.user.email === "" && state.user.authorities.length === 0
    },

    logOut({commit}) {
        localStorage.removeItem('JWT');
        localStorage.removeItem('Duration');
        commit('removeUser');
    },

    userSetter({commit}, user) {
        commit('setUser', user);
    },
};

const mutations = {
    setUser: (state, user) => (state.user = user),
    setUserEmail: (state,email) => (state.user.email = email),
    removeUser: (state) => (
        state.user.email = "",
        state.user.authorities.length = 0
        )
};

const namespaced = true;

export default {
    namespaced,
    state,
    getters,
    actions,
    mutations
}