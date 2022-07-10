<template>
    <div id="login-wrap">
        <after-login-view></after-login-view>

        <div id="content">
        <v-row no-gutters>
            <v-col cols="4">
            <my-profile-bar :userInfo="userInfo"></my-profile-bar>
            </v-col>

            <v-col cols="8">
            <my-follow-board :myFollowBoards="myFollowBoards"/>
            </v-col>
        </v-row>
        </div>
    </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import AfterLoginView from "@/components/home/AfterLoginView";
import MyProfileBar from "@/components/myPage/MyPageBar";
import cookies from "vue-cookies";
import MyFollowBoard from '../../components/myPage/MyFollowBoard.vue';
export default {
    components: {
        MyProfileBar,
        AfterLoginView,
        MyFollowBoard,
    },
    name: "MyFollowBoardList",
    data() {
        return {
        memberId: cookies.get("id"),
        memberNo: cookies.get("memberNo"),
        };
    },
    computed: {
        ...mapState(["myFollowBoards"]),
        ...mapState(["userInfo"]),
    },
    mounted() {
        this.fetchMyFollowBoardList(this.memberNo);
        this.fetchMyPage(this.memberId);
    },
    methods: {
        ...mapActions(["fetchMyFollowBoardList"]),
        ...mapActions(["fetchMyPage"]),
    },
};
</script>

<style scoped>
#login-wrap {
  background: #f8f9fa;
  min-height: 100vh;
}
#content {
  display: block;
  margin-top: 72px;
  margin: 30px auto 0 auto;
  border-left: 1px solid #e9ecef;
  border-right: 1px solid #e9ecef;
  width: 1200px;
  margin: 0 auto;
  line-height: 24px;
  background: #fff;
}
</style>