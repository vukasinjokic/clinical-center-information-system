<template>
    <div>
        <v-container>
            <v-card>
                <v-card-title justify="left">
                    Istorija pregleda i operacija

                    <v-spacer></v-spacer>
                    <v-card-actions>
                        <GradeDoctor  color="blue" v-bind:doctors="getDoctors"/>
                        <GradeClinic  color="blue" v-bind:clinics="getClinics"/>
                    </v-card-actions>
                </v-card-title>

                <v-data-table
                :headers="headers"
                :items="allAppointments"
                :items-per-page="5"
                class="blue-grey darken-4 white--text"
                dark grey>
                    <template v-slot:item.doctor="{ item }">
                        {{item.doctor.firstName + " " + item.doctor.lastName}}
                    </template>
                </v-data-table>
            </v-card>
        </v-container>
    </div>
</template>

<script>
import {mapGetters, mapActions} from 'vuex';
import GradeDoctor from "./GradeDoctor";
import GradeClinic from "./GradeClinic";

export default {
    name: "AppointmentsForPatient",

    components: {
        GradeDoctor,
        GradeClinic
    },

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
                }
            ]
        }
    },
    created(){
        this.fetchPatientAppointments(localStorage.getItem('user_email'));
    },
    
    computed:  {
        ...mapGetters('appointments',['allAppointments']),

        getClinics() {
            var ids = [];
            var clinics = [];
            this.allAppointments.forEach(appointment => {
                // Avoid duplicates
                if (!ids.includes(appointment.doctor.clinicId)) {
                    ids.push(appointment.doctor.clinicId);
                    clinics.push({
                        id: appointment.doctor.clinicId,
                        name: appointment.doctor.clinic,
                    })   
                }
            });
            return clinics;
        },

        getDoctors() {
            var ids = [];
            var doctors = [];
            this.allAppointments.forEach(appointment => {
                // Avoid duplicates
                if (!ids.includes(appointment.doctor.id)) {
                    ids.push(appointment.doctor.id);
                    doctors.push({
                        id: appointment.doctor.id,
                        mail: appointment.doctor.email,
                        fullName: appointment.doctor.firstName + " " + appointment.doctor.lastName
                    })
                }
            });
            return doctors;
        }
    },

    methods:{
        ...mapActions('appointments', ['fetchPatientAppointments']),
    },
    
}
</script>

<style scoped>

</style>