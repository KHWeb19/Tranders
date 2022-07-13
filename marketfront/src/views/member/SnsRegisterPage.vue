<template>
  <div>
    <sns-register-view @register="register"></sns-register-view>
  </div>
</template>

<script>
import SnsRegisterView from "@/components/member/SnsRegisterView";
import cookies from "vue-cookies";
import axios from "axios";
import {API_BASE_URL} from "@/constant/login";

export default {
  name: "SnsRegisterPage",
  components: {SnsRegisterView},
  data() {
    return {
      id: cookies.get("id"),
    }
  },
  methods: {
    register(payload){
      const {id, name, region, lat, lng} = payload
      console.log(name)

      axios.post(API_BASE_URL+'/member/snsRegister', {id, name, region, lat, lng})
          .then((res) => {
            console.log(res);
            this.$router.push({name: 'HomePage'})
          })
          .catch(() => {
            alert('에러')
          })
    }
  },

}
</script>

<style scoped>

</style>