<template>
    <v-toolbar
    height="35px"
    class="indigo lighten-5">
        <v-spacer></v-spacer>
        <LogOut/>
    </v-toolbar>
</template>

<script>
import LogOut from "./LogOut"
export default {
    name: "CustomToolbar",

    components: {
        LogOut
    }
}
</script>

<style scoped>

</style>