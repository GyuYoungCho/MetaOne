<template>
  <section class="MessageList">
    <div class="m_title">
      <p>접속자 목록</p>
    </div>
    <div class="m-contain">
    </div>
    <div class="a_card">
        <ul class="list-group mt-3">
            <AvatarCard  v-for="(user, index) in onlinelist" :key="index" :user="user"
              @click.native="getOneMessage(user)"/>
        </ul>
    </div>
    
  </section>
</template>

<script>
import { mapActions } from "vuex";
import AvatarCard from "@/components/AvatarCard.vue"
export default {
    components:{
        AvatarCard
    },
    data(){
        return{
            
        }
    },
    props:{
        onlinelist:Array,
    },
    methods:{
      ...mapActions('message', ['getReceiver']),
      getOneMessage(user){
        
      }
    }
}
</script>

<style>

</style>