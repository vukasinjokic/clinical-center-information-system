import Vue from 'vue'

const getDefaultState = () => {
    return {
        examination_types: []
    }
};

const state = getDefaultState();

const getters = {
    getTypes: (state) => state.examination_types,
};

const actions = {
    async fetchExaminationTypes({commit}){
        const response = await Vue.$axios.get('http://localhost:8081/ex_type/getTypes');
        commit('setTypes', response.data);
    },
    async deleteType({commit, dispatch},type_name){
        try{
            await Vue.$axios.delete("http://localhost:8081/ex_type/delete/"+type_name);
            commit('deletedType', type_name);
            dispatch('snackbar/showSuccess', "Uspesno obrisan", {root: true})
            
        }catch(error){
            dispatch('snackbar/showError', 'Status' + error.response.status, {root: true}); //ne moze da se obrise referenciran 
        }
    },
    async addType({commit, dispatch}, type){
        try{
            const response = await Vue.$axios.post("http://localhost:8081/ex_type/addType", type);
            commit('addedType',response.data);
            dispatch('snackbar/showSuccess', "Uspesno dodat", {root: true});
        }catch(error){
            dispatch('showWarning', "Zauzeto ime", {root: true}); //videcemo kako da hendlamo error
        }
    },
    async updateType({commit, dispatch},type){
        try{
            const response = await Vue.$axios.post("http://localhost:8081/ex_type/updateType", type);
            commit('updatedType',response.data);
            dispatch('snackbar/showSuccess', "Uspesno izmenjen", {root: true});
        }catch(error){
            dispatch('snackbar/showError',error.response.status, {root:true}); //videcemo kako da hendlamo error
        }
        
    },

    resetExaminationType({commit}) {
        commit("resetState");
    }
};

const mutations ={
    setTypes: (state,types) => state.examination_types = types,
    
    deletedType(state, type_name) {
        const index = state.examination_types.findIndex(type => type.name === type_name);
        state.examination_types.splice(index,1);
    },
    addedType: (state, type) => state.examination_types.push(type),

    updatedType(state,type){
        const index = state.examination_types.findIndex(t => t.id === type.id);
        Object.assign(state.examination_types[index], type);
    },

    resetState(state) {
        Object.assign(state, getDefaultState());
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