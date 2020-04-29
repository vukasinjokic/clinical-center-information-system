<template>
    <div class="home"> 
        <h1>Room view</h1>
        <div>
            <v-data-table
                :headers="headers"
                :items="desserts"
                :items-per-page="5"
                class="elevation-1 grey"
                calculate-widths>
            </v-data-table>
        </div>
    </div>
</template>
<script>

export default {
    name: 'Rooms',
    data(){
        return {
            headers: [
                {
                    text: 'Name',
                    align: 'start',
                    sortable: false,
                    value: 'name',
                },
                {
                    text: 'Calendar',
                    value: 'calendar'
                }
            ],
            desserts: [{
                    name: 'Sala za preglede',
                    calendar : 'Ne znam sta treba za kalendar'
            }]
        }
    },
    methods: {
    } 
}
</script>


<style></style>