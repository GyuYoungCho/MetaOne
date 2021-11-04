<template>
  <div>
      <unity id="bts-unity"
            src = "./unity/Build/webGL.json"
            unityLoader = "./unity/Build/UnityLoader.js"
            ref = "hookInstance"
            :height = height
            :width = width
            :hideFooter="true"
            >
  </div>
</template>

<script>
import Unity from 'vue-unity-webgl';
export default {
    components : {Unity},
}
</script>

<style>

</style>