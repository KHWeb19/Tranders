<template>
    <div>
        <h2>홈 테스트 페이지</h2>
        <home-test-view/>
    </div>
</template>

<script>
import HomeTestView from '@/components/chatting/HomeTestView.vue'
export default {
    components: { HomeTestView },
    name: 'HomeTestPage'
}
</script>