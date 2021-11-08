<template>
  <div id="app">
    <ConfirmModal :contentBody ="contentBody"></ConfirmModal>
    <Loader :isLoading="subComplete"/>
    <Menubar v-if="isLogin"/>
    <div v-else style="width: 50px;height: 105px"></div>
    <router-view/>
  </div>
</template>

<script>
import "./assets/css/index.scss"
import Menubar from "@/components/Menubar"
import Loader from '@/components/Loader';
import ConfirmModal from "@/components/ConfirmModal.vue"
import { messaging } from '@/api/firebase.js'
import { mapActions, mapState } from 'vuex'


export default {
  name: 'App',
  components:{
    Menubar, Loader, ConfirmModal
  },
  computed: {
    ...mapState('user', ['isLogin',]),
    ...mapState('process',['subComplete','contentBody'])
  },
  created(){
    this.getSubComplete(false);
    this.getContentBody("")
    messaging.onMessage((payload) => {
      this.messageUpdate(payload.data.nickname)
    })
  },
  methods:{
    ...mapActions('user', ['onNotification']),
    ...mapActions('process', ['getSubComplete','getContentBody']),
    ...mapActions('message', ['getMyMessages','getOnebyOneMessages','getNewMessage','getOnlineList']),

    async messageUpdate(val){
      await this.getMyMessages()
      await this.getOnebyOneMessages(val)
      await this.getNewMessage(true)
      await this.getOnlineList()
    }
  }
}
</script>

<style>
</style>
