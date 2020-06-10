<template>
    <div>
        <v-card>
            <v-card-title>
                <v-container>
                    <v-row cols="12" sm = "6">
                        <v-col>
                            <v-text-field
                            v-model="getMedicalRecord.weight"
                            label="Weight"
                            show-details
                            v-bind:readonly="viewMode != 'doctor'">
                            </v-text-field>
                        </v-col>
                        <v-col>
                            <v-text-field
                            v-model="getMedicalRecord.height"
                            label="Height"
                            show-details
                            v-bind:readonly="viewMode != 'doctor'">
                            </v-text-field>
                        </v-col>
                        <v-col>
                            <v-text-field
                            v-model="getMedicalRecord.bloodType"
                            label="Blood type"
                            show-details
                            v-bind:readonly="viewMode != 'doctor'">
                            </v-text-field>
                        </v-col>
                        <v-col>
                            <v-text-field
                            v-model="getMedicalRecord.leftEye"
                            label="Left eye diopter"
                            show-details
                            v-bind:readonly="viewMode != 'doctor'">
                            </v-text-field>
                        </v-col>
                        <v-col>
                            <v-text-field
                            v-model="getMedicalRecord.rightEye"
                            label="Right eye diopter"
                            show-details
                            v-bind:readonly="viewMode != 'doctor'">
                            </v-text-field>
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col >
                            <v-data-table
                            :headers="historyHeaders"
                            :items="getHistoryItems"
                            class="grey lighten-4"
                            >

                            <template v-slot:item.history="props" v-if="viewMode == 'doctor'">
                                <v-edit-dialog 
                                :return-value.sync="props.item.history"
                                @save="save(props.item)"
                                @cancel="cancel"
                                @open="!isCurrentDoctorAuthor(props.item)"
                                @close="close"
                                > {{ props.item.history }}
                                <template v-slot:input v-if="editMode">
                                    <v-text-field
                                    v-model="props.item.history"
                                    label="Edit"
                                    counter
                                    ></v-text-field>
                                </template>
                                </v-edit-dialog>
                            </template>

                            </v-data-table>
                        </v-col>
                        <v-col>
                            <v-data-table
                            :headers="prescriptionHeaders"
                            :items="getPrescriptionItems"
                            class="grey lighten-4"
                            >
                            </v-data-table>
                        </v-col>
                    </v-row>
                    <v-row>
            
            </v-row>
                </v-container>
            </v-card-title>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="orange lighten-1" text outlined @click="cancelUpdate" v-if="viewMode == 'doctor'">Cancel</v-btn>
                <v-btn color="orange lighten-1" dark @click="updateMedicalRecord" v-if="viewMode == 'doctor'">Update</v-btn>
            
            </v-card-actions>
        </v-card>
    </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
    name: "MedicalRecord",
    props : ['viewMode', 'patientEmail'],
    data() {
        return {
            historyHeaders: [
                {text: "History", value: "history"},
            ],

            prescriptionHeaders: [
                {text: "Prescriptions", value: "medication"},
                {text: "Times", value: "time"},
                {text: "Verified", value: "verified"}
            ],
            editMode : false,
            
            backup : '',
            
        }
    },

    methods: {
        ...mapActions("patient", ["fetchMedicalRecord", "resetMedicalRecord"]),
        ...mapActions("snackbar", ["showWarning"]),
        ...mapActions("doctor", ["updateRecord"]),
        
        cancelUpdate(){
            this.resetMedicalRecord(this.getRecordBackup);
            this.$emit('closeRecordDialog');
        },

        updateMedicalRecord(){
            this.updateRecord(this.getMedicalRecord);
            this.$emit('closeRecordDialog')
        },

        save(item){
            this.getMedicalRecord.reports[item.history] = this.getMedicalRecord.reports[this.backup];
            delete this.getMedicalRecord.reports[this.backup];
            this.backup = '';

        },

        isCurrentDoctorAuthor(item){
            this.editMode = this.getMedicalRecord.reports[item.history] === localStorage.getItem("user_email") || (this.backup == item.history && this.getMedicalRecord.reports[this.backup] === localStorage.getItem("user_email"));
            if(!this.editMode)
                this.showWarning("You can't edit reports you didn't write");  
            else{
                this.backup = item.history;
            }         
        }
    },

    computed: {
        ...mapGetters("patient", ["getMedicalRecord", "getRecordBackup"]),

        getHistoryItems() {
            var items = [];
            let keys = Object.keys(this.getMedicalRecord.reports);
            keys.forEach(key => {
                items.push({
                    history: key
                });
            });
            return items;
        },

        getPrescriptionItems() {
            var items = [];
            this.getMedicalRecord.prescriptions.forEach(prescription => {
                for (let i = 0; i < prescription.medications.length; i++) {
                    const medication = prescription.medications[i];
                    const time = prescription.times[i];
                    items.push({
                        medication: medication,
                        time: time,
                        verified: prescription.verified ? "Overen" : "Neoveren"
                    });
                }
            });
            return items;
        },
        
        getWeight() {
            return this.getMedicalRecord.weight;
        },

        getHeight() {
            return this.getMedicalRecord.height;
        },

        getLeftEye() {
            return this.getMedicalRecord.leftEye;
        },

        getRightEye() {
            return this.getMedicalRecord.rightEye;
        },

        getBloodType() {
            return this.getMedicalRecord.bloodType;
        },
    },

    created() {
        var user_email = localStorage.getItem('user_email');
        var user_role = localStorage.getItem('user_role');
        if(user_role === 'ROLE_PATIENT')
            this.fetchMedicalRecord(user_email);
        else
            this.fetchMedicalRecord(this.$route.params.email);
        console.log(this.$route.params.email);
    }
}
</script>

<style scoped>

</style>