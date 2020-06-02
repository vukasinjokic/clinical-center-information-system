<template>
    <div>
        <v-container>
            <v-card>
                <v-card-title>Istorija pregleda i operacija</v-card-title>
                
                <v-data-table
                            :headers="headers"
                            :items="allAppointments"
                            :items-per-page="5"
                            item-key="name"
                            class="blue-grey darken-4 white--text"
                            dark grey>
                </v-data-table>
            </v-card>
        </v-container>
    </div>
</template>

<script>
import {mapGetters, mapActions} from 'vuex';
export default {
    name: "AppointmentsForPatient",
    data(){
        return {
            headers:[
                 {
                    text: 'Discount', value: 'discount',fileterable: true
                },
                {
                    text: 'Price', value: 'price', fileterable:true            
                },
                {
                    text: 'Doctor', value: 'doctor.email',fileterable: true
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
                    text: 'Examination type', value: 'examinationType'
                },
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