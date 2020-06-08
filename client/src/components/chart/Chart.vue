<template>
<div>
    <v-row>
    <v-col cols="12" md="3">
      <span>Choose period </span>
    </v-col>  
    <v-col cols="12" md="4">
      <v-select 
          solo
          v-model="selected"
          :items="items"
          @change="updateChart">
      </v-select>
    </v-col>
    </v-row>
    <div id="chart">
       <apexchart type="line" height="350" :options="chartOptions" :series="series"></apexchart>
    </div>
</div>
</template>

<script>
import VueApexCharts from 'vue-apexcharts'
import Vue from 'vue'
export default {
  name: "Chart",
    components: {
         apexchart: VueApexCharts, 
    },
    data(){
        return{
          items: ["DAILY","MONTHLY","YEARLY"],
          selected: "DAILY",
          series: [{
            name: "Number of appointments", 
            data: []
          }],
          chartOptions: {
            chart: {
              height: 350,
              type: 'line',
              zoom: {
                enabled: false
              }
            },
            dataLabels: {
              enabled: false
            },
            stroke: {
              curve: 'straight'
            },
            title: {
              text: 'Number of appointments by ',
              align: 'left'
            },
            grid: {
              row: {
                colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
                opacity: 0.5
              },
            },
            xaxis: {
            }
          },
      }
    },
    created() {
      this.updateChart();
    },
    methods: {
        timePeriod(){
          if(this.selected === "DAILY")
            return "Day";
          else if(this.selected === "MONTHLY")
            return "Month"
          else
            return "Year"
        },
        updateChart(){
          
          const email = localStorage.getItem('user_email'); 
          Vue.$axios.get('http://localhost:8081/clinics/getAppointments/' + this.selected + "/"+email)
            .then(response => {
              this.series = [{data: response.data}];
            })
            .catch(err =>{
              alert(err.data);
            });

        }
    }
    
}
</script>