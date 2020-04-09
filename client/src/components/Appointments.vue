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
    <v-btn v-on:click="getAllAppointments" color="primary">Get All Appointments</v-btn>

</div>
</template>

<script>
import axios from 'axios'
export default {
    name: "Appointments",
    data(){
        return {
            appointment:{
                price : null
            }
        }
    },
    methods:{
        addAppointment(){
            axios
            .post('http://localhost:8081/appointments/add', this.appointment)
            .then(response => {
                if(response.data){
                    alert("Uspesno dodat pregled");
                }
            })
        },
        getAllAppointments(){
            axios
            .get('http://localhost:8081/appointments/getAppointments')
            .then(response => {
                alert(response.data);
            })
        }
    }
}
</script>