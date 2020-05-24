<template>
    <div>     
        <v-navigation-drawer width="220" :clipped="clipped" v-model="drawer" enable-resize-watcher app dark class="blue-grey darken-2">
             <v-divider class="mb-7" />
            <v-list>
                <v-list-item v-for="item in items"
                        :to="item.link" 
                        :key="item.title"
                        link
                        >
                    <v-list-item-content>
                        <v-list-item-title class="font">{{ item.title }}</v-list-item-title>
                    </v-list-item-content>
                </v-list-item> 
            </v-list>
            <template v-slot:append>
                <div class="pa-3">
                    <v-btn block >Logout</v-btn>
                </div>
            </template>
        </v-navigation-drawer>      
         
        <v-content style="padding: 4px 0px 0px 2px">
            <v-container fluid>
                <router-view></router-view>
            </v-container>
        </v-content>
    </div>
</template>

<script>
export default {
    data(){
        return {
            items: [
                { title: 'Appointment requests', link: '/clinicAdmin/appointmentRequests' },
                { title: 'Vacation requests', link: '/clinicAdmin/vacationRequests'},
                { title: 'Rooms', link: '/clinicAdmin/rooms'},
                { title: 'Examination types', link: '/clinicAdmin/ex_type'},
                { title: 'Appointments', link: '/clinicAdmin/appointments'},
                { title: 'Doctors', link: '/clinicAdmin/doctors'},
                { title: 'CLINIC PROFILE', link: '/clinicAdmin/clinicProfile'}
            ],
            drawer: true
        }   
    }
}
</script>

<style>
.font{
    font-size: 13px
}
</style>