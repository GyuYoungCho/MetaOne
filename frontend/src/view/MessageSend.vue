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
            <MessageList :messagelist="messagelist"/>
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
  data(){
    return{
      onlinelist:[
          {name:'KIM'},{name:'KWON'},{name:'JO'}
      ],
      messagelist:[
          {title:'안녕',isRead:false,content:'반가워~~~',sender:'KIM'},
          {title:'오늘은',isRead:true,content:'뭐한담',sender:'KIM'},
          {title:'집에',isRead:true,content:'가고싶다',sender:'KWON'},
      ]
    }
  },

}
</script>

<style>

</style>