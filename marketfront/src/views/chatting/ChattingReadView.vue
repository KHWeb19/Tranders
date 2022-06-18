<template>
<div>
        <chatting-read :chatrooms="chatrooms" :chatroom="chatroom" @click="onAppoint"/>

</div>

</template>

<script>
import ChattingRead from '@/components/chatting/ChattingRead.vue'
import { mapState, mapActions } from 'vuex'
import axios from 'axios'
export default {
    name: "ChattingReadView",
    components: { 
      ChattingRead
    },
    props: {
        roomNo: {
            type: String,
            required: true
        }
    },
    computed: {
    ...mapState(['chatroom']),
        ...mapState(['chatrooms'])
    },
    mounted() {
    this.fetchChatroomList()
    },
    created() {

      this.fetchChatroom(this.roomNo)
          .catch(()=>{
              alert('채팅방 요청 실패')
              this.$router.push()
          }),
      this.getNewData();
    },
    methods: {
        ...mapActions(['fetchChatroom']),
            ...mapActions(['fetchChatroomList']),
        onAppoint(payload) {
            console.log(payload)
            const { roomNo, date, time } = payload
            // axios.post('http://127.0.0.1:5000/kakao-message', {memberName, boardName, date, time})
            axios.put(`http://localhost:7777/chatting/${roomNo}`, {appointDate: date, appointTime: time})
        }
    } 
}
</script>