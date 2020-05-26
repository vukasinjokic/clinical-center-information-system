<template>
    <div class="center">
        <h1 class="blue-grey--text">Error 401 unauthorized</h1>
        <h2 class="blue-grey--text">Nemate pravo pristupa ovoj stranici.</h2>
        <img 
        src="../assets/Nono-hand.png"
        alt="Ni slika nema prava pristupa."
        width="160"
        height="276">
        <ReturnHome/>
    </div>
</template>

<script>
import ReturnHome from "../components/ReturnHome"
export default {
    name: "Unauthorized",
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