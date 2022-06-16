<template>
    <v-container>
        <!-- <v-row justify="center">
            <img class="titleImg" src="@/assets/title/marketTitle.png" width=180 >
        </v-row> -->
        <v-row>
            <community-board-modify :communityBoard="communityBoard" @submit="onSubmit"/>
        </v-row>
    </v-container>
</template>

<script>
import axios from 'axios'
import { mapActions, mapState } from 'vuex'
import CommunityBoardModify from '@/components/communityBoard/CommunityBoardModify.vue'
export default {
  components: { CommunityBoardModify },
    name:'CommunityBoardModifyPage',
    props: {
        boardNo: {
            type: String,
            required: true
        }
    },
    computed: {
        ...mapState(['communityBoard'])
    },
    methods: {
        ...mapActions(['fetchCommunityBoard']),
        onSubmit (payload) {
            const {formData} = payload
            axios.put(`http://localhost:7777/communityboard/${this.boardNo}`, formData, { headers: {
                    'Content-Type': 'multipart/form-data'
                }})
                    .then(res => {
                        alert('게시물 수정 성공!')
                        this.$router.push({
                            name: 'CommunityBoardReadPage',
                            params: { boardNo: res.data.boardNo.toString() }
                        })
                    })
                    .catch(() => {
                        alert('게시물 수정 실패!')
                    })
        }
    },
    created() {
        this.fetchCommunityBoard(this.boardNo)
        .catch(() => {
            alert('게시물 DB조회 실패!')
        })
    }
}
</script>

<style scoped>
/* .titleImg{ 
    margin-top:4%;
    margin-bottom: 5%;
}
@media (max-width:700px){
    .titleImg {
        margin-top:10%;
        margin-left:20px;
        margin-bottom:10%;
        width:200px;
    }
}*/
</style>