<template>
    <div>
                    <!-- 상대방과의 채팅방이 있으면 이동, 없으면 생성하고 이동 -->
            <!-- roomNo로 이동, params: 내No/상대방No/게시물no -->
                        <!-- <router-link id='' :to="{
                name: 'ChattingReadView',
                params: {
                    member1: login.memberNo.toString(), 
                    member2: '10'.toString(),
                    boardNo: '테스트게시물1'
                }}">
                채팅
            </router-link> -->
        <div>           
            1번게시물 10번판매자
            <v-btn @click="onChat">채팅</v-btn>
        </div>
        <div>           
            2번게시물 10번판매자
            <v-btn @click="onChat">채팅</v-btn>
        </div>
        <div>           
            3번게시물 11번판매자
            <v-btn @click="onChat">채팅</v-btn>
        </div>
        <!-- <v-btn @click="onChat">채팅생성</v-btn> -->
    </div>
</template>

<script>
import Vue from 'vue'
import cookies from "vue-cookies";
Vue.use(cookies)
export default {
    name: 'BoardTestView',
    data() {
        return {
            login: {
                memberNo: cookies.get('memberNo'),
                id: cookies.get('id'),
                name: cookies.get('name'),
                access_token: cookies.get('access_token'),
      },
        }
    },
    methods: {
        onChat() {
            //채팅방생성하고 해당채팅방정보 받아서 이동해야함
            this.$router.push({ name: "ChattingListPage", 
            //params: {
            //     member1No: this.login.memberNo.toString(), 
            //     member2: '10'.toString(),
            //     boardNo: '테스트게시물1',
            //     }
            });

            this.$emit('onChat', {member1No: this.login.memberNo, member2: '10'.toString()})
            // this.$emit('onChat', {member2: 10})
        }
    }
}
</script>