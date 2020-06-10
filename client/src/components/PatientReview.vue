<template>
<div style="margin: 30px 70px 0px 70px">
        <v-data-table
            class="blue-grey darken-4 white--text"
            dark
            :headers="headers"
            :items="allPatients"
            :items-per-page="5"
            item-key="item.email"
        >
            <template v-slot:item.actions="{ item }">
                <v-btn
                    color="primary"
                    small
                    class="mr-2"
                    @click="showPatient(item)"
                >Show patient</v-btn>
            </template>
        </v-data-table>
        <v-dialog v-model="dialog" max-width="554px">
            <v-card>
                <PatientProfile></PatientProfile>
                <v-card-actions>
                    <v-btn
                        color="primary"
                        rounded
                        outlined
                        @click="goTostartAppointment"
                        >Start appointment</v-btn>
                    <v-btn
                        color="primary"
                        rounded
                        @click="goTomedicalRecord"
                        >Medical record</v-btn>
                    <v-spacer></v-spacer>
                    <v-btn
                        color="red darken-1"
                        text
                        @click="dialog = false"
                        >
                        Close
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
        <v-spacer></v-spacer>
</div>
</template>

<script>
import {mapGetters,mapActions} from 'vuex';
import PatientProfile from './PatientProfile'
export default {
    name: "PatientReview",
    components: {PatientProfile},
    data(){
        return{
            headers: [
                {
                    text: 'Name', value: 'firstName',fileterable: true
                },
                {
                    text: 'Last name', value: 'lastName', sortable:false
                },
                {
                    text: 'SSNumber', value: 'socialSecurityNumber'
                },
                {
                    text: 'Email', value:'email', fileterable: true
                },
                {
                    text:'City', value:'city', sortable:false
                },
                {
                    text:'Phone number', value:'phoneNumber', sortable:false
                },
                {  text: 'Profil pacijenta', value:"actions"}
            ],
            dialog: false,
            selectedItem: {},
        }
    },
    created(){
        this.fetchPatients();
    },
    computed: {
        ...mapGetters('patient',['allPatients'])
    },
    methods:{
        ...mapActions('patient',['fetchPatients','fetchMedicalRecord']),
        ...mapActions('doctor',['fetchPatientProfile','canStaffViewRecord']),

        showPatient(item){
            this.dialog = true;
            this.selectedItem = Object.assign({}, item);
            this.fetchPatientProfile(item.email);
        },
        goTostartAppointment(){
            console.log(this.selectedItem.email);
        },
        goTomedicalRecord(){
            this.canStaffViewRecord(this.selectedItem.email)
                .then(() => {
                    console.log("trueeeeeeeee");
                    this.$router.push('patient/record/'+ this.selectedItem.email);
                    this.fetchMedicalRecord(this.selectedItem.email);
                    //redirektujemo na medical Record
                }).catch(()=> {
                    console.log("nema pravo pristupa");
                });

        }
    }
}
</script>