<template>
    <div id='content'>  
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
                                    홈으로 이동합니다
                                </v-card-title>
                                <v-card-text >
                                    * 작성하던 내용이 사라지고 다시 후기를 작성할 수 없어요
                                </v-card-text>
                                <v-card-actions>
                                    <v-spacer></v-spacer>
                                    <v-btn text color="red" @click="goBack()">
                                        안쓸래요
                                    </v-btn>
                                    <v-btn text @click="dialog.value=false">
                                        작성할래요
                                    </v-btn>
                                </v-card-actions>
                            </v-card>
                        </template>
                    </v-dialog>
                </v-layout>
            </div>
            <div><h2 style="margin-top: 6px; margin-left: 5px;">거래 후기 보내기</h2></div>
        </div>
        <div id='content_text'>       
            <h3>{{userInfo.name}}님과 거래가 어떠셨나요?</h3>
            <span>선택항목은 상대방이 알 수 없어요.</span>
        </div>
        <div id='icon_list'>
            <div>
                <v-img id="icon" v-if="isBad" @click="onBad" src="@/assets/reviewImg/free-icon-angry-1933552.png"/>
                <v-img id="icon" v-else @click="onBad" src="@/assets/reviewImg/free-icon-angry-1933572.png"/>
                <span :style="isBad ? 'color:green':''"><b>별로에요</b></span>
            </div>

            <div>
                <v-img id="icon" v-if="isGood" @click="onGood" src="@/assets/reviewImg/free-icon-smile-1933646.png"/>
                <v-img id="icon" v-else @click="onGood" src="@/assets/reviewImg/free-icon-smile-1933648.png"/>
                <span :style="isGood ? 'color:green':''"><b>좋아요!</b></span>
            </div>

            <div>
                <v-img id="icon" v-if="isPerfect" @click="onPerfect" src="@/assets/reviewImg/free-icon-hug-1933429.png"/>
                <v-img id="icon" v-else @click="onPerfect" src="@/assets/reviewImg/free-icon-hug-1933513.png"/>
                <span :style="isPerfect ? 'color:green':''"><b>최고에요!</b></span>
            </div>
        </div>
        <div id='content_text' v-if="isGood || isPerfect">       
            <h3>거래하며 좋았던 점을 선택해 주세요.</h3>
        </div>
        <div id='content_text' v-else>       
            <h3>거래하며 나빴던 점을 선택해 주세요.</h3>
        </div>
        <div id='user_manners'>
            <ol id='manners_list' v-if="isGood || isPerfect">
                <li id='manner'>
                    <v-checkbox style="margin:0px; padding: 0px;"
                    v-model="good1"
                    color="green"
                    ></v-checkbox>
                    <p id='manner_content'>제가 있는 곳까지 와서 거래했어요.</p>
                </li>
                <li id='manner'>
                    <v-checkbox style="margin:0px; padding: 0px;"
                    v-model="good2"
                    color="green"
                    ></v-checkbox>
                    <p id='manner_content'>친절하고 매너가 좋아요.</p>
                </li>
                <li id='manner'>
                    <v-checkbox style="margin:0px; padding: 0px;"
                    v-model="good3"
                    color="green"
                    ></v-checkbox>
                    <p id='manner_content'>시간 약속을 잘 지켜요.</p>
                </li>
                <li id='manner'>
                    <v-checkbox style="margin:0px; padding: 0px;"
                    v-model="good4"
                    color="green"
                    ></v-checkbox>
                    <p id='manner_content'>응답이 빨라요.</p>
                </li>
            </ol>

            <ol id='manners_list' v-else>
                <li id='manner'>
                    <v-checkbox style="margin:0px; padding: 0px;"
                    v-model="bad1"
                    color="green"
                    ></v-checkbox>
                    <p id='manner_content'>무리하게 가격을 깎아요.</p>
                </li>
                <li id='manner'>
                    <v-checkbox style="margin:0px; padding: 0px;"
                    v-model="bad2"
                    color="green"
                    ></v-checkbox>
                    <p id='manner_content'>불친절해요.</p>
                </li>
                <li id='manner'>
                    <v-checkbox style="margin:0px; padding: 0px;"
                    v-model="bad3"
                    color="green"
                    ></v-checkbox>
                    <p id='manner_content'>구매의사 없이 계속 찔러봐요.</p>
                </li>
                <li id='manner'>
                    <v-checkbox style="margin:0px; padding: 0px;"
                    v-model="bad4"
                    color="green"
                    ></v-checkbox>
                    <p id='manner_content'>채팅 메시지를 보내도 답이 없어요.</p>
                </li>
            </ol>
        </div>

        <div id='content_text'>       
            <h3>{{userInfo.name}}님에게 감사 인사를 남겨보세요.</h3>
            <span>작성한 내용은 상대방 프로필에 공개됩니다.</span>
        </div>
        <textarea v-model="message"></textarea>
        <v-btn block depressed color="success" height="50" @click="onSubmit"><h3><b>거래 후기 작성 완료</b></h3></v-btn>
    </div>
