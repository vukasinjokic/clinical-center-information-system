<template>
    <div>
        <v-data-table
        :headers="headers"
        :items="appointmentRequests"
        item-key="name"
        group-by="type"
        class="elevation-1"
        >
            <template #item.doctorName="{ item }">{{ item.doctor.firstName }} {{ item.doctor.lastName }}</template>
            <template #item.patientName="{ item }">{{ item.patient.firstName }} {{ item.patient.lastName }}</template>
            <template #item.examinationType="{ item }">{{ item.doctor.examinationType.name }}</template>
            <template #item.times="{ item }" >{{getTime(item)}}</template>
            <template #item.reserveRoomButton="{ item }" ><v-btn @click="reserveRoom(item)" color="orange lighten-1" dark>Reserve Room</v-btn></template>

        </v-data-table>
    <v-dialog v-model="roomsDialog" eager>    
        <Rooms ref="roomsComponent"></Rooms>
    </v-dialog>
    </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
import Rooms from '../views/Rooms.vue'
export default {
    components: {
        Rooms
    },
    data(){
        return {
            headers: [
                {
                    text: 'Doctor', value: 'doctorName',fileterable: true
                },
                {
                    text: 'Patient', value: 'patientName',fileterable: true
                },
                {
                    text: 'Requested Time', value: 'times',fileterable: true
                },
                {
                    text: 'Price', value: 'price',fileterable: true
                },
                {
                    text: 'Discount', value: 'discount',fileterable: true
                },
                {
                    text: 'Examination type', value: 'examinationType',fileterable: true
                },
                {
                    text: 'Action', value: 'reserveRoomButton',fileterable: true
                },
                
            ],
            roomsDialog : false,
            
        }   
    },
    methods : {
        ...mapActions('appointmentRequests', ['fetchAppRequests']),

        getTime(item){
            if(item.type == 'DOCTOR') return new Date(item.time).toLocaleString();
            return new Date(item.predefAppointment.date).toLocaleString();
        },

        reserveRoom(request){
            console.log(request.doctor);
            this.roomsDialog = true;
            this.$refs.roomsComponent.setUpFields(request);
        }

        

        
    },
    computed : {  
        ...mapGetters('appointmentRequests', ['getAppointmentRequests']),

        appointmentRequests: function(){
            return this.getAppointmentRequests();
        }
    },
    created(){
        this.fetchAppRequests();

        
    }



}
</script>

<style>
</style>