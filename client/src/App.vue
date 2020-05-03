<template>
  <v-app>
     <v-content>
        <router-view></router-view>  
      </v-content>
    <v-app-bar
      height="35px"
      app
      dark
    >
      <router-link
        to="/home"
      >
      Home</router-link>
      <v-spacer></v-spacer>
      <router-link
        to="/rooms"
      >Rooms</router-link>
      <v-spacer></v-spacer>
        
      <router-link
        to="/appointments"
      >Appointments</router-link>
      <v-spacer></v-spacer>
      
      
      <router-link
        to="/patients"
      >Patients</router-link>
      <v-spacer></v-spacer>

      <router-link
        to="/clinics"
      >Clinics</router-link>
      <v-spacer></v-spacer>

      <v-toolbar-title>Clinic center</v-toolbar-title>
    </v-app-bar>
   
  </v-app>
</template>

<script>

export default {
  name: 'App',

  components: {
    
  },

  data: () => ({
    //
  }),
};
</script>
