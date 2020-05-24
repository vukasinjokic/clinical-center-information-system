<template>
    <div>
        <v-container>
            <v-row>
                <v-col cols="12" sm="6" md="5">
                    <v-card>
                        <v-card-title>
                            Osnovni podaci o klinici
                        </v-card-title>
                        <v-divader/>
                        <v-card-text>
                            <v-text-field
                                label="Clinic name"
                                filled
                                v-model="clinic.name"
                            >
                            </v-text-field>
                            <v-text-field
                                filled
                                label="Address"
                                prepend-icon="place"
                            ></v-text-field>
                        </v-card-text>
                    </v-card>
                </v-col>
                <v-col cols="12" sm="6" md="5">
                    <v-card>
                       <v-card-title>
                           Cenovnik
                       </v-card-title>
                    </v-card>
                </v-col>
            </v-row>
        </v-container>
    </div>
</template>

<script>
export default {
    data(){
        return{
            clinic:{
                name:"",
            }
        }
    }
}
</script>

<style>
</style>