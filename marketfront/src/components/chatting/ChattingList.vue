<template>
  <form @submit.prevent="onSubmit" >
    <div id='top'>
      <after-login-view/>
    </div>
      <div id='full'>
        <div id='left'>
          <div id='image'>
              <div>
                  <div style="border-radius: 50%; overflow: hidden; margin-top: 20px">
                      <v-img width="44" height="44" src="@/assets/profile.jpg"/>
                  </div>
              </div>
          </div>
        </div>
        
        <div id='center'>
          <div id='name'>회원 이름</div>
            <div id='chatList'>
              <div id='chatroom' v-for="chatroom in chatrooms" :key="chatroom.roomNo">
                <router-link :to="{
                          name: 'ChattingReadView',
                          params: {roomNo: chatroom.roomNo.toString()}}">
                      {{ chatroom.roomNo }}입장
                  </router-link>
                  <v-btn style="width: 310px; padding: 0px; margin-left: 0px" text @click="goChatroom(chatroom.roomNo)">
                <div>
                  <div style="border-radius: 50%; overflow: hidden;">
                      <v-img width="40" height="40" src="@/assets/profile.jpg"/>
                  </div>
                </div>
                회원이름
                <br/>
                roomNo: {{chatroom.roomNo}}
              </v-btn>
            </div>
          </div>
        </div>
        
        <div id='right'>
          
        </div>
      </div>
  </form>

</template>

<script>
import axios from 'axios'
import AfterLoginView from '../home/AfterLoginView.vue';
// import ChattingRead from './ChattingRead.vue';

export default {
  components: { AfterLoginView,  },
  name: "ChattingList",
    props: {
      // roomNo: {
      //     type: String,
      //     required: true
      // },
      chatrooms: {
          type: Array
      }
  },
  data() {
    return {
      // message: '',
      // // time: new Date(),
      // new_data: [],
      // newMessage: [],
      // memberNo: 1,
      // memberName: '지은',
      // boardName: '물품이름',
      // date: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
      // menu: false,
      // time: null,
      // menu2: false,

    }
  },

  methods: {

    getNewData() {
                axios.get('http://127.0.0.1:5000/kafka-data')
                  .then((res) => {
                    console.log(res.data)
                      this.new_data = res.data;
                  }).catch((error) => {
                      console.error(error);
                  });
            },
    goChatroom(roomNo) {
      this.$router.push(`/chatting/${roomNo}`)
    },
    // onSubmit() {
    //   console.log(this.roomNo)
    //   const { roomNo, message } = this
    //   console.log({ roomNo, message })
    //   // this.$emit('submit', { roomNo, message })
    //   this.newMessage.push(message)
    //   // this.getNewData();
    // },
    // onAppoint() {
    //   console.log({date: this.date, time: this.time})
    //   const { roomNo, date, time } = this
    //   this.$emit('click', { roomNo, date, time })
    // }
  },

  beforeUpdate() {
    console.log('check: ' + this.chatrooms)
    // this.getNewData();
  }

}
</script>

<style scoped>
#top{
  /* height: 70px;
  display: flex;
  justify-content: center; */
  border: 1px solid #bcbcbc;
  border-top-style: none;
  border-left-style: none;
  border-right-style: none;
}
#logo{
  display: flex;
  /* -webkit-box-pack: justify; */
  /* justify-content: space-between; */
  /* -webkit-box-align: center; */
  align-items: center;
  width: 1200px;
  padding: 0px 8px;
}
#full{
	width: 1200px;
	margin: auto; /* 중앙 맞춤 */
}
#left{
	width: 80px;
	float: left;	
}
#image{
  background-color: #ededed;
	width: 80px;
	height: 894px;
  display: flex; 
  justify-content: center;
  border: 1px solid #bcbcbc;
  border-top-style: none;
  border-right-style: none;
}
#center{
	width: 310px;
	float: left;
}
#name{
	width: 310px;
	height: 72px;
  border: 1px solid #bcbcbc;
  border-top-style: none;
  border-bottom-style: none;
  border-right-style: none;
}
#chatList{
	width: 310px;
	height: 822px;
  border: 1px solid #bcbcbc;
  border-right-style: none;
}
#chatroom{
  display: flex;   
  border: 1px solid #bcbcbc;
  border-top-style: none;
  border-left-style: none;
  border-right-style: none;
}
#right{
	width: 810px;
  height: 894px;
	float: left;
  border: 1px solid #bcbcbc;
  border-top-style: none;
}
#lightgreen{
	width: 810px;
	height: 72px;
  display: flex; 
  border: 1px solid #bcbcbc;
  border-top-style: none;
  border-bottom-style: none;
}
#product{
	width: 810px;
	height: 72px;
  display: flex;
  border: 1px solid #bcbcbc;
}
#notice{
  display: flex;
      -webkit-box-align: center;
    align-items: center;
    -webkit-box-pack: center;
    justify-content: center;

      border: 1px solid #bcbcbc;
        border-top-style: none;
  border-bottom-style: none;
}
#notice-box{
  padding: 20px;
  border-radius: 10px;
  font-size: 14px;
  letter-spacing: -0.02em;
  margin: 8px 0px;
  background-color: #fff7e6;
  color: #ba5e02;
}
#chatView{
	width: 810px;
	max-height: 625px;
  min-height: 553px;
  border: 1px solid #bcbcbc;
  border-top-style: none;
  border-bottom-style: none;
  overflow-y:auto; 
  overflow-x:hidden; 
}
#submit{
	width: 810px;
	height: 125px;
  border: 1px solid #bcbcbc;
}
#message-box{
	height: 40px;
  color: white;
  background-color: #ff7E36;
  border-radius: 20px 2px 20px 20px;
  display: inline-flex;
  margin: 0px;
  padding: 10px 14px;
  max-width: 484px;
  white-space: pre-wrap;
  font-size: 14px;
  line-height: 150%;
  letter-spacing: -0.02em;
}
#message-date{
  color: #868b94;
  font-size: 12px;
  /* line-height: 150%; */
  letter-spacing: -0.02em;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  padding: 0px 4px;
}
textarea{
  margin: 12px 12px 0px;
  width: calc(100% - 24px);
  height: 63px;
  line-height: 150%;
  padding: 0px;
  resize: none;
  font-size: 14px;
  border: none;
  outline: none;
}
</style>