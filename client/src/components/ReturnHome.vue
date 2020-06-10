<template>
    <div>
        <v-btn
        class="blue-grey darken-2 white--text"
        @click="returnHome">
            Nazad na početnu stranicu
        </v-btn>
    </div>
</template>

<script>
export default {
    name: "ReturnHome",

    methods: {
        returnHome() {
            this.$router.push({
                name: "InstantHomeRedirect"
            });
        }
    }
}
</script>

<style scoped>

</style>