<template>
    <div>
        <v-container>
            <h1 class="display-1"> Business Report </h1>
            <hr/>
            <v-row>
            <v-col cols="12" md="7" sm="7">
                <financial-gain></financial-gain>
            </v-col>
            <v-col cols="12" md="4" sm="4">
                <ClinicRatingCard/>
            </v-col>
            
            </v-row>
            <div class="col-md-8 col-12">
                <Chart></Chart>
            </div>
            <v-col cols="12" md="9">
                <DoctorsRating/>
            </v-col>

        </v-container>
    </div>
</template>

<script>
import ClinicRatingCard from './ClinicRatingCard'
import DoctorsRating from './DoctorsRating'
import FinancialGain from './FinancialGain'
import Chart from '../chart/Chart'
export default {
    components: {ClinicRatingCard, DoctorsRating, FinancialGain, Chart},
    data(){
        return {
            rating:2.5,
            
        }
    },
}
</script>



<style></style>