const state = {
    user: {
        email: "",
        authorities: []
    }
};

const getters = {
    getUser: (state) => state.user
};

const actions = {
    async logIn({ commit }, data) {
        localStorage.setItem('JWT', data.accessToken);
        localStorage.setItem('Duration', data.expiresIn);
        commit('setUser', {
            email: data.email,
            authorities: data.authorities
        });
    },

    async isLogedIn() {
        return state.user.email === "" && state.user.authorities.length === 0
    },

    async logOut({commit}) {
        localStorage.removeItem('JWT');
        localStorage.removeItem('Duration');
        commit('removeUser');
    },

    async userSetter({commit}, user) {
        commit('setUser', user);
    }
};

const mutations = {
    setUser: (state, user) => (state.user = user),
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