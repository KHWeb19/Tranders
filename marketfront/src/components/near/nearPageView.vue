<template>
  <div id="near_page_box" style="position: relative">

      <v-card height="700px" width="100%">
        <div style="width: 100%" class="pa-4">
          <div style="font-size: 35px; font-weight: bold">이웃과 함께 만드는 당근 지도</div>
          <div style="font-size: 20px;">이웃들이 소개한 맛집을 지도에 담았어요</div>

          <div class="kmap" ref="map" style="margin-top: 25px"></div>
        </div>
      </v-card>
  </div>
</template>

<script>
let kakao = window.kakao;

export default {
  name: "nearPageView",
  data(){
    return {
      mapInstance: null,
      markInstance: null,
    }
  },
  methods: {

  },
  mounted() {
    let container = this.$refs.map;

    this.mapInstance = new kakao.maps.Map(container, {
      center: new kakao.maps.LatLng(37.5666805, 126.9784147),
      level: 5,
    });

    this.markInstance = new kakao.maps.Marker({
      position: new kakao.maps.LatLng(37.5666805, 126.9784147),
      //clickable: true
    })

    this.markInstance.setMap(this.mapInstance);
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
.kmap {
  width: 100%;
  height: 500px;
}
</style>