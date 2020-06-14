
<template v-slot:top>
<div>
    <v-toolbar flat class="indigo lighten-5">
      <v-spacer></v-spacer>
  <v-dialog v-model="dialog" persistent max-width="400px">
    <template v-slot:activator="{ on }">
        <v-btn v-on="on" dark color="orange lighten-1">Add Clinic</v-btn>
    </template>
    <v-card>
      <v-toolbar height="45px" color="orange lighten-1">
        <span class="headline" style="color:white">New Clinic</span>
      </v-toolbar>
      <v-card-text>
        <v-form ref="form">
        <v-container>

            <v-text-field 
            label="Name" type="float"
            v-model="name" 
            required></v-text-field>
    
            <v-text-field 
            label="Address" type="float"
            v-model="address" 
            required></v-text-field>
    

            <v-text-field 
            label="Description" type="float"
            v-model="description" 
            required></v-text-field>

          
        </v-container>
        </v-form>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="orange lighten-1" text v-on:click="cancel">Cancel</v-btn>
        <v-btn color="orange lighten-1" dark  @click="addClinic">Add</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
  </v-toolbar>
  </div>
</template>

<script>
import { mapActions } from 'vuex';
// import {mapGetters, mapActions} from 'vuex'

export default {
    data(){
        return {
            dialog: false,
            name: '',
            address:'',
            description:''
            
        }
    },
    created() {
        
    },
    computed: {

        
    },
    methods:{
        ...mapActions('clinics', ['saveClinic']),
        
        cancel(){
            this.dialog = false;
            this.$refs.form.reset();
        },

        addClinic(){
            if(this.$refs.form.validate()){
                var newClinic = {
                    name: this.name,
                    description: this.description,
                    address: this.address,
                }
                this.saveClinic(newClinic);
                this.dialog = false;
            }

        }

        
    }
}
</script>


<style></style>