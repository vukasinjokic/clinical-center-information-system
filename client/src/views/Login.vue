
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
                      v-model="user.email"
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
// import axios from 'axios'

  export default {
    name: "Login",
    computed: {
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
                email: "",
                password: ""
            }
       } 
    },
    methods:{
        submit(){
          if(this.$refs.form.validate()){
            //tvoj kod jefto
          }
        }
    }
  }
</script>