<template>
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
                      </div>
                    </div>
                  </div>
                  <div v-if="chatroom.productBoard" style="display: flex; margin-left: auto;">
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
    justify-content: center;
    align-items: center;
    width: 800px;
    height: 100%;
    flex-direction: column;"><v-icon x-large>mdi-chat</v-icon><span style="margin-top: 34px;">채팅할 상대를 선택해주세요.</span></div>
          
        </div>
      </div>

</template>

<script>
import axios from 'axios'
import Vue from 'vue'
import cookies from "vue-cookies";
Vue.use(cookies)

export default {
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
/* #top{
  border-bottom: 1px solid #e9ecef;
} */
#logo{
  display: flex;
  align-items: center;
  width: 1200px;
  padding: 0px 8px;
}
#full{
  display: flex;
  border-left: 1px solid #e9ecef;
  border-right: 1px solid #e9ecef;
  width: 1200px;
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
	height: 900px;
  display: flex; 
  justify-content: center;
}
#center{
	width: 310px;
	float: left;
  border: 1px solid #e9ecef;
  border-top-style: none;
  border-bottom-style: none;
}
#name{
    border-bottom: 1px solid #e9ecef;
    position: relative;
    display: flex;
    height: 72px;
    min-height: 72px;
    padding: 0px 20px;
    align-items: center;
}
#name_value{
    font-weight: bold;
    font-size: 16px;
    line-height: 150%;
}
#chatList{
	width: 310px;
	height: 825px;
}

#chatroom_link{
  display: flex;
  padding: 16px;
  height: 72px;
  border-bottom: 1px solid #e9ecef;
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

</style>