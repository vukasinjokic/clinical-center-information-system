import axios from 'axios';

const state = {
    clinics: [],
};

const getters = {
    allClinics: (state) => state.clinics
};

const actions = {
    async fetchClinics({commit}){
        let config = {
            headers: {
                Authorization: "Bearer " + localStorage.getItem("JWT"),
            }
          }
          
        //   let data = {
        //     'HTTP_CONTENT_LANGUAGE': self.language
        //   }
          
        //   axios.post(URL, data, config).then(...)
        await axios.get('http://localhost:8081/clinics', config)
        .then(response => {
            commit('setClinics', response.data);
        })
        .catch(() => { alert("Nemate pravo pregleda svih klinika") });
    },

    async clinicsSetter({commit}, clinics) {
        commit('setClinics', clinics);
    }
};

const mutations = {
    setClinics: (state, clinics) => (state.clinics = clinics)
};

const namespaced = true;

export default {
    namespaced,
    state,
    getters,
    actions,
    mutations
}