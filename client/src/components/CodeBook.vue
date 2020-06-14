<template>
    <div>
        <v-container>
                <v-card max-width="80%">
                    <v-card-title>
                        CodeBook
                    </v-card-title>
                    <v-card-text >
                        
                        <v-row>
                        <v-card style="width:49%">
                            <v-card-title>
                                Medications
                                <v-spacer></v-spacer>
                                <v-btn class="mx-2" fab dark  small color="blue lighten-1" @click="dialogAddMedication = true">
                                    <v-icon>mdi-plus</v-icon>
                                </v-btn>
                            </v-card-title>
                                <v-data-table
                                    :headers="medicationHeaders"
                                    :items="getMedications"
                                    class="grey lighten-4"
                                    >
                                </v-data-table>
                        </v-card>
                        <v-spacer></v-spacer>
                        <v-card style="width:49%">
                            <v-card-title>
                                Diagnoses
                                <v-spacer></v-spacer>
                                <v-btn class="mx-2" fab dark  small color="blue lighten-1" @click="dialogAddDiagnosis = true">
                                    <v-icon>mdi-plus</v-icon>
                                </v-btn>
                            </v-card-title>
                                
                                <v-data-table 
                                    :headers="diagnosesHeaders"
                                    :items="getDiagnoses"
                                    class="grey lighten-4"
                                    >
                                </v-data-table>
                        </v-card>
                        </v-row>
                    </v-card-text>
                </v-card>
            
        </v-container>
        <v-dialog v-model="dialogAddMedication" width="40%">
            <v-card>
                <v-card-title>New Medication</v-card-title>
                <v-card-text >
                    <v-form ref="medicationForm">
                    <v-row>
                        <v-spacer></v-spacer>
                        <v-text-field
                        v-model="medicationCode"
                        label="Code"
                        show-details
                        :rules="[requiredRule]"
                        >
                        </v-text-field>
                        <v-spacer></v-spacer>
                        <v-text-field
                        v-model="medicationName"
                        label="Medication name"
                        show-details
                        :rules="[requiredRule]"
                        >
                        </v-text-field>
                        <v-spacer></v-spacer>
                                <v-btn class="mx-2" tile dark color="blue lighten-1" width="20px" style="margin-top:10px" @click="addMedicationBtnClicked">Add</v-btn>
                    </v-row>
                    </v-form>
                </v-card-text>

            </v-card>
        </v-dialog>

        <v-dialog v-model="dialogAddDiagnosis" width="33%">
            <v-card>
                <v-card-title>New Diagnosis</v-card-title>
                <v-card-text >
                    <v-form ref="diagnosisForm">
                    <v-row>
                        <v-spacer></v-spacer>
                        <v-text-field
                        v-model="diagnosisCode"
                        label="Code"
                        show-details
                        :rules="[requiredRule]"
                        >
                        </v-text-field>
                        <v-spacer></v-spacer>
                        <v-text-field
                        v-model="diagnosisName"
                        label="Diagnosis name"
                        show-details
                        :rules="[requiredRule]"
                        >
                        </v-text-field>
                        <v-spacer></v-spacer>
                                <v-btn class="mx-2" tile dark color="blue lighten-1" width="20px" style="margin-top:10px" @click="addDiagnosisBtnClicked">Add</v-btn>
                    </v-row>
                    </v-form>
                </v-card-text>

            </v-card>
        </v-dialog>
        {{getDiagnoses}}
    </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
export default {
    data(){
        return{
            medicationHeaders: [
                {text: "Code", value: "code"},
                {text: "Medication", value: "medication"},
            ],
            diagnosesHeaders: [
                {text: "Code", value: "code"},
                {text: "Diagnosis", value: "diagnosis"},
            ],

            dialogAddMedication : false,
            dialogAddDiagnosis: false,
            medicationCode: '',
            medicationName: '',
            diagnosisCode: '',
            diagnosisName: ''
        }
    },
    methods: {
        ...mapActions('clinicCenterAdmin',['fetchCodeBook', 'addMedication', 'addDiagnosis']),
        ...mapGetters('clinicCenterAdmin', ['getCodeBook']),

        addMedicationBtnClicked(){
            this.addMedication({code: this.medicationCode, name: this.medicationName});
            this.resetMedicationForm();
        },

        addDiagnosisBtnClicked(){
            this.addDiagnosis({code: this.diagnosisCode, name: this.diagnosisName});
            this.resetDiagnosisForm();
        },

        resetMedicationForm(){
            this.dialogAddMedication = false;
            this.medicationCode = '';
            this.medicationName = '';
        },

        resetDiagnosisForm(){
            this.dialogAddDiagnosis = false;
            this.diagnosisCode = '';
            this.diagnosisName = '';
        }

    },
    
    computed: {

        codeBook : function(){
            return this.getCodeBook();
        },

        requiredRule(){
          return (value) => !!value || "Required.";
        },

        getDiagnoses() {
            var items = [];
            let keys = Object.keys(this.codeBook.diagnoses);
            keys.forEach(key => {
                items.push({
                    code: key,
                    diagnosis : this.codeBook.diagnoses[key]
                });
            });
            return items;
        },

        getMedications() {
            var items = [];
            let keys = Object.keys(this.codeBook.medications);
            keys.forEach(key => {
                items.push({
                    code: key,
                    medication: this.codeBook.medications[key]
                });
            });
            return items;
        },

        
    },
    created(){
        this.fetchCodeBook();
    },
}
</script>

<style>
</style>