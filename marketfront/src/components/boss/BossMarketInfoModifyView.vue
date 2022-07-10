<template>
  <div id="my_page_box" style="position: relative; border: 1px solid rgba(0,0,0,0.4); height: auto;">
    <div style="background-color: rgba(43,152,48,0.93); height: 60px; display: flex">
      <span style="width: 10%; padding: 10px 0 10px 20px;"><v-btn icon @click="back"><v-icon x-large>mdi-chevron-left</v-icon></v-btn></span>
      <span style="width: 80%; text-align: center; font-size: 25px; font-weight: bold" class="pa-2">정보 관리</span>
      <span style="width: 10%; text-align: end; padding: 10px 5px 20px 0; margin-right: 10px"><v-btn text style="font-size: 22px" @click="saveMarketInfo">변경 완료</v-btn></span>
    </div>

    <div class="pa-4">
      <span style="font-size: 20px; font-weight: bold">주소</span>

      <div style="display: flex; padding: 10px 0 10px 0; width: 100%; height: 70px;">
        <v-text-field id="addrInputText" disabled readonly solo style="width: 50%; font-size: 20px" v-model="address1"></v-text-field>
        <v-btn @click="search" style="width: 10%; margin-left: 10px; height: auto" text><v-icon>mdi-magnify</v-icon></v-btn>
      </div>

      <div style="display: flex; padding: 10px 0 10px 0; width: 100%; height: 70px;" v-if="addAddress">
        <v-text-field label="상세주소 입력해주세요." v-model="address2" solo style="width: 60%; font-size: 20px"></v-text-field>
        <span style="margin-left: 20px; padding-top: 15px; opacity: 0.5">* 주소를 입력하면 활동 지역이 주소에 따라 변경돼요.</span>
      </div>

      <div class="kmap" ref="map"></div>

    </div>

    <div class="pa-4">
      <span style="font-size: 20px; font-weight: bold">전화번호</span>

      <div style="display: flex; padding: 10px 0 10px 0; width: 100%; height: 70px;">
        <v-text-field solo style="font-size: 20px" v-model="phoneNumber"></v-text-field>
      </div>
    </div>

    <div class="pa-4" style="height: 220px;">
      <span style="font-size: 20px; font-weight: bold">소개 문구</span>

      <div style="display: flex; padding: 10px 0 10px 0; width: 100%; height: 70px;">
        <v-textarea label="동네 가게 검색 결과에 이 소개 문구가 함께 보여요. 가게나 서비스의 강점이 드러나게 작성해보세요." v-model="marketInfo" solo style="font-size: 20px"></v-textarea>
      </div>
    </div>

    <div class="pa-4">
      <span style="font-size: 20px; font-weight: bold">영업 시간</span>

      <div v-if="!timeCheck" style="display: flex; padding: 10px 0 10px 0; width: 100%; height: 70px;">
        <v-text-field solo style="font-size: 20px; width: 40%" v-model="startTime" disabled readonly></v-text-field>
        <span>&nbsp; ~ &nbsp;</span>
        <v-text-field solo style="font-size: 20px; width: 40%" v-model="endTime" disabled readonly></v-text-field>
      </div>

      <div style="display: flex; padding: 10px 0 10px 0; width: 100%; height: 70px;">
        <v-btn block depressed color="success" height="50" type="submit" style="font-size: 20px" @click="businessHoursDialog = true"><h3><b>입력하기</b></h3></v-btn>
      </div>
    </div>

    <div class="pa-4">
      <span style="font-size: 20px; font-weight: bold">홈페이지</span>

      <div style="display: flex; padding: 10px 0 10px 0; width: 100%; height: 70px;">
        <v-text-field label="URL 주소를 입력하세요." v-model="marketHomePage" solo style="font-size: 20px"></v-text-field>
      </div>
    </div>

    <v-dialog width="900" v-model="businessHoursDialog">
      <v-card style="width: 900px; position: relative">
        <v-row justify="center" style="width: 500px; padding-bottom: 10px">
          <v-card-title>영업 시간을 입력해주세요</v-card-title>
        </v-row>

        <v-row style="height: 500px; width: 900px;" justify="center">
          <v-col style="width: 290px; flex: 0 1 auto;">
            <h2>Start:</h2>
            <v-time-picker v-model="startTime" :max="endTime"></v-time-picker>
          </v-col>
          <v-col style="width: 290px; flex: 0 1 auto;">
            <h2>End:</h2>
            <v-time-picker v-model="endTime" :min="startTime"></v-time-picker>
          </v-col>
        </v-row>

        <v-row justify="end" no-gutters>
          <v-card-actions>
            <v-btn class="light-green lighten-3" @click="saveBusinessHours">Save</v-btn>
            <v-btn @click="businessHoursDialog = false">Close</v-btn>
          </v-card-actions>
        </v-row>
      </v-card>
    </v-dialog>

    <div>
      <span id="lat"></span>
      <span id="lng"></span>
    </div>
  </div>
