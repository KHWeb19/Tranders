<template>
  <div id="search_list" style="position: relative">
    <!-- 중고 물품 -->
    <div v-for="product in searchProduct" :key="product.productNo">
      {{product.productNo}}
    </div>

    <div>
      <v-btn @click="add">추가</v-btn>
    </div>
    <!-- 현재 물품 -->
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
      word: ''
    }
  },
  methods: {
    fetchSearchProduct(keyWord){
      let page = 0;
      this.word = keyWord;
      alert(keyWord)
      axios.post(API_BASE_URL+'/search/all/product', {page, keyWord}, {
        headers: {
          'Authorization': 'Bearer '+ cookies.get('access_token'),
          'Accept' : 'application/json',
          'Content-Type': 'application/json'
        }
      })
          .then((res) => {
            console.log(res)
           this.$store.state.searchProduct = res.data;
          })
    },
    add(){
      let page = 1;
      alert(page)
      let keyWord = this.word;
      alert(keyWord)

      axios.post(API_BASE_URL+'/search/all/product', {page, keyWord}, {
        headers: {
          'Authorization': 'Bearer '+ cookies.get('access_token'),
          'Accept' : 'application/json',
          'Content-Type': 'application/json'
        }
      })
          .then((res) => {
            console.log(res.data)
            this.$store.state.searchProduct.push(...res.data);
            console.log(this.searchProduct)
          })
    }
  },
  computed: {
    ...mapState(['searchProduct']),
  },
  watch: {
    keyWord(){
      this.fetchSearchProduct(this.keyWord);
    }
  },
  mounted() {
    alert(this.keyWord);
    this.fetchSearchProduct(this.keyWord);
    //this.fetchSearchComm(this.keyWord);
  }
}
</script>

<style scoped>
#search_list{
  align-items: center;
  width: 100%;
  max-width: 800px;
  margin: 0 auto;
  padding: 40px 40px 40px 40px; /*위, 오른쪽, 아래, 왼쪽 */
}
</style>