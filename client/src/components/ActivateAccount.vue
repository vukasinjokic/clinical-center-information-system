<template>
    <div>
      
    </div>
</template>

<script>
import Vue from 'vue';
export default {

    data(){
        return {
            
            
        }   
    },
    created(){
        Vue.$axios.post('http://localhost:8081/patients/activateAccount/' + this.$route.params.id)
        .then(this.$router.replace('/login'));
    }



}
</script>

<style>
</style>