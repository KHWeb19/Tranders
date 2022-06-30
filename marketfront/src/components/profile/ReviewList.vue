<template>
    <div>
        <div v-for="review in reviews" :key="review.reviewNo">
            <div v-if="review.message">
                <div>{{review.writer.id}}{{review.writer.region}}</div>
                <div>{{review.message}}</div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'ReviewList',
        props: {
            reviews: {
                type: Array
            }
    },
}
</script>