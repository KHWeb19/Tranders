<template>
<div align="left">
  &nbsp;
  <v-row>
    <v-col cols="3">  
      <v-icon>mdi-magnify</v-icon>
      <input type="text" style="width:160px; border: 0; background-color: rgb(191, 246, 201)" value="" placeholder="장소를 검색해주세요." @keyup.enter="searchPlace"/>
      <div class="results">
        <div class="place" v-for="rs in search.results" :key="rs.id">
          <h4 style="color:rgb(38,140,250);"><a href="#" @click="placeRegister1((rs.place_url),$event); return false;" style="text-decoration:none">{{rs.place_name}}</a></h4>
          <div class="addr">{{rs.road_address_name}}
          </div>
        </div>
      </div>
    </v-col>
    <v-col cols="9">
      <v-text-field style="width:230px" v-model="placeName"/><v-btn @click="registerAlert(checkBatchimEnding)" class="rsBtn" color="light green accent-4" style="box-shadow:none" dark fab small><v-icon>mdi-send</v-icon></v-btn>
      <section class="cKakaoMap">   
        <div id="map">
        </div>
      </section>
    </v-col>
  </v-row>
</div>
</template>

<script>
import EventBus from '@/eventBus.js'

export default {
  data() {
    return {
      mapOption: {
        center: {
          lat: 37.4990068197872, 
          lng: 127.03284079061625,
        },
        level: 3,
      },
      search: {
        keyword: null,
        pgn: null,
        results: [],
      },
      placeName:'',
    }
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      setTimeout(() => { this.initMap() }, 100)
    } else {
      const script = document.createElement('script');
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
      'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=a70ab818e3db899b3fc9b8f9e1e181f3&libraries=services';
      document.head.appendChild(script);
    }
  },
  created () {
        EventBus.$on('placeRegister1', (payload) => {
            this.placeUrl = payload[0]
            this.placeName = payload[1]
            console.log(payload) 
        })
    },
  methods: {
    initMap() {
      var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
      mapOption = {
        center: new kakao.maps.LatLng(37.4990068197872, 127.03284079061625), // 지도의 중심좌표
        level: 3, // 지도의 확대 레벨
        };          
      var map = new kakao.maps.Map(mapContainer, mapOption);
      var positions = [
        {
          latlng: new kakao.maps.LatLng(37.4990068197872, 127.03284079061625),
        },
      ];

      // 마커를 생성합니다
      positions.forEach(function(pos) {
        var marker = new kakao.maps.Marker({
          position: pos.latlng, // 마커의 위치
        });
      //마커가 지도 위에 표시 되도록 설정합니다.
        marker.setMap(map);
      });
    },
    searchPlace(e) {     
    // 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
      const keyword = e.target.value.trim();
      if (keyword.length === 0){
        return;
      }

    // 장소 검색 객체를 생성합니다
      const ps = new window.kakao.maps.services.Places();

    // 키워드로 장소를 검색합니다
      ps.keywordSearch(keyword, (data, status, pgn) => {
        if (status === kakao.maps.services.Status.OK) {
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
          var bounds = new kakao.maps.LatLngBounds();
          for (var i=0; i<data.length; i++) {
            new kakao.maps.Marker(data[i]);    
            bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
          }      
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
          map.setBounds(bounds);
        }
        this.search.keyword = keyword;
        this.search.pgn= pgn;
        this.search.results = data;
        for (i=0; i<data.length; i++) {
          var positions = [
            {
              content: data[i].place_name,
              url: data[i].place_url,
              latlng: new kakao.maps.LatLng(data[i].y, data[i].x),
            },
          ]      
        // 마커를 생성합니다
          positions.forEach(function(pos) {
            var marker = new kakao.maps.Marker({
              map: map, // 마커를 표시할 지도
              position: pos.latlng, // 마커의 위치
            });       
        // 마커에 마우스오버 이벤트를 등록합니다
            kakao.maps.event.addListener(marker, 'mouseover', function() {

        // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
              infowindow.open(map, marker);
            });

        // 마커에 마우스아웃 이벤트를 등록합니다
            kakao.maps.event.addListener(marker, 'mouseout', function() {

        // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다    
              infowindow.close();
            });

        // 마커에 클릭이벤트를 등록합니다
            kakao.maps.event.addListener(marker, 'click', function() {
              window.open(pos.url);
            });

            var infowindow = new kakao.maps.InfoWindow({              
              content : '<div style="width:105%; padding:5px;">'+  pos.content +  '</div>',
              // removable : true
              });             
              kakao.maps.event.addListener(marker, 'click', function() {
                infowindow.open(map, marker);
              });
          })
        }
      });           
      var mapContainer = document.getElementById('map'), // 지도를 표시할 div
      mapOption = {
        center: new kakao.maps.LatLng(37.564343, 126.947613), // 지도의 중심좌표
        level: 3, // 지도의 확대 레벨
        };       
      var map = new kakao.maps.Map(mapContainer, mapOption);
    },

    placeRegister1(placeUrl,$event) {
      const payload = [ placeUrl, $event.target.innerHTML ]
      const placeName = $event.target.innerHTML
      EventBus.$emit('placeRegister1', payload)
      console.log(placeName) 
      console.log(placeUrl)
    },

    checkBatchimEnding(word) {
      if (typeof word !== 'string') return null;
      var lastLetter = word[word.length - 1];
      var uni = lastLetter.charCodeAt(0);
      if (uni < 44032 || uni > 55203) return null;
      return (uni - 44032) % 28 != 0;
    },
    
    registerAlert(checkBatchimEnding){
      const word = this.placeName
      var adj = checkBatchimEnding(word)?'이':'가';
      console.log (`${word}${adj} 등록되었습니다.`);
      alert( this.placeName + adj + ' 등록되었습니다.') 
    }
  },
}
</script>


<style scoped>
.results{
  .place{
    padding: 8px;
  h4{
    margin:0;
  }; 
}
}
.KakaoMap {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
#map {
  width: 100%; 
  height: 725px;
  border: 1px #a8a8a8 solid;
}
.rsBtn {
    position: absolute;
    zoom:0.8;
    margin-top:-5%;
    margin-left:25%;
    float:left;
}
</style>