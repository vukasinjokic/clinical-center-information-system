
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
                <v-form>
                  <v-text-field
                    v-model="user.email"
                    label="Email"
                    name="email"
                    type="text"
                  />

                  <v-text-field
                    v-model="user.password"
                    id="password"
                    label="Password"
                    name="password"
                    type="password"
                  />
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer />
                <v-btn v-on:click="login" color="primary">Login</v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
import axios from 'axios'

  export default {
    props: {
      source: String,
    },
    data() {
       return{
            user:{
                email: "",
                password: ""
            }
       } 
    },
    name: "Login",
    methods:{
        login(){
            axios
            .post('http://localhost:8081/api/login',this.user)
            .then(response =>{
                if(response.data){
                    alert("Uspesno logovanje");
                    this.$router.push('home');
                }
                else
                    alert("Neispravan email ili lozinka");
            });

        }
    }
  }
</script>