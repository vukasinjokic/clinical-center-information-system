
<template>
  <v-app id="inspire">
    <v-content>
      <v-container
        
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
import Vue from 'vue';
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
            Vue.$axios
            .post('http://localhost:8081/auth/login', this.user)
            .then(response =>{
                if(response.data){
                    localStorage.setItem('JWT', response.data.accessToken);
                    localStorage.setItem('Duration', response.data.expiresIn);
                    localStorage.setItem('user_email', response.data.email);
                    localStorage.setItem('user_role', response.data.authorities[0]);
                    localStorage.setItem('is_password_changed', response.data.passwordChanged);
                    Vue.$axios.defaults.headers['Authorization'] = "Bearer " + localStorage.getItem("JWT");
                    this.$store.dispatch('snackbar/showSuccess', "Uspešno logovanje", {root: true});

                    this.$router.push({
                      name: "InstantHomeRedirect"
                    });
                } else
                  this.$store.dispatch('snackbar/showError', "Unknown error", {root: true});
            })
            .catch(() => { this.$store.dispatch('snackbar/showError', "Neispravan email ili lozinka", {root: true}); });

          }
        }
    }
  }
</script>