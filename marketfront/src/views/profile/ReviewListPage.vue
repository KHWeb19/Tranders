<template>
    <div>
        <after-login-view/>
        <profile-top/>
        <h2> 리뷰 리스트</h2>
        <review-list :reviews="reviews"/>
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
    computed: {
        ...mapState(['reviews'])
    },
    mounted () {
        this.fetchReviewList('rrrrrrrr')
    },
    methods: {
        ...mapActions(['fetchReviewList'])
    }
}
</script>