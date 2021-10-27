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
          <div class="col-4">
            <MessageConfirm/>
          </div>
          <div class="col"></div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import OnlineList from "@/components/messageview/OnlineList.vue"
import MessageForm from "@/components/messageview/MessageForm.vue"
import MessageConfirm from "@/components/messageview/MessageConfirm.vue"
export default {
  components:{
    OnlineList, MessageForm,MessageConfirm
  },
}
</script>

<style>

</style>