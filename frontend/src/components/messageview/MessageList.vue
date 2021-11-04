<template>
  <section class="MessageList">
    <div class="m_title">
        <p>받은 쪽지함</p>
    </div>
    <div class="m-contain">
    </div>
    <div class="m_card">
      <ul class="list-group overflow-auto pt-3">
        <MessageCard  v-for="(message, index) in messagelist" :key="index" :message="message"
        @click.native="readAndGet(message)"/>
      </ul>
    </div>
  </section>
</template>

<script>
import messageAPI from "@/api/message.js";
import MessageCard from "@/components/messageview/MessageCard.vue"
import { mapActions } from "vuex";
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
    },
    methods:{
      ...mapActions('message', ['getMessage']),
      readAndGet(mm){
        if(!mm.read){
          this.reading(mm.msgId)
        }

        this.getMessage(mm)
      },

      async reading(msgId){
        await messageAPI
          .reading(msgId)
          .then((res) => {
          console.log(res.data)
        })
        .catch((error) => {
          alert("x");
          console.log(error);
        });
          
        
      }
    },
    created(){

    }
}
</script>

<style>

</style>