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
      console.log(payload)
      console.log(payload.notification)
      this.$store.dispatch('user/onNotification', payload)
    })
  },
  methods:{
    ...mapActions('user', ['onNotification']),
    ...mapActions('process', ['getSubComplete'])
  }
}
</script>

<style>
</style>
