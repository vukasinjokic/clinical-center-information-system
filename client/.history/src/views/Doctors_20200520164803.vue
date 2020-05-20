<template>
    <div>
        <v-container>
            <v-card>
                <v-card-title>
                    <v-text-field
                    v-model="filterName"
                    label="Filter doctors by name"
                    show-details>
                    </v-text-field>

                    <v-spacer></v-spacer>

                    <v-text-field
                    v-model="filterSurname"
                    label="Filter doctors by surname"
                    show-details>
                    </v-text-field>

                    <v-spacer></v-spacer>

                    <v-select
                    v-model.number="filterRating"
                    :items="numbers"
                    label="Filter doctors by rating"
                    @click="filterRating = -1">
                    </v-select>

                    <v-spacer></v-spacer>

                    <v-menu
                    v-if="!alreadyDateTypeFiltered"
                    v-model="fromDateMenu"
                    :close-on-content-click="true"
                    :nudge-right="40"
                    transition="scale-transition"
                    offset-y
                    max-width="290px">
                        <template v-slot:activator="{ on }">
                            <v-text-field  v-model="filterDate"
                            v-on="on"
                            label="Pick date"
                            :value="filterDate"
                            hint="YYYY-MM-DD format"
                            readonly
                            @click="filterDate = ''"/>
                        </template>
                        <v-date-picker  v-model="filterDate"
                        @input="fromDateMenu = false">           
                        </v-date-picker>          
                    </v-menu>

                    <v-spacer
                    v-if="!alreadyDateTypeFiltered">
                    </v-spacer>

                    <v-select
                    v-if="!alreadyDateTypeFiltered"
                    v-model="filterType"
                    :items="this.getTypes()"
                    item-text="name"
                    label="Chose examination type"
                    @click="filterType = ''">
                    </v-select>

                    <v-spacer
                    v-if="!alreadyDateTypeFiltered">
                    </v-spacer>

                    <v-btn @click="setApplyFilters(true)">Apply filters</v-btn>
                </v-card-title>
            </v-card>

            <v-data-table
            :headers="headers"
            :items="getDoctorsTable"
            class="blue-grey darken-4 white--text"
            show-expand
            dark>

                <template v-slot:expanded-item="{ headers, item }">
                    <td :colspan="headers.length">
                        <th>Free appointment</th>
                        <tr v-for="freeAppointment in item.freeAppointments" v-bind:key="freeAppointment.id">
                            <td>{{freeAppointment.time}}</td>
                            <td>
                                <v-btn color="blue"
                                @click="onClick({
                                    doctorId: item.id,
                                    appointmentTime: freeAppointment.time
                                })">
                                    Schedule appointment
                                </v-btn>
                            </td>
                        </tr>
                    </td>
                </template>
            </v-data-table>

        </v-container>
    </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import Vue from 'vue';

