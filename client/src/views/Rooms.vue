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
                    <v-btn @click="filterRooms">Search</v-btn>
                </v-card-title>          
                </v-card>
                <v-data-table
                    :ref="table"
                    :headers="headers"
                    :items="getRoomsTable"
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
                        <tr style="background-color:gray" v-if="availableAppointments">
                            <td >First available appointment : {{dateToString(availableAppointments[item.id])}}</td>
                            <td style="text-align:center; margin-left:140px;"><v-btn @click="filterRooms" color="blue">Reserve room</v-btn> </td>
                            
                        </tr>
                     </td>
                </template>
                </v-data-table>
            
        </v-container>
        </div>
        <div>
            
        </div>
    </div> 
</template>
<script>

import {mapGetters, mapActions} from 'vuex'

export default {
    name: 'Rooms',
    created(){
        this.fetchRooms();    
        this.getRooms();    
    },

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
                // { 
                //     text: 'Actions', value: 'actions', sortable: false 
                // },
            ],
            search: "",
            date:"",
            expanded: [],
            rooms: [],
            availableAppointments : null,
            duration: "00:00",
            menu2: false,
        }
    },
    methods: {
        ...mapActions('room',['fetchRooms', 'getRooms']),

        getRooms(){
            this.rooms = this.getAllRooms();
        },

        dateToString(item){
            var d = new Date(item);
            return d.toString().substring(0,21);
        },
        filterRooms(){
          let result = this.getFiltered(this.search, this.date, this.duration);
          this.availableAppointments = result.availableAppointments;
          this.rooms = result.rooms;
          
        },
        
        allowedMinutes: m => m % 15 === 0,
        allowedHours: h => h <= 10
    },
    computed:{ 
        ...mapGetters('room', ['getAllRooms','getFiltered']),
        getRoomsTable: function(){
            return (this.getFiltered(this.search,this.date,this.duration)).rooms;
        },
        // getRooms: function(){
        //     this.rooms = this.getAllRooms();
        // },
 
    },


}
</script>


<style>
.upper{
    position: relative;
}


</style>