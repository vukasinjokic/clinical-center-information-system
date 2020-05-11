<template>
    <div>
        <v-card max-width="344"
            class="mx-auto">
            <v-list-item>
                <v-list-item-avatar color="grey"></v-list-item-avatar>
                <v-list-item-content>
                    <v-list-item-title class="headline">Profile</v-list-item-title>
                </v-list-item-content>
            </v-list-item>
            <v-list-item>
                <v-list-item-title>Email: </v-list-item-title>
                <v-list-item-title v-text="user.username"/>
            </v-list-item>
            <v-list-item>
                <v-list-item-title>Ime: </v-list-item-title>
                <v-list-item-title v-text="user.firstName"/>
            </v-list-item>
            <v-list-item>
                <v-list-item-title>Prezime: </v-list-item-title>
                <v-list-item-title v-text="user.lastName"/>
            </v-list-item>
            <v-list-item>
                <v-list-item-title>Adresa prebivalista: </v-list-item-title>
                <v-list-item-title v-text="user.address"/>
            </v-list-item>
            <v-list-item>
                <v-list-item-title>Grad: </v-list-item-title>
                <v-list-item-title v-text="user.city"/>
            </v-list-item>                        
            <v-list-item>
                <v-list-item-title>Drzava: </v-list-item-title>
                <v-list-item-title v-text="user.country"/>
            </v-list-item>
            <v-list-item>
                <v-list-item-title>Broj telefona: </v-list-item-title>
                <v-list-item-title v-text="user.phoneNumber"/>
            </v-list-item>

        </v-card>

        </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
export default {
    name:'UserProfile',
    data(){
        return{
            user:{
                id: "",
                username:"",
                password: "",
                firstName : "",
                lastName: "",
                address : "",
                city: "",
                country : "",
                phoneNumber : "",
                socialSecurityNumber: "",
                lastPasswordResetDate : "",
            }
        }
    },
    created() {
        this.fetchUserProfile();
        this.user = this.getUserProfile;
    },
    computed: mapGetters('patient', ['getUserProfile']),

    methods:{
        ...mapActions('patient',['fetchUserProfile'])
    }
}
</script>


<style></style>