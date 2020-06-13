<template>
    <div>     
        <v-navigation-drawer width="250" :clipped="clipped" v-model="drawer" enable-resize-watcher app 
                                        class="indigo lighten-5">
            <v-divider class="mb-12" />
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
                { title: 'Patient', link: '/doctor/patient', icon: 'mdi-account-group' },
                // { title: 'Zapocinjanje pregleda', link: '/doctor/', icon:'mdi-check' },
                { title: 'Work calendar', link: '/doctor/:16/calendar', icon: 'mdi-calendar' },
                { title: 'Create request for vacation', link: '/doctor/vacationRequest',icon:'mdi-airplane' },
                // { title: 'Profile', link: '/doctor/profile', icon: 'mdi-account' },
                { title: 'Schedule appointment', link: '/doctor/scheduleApp', icon: 'mdi-arrow-down-bold-box' },
            ],
            profile: [
                {title: 'Edit profile',link:'/doctor/profile', icon: 'mdi-account'},
                {title: 'Change password',link: '/change-password', icon: 'mdi-key'}
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