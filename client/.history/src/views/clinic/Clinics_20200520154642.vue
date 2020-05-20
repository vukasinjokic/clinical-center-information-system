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
                            hint="YYYY-MM-DD format"
                            readonly
                            @click="chosenDate = ''"/>
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
                    label="Chose examination type"
                    @click="chosenExamination = ''">
                    </v-select>

                    <v-spacer></v-spacer>

                    <v-btn @click="setApplyFilters(true)">Apply filters</v-btn>
                </v-card-title>
            </v-card>

            <AddClinic></AddClinic>

            <v-data-table
                :headers="headers"
                :items="filterClinics"
                class="blue-grey darken-4 white--text"
                @click:row="redirect"
                show-expand
                dark>

                <template v-slot:expanded-item="{ headers, item }">
                    <td :colspan="headers.length">
                        <th>Ime doktora</th>
                        <th>Prezime doktora</th>
                        <th>Tip pregleda</th>
                        <tr v-for="doctor in item.filteredDoctors" v-bind:key=doctor.name>
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
import AddClinic from '../../components/AddClinic.vue'


export default {
    name: "Clinics",
    components: {
        AddClinic
    },

    data() {
        return {
            applyFilters: false,
            doctorsFiltered: false,
            clinics: this.allClinics(),
            chosenExamination: "",
            filterWord: '',
            chosenDate: "",
            headers: [
                {text: "Name", value: "name"},
                {text: "Address", value: "address"},
                {text: "Price List", value: "priceList"},
                {text: "Rating", value: "rating"}
                {text: "Description", value: "description", width: "25%"}
            ]
        }
    },

    methods: {
        ...mapActions("doctors", ["doctorsSetter"]),

        ...mapActions("clinics", ["fetchClinics", "fetchFilteredClinics"]),

        ...mapActions("examination_type", ["fetchExaminationTypes"]),

        ...mapGetters("clinics", ['allClinics', "getFilteredClinics"]),

        ...mapGetters("examination_type", ['getTypes']),


        redirect(clinic) {
            sessionStorage.setItem("filterDetails",
            JSON.stringify({
                filterDate: this.chosenDate,
                filterType: this.chosenExamination,
                alreadyFiltered: true
            }));
            sessionStorage.setItem("doctors", JSON.stringify(clinic.filteredDoctors));
            this.$router.push({ name: 'Doctors'});
        },

        setApplyFilters(newApply) {
            this.applyFilters = newApply;
        },

        setDoctorsFiltered(newFiltered) {
            this.doctorsFiltered = newFiltered;
        }
    },
    
    computed: {
        // Kao sto se vrsi filtriranje klinika tako i filtriram doktore u okviru te klinike
        filterClinics: function(){
            // Is button clicked for applying filters?
            if (this.applyFilters) {
                // Reset state of button and perform filter
                this.setApplyFilters(false);
                return this.clinics.filter(clinic => {
                    var foundByName = false;

                    // Filter clinics by clinic name
                    if (clinic.name.toLowerCase().match(this.filterWord.toLowerCase())) {
                        foundByName = true;
                    }

                    // This clinic name, does not match name from input, no need for further check
                    if (!foundByName) {
                        return false;
                    }

                    // User does not want to filter by examination name and examination date
                    if (this.chosenExamination === "" && this.chosenDate === "") {
                        return true;
                    } else {
                        // Filter doctors in current clinic
                        clinic.filteredDoctors = clinic.doctors.filter(doctor => {
                            this.setDoctorsFiltered(true);
                            // TODO: filterDate and filterType are required
                            // Does chosen examination name matches doctors examination type?
                            if (doctor.examinationType.name.match(this.chosenExamination)) {
                                // User does not want to filter by examination date, only by examination type
                                if (this.chosenDate === "") {
                                    return true;
                                } else {
                                    var examinationType = {};
                                    var hours = 0;

                                    if (this.chosenExamination === "") {
                                        examinationType = doctor.examinationType;
                                        hours = examinationType.duration/3600000;
                                    } else {
                                        examinationType = this.getTypes().filter(examination => {
                                            return examination.name.match(this.chosenExamination);
                                        })[0];

                                        hours = examinationType.duration;
                                    }
                                    let durationMilliseconds = hours * 1000 * 60 * 60;

                                    let eventStartDates = doctor.calendar.eventStartDates.slice();
                                    let eventEndDates = doctor.calendar.eventEndDates;

                                    let startSelectedDay = (new Date(this.chosenDate));
                                    startSelectedDay.setHours(7,0,0,0);
                                    let endSelectedDay = new Date(this.chosenDate);
                                    endSelectedDay.setHours(14,0,0,0);

                                    eventStartDates.unshift(startSelectedDay);

                                    for (var i = 1; i <= eventStartDates.length; i++) {
                                        let startAppDate = new Date(eventStartDates[i]);
                                        let endAppDate = new Date(eventEndDates[i-1]);

                                        // Can the appointment be set BEFORE the first already set appointment
                                        if(i == 1 && new Date(eventStartDates[i-1]).getTime() + durationMilliseconds <= startAppDate.getTime()){
                                            return true;
                                        }

                                        // Can the appointment be set INBETWEEN already set appointments
                                        if(i < eventStartDates.length && endAppDate.getTime() + durationMilliseconds  <= new Date(eventStartDates[i + 1]).getTime()){
                                            return true;
                                        }

                                        // Can the appontment be set AFTER the last already set appointment
                                        if(i == eventStartDates.length - 1 && new Date(endAppDate).getTime() + durationMilliseconds <= endSelectedDay.getTime()){
                                            return true
                                        }
                                    }
                                    // Doctor does not have time for chosen examination date
                                    return false;
                                }
                            } else {
                                // Chosen examination type does not match current doctors examination type
                                return false;
                            }
                        })

                        // There are doctors in current clinic available to perform examination
                        if (clinic.filteredDoctors.length !== 0) {
                            return true;
                        } else {
                            // There are no doctors in current clinic available to perform examination
                            return false;
                        }
                    }
                })
            } else {
                // If doctors are filtered, reset filteredDoctors
                if (this.doctorsFiltered) {
                    this.setDoctorsFiltered(false);
                    for (let i = 0; i < this.clinics.length; i++) {
                        const clinic = this.clinics[i];
                        clinic.filteredDoctors = clinic.doctors;
                    }
                }
                // Button for applying filters is not clicked, return all clinics
                return this.allClinics();
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