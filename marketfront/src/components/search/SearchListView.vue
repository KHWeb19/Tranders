<template>
  <div id="search_list" style="position: relative">
    <!-- 중고 물품 -->
    <v-card style="width: 800px">
      <v-card-text style="font-size: 20px; font-weight: bolder">중고 거래</v-card-text>
        <div class="noMessage" v-if="!searchProduct || (Array.isArray(searchProduct) && searchProduct.length === 0)">
          찾으시는 내용이 없습니다.
        </div>

        <div v-else>
          <div style="padding: 40px;">
            <v-row>
              <v-col v-for="product in searchProduct" :key="product.productNo" style="max-width: 33%; height: 250px;">
                <div class="card-top" @click="moveProduct(product.productNo)">
                  <div class="card-photo">
                    <v-img :src="require(`@/assets/pImage/${product.productImage}`)"/>
                  </div>

                  <div class="card-desc">
                    <div class="card-title">{{ product.title }}</div>
                    <div class="card-title">{{ product.price }}원</div>
                    <div class="card-counts">
                      <span> 관심 5 </span>∙
                      <span> 채팅 6 </span>
                    </div>
                  </div>
                </div>
              </v-col>
            </v-row>
          </div>

          <div style="border-top: 1px solid rgba(218,218,218,0.52); height: 50px; display: flex; justify-content: center; align-items: center">
            <v-btn @click="addProduct" text style="width: 100%">추가</v-btn>
          </div>
        </div>
    </v-card>

    <div id='community_list'>
      <v-card style="width: 800px"> <!-- 현재 물품 -->
        <v-card-text style="font-size: 20px; font-weight: bolder;">동네 정보</v-card-text>

        <div class="noMessage" v-if="!searchComm || (Array.isArray(searchComm) && searchComm.length === 0)">
          찾으시는 내용이 없습니다.
        </div>

        <div v-else>
          <div style="padding: 20px 40px 40px 40px;">
            <div v-for="comm in searchComm" :key="comm.boardNo" @click="moveComm(comm.boardNo)">
              <div id='community_title_content'>
                <p>{{ comm.content}}</p>
              </div>
              <div id="community_writer">
                <div style="width: 50%; display: flex; align-items: center">
                  <span>{{comm.writer}}</span>
                  <span> &bull; {{comm.region}}</span>
                </div>

                <div id='community_icon'>
                  <v-icon x-small>mdi-heart-outline</v-icon>
                  <span style="padding-right: 10px; font-size: 15px">&nbsp;{{comm.likeCnt}}</span>
                  &nbsp;
                  <v-icon x-small>mdi-comment-outline</v-icon>
                  <span style="padding-right: 10px; font-size: 15px">&nbsp;{{comm.commentCnt}}</span>
                </div>
              </div>

              <hr id='community_hr'>
            </div>
          </div>

          <div style="border-top: 1px solid rgba(218,218,218,0.52); height: 50px; display: flex; justify-content: center; align-items: center">
            <v-btn @click="addComm" text style="width: 100%">추가</v-btn>
          </div>
        </div>
      </v-card>
    </div>
  </div>
</template>

<script>
import {mapState} from "vuex";
import axios from "axios";
import {API_BASE_URL} from "@/constant/login";
import cookies from "vue-cookies";

