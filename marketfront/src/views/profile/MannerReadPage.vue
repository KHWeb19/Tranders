<template>
    <div>
        <after-login-view/>
        <profile-top/>
        <h2>매너 칭찬</h2>
        <manner-read :manner="manner"/>
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
        ...mapState(['manner'])
    },
    created() {
        this.fetchManner(this.memberId)
            .catch(()=>{
                alert('매너 칭찬 요청 실패')
                this.$router.push()
            })
    },
    methods: {
        ...mapActions(['fetchManner']),
    }
}
</script>