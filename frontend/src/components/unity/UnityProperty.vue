<template>
  <div class="unityScreen">
    <div id="unity-container" class=" unity-desktop" style="margin:100px 0 0 0">
      <canvas id="unity-canvas"  ></canvas>
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
        canvas.style.width = "800px";
        canvas.style.height = "500px";
        console.log("?")
      }
      loadingBar.style.display = "block";
      var script = document.createElement("script");
      script.src = loaderUrl;
      
      script.onload = () => {
        window.createUnityInstance(canvas, config, (progress) => {
          progressBarFull.style.width = 100 * progress + "%";
        }).then((unityInstance) => {
          this.$store.commit("getUnityIn",unityInstance);
          loadingBar.style.display = "none";
        }).catch((message) => {
          alert(message);
        });
      };
      document.body.appendChild(script);
      
      // if (this.$route.name !== "Town") {
      //   canvas.style.width = "300px";
      //   canvas.style.height = "300px";
      // }
      // else {
      //   canvas.style.width = "1120px";
      //   canvas.style.height = "630px";
      // }
    }
  }
}
</script>

<style>

</style>