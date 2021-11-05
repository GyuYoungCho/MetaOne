<template>
  <div id="app">
    <Loader :isLoading="$store.getters.subComplete"/>
    <Menubar v-if="isLogin"/>
    <div v-else style="width: 50px;height: 105px"></div>
    <router-view/>
  </div>
</template>

<script>
import "./assets/css/index.scss"
import Menubar from "@/components/Menubar"
import Loader from '@/components/Loader';
import { messaging } from '@/api/firebase.js'
import { mapActions, mapState } from 'vuex'


export default {
  name: 'App',
  components:{
    Menubar, Loader
  },
  computed: {
    ...mapState('user', ['isLogin',])
  },
  created(){
    this.getSubComplete(false);
    messaging.onMessage((payload) => {
      this.messageUpdate(payload.data.nickname)
    })
  },
  methods:{
    ...mapActions('user', ['onNotification']),
    ...mapActions('process', ['getSubComplete']),
    ...mapActions('message', ['getMyMessages','getOnebyOneMessages','getNewMessage']),

    async messageUpdate(val){
      await this.getMyMessages()
      await this.getOnebyOneMessages(val)
      await this.getNewMessage(true)
    }
  }
}
</script>

<style>
</style>
