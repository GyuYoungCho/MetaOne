<template>
  <div id="app">
    <Menubar />
    <router-view/>
  </div>
</template>

<script>
import "./assets/css/index.scss"
import Menubar from "@/components/Menubar"

export default {
  name: 'App',
  components:{
    Menubar
  }
}
</script>

<style>
</style>
