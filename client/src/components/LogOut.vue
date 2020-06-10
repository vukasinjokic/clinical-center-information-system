<template>
  <div>
      <v-btn
      small
      class="blue-grey darken-4 white--text"
      style="width: 100px"
      @click="logOut"
      block>
      Log out
      </v-btn>
  </div>
</template>

<script>

export default {
  name: 'LogOut',

  methods: {
    logOut() {
      this.$store.dispatch("clearStore")
      this.$router.push({
        name: "Login"
      });
    }
  }
};
</script>
