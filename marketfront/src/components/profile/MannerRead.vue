<template>
    <div id='user_manners'>
        <ol id='manners_list'>
            <li id='manner'>
                <p id='manner_content'>제가 있는 곳까지 와서 거래했어요.</p> 
                <p id='manner_count'>{{manner.good1}}명</p>
            </li>
            <li id='manner'>
                <p id='manner_content'>친절하고 매너가 좋아요.</p> 
                <p id='manner_count'>{{manner.good2}}명</p>
            </li>
            <li id='manner'>
                <p id='manner_content'>시간 약속을 잘 지켜요.</p> 
                <p id='manner_count'>{{manner.good3}}명</p>
            </li>
            <li id='manner'>
                <p id='manner_content'>응답이 빨라요.</p> 
                <p id='manner_count'>{{manner.good4}}명</p>
            </li>
        </ol>
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

<style scoped>
#user_manners{
    position: relative;
    display: block;
}
ol {
    display: block;
    list-style-type: decimal;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    padding-inline-start: 0px;
}
#manners_list {
    counter-reset: item;
}
li {
    display: list-item;
    text-align: -webkit-match-parent;
}
#manner {
    list-style-type: none;
    counter-increment: item;
    position: relative;
    padding: 16px 0;
    border-bottom: 1px solid #eaebee;
}
#manner_content {
    display: inline-block;
    font-size: 15px;
}
#manner_count{
    position: absolute;
    font-size: 15px;
    font-weight: 600;
    right: 0;
    top: 16px;
}
p {
    display: block;
    margin-block-start: 10px;
    margin-block-end: 10px;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
}
</style>