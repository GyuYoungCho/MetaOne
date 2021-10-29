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
            <MessageSendForm/>
          </div>
          <div class="col"></div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import OnlineList from "@/components/messageview/OnlineList.vue"
import MessageSendForm from "@/components/messageview/MessageSendForm.vue"
import MessageList from "@/components/messageview/MessageList.vue"
export default {
  components:{
    OnlineList, MessageSendForm,MessageList
  },
}
</script>

<style>

</style>