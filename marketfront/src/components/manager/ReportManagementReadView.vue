<template>
  <div id="manager_page_box" style="position: relative">
    <v-card style="min-height: 500px; padding: 10px 10px 10px 10px">
      <v-row justify="end" style="padding: 5px 5px 20px 0"><v-card-actions><v-btn @click="done">처리 완료</v-btn></v-card-actions></v-row>
      <table style="border: 1px solid black; width: 100%; border-collapse: collapse">
        <tr>
          <td>신고 번호</td>
          <td style="padding: 5px 5px 5px 5px">{{reportNo}}</td>
        </tr>

        <tr>
          <td>상태</td>
          <td style="height: 50px; padding: 5px 5px 5px 5px">
            <v-chip v-if="reportRead.state === 'PROCESSING'" color="red" text-color="white">처리 전</v-chip>
            <v-chip v-else color="green" text-color="white">처리 완료</v-chip>
          </td>
        </tr>

        <tr>
          <td>제보자</td>
          <td style="padding: 5px 5px 5px 5px">{{reportRead.fromMember}}</td>
        </tr>

        <tr>
          <td>신고</td>
          <td style="padding: 5px 5px 5px 5px">{{reportRead.toMember}} <v-btn>사용자 제거</v-btn></td>
        </tr>

        <tr>
          <td>카테고리 1</td>
          <td>{{reportRead.category1}}</td>
        </tr>

        <tr>
          <td>카테고리 2</td>
          <td style="padding: 5px 5px 5px 5px">{{reportRead.category2}}</td>
        </tr>

        <tr v-if="reportRead.content !== ''">
          <td>내용</td>
          <td>
            <v-textarea readonly solo style="height: 100%" v-model="reportRead.content"></v-textarea>
          </td>
        </tr>

        <tr v-if="reportImageRead">
          <td colspan="2">
            <swiper class="swiper" :options="swiperOption" style="height: 300px; width: 300px">
              <swiper-slide v-for="(img,index) in reportImageRead" :key="index">
                <v-img max-height="550" max-width="500px" :src="require(`@/assets/report/${img}`)" id="img"></v-img>
              </swiper-slide>

              <div class="swiper-pagination" slot="pagination"></div>
            </swiper>
          </td>
        </tr>

        <tr style="border: none" v-if="reportBoardRead">
          <td colspan="2" align="end"  style="padding-top: 30px; border: none">
            <v-btn style="margin-right: 50px" class="red lighten-2 white--text" @click="deleteBoard">게시글 삭제</v-btn>
          </td>
        </tr>

        <tr>
          <td colspan="2" style="border-top: none" v-if="reportBoardRead">
            <div @click="moveProduct">
              <article id="content">
                <section id="profile">
                  <div class="space-between">
                    <div style="display: flex">
                      <div id="profile-image">
                        <v-img width="650px" height="550px" :src="require(`@/assets/pImage/${reportBoardRead.productImage}`)"/>
                      </div>
                    </div>
                  </div>
                </section>

                <section id="article-profile">
                  <div id="article-profile-link">
                    <div class="space-between">
                      <div style="display: flex">
                        <div id="article-profile-image">
                          <v-img max-width="50px" :src="require(`@/assets/profile/${reportBoardRead.member.profileImg}`)" />
                        </div>

                        <div id="profile-left">
                          <div id="userName">
                            {{ reportBoardRead.member.name }}
                          </div>
                          <div id="region-name">{{ reportBoardRead.member.address }}</div>
                        </div>
                      </div>

                      <div id="profile-right">
                        <dl id="temperature-wrap">
                          <dt>매너온도</dt>
                          <dd class="text-color-03">
                            {{ reportBoardRead.member.temperature }}
                            <span>°C</span>
                          </dd>
                        </dl>
                        <div class="meters">
                          <div class="bar bar-color-03" style="width: 37%"></div>
                        </div>
                      </div>
                    </div>
                  </div>
                </section>

                <section id="description">
                  <h1 property="schema:name" id="title" style="margin-top: 0px">
                    {{ reportBoardRead.title }}
                  </h1>
                  <p id="category">
                    {{ reportBoardRead.category }} •
                    {{ reportBoardRead.regDate }}
                  </p>
                  <p id="price" property="schema:price" content style="font-size: 18px; font-weight: bold">
                    <span class="text-light-gray">{{ reportBoardRead.price }}원</span>
                  </p>

                  <div property="schema:content" id="detail">
                    <p>{{ reportBoardRead.content }}</p>
                  </div>

                  <p id="counts">관심 8 ∙ 채팅 11 ∙ 조회 111</p>

                </section>
              </article>
            </div>
          </td>

          <td v-else colspan="2" align="center" style="font-size: 30px">
            게시글 삭제완료
          </td>
        </tr>
      </table>
    </v-card>
  </div>
