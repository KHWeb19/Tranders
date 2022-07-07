<template>
  <div id="near_page_box">
      <!-- <v-card height="700px" width="100%"> -->
        <div style="width: 100%">
          <div style="font-size: 35px; font-weight: bold">이웃과 함께 만드는 오이 지도</div>
          <div style="font-size: 20px;">이웃들이 소개한 맛집을 지도에 담았어요</div>
          <div class="map_wrap" style="position: relative; margin-top: 25px">
            <div class="infoBox" id="infoBox1" ref="mapDiv" style="border-radius: 8px; display: none; padding-top: 5px; padding-left: 5px">
              <div style="display: flex; align-items: center; padding-left: 8px; height: 30px">
                <div style="font-size: 20px; font-weight: bold; padding-right: 10px; width: 30%"><input v-model="storeName" readonly style="width: 100%;"></div>
                <div style="font-size: 15px; font-weight: normal; width: 40%"> <input v-model="category" style="width: 100%" readonly> </div>
              </div>

              <div> <!--후기 작성한 사람의 정보 -->
                <div style="display: flex; align-items: center;">
                  <div id="noReviewMessage" style="padding-top: 5px; padding-left: 10px">후기가 없어요! 작성해보세요!</div>
                  <img id="imgId1" height="30" width="30" style="margin-left: 5px; margin-right: 5px; display: none" src="" alt="">
                  <div style="font-size: 15px; width: 25%; display: flex;">
                    <input v-model="profileName" readonly style="width: 50px; height: 20px">
                    <input v-model="region" readonly style="width: 70px; font-weight: lighter; font-size: 12px">
                  </div>

                </div>
                <div style="padding-left: 8px; padding-top: 10px; height: 50px;">
                  <input v-model="content" style="width: 170px; font-size: 18px" readonly>
                </div>
              </div>

              <div style=" height: 35px;">
                <v-chip-group id="chipGroup" multiple v-model="selection" style="display: none;" active-class="light-green lighten-2 white--text">
                  <v-chip v-for="(tag, index) in reviewTag" :key="index" x-small style="font-size: 11px; height: 20px">
                    {{ tag }}
                  </v-chip>
                </v-chip-group>
              </div>

              <div style="padding-left: 8px; padding-right: 8px; display: flex; width: 100%;">
                <a id="bossLink" href="/near" style="width: 50%;">
                  <div style="width: 100%;"><input v-model="reviewCount" readonly style="width: 50px;"> <v-icon>mdi-chevron-right</v-icon></div>
                </a>
                <div style="width: 50%; display: flex; justify-content: end;"> <v-icon>mdi-bookmark-outline</v-icon></div>
              </div>

            </div>


            <div class="infoBox" id="infoBox2" style="border-radius: 8px; display: none; padding-top: 5px; padding-left: 5px">
              <div style="display: flex; align-items: center; padding-left: 8px; height: 30px">
                <div style="font-size: 20px; font-weight: bold; padding-right: 10px; width: 30%"><input v-model="storeName" readonly style="width: 100%;"></div>
                <div style="font-size: 15px; font-weight: normal; width: 40%"> <input v-model="category" style="width: 100%" readonly> </div>
              </div>

              <div> <!--후기 작성한 사람의 정보 -->
                <div style="display: flex; align-items: center;">
                  <img id="imgId2" height="30" width="30" style="margin-left: 5px; margin-right: 5px" src="" alt="">
                  <div style="font-size: 15px; width: 25%; display: flex;">
                    <input v-model="profileName" readonly style="width: 50px; height: 20px">
                    <input v-model="region" readonly style="width: 70px; font-weight: lighter; font-size: 12px">
                  </div>

                </div>
                <div style="padding-left: 8px; padding-top: 10px; height: 40px;">
                  <input v-model="content" style="width: 170px; font-size: 18px" readonly>
                </div>
              </div>

              <div style=" height: 43px;">
              </div>

              <div style="padding-left: 8px; padding-right: 8px; display: flex; width: 100%;">
                <a id="nearLink" href="/near" style="width: 50%;">
                  <div style="width: 100%;"><input v-model="reviewCount" readonly style="width: 50px;"> <v-icon>mdi-chevron-right</v-icon></div>
                </a>
                <div style="width: 50%; display: flex; justify-content: end;"> <v-icon>mdi-bookmark-outline</v-icon></div>
              </div>

            </div>
            <div class="kmap" ref="map" style=" width: 100%; height:100%; position:absolute; overflow:hidden;"></div>

          </div>
        </div>

      <!-- </v-card> -->
  </div>
