<template>
  <div>
    <!-- 상단바 -->
    상단바 자리
    <!-- 채팅방 -->
    <chatting-view :messages="messages" @submit="onSubmit"/>
  </div>
</template>

<script>
import ChattingView from '../../components/chatting/ChattingView.vue'
import { mapActions, mapState } from 'vuex'
import axios from 'axios'

export default {
  components: { ChattingView },
  name: "ChattingPage",
  computed: {
        ...mapState(['messages'])
    },
    created() {
        this.fetchMessageList()
    },
    mounted () {
        // this.fetchMessageList()
    },
  methods: {
    ...mapActions(['fetchMessageList']),
    onSubmit(payload) {
      console.log(payload)
      const { message } = payload
      axios.post(`http://localhost:7777/kafka/sendMessage`, { message })
        
      axios.post('http://localhost:7777/kafka/register', { message })
          .then(() => {
            this.fetchMessageList()
          })
    }    
  }
}
</script>

<style scoped>

</style>