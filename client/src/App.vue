<template>
  <v-app>
     <v-content>
        <router-view></router-view>  
      </v-content>
    <v-app-bar
      height="35px"
      app
      class="blue-grey darken-4 white--text"
    >
      
      <router-link
      class="white--text"
        to="/clinics"
      >Clinics</router-link>
      <v-spacer></v-spacer>

      <v-toolbar-title class="white--text">Clinic center</v-toolbar-title>
    </v-app-bar>
    <Snackbar></Snackbar>
  </v-app>
</template>

<script>
import Snackbar from './components/global/Snackbar'
export default {
  name: 'App',
  components: {Snackbar},
  data: () => ({
    //
  }),
};
</script>
