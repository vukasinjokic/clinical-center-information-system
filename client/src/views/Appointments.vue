<template>
<div>
    <div class="home">
    </div>
    <v-form>
    <v-text-field
    v-model="appointment.price"
    label="Appointment price"
    name="price"
    type="number"
    />
    </v-form>

    <v-btn v-on:click="addAppointment" color="primary">Add appointment</v-btn>
  
    <div> 
        <div v-for="app in allAppointments" :key="app.id">
            {{app.id}}
            {{app.discount}}
            {{app.price}}
        </div>
    </div>

</div>
</template>

<script>

import {mapGetters, mapActions} from 'vuex';

export default {
    name: "Appointments",
    
    created(){
        this.fetchAppointments();
    },
    data(){
        return {
            appointment:{
                id: null,
                price : null
            }
        }
    },
    methods:{
        ...mapActions('appointments', ['fetchAppointments']),
        addAppointment(){
            console.log("Implementiraj");
        }
    },
    computed: mapGetters('appointments',['allAppointments']),
}
</script>