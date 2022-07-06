<template>
    <div style="background: #f8f9fa; min-height: 100vh;">
        <after-login-view/>
        <div style="padding: 30px;">
            <div id='content'>
                <profile-top :userInfo="userInfo" />
                <profile-basic />
            </div>
        </div>
    </div>
</template>

<script>
import AfterLoginView from '../../components/home/AfterLoginView.vue'
import ProfileBasic from '../../components/profile/ProfileBasic.vue'
import ProfileTop from '../../components/profile/ProfileTop.vue'
import { mapState, mapActions } from 'vuex'
export default {
    components: { ProfileBasic, ProfileTop, AfterLoginView },
    name: 'ProfileBasicPage',
    props: {
        memberId: {
            type: String,
            required: true
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
  border: 1px solid #e9ecef;
  width: 800px;
  margin: 0 auto;
  line-height: 24px;
  background: #fff;
  padding: 0 40px 40px 40px;
}
</style>