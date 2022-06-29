<template>
<v-container>
    <v-row wrap>
        <v-btn @click="goPage" class="backBtn" color="grey darken-3" style="box-shadow:none" dark fab small><v-icon>mdi-arrow-left</v-icon></v-btn>
    </v-row> 
    <br><br>
    <v-card v-if="!searchList || (Array.isArray(searchList) && searchList.length === 0)" elevation="10" outlined width="75%" class="mx-auto">
      <br>
      <v-card-text>
        <span class="mr-2">작성된 게시글이 없습니다.</span>
      </v-card-text>
      <br>
    </v-card>
    <v-card v-else v-for="communityBoard in searchList" :key="communityBoard.boardNo" elevation="10" outlined width="75%" class="mx-auto">
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
      &nbsp;&nbsp;&nbsp;
      <v-icon>
              mdi-heart
      </v-icon>
      &nbsp;
      <span class="mr-2">{{communityBoard.likeCnt}}</span>
      &nbsp;&nbsp;&nbsp;
      <v-icon>
              mdi-comment
      </v-icon>
      <span class="mr-2">{{communityBoard.commentCnt}}</span>
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
export default {
  name: 'SearchList',
  props: {
    searchList:{
      type: Array,
      require: true
    },
  },
  data () {
    return {
      pageNum: 0,
      pageSize: 10
    }
  },
  computed: {
    pageCount () {
      let listLeng = this.searchList.length,
      listSize = this.pageSize,
      page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1
      return page;
    },
    paginatedData () {
      const start = this.pageNum * this.pageSize,
      end = start + this.pageSize;
      return this.searchList.slice(start, end);
    },
  },
  methods: {
    goPage () {
      this.$router.push('/Tranders/CommunityList')
    },
    nextPage () {
      this.pageNum += 1;
    },     
    prevPage () {
      this.pageNum -= 1;
    },
  }
}
</script>

<style scoped>
#sj{
  font-size: 17px;
  color:black;
}
#cd{
  margin-left:68%;
}
.backBtn {
  margin-top:3%;
  margin-left:13.5%;
  zoom:0.8;
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