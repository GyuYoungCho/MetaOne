<template>
  <div id="app">
    <Menubar v-if="isLogin"/>
    <div v-else style="width: 50px;height: 105px"></div>
    <router-view/>
  </div>
</template>

<script>
import "./assets/css/index.scss"
import Menubar from "@/components/Menubar"
import { messaging } from '@/api/firebase.js'
import { mapActions, mapState } from 'vuex'


export default {
  name: 'App',
  components:{
    Menubar
  },
  computed: {
    ...mapState('user', ['isLogin',])
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
