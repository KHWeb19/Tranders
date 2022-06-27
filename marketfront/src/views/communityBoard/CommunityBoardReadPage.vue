<template>
<v-container>
    <home-view></home-view>
    <br><br>
    <v-row>
        <community-board-read v-if="communityBoard" :communityBoard="communityBoard"/>
    </v-row>
</v-container>
</template>

<script>
import HomeView from '@/components/home/HomeView'
import { mapActions, mapState } from 'vuex'
import CommunityBoardRead from '@/components/communityBoard/CommunityBoardRead.vue'

export default {
    name:'CommunityBoardReadPage',
        props: {
        boardNo: {
            type: String,
            required: true
        }
    },
    computed: {
        ...mapState(['communityBoard'])
    },
    components: {
        CommunityBoardRead,
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
        ...mapActions(['fetchCommunityBoard']),
     }
}
</script>

<style scoped>

</style>