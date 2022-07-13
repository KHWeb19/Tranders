<template>
    <div>
    <v-btn v-if="!this.onFollow" depressed class="success" @click="followRegister"><b>모아보기</b></v-btn>
    <v-btn v-else depressed @click="followRegister"><b>모아보기 취소</b></v-btn>

    </div>
</template>

<script>
import axios from 'axios'
import { mapState, mapActions } from "vuex";
import cookies from "vue-cookies";

export default {
    name: 'FollowButton',
    props: {
        userInfo: {
            type: Object,
        },
        myFollows: {
            type: Array,
        }
    },
    data() {
        return {
            memberNo: cookies.get("memberNo"),
        }
    },
    computed: {
        ...mapState(['onFollow']),
    },
    created () {
        this.fetchOnFollow({loginNo:this.memberNo, memberNo:this.userInfo.memberNo})
    },
    mounted () {
        this.fetchOnFollow({loginNo:this.memberNo, memberNo:this.userInfo.memberNo})
    },
    methods: {
        ...mapActions(['fetchOnFollow']),
        followRegister() {
            axios.post(`http://localhost:7777/follow/${this.memberNo}/${this.userInfo.memberNo}`, 
            {loginNo:this.memberNo, memberNo:this.userInfo.memberNo})
                .then(() => {
                    history.go(0);
                })
                .catch(() => {
                    alert('문제 발생!')
                })
        }
        
    }
}
</script>