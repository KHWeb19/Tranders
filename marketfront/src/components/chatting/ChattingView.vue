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
          <div id='green'>채팅방 목록</div>
          <div id='yellow'>기타</div>
        </div>
        
        <div id='right'>
          <div id='lightgreen'>
            <div>
                <div style="border-radius: 50%; overflow: hidden;">
                    <v-img width="40" height="40" src="@/assets/profile.jpg"/>
                </div>
            </div>
            상대 이름
          </div>
          <div id='pink'>
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
          <div id='skyblue'>
            <div v-if="!messages || (Array.isArray(messages) && messages.length === 0)">
                <div colspan="4">
                    채팅 기록이 없습니다!
                </div>
            </div>
            <div v-else v-for="msg in messages" :key="msg.messageNo">{{msg.message}}{{msg.regDate}}</div>
            <div v-for="msg in sendMessage" :key="msg.messageNo">{{msg}}</div>
          </div>

          <div id='orange'>
            <textarea v-model="message" placeholder="메시지를 입력해주세요"></textarea>   
            <v-btn type="submit">전송</v-btn>
          </div>
        </div>
      </div>
  </form>

</template>

<script>
export default {
  name: "ChattingView",
  props: {
    messages: {
        type: Array
    }
  },
  data() {
    return {
      message: '',
      sendMessage: [],
    }
  },
  methods: {
    onSubmit() {
      const { message } = this
      this.$emit('submit', { message })
      this.sendMessage.push(message)
    }
  }
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
#green{
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
}
#orange{
	width: 810px;
	height: 125px;
  border: 1px solid #bcbcbc;
}
</style>