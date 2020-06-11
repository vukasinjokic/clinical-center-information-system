<template>
<div>
    <v-row>
    <v-col md="5" style="margin-top:10px">
      <v-btn fab text small color="grey darken-2" @click="prev">
              <v-icon small>mdi-chevron-left</v-icon>
            </v-btn>
            <v-btn fab text small color="grey darken-2" @click="next">
              <v-icon small>mdi-chevron-right</v-icon>
            </v-btn>
            <span>{{ title }}</span>
            <v-spacer></v-spacer>
    </v-col>  
    <v-col cols="12" md="3">
      <v-select   
          dense
          outlined
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
          monthNames: [
            'January', 'February', 'March', 'April', 'May', 'June',
            'July', 'August', 'September', 'October', 'November', 'December',
          ],
          date: new Date(),
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
    computed: {
      title () {
        switch (this.selected) {
       case 'YEARLY':
         return `${this.date.getUTCFullYear()}`;
       case 'MONTHLY':
         return `${this.monthNames[this.date.getUTCMonth()]} ${this.date.getUTCFullYear()}`;
       case 'DAILY':
         return `${this.date.getUTCDate()} ${this.monthNames[this.date.getUTCMonth()]} ${this.date.getUTCFullYear()}`;
       default:
         return '';
        }
      },
      monthFormatter () {
      return this.$refs.calendar.getFormatter({
        timeZone: 'UTC', month: 'long',
      })
    },
    },
    methods: {
        prev(){
          if (this.selected === 'DAILY') {
            this.date = new Date(this.date.setDate(this.date.getDate() - 1));
          } else if (this.selected === 'MONTHLY') {
            this.date = new Date(this.date.setMonth(this.date.getMonth() - 1));
          } 
          // this.request.date = this.date.toISOString().substr(0, 10);
          this.updateChart();
        },
        next(){
          if (this.selected === 'DAILY') {
            this.date = new Date(this.date.setDate(this.date.getDate() + 1));
          } else if (this.selected === 'MONTHLY') {
          this.date = new Date(this.date.setMonth(this.date.getMonth() + 1));
          }
        // this.request.date = this.date.toISOString().substr(0, 10);
          this.updateChart();
        },

        timePeriod(){
          if(this.selected === "DAILY")
            return "Day";
          else if(this.selected === "MONTHLY")
            return "Month"
          else
            return "Year"
        },
        updateChart(){
          var time = this.date.toISOString(0,10);
          const email = localStorage.getItem('user_email'); 
          Vue.$axios.get('http://localhost:8081/clinics/getAppointments/' + this.selected +"/"+time+ "/"+email)
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