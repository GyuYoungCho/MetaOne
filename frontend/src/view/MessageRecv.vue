<template>
  <div>
    <section class="messages">
      <div class="container-fluid">
        <div class="row">
          <div class="col"></div>
          <div class="col-2">
            <OnlineList :onlinelist="onlinelist" />
          </div>
          <div class="col-3">
            <MessageList :messagelist="mymessages"/>
          </div>
          <div class="col-4">
            <MessageConfirm :message="selectmessage"/>
          </div>
          <div class="col"></div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import OnlineList from "@/components/messageview/OnlineList.vue"
import MessageList from "@/components/messageview/MessageList.vue"
import MessageConfirm from "@/components/messageview/MessageConfirm.vue"
import { mapGetters, mapActions } from "vuex";


export default {
  components:{
    OnlineList, MessageList,MessageConfirm
  },
  data(){
    return{
      messagelist:[],
      sendmode:true,
    }
  },
  computed:{
    ...mapGetters("message", ["selectmessage","onlinelist","mymessages"]),
    ...mapGetters("process", ["sendmode"]),
  },
  methods:{
    ...mapActions("message", ["getMessage","getMyMessages","getOnlineList"]),
    ...mapActions("process", ["getSendmode"]),
  },
  created(){
    this.getOnlineList()
    this.getMyMessages()
    this.getSendmode(true);
    this.messagelist = this.mymessages
    console.log(this.mymessages)
  }
}
</script>

<style>

</style>