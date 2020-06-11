<template>
    <div class="home"> 
        <div>
        <v-container>
             <v-card>
                 <v-card-title>
                     Rooms
                    <v-spacer></v-spacer>
                    <v-text-field
                        style="width:100px"
                        dense
                        outlined
                        v-model="search"
                        append-icon="mdi-magnify"
                        label="Search"
                        hide-details
                    ></v-text-field>
                </v-card-title>
                
                <v-data-table 
                    :search="search"
                    :ref="table"
                    :headers="headers"
                    :items="filteredRooms"
                    :items-per-page="5"
                    item-key="name"
                    class="blue-grey darken-4 white--text"
                    dark>
                
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
                </v-card>
                <v-dialog v-model="confirmDialog" max-width="300px">
                    <v-card>
                        <v-card-title>
                            <span> Do you want to proceed?</span>
                        </v-card-title>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="blue darken-1" text @click="saveDelete">Yes</v-btn>
                            <v-btn color="blue darken-1" text @click="cancelDelete">No</v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
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
            doctorsSelect: [],
            mainDoctor: {email: ''},
            selectedRoom : null,
            confirmDialog: false,
            deleteItemId: ""
        }
    },
    methods: {
        ...mapActions('room',['fetchRooms','filterRooms', 'fetchClinicDoctors',
         'handleReservation','deleteRoom','addRoom', 'updateRoom']),

        dateToString(item){
            var d = new Date(item);
            return d.toString().substring(0,21);
        },
        filter(){
          this.filterRooms({'search': this.search, 'date' : this.date, 'duration' : this.duration, 'type': this.type ? this.type : ''});         
        },

        reserveRoom(room){
            this.selectedRoom = room;
            if(room.type.toUpperCase().match('OPERATION')){
                this.showSelectDoctorsDialog();
            }
            else{
                this.sendNotification();
            }
        },
        showSelectDoctorsDialog(){
            this.doctorsSelect = [];
            this.dialog = true;
        },

        sendNotification(){
            let doctors = [];
            this.doctorsSelect.concat(this.mainDoctor.firstName + ' ' + this.mainDoctor.lastName).forEach(name => {
                doctors.push(this.clinicDoctorsDict[name]);
            });
            let payload = {doctors: doctors, requestId : this.request.id, room : this.selectedRoom, reservedTime : this.availableTimes[this.selectedRoom.id]}
            this.handleReservation(payload);
            this.dialog = false;
            this.$emit('reserved');
            this.resetTable();
        },     
        resetTable(){
            this.$data.expanded = [];
            this.type = null;
        },
        allowedMinutes: m => m % 15 === 0,
        allowedHours: h => h <= 10,

        editItem(item){
            this.editedIndex = this.getAllRooms.indexOf(item);
            this.editedItem = Object.assign({}, item);
            this.editDialog = true;

        },
        deleteItem(item){
            this.confirmDialog = true;
            this.deleteItemId = item.id;
            //this.deleteRoom(item.id);
        },
        saveDelete(){
            this.confirmDialog = false;
            this.deleteRoom(this.deleteItemId);
            this.confirmDialog = false;
        },
        cancelDelete(){
            this.confirmDialog = false;
        
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
        },
        setUpFields(request){
            this.request = request;
            this.date = request.time;
            this.mainDoctor = request.doctor;
            this.duration = this.floatToHours(request.doctor.examinationType.duration);
        },

        getDoctorsForSelect(){
            let clinicDoctorsDictCopy = {...this.clinicDoctorsDict};
            for(let key in clinicDoctorsDictCopy){
                if(clinicDoctorsDictCopy[key].email.match(this.mainDoctor.email)){
                    delete clinicDoctorsDictCopy[key];
                }
            }
            return Object.keys(clinicDoctorsDictCopy);
        },

        floatToHours(durationMilliseconds){
            var minutes = Math.floor((durationMilliseconds * 60) % 60),
                hours = Math.floor((durationMilliseconds) % 24);

            hours = (hours < 10) ? "0" + hours : hours;
            minutes = (minutes < 10) ? "0" + minutes : minutes;
            return (hours+ ':' + minutes);
        }
    },
    computed:{ 
        ...mapGetters('room', ['getAllRooms','getAvailableTimes', 'getFilteredRooms', 'getClinicDoctorsDict']),
        
        doctorsForSelect: function(){
            return this.getDoctorsForSelect();
        },

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