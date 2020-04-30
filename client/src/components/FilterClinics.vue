<template>
    <div>
        <input type="text" v-model="search" placeholder="Search clinics">
    </div>
</template>

<script>

export default {
    name: "FilterClinics",
    data() {
        return {
            search: ''
        }
    },

    computed: {
        filteredBlogs: function() {
            return this.allClinics().filter(clinic => {
                return clinic.name.match(this.search);
            })
        }
    }
}
</script>

<style scoped>
    select {
        margin-top: 20px;
        padding: 6px;
        border: #41b883 1px solid;
    }
</style>