<template>
        <div class="text-center ma-2">
            <v-snackbar
                :timeout="4000"
                top
                :color="getColor"
                v-model="snackBar"
            >
                {{ getText }}
               
            </v-snackbar>
        </div>

</template>

<script>
import { mapGetters } from 'vuex'
export default {
    data(){
        return{
        }
    },
    computed:{
        ...mapGetters('snackbar',['getSnackbar', 'getText','getColor']),
        snackBar: {
            get() {
                return this.$store.state.snackbar.snackbar;
            },
            set(value) {
                this.$store.commit('snackbar/SET_SNACKBAR', value);
            }
        }
    },
    methods: {
        closeSnackbar(){
            //this.snackbar = false;
        }
    }
}
</script>

