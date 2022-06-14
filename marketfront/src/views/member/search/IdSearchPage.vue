<template>
  <div>
    <id-search-view @searchId="searchId"></id-search-view>
  </div>
</template>

<script>
import IdSearchView from "@/components/member/IdSearchView";
import axios from "axios";
export default {
  name: "IdSearchPage",
  components: {IdSearchView},
  methods: {
    searchId(payload){
      const {name, phoneNumber} = payload;

      axios.post('http://localhost:7777/search/id', {name, phoneNumber})
        .then((res) => {
          console.log(res)
          if(res.data === ""){
            alert("일치하는 정보가 없습니다.")
          }else {
            this.$router.push({name: 'ResultPage', params: {resultId: res.data}})
            //alert("당신의 id는\" "+res.data+" \"입니다!")
          }
        })
      .catch(() => {
        alert('에러 발생');
      })
    }
  }
}
</script>

<style scoped>

</style>