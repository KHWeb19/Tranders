<template>
<v-container>
    <home-view></home-view>
    <br><br>
    <v-row>
        <community-board-read v-if="communityBoard" :communityBoard="communityBoard"/>
    </v-row>
   <v-row>
       <community-comment-list @submit="onCommentSubmit" :communityComments="communityComments" />
   </v-row>
</v-container>
</template>

<script>
import HomeView from '@/components/home/HomeView'
import axios from 'axios'
import { mapActions, mapState } from 'vuex'
import CommunityBoardRead from '@/components/communityBoard/CommunityBoardRead.vue'
import CommunityCommentList from '@/components/communityBoard/CommunityCommentList.vue'

export default {
    name:'CommunityBoardReadPage',
        props: {
        boardNo: {
            type: String,
            required: true
        }
    },
    mounted() {
        this.fetchCommunityCommentsList(this.boardNo)
    },
    computed: {
        ...mapState(['communityBoard','communityComments'])
    },
    components: {
        CommunityBoardRead,
        CommunityCommentList,
        HomeView,
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
        ...mapActions(['fetchCommunityBoard','fetchCommunityCommentsList']),
        onCommentSubmit (payload) {
            const { comment, commentWriter, commentRegion, file, placeName, placeUrl } = payload

            let formData = new FormData()

            if (file != null ){formData.append('file', file)}
            formData.append('comment', comment)
            formData.append('commentWriter', commentWriter)
            formData.append('commentRegion', commentRegion)
            formData.append('placeName', placeName)
            formData.append('placeUrl', placeUrl)
            console.log(formData)
            
            axios.post(`http://localhost:7777/communityboard/${this.boardNo}/comment/register`, formData, { headers: {
                    'Content-Type': 'multipart/form-data'
                }})
                   .then(() => {
                    alert('댓글이 작성되었습니다!')
                    history.go(0)
            })
            .catch (() => {
                console.log(this.boardNo)
                alert('문제 발생!')
                })
        },
    }
}
</script>

<style scoped>

</style>