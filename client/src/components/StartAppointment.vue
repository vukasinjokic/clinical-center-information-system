<template>
    <div>
        <v-container>
        <v-card width="81%" v-if="appointment">
            <v-card-title>{{appointment.examinationType}}</v-card-title>
            <v-text>
                <v-container>
                    <v-form ref="form">
                        <v-autocomplete
                            v-model="diagnosis"
                            :items="Object.entries(codebook.diagnoses)"
                            color="black"
                            label="Diagnoses"
                            placeholder="Start typing to Search"
                            prepend-icon="mdi-book-search-outline"
                        >
                            <template v-slot:selection="data">
                                <!-- HTML that describe how select should render selected items -->
                                {{ data.item[0] }} - {{ data.item[1] }}
                            </template>
                            <template v-slot:item="data">
                                <!-- HTML that describe how select should render items when the select is open -->
                                {{ data.item[0] }} - {{ data.item[1] }}
                            </template>
                        </v-autocomplete>

                        <v-textarea
                        v-model="report"
                        filled
                        label="Report"
                        auto-grow
                        ></v-textarea>
                        <v-col>
                            <v-row>
                                <v-btn color="blue-grey darken-3" outlined="true" @click="prescriptionDialog = true">Make prescription</v-btn>
                                <v-spacer></v-spacer>
                                <v-btn color="blue-grey darken-3" outlined="true" @click="scheduleAnotherAppBtnClicked">Schedule another appointment</v-btn>
                                <v-spacer></v-spacer>
                                <v-btn color="blue-grey darken-3" outlined="true" @click="medicalRecordDialog = true">Patients medical record</v-btn>
                            
                            </v-row>
                        </v-col>
                        <v-col></v-col>
                        <v-col md="5" offset-md="10">
                            <v-spacer></v-spacer>
                            <v-btn
                                color="orange lighten-1" dark @click="finishBtnClicked()">Finish</v-btn>
                        </v-col>
                    </v-form>
                </v-container>
            </v-text>
        </v-card>

        <v-dialog v-model="prescriptionDialog" eager persistent max-width="55%">    
            <v-card>
                <v-card-title>Make prescription</v-card-title>
                <v-text>
                    <v-container>
                        <v-toolbar flat >
                            <v-spacer></v-spacer>
                            <v-dialog v-model="addMedicineDialog" persistent max-width="50%">
                                <template v-slot:activator="{ on }">
                                    <v-btn color="orange lighten-1" outlined class="mb-2" v-on="on">Add medicine</v-btn>
                                </template>
                                <v-card>
                                    <v-card-title>
                                        Add medicine
                                    </v-card-title>
                                    <v-card-text>
                                        <v-container>
                                        <v-form>
                                            <v-autocomplete
                                                v-model="medication"
                                                :items="Object.entries(codebook.medications)"
                                                color="black"
                                                label="Medications"
                                                placeholder="Start typing to Search"
                                                prepend-icon="mdi-book-search-outline"
                                            >
                                                <template v-slot:selection="data">
                                                    <!-- HTML that describe how select should render selected items -->
                                                    {{ data.item[0] }} - {{ data.item[1] }}
                                                </template>
                                                <template v-slot:item="data">
                                                    <!-- HTML that describe how select should render items when the select is open -->
                                                    {{ data.item[0] }} - {{ data.item[1] }}
                                                </template>
                                            </v-autocomplete>

                                            <v-text-field
                                            v-model="description"
                                            label="Description"
                                            ></v-text-field>
                                        </v-form>
                                        </v-container>
                                    </v-card-text>
                                    <v-card-actions>
                                        <v-spacer></v-spacer>
                                        <v-btn color="blue darken-1" text @click="saveMedication">Save</v-btn>
                                    </v-card-actions>
                                </v-card>
                            </v-dialog>
                        </v-toolbar>
                        <v-data-table style="margin: 10px; 0px; 0px; 0px;"
                            calculate-widths
                            :headers="headers"
                            :items="prescription"
                        >
                        </v-data-table>
                        <v-col md="2" offset-md="10" >
                        <v-btn color="orange lighten-1" v-bind:disabled="prescription.length === 0" class="white--text" v-on="on" @click="prescriptionDialog = false">Finish</v-btn>
                        </v-col>
                    </v-container>
                </v-text>
            </v-card>
        </v-dialog>
        </v-container>
        <v-dialog v-model="scheduleAppDialog" width="55%" eager>    
            <ScheduleAppointment style="width:100%" ref="scheduleAppComponent" @scheduled="schedulingFinished"></ScheduleAppointment>
        </v-dialog>
        <v-dialog v-model="medicalRecordDialog"  width="80%">
            <MedicalRecord ref="medicalRecordComponent" viewMode="doctor" @closeRecordDialog="medicalRecordDialog = false" v-bind:patientEmail="patientEmail"></MedicalRecord>
        </v-dialog>
        
    </div>
</template> 

<script>
import { mapActions, mapGetters } from 'vuex';
import ScheduleAppointment from './ScheduleAppointment'
import MedicalRecord from './MedicalRecord'

export default {
    components: {
        ScheduleAppointment,
        MedicalRecord
    },
    data(){
        return {
            diagnosis : '',
            report : '',
            prescriptionDialog : false,
            addMedicineDialog : false,
            medicalRecordDialog : false,
            prescription : [],
            medication : null,
            description : '',
            headers : [
                {text : "Code", value : 'code'},
                {text : "Medicine Name", value : 'medicineName'},
                {text : "Description", value : 'description'},

            ],
            scheduleAppDialog : false
        }   
    },
    methods : {
        ...mapActions('startAppointment', ['fetchAppointment', 'fetchCodebook', 'handleFinishAppointment', 'fetchPatientEmail']),

        scheduleAnotherAppBtnClicked(){
            this.scheduleAppDialog = true;
            this.$refs.scheduleAppComponent.setPatientEmail(this.getPatientEmail())
        },

        schedulingFinished(){
            this.scheduleAppDialog = false;
        },

        saveMedication(){
            this.prescription.push({code : this.medication[0], medicineName : this.medication[1], description : this.description});
            this.medication = null;
            this.description = '';
            this.addMedicineDialog = false;
        },

        finishBtnClicked(){
            this.handleFinishAppointment({
                appointmentId : this.getAppointmentId(),
                diagnosis : this.diagnosis[0] + ' ' + this.diagnosis[1],
                report : this.report, 
                prescriptionToAdd : this.prescription
            });
            this.$router.replace(':16/calendar');
        }
        
    },
    computed : {
        ...mapGetters('startAppointment', ['getAppointment', 'getCodebook', 'getAppointmentId', 'getPatientEmail']),

        appointment : function() {
            return this.getAppointment();
        },
        codebook : function(){
            return this.getCodebook();
        },
        appointmentId : function() {
            return this.getAppointmentId();
        },

        patientEmail : function() {
            return this.getPatientEmail();
        }

    },
    created(){
        this.fetchAppointment(this.appointmentId); 
        this.fetchCodebook(this.appointmentId);
        this.fetchPatientEmail(this.appointmentId);
    }



}
</script>

<style>

</style>