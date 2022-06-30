<template>
    <div>           
        <div>{{memberId}}님과 거래가 어떠셨나요?</div>
        <div>선택항목은 상대방이 알 수 없어요.</div>
        <div>
            <v-btn @click="onBad" :style="isBad==true?'color:green':''">별로에요</v-btn>
            <v-btn @click="onGood" :style="isGood==true?'color:green':''">좋아요!</v-btn>
            <v-btn @click="onPerfect" :style="isPerfect==true?'color:green':''">최고에요!</v-btn>
        </div>
        <div>
            <v-col v-if="isGood || isPerfect">
                <v-checkbox
                v-model="select1"
                label="제가 있는 곳까지 와서 거래했어요."
                color="green"
                ></v-checkbox>
                <v-checkbox
                v-model="select2"
                label="친절하고 매너가 좋아요."
                color="green"
                ></v-checkbox>
                <v-checkbox
                v-model="select3"
                label="시간 약속을 잘 지켜요."
                color="green"
                ></v-checkbox>
                <v-checkbox
                v-model="select4"
                label="응답이 빨라요."
                color="green"
                ></v-checkbox>
            </v-col>
            <v-col v-else>
                <v-checkbox
                v-model="select1"
                label="무리하게 가격을 깎아요."
                color="green"
                ></v-checkbox>
                <v-checkbox
                v-model="select2"
                label="불친절해요."
                color="green"
                ></v-checkbox>
                <v-checkbox
                v-model="select3"
                label="구매의사 없이 계속 찔러봐요."
                color="green"
                ></v-checkbox>
                <v-checkbox
                v-model="select4"
                label="채팅 메시지를 보내도 답이 없어요."
                color="green"
                ></v-checkbox>
            </v-col>
        </div>
        <div>거래하며 좋았던 점을 선택해 주세요.</div>
        <div>님에게 감사 인사를 남겨보세요.</div>
        <div>작성한 내용은 상대방 프로필에 공개됩니다.</div>
        <div><textarea v-model="message"></textarea></div>
        <div><v-btn @click="onSubmit">거래 후기 작성 완료</v-btn></div>
    </div>
</template>

<script>
import Vue from 'vue'
import cookies from "vue-cookies";
Vue.use(cookies)
export default {
    name: 'ReviewRegister',
    props: {
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
            select1: 1,
            select2: 1,
            select3: 1,
            select4: 1,
            sum: 0,
            message: '',
        }
    },
    mounted() {
        // console.log(this.isBad)
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
        },
        onSubmit() {
            const { memberId, button, select1, select2, select3, select4, message } = this
            let sum = (select1+select2+select3+select4)*button*0.025
            this.$emit('onSubmit', { memberId, member2Id: this.login.id, select1, select2, select3, select4, sum, message })
        }
    }
}
</script>