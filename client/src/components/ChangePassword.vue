<template>
    <v-row justify="center">
            <v-card style="width: 40%">
                <v-card-title>
                    <v-toolbar height="45px" color="orange lighten-1" class="white--text">
                        <span class="headline">Change password</span>
                    </v-toolbar>
                </v-card-title>
                <v-card-text>
                    <v-container>
                        <v-form ref="form">
                        <v-text-field
                            :rules="[requiredRule]"
                            v-model="oldPass"
                            label="Old password"
                            type="password">
                        </v-text-field>
                        <v-text-field
                            :rules="[requiredRule]"
                            v-model="newPass"
                            label="New password"
                            type="password">
                        </v-text-field>
                        <v-text-field
                            :rules="[passwordMatch,requiredRule]"
                            v-model="confirmPass"
                            label="Confirm password"
                            type="password">
                        </v-text-field>
                        </v-form>
                    </v-container>
                </v-card-text>

                <v-card-actions>
                <v-spacer></v-spacer>
                    <v-btn color="red darken-1"  text v-on:click="closeDialog">Back to Homepage</v-btn>
                    <v-btn color="green darken-1" text @click="savePassword">Save</v-btn>
                </v-card-actions>

            </v-card>
     
    </v-row>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
export default {
    data(){
        return {
            dialogPass: false,
            oldPass: "",
            newPass: "",
            confirmPass: ""
        }
    },
    created(){
        this.fetchUserProf();
    },
    computed: {
        //mora da se fetchujeee
        ...mapGetters('userProfile', ['getUserProf']),

        passwordMatch(){
            return () => (this.newPass === this.confirmPass) || "Password must match.";
        },
        requiredRule(){
            return (value) => !!value || "Required.";
        }
    },

    methods: {
        ...mapActions('userProfile',['changePassword','fetchUserProf']),

        closeDialog(){
            const role = localStorage.getItem("user_role");
            this.dialogPass = false;
            this.$refs.form.reset();
            if(role === 'ROLE_CLINIC_ADMIN'){
                this.$router.push('/clinicAdmin');
            }else
                this.$router.push('/doctor');
        },
        savePassword(){
            if(this.$refs.form.validate()){
                var passForm = {
                    id : this.getUserProf.id,
                    old : this.oldPass,
                    new_pass : this.newPass
                }
                this.changePassword(passForm)
                    .then(() => {
                        this.closeDialog(); 
                    }).catch(() => {});

                
            }
        }
    }
}
</script>

