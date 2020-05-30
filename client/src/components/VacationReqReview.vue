<template>
    <div>
        <v-container style="width:70%">
            <v-card>
                <v-card-title>
                    Confirm vacation Requests
                </v-card-title>

                <v-data-table
                    style="margin: 10px; 0px; 0px; 0px;"
                    class="blue-grey darken-4 white--text"
                    dark
                    :headers="headers"
                    :items="getRequest"
                    >
                    <template v-slot:item.fromDate="{ item }">
                        {{ item.fromDate.substring(0,10) }}
                    </template>
                    <template v-slot:item.toDate="{ item }">
                        {{ item.toDate.substring(0,10) }}
                    </template>
                    <template v-slot:item.actions="{ item }">
                        <v-btn class="ma-2" color="success" @click="acceptReq(item)">
                            <v-icon  class="mr-1">
                            mdi-checkbox-marked-circle
                            </v-icon>
                        </v-btn>
                        <v-btn color="red" @click="deleteItem(item)">
                            <v-icon class="mr-1">
                            mdi-cancel
                            </v-icon>
                        </v-btn>
                    </template>
                    
                </v-data-table>
                <v-dialog v-model="dialog" persistent max-width="290px">
                        <v-card>
                            <v-card-title class="headline">Description</v-card-title>
                            <v-card-text>
                                <v-form ref="form">
                                <v-textarea
                                    :rules="[requiredRule]"
                                    v-model="description"
                                    label="Description"
                                    counter
                                    maxlength="300"
                                    full-width
                                    single-line
                                ></v-textarea>
                                </v-form>
                            </v-card-text>
                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn color="red darken-1" text @click="close">Cancel</v-btn>
                                <v-btn color="green darken-1" text @click="declineReq">Decline request</v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-dialog>
            </v-card>
        </v-container>
    </div>

</template>

<script>
import { mapActions, mapGetters } from 'vuex'
export default {
    data(){
        return {
            headers: [
                {text: "First name:", value: "medicalStaffName"},
                {text: "Last name:", value: "medicalStaffLastName"},
                {text: "From date", value: "fromDate"},
                {text: "To date", value: "toDate"},
                {text: "Type",value: "type"},
                {text: "Actions", value:"actions"}
            ],
            dialog: false,
            description: "",
            id: "",
        }
    },
    created(){
        this.fetchRequests();
    },
    computed: {
            ...mapGetters('clinicAdmin',['getRequest']),

            requiredRule(){
                return (value) => !!value || "Required."; 
            }
        },

    methods:{
        ...mapActions('clinicAdmin', ['fetchRequests', 'acceptVacationRequest','declineRequest']),

        acceptReq(item){
            this.acceptVacationRequest(item.id);
            console.log(item);
        },
        deleteItem(item){
            this.id = item.id;
            this.dialog = true;
        },

        close(){
            this.dialog = false;
            this.$refs.form.reset();
        },
        declineReq(){
            if(this.$refs.form.validate()){
                var declinedReq = {
                    id: this.id,
                    description: this.description
                }
                this.declineRequest(declinedReq);
                this.close();
            }
        }
    }
}
</script>