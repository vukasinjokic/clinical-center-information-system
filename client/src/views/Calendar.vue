<template>
  <div>
    <v-sheet
      tile
      height="54"
      color="grey lighten-3"
      class="d-flex"
    >
      <v-btn
        icon
        class="ma-2"
        @click="$refs.calendar.prev()"
      >
        <v-icon>mdi-chevron-left</v-icon>
      </v-btn>
      <v-select
        v-model="type"
        :items="types"
        dense
        outlined
        hide-details
        class="ma-2"
        label="type"
      ></v-select>
   
      <v-select
        v-model="weekday"
        :items="weekdays"
        dense
        outlined
        hide-details
        label="weekdays"
        class="ma-2"
      ></v-select>
      <v-spacer></v-spacer>
      <v-btn
        icon
        class="ma-2"
        @click="$refs.calendar.next()"
      >
        <v-icon>mdi-chevron-right</v-icon>
      </v-btn>
    </v-sheet>
    <v-sheet height="600">
      <v-calendar
        ref="calendar"
        v-model="value"
        :weekdays="weekday"
        :type="type"
        :events="events"
        :event-overlap-mode="mode"
        :event-overlap-threshold="30"
        :event-color="getEventColor"
        @change="getEvents"
      ></v-calendar>
    </v-sheet>
  </div>
</template>

<script>
import axios from "axios";
  export default {
    data: () => ({
      type: 'month',
      types: ['month', 'week', 'day', ],
      mode: 'stack',
      modes: ['stack', 'column'],
      weekday: [0, 1, 2, 3, 4, 5, 6],
      weekdays: [
        { text: 'Sun - Sat', value: [0, 1, 2, 3, 4, 5, 6] },
        { text: 'Mon - Sun', value: [1, 2, 3, 4, 5, 6, 0] },
        { text: 'Mon - Fri', value: [1, 2, 3, 4, 5] },
        { text: 'Mon, Wed, Fri', value: [1, 3, 5] },
      ],
      value: '',
      events: [],
      eventStartDates: [],
      eventEndDates: [],
      colors: ['blue', 'indigo', 'deep-purple', 'cyan', 'green', 'orange', 'grey darken-1'],
      names: [],
    }),
    created() {
          const config = {
        headers: {
            Authorization: "Bearer " + localStorage.getItem("JWT"),
        }
      }

        axios
        .get("http://localhost:8081/doctors/16/calendar", config)
        .then(response => {
            response.data.eventStartDates.forEach(date => {
                let eventStartDate= new Date(date)
                let formatted_date = eventStartDate.getFullYear() + "-" + (eventStartDate.getMonth() + 1) + "-" + eventStartDate.getDate() + " " + eventStartDate.getHours() + ":" + eventStartDate.getMinutes();
                this.eventStartDates.push(formatted_date)
            }); 
            response.data.eventEndDates.forEach(date => {
                let eventEndDate= new Date(date)
                let formatted_date = eventEndDate.getFullYear() + "-" + (eventEndDate.getMonth() + 1) + "-" + eventEndDate.getDate() + " " + eventEndDate.getHours() + ":" + eventEndDate.getMinutes();
                this.eventEndDates.push(formatted_date)
            }); 
            this.names = response.data.eventNames;
            this.getEvents();
        })


    },
    methods: {
      getEvents () {
        const events = []

        // const min = new Date(`${start.date}T00:00:00`)
        // const max = new Date(`${end.date}T23:59:59`)

        for (let i = 0; i < this.names.length; i++) {
        //   const allDay = this.rnd(0, 3) === 0
        //   const firstTimestamp = this.rnd(min.getTime(), max.getTime())
        //   const first = new Date(firstTimestamp - (firstTimestamp % 900000))
        //   const secondTimestamp = this.rnd(2, allDay ? 288 : 8) * 900000
        //   const second = new Date(first.getTime() + secondTimestamp)

          events.push({
            name: this.names[i],
            // start: this.formatDate(first, !allDay),
            // end: this.formatDate(second, !allDay),
            start: this.eventStartDates[i],
            end: this.eventEndDates[i],
            color: this.colors[this.rnd(0, this.colors.length - 1)],
          })
        }

        this.events = events
      },
      getEventColor (event) {
        return event.color
      },
      rnd (a, b) {
        return Math.floor((b - a + 1) * Math.random()) + a
      },
      formatDate (a, withTime) {
        return withTime
          ? `${a.getFullYear()}-${a.getMonth() + 1}-${a.getDate()} ${a.getHours()}:${a.getMinutes()}`
          : `${a.getFullYear()}-${a.getMonth() + 1}-${a.getDate()}`
      },
    },
  }
</script>