<template>
    <div>     
        <v-navigation-drawer width="260" :clipped="clipped" v-model="drawer" enable-resize-watcher app dark class="blue-grey darken-2">
            <v-divider class="mb-7" />
            <v-list nav dense>
                <div/>
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
        </v-navigation-drawer>      
         
         <CustomToolbar/>
        <v-content style="padding: 6px 0px 0px 6px">
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
                { title: 'Patient', link: '/doctor/patient', icon: 'mdi-clipboard-outline' },
                { title: 'Zapocinjanje pregleda', link: '/doctor/', icon:'mdi-check' },
                { title: 'Work calendar', link: '/doctor/:16/calendar', icon: 'mdi-calendar' },
                { title: 'Create request for vacation', link: '/doctor/vacationRequest',icon:'mdi-airplane' },
                // { title: 'Profile', link: '/doctor/profile', icon: 'mdi-account' },
                { title: 'Zakazivanje pregleda/operacija', link: '/doctor/scheduleApp', icon: 'mdi-arrow-down-bold-box' },
            ],
            profile: [
                {title: 'Edit profile',link:'/doctor/profile', icon: 'mdi-account'},
                {title: 'Change password',link: '/change-password', icon: 'mdi-account'}
            ],
            drawer: true,
            icon: 'mdi-account'
        }   
    }
}
</script>

<style>
.font{
    font-size: 13px
}
</style>