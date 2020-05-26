<template>
    <div class="center">
        <h1 class="blue-grey--text">Error 404 not found</h1>
        <h2 class="blue-grey--text">Zatražili ste nepostojeći resurs.</h2>
        <img 
        src="../assets/bbone.png"
        alt="Čak i slika ne postoji."
        width="200"
        height="200">
        <ReturnHome/>
    </div>
</template>

<script>
import ReturnHome from "../components/ReturnHome"
export default {
    name: "NotFound",
    components: {
        ReturnHome
    }
}
</script>

<style scoped>
    .center {
        position: fixed; /* or absolute */
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
    }
</style>
