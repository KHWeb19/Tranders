<template>
    <div>           
        <router-link :to="{
                    name: 'ChattingListPage',
                    //params: {memberNo: this.memberNo.toString()}
                    }">
            <v-btn>오이채팅</v-btn>
        </router-link>
    </div>
</template>

<script>
export default {
    name: 'HomeTestView',
    data() {
        return {
            memberNo: 1
        }
    }
}
</script>