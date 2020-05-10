<template>
    <div class="home"> 
        <h1>Room view</h1>
        <div>
        <v-container>
             <v-card>
                <v-card-title>
                    <v-text-field
                        v-model="search"
                        label="Search"
                        single-line
                        show-details
                        
                    ></v-text-field>
                    <v-spacer></v-spacer>
                    <v-menu
                        ref="menu"
                        v-model="menu2"
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
                            v-model="time"
                            label="Pick duration"
                            prepend-icon="mdi-access_time"
                            readonly
                            v-on="on"
                        ></v-text-field>
                        </template>
                        <v-time-picker
                        v-if="menu2"
                        v-model="duration"
                        :allowed-minutes="allowedMinutes"
                        :allowed-hours="allowedHours"
                        format="24h"
                        full-width
                        @click:minute="$refs.menu.save(duration)"
                        ></v-time-picker>
                    </v-menu>
                    <v-spacer></v-spacer>
                    <v-menu
                        v-model="fromDateMenu"
                        :close-on-content-click="true"
                        :nudge-right="40"
                        transition="scale-transition"
                        offset-y
                        max-width="290px">
                        <template v-slot:activator="{ on }">
                            <v-text-field  v-model="date"
                                v-on="on"
                                label="Pick date"
                                :value="date"
                                hint="MM/DD/YYYY format"
                                />
                        </template>
                        <v-date-picker  v-model="date"
                                @input="fromDateMenu = false">           
                        </v-date-picker>          
                    </v-menu>
                    <v-spacer></v-spacer>
                    <v-combobox
                        v-model="type"
                        :items="types"
                        label="Select type"

                    >

                    </v-combobox>
                    <v-spacer></v-spacer>
                    <v-btn @click="filter">Search</v-btn>
                </v-card-title>          
                </v-card>
                <v-data-table 
                    :ref="table"
                    :headers="headers"
                    :items="filteredRooms"
                    :items-per-page="5"
                    :expanded.sync="expanded"
                    item-key="name"
                    show-expand
                    dark grey>
                <template v-slot:expanded-item="{ headers, item }">
                    <td :colspan="headers.length">
                        <tr v-for="it in item.calendar.eventStartDates.length" v-bind:key=it.name>
                            <td>Start date: {{ dateToString(item.calendar.eventStartDates[it-1])}}</td>
                            <td>End date: {{ dateToString(item.calendar.eventEndDates[it-1])}}</td>
                        </tr>
                        <tr style="background-color:gray" v-if="availableTimes">
                            <td >First available appointment : {{dateToString(availableTimes[item.id])}}</td>
                            <td style="text-align:center; margin-left:140px;"><v-btn @click="reserveRoom(item)" color="blue">Reserve room</v-btn> </td>
                            <v-row justify="center">
                                <v-dialog v-model="dialog" persistent max-width="500">
                                <v-card>
                                    <v-card-title class="headline">Choose doctors to attend operation</v-card-title>
                                    <v-container fluid>
                                    <v-row align="center">
                                        <v-col sm="100">
                                            <v-select
                                            v-model="doctorsSelect"
                                            :items="Object.keys(clinicDoctorsDict)"
                                            :menu-props="{ maxHeight: '400' }"
                                            label="Select"
                                            style="width:500px"
                                            multiple
                                            ></v-select>
                                        </v-col>
                                    </v-row>
                                    </v-container>
                                    <v-card-actions>
                                    <v-spacer></v-spacer>
                                    <v-btn color="blue darken-1" text @click="dialog = false">Cancel</v-btn>
                                    <v-btn color="blue darken-1" text @click="sendNotification()">Send notification</v-btn>
                                    </v-card-actions>
                                </v-card>
                                </v-dialog>
                            </v-row>
                        </tr>
                     </td>
                </template>
                </v-data-table>
            
        </v-container>
        </div>
        
    </div> 
</template>
<script>

import {mapGetters, mapActions} from 'vuex'
 
export default {
    name: 'Rooms',
    

    data(){
        return {
            headers: [
                {
                    text: 'Name', value: 'name',fileterable: true
                },
                {
                    text: 'Number', value: 'number', fileterable:true            
                },
                {
                    text: 'Clinic', value: 'clinic',fileterable: true
                },
                { 
                    text: 'Type', value: 'type', sortable: true 
                },
            ],
            search: "",
            date:"",
            type:"",
            types:['Appointment','Operation'],
            expanded: [],
            duration: "00:00",
            menu2: false,
            dialog: false,

            doctorsSelect: []
        }
    },
    methods: {
        ...mapActions('room',['fetchRooms','filterRooms', 'fetchClinicDoctors']),

        dateToString(item){
            var d = new Date(item);
            return d.toString().substring(0,21);
        },
        filter(){
          this.filterRooms({'search': this.search, 'date' : this.date, 'duration' : this.duration, 'type': this.type});         
        },

        reserveRoom(room){
            if(room.type.toUpperCase().match('OPERATION')){
                this.reserveOperationRoom(room);
            }
            else{
                this.reserveAppointmentRoom(room);
            }
        },
        reserveOperationRoom(room){
            this.doctorsSelect = [];
            this.dialog = true;
            console.log(room);
            
        },

        sendNotification(){

        },

        
                
        allowedMinutes: m => m % 15 === 0,
        allowedHours: h => h <= 10
    },
    computed:{ 
        ...mapGetters('room', ['getAvailableTimes', 'getFilteredRooms', 'getClinicDoctorsDict']),
        

        filteredRooms: function(){
            return this.getFilteredRooms();
        },

        availableTimes: function(){
            return this.getAvailableTimes();
        },

        clinicDoctorsDict: function(){
            return this.getClinicDoctorsDict();
        }
    },
    created(){
        this.fetchRooms();
        this.fetchClinicDoctors();
    },
}
</script>


<style>
.upper{
    position: relative;
}


</style>