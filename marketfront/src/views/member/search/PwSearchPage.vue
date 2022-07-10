<template>
  <div>
    <pw-search-view @searchPw="searchPw"></pw-search-view>
  </div>
</template>

<script>
import PwSearchView from "@/components/member/PwSearchView";
import axios from "axios";
export default {
  name: "PwSearchPage",
  components: {PwSearchView},
  methods: {
    searchPw(payload){
      const {id, name, phoneNumber} = payload;

      axios.post('http://localhost:7777/search/pw', {id, name, phoneNumber})
        .then((res) => {
          console.log(res.data)
          if(res.data !== ""){
            //alert(res.data)
            this.$router.push({name: 'ChangePwPage', params: {id: res.data}})
          }else {
            alert('에러!')
          }
        })
      .catch(() => {
        alert("에러 발생")
      })
    }
  }
}
</script>

<style scoped>

</style>