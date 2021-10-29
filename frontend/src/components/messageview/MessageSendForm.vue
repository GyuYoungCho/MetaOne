<template>
  <section class="MessageForm">
    <div class="m_title">
        <p>쪽지 보내기</p>
    </div>
    <div class="m-contain">
    </div>
    <div class="send_card">
      <div class="row message_com m_user mt-3">
        
        <div class="col-md-3 pt-1 px-0">{{titles[0]}}</div>
        <div class="col-md-9">
            <input type="email" class="form-control" v-model="receiver">
        </div>
      </div>
      <div class="row message_com m_title mt-3">
        <div class="col-md-2 pt-1 px-0">{{titles[1]}}</div>
        <div class="col-md-10">
            <input type="email" class="form-control" v-model="receiver">
        </div>
      </div>
      <div class="row message_com m_content mt-3">
        <label for="ContentArea" class="content-label">내용</label>
        <textarea class="form-control" id="ContentArea" rows="10"></textarea>
      </div>
      <div class="row message_com mt-4 m_submit">
        <button @click="sendMessage()">전송</button>
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
  },
  props:{
    recevier : String,
  },

  methods:{
    sendMessage(){
      console.log("hi");
    }
  }
}
</script>

<style>

</style>