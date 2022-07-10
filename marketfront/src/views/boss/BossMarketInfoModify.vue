<template>
  <div>
    <boss-market-info-modify-view :bossNo="bossNo" @modifyMarketInfo="modifyMarketInfo"></boss-market-info-modify-view>
  </div>
</template>

<script>

import cookies from "vue-cookies";

const config = {
  headers: {
    'Authorization': 'Bearer '+ cookies.get('access_token'),
    'Accept' : 'application/json',
    'Content-Type': 'application/json'
  }
};

import BossMarketInfoModifyView from "@/components/boss/BossMarketInfoModifyView";
import axios from "axios";
import {API_BASE_URL} from "@/constant/login";
export default {
  name: "BossMarketInfoModify",
  components: {BossMarketInfoModifyView},
  props: {
    bossNo: {
      type: String,
      default: ''
    }
  },
  data(){
    return {
      id: cookies.get("id")
    }
  },
  methods: {
    modifyMarketInfo(payload){
      const {phoneNumber, marketInfo, marketHomePage, startTime, endTime, address, detailAddress, lat, lng} = payload;
      let id = this.id;

      axios.post(API_BASE_URL+'/boss/modifyMarketInfo',{id, phoneNumber, marketInfo, marketHomePage, startTime, endTime, address, detailAddress, lat, lng}, config)
          .then((res) => {
            console.log(res)

            setTimeout(() => {
              this.$router.push({name: 'BossPage'})
            }, 120)
          })
          .catch(() => {
            alert('에러')
          })
    }

  },
  created() {
  }
}
</script>

<style scoped>

</style>