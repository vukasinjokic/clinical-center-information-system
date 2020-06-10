<template>
    <div>
        
    </div>
</template>

<script>
export default {
    name: "InstantHomeRedirect",

    created() {
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
        } else if (user_role == null) {
            this.$store.dispatch("clearStore");
            this.$router.push({
                name: "Login"
            });
        }
        else {
            alert("Something went wrong. This should not happen.");
            this.$store.dispatch("clearStore");
            this.$router.push({
                name: "Login"
            });
        }
    }
}
</script>

<style scoped>

</style>