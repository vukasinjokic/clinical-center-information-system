<template>
    <div>     
        <v-navigation-drawer width="250" :clipped="clipped" v-model="drawer" enable-resize-watcher app dark class="blue-grey darken-2">
             <v-divider class="mb-7" />
            <v-list nav dense>
                <v-list-item v-for="item in items"
                        :to="item.link" 
                        :key="item.title"
                        link
                        >
                    <v-list-item-content>
                        <v-list-item-title class="font">{{ item.title }}</v-list-item-title>
                    </v-list-item-content>
                </v-list-item> 

                <v-list-group
                    value="true"
                    >
                   <template v-slot:activator>
                        <v-list-item>
                            <v-list-item-icon>
                                <v-icon>{{ icon }}</v-icon>
                            </v-list-item-icon>
                            <v-list-item-content>Profile</v-list-item-content>
                        </v-list-item>
                    </template> 
                    <v-list-item
                        v-for="prof in profile"
                        :key="prof"
                        :to="prof.link"
                        >
                        <v-list-tile-title v-text="prof.title"></v-list-tile-title>
                            <v-list-tile-action>
                                <v-icon v-text="prof.icon"></v-icon>
                            </v-list-tile-action>
                    </v-list-item>
                    </v-list-group>

            </v-list>
            <template v-slot:append>
                <div class="pa-3">
                    <LogOut/>
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
import LogOut from "../components/LogOut"

export default {
    components: {
        LogOut
    },
    
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
            profile: [
                {title: 'Edit profile',link:'/clinicAdmin/profile', icon: 'mdi-account'},
                {title: 'Change password',link: '/change-password', icon: 'mdi-account'}
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