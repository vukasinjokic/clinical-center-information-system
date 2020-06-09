<template>
    <div>
        <v-container>
            <v-row>
                <v-col cols="12" sm="6" md="5">
                    <v-card>
                        <v-card-title>
                            Osnovni podaci o klinici
                        </v-card-title>
                        <v-card-text >
                            <v-text-field
                                label="Clinic name"
                                v-model="getClinic.name"
                            >
                            </v-text-field>
                            <v-text-field
                                v-model="getClinic.address"
                                label="Address"
                                prepend-icon="mdi-home"
                            ></v-text-field>
                            <v-textarea
                                v-model="getClinic.description"
                                height="130"
                                no-resize
                                label="Description"
                                filled>
                            </v-textarea>
                            <v-btn color="success" @click="saveUpdate"> Update </v-btn>
                        </v-card-text>
                    </v-card>
                </v-col>
                <v-col cols="12" sm="6" md="6">
                    <v-card>
                       <v-card-title>
                           Price list
                       </v-card-title>
                        <v-data-table
                            class="blue-grey darken-4 white--text"
                            dark
                            :headers="headers"
                            :items="getPriceListItems"
                            :items-per-page="5"
                            item-key="item.price">
                        <template v-slot:top>
                            <v-toolbar flat class="blue-grey darken-4 white--text">
                                <v-spacer/>
                            <v-dialog v-model="dialog" max-width="300px">
                                <template v-slot:activator="{ on }">
                                <v-btn color="orange lighten-1" @click="variable=true" dark class="mb-2" v-on="on">New item</v-btn>
                                </template>
                                <v-card>
                                    <v-card-title>
                                        <span class="headline">{{formTitle}}</span>
                                    </v-card-title>
                                    <v-card-text>
                                        <v-container>
                                            <v-form ref="form">
                                            <v-col cols="12" sm="6" md="12">
                                                <v-select
                                                    :rules="[requiredRule]"
                                                    v-model="editPriceList.examinationType.name"
                                                    :items="getExaminationTypeOnlyNames"
                                                    label="Examination type"
                                                    :readonly="!variable"
                                                    >
                                                </v-select>
                                            </v-col>
                                            <v-col cols="12" sm="6" md="12">
                                                <v-text-field
                                                    :rules="[requiredRule]"
                                                    v-model="editPriceList.price"
                                                    label="Price">
                                                </v-text-field>
                                            </v-col>
                                            <v-col cols="12" sm="6" md="12">
                                            </v-col>
                                            </v-form>
                                        </v-container>
                                    </v-card-text>
                                    <v-card-actions>
                                        <v-spacer></v-spacer>
                                        <v-btn color="blue darken-1" text @click="close">Cancel</v-btn>
                                        <v-btn color="blue darken-1" text @click="saveListItem">Save</v-btn>
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
                            </template>
                        </v-data-table>
                    </v-card>
                </v-col>
            </v-row>
        </v-container>
        <Map v-bind:coords="coords"></Map>
    </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import axios from 'axios'
import Map from './Map'
export default {
    components: {Map},
    data(){
        return{
            headers:[
                {text:"Examination type", value: "examinationType.name"},
                {text:"Price", value: "price"},
                {text: "Actions", value: "actions"}
            ],
            defaultClinic:{
                id: "",
                name:"",
                address: "",
                description: ""
            },
            editPriceList:{
                id: "",
                examinationType:{
                    id:"",
                    name:""
                },
                price:"",
            },
            defaultPriceList:{
                id: "",
                examinationType:{
                    id:"",
                    name:""
                },
                price:"",
            },
            variable: true,
            dialog: false,
            editIndex: -1,
            apiKey: 'dbf9f437-0f1f-4042-ac07-0f253547667f',
            coords: [20.457273,44.787197]
        }
    },
    created(){
        this.fetchClinic();
        this.fetchTypes();
        this.fetchPriceList();
        this.getCoordinates();
        // console.log(this.coords);
    },
    computed: {
        ...mapGetters('clinicProfile',['getClinic','getPriceList','getPriceListItems']),
        ...mapGetters('appointments', ['getExaminationTypeOnlyNames']),

        formTitle(){
            return this.editIndex === -1 ? 'New item' : 'Edit item';
        },
        requiredRule(){
            return (value) => !!value || "Required field.";
        },
        
    },
    methods: {
        ...mapActions('clinicProfile',['fetchClinic','updateClinic','fetchPriceList','addPriceList','updatePriceList']),
        ...mapActions('appointments',['fetchTypes']),

        saveUpdate(){
            //this.defaultClinic = Object.assign({}, this.getClinic);
            this.defaultClinic.id = this.getClinic.id;
            this.defaultClinic.name = this.getClinic.name;
            this.defaultClinic.description = this.getClinic.description;
            this.defaultClinic.address = this.getClinic.address;
            this.updateClinic(this.defaultClinic);
            this.getCoordinates();
        },
        editItem(item){
            this.editIndex = this.getPriceListItems.indexOf(item);
            this.editPriceList = JSON.parse(JSON.stringify(item)); //deep clone
            this.variable = false;
            this.dialog = true;
            
        },
        saveListItem(){
            if(this.$refs.form.validate()){
                if(this.editIndex > -1){
                    this.updatePriceList(this.editPriceList);
                }else{
                    var newObj = JSON.parse(JSON.stringify(this.editPriceList));
                    this.addPriceList(newObj);
                }
                this.close();
            }
        },
        close(){    
            this.editIndex = -1;
            this.dialog = false;
            this.editPriceList = Object.assign({},this.defaultPriceList);
            this.$refs.form.reset();
        },
        
        getCoordinates(){
            var self = this;
            var arr = this.getClinic.address.split(" ");
            var new_address = "";
            for(let i = 0; i<arr.length; i++){
                if(i != arr.length-1)
                    new_address = new_address + arr[i] + "+";
                else
                    new_address = new_address + arr[i];
            }
            
            console.log(new_address);
            axios
            .get(`https://geocode-maps.yandex.ru/1.x/?format=json&apikey=${this.apiKey}&geocode=${new_address}`)
            .then(response =>{
                var possition = response.data.response.GeoObjectCollection.featureMember[0].GeoObject.Point.pos;
                var array = possition.split(" ");
                self.coords = array;
            });
        }

    },
    mounted(){
    }
}
</script>

<style>
</style>