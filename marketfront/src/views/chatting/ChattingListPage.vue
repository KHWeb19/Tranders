<template>
  <div id='back_page'>
    <after-login-view/>
      <div id='full'>
          <div id='left'>
              <div id='image'>
                  <div>
                      <div style="border-radius: 50%; overflow: hidden; margin-top: 20px">
                          <v-img width="44" height="44" :src="require(`@/assets/profile/${userInfo.profileImg}`)"/>
                      </div>
                  </div>
              </div>
          </div>
          <chatting-list :chatrooms="chatrooms"/>
      </div>

  </div>
</template>

<script>
import ChattingList from '@/components/chatting/ChattingList.vue'
import { mapState, mapActions } from 'vuex'
import AfterLoginView from '../../components/home/AfterLoginView.vue'
import Vue from 'vue'
import cookies from "vue-cookies";
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
    ...mapState(['userInfo']),
    ...mapState(['chatrooms'])
  },
  mounted () {
    this.fetchChatroomList(this.login.memberNo)
    this.fetchMyPage(this.login.id)
  },
  methods: {
    ...mapActions(['fetchMyPage']),
    // onSubmit(payload) {
    //   console.log(payload)
    //   const { message } = payload
    //   axios.post('http://localhost:7777/kafka', { message })

    // }    
  }
}
</script>

<style scoped>
#back_page{
    background: #f8f9fa; 
    min-height:100vh;
}
#full{
  display: flex;
  width: 1200px;
  height: 100%;
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
	height: 100%;
  display: flex; 
  justify-content: center;
}
</style>