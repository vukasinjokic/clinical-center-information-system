<template>
    <v-container>
        <v-data-table dark class="blue-grey darken-4 white--text"
            :headers="headers">
        </v-data-table>
    </v-container>
</template>

<script>
export default {
    data(){
        return{
            headers: [
                {text: "First name", value: "firstName"},
                {text: "Last name", value: "lastName"},
                {text: "Doctor rating", value: "rating"}
            ]
        }
    },
    created(){},
    computed: {}
}
</script>