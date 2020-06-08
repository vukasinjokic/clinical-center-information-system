<template>
    <div>
    <v-card>
        <v-card-title> Financial Gain</v-card-title>
        <v-row>
        <v-col class="ml-2" sm="6" md="4">
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
                    >           
                </v-date-picker>          
            </v-menu>
        </v-col>
        <v-col sm="6" md="4">
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
         <v-col sm="3" md="3">
            <v-btn @click="getProfits" style="margin-top:10px" color="primary">Get gain </v-btn>
        </v-col>
        </v-row>
        <v-col sm="6" md="12">
            <v-card style="margin-left:100px"  max-width = "350px">
                <v-card-title class="subtitle-1 font-italic font-weight-bold"> 
                    <v-container>
                        <v-row>
                        <v-col cols="12" md="5" sm="5">
                            <div class="text-start v-card--material__heading mb-n6 v-sheet theme--dark elevation-6 
                            success pa-7" style="max-height: 90px; width: auto;">
                            <i aria-hidden="true" 
                            class="v-icon notranslate mdi mdi-cash theme--dark" style="font-size: 35px;"></i>
                            </div>
                        </v-col>
                        <v-col cols="12" md="7" sm="7">
                            <div class="ml-1"><div class="ml-auto text-right">
                                <div class="body-1 grey--text font-weight-regular">Clinic profit
                                </div>
                                <h1 class="headline font-weight-light text--primary">RDS {{getProfit}} 
                                </h1></div>
                            </div>
                        </v-col>
                        </v-row>
                    </v-container>
                </v-card-title>
            </v-card>
        </v-col>
    </v-card>
    </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
export default {
    name: "financial-gain",
    data(){
        return{           
            dateTo: new Date().toISOString().slice(0,10),
            dateFrom: new Date().toISOString().slice(0,10),
        }
    },
    computed: {
        ...mapGetters('businessReport', ['getProfit']),
        requiredRule(){
            return (value) => !!value || "Required.";
        },
        nowDate(){
            return new Date(this.dateFrom).toISOString().slice(0,10);
        }
    },
    methods: {
        ...mapActions('businessReport',['getClinicProfit']),

        getProfits(){
            this.getClinicProfit({"dateFrom":this.dateFrom, "dateTo":this.dateTo});
        }
    }
}
</script>