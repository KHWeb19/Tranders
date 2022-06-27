<template>
<v-container>
    <home-view></home-view>
    <br><br>
    <v-row>
        <community-board-modify :communityBoard="communityBoard" @submit="onSubmit"/>
    </v-row>
</v-container>
</template>

<script>
import axios from 'axios'
import { mapActions, mapState } from 'vuex'
import HomeView from '@/components/home/HomeView'
import CommunityBoardModify from '@/components/communityBoard/CommunityBoardModify.vue'

export default {
    name:'CommunityBoardModifyPage',
    components: { 
        CommunityBoardModify,
        HomeView, 
        },
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
                        alert('게시물이 수정되었습니다!')
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

</style>