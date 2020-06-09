<template>

        <yandex-map 
            :coords="coords"
            zoom="13"
            :settings="settings"
            >
            <ymap-marker 
                marker-id="12"
                :coords="coords"
                
            ></ymap-marker>
    
        </yandex-map>
   
</template>

<script>
import {yandexMap, ymapMarker} from 'vue-yandex-maps'
export default {
    components: {yandexMap, ymapMarker},
    props:[
        'coords'
    ],
    data(){
        return{
            settings:{
                apiKey: 'dbf9f437-0f1f-4042-ac07-0f253547667f',
                lang: 'en',
                coordorder: 'longlat',
                version: '2.1'
            }
        }
    }
}
</script>
<style scoped>
.ymap-container {
  height: 300px;
}
</style>