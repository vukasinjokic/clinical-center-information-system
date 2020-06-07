<template>
    <div>
        <v-container>
            <h1 class="display-1"> Business Report </h1>
            <hr/>
            <v-row>
            <v-col cols="12" md="4" sm="4">
                <ClinicRatingCard/>
            </v-col>
            <v-col cols="12" md="7" sm="7">
                <DoctorsRating/>
            </v-col>
            </v-row>
            <div class="col-md-7 col-12">
                <Chart></Chart>
            </div>
        </v-container>
    </div>
</template>

<script>
import ClinicRatingCard from '../components/ClinicRatingCard'
import DoctorsRating from '../components/DoctorsRating'
import Chart from '../components/chart/Chart'
export default {
    components: {ClinicRatingCard, DoctorsRating, Chart},
    data(){
        return {
            rating:2.5,
            
        }
    },
}
</script>



<style></style>