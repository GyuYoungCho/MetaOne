<template>
  <div>
    <section class="messages">
      <div class="container-fluid">
        <div class="row">
          <div class="col"></div>
          <div class="col-2">
            <OnlineList/>
          </div>
          <div class="col-3">
            <MessageList/>
          </div>
          <div v-if="sendmode" class="col-4">
            <MessageSendForm/>
          </div>
          <div v-else class="col-4">
            <MessageConfirm/>
          </div>
          <div class="col"></div>
        </div>
      </div>
    </section>
    <ConfirmModal :contentBody ="completeMess"></ConfirmModal>
  </div>
</template>

<script>
import OnlineList from "@/components/messageview/OnlineList.vue"
import MessageList from "@/components/messageview/MessageList.vue"
import MessageSendForm from "@/components/messageview/MessageSendForm.vue"
import MessageConfirm from "@/components/messageview/MessageConfirm.vue"
import ConfirmModal from "@/components/ConfirmModal.vue"
import { mapGetters, mapActions } from "vuex";


export default {
  components:{
    OnlineList, MessageList,MessageConfirm,MessageSendForm,ConfirmModal
  },
  data(){
    return{
      completeMess:"전송되었습니다",
    }
  },
  computed:{
    ...mapGetters("message", ["sendmode"]),
  },
  methods:{
    ...mapActions("message", ["getMessage","getAllmode","getSendmode","getReceiver"]),
  
  },
  created(){
    this.getSendmode(true)
    this.getAllmode(true)
    this.getMessage(Object)
    this.getReceiver("")
  }
}
</script>

<style>

</style>