<template>
  <div>
    <ordinary-register-view @register="register" @check="check" :checkDoubleId="checkDoubleId"></ordinary-register-view>
  </div>
</template>

<script>
import OrdinaryRegisterView from "@/components/member/OrdinaryRegisterView";
import axios from "axios";

export default {
  name: "OrdinaryRegisterPage",
  components: {OrdinaryRegisterView},
  data(){
    return{
      checkDoubleId: true
    }
  },
  methods: {
    register(payload){
      const {id, password, name, phoneNumber, region} = payload;

      axios.post('http://localhost:7777/member/register', {id, password, name, phoneNumber, region})
        .catch((res) => {
          alert(res)
        })
        .then((res) => {
          console.log(res)
          alert("회원가입 성공")
          this.$router.push({name: "HomePage"})
        })
    },
    async check(payload){
      const {id} = payload;
      let response = await axios.get(`http://localhost:7777/member/register/${id}`)
      if(response.data === true){
        alert('중복이 아닙니다.')
        this.checkDoubleId = false;
      }else{
        alert('중복입니다.')
        this.checkDoubleId = true;
      }
    }
  }
}
</script>

<style scoped>


</style>