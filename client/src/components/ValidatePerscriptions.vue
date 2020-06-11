<template>
    <div>
        <v-container style="width:60%">
            <v-data-table
            :headers="headers"
            :items="perscriptions"
            item-key="name"
            class="elevation-1"
            >
                <template #item.content="{item}"><span style="white-space: pre;">{{formatContent(item.content)}}</span></template>
                <template #item.actions="{ item }" >
                        <v-btn class="ma-2" color="success" @click="acceptPerscription(item)">
                                <v-icon  class="mr-1">
                                mdi-checkbox-marked-circle
                                </v-icon>
                            </v-btn>
                            <v-btn color="red" @click="denyPerscription(item)" dark>
                                <v-icon class="mr-1">
                                mdi-cancel
                                </v-icon>
                            </v-btn>
                </template>

            </v-data-table>
        </v-container>
    </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
export default {
    data(){
        return {
            headers: [
                {
                    text: 'Content', value: 'content',fileterable: true
                },
                {
                    text: 'Actions', value:'actions', sortable:false
                }   
                
            ],
            dialog : false,
            message : '',
            requestToDeny : null
            
        }   
    },
    methods : {
        ...mapActions('perscriptions', ['fetchPerscriptions', 'handleAcceptingPerscription', 'handleDenyingPerscription']),

        acceptPerscription(perscription){
            this.handleAcceptingPerscription(perscription.id);
        },


        denyPerscription(perscription){
            this.handleDenyingPerscription(perscription.id);
            
        },

        formatContent(content){
            let retVal = '';
            let keys = Object.keys(content);
            keys.forEach(key => {
                retVal += '' + key + ' ' + content[key] + " \n";
            })
            return retVal;
        }


        
    },
    computed : {  
        ...mapGetters('perscriptions', ['getPerscriptions']),

        perscriptions: function(){
            return this.getPerscriptions();
        }
    },
    created(){
        this.fetchPerscriptions();

        
    }



}
</script>

<style>
</style>