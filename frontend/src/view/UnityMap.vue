<template>
  <div>
    <ChattingView v-if="unityRoom"/>
  </div>
</template>

<script>

import ChattingView from "@/components/messageview/ChattingView.vue"
import { mapState } from 'vuex'
export default {
    components:{
        ChattingView
    },
    computed:{
      ...mapState('unity', ['unityRoom']),
    }
}
</script>

<style>

</style>