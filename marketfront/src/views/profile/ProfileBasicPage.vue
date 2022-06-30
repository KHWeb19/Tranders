<template>
    <div>
        <after-login-view/>
        <profile-top/>
        <profile-basic/>
    </div>
</template>

<script>
import AfterLoginView from '../../components/home/AfterLoginView.vue'
import ProfileBasic from '../../components/profile/ProfileBasic.vue'
import ProfileTop from '../../components/profile/ProfileTop.vue'
export default {
    components: { ProfileBasic, ProfileTop, AfterLoginView },
    name: 'ProfileBasicPage'
}
</script>