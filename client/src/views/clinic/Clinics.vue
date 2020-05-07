<template>
    <div>
        <v-container>
            <v-row>
                <v-col
                    cols="12"
                    sm="4"
                    >
                <v-text-field outlined class="desno" type="text" v-model="search" placeholder="Search clinics"/>
                </v-col>
            </v-row>
            
            <v-data-table
                :headers="headers"
                :items="filteredClinics"
                dark/>
            
        </v-container>
    </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';


export default {
    name: "Clinics",

    data() {
        return {
            search: '',
            headers: [
                {text: "Name", value: "name"},
                {text: "Address", value: "address"},
                {text: "Rating", value: "rating"},
            ]
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

    .desno{
    position: relative;
    left: 326px;
}

    input[type="text"] {
        padding: 5px;
    }
</style>