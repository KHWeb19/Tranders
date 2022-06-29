<template>
  <div id="my_page_box" style="position: relative">
    <div id="profile_back_img">
      <div style="width: 100%; position: relative;">
        <v-img v-if="backProfileImgs === null" src="@/assets/bossProfile/back/Tranders_boss_base_backProfile.png" id="img" style="width: 100%; height: 500px;"></v-img>
        <swiper v-else class="swiper" :options="swiperOption" style="height: 500px">
          <swiper-slide v-for="(boardImg,index) in backProfileImgs" :key="index">
            <v-img :src="require(`@/assets/bossProfile/back/${boardImg.fileName}`)" id="img"></v-img>
          </swiper-slide>

          <div class="swiper-pagination" slot="pagination"></div>
        </swiper>
      </div>

      <div style="top: 25px; padding-left: 5px; position: absolute; z-index: 1">
        <v-btn icon @click="backPage"><v-icon x-large>mdi-chevron-left</v-icon></v-btn>
      </div>
      <div style="top: 25px; position: absolute; left: 88%; z-index: 1">
        <v-btn text @click="modify"><v-icon x-large>mdi-camera</v-icon>&nbsp; &nbsp;수정</v-btn>
      </div>
    </div>

    <div id="profile" style="padding-top: 30px;">

      <div id="profile_img">
        <v-img :src="require(`@/assets/bossProfile/front/${boss.profileImg}`)" height="200px" width="200px" style="border-radius: 70%"></v-img>
      </div>
      <div style="padding-left: 55px; font-size: 40px; width: 100%" class="pa-5">
        {{ boss.placeName }}
        <div style="font-size: 20px" class="pa-2">
          {{ boss.region }} ㆍ {{boss.category}} ㆍ 단골 0
        </div>
        <div>
          <v-btn class="white--text" style="font-weight: bold; background-color: #4c7732"> + 단골 맺기</v-btn>
        </div>
      </div>
    </div>

    <div style="display: flex; padding-top: 50px; justify-content: center; align-items: center;" class="pa-4">
      <div @click="phoneNumDialog = true" style="width: 33%; display: flex; flex-direction: column; align-items: center; border-right: rgba(168,168,168,0.4) solid 1px">
        <div><v-img style="opacity: 0.7" src="@/assets/icon/cellPhone.png" max-width="80px"></v-img></div>
        <div style="flex-direction: row; flex-wrap: wrap; padding-top: 20px; font-size: 18px">전화문의</div>
      </div>

      <div style="width: 33%; display: flex; flex-direction: column; align-items: center; border-right: rgba(168,168,168,0.4) solid 1px">
        <div><v-img style="opacity: 0.7" src="@/assets/icon/conversation.png" max-width="80px"></v-img></div>
        <div style="flex-direction: row; flex-wrap: wrap; padding-top: 20px; font-size: 18px">채팅문의</div>
      </div>

      <div style="width: 33%; display: flex; flex-direction: column; align-items: center;" @click="reviewDialog = true">
        <div><v-img style="opacity: 0.7" src="@/assets/icon/pencil.png" max-width="80px"></v-img></div>
        <div style="flex-direction: row; flex-wrap: wrap; padding-top: 20px; font-size: 18px">후기작성</div>
      </div>
    </div>

    <div style="padding-bottom: 50px; height: 40px; padding-top: 30px">
      <div v-if="isCheck" style="float: left; height: 40px; width: 50%; border-right: black solid 1px; font-size: 25px; text-align: center; border-bottom: 3px solid black">
        홈
      </div>

      <div v-else style="float: left; height: 40px; width: 50%; border-right: black solid 1px; font-size: 25px; text-align: center" @click="home">
        홈
      </div>

      <div v-if="isCheck" style="float: left; height: 40px; width: 50%; font-size: 25px; text-align: center" @click="review">
        후기
      </div>

      <div v-else style="float: left; height: 40px; width: 50%; font-size: 25px; text-align: center; border-bottom: 3px solid black">
        후기
      </div>
    </div>

      <div v-if="isCheck">
        <div style="padding-bottom: 20px" class="home_style" v-if="boss.marketInfo === null">
          <span style="font-size: 30px; font-weight: bold;"> 정보 </span>
          <!--   정보가 없을 경우에     -->
          <div style="margin-top: 20px">
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

          <!--    정보가 있을 경우    -->
        </div>

        <div style="padding-bottom: 20px" class="home_style" v-else>
          <div style="display: flex">
            <span style="font-size: 30px; font-weight: bold; width: 30%"> 정보 </span> <span style="width: 70%; display: flex; justify-content: end;"><v-btn>수정</v-btn></span>
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

          </div>

        </div> <!-- 쿠폰 부분 -->

        <hr style="height: 15px; background-color: rgba(94,94,94,0.16); border: none"/>

        <div style="padding-bottom: 20px" class="home_style">
          <span style="font-size: 30px; font-weight: bold;"> 가격 </span>
          <!--   메뉴가 없을 경우에     -->
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

        </div> <!-- 가격 부분 -->
      </div>

      <div v-else style="border: 5px solid orange;">
        <div style="height: 320px; text-align: center; font-size: 23px; opacity: 0.5" class="pa-12">
          아직 후기가 없어요.
        </div>
      </div>

      <div style="background-color: rgba(187,187,187,0.23); height: 100px; text-align: center; font-size: 15px" class="pa-10">
        오이마켓에 등록된 가게의 정보는 가게 관리자가 직접 등록한 것으로 (주)오이마켓은 등록된 내용에 대하여 일체의 책임을 지지 않습니다.
      </div>

    <v-dialog v-model="reviewDialog" width="800px">
      <v-card width="800px" height="auto" class="pa-4">
        <div style="padding: 5px 20px 5px 20px">
          <v-row justify="center" style="font-size: 20px; padding-top: 20px">
            {{name}} 님, {{boss.placeName}} 이용하셨나요?
          </v-row>
          <v-row justify="center" style="font-weight: lighter; font-size: 18px">
            이웃에게 이용 경험을 알려보세요
          </v-row>

          <v-row justify="center" style="padding-top: 15px">
            <v-chip-group multiple v-model="selection" active-class="light-green lighten-2 white--text">
              <v-chip v-for="(tag, index) in reviewTag" :key="index">
                {{ tag }}
              </v-chip>
            </v-chip-group>
          </v-row>

          <v-row style="padding-top: 25px">
            <v-textarea solo placeholder="후기를 남겨주세요" v-model="content" height="250px">
            </v-textarea>
          </v-row>

          <div class="image_upload_box" style="display: flex;">
            <div @click="addImg" style="width: 200px; height: 150px; border: 1px solid black; border-radius: 7px; display: flex; flex-direction: column; justify-content: center; align-items: center ">
              <div><v-img width="50px" height="50px" src="@/assets/icon/camera.png"></v-img></div>
              <div><span style="color: #06850e">{{files.length}}</span> / 10</div>
            </div>
            <input type="file" id="file" ref="files" @change="imageUpload" multiple style="display: none"/>

            <div style="display: flex; overflow: scroll">
              <div v-for="(file, index) in files" :key="index" class="file-preview-wrapper">
                <div class="file-close-button" @click="fileDeleteButton" :name="file.number">
                  x
                </div>
                <img :src="file.preview" />
              </div>
            </div>
          </div>

          <div style="padding-top: 25px; padding-bottom: 10px; display: flex; justify-content: end">
            <v-btn style="margin-right: 15px" @click="reviewClose">취소</v-btn>
            <v-btn class="light-green lighten-2" @click="saveReview" v-bind:disabled="selection.length === 0">저장</v-btn>
          </div>
        </div>
      </v-card>
    </v-dialog>

    <v-dialog v-model="phoneNumDialog" width="500px">
      <v-card width="500px" height="150px" style="padding: 35px 30px 20px 30px">
        <v-row justify="center">
          <div>
            <span class="light-green--text lighten-3" style="font-weight: bolder; font-size: 30px">{{boss.placeName}}</span>
            <span style="font-size: 22px">의 전화번호</span>
          </div>
        </v-row>

        <v-row justify="center">
          <span style="font-size: 20px; padding-top: 10px">{{phoneNum}}</span>
        </v-row>

        <v-row justify="end">
          <v-btn @click="phoneNumDialog=false">확인</v-btn>
        </v-row>
      </v-card>
    </v-dialog>

  </div>

