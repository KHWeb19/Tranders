<template>
    <v-container >
        <!-- <v-row justify="center"> -->
            <!-- <img class="title" src="@/assets/title/communityTitle.png" width=250 > -->
        <!-- </v-row> -->
        <v-row>
            <community-board-register-form @submit="onBoardSubmit"/>
        </v-row>
    </v-container>
</template>

<script>

import axios from 'axios'
import CommunityBoardRegisterForm from '../../components/communityBoard/CommunityBoardRegisterForm.vue'

export default {
  components: { CommunityBoardRegisterForm },
    name: 'CommunityBoardRegisterPage',
    methods: {
        onBoardSubmit (payload) {
            const {formData} = payload
       
            axios.post('http://localhost:7777/communityboard/register', formData, { headers: {
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
.title{
    margin-top:4%;
    margin-bottom: 5%;
}
@media (max-width:700px){
    .title {
        margin-top:10%;
        margin-left:20px;
        margin-bottom:10%;
        width:200px;
    }
}
</style>