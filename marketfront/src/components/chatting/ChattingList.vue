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
          <div id='name'>
            <div id='name_value'>{{this.login.name}}</div>
          </div>
          <div id='chatList'>
            <div id='chatroom' v-for="chatroom in chatrooms" :key="chatroom.roomNo">
              <router-link id='chatroom_link' :to="{
                        name: 'ChattingReadView',
                        params: {roomNo: chatroom.roomNo.toString()}}">
                <div>
                    <div style="border-radius: 50%; overflow: hidden; margin-right: 12px; width: 40px; height: 40px">
                        <v-img src="@/assets/profile.jpg"/>
                    </div>
                </div>
                <div style="display: flex; ">
                  <div>
                    <div v-if="login.memberNo==chatroom.member2.memberNo" style="display: flex; align-items: center; height: 20px;">
                      <span  style="font-weight: bold; font-size: 13px;">{{chatroom.member1.name}}</span>&nbsp;<span style="font-size: 12px;">00동</span>
                    </div>
                    <div v-if="login.memberNo==chatroom.member1.memberNo" style="display: flex; align-items: center; height: 20px;">
                      <span  style="font-weight: bold; font-size: 13px;">{{chatroom.member2.name}}</span>&nbsp;<span style="font-size: 12px;">00동</span>
                    </div>
                      <div v-if="lastMessage.roomNo==chatroom.roomNo" style="display: flex;
                      -webkit-box-align: center;
                      align-items: center;
                      height: 20px;
                      font-size: 13px;
                      color: var(--seed-scale-color-gray-700);">{{lastMessage.message}}
                      </div>
                      <div v-else style="display: flex;
                      -webkit-box-align: center;
                      align-items: center;
                      height: 20px;
                      font-size: 13px;
                      color: var(--seed-scale-color-gray-700);">{{chatroom.lastMessage}}
                      <!-- {{new_data.slice(-1)[0].content.message}} -->
                      </div>
                    </div>
                  </div>
                  <div v-if="chatroom.productBoard.productImage" style="display: flex; margin-left: auto;">
                      <v-img style="margin-right: 4px;
                      border: 1px solid var(--profile-image-border);
                      border-radius: 4px;
                      width: 40px;
                      height: 40px;
                      object-fit: cover;" :src="require(`@/assets/pImage/${chatroom.productBoard.productImage}`)"/>
                  </div>
                </router-link>
            </div>
          </div>
        </div>
        
        <div id='right'>
          <div style="display: flex;
    -webkit-box-pack: center;
    justify-content: center;
    -webkit-box-align: center;
    align-items: center;
    width: 100%;
    height: 100%;
    flex-direction: column;"><v-icon x-large>mdi-chat</v-icon><span style="margin-top: 34px;">채팅할 상대를 선택해주세요.</span></div>
          
        </div>
      </div>
  </form>

</template>

<script>
import axios from 'axios'
import AfterLoginView from '../home/AfterLoginView.vue';
import Vue from 'vue'
import cookies from "vue-cookies";
Vue.use(cookies)

export default {
  components: { AfterLoginView },
  name: "ChattingList",
  props: {
      chatroom: {
          type: Object,
          require: true
      },
      chatrooms: {
          type: Array
      }
    },
  data() {
    return {
      login: {
        memberNo: cookies.get("memberNo"),
        id: cookies.get('id'),
        name: cookies.get('name'),
        access_token: cookies.get('access_token'),
      },
      money: 0,
      
      message: '',
      lastMessage: '',
      now: ('0' + (new Date()).getHours()).slice(-2) +':'+('0' + (new Date()).getMinutes()).slice(-2),
      new_data: [],
      newMessage: [],
      date: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
      menu: false,
      time: '00:00',
      menu2: false,
      priview: '',
    }
  },
  created() {
      this.getNewData();
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
      this.$router.push({name: 'ChattingReadView',
                          params: {roomNo: roomNo.toString()}})
    },
    handleFileUpload () {
      this.files = this.$refs.files.files
      this.priview = URL.createObjectURL(this.files[0])
    },
    deletePriview() {
      this.priview = ''
      console.log(this.newMessage)
    },
    onSubmit() {
        const { roomNo } = this.chatroom
        const { memberNo } = this.login
        const { message, now } = this
        this.message=''
        if(this.priview){
          let formData = new FormData()
          formData.append('fileList', this.files[0])
          axios.post('http://localhost:7777/chatting/uploadImg', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                }
            })
          .then (()=> {
            this.$emit('onSubmit', { roomNo, memberNo, message:this.files[0].name, now, image:true })
          })
          this.newMessage.push({message:this.priview, now, image:true})
          this.priview=''
          this.lastMessage={roomNo, message:'사진을 전송 했습니다'}
        }
        if(message!='\n' && message!=''){
          this.$emit('onSubmit', { roomNo, memberNo, message, now })
          this.newMessage.push({message, now})
          this.message=''
          this.lastMessage={roomNo, message}
        }
    },
    onAppoint() {
      const { date, time } = this
      this.$emit('onAppoint', { roomNo: this.chatroom.roomNo, date, time })
      history.go(0);
    },
    onReminder() {
      const { access_token } = this.login
      const { date, time } = this
      this.$emit('onReminder', { access_token, date, time })
    },
    onCharge(){
      window.open('http://kko.to/LJwi9Wf7n')
      this.money += this.chatroom.productBoard.price
    }
  },
}
</script>

