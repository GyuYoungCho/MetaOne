<template>
  <section class="MessageList">
    <div class="m_title">
        <p>받은 쪽지함</p>
    </div>
    <div class="m-contain">
    </div>
    <div class="m_card">
      <ul class="list-group overflow-auto pt-3">
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
                {title:'안녕',isRead:false,content:'반가워~~~',sender:'KIM'},
                {title:'오늘은',isRead:true,content:'뭐한담',sender:'KIM'},
                {title:'집에',isRead:true,content:'가고싶다',sender:'KWON'},
            ]
        }
    }
}
</script>

<style>

</style>