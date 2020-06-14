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
                                readonly>
                            </v-text-field>
                            <v-text-field
                                v-model="getClinic.address"
                                label="Address"
                                prepend-icon="mdi-home"
                                readonly>
                            </v-text-field>
                            <v-textarea
                                v-model="getClinic.description"
                                height="130"
                                no-resize
                                label="Description"
                                readonly
                                filled>
                            </v-textarea>
                            <v-card-actions>                           
                                <v-btn color="blue" @click="redirectToAppointments">Predefinisani pregledi</v-btn>
                                <v-btn color="blue" @click="redirectToDoctors">Doktori</v-btn>
                            </v-card-actions>
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
            clinicId: -1,
            headers:[
                {text:"Examination type", value: "examinationType.name"},
                {text:"Price", value: "price"}
            ],
            apiKey: 'dbf9f437-0f1f-4042-ac07-0f253547667f',
            coords: [20.457273,44.787197]
        }
    },
    created(){
        this.clinicId = this.$route.params.id
        this.fetchClinicById(this.clinicId)
        .then(() => {
            this.getCoordinates();
        });
        this.fetchPriceListByClinicId(this.clinicId);
        this.fetchDoctorsFromClinic(this.clinicId);
    },
    computed: {
        ...mapGetters('clinicProfile',['getClinic','getPriceListItems']),
        
    },
    methods: {
        ...mapActions('clinicProfile',['fetchClinicById','fetchPriceListByClinicId']),
        ...mapActions('doctor',['fetchDoctorsFromClinic']),
        ...mapGetters('doctor',['getDoctorList']),
        
        redirectToAppointments() {
            this.$router.push({
                name: "PredefinedAppointments",
                params: {
                    clinicId: this.clinicId
                }
            });
        },

        redirectToDoctors() {
            for (let i = 0; i < this.getPriceListItems.length; i++) {
                const priceListItem = this.getPriceListItems[i];
                for (let j = 0; j < this.getDoctorList().length; j++) {
                    const doctor = this.getDoctorList()[j];
                    if (doctor.examinationType.name == priceListItem.examinationType.name) {
                        doctor.price = priceListItem.price;
                    } else {
                        continue;
                    }
                    
                }
            }

            var doctors = this.getDoctorList();
            sessionStorage.setItem("filterDetails",
            JSON.stringify({
                filterDate: "",
                filterType: "",
                alreadyFiltered: false
            }));
            sessionStorage.setItem("doctors", JSON.stringify(doctors));
            this.$router.push({ name: 'DoctorsForPatient'});
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
            
            axios
            .get(`https://geocode-maps.yandex.ru/1.x/?format=json&apikey=${this.apiKey}&geocode=${new_address}`)
            .then(response =>{
                var possition = response.data.response.GeoObjectCollection.featureMember[0].GeoObject.Point.pos;
                var array = possition.split(" ");
                self.coords = array;
            });
        }

    },
}
</script>

<style scoped>
</style>