<template>
  <div>
    <boss-market-info-view @saveMarketInfo="saveMarketInfo"></boss-market-info-view>
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

import BossMarketInfoView from "@/components/boss/BossMarketInfoView";
import axios from "axios";
import {API_BASE_URL} from "@/constant/login";
export default {
  name: "BossMarketInfo",
  components: {BossMarketInfoView},
  data(){
    return {
      id: cookies.get("id")
    }
  },
  methods: {
    saveMarketInfo(payload) {
      const {phoneNumber, marketInfo, marketHomePage, startTime, endTime, address, lat, lng} = payload;
      let id = this.id;

      axios.post(API_BASE_URL+'/boss/saveMarketInfo',{id, phoneNumber, marketInfo, marketHomePage, startTime, endTime, address, lat, lng}, config)
          .then((res) => {
            console.log(res)
            setTimeout(() => {
              this.$router.push({name: 'BossPage'})
            }, 80)
          })
          .catch(() => {
            alert('에러')
          })
    }
  }
}
</script>

<style scoped>

</style>