<template>
    <div  style="padding: 35px, 0px, 0px, 50px; width:55%" >
        <v-card >
            <v-card-title>Postupak zakazivanja pregleda i operacija</v-card-title>
            <v-text>
                <v-container>
                    <v-form ref="form">
                    <v-row>
                    <v-col cols="12" sm= "6" md= '6'>
                        <v-menu
                            ref="menu"
                            v-model="menuTime"
                            :close-on-content-click="false"
                            :nudge-right="40"
                            :return-value.sync="time"
                            transition="scale-transition"
                            offset-y
                            max-width="290px"
                            min-width="290px"
                        >   
                        <template v-slot:activator="{ on }">
                            <v-text-field
                                :rules="[requiredRule]"
                                v-model="time"
                                label="Pick duration"
                                prepend-icon="mdi-timer"
                                readonly
                                v-on="on"
                            ></v-text-field>
                        </template>
                            <v-time-picker
                                v-if="menuTime"
                                v-model="duration"
                                :allowed-minutes="allowedMinutes"
                                :allowed-hours="allowedHours"
                                format="24h"
                                full-width
                                @click:minute="$refs.menu.save(duration)"
                                ></v-time-picker>
                        </v-menu>
                    </v-col>    
                    <v-col cols="12" sm="6" md="6">
                        <v-menu
                            v-model="menuDate"
                            :close-on-content-click="true"
                            :nudge-right="40"
                            transition="scale-transition"
                            offset-y
                            max-width="290px">
                            <template v-slot:activator="{ on }">
                                <v-text-field  
                                    :rules="[requiredRule]"
                                    prepend-icon="mdi-timetable"
                                    v-model="date"
                                    readonly
                                    v-on="on"
                                    label="Pick date"
                                    :value="date"
                                    hint="MM/DD/YYYY format"
                                    />
                            </template>
                            <v-date-picker  
                                :min="nowDate"
                                v-model="date"
                                @input="menuDate = false">           
                            </v-date-picker>          
                        </v-menu>
                    </v-col>
                    <v-col cols="12" sm="6" md="6">
                        <v-select
                            :rules="[requiredRule]"
                            v-model="type"
                            :items="items"
                            label="Choose type">
                        </v-select>
                    </v-col>
                    <v-col  cols="12" sm="6" md="6">
                        <v-text-field 
                            :rules="[requiredRule]"
                            v-model="email"
                            label="Patient email">
                        </v-text-field>
                    </v-col>
                    </v-row>
                    <v-col md="3" offset-md="9">
                        <v-spacer></v-spacer>
                        <v-btn
                            color="orange lighten-1" dark @click="zakazi">Schedule</v-btn>
                    </v-col>
                </v-form>
                </v-container>
            </v-text>
        </v-card>
    </div>
</template>

<script>
import {mapActions} from 'vuex'
export default {
    data(){
        return {
            items: ["Operation", "Appointment"],
            type: "",
            email: "",
            menuTime: false,
            menuDate: false,
            date: "",
            duration: "00:00",
            number: ""
        }
    },
    computed: {
        requiredRule(){
            return (value) => !!value || "Required.";
        },
        nowDate(){
            return new Date().toISOString().slice(0,10);
        }
    },
    methods: {
        ...mapActions('doctor',['scheduleAppointment']),

        zakazi(){
            if(this.$refs.form.validate()){
                var obj = {
                    time: new Date(this.date +  ' ' + this.duration),
                    examinationType: this.type,
                    patient: this.email
                }
                this.scheduleAppointment(obj);
            }
        },

        setPatientEmail(email){
            this.email = email;
        }
    },
}
</script>

<style></style>