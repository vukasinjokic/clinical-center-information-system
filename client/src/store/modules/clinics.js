import axios from 'axios';

const state = {
    clinics: [],
};

const getters = {
    allClinics: (state) => state.clinics
};

const config = {
    headers: {
        Authorization: "Bearer " + localStorage.getItem("JWT"),
    }
}

const actions = {
    async fetchClinics({commit}){          
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
    },

    async saveClinic({commit}, clinic){
        const response = await axios.post('http://localhost:8081/clinics/addClinic', clinic, config);
        commit('newClinic', response.data);
    }

};

const mutations = {
    setClinics: (state, clinics) => (state.clinics = clinics),
    newClinic: (state, newClinic) => state.clinics.unshift(newClinic)
};

const namespaced = true;

export default {
    namespaced,
    state,
    getters,
    actions,
    mutations
}