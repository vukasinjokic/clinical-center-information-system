<template>
    <div>
        <v-container>
            <v-form ref="form">
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
                                :rules="[requiredRule]"
                                @click="chosenDate = ''"/>
                            </template>
                            <v-date-picker 
                            v-model="chosenDate"
                            :min="nowDate"
                            @input="fromDateMenu = false">
                            </v-date-picker>
                        </v-menu>

                        <v-spacer></v-spacer>

                        <v-select
                        v-model="chosenExamination"
                        :rules="[requiredRule]"
                        :items="this.getTypes()"
                        item-text="name"
                        label="Chose examination type"
                        @click="chosenExamination = ''">
                        </v-select>

                        <v-spacer></v-spacer>

                        <v-btn @click="validate">Apply filters</v-btn>
                    </v-card-title>
                </v-card>
            </v-form>

            <v-data-table
                :headers="headers"
                :items="filterClinics"
                class="blue-grey darken-4 white--text"
                show-expand
                dark>

                <template v-slot:item.actions="{ item }">
                    <v-btn color="blue" @click="redirectToDoctors(item.filteredDoctors)">Doktori</v-btn>
                    &nbsp;
                    <v-btn color="blue" @click="redirectToClinic(item.id)">Profil</v-btn>
                </template>
                
                <template v-slot:expanded-item="{ headers, item }">
                    <td :colspan="headers.length">
                        <th>Ime doktora</th>
                        <th>Prezime doktora</th>
                        <th>Tip pregleda</th>
                        <th>Cena pregleda</th>
                        <tr v-for="doctor in item.filteredDoctors" v-bind:key=doctor.name>
                            <td>{{doctor.firstName}}</td>
                            <td>{{doctor.lastName}}</td>
                            <td>{{doctor.examinationType.name}}</td>
                            <td>{{doctor.price}}</td>
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
    name: "ClinicsForPatient",

    data() {
        return {
            applyFilters: false,
            doctorsFiltered: false,
            chosenExamination: "",
            filterWord: '',
            chosenDate: "",
            headers: [
                {text: "Name", value: "name"},
                {text: "Address", value: "address"},
                {text: "Rating", value: "rating"},
                {text: "Description", value: "description", width: "30%"},
                {text: "Actions", value: "actions"}
            ]
        }
    },

    methods: {
        ...mapActions("clinics", ["fetchClinics"]),

        ...mapActions("examination_type", ["fetchExaminationTypes"]),

        ...mapGetters("clinics", ['allClinics']),

        ...mapGetters("examination_type", ['getTypes']),


        redirectToDoctors(filteredDoctors) {
            if (this.$refs.form.validate()) {
                sessionStorage.setItem("filterDetails",
                JSON.stringify({
                    filterDate: this.chosenDate,
                    filterType: this.chosenExamination,
                    alreadyFiltered: true
                }));
                sessionStorage.setItem("doctors", JSON.stringify(filteredDoctors));
                this.$router.push({ name: 'DoctorsForPatient'});
            }
        },

        redirectToClinic(clinicId) {
            this.$router.push({
                name: "ClinicProfileForPatient",
                params: {
                    id: clinicId
                }
            })
        },


        validate() {
            if (this.$refs.form.validate()) {
                this.setApplyFilters(true);
            }
        },

        setApplyFilters(newApply) {
            this.applyFilters = newApply;
        },

        setDoctorsFiltered(newFiltered) {
            this.doctorsFiltered = newFiltered;
        }
    },
    
    computed: {
        nowDate(){
            return new Date().toISOString().slice(0,10);
        },

        requiredRule(){
            return (value) => !!value || "Required.";
        },

        filterClinics: function(){
            // Is button clicked for applying filters?
            if (this.applyFilters) {
                // Reset state of button and perform filter
                this.setApplyFilters(false);
                return this.allClinics().filter(clinic => {
                    // Does user input matches clinic name?
                    if (clinic.name.toLowerCase().match(this.filterWord.toLowerCase())) {
                        // Filter doctors in current clinic
                        clinic.filteredDoctors = clinic.doctors.filter(doctor => {
                            this.setDoctorsFiltered(true);
                            // Does chosen examination name matches doctors examination type?
                            if (doctor.examinationType.name.match(this.chosenExamination)) {
                                
                                let startSelectedDay = new Date(this.chosenDate);
                                startSelectedDay.setHours(7,0,0,0);
                                let startSelectedDayMiliseconds = startSelectedDay.getTime();

                                let endSelectedDay = new Date(this.chosenDate);
                                endSelectedDay.setHours(14,0,0,0);

                                let vacationDates = doctor.calendar.vacationDates;
                                var hasVacation = vacationDates.length == 2;
                                if (hasVacation) {
                                    let startVacation = new Date(vacationDates[0]);
                                    let endVacation = new Date(vacationDates[1]);
                                    // If doctor is on vacation, skip him 
                                    if (startVacation.getMilliseconds() <= startSelectedDayMiliseconds < endVacation.getMilliseconds()) {
                                        return false;
                                    }
                                }

                                var hours = doctor.examinationType.duration;
                                let durationMilliseconds = hours * 1000 * 60 * 60;

                                let eventStartDates = doctor.calendar.eventStartDates.slice();
                                let eventEndDates = doctor.calendar.eventEndDates;

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
                    // If current clinic name does not match, skip current clinic
                    } else {
                        return false;
                    }
                })
            } else {
                // If doctors are filtered, reset filteredDoctors
                if (this.doctorsFiltered) {
                    this.setDoctorsFiltered(false);
                    var clinics = this.allClinics();
                    for (let i = 0; i < this.allClinics().length; i++) {
                        const clinic = clinics[i];
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

</style>