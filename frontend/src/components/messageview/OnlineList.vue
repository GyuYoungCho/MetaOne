// 온라인 사용자 목록

<template>
  <section class="MessageList">
    <div class="m_title">
      <p>접속자</p>
    </div>
    <div class="m-contain">
    </div>
    <div class="a_card">
        <ul class="list-group mt-3"> 
            <AvatarCard  v-for="(user, index) in onlinelist" :key="index" :user="user"
              @click.native="getOneMessage(user, index)" 
              :class="{'MessageListSelected':onlineLen[0]}"/>
        </ul>
    </div>
    
  </section>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import AvatarCard from "@/components/AvatarCard.vue"
export default {
    components:{
        AvatarCard
    },
    data(){
        return{
          onlineLen : []
        }
    },
    computed:{
      ...mapGetters('message',["allmode","onlinelist"])
    },
    methods:{
      ...mapActions('message', ['getReceiver','getOnebyOneMessages','getSendmode','getAllmode','getOnlineList']),
      getOneMessage(user, idx){
        this.getOnebyOneMessages(user.nickname)
        this.getReceiver(user.nickname)
        this.getSendmode(true)
        this.getAllmode(false)

        for (let i = 0; i < idx; i++) {
          this.onlineLen[i] = false
        }
        this.onlineLen[idx] = true
        
        for (let i = idx+1; i < this.onlineLen.length; i++) {
          this.onlineLen[i] = false
        }
      }
    },
    created(){
      this.getOnlineList()
      this.onlineLen = Array.from({length: this.onlinelist.length}, () => false);
    }
}
</script>

<style>

</style>