<template>
    <div>
        <input type="text" v-model="search" placeholder="Search clinics">
        <div class="clinics">
            <div
            class="clinic"
            @click="onClick(clinic)"
            v-for="clinic in filteredClinics" 
            v-bind:key="clinic.id">
                {{clinic.name}}
                {{clinic.address}}
                {{clinic.rating}}
            </div>
        </div>
    </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';


export default {
    name: "Clinics",

    data() {
        return {
            search: ''
        }
    },

    methods: {
        ...mapActions("clinics", ["fetchClinics"]),

        onClick(clinic) {
            this.$router.push({ name: 'Clinic', params: { id: clinic.id } });
            
        }
    },
    
    computed: {
        ...mapGetters("clinics", ['allClinics']),

        filteredClinics: function() {
            return this.allClinics.filter(clinic => {
                return clinic.name.match(this.search);
            })
        }
    }, 
    created() {
        this.fetchClinics();
    }
}
</script>

<style scoped>
     .clinics {
         display: grid;
         grid-template-columns: repeat(3, 1fr);
         grid-gap: 1rem;
     }

     .clinic {
         border: 1px solid #ccc;
         background: #41b883;
         padding: 1rem;
         border-radius: 5px;
         text-align: center;
         position: relative;
         cursor: pointer;
     }

    input[type="text"] {
        padding: 5px;
    }
</style>