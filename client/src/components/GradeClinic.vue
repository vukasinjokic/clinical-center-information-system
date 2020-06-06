<template>
    <v-dialog v-model="dialog" persistent max-width="450px">
        <template v-slot:activator="{ on }">
            <v-btn  v-on="on" color="blue">Oceni kliniku</v-btn>
        </template>
        <v-card>
            <v-toolbar height="45px" color="blue" class="white--text">
                <span class="headline">Ocenite kliniku</span>
            </v-toolbar>
            <v-card-text>
                <v-form ref="form">
                    <v-container>
                        <v-row cols="12" sm = "6">
                            <v-select
                            v-model="id"
                            label="Clinic"
                            :items="this.clinics"
                            :rules="[requiredRule]"
                            item-text="name"
                            item-value="id">
                            </v-select>

                        </v-row>
                        <v-row cols="12" sm="6">
                            <v-rating
                            v-model="newRating"
                            v-bind:length="10"
                            color="yellow darken-3"
                            background-color="grey darken-1"
                            empty-icon="$ratingFull"
                            half-increments
                            dense
                            large
                            hover>
                            </v-rating>
                        </v-row>
                    </v-container>
                </v-form>
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="closeDialog">Odustani</v-btn>
                <v-btn color="blue darken-1" text @click="save">Oceni</v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
import Vue from "vue";
export default {    
    name: "GradeClinic",
    props: ["clinics"],

    data() {
        return {     
            id: 0,
            newRating: 10,
            dialog: false
        }
    },

    methods: {
        closeDialog(){
            this.dialog = false;
            this.$refs.form.reset();
        },
        save(){
            if (this.$refs.form.validate()) {
                Vue.$axios.post("http://localhost:8081/grades/gradeClinic", {
                    id: new Number(this.id),
                    newRating: this.newRating
                })
                this.closeDialog();
            }
        }
    },

    computed: {
        requiredRule(){
          return (value) => !!value || "Required.";
        },
    }
}
</script>

<style scoped>

</style>