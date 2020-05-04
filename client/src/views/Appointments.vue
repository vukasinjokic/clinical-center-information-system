<template>
<div>
    <v-container>
        <div class="text-right">
            <AddFreeAppointment></AddFreeAppointment>
        </div>
        <div v-for="app in allAppointments" :key="app.id">
            {{app.id}}
            {{app.discount}}
            {{app.price}}
            {{app.doctor}}
            {{app.patient}}
            {{app.clinic}}
            {{app.operationRoom}}
            {{app.examinationType}}
            {{app.date}}
        </div>
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
            }
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