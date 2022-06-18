<template>
    <v-container>
        <br><br>
            <v-form enctype="multipart/form-data" @submit.prevent="onSubmit">
                <table>
                    <!-- <v-col cols="12">
                            <v-chip color="light green accent-2" @click="selectedSubject(item)" class="subject" v-model="usedSubject" v-for="(item, i) in subject" :key="i" >
                                {{item}}
                            </v-chip>                           
                    </v-col>-->
                    <br>
                    <v-chip class="subject" color="light green accent-2">
                        {{communityBoard.usedSubject}}
                    </v-chip>
                    <br><br>
                    <v-row justify="center">
                        <!-- <v-col cols="2" class="label" style="font-size:20pt">제목</v-col> -->
                        <v-col>

                            <!-- <v-combobox 
                            class="titleFloat"
                                v-model="selectSubject"
                                :items="items"
                                label="말머리"
                                filled
                                style="width:150px; zoom:1"
                                outlined
                                dense
                                color="indigo darken-4"
                                ></v-combobox>-->
                            
                            <v-text-field class="titleFloat" style="width:460px" color="indigo darken-4" placeholder=" 제목을 작성하세요." v-model="title"/>
                        </v-col>
                    </v-row>
                    <v-row  justify="center">
                        <v-col cols="2" class="label" style="font-size:20pt">내용</v-col>
                        <v-col cols="12">
                            <v-textarea style="white-space:pre-line" cols="75" rows="7" 
                            outlined color="indigo darken-4" placeholder=" 우리 동네 관련된 질문이나 이야기를 해보세요."
                            v-model="content">
                            </v-textarea>
                        </v-col>                       
                    </v-row>
                    <v-row  wrap justify="center">
                        <v-carousel hide-delimiters  height="auto">
                            <v-carousel-item 
                            v-for="(file, index) in files" :key="index" style="text-align:center">
                            <img :src=file.preview class="preview"/>
                            </v-carousel-item>
                            <v-carousel-item 
                            v-for="(file, index) in checkFile()" :key="index" style="text-align:center">
                            <!-- <img :src="require(`@/assets/uploadImg/community/${file}`)" class="preview"/> -->
                            </v-carousel-item>                              
                        </v-carousel>
                        <!-- <v-carousel hide-delimiters height="auto">   
                            <v-carousel-item 
                            v-for="(file, index) in files" :key="index" style="text-align:center">
                            <img :src=file.preview class="preview"/>
                            </v-carousel-item>  
                        </v-carousel>-->
                    </v-row><br>                   
                        <v-icon large>mdi-image-outline</v-icon>
                        <input type="file" id="files" ref="files"  dense style="width:193px"
                                multiple v-on:change="handleFileUpload()"/>
                                <v-btn onclick="location.href='http://localhost:8080/Tranders/CommunityRegister/PlaceSearch'" color="blue-grey" text>
                                    <v-icon large>mdi-map-marker-outline
                                    </v-icon>
                                </v-btn>                                    
                    <br>
                    <div align="left">
                    <span style="color:red; font-size:12pt">최대 10개의 이미지 등록 가능({{files.length}}/10)</span>
                    </div>
                    <br>
                    <!-- <v-row wrap>
                        <v-btn class="writeBtn" color="black" dark>
                            <router-link :to="{ name: 'CommunityBoardReadPage',
                                    params: { boardNo: communityBoard.boardNo.toString() } }" style="color:white">cancle</router-link></v-btn>
                        <v-btn type="submit" class="writeBtn2" color="red darken-3" dark>Modify</v-btn>
                    </v-row> -->
                    <v-row wrap>
                        <v-btn onclick="location.href='http://localhost:8080/Tranders/CommunityList'" class="writeBtn" color="red accent-4" style="box-shadow:none" dark fab small><v-icon>mdi-close</v-icon></v-btn>
                        <v-btn type="submit" class="writeBtn2" color="light green accent-4" style="box-shadow:none" dark fab small><v-icon> mdi-check</v-icon></v-btn>
                    </v-row>
                </table>
            </v-form>
    </v-container>
</template>

<script>
export default {
    name:'CommunityBoardModify',
    props: {
        communityBoard: {
            type: Object,
            required: true
        }
    },
    data() {
        return {
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
        // this.writer = this.$store.state.userInfo.name
        this.writer = ''
        // this.viewCnt = this.market.viewCnt
        this.title = this.communityBoard.title
        this.content = this.communityBoard.content
        this.usedSubject = this.communityBoard.usedSubject
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
            const { title, content, writer, usedSubject} = this
            let formData = new FormData();

            for (let idx = 0; idx <  this.$refs.files.files.length; idx++) {
                  formData.append('file',this.$refs.files.files[idx])
            }

            formData.append('title',title)
            formData.append('content', content)
            formData.append('writer', writer)
            formData.append('usedSubject', usedSubject)
            
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
    /* rgb(185, 255, 75) (210, 255, 140) */
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