</template>

<script>
import cookies from "vue-cookies";
import {mapActions, mapState} from "vuex";
import 'swiper/css/swiper.css'
import {Swiper, SwiperSlide} from "vue-awesome-swiper";
let kakao = window.kakao;

export default {
  name: "BossReadPageView",
  props: {
    boss: {
      type: Object
    }
  },
  components:{
    Swiper, SwiperSlide
  },
  data(){
    return {
      isCheck: true,
      mapInstance: null,
      markInstance: null,
      id: cookies.get("id"),
      name: cookies.get("name"),
      swiperOption: {
        slidesPerView: 1,
        spaceBetween: 30,
        autoHeight: true,
        loop: false,
        pagination: {
          el: '.swiper-pagination',
          clickable: true
        }
      },
      reviewDialog: false,
      selection: [],
      reviewTag: [
        '친절해요',
        '가격이 저렴해요',
        '만족스러워요',
        '아쉬워요'
      ],
      textAreaMessage: this.boss.placeName +"에 대한 후기를 남겨주세요",
      content: '',
      files: [], //업로드용 파일
      filesPreview: [],
      uploadImageIndex: 0,
      phoneNumDialog: false,
      phoneNum: ''
    }
  },
  methods: {
    ...mapActions(['fetchBossBackProfile']),
    ...mapActions(['fetchBossMenuList']),
    backPage(){
      this.$router.push({name: 'NearPage'})
    },
    modify(){
      alert('사진을 변경하겠다!')
      this.backProfileImgDialog = true
    },
    home(){
      this.isCheck = true;
      this.initMap();
    },
    review(){
      this.isCheck = false;
    },
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
    reviewClose(){
      this.reviewDialog = false;
      this.files = [];
      this.filesPreview = [];
      this.content = '';
      this.selection = [];
    },
    saveReview(){
      let formData = new FormData;

      for(let i = 0; i < this.files.length; i++){
        formData.append('fileList', this.files[i].file)
      }
      formData.append('id', this.id)
      formData.append('name', this.name)
      formData.append('content', this.content)
      formData.append('bossNo', this.boss.bossAuthNo)

      let select = '';

      for(let i = 0; i < this.selection.length; i++){
        console.log(this.selection[i])
        if(i === 0) {
          select = this.selection[i];
        }else {
          select += ',' + this.selection[i];
        }
      }

      formData.append('state', select)

      this.$emit('saveReview', formData)
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
  },
  mounted() {
    this.initMap();

    this.phoneNum = this.boss.phoneNumber.substr(0,3) +'-'+ this.boss.phoneNumber.substr(3, 4) + '-' + this.boss.phoneNumber.substr(8, 4);

  },
  computed: {
    ...mapState(['backProfileImgs']),
    ...mapState(['bossMenu'])
  },
  created() {
    this.fetchBossBackProfile(this.id)
    this.fetchBossMenuList(this.id)
  }
}
</script>

<style lang="scss" scoped>
#my_page_box{
  align-items: center;
  width: 100%;
  max-width: 1250px;
  margin: 0 auto;
  padding: 0 7px 0 7px; /*위, 오른쪽, 아래, 왼쪽 */
}
#profile {
  padding: 15px 20px 20px 20px;
  display: flex;
}
.home_style {
  padding: 10px 20px 10px 20px;
}
.home_style_btn {
  padding: 10px 0 10px 0;
}
.kmap {
  width: 100%;
  height: 300px;
}
.main-container {
  width: 1200px;
  height: 400px;
  margin: 0 auto;
}
.swiper {
  height: 900px;
  width: 100%;

  .swiper-slide {
    display: flex;
    justify-content: center;
    align-items: center;
  }
}

#img {
  width: 100%; height: 500px;
  max-width: 100%;
  max-height: 500px;
}
.priceTitle {
  padding-bottom: 10px;
  font-size: 18px;
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