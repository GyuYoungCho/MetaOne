<template>
  <div class="unityScreen">
    <div id="unity-container" class="unity-desktop" :class="{'unity-mini':!allMap}">
      
      <div class="back-map" v-if="!allMap" @click="goUnityMap()"> 
        <i class="fas fa-long-arrow-alt-left"></i>
        <p class="back-map-text">Back</p>
      </div>

      <canvas id="unity-canvas" ></canvas>
      <div id="unity-loading-bar">
        <div id="unity-logo"></div>
        <div id="unity-progress-bar-empty">
          <div id="unity-progress-bar-full"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {

  computed:{
    allMap(){
        if(this.$route.name == 'UnityMap' || this.$route.name ==  'StartMap') return true;
        else return false;
    }
  },
  watch:{
    allMap(val){
      var canvas = document.querySelector("#unity-canvas");
      if(val){
        canvas.style.width = "1000px";
        canvas.style.height = "700px";
      }else{
        canvas.style.width = "150px";
        canvas.style.height = "100px";
      }
    }
  },
  
  mounted(){
    this.runWebGL()
  },

  methods:{
    runWebGL(){
      var buildUrl = "unity/Build";
      var loaderUrl = buildUrl + "/unity.loader.js";
      var config = {
        dataUrl: buildUrl + "/unity.data",
        frameworkUrl: buildUrl + "/unity.framework.js",
        codeUrl: buildUrl + "/unity.wasm",
        streamingAssetsUrl: "StreamingAssets",
        companyName: "DefaultCompany",
        productName: "MetaOne",
        productVersion: "1.0",
      };
      var container = document.querySelector("#unity-container");
      var canvas = document.querySelector("#unity-canvas");
      var loadingBar = document.querySelector("#unity-loading-bar");
      var progressBarFull = document.querySelector("#unity-progress-bar-full");
      var mobileWarning = document.querySelector("#unity-mobile-warning");

      if (/iPhone|iPad|iPod|Android/i.test(navigator.userAgent)) {
        container.className = "unity-mobile";
        config.devicePixelRatio = 1;
        mobileWarning.style.display = "block";
        setTimeout(() => {
          mobileWarning.style.display = "none";
        }, 5000);
      } else {
        canvas.style.width = "1280px";
        canvas.style.height = "800px";
      }
      loadingBar.style.display = "block";
      var script = document.createElement("script");
      script.src = loaderUrl;
      
      script.onload = () => {
        window.createUnityInstance(canvas, config, (progress) => {
          progressBarFull.style.width = 100 * progress + "%";
        }).then((unityInstance) => {
          this.$store.commit("process/SET_UNITY",unityInstance);
          loadingBar.style.display = "none";
        }).catch((message) => {
          alert(message);
        });
      };
      document.body.appendChild(script);

      if(this.allMap){
        canvas.style.width = "1280px";
        canvas.style.height = "800px";
      }else{
        canvas.style.width = "150px";
        canvas.style.height = "100px";
      }
    },

    goUnityMap(){
        if(!this.allMap){
            this.$router.push({name : "UnityMap"});
        }
    },
  }
}
</script>

<style>

</style>