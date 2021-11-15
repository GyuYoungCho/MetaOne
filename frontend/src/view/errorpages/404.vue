<template>
  <section class="pageNotFound">
    <div class="entire">
      <img src="@/assets/image/notfound.gif" alt="">
      <h2>페이지를 찾을 수 없습니다.</h2>
      <h1>404</h1>
      <p>잘못된 주소이거나, 다시 시도해 주세요!</p>
      <div>
        <button class="a" @click="onHomeBtnClick">메인으로</button> 
        <button class="a" @click="onBackBtnClick">뒤로가기</button>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "NotFound",
  methods: {
    onHomeBtnClick () {
      this.$router.push({ name: 'UnityMap' })
    },
    onBackBtnClick () {
      this.$router.back()
    }
  },
  created(){
    this.$store.commit("process/SET_UNITY_INSTANCE",false);
  }
}
</script>

<style>
  
</style>