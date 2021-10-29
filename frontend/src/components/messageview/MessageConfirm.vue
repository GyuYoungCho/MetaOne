<template>
  <section class="MessageForm">
    <div class="m_title">
        <p>받은 쪽지</p>
    </div>
    <div class="m-contain">
    </div>
    <div class="send_card">
      <div class="row message_com m_title mt-3">
        <div class="col-md-2 pt-1 px-0">{{titles[0]}}</div>
        <div class="col-md-10">
            <input type="email" class="form-control" v-model="receiver">
        </div>
      </div>
      <div class="row message_com m_content mt-3">
        <label for="ContentArea" class="content-label">{{titles[1]}}</label>
        <textarea class="form-control" id="ContentArea" rows="10"></textarea>
      </div>
      <div class="row message_com mt-4 m_answer justify-content-end">
        <button @click="sendMessage()">답장하기</button>
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
      titles: ["제목", "내용"],
      empty: '',
    }
  }
}
</script>

<style>

</style>