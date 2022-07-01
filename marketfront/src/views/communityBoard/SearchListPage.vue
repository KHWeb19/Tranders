<template>
<v-container>
    <home-view></home-view>
    <br><br><br>
    <v-row justify="center">
        <span id="shadow" class="shadow">동네 생활</span>
        <search-list :searchList="searchList"/>   
    </v-row>
</v-container>    
</template>

<script>
import HomeView from '@/components/home/HomeView'
import SearchList from "@/components/communityBoard/SearchList.vue"
export default {
    props:{
        searchList: {
            type: Array,
            required: true
        }
    },
    components: {
        SearchList,
        HomeView,
    }   
}
</script>

<style scoped>
.shadow{
    font-family:  'Cute Font', cursive;
    font-size: 50px;
    font-weight: 900;
    line-height: 20px;
}
#shadow{
    text-shadow: 2px 2px rgb(135,255,0);
    /* color: rgb(185,255,75); */
    color: green;
}
</style>            