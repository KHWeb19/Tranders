<template>
    <div style="background: #f8f9fa; min-height: 100vh;">
        <after-login-view/>
        <div id='content'>
            <profile-top :userInfo="userInfo"/>
            <profile-basic />
        </div>
    </div>
</template>

<script>
import AfterLoginView from '../../components/home/AfterLoginView.vue'
import ProfileBasic from '../../components/profile/ProfileBasic.vue'
import ProfileTop from '../../components/profile/ProfileTop.vue'
import { mapState, mapActions } from 'vuex'
import cookies from "vue-cookies";

export default {
    components: { ProfileBasic, 
    ProfileTop, 
    AfterLoginView },
    name: 'ProfileBasicPage',
    props: {
        memberId: {
            type: String,
            required: true
        }
    },
    data() {
        return {
            login: {
                memberNo: cookies.get('memberNo'),
            }
        }
    },
    computed: {
        ...mapState(['userInfo']),
    },
    created() {
        this.fetchMyPage(this.memberId)
    },
    methods: {
        ...mapActions(['fetchMyPage']),
    }
}
</script>

<style scoped>
#content{
  display: block;
  margin-top: 72px;
  margin: 30px auto 0 auto;
  width: 800px;
  margin: 0 auto;
  line-height: 24px;
  background: #fff;
  padding: 40px;
}
</style>