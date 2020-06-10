<template>
    <v-card max-width="544"
            class="mx-auto">
            <v-card-title>
                <v-toolbar color="primary" dark> 
                <v-list-item>
                <v-avatar color="grey">
                    <v-icon dark>mdi-account-circle</v-icon>
                </v-avatar> 
                <v-list-item-content>
                    <v-list-item-title class="headline">Patient profile</v-list-item-title>
                </v-list-item-content>
            </v-list-item>
              </v-toolbar>
            </v-card-title>
            <v-card-text>
                <v-container>
                    <v-row>
                        <v-col cols="12" sm="6" md="6">
                            <v-text-field
                                dense
                                readonly
                                filled
                                outlined
                                v-model="getPatientProfile.firstName"
                                label="First name">
                            </v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="6">
                            <v-text-field
                                dense
                                readonly
                                filled
                                outlined
                                v-model="getPatientProfile.lastName"
                                label="Last name"
                            ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6" md="6">
                            <v-text-field
                                dense
                                filled
                                readonly
                                outlined
                                v-model="getPatientProfile.email"
                                label="Email"
                            ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm = "6" md="6">
                            <v-text-field
                                dense
                                filled
                                readonly
                                outlined
                                v-model="getPatientProfile.address"
                                label="Address"
                            ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm = "6">
                            <v-text-field 
                                dense
                                filled
                                readonly
                                outlined
                                v-model="getPatientProfile.city"
                                label="City"
                            ></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6">
                            <v-text-field 
                                dense
                                filled
                                outlined
                                readonly
                                v-model="getPatientProfile.country"
                                label="Country"  
                            required></v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6">
                            <v-text-field
                                dense
                                outlined
                                readonly
                                filled
                                v-model="getPatientProfile.phoneNumber"
                                label="Telephone number">
                            </v-text-field>
                        </v-col>
                        <v-col cols="12" sm="6">
                            <v-text-field
                                filled
                                dense
                                outlined
                                readonly
                                v-model="getPatientProfile.socialSecurityNumber"
                                label="Social security number">
                            </v-text-field>
                        </v-col>
                    </v-row>
                </v-container>
            </v-card-text>
        </v-card>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    data(){
        return{

        }
    },
    computed:{
        ...mapGetters('doctor',['getPatientProfile'])
    },
    methods:{
        
    }
}
</script>