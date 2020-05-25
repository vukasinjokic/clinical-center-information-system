<template>
  <div>
      <v-btn
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
      this.$router.push("/");
    }
  }
};
</script>
