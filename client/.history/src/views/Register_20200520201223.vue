<template>
    <div id="register">
        <v-container >
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
                                :rules="[ruleRequired]" 
                                label="Phone number" 
                                required>
                            </v-text-field>
                        </v-col>
                        <v-col cols="10" sm="5" md="5">
                            <v-text-field 
                                v-model="user.socialSecurityNumber" 
                                :rules="[ruleRequired]" 
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
                        <v-spacer></v-spacer>
                        <v-col cols="6"/>
                        <v-col cols="2" sm="2">
                            <v-btn :to="`/`" text class="grey white--text ">Login</v-btn>
                        </v-col>
                        <v-col cols="3" class="pa-2" sm="2" >
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
        }
    },
    methods: {
        async submit() {
            if (this.$refs.registerForm.validate()) {
                await axios.post('http://localhost:8081/auth/registerPatient', this.user)
                .then(response => {
                    if (response.status === 200) {
                        alert("Vaš zahtev za registraciju je poslat serveru. Odgovor da li je zahtev prihvaćen ili odbijen ćete dobiti na mejl.")
                    } else {
                        console.log(response)
                        alert("Unknown error: " + response.status + ".\nMessage: " + response.data);
                    }
                })
                .catch(response => {
                    if (response.status >= 400) {
                        alert("Error: " + response.status + ".\nMessage: " + response.data)
                    } else {
                        alert("Unknown error: " + response.status + ".\nMessage: " + response.data);
                    }
                });
            }
        },
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
                password: ""
            },
            valid: true,
            verify: "",
            emailRules: [
                v => !!v || "Required",
                v => /.+@.+\..+/.test(v) || "E-mail must be valid"
            ],
            show1: false,
            
        }
    }
}
</script>



<style></style>