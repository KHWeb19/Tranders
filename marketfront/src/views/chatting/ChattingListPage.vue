<template>
  <div>
    <chatting-list :chatrooms="chatrooms" @submit="onSubmit"/>
  </div>
</template>

<script>
import ChattingList from '@/components/chatting/ChattingList.vue'
import { mapState, mapActions } from 'vuex'
import axios from 'axios'

export default {
  components: { ChattingList },
  name: "ChattingListPage",
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