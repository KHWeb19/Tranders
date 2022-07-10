<template>
  <div>
    <after-login-view :num="3" @keyWordTest="keyWordTest"></after-login-view>
    <search-list-view :keyWord="keyWords"></search-list-view>
  </div>
</template>

<script>
import AfterLoginView from "@/components/home/AfterLoginView";
import SearchListView from "@/components/search/SearchListView";
export default {
  name: "SearchKeyWordListPage",
  components: {SearchListView, AfterLoginView},
  props: {
    keyWord: {
      type: String,
    },
  },
  data(){
    return {
      keyWords: '',
    }
  },
  methods: {
    keyWordTest(payload){
      console.log(payload)
      this.keyWords = payload;
    }
  },
  created() {
    this.keyWords = this.keyWord;
  }
}
</script>

<style scoped>

</style>