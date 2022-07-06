<template>
<div>
    <after-login-view/>
    <div id='chat_read'>
        <chatting-read :userInfo="userInfo" :chatrooms="chatrooms" :chatroom="chatroom" @onSubmit="onSubmit" @onAppoint="onAppoint" @onReminder="onReminder" @onCharge="onCharge" @onPay="onPay"/>

    </div>
</div>
</template>

<script>
import ChattingRead from '@/components/chatting/ChattingRead.vue'
import { mapState, mapActions } from 'vuex'
import axios from 'axios'
import Vue from 'vue'
import cookies from "vue-cookies";
import AfterLoginView from '../../components/home/AfterLoginView.vue'
Vue.use(cookies)

export default {
    name: "ChattingReadView",
    components: { 
      ChattingRead,
        AfterLoginView
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
        ...mapState(['userInfo']),
        ...mapState(['chatroom']),
        ...mapState(['chatrooms'])
    },
    mounted() {
        this.fetchChatroomList(this.login.memberNo)
    },
    created() {
        this.fetchMyPage(this.login.id)
        this.fetchChatroom(this.roomNo)
          .catch(()=>{
              alert('채팅방 요청 실패')
              this.$router.push()
          })
    //   this.getNewData();
    },
    methods: {
        ...mapActions(['fetchMyPage']),
        ...mapActions(['fetchChatroom']),
        ...mapActions(['fetchChatroomList']),
        onSubmit(payload) {
            console.log(payload)
            // const { roomNo, message } = payload
            const { roomNo, memberNo, message, now, image } = payload
            axios.post('http://localhost:7777/kafka', { roomNo, memberNo, message, now, image })
            if(image){
                axios.put(`http://localhost:7777/chatting/last/${roomNo}`, {lastMessage: '사진을 전송 했습니다'})
            } else{
                axios.put(`http://localhost:7777/chatting/last/${roomNo}`, {lastMessage: message})
            }
            
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
        },
        onCharge(payload){
            const { id, money } = payload
            axios.put('http://localhost:7777/chatting/charge', {id, money})
        },
        onPay(payload){
            const { id, money, memberId } = payload
            axios.put('http://localhost:7777/chatting/charge', {id, money})
            console.log(memberId)
            this.$router.push({name: 'ReviewRegisterPage', params: {memberId:memberId.toString()} })
        }
    } 
}
</script>

<style scoped>
#chat_read{
  background: #f8f9fa;
  /* padding: 30px 0; */
}
</style>