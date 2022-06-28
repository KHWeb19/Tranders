<template>
  <div id="near_page_box" style="position: relative">

      <v-card height="700px" width="100%">
        <div class="pa-4" style="width: 100%">
          <div style="font-size: 35px; font-weight: bold">이웃과 함께 만드는 당근 지도</div>
          <div style="font-size: 20px;">이웃들이 소개한 맛집을 지도에 담았어요</div>

          <div class="map_wrap" style="position: relative; margin-top: 25px">
            <div id="menu_wrap" ref="mapDiv" style="border-radius: 8px;">
              <div class="map_popup" style="display: flex; align-items: center; padding-left: 8px; height: 30px">
                <div id="store_name" ref="store_name" style="font-size: 20px; font-weight: bold; padding-right: 10px"></div>
                <div style="font-size: 15px; font-weight: lighter;">술집</div>
              </div>

              <div> <!--후기 작성한 사람의 정보 -->
                <div style="display: flex; align-items: center">
                  <v-img contain max-height="28" max-width="28px" style="padding-right: 10px" src="@/assets/profile/Tranders_base_profile_img.png"></v-img>
                  <div ref="profile_name" style="padding-right: 10px; font-size: 15px"> </div>
                  <div style="font-weight: lighter"> 봉천동 </div>
                </div>
                <div style="padding-left: 8px">
                  가끔 혼술하러 가는데 좋아요
                </div>
              </div>

              <div style="padding-left: 8px; padding-right: 8px; padding-top: 15px; display: flex; width: 100%;">
                <div style="width: 50%;">2개 후기 <v-icon>mdi-chevron-right</v-icon></div>
                <div style="width: 50%; display: flex; justify-content: end;"><v-icon>mdi-bookmark-outline</v-icon></div>
              </div>

              <div ref="test">

              </div>
            </div>
            <div class="kmap" ref="map" style=" width: 100%; height:100%; position:absolute; overflow:hidden;"></div>

          </div>


        </div>

        <v-btn @click="addMap">add</v-btn>
      </v-card>
  </div>
</template>

<script>
import cookies from "vue-cookies";
import {mapActions, mapState} from "vuex";
import axios from "axios";
import {API_BASE_URL} from "@/constant/login";

const config = {
  headers: {
    'Authorization': 'Bearer '+ cookies.get('access_token'),
    'Accept' : 'application/json',
    'Content-Type': 'application/json'
  }
};

let kakao = window.kakao;

export default {
  name: "nearPageView",
  data(){
    return {
      memberNo: cookies.get('memberNo'),
      mapInstance: null,
      markInstance: null,
      nearMapPlaceName: [],
      nearMapMarkLat: [],
      nearMapMarkLng: [],
      infowindow: null,
      placeName: null,
      data: '',
      overlay: null,
      markArray: [],
    }
  },
  methods: {
    ...mapActions(['fetchShowNearMap']),
    test(){
      for(let i = 0; i < this.nearMap.length; i++){
        console.log(i)
        this.nearMapPlaceName.push(this.nearMap[i].placeName)
        this.nearMapMarkLat.push(this.nearMap[i].lat)
        this.nearMapMarkLng.push(this.nearMap[i].lng)

        console.log(this.nearMapPlaceName[i])
      }
    },
    closeOverlay() {
      this.overlay.setMap(null);
    },
    test2(){
      return function () {
      }
    },
    addMap(){
      axios.post(API_BASE_URL+'/near/addMap', {}, config)
          .catch(() => {
            alert('에러')
          })
    // },
    // makeOverListener(map, marker, infowindow) {
    //   return function() {
    //     infowindow.open(map, marker);
    //   };
    // },
    // showAt(){
    //   this.infowindow = new kakao.maps.InfoWindow({
    //     content: '<v-card><div id="menu_wrap" style="border-radius: 8px; ">'+
    //
    //         '<div style="display: flex; align-items: center; padding-left: 8px; height: 30px">'+
    //         '<div style="font-size: 20px; font-weight: bold; padding-right: 10px">오이 술집</div>'+
    //         '<div style="font-size: 15px; font-weight: lighter;">술집</div>'+
    //         '</div>'+
    //
    //         '<div>'+ //<!-- 후기 작성한 사람의 정보 -->
    //         '<div style="display: flex; align-items: center">'+
    //         '<v-img contain max-height="28" max-width="28px" style="padding-right: 10px" src="@/assets/profile/Tranders_base_profile_img.png"></v-img>'+
    //         '<div style="padding-right: 10px; font-size: 15px"> 오이 </div>'+
    //         '<div style="font-weight: lighter"> 봉천동 </div>'+
    //         '</div>'+
    //         '<div style="padding-left: 8px">'+
    //         '가끔 혼술하러 가는데 좋아요'+
    //         '</div>'+
    //         '</div>'+
    //
    //         '<div style="padding-left: 8px; padding-right: 8px; padding-top: 15px; display: flex; width: 100%;">'+
    //         '<div style="width: 50%;">2개 후기 <v-icon>mdi-chevron-right</v-icon></div>'+
    //         '<div style="width: 50%; display: flex; justify-content: end;"><v-icon>mdi-bookmark-outline</v-icon></div>'+
    //         '</div>'+
    //
    //         '</div>',
    //     removable : true,
    //   });
    //
    //   /*kakao.maps.event.addListener(this.markerInstance, 'click', this.makeOverListener(map, this.markInstance, this.infowindow));*/
    //   kakao.maps.event.addListener(this.markInstance, 'click', this.makeOverListener(this.mapInstance, this.markInstance, this.infowindow));
    },
  },
  async mounted() {
    await this.fetchShowNearMap()
    await this.test()
    let container = this.$refs.map;

    let mapInstance = new kakao.maps.Map(container, {
      center: new kakao.maps.LatLng(this.nearMapMarkLat[3], this.nearMapMarkLng[3]),
      level: 5,
    });

    for(let i =0; i < this.nearMapMarkLat.length; i++) {
      let markInstance = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(this.nearMapMarkLat[i], this.nearMapMarkLng[i]),
        //clickable: true
      })

      markInstance.setMap(mapInstance);

      let profileName = this.$refs.profile_name;
      let storeName = document.getElementById('store_name')

      kakao.maps.event.addListener(markInstance, 'click', function () {
        profileName.appendChild(document.createTextNode('test'));
        storeName.appendChild(document.createTextNode('오이술집'));
      });


      //this.overlay.setContent
      //this.showAt();
    }


  },
  computed: {
    ...mapState(['nearMap'])
  }
}
</script>

<style scoped>
#near_page_box{
  display: flex;
  align-items: center;
  width: 100%;
  max-width: 1250px;
  margin: 0 auto;
  padding: 15px 7px 0 7px; /*위, 오른쪽, 아래, 왼쪽 */
}
.map_wrap a, .map_wrap a:hover, .map_wrap a:active{
  color:#000;text-decoration: none;
}
.map_wrap {
  position:relative;
  width:100%;
  height:500px;
}
#menu_wrap {
  position:absolute;
  top:0;
  left:0;
  bottom:0;
  width:250px;
  height: 480px;
  margin:10px 0 30px 10px;
  padding:5px;
  overflow-y:auto;
  background:rgba(255, 255, 255, 0.8);
  z-index: 3;
  font-size:12px;
  border-radius: 10px;
}
#menu_wrap hr {
  display: block;
  height: 1px;
  border: 0;
  border-top: 2px solid #5F5F5F;
  margin:3px 0;
}
#menu_wrap .option p {
  margin:10px 0;
}
#menu_wrap .option button {
  margin-left:5px;
}
</style>