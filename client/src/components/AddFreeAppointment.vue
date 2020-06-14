<template>
<div>
    <v-row justify="center">
      <v-dialog v-model="dialog" persistent max-width="600px">
        <template v-slot:activator="{ on }">
           <v-btn  v-on="on" color="orange lighten-1">Add free appointment</v-btn>
        </template>
        <v-card>
          <v-toolbar height="45px" color="primary lighten-1" class="white--text">
            <span class="headline">Add free appointment</span>
          </v-toolbar>
          <v-card-text>
            <v-form ref="form">
            <v-container>
              <v-row>
                <v-col cols="12" sm="6" md="6">
                     <v-menu
                        v-model="fromDateMenu"
                        :close-on-content-click="true"
                        :nudge-right="40"
                        transition="scale-transition"
                        offset-y
                        max-width="290px">
                        <template v-slot:activator="{ on }">
                          <v-text-field  v-model="date"
                            outlined
                            dense
                            append-icon="mdi-timetable"
                            v-on="on"
                            :rules="[requiredRule]"
                            readonly
                            label="Pick date"
                            :value="date"
                            hint="Appointment date"
                            />
                        </template>
                        <v-date-picker  v-model="date"
                                @input="fromDateMenu = false"
                                 :min="dateNow">           
                        </v-date-picker>          
                    </v-menu>
                </v-col>
                <v-col cols="12" sm="6" md="6">
                     <v-text-field
                        outlined
                        dense
                        :rules="[requiredRule]"
                        v-model="time"
                        label="Pick time"
                        value="XX:XX"
                        type="time"
                        suffix="PST"
                        required
                    ></v-text-field>
                </v-col>
                <v-col cols="12" sm="6">
                 <v-select
                    outlined
                    dense
                    :items="getExaminationTypes"
                    :rules="[requiredRule]"
                    label="Examination type"
                    v-model="type"
                    item-text="name"
                    return-object
                    @change="doSome"
                    required
                  ></v-select>
                </v-col>
                <v-col cols="12" sm = "6">
                  <v-text-field 
                      outlined
                      dense
                      readonly
                      label="Duration" type="float" 
                      v-model="dura" 
                      ></v-text-field>
                </v-col>
                
                <v-col cols="12" sm="6">
                  <v-select
                    outlined
                    dense
                    v-model="room"
                    :rules="[requiredRule]"
                    :items="allRooms"
                    item-text="number"
                    return-object
                    label="Examination room"
                    required
                  ></v-select>
                </v-col>
                <v-col cols="12" sm="6">
                  <v-select
                    outlined
                    dense
                    :rules="[requiredRule]"
                    hint="Choose examination type"
                    :items="getDoctors"
                    label="Doctor(choose examination type)"
                    v-model="doctor"
                  ></v-select>
                </v-col>
                <v-col cols="12" sm="6">
                  <v-select
                    outlined
                    dense
                    :items="discounts"
                    label="Discount(%)"
                    v-model="discount">
                  </v-select>
                </v-col>
              </v-row>
            </v-container>
            </v-form>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text v-on:click="closeDialog">Close</v-btn>
            <v-btn color="blue darken-1" text @click="save">Save</v-btn>
                {{time}}
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
        return {
            dialog: false,
            date: "",
            time: "",
            type: {},
            dura: null,
            price: null,
            doctor: "",
            room: {},
            discount: null,
            discounts: [0,10,20,30,40,50,60,70,80,90,100]
        }
    },
    created() {
        this.fetchRooms();
        this.fetchTypes();
        
    },
    computed: {
        ...mapGetters('appointments',['allRooms','allRoomsNumber','getExaminationTypes',
        'getTypeDuration','getDoctors']),

        requiredRule(){
          return (value) => !!value || "Required.";
        },
        durationRule(){
            return v => /(^(\+)?\d+(\.\d+)?$)/.test(v) || "Input must be number.";
        },
        dateNow(){
          return new Date().toISOString().slice(0,10);
        }
    },
    methods:{
        ...mapActions('appointments',['fetchRooms','fetchTypes','fetchDoctors','saveAppointment']),

        closeDialog(){
            this.dialog = false;
            this.$refs.form.reset();
        },
        save(){ 
          if(this.$refs.form.validate()){
            var newAppointment = 
                    {
                      time: this.date + " " + this.time,
                      doctorEmail: this.doctor,
                      roomId: this.room.id,
                      examinationType: this.type.id,
                      clinic: "Ne znam kliniku dok se ne uradi login",
                      discount: this.discount
                    }

            this.saveAppointment(newAppointment); 
            this.closeDialog();
          }
        },
        doSome(){
            //var duration = parseFloat(this.getTypeDuration(this.type).duration);
            var duration = parseFloat(this.type.duration);
            this.dura = duration + "h";
            this.fetchDoctors(this.type.id);
        }
    }
}
</script>


<style></style>