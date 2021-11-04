<template>
  <section class="MessageForm">
    <div class="m_title">
        <p>쪽지 보내기</p>
    </div>
    <div class="m-contain">
    </div>
    <div class="send_card">
      <div class="row message_com m_user mt-3">
        
        <div class="col-md-3 pt-1 px-0">{{form_content[0]}}</div>
        <div class="col-md-9">
            <input type="text" class="form-control" readonly v-model="valid_receiver">
        </div>
      </div>
      <div class="row message_com m_title mt-3">
        <div class="col-md-2 pt-1 px-0">{{form_content[1]}}</div>
        <div class="col-md-10">
            <input type="text" class="form-control" v-model="title">
        </div>
      </div>
      <div class="row message_com m_content mt-3">
        <label for="ContentArea" class="content-label">{{form_content[2]}}</label>
        <textarea class="form-control" id="ContentArea" rows="10" v-model="content"></textarea>
      </div>
      <div class="row message_com mt-4 m_submit">
        <button @click="sendMessage()" data-bs-toggle="modal" data-bs-target="#ConfirmModal">전송</button>
      </div>
    </div>
    
  </section>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";
import messageAPI from "@/api/message.js";

export default {
  components:{
  },
  data(){
    return{
      form_content: ["받는 사람", "제목", "내용"],
      empty: '',
      title:'',
      content:'',
    }
  },
  computed:{
    ...mapState('user', ["firebaseToken"]),
    ...mapGetters("message", ["selectreceiver"]),
    valid_receiver(){
      if(this.selectreceiver && typeof this.selectreceiver =="string") 
        return this.selectreceiver
      else return ''
    }
  },

  methods:{
    ...mapActions("message",["getMyMessages","getOnebyOneMessages"]),
    async sendMessage(){

      if(this.valid_receiver=="" || this.title=="" || this.content==""){
        alert("입력되지 않은 정보가 있습니다.")
        return
      }

      const message = {
        nickname: this.valid_receiver,
        title: this.title,
        content: this.content,
        firebaseToken: this.firebaseToken,
      }

      await messageAPI
        .sendOne(message)
        .then((res) => {
          console.log(res.data)
        })
        .catch((error) => {
          alert("못가져옴");
          console.log(error);
        });

      await this.getMyMessages()
      await this.getOnebyOneMessages(this.valid_receiver)
    }
  }
}
</script>

<style>

</style>