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
                        <v-btn color="red" @click="denyRequest(item)" dark>
                            <v-icon class="mr-1">
                            mdi-cancel
                            </v-icon>
                        </v-btn>
            </template>

        </v-data-table>
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
            roomsDialog : false,
            
        }   
    },
    methods : {
        ...mapActions('registrationRequests', ['fetchRegRequests', 'deleteRequest']),

        getTime(item){
            if(item.type == 'DOCTOR') return new Date(item.time).toLocaleString();
            return new Date(item.predefregistration.date).toLocaleString();
        },

        reserveRoom(request){
            this.roomsDialog = true;
            this.$refs.roomsComponent.setUpFields(request);
        },

        roomReserved(){
            this.roomsDialog = false;
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