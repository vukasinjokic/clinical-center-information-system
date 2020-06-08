<template>
    <div>
        <v-data-table
        :headers="headers"
        :items="registrationRequests"
        item-key="name"
        class="elevation-1"
        >
            <template #item.name="{ item }">{{ item.firstName }} {{ item.lastName }}</template>
            <template #item.actions="{ item }" >
                    <v-btn class="ma-2" color="success" @click="acceptRequest(item)">
                            <v-icon  class="mr-1">
                            mdi-checkbox-marked-circle
                            </v-icon>
                        </v-btn>
                        <v-btn color="red" @click="openMessageDialogForRequest(item)" dark>
                            <v-icon class="mr-1">
                            mdi-cancel
                            </v-icon>
                        </v-btn>
            </template>

        </v-data-table>
        <v-dialog v-model="dialog" width="50%">
            <v-card>
                <v-card-title>Write message</v-card-title>
                <v-text>
                    <v-container>
                        <v-textarea
                        v-model="message"
                        filled
                        label="Why did you deny the request"
                        auto-grow
                        ></v-textarea>
                        
                        <v-btn color="orange lighten-1" dark @click="denyRequest">Finish</v-btn>
                        
                    </v-container>
                </v-text>
            </v-card>
        </v-dialog>
    </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
export default {
    data(){
        return {
            headers: [
                {
                    text: 'Name', value: 'name',fileterable: true
                },
                {
                    text: 'Email', value:'email', fileterable: true
                },
                {
                    text: 'Address', value:'address', sortable:false
                },
                {
                    text:'City', value:'city', sortable:true
                },
                {
                    text:'Country', value:'country', sortable:true
                },
                {
                    text:'Phone number', value:'phoneNumber', sortable:true
                },
                {
                    text:'Social security number', value:'socialSecurityNumber', sortable:true
                },
                {
                    text:'Actions', value:'actions', sortable:false
                }
                
                
            ],
            dialog : false,
            message : '',
            requestToDeny : null
            
        }   
    },
    methods : {
        ...mapActions('registrationRequests', ['fetchRegRequests', 'handleAcceptingRequest', 'handleDenyingRequest']),

        acceptRequest(request){
            this.handleAcceptingRequest(request.id);
        },

        openMessageDialogForRequest(request){
            this.message = '';
            this.dialog = true;
            this.requestToDeny = request;
            //this.handleDenyingRequest(request.id);
        },

        denyRequest(){
            this.dialog = false;
            this.handleDenyingRequest({ requestId : this.requestToDeny.id, message : this.message} );
        }


        
    },
    computed : {  
        ...mapGetters('registrationRequests', ['getregistrationRequests']),

        registrationRequests: function(){
            return this.getregistrationRequests();
        }
    },
    created(){
        this.fetchRegRequests();

        
    }



}
</script>

<style>
</style>