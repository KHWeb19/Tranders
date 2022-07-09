<template>
      <!-- <div id='full'> -->
      <form>
        <!-- <div id='left'>
          <div id='image'>
              <div>
                  <div style="border-radius: 50%; overflow: hidden; margin-top: 20px">
                      <v-img width="44" height="44" src="@/assets/profile.jpg"/>
                  </div>
              </div>
          </div>
        </div> -->
        
        <div id='center'>
          <div id='name'>
            <div id='name_value'>{{this.login.name}}</div>
          </div>
          <chatting-select :chatrooms="chatrooms" :lastMessage="lastMessage"/>
        </div>
        
        <div id='right'>
          <div style="display: flex;
          justify-content: center;
          align-items: center;
          width: 800px;
          height: 944px;
          flex-direction: column;"><v-icon x-large>mdi-chat</v-icon><span style="margin-top: 34px;">채팅할 상대를 선택해주세요.</span>
        </div>
                
      </div>
    </form>

</template>

<script>
import axios from 'axios'
import Vue from 'vue'
import cookies from "vue-cookies";
import ChattingSelect from './ChattingSelect.vue';
Vue.use(cookies)

export default {
  components: { ChattingSelect },
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
#center{
	width: 310px;
  height: 100%;
	float: left;
  border-right: 2px solid #e9ecef;
}
#name{
    border-bottom: 2px solid #e9ecef;
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
#right{
	width: 810px;
	float: left;
}

</style>