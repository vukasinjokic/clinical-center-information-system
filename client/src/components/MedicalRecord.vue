<template>
    <div>
        <v-card>
            <v-card-title>
                <v-container>
                    <v-row cols="12" sm = "6">
                        <v-col>
                            <v-text-field
                            v-model="getWeight"
                            label="Weight"
                            show-details
                            readonly>
                            </v-text-field>
                        </v-col>
                        <v-col>
                            <v-text-field
                            v-model="getHeight"
                            label="Height"
                            show-details
                            readonly>
                            </v-text-field>
                        </v-col>
                        <v-col>
                            <v-text-field
                            v-model="getBloodType"
                            label="Blood type"
                            show-details
                            readonly>
                            </v-text-field>
                        </v-col>
                        <v-col>
                            <v-text-field
                            v-model="getLeftEye"
                            label="Left eye diopter"
                            show-details
                            readonly>
                            </v-text-field>
                        </v-col>
                        <v-col>
                            <v-text-field
                            v-model="getRightEye"
                            label="Right eye diopter"
                            show-details
                            readonly>
                            </v-text-field>
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col sm = "4">
                            <v-data-table
                            :headers="historyHeaders"
                            :items="getHistoryItems"
                            class="blue-grey darken-4 white--text"
                            dark>
                            </v-data-table>
                        </v-col>
                        <v-col>
                            <v-data-table
                            :headers="prescriptionHeaders"
                            :items="getPrescriptionItems"
                            class="blue-grey darken-4 white--text"
                            dark>
                            </v-data-table>
                        </v-col>
                    </v-row>
                </v-container>
            </v-card-title>
        </v-card>
    </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
    name: "MedicalRecord",

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
        }
    },

    methods: {
        ...mapActions("patient", ["fetchMedicalRecord"])
    },

    computed: {
        ...mapGetters("patient", ["getMedicalRecord"]),

        getHistoryItems() {
            var items = [];
            this.getMedicalRecord.history.forEach(element => {
                items.push({
                    history: element
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
        this.fetchMedicalRecord();
    }
}
</script>

<style scoped>

</style>