<template>
    <v-card max-width = "300px">
        <v-card-title class="subtitle-1 font-italic font-weight-bold"> 
            <v-container>
                <v-row>
                <v-col cols="12" md="5" sm="5">
                    <div class="text-start v-card--material__heading mb-n6 v-sheet theme--dark elevation-6 
                    success pa-7" style="max-height: 90px; width: auto;">
                    <i aria-hidden="true" 
                    class="v-icon notranslate mdi mdi-star theme--dark" style="font-size: 32px;"></i>
                    </div>
                </v-col>
                <v-col cols="12" md="7" sm="7">
                    <div class="ml-3"><div class="ml-auto text-right">
                        <div class="body-1 grey--text font-weight-regular">Clinic rating
                        </div>
                        <h1 class="display-3 font-weight-light text--primary"> {{getRating}} 
                        </h1></div>
                    </div>
                </v-col>
                </v-row>
            </v-container>
        </v-card-title>
        <span class=" px-4 caption grey--text font-weight-regular">Rated by patients</span>
        </v-card>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'
export default {
    data(){
        return{
            rating:5.2
        }
    },
    computed: {
        ...mapGetters('businessReport', ['getRating'])
    },
    methods:{
        ...mapActions('businessReport', ['fetchClinicRating'])
    },
    created(){
        this.fetchClinicRating();
    }      
}
</script>