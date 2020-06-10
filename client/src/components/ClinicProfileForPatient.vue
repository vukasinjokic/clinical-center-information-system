<template>
    <div>
        <h1>This is ClinicProfileForPatient.vue</h1>
        <h2>Chosen clinic id: {{this.clinicId}}</h2>
        <v-btn @click="redirectToAppointments">Pregledi</v-btn>
    </div>
</template>

<script>
export default {
    name: "ClinicProfileForPatient",
    
    data() {
        return {
            clinicId: -1
        }
    },

    methods: {
        redirectToAppointments() {
            this.$router.push({
                name: "PredefinedAppointments",
                params: {
                    clinicId: this.clinicId
                }
            });
        }
    },

    created() {
        this.clinicId = this.$route.params.id
    }
}
</script>

<style scoped>

</style>