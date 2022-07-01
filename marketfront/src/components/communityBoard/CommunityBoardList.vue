<template>
<v-container>
   <div class="btn-cover">
    <input type="text" 
           class="search" 
           v-model="keyWord" 
           cols="70" 
           placeholder="Search" />
    <v-btn @click="searchstart()" 
            class="search-btn"
            color="light green accent-2"
            style="box-shadow:none"
            dark fab small>
            <v-icon>mdi-magnify</v-icon>
    </v-btn>
    </div>
    <br>
    <v-card v-if="!communityBoards || (Array.isArray(communityBoards) && communityBoards.length === 0)" elevation="10" outlined width="75%" class="mx-auto">
      <br>
      <v-card-text>
        <span class="mr-2">작성된 게시글이 없습니다.</span>
      </v-card-text>
      <br>
    </v-card>
    <v-card v-else v-for="communityBoard in paginatedData" :key="communityBoard.boardNo" elevation="10" outlined width="75%" class="mx-auto">
      <v-card-text>
         <v-chip color="light green accent-1">
           <b>{{communityBoard.usedSubject}}</b>
         </v-chip>               
      </v-card-text>
      <v-card-text>
        <router-link id=sj style=text-decoration:none; :to="{ name: 'CommunityBoardReadPage',
        params: { boardNo: communityBoard.boardNo.toString() } }">
        {{ communityBoard.title}}
        </router-link>        
        <br>
      </v-card-text>
      <v-card-actions>
          <span class="mr-2">{{communityBoard.writer}}</span>
          <span class="mr-2">&bull;&nbsp;{{communityBoard.region}}</span>
      </v-card-actions>
      <hr>
      <v-icon>
              mdi-heart
      </v-icon>
      &nbsp;
      <span class="mr-1">{{communityBoard.likeCnt}}</span>
      &nbsp;
      <v-icon>
              mdi-comment
      </v-icon>
      &nbsp;
      <span class="mr-1">{{communityBoard.commentCnt}}</span>
      <span id="cd" class="mr-2">{{communityBoard.createdDate}}</span>
    </v-card>
    <div class="btn-cover">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
        prev             
      </button>
      <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} page</span>
      <button :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn">
        next
      </button>                      
    </div>
  </v-container>  
</template>

<script>
import axios from 'axios'

export default {
  name: 'CommunityBoardList',
  props: {
    communityBoards:{
      type: Array,
    },
    pageSize: {
      type: Number,
      required: false,
      default: 10
    }
  },
  data () {
    return {
      pageNum: 0,
      searchList:[],
      keyWord:''
    }
  },
computed: {
  pageCount () {
    let listLeng = this.communityBoards.length,
    listSize = this.pageSize,
    page = Math.floor(listLeng / listSize);
    if (listLeng % listSize > 0) page += 1
    return page;
  },      
  paginatedData () {
    const start = this.pageNum * this.pageSize,
    end = start + this.pageSize;
    return this.communityBoards.slice(start, end);
  },
},
methods: {
  nextPage () {
    this.pageNum += 1;
  },
  prevPage () {
    this.pageNum -= 1;
  },
  searchstart() {
    const keyWord = this.keyWord
    console.log(keyWord)
    axios.post('http://localhost:7777/communityboard/search',{ keyWord })
    .then((res) => {
      console.log("검색 성공")
      console.log(res.data)
      this.$router.push({name: 'SearchListPage',
      params: { searchList: res.data} })
    })
  }
}
}                                            
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Cute+Font&display=swap');
#sj{
  font-size: 17px;
  color:black;
}
#cd{
  margin-left:68%;
}
.search{
  border-bottom: 3px solid rgb(187, 246, 202);
  width: 100px;
  outline: none;
  margin-left: 730px;   
}
.search-btn{
  margin-left:5px;
  zoom:0.8;
  color:black;
}
.btn-cover {
  margin-top: 1.5rem;
  text-align: center;
}
.btn-cover .page-btn {
  width: 5rem;
  height: 2rem;
  letter-spacing: 0.5px;
}
.btn-cover .page-count {
  padding: 0 1rem;
}
</style>