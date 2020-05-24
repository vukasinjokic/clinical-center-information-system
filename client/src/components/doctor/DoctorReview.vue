<template>
    <div>
    <v-container style="width: 90%">
        <v-card>
            <v-card-title> Doctors
                <v-spacer></v-spacer>
                <v-text-field 
                    style="width:100px"
                    v-model="search"
                    append-icon="mdi-magnify"
                    label="Search"
                    single-line
                    hide-details
                ></v-text-field>
            </v-card-title>
            <v-data-table style="margin: 10px; 0px; 0px; 0px;"
                calculate-widths
                class="blue-grey darken-4 white--text"
                dark
                :headers="headers"
                :items="getDoctorList"
                :items-per-page="5"
                item-key="item.name"
                :search="search"
            >
            <template v-slot:top>
                <v-toolbar flat class="blue-grey darken-4 white--text">
                <v-spacer></v-spacer>
                    <v-dialog v-model="dialog" max-width="650px">
                        <template v-slot:activator="{ on }">
                            <v-btn color="orange lighten-1" dark class="mb-2" v-on="on">New doctor</v-btn>
                        </template>
                        <v-card>
                        <v-card-title>
                            <span class="headline">{{ formTitle }}</span>
                        </v-card-title>
                        <v-card-text>
                            <v-form ref="form">
                            <v-container>
                                    <v-row>
                                        <v-col cols="12" sm="6" md="6">
                                            <v-text-field
                                                :rules="[requredRule]"
                                                v-model="editedItem.firstName"
                                                label="First name">
                                            </v-text-field>
                                        </v-col>
                                        <v-col cols="12" sm="6" md="6">
                                            <v-text-field
                                                :rules="[requredRule]"
                                                v-model="editedItem.lastName"
                                                label="Last name">
                                            </v-text-field>
                                        </v-col>
                                        <v-col cols="12" sm="6" md="6">
                                            <v-text-field
                                                :rules="[requredRule]"
                                                v-model="editedItem.email"
                                                label="Email">
                                            </v-text-field>
                                        </v-col>
                                        <v-col cols="12" sm="6" md="6">
                                            <v-text-field
                                                :rules="[requredRule]"
                                                v-model="editedItem.password"
                                                label="Password"
                                                type="password">
                                            </v-text-field>
                                        </v-col>
                                        <v-col cols="12" sm="6" md="6">
                                            <v-text-field
                                                :rules="[requredRule]"
                                                v-model="editedItem.city"
                                                label="City">
                                            </v-text-field>
                                        </v-col>
                                        <v-col cols="12" sm="6" md="6">
                                            <v-text-field
                                                :rules="[requredRule]"
                                                v-model="editedItem.address"
                                                label="Address">
                                            </v-text-field>
                                        </v-col>
                                        <v-col cols="12" sm="6" md="6">
                                            <v-text-field
                                                :rules="[requredRule]"
                                                v-model="editedItem.country"
                                                label="Country">
                                            </v-text-field>
                                        </v-col>
                                        <v-col cols="12" sm="6" md="6">
                                            <v-text-field
                                                :rules="[requredRule, numberRule]"
                                                v-model="editedItem.socialSecurityNumber"
                                                label="SocialSecurityNumber">
                                            </v-text-field>
                                        </v-col>
                                        <v-col cols="12" sm="6" md="6">
                                            <v-text-field
                                                :rules="[requredRule,numberRule]"
                                                v-model="editedItem.phoneNumber"
                                                label="Phone number">
                                            </v-text-field>
                                        </v-col>
                                        <v-col cols="12" sm="6" md="6">
                                            <v-text-field
                                                :rules="[requredRule]"
                                                v-model="editedItem.businessHours.started"
                                                label="Start business hours"
                                                value="08:00"
                                                type="time"
                                                suffix="PST"
                                            ></v-text-field>
                                        </v-col>
                                        <v-col cols="12" sm="6" md="6">
                                            <v-text-field
                                                :rules="[requredRule]"
                                                v-model="editedItem.businessHours.ended"
                                                label="End business hours"
                                                type="time"
                                                suffix="PST">
                                            </v-text-field>
                                        </v-col>
                                        <v-col cols="12" sm="6" md="6">
                                            <v-select
                                                :rules="[requredRule]"
                                                v-model="selected"
                                                :items="getExaminationTypeNames"
                                                label="Examination type"
                                                >
                                            </v-select>
                                        </v-col>
                                    </v-row>
                                
                            </v-container>
                            </v-form>
                        </v-card-text>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="blue darken-1" text @click="close">Cancel</v-btn>
                            <v-btn color="blue darken-1"  text @click="save">Save</v-btn>
                        </v-card-actions>
                        </v-card>
                    </v-dialog>
                    </v-toolbar>
                </template>
                <template v-slot:item.actions="{ item }">
                    
                    <v-icon
                        small
                        @click="deleteItem(item)"
                    >
                    mdi-delete
                    </v-icon>
                </template>
            </v-data-table>
        </v-card>
    </v-container>
    </div>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'
export default {
    data(){
        return{
            headers: [
                {text: "Name", value: "firstName"},
                {text: "Last name", value: "lastName"},
                {text: "Email", value: "email"},
                {text: "City", value: "city"},
                {text: "Address", value: "address"},
                {text: "SSN", value: "socialSecurityNumber"},
                {text: "Phone number", value: "phoneNumber"},
                {text: "Ex type", value:"examinationType.name"},
                {text: "Actions", value: "actions"}
            ],  
            search: "",
            editedIndex: -1,    
            dialog: false,
            editedItem: {
                firstName: "",
                lastName: "",
                email: "",
                city: "",
                address: "",
                country: "",
                socialSecurityNumber: "",
                phoneNumber: "",
                examinationType: {
                    id:"",
                    name:""
                },
                businessHours:{
                    started:"",
                    ended: ""
                }
            },
            doctor: {
                firstName: "",
                lastName: "",
                email: "",
                city: "",
                address: "",
                country: "",
                socialSecurityNumber: "",
                phoneNumber: "",
                examinationType: {
                    id:"",
                    name:""
                },
                businessHours:{
                    started:"",
                    ended: ""
                }
            },
            selected: {text: "", value: ""}
        }  
    },
    created(){
        this.fetchDoctors();
        this.fetchTypes();
    },
    methods: {
        ...mapActions('doctor',['fetchDoctors','saveDoctor','deleteDoctor']),
        ...mapActions('appointments',['fetchTypes']),

        save(){
            if(this.$refs.form.validate()){         
                this.editedItem.examinationType.id = this.selected;
                this.doctor = Object.assign({}, this.editedItem);
                this.doctor.businessHours = Object.assign({},this.editedItem.businessHours);
                this.saveDoctor(this.doctor);
                // this.dialog = false;
                this.close();
            }
        },
        deleteItem(item){
            this.deleteDoctor(item.id);
        },
        close(){
            this.dialog = false;
            this.$refs.form.reset();
        }
    },
    computed:{
        ...mapGetters('doctor', ['getDoctorList']),
        ...mapGetters('appointments', ['getExaminationTypeNames']),

        requredRule(){
            return (value) => !!value || "Required field."
        },
        formTitle(){
            return this.editedIndex === -1 ? 'New item' : 'Edit item.'
        },
        numberRule(){
            return v => /(^(\+)?\d+(\.\d+)?$)/.test(v) || "Input must be valid.";
        },
    }

}
</script>

<style>
</style>