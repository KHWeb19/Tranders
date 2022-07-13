<template>
    <v-container>
        <v-form enctype="multipart/form-data" @submit.prevent="onCommentSubmit">
            <table>
                <v-row justify="start">
                    &nbsp;&nbsp;
                    <v-col cols="1" style="font-size:20pt" class="label">
                        Comment
                    </v-col>
                </v-row>
                <v-row justify="start">
                    <v-col cols="4">
                    <v-img :src="image" class="preview" alt=""/>
                    </v-col>
                </v-row>   
                <v-row wrap justify="center">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <label for="files"><v-icon class="vb" large>mdi-image-outline</v-icon></label>
                    <input type="file" id="files" ref="files" dense style="width:0px" 
                            multiple v-on:change="handleFileUpload()"/>
                            <v-dialog v-model="dialog" persisten max-width="1000">
                                <template v-slot:activator="{ on }">
                                    <v-btn v-on="on"  onclick="" color="blue-grey" text>
                                        <v-icon class="vb" large>mdi-map-marker-outline</v-icon>
                                        <v-text-field style="width:140px" placeholder="장소를 등록하세요." v-model="placeName"/>
                                    </v-btn>                              
                                </template>
                                <v-card>
                                    <kakao-map></kakao-map>        
                                </v-card>
                            </v-dialog>
                            <div class="reply">
                                <v-col cols="10">
                                    <v-textarea color="indigo darken-4" cols="45" rows="1" 
                                                placeholder=" 댓글을 입력해주세요." v-model="comment"/>
                                    <v-btn type="submit" class="commentBtn" color="light green accent-2 black--text" fab x-small dark><v-icon> mdi-arrow-left-bottom-bold</v-icon></v-btn>
                                </v-col>
                            </div>                                                                                                         
                </v-row>
                <v-row v-for="commentList in communityComments" 
                        :key="commentList.commentId" justify="start" class="commentList">
                <v-col cols="2">
                    &nbsp;&nbsp;{{ commentList.commentWriter}}
                </v-col>
                <v-col cols="2">
                    {{ commentList.commentRegion }} 
                </v-col>        
                <v-col cols="5">
                    {{ commentList.comment}} 
                </v-col>
                <v-col cols="3">
                    {{ commentList.updDate | timeForToday}}
                </v-col>
                <v-container v-if="commentList.placeName != ''">
                    <v-icon>mdi-map-marker-outline</v-icon><b><a v-bind:href="commentList.placeUrl" style="text-decoration:none"  target="_blank" >{{commentList.placeName}}</a></b><br>
                </v-container>
                <v-container v-if="commentList.placeName == ''">
                </v-container>
                <div class="imgArea">
                <div v-if="commentList.fileName===null">                        
                </div>
                <v-img v-else :src="require(`@/assets/uploadImg/community/${commentList.fileName}`)"></v-img>
                </div>               
                <template v-if="commentList.commentWriter == commentWriter">
                    <v-dialog persisten max-width="330">
                            <template v-slot:activator="{ on }">
                                <v-btn @click="modifyComment(commentList)" v-on="on" type="button" class="comModifyBtn" color="blue-grey darken-1" style="box-shadow:none" fab x-small dark>
                                    <v-icon>mdi-eraser</v-icon>    
                                </v-btn>                           
                            </template>
                            <v-card>
                                 <v-text-field style="width:300px" v-model="ediComment" />
                                  <v-icon large>mdi-image-outline</v-icon>
                        <input type="file" id="files1" ref="files1" dense style="width:193px"
                                multiple v-on:change="handleFileUpload1()"/>
                                <v-dialog v-model="dialog" persisten max-width="1000">
                            <template v-slot:activator="{ on }">
                                <v-btn v-on="on"  onclick="" color="blue-grey" text>
                                    <v-icon large>mdi-map-marker-outline</v-icon>
                                    <v-text-field style="width:210px" v-model="ediPlaceName"/>
                                </v-btn>
                            </template>
                            <v-card>
                                <c-kakao-map></c-kakao-map>
                            </v-card>
                        </v-dialog>          
                        <v-btn @click=onModifySubmit() class="writeBtn" color="light green accent-4" style="box-shadow:none" dark fab x-small><v-icon> mdi-check</v-icon></v-btn>
                            </v-card>
                    </v-dialog>         
                    <v-btn class="comDeleteBtn" @click=comDelete(commentList.commentId) color="blue-grey darken-4" style="box-shadow:none" fab x-small dark>
                        <v-icon>mdi-trash-can-outline</v-icon>
                    </v-btn> 
                </template>
                </v-row>
            </table>
        </v-form>
    </v-container>
</template>

