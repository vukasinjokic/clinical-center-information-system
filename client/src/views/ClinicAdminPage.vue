<template>
    <div>     
        <v-navigation-drawer width="240" :clipped="clipped" v-model="drawer" enable-resize-watcher app class="indigo lighten-5">
             <v-divider class="mb-12" />
            <v-list nav dense>
                <v-list-item v-for="item in items"
                        :to="item.link" 
                        :key="item.title"
                        link
                        >
                    <v-list-item-icon>
                        <v-icon>{{ item.icon }}</v-icon>
                    </v-list-item-icon>
                    <v-list-item-content>
                        <v-list-item-title class="font">{{ item.title }}</v-list-item-title>
                    </v-list-item-content>
                </v-list-item> 

                <v-list-group
                    no-action
                    value="true"
                    prepend-icon="mdi-account"
                    >
                   <template v-slot:activator>
                        <v-list-item>
                            <v-list-item-content>Profile</v-list-item-content>
                        </v-list-item>
                    </template> 
                    <v-list-item
                        v-for="prof in profile"
                        :key="prof"
                        :to="prof.link"
                        >
                        <v-list-item-icon>
                            <v-icon v-text="prof.icon"></v-icon>
                        </v-list-item-icon>
                        <v-list-item-title v-text="prof.title"></v-list-item-title>
                        
                    </v-list-item>
                </v-list-group>

            </v-list>
        </v-navigation-drawer>      
         
         <CustomToolbar/>
        <v-content style="padding: 4px 0px 0px 2px">
            <v-container fluid>
                <router-view></router-view>
            </v-container>
        </v-content>
    </div>
</template>

<script>
import CustomToolbar from "../components/CustomToolbar"

export default {
    components: {
        CustomToolbar
    },
    
    data(){
        return {
            items: [
                { title: 'Business report', link: '/clinicAdmin/businessReport', icon:"mdi-bookmark-check"},
                { title: 'Appointment requests', link: '/clinicAdmin/appointmentRequests', icon: "mdi-inbox" },
                { title: 'Vacation requests', link: '/clinicAdmin/vacationRequests', icon:"mdi-mailbox"},
                { title: 'Rooms', link: '/clinicAdmin/updateRooms', icon:"mdi-door-closed"},
                { title: 'Examination types', link: '/clinicAdmin/ex_type', icon:"mdi-stethoscope"},
                { title: 'Appointments', link: '/clinicAdmin/appointments', icon:"mdi-calendar-today"},
                { title: 'Doctors', link: '/clinicAdmin/doctors', icon:"mdi-doctor"},
                { title: 'Clinic profile', link: '/clinicAdmin/clinicProfile', icon:"mdi-hospital-box"}
            ],
            profile: [
                {title: 'Edit profile',link:'/clinicAdmin/profile', icon: 'mdi-account'},
                {title: 'Change password',link: '/change-password', icon: 'mdi-key'}
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