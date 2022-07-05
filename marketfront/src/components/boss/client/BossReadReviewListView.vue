<template>
  <div>
    <div v-if="bossReview === ''" style="height: 320px; text-align: center; font-size: 23px; opacity: 0.5" class="pa-12">
      아직 후기가 없어요.
    </div>

    <div v-else>
      <div v-for="(boss, index) in bossReview" :key="index" style="padding: 20px 15px 0 20px; border-bottom: 1px solid rgba(187,187,187,0.23);">
        <div style="display: flex; margin-top: 2px">
          <div style="width: 50%">
            <div style="padding-right: 10px; display: flex">
              <v-img :src="require(`@/assets/profile/${boss.profileImg}`)" max-height="60" max-width="60"></v-img>
            </div>
            <div>
              <div style="font-size: 22px; font-weight: bolder">{{boss.name}}</div>
              <div> {{boss.region}} ㆍ {{boss.date}}</div>
            </div>
          </div>
          <div style="display: flex; justify-content: end; width: 50%">
            <v-btn v-if="id === boss.id" style="margin-right: 10px" @click="modify(boss, index)">수정</v-btn>
            <v-btn v-if="id === boss.id" @click="deleteReview(boss)">삭제</v-btn>
          </div>
        </div>

        <div style="padding-top: 20px; font-size: 25px">
          {{boss.content}}
        </div>

<!--        <span>{{bossReviewImg[index].length}}</span>-->
        <div v-if="bossReviewImg[index][0]">
          <swiper class="swiper" :options="swiperOption" style="height: 500px">
            <swiper-slide v-for="(img) in bossReviewImg[index]" :key="img.no">
              <v-img max-height="550px" :src="require(`@/assets/bossReview/${img.imgName}`)" id="img"></v-img>
            </swiper-slide>

            <div class="swiper-pagination" slot="pagination"></div>
          </swiper>
        </div>
        <div v-else></div>

        <div style="padding-bottom: 8px">
          <v-chip-group multiple v-model="selection[index]" active-class="light-green lighten-2 white--text">
            <v-chip v-for="(tag, index) in reviewTag" :key="index">
              {{ tag }}
            </v-chip>
          </v-chip-group>
        </div>
      </div>
    </div>

    <v-dialog v-model="modifyDialog" width="800px">
      <v-card width="800px" height="auto" class="pa-4">
        <div style="padding: 5px 20px 5px 20px">
          <v-row justify="center" style="font-size: 20px; padding-top: 20px">

          </v-row>
          <v-row justify="center" style="font-weight: lighter; font-size: 18px">
            이웃에게 이용 경험을 알려보세요
          </v-row>

          <v-row justify="center" style="padding-top: 15px">
            <v-chip-group multiple v-model="modifySelection" active-class="light-green lighten-2 white--text">
              <v-chip v-for="(tag, index) in reviewTag" :key="index">
                {{ tag }}
              </v-chip>
            </v-chip-group>
          </v-row>

          <v-row style="padding-top: 25px">
            <v-textarea solo v-model="content" height="250px">
            </v-textarea>
          </v-row>

          <v-row>
            <div>삭제할 사진을 선택해주세요</div>
          </v-row>
          <v-row>
            <div style="overflow-x: scroll; display: flex">
              <div v-for="(img, index) in bossReviewImg[modifyIndex]" :key="img.no">
                <div style="display: flex;">
                  <div style="padding: 5px 5px 5px 5px">
                    <div><v-img min-width="100" min-height="100" style="width: 200px; height: 180px" :src="require(`@/assets/bossReview/${img.imgName}`)"></v-img></div>
                    <v-checkbox v-model="checkBox" :value="index"></v-checkbox>
                  </div>
                </div>
              </div>
            </div>
          </v-row>

          <div class="image_upload_box" style="display: flex; padding-top: 20px">
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
                <img :src="file.preview"  alt=""/>
              </div>
            </div>
          </div>

          <div style="padding-top: 25px; padding-bottom: 10px; display: flex; justify-content: end">
            <v-btn style="margin-right: 15px" @click="modifyDialog = false">취소</v-btn>
            <v-btn class="light-green lighten-2" @click="modifyReview" v-bind:disabled="selection.length === 0">저장</v-btn>
          </div>
        </div>
      </v-card>
    </v-dialog>

  </div>
</template>

<script>
import {mapActions, mapState} from "vuex";
import 'swiper/css/swiper.css'
import {Swiper, SwiperSlide} from "vue-awesome-swiper";
import cookies from "vue-cookies";

export default {
  name: "BossReadReviewListView",
  props: {
    bossNo: {
      type: Number,
    }
  },
  components: {
    Swiper, SwiperSlide
  },
  data(){
    return {
      id: cookies.get('id'),
      selection: [],
      modifySelection: [],
      reviewTag: [
        '친절해요',
        '가격이 저렴해요',
        '만족스러워요',
        '아쉬워요'
      ],
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
      files: [], //업로드용 파일
      filesPreview: [],
      uploadImageIndex: 0,
      modifyDialog: false,
      reviewNo: '',
      content: '',
      modifyIndex: '',
      checkBox: [],
    }
  },
  methods: {
    ...mapActions(['fetchBossReview']),
    ...mapActions(['fetchBossReviewImage']),
    parsingState() {
      for(let i = 0; i < this.bossReview.length; i++) {
        let num = this.bossReview[i].state.split(',');
        let arr = [];

        for(let j = 0; j < num.length; j++){
          arr.push(parseInt(num[j]));
        }

        console.log(arr)
        this.selection.push(arr);
      }
    },
    modify(boss, index) {
      this.content = boss.content;
      this.modifyIndex = index;
      this.reviewNo = boss.reviewNo
      this.modifySelection = this.selection[index]
      this.modifyDialog = true
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
    modifyReview(){
      let formData = new FormData;

      if(this.checkBox.length !== 0) {
        for (let i = 0; i < this.checkBox.length; i++) {
          formData.append('deleteImg', this.bossReviewImg[this.modifyIndex][this.checkBox[i]].no);
        }
      }else {
        formData.append('deleteImg', 'noImg')
      }

      formData.append('id', this.id)
      formData.append('content', this.content)
      formData.append('state', this.modifySelection)
      formData.append('reviewNo', this.reviewNo)

      if(this.files.length > 0) {
        for (let i = 0; i < this.files.length; i++) {
          formData.append('fileList', this.files[i].file)
        }

        this.$emit('modifyImgReview', formData)
      }else {
        this.$emit('modifyReview', formData)
      }
    },
    deleteReview(boss){
      let reviewNo = boss.reviewNo;
      this.$emit('deleteReview', {reviewNo});
    }
  },
  computed: {
    ...mapState(['bossReview']),
    ...mapState(['bossReviewImg'])
  },
  async mounted() {
    let num = '0' +this.bossNo;
    await this.fetchBossReview(num);
    setTimeout(() => {
      this.fetchBossReviewImage(num)
    }, 100)
    await this.parsingState();
  }
}
</script>

<style scoped>
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