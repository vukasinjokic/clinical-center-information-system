<template>
<div>
    <v-row justify="center">
      <v-dialog v-model="dialog" persistent max-width="600px">
        <template v-slot:activator="{ on }">
           <v-btn  v-on="on" @click="edit" dark color="orange lighten-1">Edit Profile</v-btn>
        </template>
        <v-card>
          <v-toolbar height="45px" color="orange lighten-1" class="white--text">
            <span class="headline">Edit profile</span>
          </v-toolbar>
          <v-card-text>
            <v-form ref="form">
            <v-container>
              <v-row>
                <v-col cols="12" sm="6" md="6">
                     <v-text-field
                        v-model="editItem.firstName"
                        label="First name">
                     </v-text-field>
                </v-col>
                <v-col cols="12" sm="6" md="6">
                     <v-text-field
                        v-model="editItem.lastName"
                        label="Last name"
                    ></v-text-field>
                </v-col>
                <v-col cols="12">
                    <v-text-field
                        v-model="editItem.address"
                        label="Address"
                    ></v-text-field>
                </v-col>
                <v-col cols="12" sm = "6">
                    <v-text-field 
                        v-model="editItem.city"
                        label="City"
                      ></v-text-field>
                </v-col>
                <v-col cols="12" sm="6">
                    <v-text-field 
                        v-model="editItem.country"
                        label="Country"  
                      required></v-text-field>
                </v-col>
                <v-col cols="12" sm="6">
                    <v-text-field
                        v-model="editItem.phoneNumber"
                        label="Telephone number">
                    </v-text-field>
                </v-col>
                <v-col cols="12" sm="6">
                    <v-text-field
                        readonly
                        :rules="[requiredRule]"
                        v-model="editItem.socialSecurityNumber"
                        label="Social security number">
                    </v-text-field>
                </v-col>
              </v-row>
            </v-container>
            </v-form>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="red darken-1"  text v-on:click="closeDialog">Close</v-btn>
            <v-btn color="green darken-1" text @click="saveProfile">Save</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
</div>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'

export default {
    data(){
        return{
            editItem:{
                id: "",
                firstName: "",
                lastName: "",
                address: "",
                city: "",
                country: "",
                phoneNumber: "",
                socialSecurityNumber: ""
            },
            defaultItem:{
                id: "",
                firstName: "",
                lastName: "",
                address: "",
                city: "",
                country: "",
                phoneNumber: "",
                socialSecurityNumber: ""
            },
            
            dialog: false
        }
    },
    computed: {
        ...mapGetters('userProfile', ['getUserProf']),

        requiredRule(){
            return (value) => !!value || "Required.";
        }
    },
    
    methods: {
        ...mapActions('userProfile',['fetchUserProf', 'updateProfile']),

        edit(){
            this.editItem.firstname = this.getUserProf.firstName;
            this.editItem = Object.assign({},this.getUserProf);        
        },
        saveProfile(){
            if(this.$refs.form.validate()){
                this.updateProfile(this.editItem);
                this.closeDialog();
            }
        },
        closeDialog(){
            this.dialog = false;
            this.editItem = Object.assign({}, this.defaultItem);
            this.$refs.form.reset();
        }
    }
}
</script>

<style></style>