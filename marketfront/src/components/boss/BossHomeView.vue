<template>
  <div>
    <div style="padding-bottom: 20px" class="home_style" v-if="boss.marketInfo === null">
    <span style="font-size: 30px; font-weight: bold;"> 정보 </span>
    <!--   정보가 없을 경우에     -->
    <div style="margin-top: 20px" @click="infoDialog">
      <div style="border-style: dotted; height: 120px; border-radius: 8px; text-align: center; font-size: 23px; opacity: 0.5" class="pa-11">
        + &nbsp; 소개 문구 등록
      </div>

      <div style="padding-top: 30px">
        <span style="font-size: 25px; opacity: 0.5;"><v-icon x-large>mdi-map-marker-outline</v-icon> 주소를 추가해주세요.</span>
      </div>

      <div style="padding-top: 15px">
        <span style="font-size: 25px; opacity: 0.5;"><v-icon x-large>mdi-clock-time-five-outline</v-icon> 영업시간을 추가해 주세요.</span>
      </div>
    </div>

    <div style="padding-top: 30px" class="home_style_btn">
      <v-btn style="width: 100%; height: 50px; font-size: 25px; font-weight: bold" @click="infoDialog">
        정보 관리하기
      </v-btn>
    </div>

    <!--    정보가 있을 경우    -->
  </div>

    <div style="padding-bottom: 20px" class="home_style" v-else>
      <div style="display: flex">
        <span style="font-size: 30px; font-weight: bold; width: 30%"> 정보 </span> <span style="width: 70%; display: flex; justify-content: end;"><v-btn @click="modifyMarketInfo">수정</v-btn></span>
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
        <div style="padding-top: 30px">
          <span style="font-size: 25px; opacity: 0.5;">아직 쿠폰이 없어요.</span>
        </div>

        <div style="padding-top: 30px">
          <v-btn style="width: 100%; height: 50px; font-size: 25px; font-weight: bold">
            쿠폰 관리하기
          </v-btn>
        </div>
      </div>

    </div> <!-- 쿠폰 부분 -->

    <hr style="height: 15px; background-color: rgba(94,94,94,0.16); border: none"/>

    <div style="padding-bottom: 20px" class="home_style">
      <span style="font-size: 30px; font-weight: bold;"> 가격 </span>
      <!--   정보가 없을 경우에     -->
      <div style="margin-top: 20px" v-if="bossMenu === null">
        <div style="padding-top: 30px">
          <span style="font-size: 25px; opacity: 0.5;">상품이나 서비의 가격을 추가하고, 이웃의 눈길을 끌어보세요.</span>
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

      <div style="padding-top: 30px">
        <v-btn style="width: 100%; height: 50px; font-size: 25px; font-weight: bold" @click="priceDialog = true">
          가격 설정
        </v-btn>
      </div>


    </div> <!-- 가격 부분 -->

    <v-dialog v-model="priceDialog" width="800px">
      <v-card width="800px" height=auto class="pa-4">
        <v-row style="padding-bottom: 30px">
          <v-col align-self="center" style="height: 40px;"> <v-btn @click="priceDialog = false"><v-icon>mdi-arrow-left</v-icon></v-btn></v-col>
          <v-col><v-card-title style="height: 40px; font-size: 22px; display: flex; justify-content: center">가격 목록</v-card-title></v-col>
          <v-col align-self="center" style="height: 40px; display: flex; justify-content: end"><v-btn @click="modifyPriceDialog = true">수정</v-btn></v-col>
        </v-row>

        <!--    리스트가 보이는 곳    -->
        <div v-for="(menu, index) in bossMenu" :key="index" style="border-bottom: solid 1px rgba(0,0,0,0.24); height: 100px; padding: 10px 10px 10px 10px" >
          <div style="font-size: 20px; font-weight: revert">{{menu.menuName}}</div>
          <div style="font-size: 17px">{{menu.menuInfo}}</div>
          <div style="font-size: 17px; font-weight: bolder">{{menu.menuPrice}}원</div>
        </div>

        <div style="padding-top: 20px; padding-bottom: 10px">
          <v-btn width="100%" @click="addPriceDialog = true">가격 추가</v-btn>
        </div>
      </v-card>
    </v-dialog> <!-- 가격 추가 1 -->

    <v-dialog v-model="addPriceDialog"  width="800px">
      <v-card width="800px" class="pa-4">

        <div style="padding: 10px 10px 10px 10px">
          <div style="justify-content: center; display: flex">
            <v-card-title style="font-weight: bold; font-size: 25px">가격 추가</v-card-title>
          </div>

          <div class="priceTitle"> 항목 </div>

          <div>
            <v-text-field v-model="menuName" solo label="항목명을 입력하세요. (예: 아메리카노)"></v-text-field>
          </div>

          <div class="priceTitle">
            가격
          </div>

          <div>
            <v-text-field v-model="menuPrice" solo label="가격을 입력하세요"></v-text-field>
          </div>

          <div class="priceTitle">
            추가 설명 (선택)
          </div>

          <div>
            <v-textarea v-model="menuInfo" label="가격에 대한 설명이 필요하다면 적어주세요." solo></v-textarea>
          </div>

          <div style="display: flex; justify-content: end">
            <v-btn style="margin-right: 8px" @click="addPriceDialog = false">close</v-btn>
            <v-btn class="light-green lighten-3" @click="savePrice">save</v-btn>
          </div>
        </div>
      </v-card>
    </v-dialog>  <!-- 가격 추가 2 -->

    <v-dialog v-model="modifyPriceDialog" width="800px">
      <v-card width="800px" height=auto class="pa-4">
        <v-row style="padding-bottom: 30px" v-if="modifyCheck">
          <v-col align-self="center" style="height: 40px;"> <v-btn @click="modifyPriceDialog = false"><v-icon>mdi-arrow-left</v-icon></v-btn></v-col>
          <v-col><v-card-title style="height: 40px; font-size: 22px; display: flex; justify-content: center">가격 목록</v-card-title></v-col>
          <v-col align-self="center" style="height: 40px; display: flex; justify-content: end"><v-btn @click="modifyPriceDialog = false">완료</v-btn></v-col>
        </v-row>

        <div v-if="modifyCheck">
          <!--    리스트가 보이는 곳    -->
          <div v-for="(menu, index) in bossMenu" :key="index" style="border-bottom: solid 1px rgba(0,0,0,0.24); height: 100px; padding: 10px 10px 10px 10px" >
            <div style="font-size: 20px; font-weight: revert; display: flex">
              <div style="width: 95%" @click="modifyPrice(menu)">{{menu.menuName}}</div>
              <div style="width: 5%; justify-content: end"><v-btn icon @click="deletePrice(menu.menuNo)"><v-icon>mdi-close</v-icon></v-btn></div>
            </div>
            <div style="font-size: 17px" @click="modifyPrice(menu)">{{menu.menuInfo}}</div>
            <div style="font-size: 17px; font-weight: bolder" @click="modifyPrice(menu)">{{menu.menuPrice}}원</div>
          </div>
          <div style="padding-top: 20px; padding-bottom: 10px">
            <v-btn width="100%" @click="addPriceDialog = true">가격 추가</v-btn>
          </div>
        </div>

        <div v-else>
          <div style="padding: 10px 10px 10px 10px">
            <div class="priceTitle"> 항목 </div>

            <div>
              <v-text-field v-model="modifyMenuName" solo label="항목명을 입력하세요. (예: 아메리카노)"></v-text-field>
            </div>

            <div class="priceTitle">
              가격
            </div>

            <div>
              <v-text-field v-model="modifyMenuPrice" solo label="가격을 입력하세요"></v-text-field>
            </div>

            <div class="priceTitle">
              추가 설명 (선택)
            </div>

            <div>
              <v-textarea v-model="modifyMenuInfo" label="가격에 대한 설명이 필요하다면 적어주세요." solo></v-textarea>
            </div>

            <div style="display: flex; justify-content: end">
              <v-btn style="margin-right: 8px" @click="modifyCheck = true">close</v-btn>
              <v-btn class="light-green lighten-3" @click="modifySave">save</v-btn>
            </div>
          </div>

        </div>
      </v-card>
    </v-dialog> <!-- 가격 수정 -->
  </div>
