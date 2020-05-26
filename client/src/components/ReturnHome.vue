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
            const user_role = localStorage.getItem("user_role");
            if (user_role === "ROLE_CLINIC_CENTER_ADMIN") {
                this.$router.push({
                    name: "ClinicCenterAdminPage"
                });
            } else if (user_role === "ROLE_CLINIC_ADMIN") {
                this.$router.push({
                    name: "ClinicAdminPage"
                });
            } else if (user_role === "ROLE_DOCTOR") {
                this.$router.push({
                    name: "DoctorPage"
                });
            } else if (user_role === "ROLE_NURSE") {
                this.$router.push({
                    name: "NursePage"
                });
            } else if (user_role === "ROLE_PATIENT") {
                this.$router.push({
                    name: "PatientPage"
                });
            } else {
                alert("Something went wrong. This should not happen.")
                // this.$store.dispatch("clearStore")
                // this.$router.push("/");
            }
        }
    }
}
</script>

<style scoped>

</style>