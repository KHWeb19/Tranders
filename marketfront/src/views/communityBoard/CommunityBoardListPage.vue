<template>
    <div>
        <after-login-view :num="2"/>
        <div id='board_list'>
            <div>
                <community-board-list :communityBoards="communityBoards"/>
                <v-btn id='writeBtn' depressed fixed right bottom fab big class="success" type="button" onclick="location.href='http://localhost:8080/Tranders/CommunityRegister'">
                    <v-icon>mdi-plus</v-icon>    
                </v-btn>                              
            </div>
        </div>
    </div>
</template>

<script>
import CommunityBoardList from '@/components/communityBoard/CommunityBoardList.vue'
import { mapState, mapActions } from 'vuex'
import AfterLoginView from '../../components/home/AfterLoginView.vue'
import cookies from "vue-cookies";

export default {
    name: 'CommunityBoardListPage',
    components: {
        CommunityBoardList,
        AfterLoginView,
    },
    data () {
    return {
      login: {
                id: cookies.get('id'),
                memberNo: cookies.get("memberNo"),
                name: cookies.get('name'),
                region: cookies.get('region'),
                access_token: cookies.get('access_token'),
            },
    }
  },
    computed: {
        ...mapState(['communityBoards'])
    },
    mounted () {
        this.fetchCommunityBoardList(this.login.memberNo)
        console.log(this.login.memberNo)
    },
    methods: {
        ...mapActions(['fetchCommunityBoardList'])
    }
}
</script>

<style scoped>
#board_list {
    background: #f8f9fa;
}
#writeBtn {
    position: sticky;
    margin-left:71%;
}
.shadow{
    font-family:  'Cute Font', cursive;
    font-size: 50px;
    font-weight: 900;
    line-height: 20px;
    }
#shadow{
    text-shadow: 2px 2px rgb(135,255,0);
    color: green;
    }        
</style>