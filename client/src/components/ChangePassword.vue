<template>
    <v-row justify="center">
        <v-dialog v-model="dialogPass" persistent max-width="350px">
            <template v-slot:activator="{ on }">
                <v-btn  v-on="on" @click="changePass" dark >Change password</v-btn>
            </template>
            <v-card>
                <v-toolbar height="45px" color="orange lighten-1" class="white--text">
                    <span class="headline">Change password</span>
                </v-toolbar>

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
                    <v-btn color="red darken-1"  text v-on:click="closeDialog">Close</v-btn>
                    <v-btn color="green darken-1" text @click="savePassword">Save</v-btn>
                </v-card-actions>

            </v-card>
        </v-dialog>
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
    computed: {
        ...mapGetters('userProfile', ['getUserProf']),

        passwordMatch(){
            return () => (this.newPass === this.confirmPass) || "Password must match.";
        },
        requiredRule(){
            return (value) => !!value || "Required.";
        }
    },

    methods: {
        ...mapActions('userProfile',['changePassword']),

        closeDialog(){
            this.dialogPass = false;
            this.$refs.form.reset();
        },
        savePassword(){
            if(this.$refs.form.validate()){
                var passForm = {
                    id : this.getUserProf.id,
                    old : this.oldPass,
                    new_pass : this.newPass
                }
                this.changePassword(passForm);

                this.closeDialog();
            }
        }
    }
}
</script>

