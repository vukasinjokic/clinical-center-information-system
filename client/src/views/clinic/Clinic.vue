<template>
    <div>
        <h1>Ovo je klinika</h1>
        <div class="clinic">
            {{clinic.name}}
            {{clinic.address}}
            {{clinic.rating}}
        </div>
        
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
        axios.get(`http://localhost:8081/clinics/${this.$route.params.id}`)
        .then( response => {
            this.clinic.id = response.data.id;
            this.clinic.name = response.data.name;
            this.clinic.address = response.data.address;
            this.clinic.description = response.data.description;
            this.clinic.priceList = response.data.priceList;
            this.clinic.rating = response.data.rating;
            this.clinic.doctors = response.data.doctors;
            this.clinic.nurses = response.data.nurses;
            this.clinic.appointments = response.data.appointments;
            this.clinic.operationRooms = response.data.operationRooms;
        })
    }
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