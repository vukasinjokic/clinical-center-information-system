<template>
<div>
    <div class="home">
    </div>
    <v-form>
    <v-text-field
    v-model="patient.name"
    label="patient name"
    name="name"
    type="text"
    />
    </v-form>

    <v-btn v-on:click="addPatient" color="primary">Add patient</v-btn>
    <v-btn v-on:click="getAllPatients" color="primary">Get All patients</v-btn>

</div>
</template>

<script>
import axios from 'axios'
export default {
    name: "patients",
    data(){
        return {
            patient:{
                name : null
            }
        }
    },
    methods:{
        addPatient(){
            axios
            .post('http://localhost:8081/patients/add', this.patient)
            .then(response => {
                if(response.data){
                    alert("Uspesno dodat pacijent");
                }
            })
        },
        getAllPatients(){
            axios
            .get('http://localhost:8081/patients/getPatients')
            .then(response => {
                alert(response.data);
            })
        }
    }
}
</script>