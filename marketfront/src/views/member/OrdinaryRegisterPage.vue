<template>
  <div>
    <ordinary-register-view
        @register="register"
        @check="check"
        :checkDoubleId="checkDoubleId"
        @checkPhoneNum="checkPhoneNum"
        @certification="certification"
        :checkDoublePhoneNum="checkDoublePhoneNum"
        :ifCheck="ifCheck">
    </ordinary-register-view>
  </div>
</template>

<script>
import OrdinaryRegisterView from "@/components/member/OrdinaryRegisterView";
import axios from "axios";
import {API_BASE_URL} from "@/constant/login";

export default {
  name: "OrdinaryRegisterPage",
  components: {OrdinaryRegisterView},
  data(){
    return{
      checkDoubleId: false,
      randomNum: 0,
      checkDoublePhoneNum: false,
      ifCheck: false,
    }
  },
  methods: {
    register(payload){
      const {id, password, name, phoneNumber, region, roles, lat, lng} = payload;

      let profileImg = 'Tranders_base_profile_img.png';
      axios.post('http://localhost:7777/member/register', {id, password, name, phoneNumber, region, roles, profileImg, lat, lng})
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
        this.checkDoubleId = true;
      }else{
        alert('중복입니다.')
        this.checkDoubleId = false;
      }
    },
    checkPhoneNum(payload){
      const {phoneNumber} = payload;
      console.log(phoneNumber)
      axios.post(API_BASE_URL+'/member/register/check/sendSMS', {phoneNumber})
          .then((res) => {
            console.log(res);
            if(res.data === 'error'){
              alert('이미 회원가입된 회원입니다')
            } else {
              this.ifCheck = true;
              this.randomNum = res.data;
            }
          })
          .catch(() => {
            alert('에러발생')
          })
    },
    certification(payload){
      const {num} = payload;
      console.log(num)
      console.log(this.randomNum)
      if(num === this.randomNum){
        alert('ok!')
        this.checkDoublePhoneNum = true;
      } else {
        alert('error!')
        this.checkDoublePhoneNum = false;
      }
    }
  }
}
</script>

<style scoped>


</style>