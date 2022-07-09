<template>
      <form>
        
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
          width: 100%;
          height: 900px;
          flex-direction: column;"><v-icon x-large>mdi-chat</v-icon><span style="margin-top: 34px;">채팅할 상대를 선택해주세요.</span>
        </div>
                
      </div>
    </form>

</template>

<script>
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
        name: cookies.get('name'),
      },
      lastMessage: '',
    }
  },
  methods: {
    goChatroom(roomNo) {
      this.$router.push({name: 'ChattingReadView',
                          params: {roomNo: roomNo.toString()}})
    },
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