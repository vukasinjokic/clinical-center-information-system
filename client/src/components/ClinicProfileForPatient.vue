<template>
    <div>
        <h1>This is ClinicProfileForPatient.vue</h1>
        <h2>Chosen clinic id: {{this.clinicId}}</h2>
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

    created() {
        this.clinicId = this.$route.params.id
    }
}
</script>

<style scoped>

</style>