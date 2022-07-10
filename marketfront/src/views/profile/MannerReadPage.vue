<template>
    <div style="background: #f8f9fa; min-height: 100vh;">
        <after-login-view/>
        <div id='content'>
            <profile-top :userInfo="userInfo" />
            <manner-read :manner="manner"/>
        </div>
    </div>
</template>

<script>
import AfterLoginView from '../../components/home/AfterLoginView.vue'
import MannerRead from '../../components/profile/MannerRead.vue'
import ProfileTop from '../../components/profile/ProfileTop.vue'
import { mapActions, mapState } from 'vuex'
export default {
    name: 'MannerReadPage',
    components: { MannerRead, AfterLoginView, ProfileTop },
    props: {
        memberId: {
            type: String,
            required: true
        }
    },
    computed: {
        ...mapState(['userInfo']),
        ...mapState(['manner'])
    },
    created() {
        this.fetchManner(this.memberId)
            .catch(()=>{
                alert('매너 칭찬 요청 실패')
                this.$router.push()
            })
        this.fetchMyPage(this.memberId)
    },
    methods: {
        ...mapActions(['fetchMyPage']),
        ...mapActions(['fetchManner']),
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