<template>
    <div>
        <after-login-view/>
        <div id='board_list'>
            <div >
                <community-board-list :communityBoards="communityBoards"/>
                <!-- <br><br><br>
                <v-row justify="center">
                    <span id="shadow" class="shadow">동네 생활</span>
                    <community-board-list :communityBoards="communityBoards"/>
                </v-row> -->
                <v-btn id='writeBtn' depressed fixed right bottom fab big class="success" type="button" onclick="location.href='http://localhost:8080/Tranders/CommunityRegister'">
                    <v-icon>mdi-plus</v-icon>    
                </v-btn>                                 <!-- green darken-1 -->
            </div>
        </div>
    </div>
</template>

<script>
import CommunityBoardList from '@/components/communityBoard/CommunityBoardList.vue'
import { mapState, mapActions } from 'vuex'
import AfterLoginView from '../../components/home/AfterLoginView.vue'

export default {
    name: 'CommunityBoardListPage',
    components: {
        CommunityBoardList,
        AfterLoginView,
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
#board_list {
    background: #f8f9fa;
    /* padding-top: 30px; */
}
#writeBtn {
    position: sticky;
    margin-left:74%;
    /* zoom:1; */
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