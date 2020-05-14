<template>
<div>
    <v-container style="width:60%">
        <v-card>
            <v-card-title>
                Examination types
            <v-spacer></v-spacer>
            
            <v-text-field 
                style="width:100px"
                v-model="search"
                append-icon="mdi-magnify"
                label="Search"
                single-line
                hide-details
            ></v-text-field>
        </v-card-title>
        <v-data-table style="margin: 10px; 0px; 0px; 0px;"
            calculate-widths
            class="blue-grey darken-4 white--text"
            dark
            :headers="headers"
            :items="getTypes"
            :items-per-page="5"
            item-key="item.name"
            :search="search"
        >
        <template v-slot:top>
        <v-toolbar flat class="blue-grey darken-4 white--text">
          <v-spacer></v-spacer>
          <v-dialog v-model="dialog" max-width="300px">
            <template v-slot:activator="{ on }">
              <v-btn color="orange lighten-1" dark class="mb-2" v-on="on">New type</v-btn>
            </template>
            <v-card>
              <v-card-title>
                <span class="headline">{{ formTitle }}</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-form ref="form">
                    <v-col cols="12" sm="6" md="12">
                      <v-text-field 
                            :rules="[requiredRule]"
                            label="Examination type name"
                            v-model="editedItem.name"></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="8">
                      <v-text-field  v-model="editedItem.duration" label="Duration (h)"
                        :rules="[numberRule,requiredRule]" >
                      </v-text-field>
                    </v-col>
                    </v-form>
                  </v-row>
                </v-container>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="close">Cancel</v-btn>
                <v-btn color="blue darken-1" text @click="save">Save</v-btn>
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
            <v-icon
            small
            @click="deleteItem(item)"
            >
            mdi-delete
            </v-icon>
        </template>
        </v-data-table>
        </v-card>
    </v-container>
</div>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'
export default {
    data(){
        return {
            headers:[
                {text: "Name", value:"name"},
                {text: "Duration(h)", value:"duration"},
                {text: "Actions", value:"actions"}
            ],
            search:"",
            dialog: false,
            editedIndex: -1,
            editedItem:{
                id: "",
                name:"",
                duration: null
            },
            defaultItem:{
                name:"",
                duration: null
            }
        }
    },
    created(){
        this.fetchExaminationTypes();
    },
    computed: {
        ...mapGetters('examination_type',['getTypes']),

        requiredRule(){
          return (value) => !!value || "Required.";
        },
        numberRule(){
            return v => /(^(\+)?\d+(\.\d+)?$)/.test(v) || "Input must be valid.";
        },
        formTitle () {
            return this.editedIndex === -1 ? 'New Item' : 'Edit Item'
        },  
    },
    methods: {
        ...mapActions('examination_type',['fetchExaminationTypes', 'deleteType','addType','updateType']),

        deleteItem(item){
            this.deleteType(item.name);
        },
        editItem(item){
            this.editedIndex = this.getTypes.indexOf(item);
            this.editedItem = Object.assign({}, item);
            this.dialog = true;
        },
        save(){
          if(this.$refs.form.validate()){
            if(this.editedIndex > -1){
                this.updateType(this.editedItem);
            }else{
                this.addType(this.editedItem);
            }
            this.close();
          }
        },
        close(){
            this.dialog = false;
            this.editedItem = Object.assign({}, this.defaultItem);
            this.editedIndex = -1;  
            this.$refs.form.reset();  //moze da pravi problem(ne stigne da posalje zahtev)
        }
    },
}
</script>


<style></style>