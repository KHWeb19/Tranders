<template>
  <v-container>
    <v-card width="100%">
      <div class="map_box" style="padding: 5px 20px 5px 20px;">
        <v-row justify="center">
          <v-card-title style="font-size: 30px;">내 동네 인증</v-card-title>
        </v-row>

        <v-row>
          <div class="map_wrap">
          <div class="kmap" ref="map" style="position: relative; overflow: hidden"></div>
            <div class="custom_typecontrol">
              <span @click="modifyLocation">
                <v-img src="@/assets/map/target.png"></v-img>
              </span>
            </div>

          </div>
        </v-row>

        <v-row justify="center" v-if="!isCheck">
          <v-card-text style="font-size: 25px;width: auto">
            내가 설정한 위치는 <span style="font-size: 30px; font-weight: bold;" class="light-green--text lighten-3">{{options.region}}</span> 입니다.
          </v-card-text>
        </v-row>

        <v-row justify="center" v-if="isCheck">
          <v-card-text style="font-size: 25px;width: auto">
            변경된 위치는 <span style="font-size: 30px; font-weight: bold;" class="light-green--text lighten-3">{{this.region}}</span> 입니다.
          </v-card-text>
        </v-row>

        <v-row justify="end" style="height: 60px">
          <v-btn class="light-green lighten-3" @click="saveRegion">동네 인증 완료</v-btn>
        </v-row>
      </div>
    </v-card>
  </v-container>
</template>

<script>
import axios from "axios";

let kakao = window.kakao;

export default {
  name: "MyVillageProofView",
  props: ["options"],
  data() {
    return {
      lat: 0,
      lng: 0,
      region: '',
      mapInstance: null,
      markInstance: null,
      isCheck: false
    }
  },
  methods: {
    saveRegion(){
      const {lat, lng, region} = this;
      this.$emit('modifyLocation', {lat, lng, region});
    },
    modifyLocation(){
      //alert('눌렀다!')
      navigator.geolocation.getCurrentPosition(this.showYourLocation, this.showErrorMsg);
    },
    showYourLocation(position) {  // 성공했을때 실행
      let y = position.coords.latitude;
      let x = position.coords.longitude;

      console.log(x);
      console.log(y);

      this.lat = y;
      this.lng = x;

      axios.post('http://127.0.0.1:5000/address-region', {x, y})
          .catch((res) => {
            console.log(res)
          })
          .then((res) => {
            console.log(res)
            this.region = res.data;
            this.isCheck = true;
          })
    },
    showErrorMsg(error) {
      alert('에러발생')
      switch(error.code) {
        case error.PERMISSION_DENIED:
          alert("이 문장은 사용자가 Geolocation API의 사용 요청을 거부했을 때 나타납니다!")
          break;

        case error.POSITION_UNAVAILABLE:
          alert("이 문장은 가져온 위치 정보를 사용할 수 없을 때 나타납니다!")
          break;

        case error.TIMEOUT:
          alert("이 문장은 위치 정보를 가져오기 위한 요청이 허용 시간을 초과했을 때 나타납니다!")
          break;
      }
    },
  },
  mounted() {
    let container = this.$refs.map;

    this.mapInstance = new kakao.maps.Map(container, {
      center: new kakao.maps.LatLng(this.options.lat, this.options.lng),
      level: 10,
    });

    this.markInstance = new kakao.maps.Marker({
      position: new kakao.maps.LatLng(this.options.lat, this.options.lng),
      //clickable: true
    })

    this.markInstance.setMap(this.mapInstance);

  },
  created() {

  },
  watch: {
    lat: function (){
      console.log('변경됨!')

    }
  }
}
</script>

<style scoped>
.kmap {
  width: 100%;
  height: 500px;
}
.map_wrap {
  position:relative;
  overflow:hidden;
  width:100%;
  height:350px;
}
.custom_typecontrol {
  position:absolute;
  top: 290px;
  right:10px;
  overflow:hidden;
  width:50px;
  height: 50px;
  margin:0;
  padding:0;
  z-index:1;
  font-size:12px;
}
.custom_typecontrol span {
  display:block;
  width: 40px;
  height: 40px;
  text-align:center;
  cursor:pointer;
}
/*.custom_typecontrol span v-img {*/
/*  width: 20px;*/
/*  height: 20px;*/
/*  padding:12px 0;*/
/*  border:none;*/
/*}*/
</style>