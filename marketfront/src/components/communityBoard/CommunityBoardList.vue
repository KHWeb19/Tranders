<template>
<v-container>
    <br><br>
    <v-card v-if="!communityBoards || (Array.isArray(communityBoards) && communityBoards.length === 0)" elevation="10" outlined width="75%" class="mx-auto">
      <v-card-title>
        <v-icon large color="red">
         
        </v-icon>
        <span style="width:5px;"></span>
        <span class="mr-2"></span>
      </v-card-title>
      <v-card-text>
        <span class="mr-2">작성된 게시글이 없습니다.</span>
        <br>
      </v-card-text>
      <v-card-actions>
        <!-- <v-btn @click="prev" color="blue-grey" text> -->
          <v-icon>
          </v-icon>
          <span class="mr-2"></span>
        <!-- </v-btn> -->
      </v-card-actions>
    </v-card>
    <v-card v-else v-for="communityBoard in paginatedData" :key="communityBoard.boardNo" elevation="10" outlined width="75%" class="mx-auto">
      <!-- v-else v-for="communityBoard in communityBoards" :key="communityBoard.boardNo"    -->
      <v-card-text>
        <!-- <v-icon large color="red">
        </v-icon>
        <span style="width:5px;"></span> -->
         <v-chip color="light green accent-1">
           {{communityBoard.usedSubject}}
         </v-chip>               
      </v-card-text>
      <v-card-text>
        <router-link id=sj style=text-decoration:none; :to="{ name: 'CommunityBoardReadPage',
        params: { boardNo: communityBoard.boardNo.toString() } }">
        {{ communityBoard.title}}
        </router-link>        
        <!-- <span class="mr-2">{{communityBoard.title}} -->
        <!-- </span> -->
        <br>
      </v-card-text>
      <!-- <div align="left">
        &nbsp;&nbsp;&nbsp;
       <v-icon large>
            mdi-account-circle-outline
       </v-icon>&nbsp;&nbsp;
        <v-icon large>
            mdi-heart
        </v-icon>
      </div>
      <br> -->
      <!-- <hr> -->
      <v-card-actions>
        <!-- <v-btn @click="prev" color="blue-grey" text> -->
          <span class="mr-2">{{communityBoard.writer}}작성자</span>
          <v-icon>
              mdi-map-marker-outline
          </v-icon>
          <span class="mr-2">{{communityBoard.dong}}지역</span>
        <!-- </v-btn> -->
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
        <!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
        <!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
        <!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
        <!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
        <!-- <v-btn @click="prev" color="blue-grey" text> -->
          <!-- <v-icon>
              mdi-comment
          </v-icon>
          <span class="mr-2">{{communityBoard.commentCnt}}댓글 수</span> -->
          <span class="mr-2">{{communityBoard.createdDate}}</span>
        <!-- </v-btn> -->
      </v-card-actions>
      <hr>
      &nbsp;&nbsp;&nbsp;
      <v-icon>
              mdi-heart
      </v-icon>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <v-icon>
              mdi-comment
      </v-icon>
      <span class="mr-2">{{communityBoard.commentCnt}}댓글 수</span>
    </v-card>
    <div class="btn-cover">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
        prev
        <!-- <v-icon>
          mdi-page-first
        </v-icon> -->               
      </button>
      <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} page</span>
      <button :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn">
        next
        <!-- <v-icon>
          mdi-page-last
        </v-icon> -->
      </button>                      
    </div>
  </v-container>  
</template>

<script>

export default {
    name: 'CommunityBoardList',
    props: {
        communityBoards:{
            type: Array,
        },
        pageSize: {
            type: Number,
            required: false,
            default: 5
        }
    },
    data () {
        return {
          pageNum: 0,
          search:'',
            /*headerTitle: [
                { text: '번호', value: 'boardNo', width:'50px', align:'center'},
                { text: '말머리', value: 'usedSubject', width: "100px", align:'center'}, 
                { text: '제목', value: 'title', width: "200px", align:'center'},
                { text: '작성자', value: 'writer', width: "100px", align:'center' },
                { text: '지역', value: 'address', width: "100px", align:'center' },
                { text: '좋아요', value: 'likesCnt', width: "50px", align:'center'},   
            ],*/
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
        
        timeForToday(value) {
        const today = new Date();
        const timeValue = new Date(value);

        const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);
        if (betweenTime < 1) return '방금전';
        if (betweenTime < 60) {
            return `${betweenTime}분전`;
        }

        const betweenTimeHour = Math.floor(betweenTime / 60);
        if (betweenTimeHour < 24) {
            return `${betweenTimeHour}시간전`;
        }

        const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
        if (betweenTimeDay < 365) {
            return `${betweenTimeDay}일전`;
        }

        return `${Math.floor(betweenTimeDay / 365)}년전`;
        }
    },
    
    methods: {
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
@import url('https://fonts.googleapis.com/css2?family=Cute+Font&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Hubballi&display=swap');
#vb{
    font-family:  'Cute Font', cursive;
    position: relative;
}
#sj{
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
.sj {
   width: 67px;
   height: 20px;
   border: 1px solid red; 
   border-radius: 40%;
   border-color: black;
   overflow: hidden;
}
</style>