</template>

<script>
import Vue from 'vue'
import cookies from "vue-cookies";
Vue.use(cookies)
export default {
    name: 'ReviewRegister',
    props: {
        userInfo: {
            type: Object,
        },
        memberId: {
            type: String,
            required: true
        }
    },
    data() {
        return {
            login: {
                id: cookies.get('id'),
            },
            isBad: false,
            isGood: false,
            isPerfect: true,
            button: 1,
            good1: 1, good2: 1, good3: 1, good4: 1,
            bad1: 1, bad2: 1, bad3: 1, bad4: 1,
            sum: 0,
            message: '',
        }
    },
    mounted() {
        console.log(this.isBad)
        console.log(this.isGood)
        console.log(this.isPerfect)
    },
    methods: {
        onBad() {
            if (this.isBad === false) {
                this.isBad = !this.isBad;
                if(this.isGood === true){
                    this.isGood = !this.isGood;
                } else {
                    this.isPerfect = !this.isPerfect;
                }
            }
            this.button = -1
        },
        onGood() {
            if (this.isGood === false) {
                this.isGood = !this.isGood;
                if(this.isBad === true) {
                    this.isBad = !this.isBad;
                } else {
                    this.isPerfect = !this.isPerfect;
                }
            }
            this.button = 0.5
            this.good1 = 1
            this.good2 = 1
            this.good3 = 1
            this.good4 = 1
        },
        onPerfect() {
            if (this.isPerfect === false) {
                this.isPerfect = !this.isPerfect;
                if(this.isBad === true) {
                    this.isBad = !this.isBad;
                } else {
                    this.isGood = !this.isGood;
                }
            }
            this.button = 1
            this.good1 = 1
            this.good2 = 1
            this.good3 = 1
            this.good4 = 1
        },
        onSubmit() {
            console.log(this.memberId)
            const { memberId, button, good1, good2, good3, good4, bad1, bad2, bad3, bad4, message } = this
            if (button == -1) {
                this.sum = (bad1 + bad2 + bad3 + bad4)*button*0.025
                this.$emit('onSubmit', { memberId, member2Id: this.login.id, good1:0, good2:0, good3:0, good4:0, bad1, bad2, bad3, bad4, sum: this.sum, message })
            } else {
                this.sum = (good1 + good2 + good3 + good4)*button*0.025
                this.$emit('onSubmit', { memberId, member2Id: this.login.id, good1, good2, good3, good4, bad1:0, bad2:0, bad3:0, bad4:0, sum: this.sum, message })
            }
            this.$router.push({ name: "MyPageProfile" });
        },
        goBack() {
            this.$router.push({ name: "HomePage" });
        }
    }
}
</script>

<style scoped>
#title{
    display:flex; 
    padding-bottom: 10px;
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
    height: 100vh;
}
#content_text{
    text-align: center;
    padding: 30px 0px;
}
#icon_list{
    display: flex; 
    text-align: center;
    justify-content: center;
    padding-bottom: 30px;
    border-bottom: 1px solid #eaebee;
}
#icon{
    width: 100px;
    margin: 0px 50px 10px 50px;
    cursor: pointer;
}
#user_manners{
    position: relative;
    padding-bottom: 30px;
    display: block;
    border-bottom: 1px solid #eaebee;
}
ol {
    display: block;
    list-style-type: decimal;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    padding-inline-start: 0px;
}
#manners_list {
    counter-reset: item;
}
li {
    display: list-item;
    text-align: -webkit-match-parent;
}
#manner {
    display:flex;
    list-style-type: none;
    counter-increment: item;
    position: relative;
}
#manner_content {
    display: inline-block;
    font-size: 15px;
}
textarea{
    border: 2px solid #eaebee;
    box-sizing: border-box;
    border-radius: 6px;
    height: 100px;
    width: 100%;
    padding: 16px;
    resize: none;
}
span{
    color: #868e96;
}
</style>