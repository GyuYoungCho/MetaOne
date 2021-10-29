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
            
        }
    },
    props:{
      messagelist:Array,
    }
}
</script>

<style>

</style>