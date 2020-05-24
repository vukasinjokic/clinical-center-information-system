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
                            :items="getPriceList"
                            :items-per-page="5"
                            item-key="item.name">
                        </v-data-table>
                    </v-card>
                </v-col>
            </v-row>
        </v-container>
        
    </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
export default {
    data(){
        return{
            defaultClinic:{
                id: "",
                name:"",
                address: "",
                description: ""
            },
            clinic:{
                id: "",
                name:"",
                address: "",
                description: ""
            },
        }
    },
    created(){
        this.fetchClinic();

    },
    computed: {
        ...mapGetters('clinicProfile',['getClinic']),
    },
    methods: {
        ...mapActions('clinicProfile',['fetchClinic','updateClinic']),

        saveUpdate(){
            //this.defaultClinic = Object.assign({}, this.getClinic);
            this.defaultClinic.id = this.getClinic.id;
            this.defaultClinic.name = this.getClinic.name;
            this.defaultClinic.description = this.getClinic.description;
            this.defaultClinic.address = this.getClinic.address;
            this.updateClinic(this.defaultClinic);
        }

    },
    mounted(){
    }
}
</script>

<style>
</style>