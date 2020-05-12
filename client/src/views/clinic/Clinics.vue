<template>
    <div>
        <v-container>
            <!-- <v-row>
                <v-col
                    cols="12"
                    sm="4"
                    >
                <v-text-field outlined class="desno" type="text" v-model="search" placeholder="Search clinics"/>
                </v-col>
            </v-row> -->

            <v-card>
                <v-card-title>
                    <v-text-field
                    v-model="filterWord"
                    label="Filter clinics by name"
                    single-line
                    show-details>
                    </v-text-field>

                    <v-spacer></v-spacer>

                    <v-menu
                    v-model="fromDateMenu"
                    :close-on-content-click="true"
                    :nudge-right="40"
                    transition="scale-transition"
                    offset-y
                    max-width="290px">
                        <template v-slot:activator="{ on }">
                            <v-text-field  v-model="chosenDate"
                            v-on="on"
                            label="Pick date"
                            :value="chosenDate"
                            hint="MM/DD/YYYY format"/>
                        </template>
                        <v-date-picker  v-model="chosenDate"
                        @input="fromDateMenu = false">           
                        </v-date-picker>          
                    </v-menu>

                    <v-spacer></v-spacer>

                    <v-select
                    v-model="chosenExamination"
                    :items="examinationTypes"
                    label="Chose examination type">
                    </v-select>
                
                    <v-spacer></v-spacer>

                    <v-btn @click="applyFilters">Apply filters</v-btn>
                </v-card-title>          
            </v-card>
            
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
            examinationTypes: ['Foo', 'Bar', 'Fizz', 'Buzz'],
            chosenExamination: "",
            filterWord: '',
            chosenDate: "",
            headers: [
                {text: "Name", value: "name"},
                {text: "Address", value: "address"},
                {text: "Price List", value: "priceList"},
                {text: "Rating", value: "rating"}
            ]
        }
    },

    methods: {
        ...mapActions("clinics", ["fetchClinics"]),

        onClick(clinic) {
            this.$router.push({ name: 'Clinic', params: { id: clinic.id } });
            
        },

        applyFilters() {
            alert("filterWord {" + this.filterWord + "} chosenExamination {" + this.chosenExamination + "} chosenDate {" + this.chosenDate + "}")
        }
    },
    
    computed: {
        ...mapGetters("clinics", ['allClinics']),

        filteredClinics: function() {
            // return this.allClinics.filter(clinic => {
            //     return clinic.name.match(this.search);
            // })
            return this.allClinics
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