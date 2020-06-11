<template>
<div style="margin: 30px 70px 0px 70px">
    <v-card>
        <v-card-title>
            Patients
            <v-spacer></v-spacer>
            <v-text-field
                style="width:100px"
                dense
                outlined
                v-model="search"
                append-icon="mdi-magnify"
                label="Search"
                hide-details
            ></v-text-field>
        </v-card-title>
        <v-data-table
            class="blue-grey darken-4 white--text"
            dark
            :search="search"
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
    </v-card>
        <v-dialog v-model="dialog" max-width="554px">
            <v-card>
                <PatientProfile></PatientProfile>
                <v-card-actions v-if="userRole != 'ROLE_NURSE'">
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
                    text: 'Name', value: 'firstName',filterable: true
                },
                {
                    text: 'Last name', value: 'lastName', sortable:false
                },
                {
                    text: 'SSNumber', value: 'socialSecurityNumber'
                },
                {
                    text: 'Email', value:'email', filterable: true
                },
                {
                    text:'City', value:'city', sortable:false
                },
                {
                    text:'Phone number', value:'phoneNumber', filterable:false, sortable:false
                },
                {  text: 'Profil pacijenta', value:"actions"}
            ],
            search: '',
            dialog: false,
            selectedItem: {},
            userRole: ''
        }
    },
    created(){
        this.fetchPatients();
        this.userRole = localStorage.getItem("user_role");
    },
    computed: {
        ...mapGetters('patient',['allPatients'])
    },
    methods:{
        ...mapActions('patient',['fetchPatients','fetchMedicalRecord']),
        ...mapActions('doctor',['fetchPatientProfile','canStaffViewRecord']),
        ...mapActions('startAppointment', ['startAppointmentForPatient']),

        showPatient(item){
            this.dialog = true;
            this.selectedItem = Object.assign({}, item);
            this.fetchPatientProfile(item.email);
        },
        goTostartAppointment(){
            this.startAppointmentForPatient(this.selectedItem.email)
            .then(() => {
                this.$router.push('startAppointment/' + this.selectedItem.email);
            }).catch(()=>{});
            
        },
        goTomedicalRecord(){
            this.canStaffViewRecord(this.selectedItem.email)
                .then(() => {
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