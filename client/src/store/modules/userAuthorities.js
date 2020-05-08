const state = {
    authorities: []
};

const getters = {
    getAuthorities: (state) => state.authorities
};

const actions = {
    async logIn({ commit }, data) {
        localStorage.setItem('JWT', data.accessToken);
        localStorage.setItem('Duration', data.expiresIn);
        commit('setAuthorities', data.authorities);
    },

    async isLogedIn() {
        return state.authorities.length === 0
    },

    async logOut({commit}) {
        localStorage.removeItem('JWT');
        localStorage.removeItem('Duration');
        commit('clearAuthorities');
    },

    async authoritiesSetter({commit}, authorities) {
        commit('setAuthorities', authorities);
    }
};

const mutations = {
    setAuthorities: (state, authorities) => (state.authorities = authorities),
    clearAuthorities: (state) => (state.authorities.length = 0)
};

const namespaced = true;

export default {
    namespaced,
    state,
    getters,
    actions,
    mutations
}