export default {
  name: "SearchListView",
  props: {
    keyWord: {
      type: String
    }
  },
  data(){
    return{
      num: 0,
      word: '',
      productPage: 0,
      commPage: 0,
      region: cookies.get('region'),
      id: cookies.get('id')
    }
  },
  methods: {
    moveProduct(productNo) {
      this.$router.push({ name: 'ProductReadPage', params: { productNo: productNo.toString()}})
    },
    moveComm(comm){
      this.$router.push({name: 'CommunityBoardReadPage', params: { boardNo: comm.toString()}})
    },
    fetchSearchProduct(keyWord){
      let page = this.productPage;
      let region = this.region;
      let id = this.id;

      this.word = keyWord;

      axios.post(API_BASE_URL+'/search/all/product', {id, page, keyWord, region}, {
        headers: {
          'Authorization': 'Bearer '+ cookies.get('access_token'),
          'Accept' : 'application/json',
          'Content-Type': 'application/json'
        }
      })
          .then((res) => {
            console.log(res)
            this.productPage ++;
           this.$store.state.searchProduct = res.data;
          })
    },
    addProduct(){
      let page = this.productPage;
      let region = this.region;
      let id = this.id;
      let keyWord = this.word;

      axios.post(API_BASE_URL+'/search/all/product', {id, page, keyWord, region}, {
        headers: {
          'Authorization': 'Bearer '+ cookies.get('access_token'),
          'Accept' : 'application/json',
          'Content-Type': 'application/json'
        }
      })
          .then((res) => {
            console.log(res.data)
            if(res.data.length === 0){
              alert('내용의 끝 입니다.')
            }else {
              this.productPage++;
              this.$store.state.searchProduct.push(...res.data);
            }
          })
    },
    addComm(){
      let page = this.commPage;
      let region = this.region;
      let id = this.id;
      let keyWord = this.word;

      axios.post(API_BASE_URL+'/search/all/comm', {id, page, keyWord, region}, {
        headers: {
          'Authorization': 'Bearer '+ cookies.get('access_token'),
          'Accept' : 'application/json',
          'Content-Type': 'application/json'
        }
      })
          .then((res) => {
            console.log(res.data)
            if(res.data.length === 0){
              alert('내용의 끝 입니다.')
            }else {
              this.commPage++;
              this.$store.state.searchComm.push(...res.data);
            }
          })
    },
    fetchSearchComm(keyWord){
      let page = this.commPage;
      let region = this.region;
      let id = this.id;

      axios.post(API_BASE_URL+'/search/all/comm', {id, page, keyWord, region}, {
        headers: {
          'Authorization': 'Bearer '+ cookies.get('access_token'),
          'Accept' : 'application/json',
          'Content-Type': 'application/json'
        }
      })
          .then((res) => {
            this.commPage++;
            this.$store.state.searchComm = res.data
          })

    },
    fetchSearchAllPage() {
      let keyWord = this.keyWord;
      let page = 0;
      axios.post(API_BASE_URL+'/search/all/getTotal', {page, keyWord}, {
        headers: {
          'Authorization': 'Bearer '+ cookies.get('access_token'),
          'Accept' : 'application/json',
          'Content-Type': 'application/json'
        }
      })
          .then((res) => {
            alert(res.data)
            this.$store.state.searchAllTotalPage = res.data
          })
    }
  },
  computed: {
    ...mapState(['searchProduct']),
    ...mapState(['searchComm']),
    ...mapState(['searchAllTotalPage']),
  },
  watch: {
    keyWord(){
      this.productPage = 0;
      this.commPage = 0;
      this.fetchSearchProduct(this.keyWord);
      this.fetchSearchComm(this.keyWord);
    }
  },
  mounted() {
    //alert(this.keyWord);
    //await this.fetchSearchAllPage();
    this.fetchSearchProduct(this.keyWord);
    this.fetchSearchComm(this.keyWord);
  }
}
</script>

<style scoped>
#search_list{
  border-left: 1px solid #e9ecef;
  border-right: 1px solid #e9ecef;
  width: 800px;
  margin: 0 auto 20px;
  line-height: 24px;
  cursor: pointer;
  background-color: rgba(229, 225, 225, 0.47);
}

.card-top {
  width: 220px;
  margin-right: 44px;
  margin-bottom: 56px;
}
.card-photo {
  width: 100%;
  position: relative;
  overflow: hidden;
  border-radius: 12px;
  background-color: #f8f9fa;
  box-shadow: inset 0 0 0 1px rgb(0, 0, 0, 15%);
  box-sizing: border-box;
}
.card-desc {
  margin-top: 12px;
}
.card-title {
  font-size: 16px;
  letter-spacing: -0.02px;
  color: #212529;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  margin-bottom: 4px;
  line-height: 1.5;
  font-weight: normal;
}
.card-counts {
  color: #868e96;
  font-size: 13px;
}
#community_list{
  width: 800px;
  margin: 30px auto 20px;
  line-height: 24px;
  background: #fff;
  cursor: pointer;
}
#community_writer{
  font-size: 14px;
  height: 45px;
  line-height: 18px;
  padding: 10px 0;
  color: #868e96;
  display: flex;
  position: relative;
}
p {
  display: block;
  margin: 0;
}
#community_hr{
  display: block;
  height: 1px;
  border: 0;
  border-top: 1px solid #e9ecef;
  padding: 15px 0 0;
}
#community_icon{
  padding: 10px 0;
  color: #868e96;
  width: 50%;
  display: flex;
  font-size: 15px;
  align-items: center;
  justify-content: end;
}
.noMessage {
  min-height: 180px;
  font-size: 18px;
  padding: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
  opacity: 0.5;
}
</style>