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
            </v-list>

            <template v-slot:append>
                <div class="pa-3">
                    <LogOut/>
                </div>
            </template>
        </v-navigation-drawer>

        <v-content style="padding: 6px 0px 0px 6px">
            <v-container fluid>
                <router-view></router-view>
            </v-container>
        </v-content>
    </div>
</template>

<script>
import LogOut from "../components/LogOut";

export default {
    name: "PatientPage",

    components: {
        LogOut
    },

    data(){
        return {
            items: [
                { title: 'Klinike', link: '/patient/clinics', icon: 'mdi-hospital' },
                { title: 'Istorija pregleda', link: '/patient/appointments', icon:'mdi-history' },
                { title: 'Medicinski karton', link: '/patient/medicalRecord', icon: 'mdi-menu' },
                { title: 'Profil', link: '/patient/profile', icon: 'mdi-account' },
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