<template>
  <div>
    <ChattingView/>
    <div id="unity-container">
        <div id="unity"></div>
    </div>
  </div>
</template>

<script>

import ChattingView from "@/components/messageview/ChattingView.vue"
export default {
    components:{
        ChattingView
    }
}
</script>

<style>

</style>