<template>
  <section class="MessageList">
    <div class="m_title">
        <p>받은 쪽지함</p>
    </div>
    <div class="m-contain overflow-auto pt-3">
      <ul class="list-group">
        <MessageCard  v-for="(message, index) in messagelist" :key="index" :message="message"/>
      </ul>
    </div>
  </section>
</template>

<script>
import MessageCard from "@/components/messageview/MessageCard.vue"
export default {
    components:{
        MessageCard
    },
    data(){
        return{
            messagelist:[
                {title:'안녕',isRead:false},
                {title:'오늘은',isRead:true},
                {title:'집에',isRead:true},
            ]
        }
    }
}
</script>

<style>

</style>