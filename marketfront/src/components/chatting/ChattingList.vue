<template>
  <form @submit.prevent="onSubmit">
      <div id='full'>
        <div id='left'>
          <div id='red'>
              <div>
                  <div style="border-radius: 50%; overflow: hidden; margin-top: 20px">
                      <v-img width="44" height="44" src="@/assets/profile.jpg"/>
                  </div>
              </div>
          </div>
        </div>
        
        <div id='center'>
          <div id='blue'>회원 이름</div>
          <div id='chatList'>
            <div v-for="chatroom in chatrooms" :key="chatroom.roomNo">
              <v-btn text @click="goChatroom(chatroom.roomNo)">채팅방{{chatroom.roomNo}}</v-btn>
            </div>
          </div>

          <div id='yellow'>기타</div>
        </div>
        
        <div id='right'>
          <div id='lightgreen'>
            <!-- <div>
                <div style="border-radius: 50%; overflow: hidden;">
                    <v-img width="40" height="40" src="@/assets/profile.jpg"/>
                </div>
            </div>
            상대 이름 -->
          </div>
          <div id='pink'>
            <!-- <div >
              <div style="display: flex;">
                  <div style="border-radius: 10%; overflow: hidden;">
                      <v-img width="40" height="40" src="@/assets/profile.jpg"/>
                  </div>
              </div>
            </div>
            <div>
              <div>게시글 제목 </div>
              <div>가격</div>
            </div> -->
          </div>
          <div id='skyblue'>
            <!-- <div v-if="!new_data || (Array.isArray(new_data) && new_data.length === 0)">
                <div colspan="4">
                    채팅방에 입장하였습니다.
                </div>
            </div>
            <div style="padding: 4px; display: flex; justify-content: flex-end;" v-else v-for="msg in new_data" :key="msg.messageNo">

              <p id='message-box'>{{msg.content.message}}</p>
            </div> -->
          </div> 

          <div id='orange'>
            <!-- <textarea v-model="message" placeholder="메시지를 입력해주세요"></textarea>   
            <v-btn type="submit">전송</v-btn> -->
          </div>
        </div>
      </div>
  </form>

</template>

<script>
export default {
  name: "ChattingList",
    props: {
      chatrooms: {
          type: Array
      }
  },
  methods: {
    goChatroom(roomNo) {
      this.$router.push(`/chat/room/${roomNo}`)
      history.go(0);
    }
  },
}
</script>

<style scoped>
#full{
	width: 1200px;
	height: 870px;
	margin: auto; /* 중앙 맞춤 */
}
#left{
	width: 80px;
	float: left;	
}
#red{
  background-color: #ededed;
	width: 80px;
	height: 870px;
  display: flex; 
  justify-content: center;
  border: 1px solid #bcbcbc;
  border-right-style: none;
}
#center{
	width: 310px;
	float: left;
}
#blue{
	width: 310px;
	height: 60px;
  border: 1px solid #bcbcbc;
  border-bottom-style: none;
  border-right-style: none;
}
#chatList{
	width: 310px;
	height: 750px;
  border: 1px solid #bcbcbc;
  border-bottom-style: none;
  border-right-style: none;
}
#yellow{
	width: 310px;
	height: 60px;
  border: 1px solid #bcbcbc;
  border-right-style: none;
}
#right{
	width: 810px;
	float: left;
    
}
#lightgreen{
	width: 810px;
	height: 60px;
  display: flex; 
  border: 1px solid #bcbcbc;
  border-bottom-style: none;
}
#pink{
	width: 810px;
	height: 60px;
  display: flex;
  border: 1px solid #bcbcbc;
  border-bottom-style: none;
}
#skyblue{
	width: 810px;
	height: 625px;
  border: 1px solid #bcbcbc;
  border-bottom-style: none;
  overflow-y:auto; 
  overflow-x:hidden; 
}
#orange{
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
</style>