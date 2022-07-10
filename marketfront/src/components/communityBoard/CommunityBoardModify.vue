<template>
    <div id='content'>
            <v-form enctype="multipart/form-data" @submit.prevent="onSubmit">
                <div id='title'>
                    <div>
                        <v-layout>
                            <v-dialog persisten max-width="400">
                                <template v-slot:activator="{ on }">
                                    <v-icon v-on="on" id='goBack' large color="black">mdi-chevron-left</v-icon>
                                </template>
                                <template v-slot:default="dialog">
                                    <v-card>
                                        <v-card-title class="headline">
                                            글쓰기를 취소하시겠습니까?
                                        </v-card-title>
                                        <v-card-text >
                                            * 수정하던 내용이 사라져요
                                        </v-card-text>
                                        <v-card-actions>
                                            <v-spacer></v-spacer>
                                            <v-btn text color="red" @click="goBack()">
                                                안쓸래요
                                            </v-btn>
                                            <v-btn text @click="dialog.value=false">
                                                계속 쓸래요
                                            </v-btn>
                                        </v-card-actions>
                                    </v-card>
                                </template>
                            </v-dialog>
                        </v-layout>
                    </div>
                    <div><h2 style="margin-top: 6px; margin-left: 5px;">동네생활 글수정</h2></div>
                </div>
                <table>
                    <v-row>
                        <v-col>
                        <v-chip color="#E6F3E6">
                            <b>{{communityBoard.usedSubject}}</b>
                        </v-chip>
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col><h3>제목</h3></v-col>
                        <v-col cols="12">
                            <input id='input' placeholder="제목을 입력해주세요." v-model="title"/>
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col><h3>내용</h3></v-col>
                        <v-col cols="12">
                            <textarea placeholder="우리 동네 관련된 질문이나 이야기를 해보세요."
                            v-model="content">
                            </textarea>
                        </v-col>                       
                    </v-row>
                    <v-row>
                        <v-col><h3>장소</h3></v-col>
                        <v-col cols="12">
                            <v-dialog v-model="dialog" persisten max-width="1000">
                            <template v-slot:activator="{ on }">
                                <input id='input' v-on="on" placeholder="공유하고 싶은 장소를 검색할 수 있어요." v-model="placeName"/>
                            </template>
                            <v-card>
                                <kakao-map></kakao-map>        
                            </v-card>
                            </v-dialog>
                        </v-col>                       
                    </v-row>
                    <v-row>
                        <v-col><h3>사진 ({{files.length}}/10)</h3></v-col>
                        <v-col cols="12">
                            <div id='image'>
                                <label for="files"><v-icon large>mdi-camera</v-icon></label>
                                <input type="file" id="files" ref="files" dense style="width:0px"
                                        multiple v-on:change="handleFileUpload()"/>
                                <v-carousel hide-delimiters height="auto">    
                                    <v-carousel-item 
                            v-for="(file, index) in files" :key="index" style="text-align:center">
                            <img :src=file.preview class="preview"/>
                            </v-carousel-item>
                            <v-carousel-item 
                            v-for="(file, index) in checkFile()" :key="index" style="text-align:center">
                            <img :src="require(`@/assets/uploadImg/community/${file}`)" class="preview"/>
                            </v-carousel-item>  
                                </v-carousel>
                            </div>
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col>
                            <v-btn block depressed color="success" height="50" type="submit"><h3><b>수정 완료</b></h3></v-btn>
                        </v-col>
                    </v-row>
                </table>
            </v-form>
    </div>
</template>

<script>
import EventBus from '@/eventBus.js'
import KakaoMap from '../../views/KakaoMap.vue';
import cookies from "vue-cookies";
export default {
    components: { KakaoMap },
    name:'CommunityBoardModify,KaKaoMap',
    props: {
        communityBoard: {
            type: Object,
            required: true
        }
    },
    data() {
        return {
            placeName:'',
            placeUrl:'',
            image :'',
            title:'',
            content:'',
            items: [],
            files:[],
            filesPreview:[],
            response: '',
            usedSubject:[],
            subject: [
                '함께해요','동네질문', '동네맛집', '동네소식', '취미생활',  '분실/실종센터', '해주세요', '일상'
            ],
            selectSubject:[],
        login: {
        memberNo: cookies.get("memberNo"),
        id: cookies.get("id"),
        name: cookies.get("name"),
        access_token: cookies.get("access_token"),
      },
        }
    },
    created () {
        EventBus.$on('placeRegister', (payload) => {
            this.placeUrl = payload[0]
            this.placeName = payload[1]
            console.log(payload) 
        })
        this.writer = this.communityBoard.writer
        this.region = this.communityBoard.region
        this.title = this.communityBoard.title
        this.content = this.communityBoard.content
        this.usedSubject = this.communityBoard.usedSubject
        this.placeName = this.communityBoard.placeName
        this.placeUrl = this.communityBoard.placeUrl
        this.memberNo = this.communityBoard.member.memberNo
    },
    methods: {
        handleFileUpload () {
             console.log(this.$refs.files.files)

            if(this.$refs.files.files.length > 10) {
                alert("선택할 수 있는 이미지 개수를 초과하였습니다.")
                document.getElementById("files").value = "";
                return 
            }else {
            for (let i = 0; i < this.$refs.files.files.length; i++){
                this.files = [
                    ...this.files,
                    {
                        file: this.$refs.files.files[i],
                        preview: URL.createObjectURL(this.$refs.files.files[i])
                    }
                ]
            }
            console.log(this.files)
            }
        },
        onSubmit () {
            const { title, content} = this
            const { boardNo} = this.communityBoard
            console.log(boardNo)
            let formData = new FormData();

            for (let idx = 0; idx <  this.$refs.files.files.length; idx++) {
                  formData.append('file',this.$refs.files.files[idx])
                  console.log(this.$refs.files.files[idx])  
            }
            formData.append('boardNo',boardNo)
            formData.append('title',title)
            formData.append('content', content)         
            this.$emit('submit', {formData})
            console.log(formData)            
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

        goBack() {
            this.$router.go(-1);
        }
},
}
</script>
<style scoped>
table {
    width: 100%;
  }
#content{
    display: block;
    margin-top: 72px;
    margin: 30px auto 0 auto;
    border: 1px solid #e9ecef;
    width: 800px;
    margin: 0 auto;
    padding: 40px;
    line-height: 24px;
    background: #fff;
}
#title{
    display:flex; 
    padding-bottom: 20px;
}
#input{
    width: 100%;
    border: 2px solid #EAEBEE;
    box-sizing: border-box;
    border-radius: 6px;
    font-size: 16px;
    box-shadow: none;
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    padding: 16px;
}
textarea{
    border: 2px solid #eaebee;
    box-sizing: border-box;
    border-radius: 6px;
    width: 100%;
    height: 300px;
    padding: 16px;
    resize: none;
}
#image{
    border: 2px solid #eaebee;
    box-sizing: border-box;
    border-radius: 6px;
    width: 100%;
    padding: 16px;
    resize: none;
}
.preview {
    position: relative;
    margin-left: auto;
    margin-right:auto;
    max-width:350px;
    height:350px; 
}
</style>