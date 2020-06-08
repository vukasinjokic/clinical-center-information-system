<template>
    <div>
        <v-card max-width="444"
            class="mx-auto">
            <v-card-title>
                <v-toolbar color="orange lighten-1" dark> 
                <v-list-item>
                <v-list-item-avatar color="grey"></v-list-item-avatar>
                <v-list-item-content>
                    <v-list-item-title class="headline">User profile</v-list-item-title>
                </v-list-item-content>
            </v-list-item>
              </v-toolbar>
            </v-card-title>
            
            <v-list-item>
                <v-list-item-title>Email: </v-list-item-title>
                <v-list-item-title v-text="getUserProf.email"/>
            </v-list-item>
            <v-list-item>
                <v-list-item-title>Ime: </v-list-item-title>
                <v-list-item-title v-text="getUserProf.firstName"/>
            </v-list-item>
            <v-list-item>
                <v-list-item-title>Prezime: </v-list-item-title>
                <v-list-item-title v-text="getUserProf.lastName"/>
            </v-list-item>
            <v-list-item>
                <v-list-item-title>Adresa prebivalista: </v-list-item-title>
                <v-list-item-title v-text="getUserProf.address"/>
            </v-list-item>
            <v-list-item>
                <v-list-item-title>Grad: </v-list-item-title>
                <v-list-item-title v-text="getUserProf.city"/>
            </v-list-item>                        
            <v-list-item>
                <v-list-item-title>Drzava: </v-list-item-title>
                <v-list-item-title v-text="getUserProf.country"/>
            </v-list-item>
            <v-list-item>
                <v-list-item-title>Broj telefona: </v-list-item-title>
                <v-list-item-title v-text="getUserProf.phoneNumber"/>
            </v-list-item>
            <v-list-item>
                <v-list-item-title>Broj osiguranika: </v-list-item-title>
                <v-list-item-title v-text="getUserProf.socialSecurityNumber"/>
            </v-list-item>
        </v-card>
        <v-spacer/>
        <EditProfile></EditProfile>
        
        </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import EditProfile from './EditProfile'

export default {
    name:'UserProfile',
    components: {EditProfile},
    data(){
        return{
           
        }
    },
    created() {
        this.fetchUserProf();
    },
    computed: mapGetters('userProfile', ['getUserProf']),

    methods:{
        ...mapActions('userProfile',['fetchUserProf'])
    }
}
</script>


<style></style>