</template>

<script>

import {mapActions, mapState} from "vuex";
import cookies from "vue-cookies";

let daum = window.daum;

export default {
  name: "BossMarketInfoModifyView",
  props: ['bossNo'],
  data(){
    return {
      memberNo: cookies.get("memberNo"),
      addFiled: '',
      contact: null,
      businessHoursDialog: false,
      timeCheck: false,
      startTime: null,
      endTime: null,
      address1: '',
      address2: '',
      phoneNumber: '',
      marketInfo: '',
      marketHomePage: '',
      time: '',
      x: 0,
      y: 0,
      addAddress: false
    }
  },
  methods: {
    ...mapActions(['fetchBossPage']),
    back() {
      this.$router.push({name: 'BossPage'})
    },
    saveMarketInfo() {
      const { phoneNumber, marketInfo, marketHomePage, startTime, endTime} = this;
      let address = '';
      let detailAddress = '';
      let lat = '';
      let lng = '';
      if(this.addAddress === true) {
        address = document.getElementById("addrInputText").value;
        detailAddress = this.address2;
        lat = document.getElementById("lat").value
        lng = document.getElementById("lng").value
      }else {
        address = this.address1;
        lat = this.y;
        lng = this.x;
      }

      console.log(lat +", "+lng)
      this.$emit('modifyMarketInfo', {phoneNumber, marketInfo, marketHomePage, startTime, endTime, address, detailAddress, lat, lng})
    },
    test(test){
      this.address1 = test;
      console.log(this.address1)
    },
    search() {
      let container = this.$refs.map;

      let mapInstance = new daum.maps.Map(container, {
        center: new daum.maps.LatLng(this.y, this.x),
        level: 2,
      });

      let markInstance = new daum.maps.Marker({
        position: new daum.maps.LatLng(this.y, this.x),
        map: mapInstance
      })

      let geocoder = new daum.maps.services.Geocoder();
      this.addAddress = true;
      new daum.Postcode({
        oncomplete: function (data) {
          let addr = data.address;

          document.getElementById("addrInputText").value = addr
          // 주소로 상세 정보를 검색
          geocoder.addressSearch(data.address, function (results, status) {
            // 정상적으로 검색이 완료됐으면
            if (status === daum.maps.services.Status.OK) {

              let result = results[0]; //첫번째 결과의 값을 활용

              // 해당 주소에 대한 좌표를 받아서
              let coords = new daum.maps.LatLng(result.y, result.x);
              document.getElementById("lat").value = result.y
              document.getElementById("lng").value = result.x
              // 지도를 보여준다.
              container.style.display = "block";
              mapInstance.relayout();
              // 지도 중심을 변경한다.
              mapInstance.setCenter(coords);
              // 마커를 결과값으로 받은 위치로 옮긴다.
              markInstance.setPosition(coords)
            } else {
              alert('에러')
            }
          });
        }
      }).open();
    },
    saveBusinessHours(){
      console.log(this.startTime)
      console.log(this.endTime)

      this.businessHoursDialog = false;
      //this.timeCheck = true;
    }
  },
  computed: {
    ...mapState(['boss'])
  },
  mounted() {
    this.fetchBossPage(this.memberNo);

    let container = this.$refs.map;

    let mapInstance = new kakao.maps.Map(container, {
      center: new kakao.maps.LatLng(this.y, this.x),
      level: 2,
    });

    let markInstance = new kakao.maps.Marker({
      position: new kakao.maps.LatLng(this.y, this.x),
      //clickable: true
    })

    markInstance.setMap(mapInstance);

  },
  created() {
    this.phoneNumber = this.boss.phoneNumber;
    this.address1 = this.boss.address + this.boss.detailAddress;
    this.marketInfo = this.boss.marketInfo;
    this.marketHomePage = this.boss.marketHomePage;
    this.startTime = this.boss.startTime;
    this.endTime = this.boss.endTime;
    this.y = this.boss.lat;
    this.x = this.boss.lng;
  }
}
</script>

<style scoped>
#my_page_box{
  align-items: center;
  width: 100%;
  max-width: 1050px;
  margin: 0 auto;
  padding: 0 0 0 0; /*위, 오른쪽, 아래, 왼쪽 */
}

.kmap {
  width:300px;
  height:300px;
  margin-top:10px;
}
</style>