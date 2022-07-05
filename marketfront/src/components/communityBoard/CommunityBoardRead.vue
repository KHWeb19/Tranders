<template>
    <v-container>
        <br><br>
            <v-form enctype="multipart/form-data">
                <table>
                    <br>
                    <v-row wrap>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <v-btn @click="goPage" class="backBtn" color="grey darken-3" style="box-shadow:none" dark fab small><v-icon>mdi-arrow-left</v-icon></v-btn>
                    </v-row>
                         <v-card-text>
                        <v-chip class="subject" color="light green accent-2">
                            {{communityBoard.usedSubject}}
                        </v-chip>
                    </v-card-text>
          <span class="mr-2"><b>{{communityBoard.writer}}</b></span>
          <span class="mr-2">&bull;&nbsp;{{communityBoard.region}}</span>
          <hr><br>
                    <v-row justify="center">
                            <div class="label2">
                            {{ communityBoard.title}}
                            </div>
                    </v-row>
                    <br>
                    <v-row  wrap justify="center">
                        <v-carousel hide-delimiters  height="auto">
                            <v-carousel-item 
                            v-for="(file, index) in checkFile()" :key="index" style="text-align:center">
                            <img 
                            :src="require(`@/assets/uploadImg/community/${file}`)" class="preview"/>
                            </v-carousel-item>
                        </v-carousel>
                    </v-row>
                    <v-row justify="center" class="mt-7">
                             <v-col cols="12">
                            <v-textarea style="white-space:pre-line" cols="75" rows="7" 
                            outlined color="indigo darken-4" readonly
                            :value="communityBoard.content">
                            </v-textarea>
                    </v-col>
                    </v-row>
                    <v-container v-if="communityBoard.placeName != ''">
                    <v-icon>mdi-map-marker-outline</v-icon><b><a v-bind:href="communityBoard.placeUrl" style="text-decoration:none"  target="_blank">{{communityBoard.placeName}}</a></b><br>
                    </v-container>
                    <v-container v-if="communityBoard.placeName == ''">
                    </v-container>
                    <br><br>
                    <v-row wrap>
                        <v-btn @click=like color="red darken-1" style="box-shadow:none" dark fab small>
                        <v-icon>mdi-heart</v-icon>
                    </v-btn>
                    &nbsp;&nbsp;
                    <div class="Cnt">
                            {{ communityBoard.likeCnt }}
                    </div>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
                    <v-btn @click=comment color="purple darken-1" style="box-shadow:none" dark fab small>
                        <v-icon>mdi-comment</v-icon>
                    </v-btn>
                     &nbsp;&nbsp;
                    <div class="Cnt">
                            {{ communityBoard.commentCnt }}
                    </div>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <router-link style=text-decoration:none; :to="{ name: 'CommunityBoardModifyPage', params: { boardNo: communityBoard.boardNo.toString() }}">
            
        <v-btn v-if="this.login.name == communityBoard.writer" type="button" color="blue-grey darken-1" style="box-shadow:none" dark fab small><v-icon>mdi-eraser</v-icon>    
        </v-btn>    
        </router-link> 
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <v-btn v-if="this.login.name == communityBoard.writer" @click="onDelete" color="blue-grey darken-4" style="box-shadow:none" dark fab small>
                        <v-icon>mdi-trash-can-outline</v-icon>
                    </v-btn>   
                    </v-row>
                                          
                </table>
            </v-form>
    </v-container>
</template>

<script>
import Vue from 'vue'
import cookies from "vue-cookies";
Vue.use(cookies)

import axios from 'axios'
// import { mapActions } from 'vuex'

export default {
    name: 'CommunityBoardRead',
    props: {
        communityBoard: {
            type: Object,
            required: true
        }
    },
    data () {
        return {
            login: {
                id: cookies.get('id'),
                name: cookies.get('name'),
                region: cookies.get('region'),
                access_token: cookies.get('access_token'),
            },
        fileName1: this.communityBoard.fileName1,
        files:[],
        }
    },
    methods: {
        goPage () {
            this.$router.push('/Tranders/CommunityList')
        },
        onDelete () {
            const { boardNo, fileName1, fileName2, fileName3, fileName4, fileName5,
            fileName6, fileName7, fileName8, fileName9, fileName10 } = this.communityBoard
            //alert('지우는 게시물 번호: ' + boardNo)
            axios.delete(`http://localhost:7777/communityboard/${boardNo}`, 
            {fileName1,  fileName2, fileName3, fileName4, fileName5, fileName6, fileName7, fileName8, fileName9, fileName10})
                    .then(() => {
                        alert('게시글이 삭제되었습니다.')
                        this.$router.push({ name: 'CommunityBoardListPage' })
                    })
                    .catch(() => {
                        alert('삭제 실패! 문제 발생!')
                    })
        },
        checkFile () {
            var files = [ this.communityBoard.fileName1, this.communityBoard.fileName2, this.communityBoard.fileName3, this.communityBoard.fileName4, this.communityBoard.fileName5,
            this.communityBoard.fileName6, this.communityBoard.fileName7, this.communityBoard.fileName8, this.communityBoard.fileName9, this.communityBoard.fileName10]
            for(var i = 0; i < files.length; i++) {
                console.log(i)
                if(files[i] != null) {
                    console.log(this.files[i])
                } else {
                    files.splice(i)
                }
            }
            return files
        },
        like () {
            const { boardNo } = this.communityBoard
            const { who } = this
            console.log(boardNo, who)
            if (this.login.name == this.communityBoard.writer){
                alert("자신의 글은 좋아요 할 수 없습니다!")
            }else{
            axios.post(`http://localhost:7777/communityboard/${boardNo}/like`, {who})
                 .then((res) => {
                    if (res.data == false) {
                    alert("이미 좋아요 하였습니다!")
                } else {
                    alert("좋아요")
                    history.go(0)
                }
            })
            }
        },        
    }
}
</script>
<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap');
.label{
    margin-right:3%;
    text-align: center;
    padding-top: 10px;
    font-size:18pt;
    font-family: 'Noto Sans KR', sans-serif;
}
table{
    position: relative;
    background-color: rgb(191, 246, 201);
    /* rgb(185, 255, 75) (210, 255, 140) */
    padding-left: 5%;
    padding-right: 5%;
    padding-top: 0.5%;
    padding-bottom: 2.5%;
    margin-left:auto;
    margin-right:auto;
    zoom:80%;
}
hr {
    height: 1px;
    background-color: #777777;
    border: none;
}
.label2{
     font-family: 'Noto Sans KR', sans-serif;
     /* margin-top:8px; */
     font-size:20pt;
}
.backBtn {
    margin-top:0%;
    margin-left:-8%;
    zoom:1;
}
.Cnt {
    font-size: 20pt;
    font-family: 'Noto Sans KR', sans-serif;
     
}
.v-combobox, .v-text-field, .v-textarea, #files{
    font-family: 'Noto Sans KR', sans-serif;
}
.writeBtn {
    position: relative;
    margin-top:0.5%;
    margin-left:1%;
    zoom:1;
    float:left;
}
.writeBtn2 {
    position: absolute;
    zoom:1;
    margin-top:0.5%;
    margin-left:79%;
    float:left;
}
.titleFloat {
    float:left;
    margin-top:-1%;
    margin-right:3%;
}
.subject {
    font-family: 'Noto Sans KR', sans-serif;
    margin-right:1.5%;
    zoom:130%;
}
.preview {
    position: relative;
    margin-left: auto;
    margin-right:auto;
    max-width:350px;
    height:350px; 
}
</style>