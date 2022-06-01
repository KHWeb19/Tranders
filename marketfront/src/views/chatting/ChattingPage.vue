<template>
  <div>
    <!-- 상단바 -->
    상단바 자리
    <!-- 채팅방 -->
    <chatting-view @submit="onSubmit"/>
  </div>
</template>

<script>
import ChattingView from '../../components/chatting/ChattingView.vue'
import axios from 'axios'

export default {
  components: { ChattingView },
  name: "ChattingPage",
  methods: {
    onSubmit(payload) {
      console.log(payload)
      const { message } = payload
      axios.post(`http://localhost:7777/kafka/sendMessage`, { message })
        // .then(() => {
        //     this.$router.push({
        //         name: 'HomeView'
        //         })
        //     })
            .catch(() => {
                alert('문제 발생!')
            })
    }    
  }
}
</script>

<style scoped>

</style>