</template>

<script>
import {mapActions, mapState} from "vuex";
import 'swiper/css/swiper.css'
import {Swiper, SwiperSlide} from "vue-awesome-swiper";

export default {
  name: "ReportManagementReadView",
  props: {
    reportNo: {
      type: String
    }
  },
  components: {
    Swiper, SwiperSlide
  },
  data(){
    return {
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
      items: ['처리전', '처리 완료']
    }
  },
  methods: {
    ...mapActions(['fetchReportRead', 'fetchReportReadImage', 'fetchReportReadBoard']),
    deleteBoard(){
      let reportNo = this.reportRead.reportNo;
      this.$emit('deleteBoard', {reportNo})
    },
    moveProduct(){
      this.$router.push({name: 'ProductReadPage', params: { productNo: this.reportBoardRead.productNo.toString() }});
    },
    done(){
      let reportNo = this.reportRead.reportNo;
      this.$emit('done', {reportNo})
    }
  },
  computed: {
    ...mapState(['reportRead', 'reportImageRead', 'reportBoardRead'])
  },
  created() {
    this.fetchReportRead(this.reportNo)
    this.fetchReportReadImage(this.reportNo)
    this.fetchReportReadBoard(this.reportNo)
  }
}
</script>

<style scoped>
#manager_page_box {
  align-items: center;
  width: 100%;
  max-width: 1250px;
  margin: 0 auto;
  padding: 15px 7px 0 7px; /*위, 오른쪽, 아래, 왼쪽 */
}
td{
  border: 1px solid black;
}
#content {
  margin-top: 10px;
  padding-bottom: 0;
  display: block;
}
#profile {
  width: 677px;
  margin: auto;
  text-decoration: none;
  display: block;
  margin-top: 25px;
  padding-bottom: 23px;
  position: relative;
  border-bottom: 1px solid #e9ecef;
}
#article-profile {
  width: 677px;
  margin: 0 auto;
}

#article-profile-link {
  text-decoration: none;
  display: block;
  margin-top: 25px;
  padding-bottom: 23px;
  position: relative;
  border-bottom: 1px solid #e9ecef;
}
.space-between {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

#profile-left {
  display: inline-block;
  margin-left: 8px;
}
#article-profile-image {
  display: inline-block;
}

img {
  width: 40px;
  height: 40px;
  object-fit: cover;
}

#userName {
  font-size: 15px;
  font-weight: 600;
  line-height: 1.5;
  letter-spacing: -0.6px;
  color: #212529;
}

#region-name {
  font-size: 13px;
  line-height: 1.46;
  letter-spacing: -0.6px;
  color: #212529;
}

#profile-right {
  position: absolute;
  right: 0;
  top: 0;
  padding-right: 30px;
}
#description {
  padding: 32px 0;
  width: 677px;
  margin: 0 auto;
  border-bottom: 1px solid #e9ecef;
}
#title {
  font-size: 20px;
  font-weight: 600;
  line-height: 1.5;
  letter-spacing: -0.6px;
}
#category {
  margin-top: 4px;
  font-size: 13px;
  line-height: 1.46;
  letter-spacing: -0.6px;
  color: #868e96;
}
#price {
  margin-top: 4px;
  font-size: 18px;
  font-weight: bold;
  line-height: 1.76;
  letter-spacing: -0.6px;
}
#detail p {
  margin-bottom: 16px;
  margin-top: 8px;
  font-size: 17px;
  line-height: 1.6;
  letter-spacing: -0.6px;
  margin: 16px 0;
  word-break: break-all;
}
#counts {
  font-size: 13px;
  line-height: 1.46;
  letter-spacing: -0.6px;
  color: #868e96;
}

dl {
  display: block;
  width: 100px;
}

dl dt {
  position: absolute;
  top: 25px;
  left: 60px;
  font-size: 12px;
  line-height: 1;
  letter-spacing: -0.6px;
  color: #868e96;
}

.text-color-03 {
  color: lightgreen;
}

dl dd {
  position: relative;
  font-size: 16px;
  font-weight: bold;
  line-height: 1;
  letter-spacing: -0.5px;
  margin-top: 1px;
  width: 100px;
  text-align: right;
}
</style>