<template>
    <div>
        <v-container>
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
                            hint="MM/DD/YYYY format"
                            readonly/>
                        </template>
                        <v-date-picker  v-model="chosenDate"
                        @input="fromDateMenu = false">           
                        </v-date-picker>          
                    </v-menu>

                    <v-spacer></v-spacer>

                    <v-select
                    v-model="chosenExamination"
                    :items="this.getTypes()"
                    item-text="name"
                    label="Chose examination type">
                    </v-select>
                
                    <v-spacer></v-spacer>

                    <v-btn @click="setApplyFilters(true)">Apply filters</v-btn>
                </v-card-title>          
            </v-card>
            
            <v-data-table
                :headers="headers"
                :items="filterClinics"
                class="blue-grey darken-4 white--text"
                @click:row="onClick"
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
import { mapGetters, mapActions } from 'vuex';


export default {
    name: "Clinics",

    data() {
        return {
            applyFilters: false,
            clinics: this.allClinics(),
            examinations: [],
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
        ...mapActions("clinics", ["fetchClinics", "fetchFilteredClinics"]),
        
        ...mapActions("examination_type", ["fetchExaminationTypes"]),

        ...mapGetters("clinics", ['allClinics', "getFilteredClinics"]),

        ...mapGetters("examination_type", ['getTypes']),

        onClick(value) {
            console.log(JSON.stringify(value))
            alert(JSON.stringify(value));
            // this.$router.push({ name: 'Clinic', params: { id: clinic.id } });
            
        },

        setApplyFilters(newApply) {
            this.applyFilters = newApply;
        },
    },
    
    computed: {
        filterClinics: function(){
            if (this.applyFilters) {
                this.setApplyFilters(false);
                return this.clinics.filter(clinic => {
                    var foundByName = false;
                    var foundByDate = true;
                    var foundByType = false;

                    // Filter clinics by clinic name
                    if (clinic.name.toLowerCase().match(this.filterWord.toLowerCase())) {
                        foundByName = true;
                    }

                    // This clinic name, does not match name from input, no need for further check
                    if (!foundByName) {
                        return false;
                    }

                    // Filter clinics by examination type
                    if (this.chosenExamination === "") {
                        foundByType = true;
                    } else {
                        for (let i = 0; i < clinic.doctors.length; i++) {
                            const doctor = clinic.doctors[i];
                            if (doctor.examinationType.name === this.chosenExamination) {
                                foundByType = true;
                                break;
                            }
                        }
                    }

                    // In this clinic there are no doctor that can perform chosen examination type, no need for further check
                    if (!foundByType) {
                        return false;
                    }


                    // Filter clinics by examination date
                    if (this.chosenDate === "") {
                        foundByDate = true;
                    } else {
                        var examinationType = this.getTypes().filter(examination => {
                            return examination.name.match(this.chosenExamination);
                        })[0];

                        for (let k = 0; k < clinic.doctors.length; k++) {
                            var doctor = clinic.doctors[k];
                            
                            let eventStartDates = doctor.calendar.eventStartDates.slice();
                            let eventEndDates = doctor.calendar.eventEndDates;   

                            let startSelectedDay = (new Date(this.chosenDate));
                            startSelectedDay.setHours(7,0,0,0);
                            let endSelectedDay = new Date(this.chosenDate);
                            endSelectedDay.setHours(14,0,0,0);

                            eventStartDates.unshift(startSelectedDay);

                            let hours = examinationType.duration
                            let durationMilliseconds = hours * 1000 * 60 * 60;
                            for (var i = 1; i <= eventStartDates.length; i++) {
                                let startAppDate = new Date(eventStartDates[i]);
                                let endAppDate = new Date(eventEndDates[i-1]);
                                
                                // Can the appointment be set before the first already set appointment 
                                if(i == 1 && new Date(eventStartDates[i-1]).getTime() + durationMilliseconds <= startAppDate.getTime()){
                                    foundByDate = true
                                    break;
                                }
                                
                                // Can the appointment be set INBETWEEN already set appointments
                                if(i < eventStartDates.length && endAppDate.getTime() + durationMilliseconds  <= new Date(eventStartDates[i + 1]).getTime()){
                                    foundByDate = true
                                    break;
                                }

                                // Can the appontment be set AFTER the last already set appointment
                                if(i == eventStartDates.length - 1 && new Date(endAppDate).getTime() + durationMilliseconds <= endSelectedDay.getTime()){
                                    foundByDate = true;
                                    break;
                                }
                            }

                            // There is doctor that can perform examination, no need to to search more doctors in same clinic
                            if (foundByDate) {
                                break;
                            }
                        }
                    }

                    return foundByDate && foundByName && foundByType;
                })
            } else {
                return this.clinics;
            }
        }
    },

    created() {
        this.fetchClinics();
        this.fetchExaminationTypes();
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