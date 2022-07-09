<template>
  <div>
    <div style="padding-bottom: 20px" class="home_style" v-if="boss.marketInfo === null">
      <span style="font-size: 30px; font-weight: bold;"> 정보 </span>
      <!--   정보가 없을 경우에     -->
      <div style="margin-top: 20px">
        <div style="border-style: dotted; height: 120px; border-radius: 8px; text-align: center; font-size: 23px; opacity: 0.5" class="pa-11">
          소개 문구가 없습니다.
        </div>

        <div style="padding-top: 30px">
          <span style="font-size: 25px; opacity: 0.5;"><v-icon x-large>mdi-map-marker-outline</v-icon> 주소를 추가해주세요.</span>
        </div>

        <div style="padding-top: 15px">
          <span style="font-size: 25px; opacity: 0.5;"><v-icon x-large>mdi-clock-time-five-outline</v-icon> 영업시간을 추가해 주세요.</span>
        </div>
      </div>

      <!--    정보가 있을 경우    -->
    </div>

    <div style="padding-bottom: 20px" class="home_style" v-else>
      <div style="display: flex">
        <span style="font-size: 30px; font-weight: bold; width: 30%"> 정보 </span>
      </div>
      <!--   정보가 없을 경우에     -->
      <div style="margin-top: 20px">
        <div style="height: auto; font-size: 23px;" class="pa-2">
          {{boss.marketInfo}}
        </div>

        <div class="kmap" ref="map" style="position: relative; overflow: hidden"></div>

        <div style="padding-top: 30px">
          <span style="font-size: 25px;"><v-icon x-large>mdi-map-marker-outline</v-icon> {{ boss.address }}</span>
        </div>

        <div style="padding-top: 15px">
          <span style="font-size: 25px;"><v-icon x-large>mdi-clock-time-five-outline</v-icon> {{boss.startTime}} ~ {{boss.endTime}}</span>
        </div>

        <div style="padding-top: 15px">
          <span style="font-size: 25px;"><v-icon x-large>mdi-earth</v-icon> {{boss.marketHomePage}}</span>
        </div>
      </div>

    </div> <!-- 정보 부분 -->


    <hr style="height: 15px; background-color: rgba(94,94,94,0.16); border: none"/>

    <div style="padding-bottom: 20px" class="home_style">
      <span style="font-size: 30px; font-weight: bold;"> 쿠폰 </span>
      <!--   정보가 없을 경우에     -->
      <div style="margin-top: 20px">
        <div v-if="!coupon || (Array.isArray(coupon) && coupon.length === 0)" style="height: 50px">
          <span style="font-size: 25px; opacity: 0.5;">아직 쿠폰이 없어요.</span>
        </div>

        <div v-else>
          <div style="padding-top: 20px">
            <div v-for="(coupon, index) in coupon" :key="index" style="width: 100%; padding: 10px 10px 10px 10px">
              <div v-if="state[index]" style="width: 100%; border-radius: 8px; border: 1px solid green; min-height: 150px; display: flex; align-items: center">
                <div style="width: 80%; padding: 5px 10px 5px 15px;">
                  <div style="display: flex; align-items: center">
                    <v-chip color="green" style="margin-right: 20px">발급중</v-chip>
                    <div style="color: green" v-if="coupon.couponMax">남은 쿠폰: {{coupon.couponMax - coupon.giveCoupon}}</div>
                  </div>

                  <div style="font-weight: bolder; font-size: 30px">
                    {{coupon.couponName}}
                  </div>

                  <div style="font-weight: lighter">
                    {{coupon.couponDate}}까지
                  </div>
                </div>

                <div style="background-color: #96cb96; width: 20%; min-height: 150px; border-top-right-radius: 8px; border-bottom-right-radius: 8px">
                  <div style="display: flex; flex-direction: column; justify-content: center; align-items: center; min-height: 150px" @click="getCoupon(coupon)">
                    <v-icon large>mdi-download-outline</v-icon>
                    <div style="font-size: 18px">쿠폰 받기</div>
                  </div>
                </div>
              </div>

              <div v-else style="width: 100%; background-color: rgba(145,145,145,0.37); border-radius: 8px; border: 1px solid black; min-height: 150px; display: flex; align-items: center">
                <div style="width: 80%; padding: 5px 10px 5px 15px;">
                  <div style="display: flex; align-items: center">
                    <v-chip style="margin-right: 20px">발급완료</v-chip>
                    <div style="color: black" v-if="coupon.couponMax">남은 쿠폰: {{coupon.couponMax - coupon.giveCoupon}}</div>
                  </div>

                  <div style="font-weight: bolder; font-size: 30px">
                    {{coupon.couponName}}
                  </div>

                  <div style="font-weight: lighter">
                    {{coupon.couponDate}}까지
                  </div>
                </div>

                <div style="background-color: #b9b9b9; width: 20%; min-height: 150px; border-top-right-radius: 8px; border-bottom-right-radius: 8px">
                  <div style="display: flex; flex-direction: column; justify-content: center; align-items: center; min-height: 150px">
                    <v-icon large>mdi-download-outline</v-icon>
                    <div style="font-size: 18px">쿠폰 발급 완료</div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

    </div> <!-- 쿠폰 부분 -->

    <hr style="height: 15px; background-color: rgba(94,94,94,0.16); border: none"/>

    <div style="padding-bottom: 20px" class="home_style">
      <span style="font-size: 30px; font-weight: bold;"> 가격 </span>
      <!--   메뉴가 없을 경우에     -->
      <div style="margin-top: 20px" v-if="bossMenu === null">
        <div style="padding-top: 30px">
          <span style="font-size: 25px; opacity: 0.5;">추가된 메뉴가 없습니다.</span>
        </div>
      </div>

      <div v-else>
        <div v-for="(menu, index) in bossMenu" :key="index" style="padding-top: 20px">
          <div style="display: flex">
            <div style="font-size: 25px; width: 20%">{{menu.menuName}}</div>
            <div style="width: 65%; display: flex; align-items: center"><hr style="border: 1px dotted rgba(126,126,126,0.11); width: 100%"/></div>
            <div style="font-size: 25px; font-weight: bold; width: 15%; display: flex; justify-content: end">{{menu.menuPrice}}원</div>
          </div>

          <div style="background-color: rgba(197,192,192,0.22); border-radius: 5px; height: 40px; font-size: 20px" class="pa-2">
            {{menu.menuInfo}}
          </div>
        </div>
      </div>

    </div> <!-- 가격 부분 -->


    <v-dialog persisten max-width="400" v-model="getCouponDialog">
      <v-card>
        <v-card-title class="headline">쿠폰을 발급받았습니다</v-card-title>
        <v-card-text>발급 받은 쿠폰을 보러갈까요?</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn text @click="getCouponDialog = false">아니요</v-btn>
          <v-btn text color="green" @click="moveCouponList">네</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

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
  name: "BossReadHomeView",
  props: {
    boss: {
      type: Object
    }
  },
  data(){
    return {
      mapInstance: null,
      markInstance: null,
      id: cookies.get("id"),
      name: cookies.get("name"),
      reviewDialog: false,
      getCouponDialog: false,
      selection: [],
      reviewTag: [
        '친절해요',
        '가격이 저렴해요',
        '만족스러워요',
        '아쉬워요'
      ],
      state: []
    }
  },
  methods: {
    ...mapActions(['fetchBossMenuList']),
    ...mapActions(['fetchShowCoupon']),
    addImg(){
      this.$refs.files.click()
    },
    imageUpload() {
      console.log(this.$refs.files.files);

      if(this.files.length < 10) {
        let num = -1;

        if(this.files.length === 0 ) {
          for (let i = 0; i < this.$refs.files.files.length; i++) {
            this.files = [
              ...this.files,
              {
                file: this.$refs.files.files[i],
                preview: URL.createObjectURL(this.$refs.files.files[i]),
                number: i
              }
            ];
            num = i;
          }
          this.uploadImageIndex = num + 1;
        } else {
          for (let i = 0; i < this.$refs.files.files.length; i++) {
            console.log(this.uploadImageIndex);
            this.files = [
              ...this.files,
              //이미지 업로드
              {
                //실제 파일
                file: this.$refs.files.files[i],
                //이미지 프리뷰
                preview: URL.createObjectURL(this.$refs.files.files[i]),
                //삭제및 관리를 위한 number
                number: i + this.uploadImageIndex
              }
            ];
            num = i;
          }
          this.uploadImageIndex = this.uploadImageIndex + num + 1;
        }
      } else {
        alert('사진 삭제 후 사용해주세요 ')
      }
    },
    fileDeleteButton(e) {
      const name = e.target.getAttribute('name');
      this.files = this.files.filter(data => data.number !== Number(name));
    },
    initMap(){
      let container = this.$refs.map;

      this.mapInstance = new kakao.maps.Map(container, {
        center: new kakao.maps.LatLng(this.boss.lat, this.boss.lng),
        level: 2,
      });

      this.markInstance = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(this.boss.lat, this.boss.lng),
        //clickable: true
      })

      this.markInstance.setMap(this.mapInstance);
    },
    getCoupon(coupon){
      let id = this.id;
      let couponNo = coupon.couponNo;

      axios.post(API_BASE_URL+'/boss/getCoupon', {id, couponNo}, config)
          .then((res) => {
            console.log('성공')
            if(res.data === false){
              alert('이미 발급받은 쿠폰입니다.')
            }else{
              this.getCouponDialog = true;
            }
          })
          .catch(() => {
            alert('에러')
          })
    },
    moveCouponList(){
      this.$router.push({name: 'MyCouponPage'})
    },
    day(){
      let today = new Date();

      let year = today.getFullYear();
      let month = ('0' + (today.getMonth() + 1)).slice(-2);
      let day = ('0' + today.getDate()).slice(-2);

      console.log(this.coupon)

      for(let i = 0;  i < this.coupon.length; i++){
        let date = this.coupon[i].couponDate.split('/');
        console.log(date[0])
        console.log(date[1])
        console.log(date[2])

        if(date[0] - year > 0){
          this.state.push(true)
        }else if(date[1] - month > 0){
          this.state.push(true)
        }else if(date[2] - day > 0){
          this.state.push(true)
        }else{
          this.state.push(false)
        }

        console.log(this.state[i])
      }
      let dateString = year + '-' + month  + '-' + day;
      console.log(dateString)
    }
  },
  mounted() {
    this.initMap();
  },
  computed: {
    ...mapState(['bossMenu']),
    ...mapState(['coupon'])
  },
  async created() {
    await this.fetchBossMenuList(this.id);
    await this.fetchShowCoupon(this.boss.bossAuthNo);
    await this.day();
  }
}
</script>

<style scoped>
.kmap {
  width: 100%;
  height: 300px;
}

.file-preview-wrapper {
  padding: 10px;
  position: relative;
}

.file-preview-wrapper>img {
  position: relative;
  width: 170px;
  height: 120px;
}

.file-close-button {
  position: absolute;
  line-height: 18px;
  z-index: 99;
  font-size: 18px;
  right: 5px;
  top: 10px;
  color: white;
  background-color: #cae3a4;
  width: 20px;
  height: 20px;
  text-align: center;
  cursor: pointer;
}
</style>