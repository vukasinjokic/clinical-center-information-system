<template>
<div  style="margin: 110px; 10px; 0px; 0px;">

    <v-card max-width="450">
        <v-card-title>
            <v-toolbar color="orange lighten-1" dark> Create request  </v-toolbar>
        </v-card-title>
        <v-card-text>
            <v-container>
                <v-form ref="form">
                <v-row>
                    
                    <v-col cols="12" sm="6" md="6">
                        <v-menu
                            v-model="fromDateMenu"
                            :close-on-content-click="true"
                            :nudge-right="40"
                            transition="scale-transition"
                            offset-y
                            max-width="290px">
                            <template v-slot:activator="{ on }">
                                <v-text-field  v-model="dateFrom"
                                    prepend-icon="mdi-timetable"
                                    v-on="on"
                                    :rules="[requiredRule]"
                                    readonly
                                    label="From date"
                                    :value="dateFrom"
                                    hint="Request date"
                                    />
                            </template>
                            <v-date-picker  v-model="dateFrom"
                                @input="fromDateMenu = false"
                                :min="nowDate">           
                            </v-date-picker>          
                        </v-menu>
                    </v-col>
                    <v-col cols="12" sm="6" md="6">
                        <v-menu
                            v-model="toDateMenu"
                            :close-on-content-click="true"
                            :nudge-right="40"
                            transition="scale-transition"
                            offset-y
                            max-width="290px">
                            <template v-slot:activator="{ on }">
                                <v-text-field  v-model="dateTo"
                                    prepend-icon="mdi-timetable"
                                    v-on="on"
                                    :rules="[requiredRule]"
                                    readonly
                                    label="To date"
                                    :value="dateTo"
                                    hint="Request date"
                                    />
                            </template>
                            <v-date-picker  v-model="dateTo"
                                @input="toDateMenu = false"
                                :min="nowDate">           
                            </v-date-picker>          
                        </v-menu>
                    </v-col>
                    
                    <v-col cols="12" sm="6" md="7">
                        <v-select
                            :rules="[requiredRule]"
                            v-model="type"
                            :items="items"
                            label="Select type">
                        </v-select>
                    </v-col>
                    
                    <v-col cols="12" sm="6" md="5">
                        <v-btn 
                            @click="send" 
                            style="margin: 15px; 0px; 0px; 30px;" 
                            color="orange lighten-1" dark>Submit</v-btn>
                    </v-col>
                </v-row>
                </v-form>
            </v-container>
        </v-card-text>
    </v-card>

</div>
</template>

<script>
import {mapActions} from 'vuex';
export default {
    
    data(){
        return{
            fromDateMenu: false,
            dateFrom: "",
            toDateMenu: false,
            dateTo: "",
            items: ["Vacation","Leave"],
            type: "",
            
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
        ...mapActions('doctor',['sendRequest']),

        send(){
            if(this.$refs.form.validate()){
                var req = {
                    fromDate: new Date(this.dateFrom),
                    toDate: new Date(this.dateTo),
                    type: this.type
                };
                this.sendRequest(req);
            }   
        }
    },
}
</script>


<style>
</style>