</template>

<script>

import cookies from "vue-cookies";
import {mapActions, mapState} from "vuex";

let kakao = window.kakao;

export default {
  name: "BossHomeView",
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
      addPriceDialog: false,
      modifyPriceDialog: false,
      priceDialog: false,
      selection: [],
      reviewTag: [
        '친절해요',
        '가격이 저렴해요',
        '만족스러워요',
        '아쉬워요'
      ],
      menuName: '',
      menuPrice: '',
      menuInfo: '',
      modifyCheck: true,
      modifyMenuNo: '',
      modifyMenuName: '',
      modifyMenuPrice: '',
      modifyMenuInfo: '',
    }
  },
  methods: {
    ...mapActions(['fetchBossMenuList']),
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
    infoDialog(){
      this.$router.push({name: 'BossMarketInfo'})
    },
    modifyMarketInfo(){
      this.$router.push({name: 'BossMarketInfoModify', params: {bossNo: this.boss.bossAuthNo.toString()}})
    },
    savePrice(){
      const {menuName, menuPrice, menuInfo} = this
      let bossNo = this.boss.bossAuthNo;
      this.$emit('savePrice', {menuName, menuPrice, menuInfo, bossNo})
      this.addPriceDialog = false;
      this.priceDialog = false;
    },
    modifyPrice(menu){
      this.modifyCheck = false;
      this.modifyMenuNo = menu.menuNo;
      this.modifyMenuPrice = menu.menuPrice;
      this.modifyMenuName = menu.menuName;
      this.modifyMenuInfo = menu.menuInfo;
    },
    modifySave(){
      let menuName = this.modifyMenuName;
      let menuPrice = this.modifyMenuPrice;
      let menuInfo = this.modifyMenuInfo;
      let bossPriceNo = this.modifyMenuNo;
      //alert(bossPriceNo)

      this.$emit('modifySave', {menuName, menuPrice, menuInfo, bossPriceNo})
    },
    deletePrice(menuNo){
      this.$emit('deletePrice', {menuNo})
      this.modifyCheck = true
    }
  },
  mounted() {
    //this.initMap();
  },
  computed: {
    ...mapState(['bossMenu'])
  },
  created() {
    this.fetchBossMenuList(this.id)
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