<template>
  <v-app>
     <v-content>
        <router-view></router-view>  
      </v-content>
    <v-app-bar
      height="40px"
      app
      class="deep-purple accent-4"
    >
      <router-link
        class="white--text"
        to="/home"
        
      >
      Home</router-link>
      <v-spacer></v-spacer>
      <router-link
        class="white--text"
        to="/rooms"
      >Rooms</router-link>
      <v-spacer></v-spacer>
        
      <router-link
        class="white--text"
        to="/appointments"
      >Appointments</router-link>
      <v-spacer></v-spacer>
      
      
      <router-link
      class="white--text"
        to="/patients"
      >Patients</router-link>
      <v-spacer></v-spacer>

      <router-link
      class="white--text"
        to="/clinics"
      >Clinics</router-link>
      <v-spacer></v-spacer>

      <v-toolbar-title class="white--text">Clinic center</v-toolbar-title>
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
