<template>
  <div>
    <sns-register-view v-if="userInfo" :userInfo="userInfo" @register="register"></sns-register-view>
    <p v-else> 로딩중 ... </p>
  </div>
</template>

<script>
import SnsRegisterView from "@/components/member/SnsRegisterView";
import {mapActions, mapState} from "vuex";
import cookies from "vue-cookies";
import axios from "axios";
import {API_BASE_URL} from "@/constant/login";

const config = {
  headers: {
    'Authorization': 'Bearer '+ cookies.get('access_token'),
    'Accept' : 'application/json',
    'Content-Type': 'application/json'
  }
};

export default {
  name: "SnsRegisterPage",
  components: {SnsRegisterView},
  data() {
    return {
      id: cookies.get("id"),
      //id: 'testtest'
    }
  },
  methods: {
    ...mapActions(['fetchMyPage']),
    register(payload){
      const {id, name, region, lat, lng} = payload
      console.log(name)

      axios.post(API_BASE_URL+'/member/snsRegister', {id, name, region, lat, lng}, config)
          .then((res) => {
            console.log(res);
          })
          .catch(() => {
            alert('에러')
          })
    }
  },
  computed: {
    ...mapState(['userInfo'])
  },
  created() {
    console.log(this.id)
    this.fetchMyPage(this.id)
        .catch(() => {
          alert('에러')
        })
  }
}
</script>

<style scoped>

</style>