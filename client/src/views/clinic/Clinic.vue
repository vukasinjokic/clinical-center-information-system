<template>
    <div>
        <h1>Ovo je klinika</h1>
        <div class="clinic">
            {{clinic.name}}
            {{clinic.address}}
            {{clinic.rating}}
        </div>
        
        <h2>Ovo su doktori u klinici</h2>
        <div
        class="doctor"
        v-for="doctor in this.clinic.doctors"
        v-bind:key="doctor.id">
            {{doctor}}
        </div>
    </div>
</template>

<script>
            // class="clinic"
            // @click="onClick(clinic)"
            // v-for="clinic in filteredClinics" 
            // v-bind:key="clinic.id">
            //     {{clinic.name}}
            //     {{clinic.address}}
            //     {{clinic.rating}}
            // </div>
import axios from 'axios';
import { mapGetters, mapActions } from 'vuex';

export default {
    name: "Clinic",

    data() {
        return {
            clinic: {
                id: 0,
                name: "",
                address: "",
                description: "",
                priceList: "",
                rating: "",
                doctors: [],
                nurses: [],
                appointments: [],
                operationRooms: []
            }
        }
    },

    created() {
        let config = {
            headers: {
                Authorization: "Bearer " + localStorage.getItem("JWT"),
            }
        }
        axios.get(`http://localhost:8081/clinics/${this.$route.params.id}`, config)
        .then( response => {
            this.clinicsSetter([response.data])

            this.clinic = this.allClinics()[0];
        });
    },

    methods: {
        ...mapActions("clinics", ["clinicsSetter"]),

        ...mapGetters("clinics", ["allClinics"])
    },
}
</script>

<style scoped>
     .clinic {
         border: 1px solid #ccc;
         background: #41b883;
         padding: 1rem;
         border-radius: 5px;
         text-align: center;
         position: relative;
         cursor: pointer;
     }

    .doctor {
         border: 1px solid #ccc;
         background: #ff0000;
         padding: 1rem;
         border-radius: 5px;
         text-align: center;
         position: relative;
         cursor: pointer;
     }
</style>