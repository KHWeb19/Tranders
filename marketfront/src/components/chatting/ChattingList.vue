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
                <div>
                  <div style="    display: flex;

    align-items: center;
    height: 20px;
    font-weight: bold;
    font-size: 13px;





    ">{{ chatroom.member2 }}</div>
                    <div style="display: flex;
    -webkit-box-align: center;
    align-items: center;
    height: 20px;
    font-size: 13px;
    color: var(--seed-scale-color-gray-700);">{{lastMessage}}</div>
                  </div>
                </router-link>
                <!-- <v-btn style="width: 310px; padding: 0px; margin-left: 0px" text @click="goChatroom(chatroom.roomNo)">
                  <div>
                    <div style="border-radius: 50%; overflow: hidden;">
                        <v-img width="40" height="40" src="@/assets/profile.jpg"/>
                    </div>
                  </div>
                  Member2
                  <br/>
                  roomNo: {{chatroom.roomNo}}
                </v-btn> -->
            </div>
          </div>
        </div>
        
        <div id='right'>
        </div>

      </div>
  </form>

</template>

<script>
// import axios from 'axios'
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
        id: cookies.get('id'),
        name: cookies.get('name'),
        access_token: cookies.get('access_token'),
      },

      
      message: '',
      lastMessage: '',
      now: ('0' + (new Date()).getHours()).slice(-2) +':'+('0' + (new Date()).getMinutes()).slice(-2),
      new_data: [],
      newMessage: [],
      memberNo: 1,
      memberName: '지은',
      boardName: '물품이름',
      date: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
      menu: false,
      time: '00:00',
      menu2: false,

    }
  },
  created() {
      // this.getNewData();
  },
  methods: {
    // getNewData() {
    //             axios.get('http://127.0.0.1:5000/kafka-data')
    //               .then((res) => {
    //                 console.log(res.data)
    //                   this.new_data = res.data;
    //               }).catch((error) => {
    //                   console.error(error);
    //               });
    //         },
    goChatroom(roomNo) {
      this.$router.push({name: 'ChattingReadView',
                          params: {roomNo: roomNo.toString()}})
    },
    onSubmit() {
      // const { roomNo } = this.chatroom
      const { message, now } = this
      // console.log({ roomNo, message, now })
      // this.$emit('submit', { this.chatroom.roomNo, this.message })
      this.newMessage.push({message, now})
      console.log(this.newMessage)
      this.lastMessage = message
      // this.getNewData();
    },
    onAppoint() {
      const { date, time } = this
      this.$emit('onAppoint', { roomNo: this.chatroom.roomNo, date, time })
      history.go(0);
    },
    onReminder() {
      // const { name, date, time } = this
      // this.$emit('onReminder', { name, date, time })
    }
  },

  // beforeUpdate() {
  //   console.log('beforeUpdate');
  //   this.getNewData();
  // }

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
	height: 822px;
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
  /* overflow-y:auto; 
  overflow-x:hidden;  */
  overflow: hidden auto;
  padding: 20px 20px 0px 20px;
      border-bottom: 1px solid #bcbcbc;
}
#submit{
	width: 810px;
	height: 125px;

}
#message_box{
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
  line-height: 150%;
  padding: 0px;
  resize: none;
  font-size: 14px;
  border: none;
  outline: none;
}
</style>