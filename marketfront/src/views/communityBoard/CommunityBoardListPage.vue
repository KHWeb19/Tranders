<template>
<v-container>
    <after-login-view/>
    <br><br><br>
    <v-row justify="center">
        <span id="shadow" class="shadow">동네 생활</span>
        <community-board-list :communityBoards="communityBoards"/>
    </v-row>
    <v-btn class=writeBtn type="button" color="light green accent-4" style="box-shadow:none" onclick="location.href='http://localhost:8080/Tranders/CommunityRegister'" fixed right bottom dark fab big><v-icon>mdi-plus</v-icon>    
    </v-btn>                                 <!-- green darken-1 -->
</v-container>
</template>

<script>
import CommunityBoardList from '@/components/communityBoard/CommunityBoardList.vue'
import AfterLoginView from "../../components/home/AfterLoginView.vue";
import { mapState, mapActions } from 'vuex'
export default {
    name: 'CommunityBoardListPage',
    components: {
      CommunityBoardList,
      AfterLoginView
    },
    computed: {
        ...mapState(['communityBoards'])
    },
    mounted () {
        this.fetchCommunityBoardList()
    },
    methods: {
        ...mapActions(['fetchCommunityBoardList'])
    }
}
</script>

<style scoped>
.writeBtn {
    position: sticky;
    /* margin-top:10%; */
    margin-left:82%;
    zoom:1;
}
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