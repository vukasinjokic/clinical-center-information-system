<template>
    <div>
        <h1>Ovo je Home page</h1>
        <div>
            E-mail adresa: {{this.user.username}}
            <br/>
            Ime: {{this.user.firstName}}
            <br/>
            Prezime: {{this.user.lastName}}
            <br/>
            Adresa prebivališta: {{this.user.address}}
            <br/>
            Grad: {{this.user.city}}
            <br/>
            Država: {{this.user.country}}
            <br/>
            Broj telefona: {{this.user.phoneNumber}}
            <br/>
            Lični broj osiguranika: {{this.user.socialSecurityNumber}}
            <br/>
            {{this.user.lastPasswordResetDate}}
        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'Home',

    data() {
        return {
            user: {
                id: "",
                email: "",
                username : "",
                password: "",
                firstName : "",
                lastName: "",
                address : "",
                city: "",
                country : "",
                phoneNumber : "",
                socialSecurityNumber: "",
                lastPasswordResetDate : "",
            }

        }
    },

    created() {
        let config = {
            headers: {
                Authorization: "Bearer " + localStorage.getItem("JWT"),
            }
        }
        axios.get(`http://localhost:8081/auth/userDetails`, config)
        .then(response => {
            this.user = response.data;
        })
    }
}
</script>



<style></style>