<template>
    <v-container>
        <v-data-table dark class="blue-grey darken-2 white--text"
            :headers="headers"
            :items="getDoctorList">

            <template v-slot:item.rating="{item}">
                <v-rating
                    small   
                    readonly
                    v-model="item.rating"
                    background-color="red lighten-1"
                    v-bind:length="10"
                ></v-rating>
            </template>
        </v-data-table>
    </v-container>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
export default {
    data(){
        return{
            headers: [
                {text: "First name", value: "firstName"},
                {text: "Last name", value: "lastName"},
                {text: "Email", value: "email"},
                {text: "Doctor rating", value: "rating"}
            ]
        }
    },
    created(){
        this.fetchDoctors();
    },
    computed: {
        ...mapGetters('doctor',['getDoctorList']),
    },
    methods: {
        ...mapActions('doctor', ['fetchDoctors']),
    }
}
</script>