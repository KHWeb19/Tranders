<template>
    <div id='back_page'>
        <after-login-view/>
        <div id='full'>
            <div id='left'>
                <div id='image'>
                    <div>
                        <div style="border-radius: 50%; overflow: hidden; margin-top: 20px">
                            <v-img width="44" height="44" :src="require(`@/assets/profile/${userInfo.profileImg}`)"/>
                        </div>
                    </div>
                </div>
            </div>
            <chatting-read :userInfo="userInfo" :chatroom="chatroom" :chatrooms="chatrooms" @onSubmit="onSubmit" @onAppoint="onAppoint" @onReminder="onReminder" @onCharge="onCharge" @onPay="onPay" @onDelete="onDelete"/>
        </div>
    </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'
import axios from 'axios'
import Vue from 'vue'
import cookies from "vue-cookies";
import AfterLoginView from '../../components/home/AfterLoginView.vue'
import ChattingRead from '../../components/chatting/ChattingRead.vue';
Vue.use(cookies)

export default {
    name: "ChattingReadView",
    components: { 
        AfterLoginView,
        ChattingRead,
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
        },
        onCharge(payload){
            const { id, money } = payload
            axios.put('http://localhost:7777/chatting/charge', {id, money})
        },
        onPay(payload){
            const { id, money, memberId, productNo } = payload
            axios.put(`http://localhost:7777/chatting/pay/${productNo}`, {id, money, productNo})
            this.$router.push({name: 'ReviewRegisterPage', params: {memberId:memberId.toString()} })
        },
        onDelete(payload) {
            const {roomNo} = payload
            axios.delete(`http://localhost:7777/chatting/${roomNo}`)
                .then(()=> {
                    alert('삭제 성공')
                    this.$router.push({name: 'ChattingListPage'})
                })
                .catch(()=> {
                    alert('삭제실패 문제발생')
                })
        },

    } 
}
</script>

<style scoped>
#back_page{
    background: #f8f9fa; 
    min-height:100vh;
}

/* #top{
  border-bottom: 1px solid #bcbcbc;
} */
#logo{
  display: flex;
  align-items: center;
  width: 1200px;
  padding: 0px 8px;
}
#full{
  display: flex;
  width: 1200px;
  height: 100%;
  margin: 0 auto;
  line-height: 24px;
  background: #fff;
}
#left{
	width: 80px;
	float: left;	
}
#image{
  background-color: #ededed;
	width: 80px;
	height: 100%;
  display: flex; 
  justify-content: center;
}

</style>