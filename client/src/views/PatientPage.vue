<template>
    <div>
        <v-navigation-drawer width="260" v-model="drawer" :clipped="clipped" enable-resize-watcher app dark class="blue-grey darken-2">
            <v-divider class="mb-7"/>
            <v-list>
                <v-list-item
                v-for="item in items"
                v-bind:to="item.link"
                v-bind:key="item.title"
                link>

                    <v-list-item-icon>
                        <v-icon>{{item.icon}}</v-icon>
                    </v-list-item-icon>
                    <v-list-item-content>
                        <v-list-item-titlte class="font">{{item.title}}</v-list-item-titlte>
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
import CustomToolbar from "../components/CustomToolbar";

export default {
    name: "PatientPage",

    components: {
        CustomToolbar
    },

    data(){
        return {
            items: [
                { title: 'Klinike', link: '/patient/clinics', icon: 'mdi-hospital' },
                { title: 'Istorija pregleda', link: '/patient/appointments', icon:'mdi-history' },
                { title: 'Medicinski karton', link: '/patient/medicalRecord', icon: 'mdi-menu' }
            ],
            profile: [
                {title: 'Edit profile',link:'/patient/profile', icon: 'mdi-account'},
                {title: 'Change password',link: '/change-password', icon: 'mdi-account'}
            ],
            drawer: true
        }   
    }
}
</script>

<style scoped>
    .font{
        font-size: 13px
    }
</style>