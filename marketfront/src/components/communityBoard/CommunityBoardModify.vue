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
                                </v-carousel>
                            </div>
                        </v-col>
                    </v-row>
                    <!-- <v-row>
                        <v-carousel hide-delimiters  height="auto">
                            <v-carousel-item 
                            v-for="(file, index) in files" :key="index" style="text-align:center">
                            <img :src=file.preview class="preview"/>
                            </v-carousel-item>
                            <v-carousel-item 
                            v-for="(file, index) in checkFile()" :key="index" style="text-align:center">
                            <img :src="require(`@/assets/uploadImg/community/${file}`)" class="preview"/>
                            </v-carousel-item>                              
                        </v-carousel>
                    </v-row>                 
                        <v-icon large>mdi-image-outline</v-icon>
                        <input type="file" id="files" ref="files"  dense style="width:193px"
                                multiple v-on:change="handleFileUpload()"/>
                            <v-dialog v-model="dialog" persisten max-width="1000">
                                <template v-slot:activator="{ on }">
                                    <v-btn v-on="on"  onclick="" color="blue-grey" text>
                                        <v-icon large>mdi-map-marker-outline</v-icon>
                                        <v-text-field style="width:200px" placeholder="장소를 등록하세요." v-model="placeName"/>
                                    </v-btn>                              
                                </template>
                                <v-card>
                                    <kakao-map></kakao-map>        
                                </v-card>
                            </v-dialog>      
                    <div align="left">
                    <span style="color:red; font-size:12pt">최대 10개의 이미지 등록 가능({{files.length}}/10)</span>
                    </div> -->

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
        this.createdDate = this.communityBoard.createdDate
        this.placeName = this.communityBoard.placeName
        this.placeUrl = this.communityBoard.placeUrl
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
            const { title, content, writer, usedSubject, region, createdDate, placeName, placeUrl } = this
            let formData = new FormData();

            for (let idx = 0; idx <  this.$refs.files.files.length; idx++) {
                  formData.append('file',this.$refs.files.files[idx])
            }

            formData.append('title',title)
            formData.append('content', content)
            formData.append('writer', writer)
            formData.append('usedSubject', usedSubject)
            formData.append('region', region)
            formData.append('createdDate', createdDate)
            formData.append('placeName', placeName)
            formData.append('placeUrl', placeUrl)
            
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
        selectedSubject (item) {
            if(this.usedSubject.length >0 && this.usedSubject != item ){
                alert("게시글의 주제는 하나만 선택 가능합니다.")
                return false
            } 
                const el = this.selectSubject.findIndex(el => el === item);
                el < 0 ? this.selectSubject.push(item) : this.selectSubject.splice(el, 1);
                const el2 = this.usedSubject.findIndex(el2 => el2 === item);
                el2 < 0 ? this.usedSubject.push(item) : this.usedSubject.splice(el2,1) 
                console.log(this.usedSubject)
            
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
    margin-left:82%;
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
} */
.preview {
    position: relative;
    margin-left: auto;
    margin-right:auto;
    max-width:350px;
    height:350px; 
}
</style>