export default {
    name: "Doctors",

    data() {
        return {
            filterName: "",
            filterSurname: "",
            filterRating: -1,
            filterDate: "",
            filterType: "",
            alreadyDateTypeFiltered: false,
            applyFilters: false,
            doctors: [],
            numbers: Array.from(new Array(101), (item, index) => (100 - index)/10),
            headers: [
                {text: "Name", value: "firstName"},
                {text: "Surname", value: "lastName"},
                {text: "Examination type", value: "examinationType.name"},
                {text: "Rating", value: "rating"},
                {text: "Clinic", value: "clinic"}
            ]
        }
    },

    methods: {
        ...mapGetters("doctors", ["getDoctors"]),
        ...mapActions("doctors", ["fetchDoctors", "doctorsSetter"]),

        ...mapActions("examination_type", ["fetchExaminationTypes"]),
        ...mapGetters("examination_type", ['getTypes']),

        async onClick(appointmentRequest) {
            // TODO: posalti zahtev na bek
            await Vue.$axios.post("http://localhost:8081/appointmentRequests/addAppointmentRequest", appointmentRequest)
            .then(response => {
                console.log(JSON.stringify(response));
                console.log(JSON.stringify(response.data));
            });
        },

        setApplyFilters(newApply) {
            this.applyFilters = newApply;
        },

        // Izdvaja i sate
        dateToString(item){
            var d = new Date(item);
            return d.toString().substring(0,21);
        },

        setAppointments(doctor) { 
            doctor["freeAppointments"] = [];
            var durationMiliseconds = doctor.examinationType.duration;
            var selectedDate = new Date(this.filterDate);
            selectedDate.setHours(7,0,0,0);

            var endDay = new Date(this.filterDate);
            endDay.setHours(14,0,0,0);

            var takenTimes = [];
            for (let i = 0; i < doctor.calendar.eventStartDates.length; i++) {
                var startDateElement = new Date(doctor.calendar.eventStartDates[i]);
                var endDateElement = new Date(doctor.calendar.eventEndDates[i]);
                if (selectedDate.toDateString() == startDateElement.toDateString()) {
                    takenTimes.push({
                        eventStartDate: startDateElement,
                        eventEndDate: endDateElement
                    })
                }
            }

            var fakeId = 0;
            if (takenTimes.length === 0) {
                while (selectedDate.getTime() + durationMiliseconds <= endDay.getTime()) {
                    fakeId++;
                    doctor.freeAppointments.push({
                        id: doctor.id.toString() + fakeId.toString(),
                        time: this.dateToString(selectedDate)
                    });
                    selectedDate.setTime(selectedDate.getTime() + durationMiliseconds)
                }
            } else {
                fakeId = 0;
                for (var i = 0; i < takenTimes.length; i++) {
                    const taken = takenTimes[i];
                    var startDate = taken.eventStartDate;
                    var endDate = taken.eventEndDate;

                    while (selectedDate.getTime() + durationMiliseconds <= startDate.getTime() &&
                        selectedDate.getTime() + durationMiliseconds <= endDay.getTime()) {
                        doctor.freeAppointments.push({
                            id: doctor.id.toString() + fakeId.toString(),
                            time: this.dateToString(selectedDate)
                        });
                        selectedDate.setTime(selectedDate.getTime() + durationMiliseconds);
                    }

                    selectedDate.setTime(endDate.getTime());
                }

                while (selectedDate.getTime() + durationMiliseconds <= endDay.getTime()) {
                    fakeId++;
                    doctor.freeAppointments.push({
                        id: doctor.id.toString() + fakeId.toString(),
                        time: this.dateToString(selectedDate)
                    });
                    selectedDate.setTime(selectedDate.getTime() + durationMiliseconds)
                }
            }
        }
    },

    created() {
        this.doctors = JSON.parse(sessionStorage.getItem("doctors"));

        var filterDetails = JSON.parse(sessionStorage.getItem("filterDetails"));
        this.filterDate = filterDetails.filterDate;
        this.filterType = filterDetails.filterType;
        this.alreadyDateTypeFiltered = filterDetails.alreadyFiltered;

        if (this.alreadyDateTypeFiltered) {
            this.doctors.forEach(doctor => {
                this.setAppointments(doctor);
            });
        }

        this.doctorsSetter(this.doctors);
        this.fetchExaminationTypes();
    },

    computed: {
        getDoctorsTable: function() {
            if (this.applyFilters) {
                this.setApplyFilters(false);
                return this.doctors.filter(doctor => {
                    var foundByName = false;
                    var foundBySurname = false;
                    var foundByRating = false;

                    foundByName = doctor.firstName.toLowerCase().match(this.filterName.toLowerCase());
                    
                    if (!foundByName) {
                        return false;
                    }

                    foundBySurname = doctor.lastName.toLowerCase().match(this.filterSurname.toLowerCase());
                    
                    if (!foundBySurname) {
                        return false;
                    }

                    if (this.filterRating === -1) {
                        foundByRating = true;
                    } else {
                        foundByRating = Number(doctor.rating) === this.filterRating;
                    }

                    if (!foundByRating) {
                        return false;
                    }

                    if (this.alreadyDateTypeFiltered) {
                        return true;
                    } else {
                        // TODO: filterDate and filterType are required
                        if (doctor.examinationType.name.match(this.filterType)) {
                            if (this.filterDate === "") {
                                return true;
                            } else {
                                var examinationType = {};
                                var hours = 0;

                                if (this.filterType === "") {
                                    examinationType = doctor.examinationType;
                                    hours = examinationType.duration/3600000;
                                } else {
                                    examinationType = this.getTypes().filter(examination => {
                                        return examination.name.match(this.filterType);
                                    })[0];

                                    hours = examinationType.duration;
                                }
                                let durationMilliseconds = hours * 1000 * 60 * 60;

                                let eventStartDates = doctor.calendar.eventStartDates.slice();
                                let eventEndDates = doctor.calendar.eventEndDates;
                                
                                let startSelectedDay = (new Date(this.filterDate));
                                startSelectedDay.setHours(7,0,0,0);
                                let endSelectedDay = new Date(this.filterDate);
                                endSelectedDay.setHours(14,0,0,0);
                                
                                eventStartDates.unshift(startSelectedDay);

                                for (var i = 1; i <= eventStartDates.length; i++) {
                                    let startAppDate = new Date(eventStartDates[i]);
                                    let endAppDate = new Date(eventEndDates[i-1]);
                                    
                                    // Can the appointment be set BEFORE the first already set appointment 
                                    if(i == 1 && new Date(eventStartDates[i-1]).getTime() + durationMilliseconds <= startAppDate.getTime()){
                                        this.setAppointments(doctor);
                                        return true;
                                    }
                                    
                                    // Can the appointment be set INBETWEEN already set appointments
                                    if(i < eventStartDates.length && endAppDate.getTime() + durationMilliseconds  <= new Date(eventStartDates[i + 1]).getTime()){
                                        this.setAppointments(doctor);
                                        return true;
                                    }

                                    // Can the appontment be set AFTER the last already set appointment
                                    if(i == eventStartDates.length - 1 && new Date(endAppDate).getTime() + durationMilliseconds <= endSelectedDay.getTime()){
                                        this.setAppointments(doctor);
                                        return true;
                                    }
                                }
                                // Doctor does not have time for chosen examination date
                                return false;
                            }
                        } else {
                            return false;
                        }
                    }
                });
            } else {
                return this.doctors;
            }
        },
    }
}
</script>

<style scoped>

</style>