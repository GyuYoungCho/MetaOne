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
              @click.native="getOneMessage(user)"
              :class="{'listSelected': selectUser(user)}" />
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
          
          OnlinePolling : null,
        }
    },
    computed:{
      ...mapGetters('message',["allmode","onlinelist","selectreceiver"]),
      onlineName(){
        if(this.onlinelist){
          return this.onlinelist.map(value => value.nickname)
        }else return []
      }
    },
    methods:{
      ...mapActions('message', ['getReceiver', 'getMessage', 'getOnebyOneMessages','getSendmode','getAllmode','getOnlineList']),
      
      pollOnlineList(){
        this.OnlinePolling = setInterval(()=>{
          this.getOnlineList()
        },5000)
      },
      
      getOneMessage(user){
        this.getOnebyOneMessages(user.nickname)
        
        if(user.nickname != this.selectreceiver){
          this.getMessage(Object)
          this.getReceiver(user.nickname)
        }
        this.getSendmode(true)
        this.getAllmode(false)
      },

      selectUser(val){
        if (val.nickname==this.selectreceiver) return true
        else false
      }
    },
    created(){
      this.getOnlineList()
      this.pollOnlineList()
    },
    beforeDestroy(){
      clearInterval(this.OnlinePolling)
    },
    watch:{
      onlineName(val){
        if(!val.includes(this.selectreceiver)){
          this.getReceiver("")
          this.getAllmode(true)
        }
      }
    }
}
</script>

<style>

</style>