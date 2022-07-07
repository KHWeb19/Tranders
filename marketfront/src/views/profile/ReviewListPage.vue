<template>
    <div style="background: #f8f9fa; min-height: 100vh;">
        <after-login-view/>
        <div style="padding: 30px;">
            <div id='content'>
                <profile-top :userInfo="userInfo" />
                <review-list :reviews="reviews"/>
            </div>
        </div>
    </div>
</template>

<script>
import ReviewList from '../../components/profile/ReviewList.vue'
import { mapState, mapActions } from 'vuex'
import ProfileTop from '../../components/profile/ProfileTop.vue'
import AfterLoginView from '../../components/home/AfterLoginView.vue'

export default {
    name: 'ReviewListPage',
    components: {
        ReviewList,
        ProfileTop,
        AfterLoginView
    },
    props: {
        memberId: {
            type: String,
            required: true
        }
    },
    computed: {
        ...mapState(['userInfo']),
        ...mapState(['reviews'])
    },
    created() {
        this.fetchMyPage(this.memberId)
    },
    mounted () {
        this.fetchReviewList(this.memberId)
    },
    methods: {
        ...mapActions(['fetchMyPage']),
        ...mapActions(['fetchReviewList'])
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