</template>

<script>
import cookies from "vue-cookies";
import {mapActions, mapState} from "vuex";

let kakao = window.kakao;

export default {
  name: "NearPageView",
  data(){
    return {
      memberNo: cookies.get('memberNo'),
      id: cookies.get('id'),
      mapInstance: null,
      markInstance: null,
      nearMapPlaceName: [],
      nearMapMarkLat: [],
      nearMapMarkLng: [],
      nearMapCategory: [],
      nearMapRegion: [],
      nearMapBossNo: [],
      nearMapBossReview: [],
      nearMemberName: [],
      nearMemberContent: [],
      nearMemberRegion: [],
      nearMemberProfile: [],
      nearMemberState: [],
      nearMapCheck: [],
      infowindow: null,
      placeName: null,
      data: '',
      overlay: null,
      markArray: [],
      selection: [],
      reviewTag: [
        '친절해요',
        '가격이 저렴해요',
        '만족스러워요',
        '아쉬워요'
      ],
      subject: [
        '함께해요',
        '동네질문',
        '동네맛집',
        '동네소식',
        '취미생활',
        '분실/실종센터',
        '해주세요',
        '일상'
      ],
      content: '',
      storeName: '',
      category: '',
      reviewCount: '',
      profileName: '',
      region: '',
      test: true,
      commCategory: []
    }
  },
  methods: {
    ...mapActions(['fetchMyRegion']),
    ...mapActions(['fetchShowNearMap']),
    ...mapActions(['fetchNearCommunityMap']),
    ...mapActions(['fetchNearComm']),
    ...mapActions(['fetchNearReview']),
    parsingMap(){
      for(let i = 0; i < this.nearMap.length; i++){
        this.nearMapPlaceName.push(this.nearMap[i].placeName)
        this.nearMapMarkLat.push(this.nearMap[i].lat)
        this.nearMapMarkLng.push(this.nearMap[i].lng)
        this.nearMapRegion.push(this.nearMap[i].region)
        this.nearMapCategory.push(this.nearMap[i].category)
        this.nearMapBossNo.push(this.nearMap[i].bossNo)
        this.nearMapBossReview.push(this.nearMap[i].reviewCount)
        this.nearMapCheck.push(true);

        if(this.nearReview[i] !== null){
          this.nearMemberName.push(this.nearReview[i].name);
          this.nearMemberRegion.push(this.nearReview[i].region);
          this.nearMemberContent.push(this.nearReview[i].content);
          this.nearMemberProfile.push(this.nearReview[i].imageName);
        }else {
          this.nearMemberName.push('');
          this.nearMemberRegion.push('');
          this.nearMemberContent.push('');
          this.nearMemberProfile.push('')
        }

      }
      //alert(this.nearMapMarkLat.length);
    },
    parsingCommMap(){
      //let num = this.nearMap.length;
      for(let i = 0; i < this.nearMapComm.length; i++){
        this.nearMapPlaceName.push(this.nearMapComm[i].placeName);
        this.nearMapMarkLat.push(this.nearMapComm[i].lat)
        this.nearMapMarkLng.push(this.nearMapComm[i].lng)
        this.nearMapRegion.push(this.nearMapComm[i].region)
        this.nearMapCategory.push(this.nearMapComm[i].category)
        this.nearMapBossNo.push(this.nearMapComm[i].nearNo)
        this.nearMapBossReview.push(this.nearMapComm[i].reviewCount)
        this.nearMapCheck.push(false);

        if(this.nearComm[i] !== null){
          this.nearMemberName.push(this.nearComm[i].name);
          this.nearMemberRegion.push(this.nearComm[i].region);
          this.nearMemberContent.push(this.nearComm[i].content);
          this.nearMemberProfile.push(this.nearComm[i].imageName);
        }else {
          this.nearMemberName.push('');
          this.nearMemberRegion.push('');
          this.nearMemberContent.push('');
          this.nearMemberProfile.push('')
        }
      }
    },
    showAt(i){
      let infoBox1 = document.getElementById('infoBox1');
      let infoBox2 = document.getElementById('infoBox2');

      let images1 = document.getElementById('imgId1')
      let images2 = document.getElementById('imgId2')

      let chip = document.getElementById('chipGroup')
      let noReviewMessage = document.getElementById('noReviewMessage')

      let categoryName = this.nearMapCategory[i];
      let storeName = this.nearMapPlaceName[i];
      let no = this.nearMapBossNo[i];
      let count = this.nearMapBossReview[i]

      let name = this.nearMemberName[i];
      let region = this.nearMemberRegion[i];
      let content = this.nearMemberContent[i];

      let profile = this.nearMemberProfile[i];

      let state = this.nearMemberState[i];

      let check = this.nearMapCheck[i];

      let bossLink = document.getElementById('bossLink');
      let nearLink = document.getElementById('nearLink');

      kakao.maps.event.addListener(this.markInstance, 'click', () => {

        if(check){
          this.profileName = '';
          this.region = '';
          this.content = '';
          this.selection = '';
          //alert(bossLink)
          bossLink.href = '/bossRead?bossNo='+no;

          infoBox1.style.display = 'block';
          noReviewMessage.style.display = 'block';
          images1.style.display = 'none';
          chip.style.display = 'none';
          infoBox2.style.display = 'none';
        }else {
          this.profileName = '';
          this.region = '';
          this.content = '';
          this.selection = '';

          nearLink.href = '/nearRead?nearNo='+no;

          infoBox1.style.display = 'none';
          infoBox2.style.display = 'block';
          noReviewMessage.style.display = 'block';
        }

        this.storeName = storeName;
        this.category = categoryName;
        this.reviewCount = count + '개 후기';

        if(count !== 0 ){
          noReviewMessage.style.display = 'none';
          images1.style.display = 'block';
          chip.style.display = 'block';
          this.profileName = name;
          this.region = region;
          this.content = content;
          if(check) {
            images1.src = require(`../../assets/profile/${profile}`)
            this.selection = state;
          }else {
            images2.src = require(`../../assets/profile/${profile}`)
            this.commCategory = state;
          }
        }

      });
    },
    parsingState() {
      for (let i = 0; i < this.nearReview.length; i++) {
        if (this.nearReview[i] !== null) {
          let num = this.nearReview[i].state.split(',');
          let arr = [];

          for (let j = 0; j < num.length; j++) {
            arr.push(parseInt(num[j]));
          }
          this.nearMemberState.push(arr);

        } else {
          this.nearMemberState.push(null);
        }
      }
    }

      // state 설정

  },
  async mounted() {
    await this.fetchMyRegion(this.id)
    await this.fetchShowNearMap()
    await this.fetchNearCommunityMap()
    await this.fetchNearReview()
    await this.fetchNearComm()
    await this.parsingMap()
    await this.parsingCommMap()
    await this.parsingState()

    let container = this.$refs.map;

     this.mapInstance = new kakao.maps.Map(container, {
      center: new kakao.maps.LatLng(this.mapOption.lat, this.mapOption.lng),
      level: 5,
    });

    for(let i =0; i < this.nearMapMarkLat.length; i++) {
      console.log(this.nearMapMarkLat[i])
      this.markInstance = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(this.nearMapMarkLat[i], this.nearMapMarkLng[i]),
        //clickable: true
      })

      this.markInstance.setMap(this.mapInstance);

      this.showAt(i);

    }
  },
  computed: {
    ...mapState(['mapOption']),
    ...mapState(['nearMap']),
    ...mapState(['nearMapComm']),
    ...mapState(['nearReview']),
    ...mapState(['nearComm']),
  }
}
</script>

