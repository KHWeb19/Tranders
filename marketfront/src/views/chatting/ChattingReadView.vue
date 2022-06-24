<template>
    <div>
        <chatting-read :chatrooms="chatrooms" :chatroom="chatroom" @onSubmit="onSubmit" @onAppoint="onAppoint" @onReminder="onReminder"/>

    </div>

</template>

<script>
import ChattingRead from '@/components/chatting/ChattingRead.vue'
import { mapState, mapActions } from 'vuex'
import axios from 'axios'
import Vue from 'vue'
import cookies from "vue-cookies";
Vue.use(cookies)

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
      data() {
    return {
      login: {
        memberNo: cookies.get('memberNo'),
        id: cookies.get('id'),
        name: cookies.get('name'),
        access_token: cookies.get('access_token'),
      }
    }
  },
    computed: {
        ...mapState(['chatroom']),
        ...mapState(['chatrooms'])
    },
    mounted() {
        this.fetchChatroomList(this.login.memberNo)
    },
    created() {

      this.fetchChatroom(this.roomNo)
          .catch(()=>{
              alert('채팅방 요청 실패')
              this.$router.push()
          })
    //   this.getNewData();
    },
    methods: {
        ...mapActions(['fetchChatroom']),
        ...mapActions(['fetchChatroomList']),
        onSubmit(payload) {
            console.log(payload)
            const { roomNo, memberNo, message, now } = payload
            axios.post('http://localhost:7777/kafka', { roomNo, memberNo, message, now })
        },
        onAppoint(payload) {
            console.log(payload)
            const { roomNo, date, time } = payload
            axios.put(`http://localhost:7777/chatting/${roomNo}`, {appointDate: date, appointTime: time})
        },
        onReminder(payload){
            console.log(payload)
            const { access_token, date, time } = payload
            axios.post('http://127.0.0.1:5000/kakao-message', {access_token, date, time})
            // axios.post('http://localhost:5000/kakao-message', {access_token, date, time})
        }
    } 
}
</script>