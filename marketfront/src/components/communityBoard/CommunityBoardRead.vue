<template>
    <div id='community_read'>
            <v-form enctype="multipart/form-data">
                <table>
                    <!-- <v-row wrap>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <v-btn @click="goPage" class="backBtn" color="grey darken-3" style="box-shadow:none" dark fab small><v-icon>mdi-arrow-left</v-icon></v-btn>
                    </v-row> -->
                    <div id='user_profile'>
                        <h2 id='nickname'>{{communityBoard.member.name}}
                            <span id='region_name'>{{communityBoard.member.address}}</span>
                        </h2>
                        <div id='profile_detail'>매너온도 {{communityBoard.member.temperature}}°C</div>
                        <div id='profile_image'>
                            <v-img id="image" :src="require(`@/assets/profile/${communityBoard.member.profileImg}`)"/>
                        </div>
                    </div>
                    <div id='read_title_content'>
                        <div style="display: flex; justify-content: space-between;">
                            <div>
                                <v-chip color="#E6F3E6">
                                    <b>{{communityBoard.usedSubject}}</b>
                                </v-chip>
                                <b style="margin-top:3px"> {{communityBoard.title}}</b>
                            </div>
                            <div>
                                <router-link style=text-decoration:none; :to="{ name: 'CommunityBoardModifyPage', params: { boardNo: communityBoard.boardNo.toString() }}">
                                    <v-btn v-if="this.login.name == communityBoard.writer" type="button" id='my_button' depressed>수정</v-btn> 
                                </router-link>    
                                <v-btn v-if="this.login.name == communityBoard.writer" @click="onDelete" id='my_button' depressed style="margin-left:12px">
                                    삭제
                                </v-btn>   
                            </div>
                        </div>
                        <v-row>
                            <v-carousel hide-delimiters height="auto">
                                <v-carousel-item 
                                v-for="(file, index) in checkFile()" :key="index" style="text-align:center">
                                <img 
                                :src="require(`@/assets/uploadImg/community/${file}`)" class="preview"/>
                                </v-carousel-item>
                            </v-carousel>
                        </v-row>
                        <v-row>
                            <v-col>
                                <div style="padding: 10px">
                                    {{communityBoard.content}}
                                    <div id='read_date_heart'>
                                        {{communityBoard.createdDate}}
                                        <div>
                                            <v-btn @click=like class="success" depressed fab x-small>
                                            <v-icon>mdi-heart</v-icon>
                                            </v-btn>
                                            {{ communityBoard.likeCnt }}
                                        </div>
                                    </div>
                                </div>
                            </v-col>
                        </v-row>
                    </div>
                    <div v-if="communityBoard.placeName != ''">
                    <v-icon>mdi-map-marker-outline</v-icon><b><a v-bind:href="communityBoard.placeUrl" style="text-decoration:none"  target="_blank">{{communityBoard.placeName}}</a></b><br>
                    </div>
                    <!-- <div v-if="communityBoard.placeName == ''">
                    </div> -->
                        
                    <!--<v-btn @click=comment color="purple darken-1" style="box-shadow:none" dark fab small>
                        <v-icon>mdi-comment</v-icon>
                    </v-btn>
                     &nbsp;&nbsp;
                    <div class="Cnt">
                            {{ communityBoard.commentCnt }}
                    </div> -->
                                                 
                </table>
            </v-form>
    </div>
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
#community_read{
    /* border-radius: 8px; */
    border-left: 1px solid #e9ecef;
    border-right: 1px solid #e9ecef;
    width: 800px;
    margin: 0 auto;
    margin-bottom: 20px;
    padding: 40px;
    line-height: 24px;
    background: #fff;
}
#user_profile{
    display: block;
    position: relative;
    border-bottom: 1px solid #eaebee;
    /* margin-top: 40px;
    margin-bottom: 40px; */
}
#nickname{
    text-align: left;
    font-size: 22px;
    font-weight: 600;
    letter-spacing: -0.6px;
    margin-bottom: 10px;
    vertical-align: middle;
    margin-left: 80px;
}
#region_name{
    font-size: 17px;
    color: #212529;
    font-weight: normal;
    vertical-align: middle;
    margin-left: 6px;
}
#profile_detail{
    margin: 10px 0;
    margin-left: 80px;
    font-size: 15px;
    letter-spacing: -0.6px;
    color: #868e96;
}
#profile_image{
    display: block;    
    position: absolute;
    top: 0;
    left: 0;
}
#image{
    width: 58px;
    height: 58px;
    -o-object-fit: cover;
    object-fit: cover;
    border-radius: 50%;
    -webkit-border-radius: 50%;
}
#read_title_content{
    padding: 32px 0 0 0;
    width: 677px;
    /* margin: 0 auto; */
}
#read_date_heart{
    font-size: 13px;
    line-height: 1.46;
    letter-spacing: -0.6px;
    color: #868e96;
    display: flex;
    align-items: center;
    justify-content: space-between;
}
#my_button{
  letter-spacing: -2%;
  background-color: white;
  color: #212124;
  height: 40px;
  font-weight: 700;
  padding: 5px 10px;
  border-radius: 3px;
  border: 1px solid #d1d3d8;
}
p {
  display: block;
  margin: 0px;
}
/* @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap');
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
     margin-top:8px;
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
}*/
.preview {
    position: relative;
    margin-left: auto;
    margin-right:auto;
    max-width:350px;
    height:350px; 
} 
</style>