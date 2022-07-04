<template>
<v-container >
    <home-view></home-view>
    <br><br>
    <v-row>
        <community-board-register-form @submit="onBoardSubmit"/>
    </v-row>
</v-container>
</template>

<script>
import HomeView from '@/components/home/HomeView'
import CommunityBoardRegisterForm from '@/components/communityBoard/CommunityBoardRegisterForm.vue'
import axios from 'axios'

export default {
    name: 'CommunityBoardRegisterPage',
    components: { 
        CommunityBoardRegisterForm, 
        HomeView, 
        },
    methods: {
        onBoardSubmit (payload) {
            const {formData} = payload
       
            axios.post(`http://localhost:7777/communityboard/register`, formData, { headers: {
                    'Content-Type': 'multipart/form-data'
                }})
                    .then(() => {
                        alert('게시글이 등록되었습니다.')
                        this.$router.push({
                            name: 'CommunityBoardListPage'
                        })
                    })
                    .catch(() => {
                        alert('문제 발생!')
                    })
        }
        
    },
}
</script>

<style scoped>


</style>