<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
import "./assets/css/index.scss"

export default {
  name: 'App',
}
</script>

<style>
#app {
  font-family: "Noto Sans KR", sans-serif;
  margin-top: 0px;
  background-color: rgba(154, 69, 235, 0.8);
  height: 1080px;
}
</style>
