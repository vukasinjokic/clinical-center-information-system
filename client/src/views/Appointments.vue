<template>
<div>
    <v-container>
        <v-card>
            <v-card-title>Appointments</v-card-title>
            
            <v-data-table
                        :headers="headers"
                        :items="allAppointments"
                        :items-per-page="5"
                        item-key="name"
                        class="blue-grey darken-4 white--text"
                        dark grey>
                    <template v-slot:item.room="{ item }">
                        {{item.roomName + " " + item.roomNumber}}
                    </template>
             <template v-slot:top>
                <v-toolbar flat class="blue-grey darken-4 white--text">
                    <v-spacer></v-spacer>
                    <AddFreeAppointment style="margin: 10px;"></AddFreeAppointment>
                </v-toolbar>
            </template>
            
            </v-data-table>
        </v-card>
    </v-container>
</div>
</template>

<script>

import {mapGetters, mapActions} from 'vuex';
import AddFreeAppointment from '../components/AddFreeAppointment.vue'

export default {
    name: "Appointments",
    components: {
        AddFreeAppointment
    },
    data(){
        return {
            appointment:{
                id: null,
                price : null
            },
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
        this.fetchAppointments();
    },
    computed: mapGetters('appointments',['allAppointments']),
    
    methods:{
        ...mapActions('appointments', ['fetchAppointments']),
        addAppointment(){
            console.log("Implementiraj");
        },
    }
}
</script>
<style>
.desno{
    position: relative;
    left: 326px;
}
</style>