<script>
import EventBus from '@/eventBus.js'
import cookies from "vue-cookies";
import axios from 'axios'
import { mapActions, mapState } from 'vuex'
import KakaoMap from '../../views/KakaoMap.vue';
import cKakaoMap from '../../views/KakaoMap.vue';
export default {
    components: { KakaoMap,cKakaoMap },
    name:'CommentList,CommunityBoardReadPage',
    props: {
        communityComments : {
            type:Array
        },
          boardNo: {
            type: String,
            required: true
        }
    },
    mounted() {
        this.fetchCommunityCommentsList(this.commentId)
        this.fetchCommunityCommentsList(this.boardNo)
    },
    data () {
        return {
            login: {
                name: cookies.get('name'),
                id: cookies.get("id"),
                region: cookies.get('region'),
                access_token: cookies.get('access_token'),
            },
            placeName:'',
            placeUrl:'',
            image :'',
            files:'',
            comment:'',
            response: '',
            fileName: '',
            ediComment:'',
            ediPlaceName:'',
            ediPlaceUrl:'',
            ediFileName:'',
            files1:null,
            ediImage:'',
        }
    },
    created () {
        EventBus.$on('placeRegister', (payload) => {
            this.placeUrl = payload[0]
            this.placeName = payload[1]
            console.log(payload) 
        })
        EventBus.$on('placeRegister1', (payload) => {
            this.ediPlaceUrl = payload[0]
            this.ediPlaceName = payload[1]
            console.log(payload) 
        })
       this.commentWriter = this.login.name
       this.commentRegion = this.login.region
       this.ediComment = this.commentList.comment
       this.ediPlaceName = this.commentList.placeName
       this.ediPlaceUrl = this.commentList.placeUrl
       this.ediFileName = this.commentList.FileName
    },
    computed: {
        ...mapState(['CommunityCommentsList']),
    },
    methods: {
        ...mapActions(['fetchCommunityCommentsList']),
        comDelete (commentId) {
            axios.delete(`http://localhost:7777/communityboard/comment/${commentId}`, {commentId})
                    .then(() => {
                        alert('댓글이 삭제되었습니다!')
                        history.go(0)
                    })
                    .catch(() => {
                        alert('삭제 실패! 문제 발생!')
                        console.log(commentId, this.boardNo)
                    })
        },
        onCommentSubmit () {
            const { comment, commentWriter, commentRegion, placeName, placeUrl } = this
            const file =  this.$refs.files.files[0]
            this.$emit('submit', {comment, commentWriter, commentRegion, placeName, placeUrl, file})
            console.log(comment, commentWriter, commentRegion, file)
            },

        handleFileUpload () {
            console.log('이미지 첨부')
            var image = this.$refs['files'].files[0]
            const url = URL.createObjectURL(image)
            this.image = url
            this.files = this.$refs.files.files[0]
        },

         handleFileUpload1 () {
            console.log('이미지 수정')

            this.files1 = this.$refs.files1[0].files
            console.log(this.files1[0])

           /* var ediImage = this.$refs['files1'].files[0]
            const url = URL.createObjectURL(ediImage)
            this.ediImage = url
            this.files1 = this.$refs.files1.files1[0]*/

        },
      modifyComment(commentList){
        this.ediComment = commentList.comment;
        this.ediPlaceName = commentList.placeName;
        this.ediPlaceUrl = commentList.placeUrl;
        this.ediFileName = commentList.fileName;
        this.commentId = commentList.commentId;
      },
            onModifySubmit () {
            const comment = this.ediComment
            const placeName = this.ediPlaceName
            const placeUrl = this.ediPlaceUrl
            const fileName =  this.ediFileName
            const file =  this.files1[0]
            console.log(this.$refs.files1.files)
            
            let formData = new FormData()
            // if (fileName != null ){formData.append('fileName', fileName)}
            // if (file != null ){formData.append('file', file)}
            
            if (file.length !== 0 ){
            formData.append('file',this.files1[0])}
            console.log(this.$refs.files1.files)  
            formData.append('fileName', fileName)
            formData.append('comment', comment)
            formData.append('placeName', placeName)
            formData.append('placeUrl', placeUrl)
            console.log(formData)
            console.log(this.boardNo)
            
            axios.put(`http://localhost:7777/communityboard/${this.boardNo}/${this.commentId}/comment/register`,formData, { headers: {
                    'Content-Type': 'multipart/form-data'
                }})
                   .then(() => {
                    alert('댓글이 수정되었습니다!')
                    history.go(0)
            })
            .catch (() => {
                console.log(this.commentId)
                alert('문제 발생!')
                })  
        }   
}
}  
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Cute+Font&display=swap');
table {
    position: relative;
    background-color: white ;
    font-size: 20px;
    color: black;
    width: 45%;
    padding-left: 0%;
    padding-right: 0%;
    padding-top:0%;
    padding-bottom:3%;
    margin-left:auto;
    margin-right:auto;
}
.label{
    font-family: 'Cute Font', cursive;
    margin-right:0%;
}
.v-textarea {
    font-family: 'Noto Sans KR', sans-serif;
}
.commentList {
    font-family: 'Noto Sans KR', sans-serif;
    font-size: 14px;
}
.commentBtn{
    position:absolute;
    margin-top:-7%;
    left:92%;
}
.comModifyBtn {
    position:absolute;
    margin-top:1.5%;
    margin-left:8%;
    left:80%; 
}
.comDeleteBtn {
    position:absolute;
    margin-top:1.5%;
    margin-left:14%;
    left:80%; 
}
.writeBtn {
    position:absolute;
    margin-top:0%;
    margin-left:auto;
    left:85%; 
}
.preview {
    position: relative;
    margin-left: auto;
    margin-right:auto;
    max-width: 150px;
    max-height: 150px;
}
.reply {
    margin-top:-4%;
}
.vb {
    zoom:0.9;
}
.imgArea {
    position: relative;
    margin-top:-6%;
    margin-left: 35%;
    margin-right:auto;
    max-height: 150px;
    max-width: 150px;
}
</style>