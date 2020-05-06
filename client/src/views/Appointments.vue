<template>
<div>
    <v-container>
        <div class="desno" style="margin: 10px">
            <AddFreeAppointment></AddFreeAppointment>
        </div>
        <v-data-table
                    :headers="headers"
                    :items="allAppointments"
                    :items-per-page="5"
                    item-key="name"
                    dark grey>
        </v-data-table>
        
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
                    text: 'Doctor', value: 'doctor',fileterable: true
                },
                { 
                    text: 'Patient', value: 'patient',fileterable: true
                },
                 {
                    text: 'Clinic', value: 'clinic',fileterable: true
                },
                {
                    text: 'Operation room', value: 'operationRoom', fileterable:true            
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
        }
    },
    
}
</script>
<style>
.desno{
    position: relative;
    left: 326px;
}
</style>