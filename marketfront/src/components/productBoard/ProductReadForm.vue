<template>
  <div>
    <table>
      <tr>
        <td>상품명</td>
        <td>
          <input type="text" :value="product.title" readonly />
        </td>
      </tr>
      <tr>
        <td>가격</td>
        <td>
          <input type="text" :value="product.price" readonly />
        </td>
      </tr>
      <tr>
        <td>등록 일자</td>
        <td>
          <input type="text" :value="product.regDate" readonly />
        </td>
      </tr>
      <tr>
        <td>설명</td>
        <td>
          <textarea cols="50" rows="20" :value="product.content" readonly />
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  name: "ProductReadPage",
  props: {
    product: {
      type: Object,
      required: true,
    },
  },
};
</script>