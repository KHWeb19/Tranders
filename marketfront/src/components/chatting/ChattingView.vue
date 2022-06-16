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
        
        <!-- <div id='center' v-for="chatroom in chatrooms" :key="chatroom.roomNo"> -->
        <div id='center'>
          <div id='name'>회원 이름</div>
          <div id='chatList'>
            <div id='chatroom' v-for="chatroom in chatrooms" :key="chatroom.roomNo">
                <!-- <router-link style="text-decoration: none;" :key="$route.fullPath" :to="{
                        name: 'ChattingPage',
                        params: {roomNo: chatroom.roomNo.toString()}}">
                        채팅방
                    </router-link> -->
                <v-btn style="width: 310px; height: 72px; padding: 0px; margin-left: 0px" text @click="goChatroom(chatroom.roomNo)">
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
          <div id='lightgreen'>
            <div>
                <div style="border-radius: 50%; overflow: hidden;">
                    <v-img width="40" height="40" src="@/assets/profile.jpg"/>
                </div>
            </div>
            상대 이름
            <v-layout>
              <v-dialog persisten max-width="400">
                  <template v-slot:activator="{ on }">
                      <v-btn text block style="height: 52px;" v-on="on">약속 잡기</v-btn>
                  </template>
                  <template v-slot:default="dialog">
                      <v-card>
                          <v-card-title class="headline">
                              약속 설정
                          </v-card-title>
                          <v-card-text>
                              <v-menu
                                ref="menu"
                                v-model="menu"
                                :close-on-content-click="false"
                                :return-value.sync="date"
                                transition="scale-transition"
                                offset-y
                                min-width="auto"
                              >
                                <template v-slot:activator="{ on, attrs }">
                                  <v-text-field
                                    v-model="date"
                                    label="날짜 선택"
                                    prepend-icon="mdi-calendar"
                                    readonly
                                    v-bind="attrs"
                                    v-on="on"
                                  ></v-text-field>
                                </template>
                                <v-date-picker
                                  v-model="date"
                                  no-title
                                  scrollable
                                >
                                  <v-spacer></v-spacer>
                                  <v-btn
                                    text
                                    color="primary"
                                    @click="$refs.menu.save(date)"
                                  >
                                    선택
                                  </v-btn>
                                  <v-btn
                                    text
                                    color="primary"
                                    @click="menu = false"
                                  >
                                    닫기
                                  </v-btn>
                                </v-date-picker>
                              </v-menu>
                              <v-menu
                                ref="menu2"
                                v-model="menu2"
                                :close-on-content-click="false"
                                :nudge-right="40"
                                :return-value.sync="time"
                                transition="scale-transition"
                                offset-y
                                max-width="290px"
                                min-width="290px"
                              >
                                <template v-slot:activator="{ on, attrs }">
                                  <v-text-field
                                    v-model="time"
                                    label="시간 선택"
                                    prepend-icon="mdi-clock-time-four-outline"
                                    readonly
                                    v-bind="attrs"
                                    v-on="on"
                                  ></v-text-field>
                                </template>
                                <v-time-picker
                                  v-if="menu2"
                                  v-model="time"
                                  full-width
                                  @click:minute="$refs.menu2.save(time)"
                                ></v-time-picker>
                              </v-menu>
                          </v-card-text>
                          <v-card-actions>
                              <v-spacer></v-spacer>
                              <v-btn color="red" text @click.native="onAppoint($event)">
                                  완료
                              </v-btn>
                              <v-btn text @click="dialog.value=false">
                                  닫기
                              </v-btn>
                          </v-card-actions>
                      </v-card>
                  </template>
              </v-dialog>
          </v-layout>
          </div>
          <div id='product'>
            <div >
              <div style="display: flex;">
                  <div style="border-radius: 10%; overflow: hidden;">
                      <v-img width="40" height="40" src="@/assets/profile.jpg"/>
                  </div>
              </div>
            </div>
            <div>
              <div>게시글 제목 </div>
              <div>가격</div>
            </div>
          </div>
          <div id='chatView'>
            <div v-if="!new_data || (Array.isArray(new_data) && new_data.length === 0)">
                <div colspan="4">
                    {{roomNo}} 채팅방에 입장하였습니다. 
                </div>
            </div>
            <div v-else>
              <div style="display: flex; justify-content: flex-end;"  v-for="msg in new_data" :key="msg.messageNo">
                <p id='message-box' v-if="roomNo==msg.content.roomNo">{{msg.content.message}}</p>
              </div>
              <div style="display: flex; justify-content: flex-end;"  v-for="msg in newMessage" :key="msg.messageNo">
                <div id='message-box'>{{msg}}</div>
              </div>
            </div>
          </div> 

          <div id='submit'>
            <textarea v-model="message" placeholder="메시지를 입력해주세요"></textarea>   
            <v-btn type="submit">전송</v-btn>
          </div>
        </div>
      </div>
  </form>

</template>

<script>
import axios from 'axios'
import AfterLoginView from '../home/AfterLoginView.vue';
export default {
  components: { AfterLoginView },
  name: "ChattingView",
    props: {
      chatrooms: {
          type: Array
      }
  },
  data() {
    return {
      message: '',
      // time: new Date(),
      new_data: [],
      newMessage: [],
      memberNo: 1,
      memberName: '지은',
      boardName: '물품이름',
      date: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
      menu: false,
      time: null,
      menu2: false,
      roomNo: JSON.parse(localStorage.getItem('roomNo')),

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
      localStorage.setItem("roomNo", JSON.stringify(roomNo))
      this.$router.push(`/chat/room/${roomNo}`)
      history.go(0);
    },
    onSubmit() {
      console.log(this.roomNo)
      const { roomNo, message } = this
      console.log({ roomNo, message })
      // this.$emit('submit', { roomNo, message })
      this.newMessage.push(message)
      // this.getNewData();
    },
    onAppoint() {
      // console.log({date: this.date, time: this.time})
      this.$emit('click', {memberName: this.memberName, boardName: this.boardName, date: this.date, time: this.time})
    }
  },
  created() {
    this.getNewData();
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
	float: left;
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
  border-bottom-style: none;
}
#chatView{
	width: 810px;
	height: 625px;
  border: 1px solid #bcbcbc;
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