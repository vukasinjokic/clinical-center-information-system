<template>
    <div class="home"> 
        <h1>Room view</h1>
        <div>
        <v-container>
             <v-card>
                <v-card-title>
                    <v-text-field
                    v-model="search"
                    label="Search"
                    single-line
                    hide-details
                    ></v-text-field>
                </v-card-title>
                <v-data-table
                    :headers="headers"
                    :items="getAllRooms"
                    :items-per-page="5"
                    :search="search"
                    :expanded.sync="expanded"
                    item-key="name"
                    show-expand
                    dark grey
                   
                >
                <template v-slot:expanded-item="{ headers, item }">
                    <td :colspan="headers.length">More info about calendar for {{ item.name }}</td>
                </template>

                <template v-slot:item.actions="{ item }">
                    <v-icon
                    small
                    class="mr-2"
                    @click="deatails(item)"
                    >
                    mdi-pencil
                    </v-icon>
                   
                </template>

                </v-data-table>
            </v-card>
        </v-container>
        </div>
        <div>
            
        </div>
    </div> 
</template>
<script>

import {mapGetters, mapActions} from 'vuex'

export default {
    name: 'Rooms',

    created(){
        this.fetchRooms();
    },

    data(){
        return {
            headers: [
                {
                    text: 'Name', value: 'name',fileterable: true
                },
                {
                    text: 'Number',
                    value: 'number',
                    fileterable:true            
                },
                {
                    text: 'Clinic', value: 'clinic',fileterable: true
                },
                { 
                    text: 'Actions', value: 'actions', sortable: false 
                },
            ],
            search: "",
            expanded: []
        }
    },
    methods: {
        ...mapActions('room',['fetchRooms']),
        deatails(){
            
        }
       
    },
    computed: mapGetters('room', ['getAllRooms']),
}
</script>


<style>
.upper{
    position: relative;
}


</style>