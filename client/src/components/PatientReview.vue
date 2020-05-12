<template>
<div style="margin: 30px 70px 0px 70px">
        <v-data-table
            class="blue-grey darken-4 white--text"
            dark
            :headers="headers"
            :items="allPatients"
            :items-per-page="5"
            item-key="item.email"
        >
        </v-data-table>
</div>
</template>

<script>
import {mapGetters,mapActions} from 'vuex';

export default {
    name: "PatientReview",
    data(){
        return{
            headers: [
                {
                    text: 'Name', value: 'firstName',fileterable: true
                },
                {
                    text: 'Last name', value: 'lastName', sortable:false
                },
                {
                    text: 'Email', value:'email', fileterable: true
                },
                {
                    text: 'Address', value:'address', sortable:false
                },
                {
                    text:'City', value:'city', sortable:false
                },
                {
                    text:'Phone number', value:'phoneNumber', sortable:false
                }
            ]
        }
    },
    created(){
        this.fetchPatients();
    },
    computed: {
        ...mapGetters('patient',['allPatients']),
        ...mapGetters('userDetails',['getUser'])
    },
    methods:{
        ...mapActions('patient',['fetchPatients'])
    }
}
</script>