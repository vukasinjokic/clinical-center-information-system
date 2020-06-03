<template>
    <div>
        <v-container>
            <v-card>
                <v-card-title>Istorija pregleda i operacija</v-card-title>
                
                <v-data-table
                :headers="headers"
                :items="allAppointments"
                :items-per-page="5"
                class="blue-grey darken-4 white--text"
                dark grey>
                    <template v-slot:item.doctor="{ item }">
                        {{item.doctor.firstName + " " + item.doctor.lastName}}
                    </template>
                    <template v-slot:item.grade="{ item }">
                        <GradeDialog  color="blue" v-bind:doctor="item.doctor"/>
                    </template>
                </v-data-table>
            </v-card>
        </v-container>
    </div>
</template>

<script>
import {mapGetters, mapActions} from 'vuex';
import GradeDialog from "./GradeDialog";

export default {
    name: "AppointmentsForPatient",

    components: {
        GradeDialog
    },

    data(){
        return {
            nemanja: "nemanja",
            headers:[
                {
                    text: 'Discount', value: 'discount',fileterable: true
                },
                {
                    text: 'Price', value: 'price', fileterable:true            
                },
                {
                    text: 'Doctor', value: 'doctor',fileterable: true
                },
                { 
                    text: 'Patient', value: 'patient',fileterable: true
                },
                 {
                    text: 'Clinic', value: 'clinic',fileterable: true
                },
                {
                    text: 'Room', value: 'room', fileterable:true            
                },
                {
                    text: 'Date', value: 'date',fileterable: true
                },
                { 
                    text: 'Examination type', value: 'examinationType', fileterable: true
                },
                { 
                    text: 'Grade', value: 'grade', sortable: false
                }
            ]
        }
    },
    created(){
        this.fetchPatientAppointments(localStorage.getItem('user_email'));
    },
    computed: mapGetters('appointments',['allAppointments']),
    
    methods:{
        ...mapActions('appointments', ['fetchPatientAppointments']),
    },
    
}
</script>

<style scoped>

</style>