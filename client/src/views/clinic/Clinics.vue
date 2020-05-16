<template>
    <div>
        <v-container>
            <v-card>
                <v-card-title>
                    Clinics
                    <v-spacer></v-spacer>
                    <v-text-field
                        v-model="search"
                        label="Search"
                        single-line
                        show-details
                        append-icon="mdi-magnify"
                        
                    ></v-text-field>
                </v-card-title>
            </v-card>    
            <AddClinic></AddClinic>

            <v-data-table
                class="blue-grey darken-4 white--text"
                :headers="headers"
                :items="filteredClinics"
                dark/>
            
        </v-container>
    </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import AddClinic from '../../components/AddClinic.vue'


export default {
    name: "Clinics",
    components: {
        AddClinic
    },

    data() {
        return {
            search: '',
            headers: [
                {text: "Name", value: "name"},
                {text: "Address", value: "address"},
                {text: "Rating", value: "rating"},
                {text: "Description", value: "description", width: "25%"}
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
                return clinic.name.toUpperCase().match(this.search.toUpperCase());
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