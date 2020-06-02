
const state = {
    text: "",
    snackbar: false,
    color: "",
};

const getters = {
    getSnackbar: (state) => state.snackbar,
    getText:(state) => state.text,
    getColor: (state) => state.color
};

const actions = {

    showSuccess({commit}, message){
        commit('setMessage', { message, color: "success" });
    },
    showError({commit}, message){
        commit('setMessage', { message, color: "error" });
    },
    showWarning({commit}, message){
        commit('setMessage', { message, color: "warning" });
    }
};

const mutations = {
    
    setMessage(state, { message, color }) {
        state.text = message;
        state.snackbar = true;
        state.color = color;
    },
    SET_SNACKBAR(state, val) {
        state.snackbar = val;
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