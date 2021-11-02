<template>
  <div id="app">
    <Menubar />
    <router-view/>
  </div>
</template>

<script>
import "./assets/css/index.scss"
import Menubar from "@/components/Menubar"
import messaging from '@/api/firebase.js'
import { mapActions } from 'vuex'


export default {
  name: 'App',
  components:{
    Menubar
  },
  created(){
    messaging.onMessage((payload) => {
      console.log(payload)
      console.log(payload.notification)
      this.$store.dispatch('user/onNotification', payload.notification)
    })
  },
  methods:{
    ...mapActions('user', ['onNotification'])
  }
}
</script>

<style>
</style>
