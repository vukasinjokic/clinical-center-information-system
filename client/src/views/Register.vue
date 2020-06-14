<template>
    <div id="register">
        <v-container style="width:70%">
        <v-card class="elevation-12">
              <v-toolbar
                color="primary"
                dark
                flat
              >
                <v-toolbar-title>Registration form</v-toolbar-title>       
              </v-toolbar>
            <v-card-text>
                <v-form ref="registerForm" v-model="valid" lazy-validation>
                    <v-row>
                        <v-col cols="10" sm="5" md="5">
                            <v-text-field
                                v-model="user.firstName" 
                                :rules="[ruleRequired]" 
                                label="First Name" 
                                maxlength="20" 
                                required>  
                            </v-text-field>
                        </v-col>
                        <v-col cols="10" sm="5" md="5">
                            <v-text-field
                                v-model="user.lastName"
                                :rules="[ruleRequired]" 
                                label="Last Name" 
                                maxlength="20" 
                                required>
                            </v-text-field>
                        </v-col>
                        <v-col cols="10" sm="5" md="5">
                            <v-text-field 
                                v-model="user.email" 
                                :rules="emailRules" 
                                label="E-mail" 
                                required>
                            </v-text-field>
                        </v-col>
                        <v-col cols="10" sm="5" md="5">
                            <v-text-field 
                                v-model="user.address" 
                                :rules="[ruleRequired]" 
                                label="Address" 
                                required>
                            </v-text-field>
                        </v-col>
                        <v-col cols="10" sm="5" md="5">
                            <v-text-field 
                                v-model="user.city" 
                                :rules="[ruleRequired]" 
                                label="City" 
                                required>
                            </v-text-field>
                        </v-col>
                        <v-col cols="10" sm="5" md="5">
                            <v-text-field 
                                v-model="user.country" 
                                :rules="[ruleRequired]" 
                                label="Country" 
                                required>
                            </v-text-field>
                        </v-col>
                        <v-col cols="10" sm="5" md="5">
                            <v-text-field 
                                v-model="user.phoneNumber" 
                                :rules="[ruleRequired,numberRule]" 
                                label="Phone number" 
                                required>
                            </v-text-field>
                        </v-col>
                        <v-col cols="10" sm="5" md="5">
                            <v-text-field 
                                v-model="user.socialSecurityNumber" 
                                :rules="[ruleRequired,numberRule]" 
                                label="Social security number" 
                                required>
                            </v-text-field>
                        </v-col>

                        <v-col cols="10" sm="5" md="5">
                            <v-text-field 
                                v-model="user.password" 
                                :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'" 
                                :rules="[ruleRequired, minChar]" 
                                :type="show1 ? 'text' : 'password'" 
                                name="input-10-1" 
                                label="Password" 
                                hint="At least 8 characters" 
                                counter @click:append="show1 = !show1">
                            </v-text-field>
                        </v-col>
                        <v-col cols="10" sm="5" md="5">
                            <v-text-field 
                                block 
                                v-model="verify" :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'" 
                                :rules="[ruleRequired, passwordMatch]" 
                                :type="show1 ? 'text' : 'password'" 
                                name="input-10-1" 
                                label="Confirm Password" 
                                counter @click:append="show1 = !show1">
                            </v-text-field>
                        </v-col>

                        <v-col cols="10" sm="5" md="5" v-if="registerMode === 'admins'">
                            <v-select
                            v-model="userType"
                            label="User type"
                            :items="selectTypeOptions"
                            />
                        </v-col>

                        <v-col cols="10" sm="5" md="5" v-if="registerMode === 'admins'">
                            <v-select
                            v-model="selectedClinicName"
                            label="Clinic"
                            :items="getClinicNames()"
                            v-bind:disabled="userType != 'Clinic Admin'"
                            />
                        </v-col>

                        <v-spacer></v-spacer>
                        <v-col cols="6"/>
                        
                        <v-col cols="2" sm="2" >
                            <v-btn :to="`/`" v-if="registerMode !== 'admins'" text class="grey white--text ">Login</v-btn>
                        </v-col>
                        <v-col cols="4" class="pa-2" sm="2" >
                            <v-btn block :disabled="!valid" color="primary" @click="submit">Register</v-btn>
                        </v-col>
                    </v-row>
                </v-form>
            </v-card-text>
        </v-card>
        </v-container>
    </div>
</template>

<script>
import axios from 'axios';
import Vue from 'vue';

export default {
    computed: {
        passwordMatch() {
            return () => this.user.password === this.verify || "Password must match";
        },
        ruleRequired(){
            return (value) => !!value || "Required.";
        },
        minChar(){
            return (value) => (value && value.length >= 8) || "Min 8 characters";
        },
        numberRule(){
            return v => /(^(\+)?\d+(\.\d+)?$)/.test(v) || "Input must be number.";
        },
    },
    methods: {
        submit(){
            if(this.$refs.registerForm.validate()){
                if(this.registerMode === "admins"){
                    this.handleRegisterAdmins();
                }
                else{
                    this.handleRegisterPatient();
                }
            }
        },
        handleRegisterAdmins(){
            this.setSelectedClinic();
            Vue.$axios.post('http://localhost:8081/auth/registerAdmins', this.user)
            .then(response => {
                if(response.status === 200){
                    alert("Admin successfully registered");
                    this.$router.push('/clinicCenterAdmin')
                } else
                    this.$store.dispatch('snackbar/showError', response.data, {root: true});
            }).catch((error) => {
                this.$store.dispatch('snackbar/showError', error.response.data, {root: true});
            })
        },

        setSelectedClinic(){
            this.clinics.forEach(clinic => {
                if(clinic.name === this.selectedClinicName){
                    this.user.clinicId = clinic.id;
                }
            });
        },

        handleRegisterPatient() {
            axios.post('http://localhost:8081/auth/registerPatient', this.user)
            .then(response => {
                if (response.status === 200) {
                    this.$store.dispatch('snackbar/showSuccess', "Vaš zahtev za registraciju je poslat serveru. Odgovor da li je zahtev prihvaćen ili odbijen ćete dobiti na mejl.", {root: true});
                } else {
                    this.$store.dispatch('snackbar/showError', "Unknown error: " + response.status + ".\nMessage: " + response.data, {root: true});
                }
            })
            .catch(error => {
                this.$store.dispatch('snackbar/showError', error.response.data, {root: true});
            });
            
        },

        getClinicNames(){
            let clinicNames = [];
            this.clinics.forEach(clinic => {
                clinicNames.push(clinic.name);
            })
            return clinicNames;
        }
    },
    data(){
        return {
            user: {
                firstName: "",
                lastName: "",
                email: "",
                address: "",
                city: "",
                country: "",
                phoneNumber: "",
                socialSecurityNumber: "",
                password: "",
                clinicId: null
            },
            valid: true,
            verify: "",
            emailRules: [
                v => !!v || "Required",
                v => /.+@.+\..+/.test(v) || "E-mail must be valid"
            ],
            show1: false,
            registerMode: "",
            userType: "",
            selectTypeOptions: ["Clinic Admin", "Clinic Center Admin"],
            clinics: [],
            selectedClinicName : ''
            
        }
    },
    created(){
        if(localStorage.getItem("user_role") === "ROLE_CLINIC_CENTER_ADMIN"){
            this.registerMode = "admins";
            let self = this;
            Vue.$axios.get('http://localhost:8081/clinics/getClinics')
                .then(response => {
                    self.clinics = response.data;
            })
        }
    }
}
</script>



<style></style>