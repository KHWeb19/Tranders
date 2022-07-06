<template>
    <div id='content'>
        <v-form enctype="multipart/form-data" @submit.prevent="onBoardSubmit">
            <div id='title'>
                <div>
                    <v-layout>
                        <v-dialog persisten max-width="400">
                            <template v-slot:activator="{ on }">
                                <v-icon v-on="on" large color="black">mdi-chevron-left</v-icon>
                            </template>
                            <template v-slot:default="dialog">
                                <v-card>
                                    <v-card-title class="headline">
                                        글쓰기를 취소하시겠습니까?
                                    </v-card-title>
                                    <v-card-text >
                                        * 작성하던 내용이 사라져요
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
                <div><h2 style="margin-top: 6px; margin-left: 5px;">동네생활 글쓰기</h2></div>
            </div>
            <table>
                <v-row>
                    <!-- <v-col cols="1"><h3>주제</h3></v-col>
                    <v-col cols="11">
                        <v-combobox
                            class="titleFloat"
                            v-model="selectSubject"
                            :items="items"
                            label="말머리"
                            filled
                            style="width:150px; zoom:1"
                            outlined
                            dense
                            color="indigo darken-4"
                        ></v-combobox>
                    </v-col> -->
                    <v-col v-if="!selectSubject[0]">
                        <v-chip style="margin-right:12px"  @click="selectedSubject(item)" v-model="usedSubject" v-for="(item, i) in subject" :key="i" >
                            <b>{{item}}</b>
                        </v-chip>   
                    </v-col>
                    
                </v-row>
                <v-row>
                    <v-col v-if="selectSubject[0]" >
                            <v-chip color="#E6F3E6">
                                <b>{{selectSubject[0]}}</b>
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
                        <v-dialog persisten max-width="1000">
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
                <v-row>
                    <v-col>
                     <v-btn block depressed color="success" height="50" type="submit"><h3><b>작성 완료</b></h3></v-btn>
                    </v-col>
                </v-row>
            </table>
        </v-form>
    </div>
</template>

<script>
import EventBus from '@/eventBus.js'
import Vue from 'vue'
import cookies from "vue-cookies";
import KakaoMap from '../../views/KakaoMap.vue';
Vue.use(cookies)

export default {
  components: { KakaoMap },
    name:'CommunityBoardRegisterForm',
    data() {
        return {
            login: {
                name: cookies.get('name'),
                id: cookies.get('id'),
                region: cookies.get('region'),
                access_token: cookies.get('access_token'),
            },
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
            lat: 0,
            lng: 0,
            category: '',
            phoneNumber: '',
            address: '',
            storeRegion: null
        }
    },
    created () {
        EventBus.$on('placeRegister', (payload) => {
            this.placeUrl = payload[0]
            this.placeName = payload[1]
            this.lng = payload[2]
            this.lat = payload[3]
            this.category = payload[4]
            this.phoneNumber = payload[5]
            this.storeRegion = payload[6];

          //rs.place_name, rs.x, rs.y, rs.category_name, rs.phone, rs.address_name
            console.log(payload) 
        })
        this.writer = this.login.id
        this.region = this.login.region
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
        onBoardSubmit () {
            const { title, content, writer, usedSubject, region, placeName, placeUrl, lat, lng, category, phoneNumber, storeRegion } = this
            let formData = new FormData();

            for (let idx = 0; idx <  this.$refs.files.files.length; idx++) {
                  formData.append('file',this.$refs.files.files[idx])
            }

            formData.append('title',title)
            formData.append('content', content)
            formData.append('writer', writer)
            formData.append('usedSubject', usedSubject)
            formData.append('region', region)
            formData.append('placeName', placeName)
            formData.append('placeUrl', placeUrl)
            formData.append('lat', lat)
            formData.append('lng', lng)
            formData.append('category', category)
            formData.append('phoneNumber', phoneNumber)
            formData.append('storeRegion', storeRegion)

            this.$emit('submit', {formData})
            console.log(formData)            
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
/* table{
    position: relative;
    background-color: rgb(191, 246, 201);
    padding-left: 5%;
    padding-right: 5%;
    padding-top: 0.5%;
    padding-bottom: 2.5%;
    margin-left:auto;
    margin-right:auto;
    zoom:80%;
} */
/* .v-combobox, .v-text-field, .v-textarea, #files{
    font-family: 'Noto Sans KR', sans-serif;
} */
/* .writeBtn {
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
} */
/* .titleFloat {
    float:left;
    margin-top:-1%;
    margin-right:3%;
} */
/* .subject {
    font-family: 'Noto Sans KR', sans-serif;
    margin-right:1.5%;
    zoom:110%;
} */
.preview {
    position: relative;
    margin-left: auto;
    margin-right:auto;
    max-width:350px;
    height:350px; 
}
</style>