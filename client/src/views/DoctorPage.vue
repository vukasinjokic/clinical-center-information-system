<template>
    <div>     
        <v-navigation-drawer width="220" :clipped="clipped" v-model="drawer" enable-resize-watcher app dark class="blue-grey darken-2">
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
         
        <v-content style="padding: 6px 0px 0px 6px">
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
                { title: 'Patient', link: '/doctor/patient' },
                { title: 'Zapocinjanje pregleda', link: '/doctor/' },
                { title: 'Work calendar', link: '/doctor/:16/calendar' },
                { title: 'Create request for vacation', link: '/doctor/vacationRequest' },
                { title: 'Profile', link: '/doctor/profile' },
                { title: 'Zakazivanje pregleda/operacija', link: '/doctor/scheduleApp' },
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