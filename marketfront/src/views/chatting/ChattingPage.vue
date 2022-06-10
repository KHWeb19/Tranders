<template>
  <div>
    <!-- 상단바 -->
    상단바 자리
    <!-- 채팅방 -->
    <chatting-view :chatrooms="chatrooms" @submit="onSubmit"/>
  </div>
</template>

<script>
import ChattingView from '@/components/chatting/ChattingView.vue'
import { mapState, mapActions } from 'vuex'
import axios from 'axios'

export default {
  components: { ChattingView },
  name: "ChattingPage",
  computed: {
    ...mapState(['chatrooms'])
  },
  mounted () {
    this.fetchChatroomList()
  },
  methods: {
    ...mapActions(['fetchChatroomList']),
    onSubmit(payload) {
      console.log(payload)
      const { message } = payload
      axios.post('http://localhost:7777/kafka', { message })

    }    
  }
}
</script>

<style scoped>

</style>