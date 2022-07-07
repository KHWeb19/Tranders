<template>
  <div>
   <!-- <div class="btn-cover">
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
    <br> -->
    <div id='community_list' v-if="!communityBoards || (Array.isArray(communityBoards) && communityBoards.length === 0)" elevation="10" outlined width="75%" class="mx-auto">
      <br>
      <div>
        <span class="mr-2">작성된 게시글이 없습니다.</span>
      </div>
      <br>
    </div>
    <div id='community_list' v-else v-for="communityBoard in paginatedData" :key="communityBoard.boardNo" @click="goRead(communityBoard.boardNo)">
      <p>
         <v-chip color="#E6F3E6">
           <b>{{communityBoard.usedSubject}}</b>
         </v-chip>         
         <b> {{communityBoard.title}}</b>      
      </p>
      <div id='community_title_content'>
        <router-link id=sj style=text-decoration:none; :to="{ name: 'CommunityBoardReadPage',
        params: { boardNo: communityBoard.boardNo.toString() } }">
        <p>{{ communityBoard.content}}</p>
        </router-link>
      </div>
      <div id="community_writer">
        <span>{{communityBoard.writer}}</span>
        <span> &bull; {{communityBoard.region}}</span>
        <span id='community_date'>{{communityBoard.createdDate}}</span>
      </div>
      <hr id='community_hr'>
      <div id='community_icon'>
        <v-icon>mdi-heart-outline</v-icon>
        <span> {{communityBoard.likeCnt}}</span>
        &nbsp;
        <v-icon>mdi-comment-outline</v-icon>
        <span> {{communityBoard.commentCnt}}</span>
      </div>
    </div>
    
    <div class="btn-cover">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
        prev             
      </button>
      <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }}</span>
      <button :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn">
        next
      </button>                      
    </div>
  </div>  
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
  },
  goRead(payload){
    this.$router.push({name: 'CommunityBoardReadPage',
              params: { boardNo: payload.toString() }})
  }
}
}                                            
</script>

<style scoped>

@import url('https://fonts.googleapis.com/css2?family=Cute+Font&display=swap');
#community_list{
    /* border-radius: 8px; */
    border: 1px solid #e9ecef;
    width: 800px;
    margin: 0 auto;
    margin-bottom: 20px;
    padding: 40px;
    line-height: 24px;
    background: #fff;
    cursor: pointer;
}
#community_title_content{
  padding-top: 20px;
}
#community_writer{
  font-size: 14px;
  line-height: 18px;
  margin-top: 6px;
  padding: 10px 0px;
  color: #868e96;
  position: relative;
}
#community_date{
  position: absolute;
  padding: 10px 0px;
  right: 0;
  bottom: 0;
}
#community_hr{
  display: block;
  height: 1px;
  border: 0;
  border-top: 1px solid #e9ecef;
  padding: 0;
}
#community_icon{
  padding: 10px 0px;
  color: #868e96;
}
p {
  display: block;
  margin: 0px;
}
#sj{
  font-size: 17px;
  color:black;
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