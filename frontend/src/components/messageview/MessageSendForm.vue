<template>
  <section class="MessageForm">
    <div class="m_title">
        <p>쪽지 보내기</p>
    </div>
    <div class="m-contain">
    </div>
    <div class="send_card">
      <div class="row">
        <Inputparam :title="titles[0]" :placeholderData="empty" />
      </div>
      <div class="row mt-3">
        <Inputparam :title="titles[1]" :placeholderData="empty" />
      </div>
      <div class="row contentText mt-3">
        <label for="ContentArea" class="form-label">내용</label>
        <textarea class="form-control" id="ContentArea" rows="3"></textarea>
      </div>
    </div>
  </section>
</template>

<script>
import Inputparam from '@/components/Inputparam.vue'

export default {
  components:{
    Inputparam,
  },
  data(){
    return{
      titles: ["받는 사람", "제목", "내용"],
      empty: '',
    }
  }
}
</script>

<style>

</style>