<style scoped>
#top{
  /* height: 70px;
  display: flex;
  justify-content: center; */
  border-bottom: 1px solid #bcbcbc;
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
	height: 938px;
  display: flex; 
  justify-content: center;
  border: 1px solid #bcbcbc;
  border-top-style: none;
  border-right-style: none;
}
#center{
	width: 310px;
	float: left;
  border: 1px solid #bcbcbc;
  border-top-style: none;
}
#name{
    border-bottom: 1px solid #bcbcbc;
    position: relative;
    display: flex;
    height: 72px;
    min-height: 72px;
    padding: 0px 20px;
    /* -webkit-box-align: center; */
    align-items: center;
}
#name_value{
font-weight: bold;
    font-size: 16px;
    line-height: 150%;
}
#chatList{
	width: 310px;
	height: 865px;
}
#chatroom{
  /* display: flex;    */
  
}
#chatroom_link{
  display: flex;
    padding: 16px;
    height: 72px;
    border-bottom: 1px solid #bcbcbc;
    -webkit-box-align: center;
    align-items: center;
    position: relative;
    overflow: hidden;
    background-position: center center;
    contain: content;
    color: inherit;
    cursor: pointer;
    text-decoration: none;
}
#right{
	width: 810px;
  height: 938px;
	float: left;
  border: 1px solid #bcbcbc;
  border-top-style: none;
  border-left-style: none;
}
#right1{
	width: 810px;
	height: 72px;
  min-height: 72px;
  display: flex; 
  border-bottom: 1px solid #bcbcbc;
    /* -webkit-box-pack: justify; */
    /* justify-content: space-between; */
    /* -webkit-box-align: center; */
    align-items: center;
    /* height: 64px; */
    /* min-height: 64px; */
    padding: 0px 20px;
}
#right_button{
  display: inline-flex;
  margin-left: auto;
    /* -webkit-box-pack: center;
    justify-content: center;
    -webkit-box-align: center;
    align-items: center;
    border-radius: 4px; */
}
#pay_box{
  border: 1px solid #212124;;
  border-radius: 8px;
  height: 70px;
  display: flex;
  align-items: center;
  padding: 0px 15px
}
#product{

	height: 72px;
  display: flex;
    -webkit-box-align: center;
    align-items: center;
    -webkit-box-pack: justify;
    justify-content: space-between;
    padding: 0px 20px;
    font-size: 14px;
    border-bottom: 1px solid #bcbcbc;
}
#notice{
  display: flex;
      -webkit-box-align: center;
    align-items: center;
    -webkit-box-pack: center;
    justify-content: center;
      padding: 20px 20px 0px 20px;
    

}
#notice_box{
    display: flex;
  padding: 20px;
  border-radius: 10px;
  font-size: 14px;
  letter-spacing: -0.02em;
  /* margin: 8px 0px; */
  background-color: #fff7e6;
  color: #ba5e02;
  width: 100%;
      align-items: center;
    justify-content: space-between;

}
#chatView{
	width: 810px;
	/* height: 555px; */
  overflow-y:auto; 
  /* overflow-x:hidden;  */
  /* overflow: hidden auto; */
  padding: 20px 20px 0px 20px;
      /* border-bottom: 1px solid #bcbcbc; */
}
#submit{
	/* height: 125px; */


}
#submit_form{

  /* display: flex; */
    /* flex-direction: column; */
    /* position: relative; */
    margin: 16px;
    border: 1px solid #212124;;
    border-radius: 8px;
    /* -webkit-box-pack: justify; */
    /* justify-content: space-between; */
}
#message_greenBox{
	/* height: 40px; */
  color: white;
  background-color: #086e5b;
  border-radius: 20px 2px 20px 20px;
  display: inline-flex;
  margin: 0px;
  padding: 10px 14px;
  max-width: 484px;
  max-height: 484px;
  white-space: pre-wrap;
  font-size: 14px;
  line-height: 150%;
  letter-spacing: -0.02em;
}
#message_box{
display: inline-flex;
    margin: 0px;
    padding: 10px 14px;
    max-width: 484px;
    word-break: break-word;
    white-space: pre-wrap;
    font-size: 14px;
    line-height: 150%;
    letter-spacing: -0.02em;
    border-radius: 2px 20px 20px;
    background-color: #eaebee;;
    /* color: #212124; */
}
#message_date{
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
  /* line-height: 150%; */
  padding: 0px;
  /* resize: none; */
  font-size: 14px;
  border: none;
  outline: none;

}
#files {
    visibility: hidden;
}
</style>