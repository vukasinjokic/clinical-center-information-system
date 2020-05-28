<template>
    <div>
        <v-card width="55%" v-if="appointment">
            <v-card-title>{{appointment.examinationType}}</v-card-title>
            <v-text>
                <v-container>
                    <v-form ref="form">
                    
                        <v-col md="3" offset-md="9">
                            <v-spacer></v-spacer>
                            <v-btn
                                color="orange lighten-1" dark >Submit</v-btn>
                        </v-col>
                    </v-form>
                </v-container>
            </v-text>
        </v-card>
    </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'

export default {
    components: {
        
    },
    props: ['appointmentId'],
    data(){
        return {
            
            
        }   
    },
    methods : {
        ...mapActions('startAppointment', ['fetchAppointment'])
        
    },
    computed : {
        ...mapGetters('startAppointment', ['getAppointment']),

        appointment : function() {
            return this.getAppointment();
        }
    },
    created(){
        this.fetchAppointment(this.appointmentId);    
    }



}
</script>

<style>
</style>