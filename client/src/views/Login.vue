
<template>
  <v-app id="inspire">
    <v-content>
      <v-container
        class="fill-height"
        fluid
      >
        <v-row
          align="center"
          justify="center"
        >
          <v-col
            cols="12"
            sm="8"
            md="4"
          >
            <v-card class="elevation-12">
                <v-toolbar
                  color="primary"
                  dark
                  flat
                >
                  <v-toolbar-title>Login form</v-toolbar-title>       
                </v-toolbar>
                <v-card-text>
                  <v-form ref="form">
                    <v-text-field
                      v-model="user.username"
                      :rules="[requiredRule,emailRule]"
                      label="Email"
                      name="email"
                      type="text"
                      required
                    />
                    <v-text-field
                      v-model="user.password"
                      :rules="[requiredRule]"
                      id="password"
                      label="Password"
                      name="password"
                      type="password"
                      required
                    />
                  </v-form>
                </v-card-text>
                <v-card-actions>
                  <v-spacer />
                  <v-btn to='/register' class=" ml-2 grey white--text">Create account</v-btn>
                  <v-btn v-on:click="submit" color="primary">Login</v-btn>
                </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
import axios from 'axios';
import { mapActions, mapGetters } from 'vuex';

  export default {
    name: "Login",
    computed: {
        ...mapGetters('userDetails',['getRole']),

        requiredRule(){
            return (value) => !!value || "Required.";
        },
        emailRule(){
          return (value) => /.+@.+\..+/.test(value) || "E-mail must be valid";
        }
    },
    data() {
       return{
            user:{
                username: "",
                password: ""
            }
       } 
    },
    methods:{
        ...mapActions("userDetails", ["logIn"]),

        submit(){
          if(this.$refs.form.validate()){
            axios
            .post('http://localhost:8081/auth/login', this.user)
            .then(response =>{
                if(response.data){
                    this.logIn(response.data);
                    alert("Uspesno logovanje");
                    if(this.getRole == "ROLE_DOCTOR"){
                      this.$router.push('doctor');
                    }else if(this.getRole == "ROLE_CLINIC_ADMIN"){
                      this.$router.push('clinicAdmin');
                    }else{
                      this.$router.push('home');
                    }

                }
                else
                    alert("Neispravan email ime ili lozinka");
            })
            .catch(() => { alert("Neispravan email ili lozinka") });

          }
        }
    }
  }
</script>