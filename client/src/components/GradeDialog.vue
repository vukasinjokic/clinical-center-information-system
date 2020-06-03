<template>
    <v-row justify="center">
        <v-dialog v-model="dialog" persistent max-width="600px">
            <template v-slot:activator="{ on }">
                <v-btn  v-on="on" color="blue">Grade</v-btn>
            </template>
            <v-card>
                <v-toolbar height="45px" color="blue" class="white--text">
                    <span class="headline">Grade</span>
                </v-toolbar>
                <v-card-text>
                    <v-container>
                        <v-row>
                            <v-col cols="12" sm = "6">
                                <v-text-field
                                label="Doctor" 
                                v-model="gradeDoctor.fullName"
                                readonly>
                                </v-text-field>
                            </v-col>
                            <v-col cols="12" sm="6">
                                <v-text-field 
                                label="Clinic"
                                v-model="gradeClinic.name" 
                                readonly>
                                </v-text-field>
                            </v-col>
                            <v-col cols="12" sm="6">
                                <v-text-field 
                                label="Grade doctor"
                                v-model="doctorGradeValue"
                                readonly>
                                </v-text-field>
                                <!-- <v-select
                                v-model="room"
                                :items="allRoomsNumber"
                                label="Examination room"
                                required>
                                </v-select> -->
                            </v-col>
                            <v-col cols="12" sm="6">
                                <v-text-field 
                                label="Grade clinic"
                                v-model="clinicGradeValue"
                                readonly>
                                </v-text-field>
                                <!-- <v-select
                                hint="Choose examination type"
                                :items="getDoctors"
                                label="Doctor(choose examination type)"
                                v-model="doctor">
                                </v-select> -->
                            </v-col>
                        </v-row>
                    </v-container>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue darken-1" text v-on:click="closeDialog">Close</v-btn>
                    <v-btn color="blue darken-1" text @click="save">Save</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-row>
</template>

<script>
export default {
    name: "GradeDialog",
    props: ["doctor"],

    data() {
        return {
            dialog: false,
            gradeDoctor: {},
            gradeClinic: {},
            doctorGradeValue: "TODO",
            clinicGradeValue: "TODO"
        }
    },

    methods: {
        closeDialog(){
            this.dialog = false;
        },
        save(){
            this.closeDialog();
        }
    },

    created() {
        this.gradeDoctor = {
            id: this.doctor.id,
            email: this.doctor.email,
            fullName: this.doctor.firstName + " " + this.doctor.lastName
        };
        this.gradeClinic = {
            id: this.doctor.clinicId,
            name: this.doctor.clinic
        };
    }
}
</script>

<style scoped>

</style>