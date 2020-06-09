<template>
    <v-toolbar
    height="40px">
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