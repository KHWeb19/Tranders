<template>
  <div>
    <ordinary-register-view @register="register"></ordinary-register-view>
  </div>
</template>

<script>
import OrdinaryRegisterView from "@/components/member/OrdinaryRegisterView";
import axios from "axios";

export default {
  name: "OrdinaryRegisterPage",
  components: {OrdinaryRegisterView},
  methods: {
    register(payload){
      const {id, pw, name, phoneNum, region} = payload;

      axios.post('http://localhost:7777/member/register', {id, pw, name, phoneNum, region})
        .catch((res) => {
          alert(res)
        })
        .then((res) => {
          console.log(res)
        })
    }
  }
}
</script>

<style scoped>


</style>