<style scoped>
#near_page_box{
    border-left: 1px solid #e9ecef;
    border-right: 1px solid #e9ecef;
    width: 1200px;
    margin: 0 auto;
    margin-bottom: 20px;
    padding: 40px;
    line-height: 24px;
    background: #fff;
}
/* #near_page_box{
  display: flex;
  align-items: center;
  width: 100%;
  max-width: 1250px;
  margin: 0 auto;
  padding: 15px 7px 0 7px; 
  position: relative
} */
.map_wrap a, .map_wrap a:hover, .map_wrap a:active{
  color:#000;text-decoration: none;
}
.map_wrap {
  position:relative;
  width:100%;
  height:500px;
}
.infoBox {
  position:absolute;
  top: 50px;
  left:0;
  bottom:0;
  width:400px;
  height: 180px;
  margin:10px 0 30px 10px;
  padding:5px;
  overflow-y:auto;
  background:rgba(255, 255, 255, 0.9);
  z-index: 3;
  font-size:12px;
  border-radius: 10px;
}
.infoBox hr {
  display: block;
  height: 1px;
  border: 0;
  border-top: 2px solid #5F5F5F;
  margin:3px 0;
}
.infoBox .option p {
  margin:10px 0;
}
.infoBox .option button {
  margin-left:5px;
}
</style>