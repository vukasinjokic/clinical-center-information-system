<template>
    <div>
        <v-container>
            <v-card>
                <v-card-title>
                    <v-text-field
                    v-model="filterWord"
                    label="Filter clinics by name"
                    show-details>
                    </v-text-field>

                    <v-spacer></v-spacer>

                    <v-btn @click="setApplyFilters(true)">Apply filters</v-btn>
                </v-card-title>
            </v-card>

            <AddClinic></AddClinic>

            <v-data-table
                :headers="headers"
                :items="filterClinics"
                class="blue-grey darken-4 white--text"
                show-expand
                dark>

                <template v-slot:expanded-item="{ headers, item }">
                    <td :colspan="headers.length">
                        <th>Ime doktora</th>
                        <th>Prezime doktora</th>
                        <th>Tip pregleda</th>
                        <tr v-for="doctor in item.doctors" v-bind:key=doctor.name>
                            <td>{{doctor.firstName}}</td>
                            <td>{{doctor.lastName}}</td>
                            <td>{{doctor.examinationType.name}}</td>
                        </tr>
                    </td>
                </template>

            </v-data-table>
            
        </v-container>
    </div>
</template>

<script>
// This page is for clinic center admins
import { mapGetters, mapActions } from 'vuex';
import AddClinic from '../../components/AddClinic.vue'


export default {
    name: "Clinics",
    components: {
        AddClinic
    },

    data() {
        return {
            applyFilters: false,
            filterWord: '',
            headers: [
                {text: "Name", value: "name"},
                {text: "Address", value: "address"},
                {text: "Price List", value: "priceList"},
                {text: "Rating", value: "rating"},
                {text: "Description", value: "description", width: "25%"}
            ]
        }
    },

    methods: {
        ...mapActions("clinics", ["fetchClinics"]),

        ...mapGetters("clinics", ['allClinics']),

        setApplyFilters(newApply) {
            this.applyFilters = newApply;
        },
    },
    
    computed: {
        filterClinics: function(){
            // Is button clicked for applying filters?
            if (this.applyFilters) {
                // Reset state of button and perform filter
                this.setApplyFilters(false);
                return this.allClinics().filter(clinic => {
                    return clinic.name.toLowerCase().match(this.filterWord.toLowerCase())
                })
            } else {
                // Button for applying filters is not clicked, return all clinics
                return this.allClinics();
            }
        }
    },

    created() {
        this.fetchClinics();
    }
}
</script>

<style scoped>

</style>