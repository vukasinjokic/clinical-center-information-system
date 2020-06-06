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
      <v-spacer></v-spacer>
      <v-toolbar-title class="white--text">Clinic center</v-toolbar-title>
      <v-spacer></v-spacer>
    </v-app-bar>
   
  </v-app>
</template>

<script>
export default {
  name: 'App',

  data: () => ({
    //
  }),
};
</script>
