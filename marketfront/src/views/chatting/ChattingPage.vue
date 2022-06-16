<template>
  <div>
    <chatting-view :chatrooms="chatrooms" @submit="onSubmit" @click="onAppoint"/>
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
      const { roomNo, message } = payload
      axios.post('http://localhost:7777/kafka', { roomNo, message })

    },
    onAppoint(payload) {
      // console.log(payload)
      const { memberName, boardName, date, time } = payload
      console.log(memberName, boardName, date, time)
      axios.post('http://127.0.0.1:5000/kakao-message', {memberName, boardName, date, time})
    }
  }
}
</script>

<style scoped>

</style>