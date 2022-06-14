<template>
    <div>
        <h2>게시물 읽기 페이지</h2>
        <board-test-view @submit="onSubmit"/>
    </div>
</template>

<script>
import BoardTestView from '@/components/chatting/BoardTestView.vue'
import axios from 'axios'
export default {
    components: { BoardTestView },
    name: 'BoardTestPage',
    methods: {
        onSubmit(payload) {
            const {sender} = payload
            axios.post('http://localhost:7777/chatting/register', {sender})
                .then(() => {
                    alert('게시물 등록 성공!')

                    // this.$router.push({
                    //     name: 'JpaBoardListPage'
                    //     })
                    })
                    .catch(() => {
                        alert('문제 발생!')
                    })
        }    
    }
}
</script>