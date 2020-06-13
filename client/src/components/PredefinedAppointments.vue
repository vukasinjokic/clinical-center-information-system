<template>
    <div>
        <v-card>
            <v-card-title>Predefinisani pregledi</v-card-title>
            <v-data-table
                :headers="headers"
                :items="allAppointments"
                class="blue-grey darken-4 white--text"
                dark>

                <template v-slot:item.doctor="{ item }">
                    {{item.doctor.firstName + " " + item.doctor.lastName}}
                </template>
                <template v-slot:item.actions="{ item }">
                    <v-btn color="blue" 
                    @click="schedule({
                        patientEmail: '',
                        appointmentId: item.id
                    })">
                    Zakaži pregled
                    </v-btn>
                </template>
            </v-data-table>
        </v-card>
    </div>
</template>

<script>
import Vue from "vue";
import { mapActions, mapGetters } from "vuex";
export default {
    name: "PredefinedAppointments",

    data() {
        return {
            headers: [
                {text: "Price", value: "price"},
                {text: "Discount", value: "discount"},
                {text: "Doctor", value: "doctor", width: ""},
                {text: "Room", value: "room", width: ""},
                {text: "Date", value: "date"},
                {text: "ExaminationType", value: "examinationType"},
                {text: "Clinic", value: "clinic"},
                {text: "Actions", value: "actions", sortable: false}
            ]
        }
    },

    methods: {
        ...mapActions("appointments", ["fetchPredefinedAppointments"]),

        schedule(appointmentRequest) {
            appointmentRequest.patientEmail = localStorage.getItem("user_email");
            Vue.$axios.post("http://localhost:8081/appointmentRequests/addAppointmentRequest", appointmentRequest)
            .then(response => {
                if (response.status === 200) {
                    this.$store.dispatch('snackbar/showSuccess', 
                    "Vaš zahtev za lekarski pregled je poslat serveru. Odgovor da li je zahtev prihvaćen ili odbijen ćete dobiti na mejl.",
                    {root: true});
                } else {
                    this.$store.dispatch('snackbar/showError', "Unknown error: " + response.status + ".\nMessage: " + response.data, {root: true});
                }
            }).catch(error => {
                this.$store.dispatch('snackbar/showError', error.response.data, {root: true});
            });
        }
    },

    computed: mapGetters("appointments", ["allAppointments"]),

    created() {
        this.fetchPredefinedAppointments(this.$route.params.clinicId);
    }
}
</script>

<style scoped>

</style>