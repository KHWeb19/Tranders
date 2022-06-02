<template>
    <v-container>
        <br><br>
            <v-form enctype="multipart/form-data" @submit.prevent="onBoardSubmit">
                <table>
                    <v-col cols="12">
                            <v-chip color="black" @click="selectedCondition(item)" class="condition" v-model="usedCondition" v-for="(item, i) in condition" :key="i" >
                                {{item}}
                            </v-chip>                           
                    </v-col>
                    <br>
                    <v-row justify="center">
                        <v-col cols="2" class="label" style="font-size:20pt">제목</v-col>
                        <v-col>
                            <v-combobox
                            class="titleFloat"
                                v-model="selectCondition"
                                :items="items"
                                label="말머리"
                                filled
                                style="width:140px; zoom:1"
                                outlined
                                dense
                                color="red darken-3"
                                ></v-combobox>
                            <v-text-field class="titleFloat" style="width:470px" color="red darken-3" v-model="title"/>
                        </v-col>
                    </v-row>
                    <v-row  justify="center">
                        <v-col cols="2" class="label" style="font-size:20pt">내용</v-col>
                        <v-col cols="12">
                            <v-textarea style="white-space:pre-line" cols="75" rows="7" 
                            outlined color="red darken-3" placeholder="우리 동네 관련된 질문이나 이야기를 해보세요."
                            v-model="content">
                            </v-textarea>
                        </v-col>                       
                    </v-row>
                    <v-row  wrap justify="center">
                        <v-carousel cycle="true" :interval="1000" :show-arrows="false" hide-delimiter-background height="auto">
                            <v-carousel-item 
                            v-for="(file, index) in files" :key="index" :src="files.src" style="text-align:center">
                            <img :src=file.preview class="preview"/>
                            </v-carousel-item>
                        </v-carousel>
                        &nbsp;&nbsp;
                        <v-icon large>mdi-image-outline</v-icon>
                        <input multiple="multiple" type="file" id="files" ref="files"  dense style="width:200px"
                                v-on:change="handleFileUpload()"/>
                                <v-btn onclick="location.href='http://localhost:8080/Tranders/CommunityRegister/PlaceSearch'" @click="initmap" color="blue-grey" text>
                                    <v-icon large>mdi-map-marker-outline
                                    </v-icon>
                                </v-btn>                                    
                    </v-row><br>
                    <div align="left">
                    <span style="color:red; font-size:12pt">최대 10개의 이미지 등록 가능({{files.length}}/10)</span>
                    </div>
                    <br>
                    <v-row wrap>
                        <v-btn onclick="location.href='http://localhost:8080/Tranders/communityList'" class="writeBtn" color="black" dark><v-icon>mdi-close-circle-outline</v-icon></v-btn>
                        <v-btn type="submit" class="writeBtn2" color="red darken-3" dark><v-icon> mdi-send</v-icon></v-btn>
                    </v-row>
                </table>
            </v-form>
    </v-container>
</template>

<script>
export default {
    name:'CommunityBoardRegisterForm',
    data() {
        return {
            image :'',
            title:'',
            content:'',
            brackets: [],
            items: [
            ],
            files:'',
            filesPreview:[],
            response: '',
            usedCondition:[],
            condition: [
                '함께해요','동네질문', '동네맛집', '동네소식', '취미생활',  '분실/실종센터', '해주세요', '일상'
            ],
            selectCondition:[],
        }
    },
    created () {
        this.writer = this.$store.state.userInfo.nickname
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
            const { title, content,writer, brackets} = this
            const file =  this.$refs.files.files[0]
            
            this.$emit('submit', { title, content,writer, brackets, file })
            console.log(title,content,writer,brackets,file)
        },
        selectedCondition (item) {
            if(this.usedCondition.length >0 && this.usedCondition != item ){
                alert("게시글의 주제는 하나만 선택 가능합니다.")
                return false
            } 
                const el = this.selectCondition.findIndex(el => el === item);
                el < 0 ? this.selectCondition.push(item) : this.selectCondition.splice(el, 1);
                const el2 = this.usedCondition.findIndex(el2 => el2 === item);
                el2 < 0 ? this.usedCondition.push(item) : this.usedCondition.splice(el2,1) 
                console.log(this.usedCondition)
            
        },
},
    mounted() {
  if (window.kakao && window.kakao.maps) {
    this.initMap();
  } else {
    const script = document.createElement('script');
    script.onload = () => kakao.maps.load(this.initMap);
    script.src =
      'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=0359ebeacf8f8cc381eb35e5034b0b6c';
    document.head.appendChild(script);
  }
},
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
    margin-left:79%;
    float:left;
}
.titleFloat {
    float:left;
    margin-top:-1%;
    margin-right:3%;
}
.condition {
    font-family: 'Noto Sans KR', sans-serif;
    margin-right:1.5%;
    zoom:110%;
}
.preview {
    position: relative;
    margin-left: auto;
    margin-right:auto;
    max-width:350px;
    height:350px; 
}
@media (max-width:700px){ 
    table {
        zoom:60%;
        margin-bottom:30px;
    }
}
</style>