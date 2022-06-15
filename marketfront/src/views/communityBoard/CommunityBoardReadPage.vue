<template>
    <v-container>
        <!-- <v-row justify="center">
            <img class="titleImg" src="@/assets/title/marketTitle.png" width=180 >
        </v-row> -->
        <v-row>
            <community-board-read v-if="communityBoard" :communityBoard="communityBoard"/>
            <!-- <market-comment-list @submit="onCommentSubmit" :marketComments="marketComments" /> -->
        </v-row>
    </v-container>
</template>

<script>
import { mapActions, mapState } from 'vuex'

// import axios from 'axios'
import CommunityBoardRead from '@/components/communityBoard/CommunityBoardRead.vue'
// import MarketCommentList from '@/components/board/market/MarketCommentList.vue'

export default {
    name:'CommunityBoardReadPage',
        props: {
        boardNo: {
            type: String,
            required: true
        }
    },
    computed: {
        // ...mapState(['market' ,'marketComments'])
        ...mapState(['communityBoard'])
    },
    components: {
        // MarketCommentList,
        CommunityBoardRead,
    },

    created () {
        this.fetchCommunityBoard(this.boardNo)
                .catch(() => {
                    alert('게시물 요청 실패!')
                    console.log(this.boardNo)
                    this.$router.push()
                })
    },

     methods: {
        ...mapActions(['fetchCommunityBoard']),
     }
}
</script>

<style scoped>
.titleImg{
    margin-top:4%;
    margin-bottom: 6%;
}
@media (max-width:700px){
    .titleImg {
        margin-top:10%;
        margin-left:20px;
        margin-bottom:10%;
        width:200px;
    }
}
</style>