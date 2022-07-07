<template>
  <div>
    <after-login-view/>
    <div id='chat_list'>
    <chatting-list :chatrooms="chatrooms" @submit="onSubmit"/>
    </div>
  </div>
</template>

<script>
import ChattingList from '@/components/chatting/ChattingList.vue'
import { mapState, mapActions } from 'vuex'
import axios from 'axios'
import Vue from 'vue'
import cookies from "vue-cookies";
import AfterLoginView from '../../components/home/AfterLoginView.vue'
Vue.use(cookies)

export default {
  name: "ChattingListPage",
  components: { 
    ChattingList,
    AfterLoginView,
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
    ...mapState(['chatrooms'])
  },
  mounted () {
    this.fetchChatroomList(this.login.memberNo)
  },
  methods: {
    ...mapActions(['fetchChatroomList']),
    onSubmit(payload) {
      console.log(payload)
      const { message } = payload
      axios.post('http://localhost:7777/kafka', { message })

    }    
  }
}
</script>

<style scoped>
#chat_list{
  background: #f8f9fa;
  /* padding: 30px 0; */
}
</style>