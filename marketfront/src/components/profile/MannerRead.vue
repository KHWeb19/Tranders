<template>
    <div>
        <div>{{manner.count1}}</div>
        <div>{{manner.count2}}</div>
        <div>{{manner.count3}}</div>
        <div>{{manner.count4}}</div>
    </div>
</template>

<script>
export default {
    name: 'MannerRead',
    props: {
        manner: {
            type: Object,
            required: true
        }
    },
}
</script>