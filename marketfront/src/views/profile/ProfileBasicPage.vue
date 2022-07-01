<template>
    <div>
        <after-login-view/>
        <div id='content'>
            <profile-top :userInfo="userInfo" />
            <profile-basic />
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
    width: 677px;
    margin: 30px auto 0 auto;
}
</style>