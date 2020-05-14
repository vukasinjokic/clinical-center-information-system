<template>
    <div class="home"> 
        <div>
        <v-container>
             <v-card>
                 <v-card-title>
                     Rooms
                 </v-card-title>
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
                    class="blue-grey darken-4 white--text"
                    dark>
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
                <template v-slot:top>
                <v-toolbar flat class="blue-grey darken-4 white--text">
                <v-spacer></v-spacer>
                <v-dialog v-model="editDialog" max-width="370px">
                    <template v-slot:activator="{ on }">
                    <v-btn color="orange lighten-1" dark class="mb-2" v-on="on">New Room</v-btn>
                    </template>
                    <v-card>
                    <v-card-title>
                        <span class="headline">{{ formTitle }}</span>
                    </v-card-title>
                    <v-card-text>
                        <v-container>
                        <v-form ref="form">
                        <v-row>
                            <v-col cols="12" sm="6" md="12">
                            <v-text-field 
                                    label="Room name"
                                    v-model="editedItem.name"
                                    :rules="[requiredRule]"></v-text-field>
                            </v-col>
                            <v-col cols="12" sm="6" md="4">
                            <v-text-field  v-model="editedItem.number" label="Room number"
                                :rules="[numberRule,requiredRule]" >
                            </v-text-field>
                            </v-col>
                            <v-col cols="12" sm= "6" md="8">
                                <v-select
                                    :rules="[requiredRule]"
                                    v-model="editedItem.type"
                                    :items="typesEx"
                                    label="Select type">
                                </v-select>
                            </v-col>
                        </v-row>
                        </v-form>
                        </v-container>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="blue darken-1" text @click="close">Cancel</v-btn>
                        <v-btn color="blue darken-1" text @click="save">Save</v-btn>
                    </v-card-actions>
                    </v-card>
                </v-dialog>
                </v-toolbar>
                 </template>
                <template v-slot:item.actions="{ item }">
                    <v-icon
                        small
                        class="mr-2"
                        @click="editItem(item)"
                        >
                        mdi-pencil
                        </v-icon>
                        <v-icon
                        small
                        @click="deleteItem(item)"
                        >
                        mdi-delete
                    </v-icon>
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
                {text: "Actions", value:"actions"}
            ],
            search: "",
            date:"",
            type:"",
            types:['Appointment','Operation'],
            expanded: [],
            duration: "00:00",
            menu2: false,
            dialog: false,
            editDialog: false,
            editedItem: {
                id: "",
                name: "",
                number: "",
                type: ""
            },
            defaultItem: {
                id: "",
                name: "",
                number: "",
                type: ""
            },
            editedIndex: -1,
            typesEx: ["APPOINTMENT","OPERATION"],
            doctorsSelect: []
        }
    },
    methods: {
        ...mapActions('room',['fetchRooms','filterRooms', 'fetchClinicDoctors',
         'alertDoctors','deleteRoom','addRoom', 'updateRoom']),

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
            console.log(this.clinicDoctorsDict[this.doctorsSelect[0]].email);
            this.alertDoctors(this.doctorsSelect);
        },      
        allowedMinutes: m => m % 15 === 0,
        allowedHours: h => h <= 10,

        editItem(item){
            this.editedIndex = this.getAllRooms.indexOf(item);
            this.editedItem = Object.assign({}, item);
            this.editDialog = true;

        },
        deleteItem(item){
            this.deleteRoom(item.id);
        },
        close(){
            this.editDialog = false;
            this.editedItem = Object.assign({}, this.defaultItem);
            this.editedIndex = -1;
            this.$refs.form.reset();

        },
        save(){
            if(this.$refs.form.validate()){
                if(this.editedIndex > -1)
                    this.updateRoom(this.editedItem);
                else
                    this.addRoom(this.editedItem);
                this.close();
            }
        }
    },
    computed:{ 
        ...mapGetters('room', ['getAllRooms','getAvailableTimes', 'getFilteredRooms', 'getClinicDoctorsDict']),
        

        filteredRooms: function(){
            return this.getFilteredRooms();
        },

        availableTimes: function(){
            return this.getAvailableTimes();
        },

        clinicDoctorsDict: function(){
            return this.getClinicDoctorsDict();
        },
        formTitle () {
            return this.editedIndex === -1 ? "Add new room" : "Edit room"
        },
        requiredRule(){
          return (value) => !!value || "Required.";
        },
        numberRule(){
            return v => /(^(\+)?\d+(\.\d+)?$)/.test(v) || "Input must be valid.";
        },
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