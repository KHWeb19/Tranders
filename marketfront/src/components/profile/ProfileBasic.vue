<template>
  <div>
          <div>
            <section>
              <div v-if="!myProductBoards || (Array.isArray(myProductBoards) && myProductBoards.length === 0)">
                현재 등록된 상품이 없습니다!
              </div>
              <v-col
                v-else
                v-for="myProductBoard in myProductBoards"
                :key="myProductBoard.productNo"
                cols="4"
              >
                <article class="card-top">
                  <router-link
                    class="product-link"
                    :to="{
                      name: 'ProductReadPage',
                      params: {
                        productNo: myProductBoard.productNo.toString(),
                      },
                    }"
                  >
                    <div class="card-photo">
                      <v-img
                        style="height: 150px; width: 250px"
                        :src="
                          require(`@/assets/pImage/${myProductBoard.productImage}`)
                        "
                      />
                    </div>
                    <div class="card-desc">
                      <div class="card-process">
                        {{ myProductBoard.process }}
                      </div>
                      <div class="card-title">
                        {{ myProductBoard.title }}
                      </div>

                      <div class="card-price">
                        {{ myProductBoard.price | makeComma }}원
                      </div>
                      <div class="card-region-name">
                        {{ myProductBoard.member.region }}
                      </div>
                      <div class="card-counts">
                        <span>
                          관심 {{ myProductBoard.productLike.length }} </span
                        >∙ <span> 채팅 {{ myProductBoard.chatCnt }} </span>∙
                        <span> 조회수 {{ myProductBoard.viewCnt }}</span>
                      </div>
                    </div>
                  </router-link>
                </article>
              </v-col>
            </section>
          </div>
  </div>
</template>

<script>
export default {
  name: "ProflieBasic",
  props: {
    myProductBoards: {
      type: Array,
    },
    memberId:{
        type: String
    }
  },
};
</script>

<style scoped>
/* .head-title {
  font-size: 32px;
  font-weight: 600;
  letter-spacing: -0.6px;
  text-align: center;
  counter-reset: #212529;
  padding-top: 30px;
} */
section {
  display: block;
}
section#content {
  margin-top: 72px;
  margin: 0;
  padding: 0;
}
.card-wrap {
  width: 700px;
  /* margin: 0 auto; */
  margin: 0 33px;
  display: flex;
  justify-content: left;
  flex-wrap: wrap;
}
.card-top {
  margin-right: 44px;
  margin-bottom: 56px;
}
.card-photo {
  position: relative;
  overflow: hidden;
  border-radius: 12px;
  background-color: #f8f9fa;
  box-shadow: inset 0px 0px 0px 1px rgb(0, 0, 0 / 15%);
  box-sizing: border-box;
}
.card-desc {
  margin-top: 12px;
}
.card-process {
  font-size: 18px;
  font-weight: 700;
  text-align: center;
  border: 1px solid white;
  border-radius: 5px;
  background-color: green;
  max-width: 70px;
  color: white;
  margin-bottom: 5px;
}
.card-title {
  font-size: 16px;
  letter-spacing: -0.02px;
  color: #212529;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  margin-bottom: 2px;
  line-height: 1.5;
  font-weight: normal;
}
.card-price {
  font-size: 15px;
  font-weight: 700;
  line-height: 1.5;
  margin-bottom: 4px;
  color: #212529;
}
.card-region-name {
  font-size: 13px;
  color: #212529;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  margin-bottom: 4px;
  line-height: 1.5;
}
.card-counts {
  color: #868e96;
  font-size: 13px;
}
.product-link {
  text-decoration: none !important;
}
img {
  position: absolute;
  top: 0;
  bottom: 0;
  width: 100%;
  box-sizing: border-box;
  border-radius: 12px;
  border: 1px solid transparent;
}
</style>