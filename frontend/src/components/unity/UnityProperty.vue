<template>
  <div class="unityScreen">
    <UnityData :instance="instance" />
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
    <div class="row start" v-if="!getInstance">
        <button class="btn yellow-btn" @click="startUnityMap()">시작하기</button>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
import UnityData from "./UnityData.vue";
export default {
  components:{
    UnityData
  },
  data(){
    return{
      instance : null,
      unityInterval : '',
    }
  },
  computed:{
    ...mapGetters('process',['getInstance','subComplete']),
    allMap(){
        if(this.$route.name == 'UnityMap') return true;
        else return false;
    }
  },
  watch:{
    allMap(val){
      var canvas = document.querySelector("#unity-canvas");
      if(val){
        canvas.style.width = "1280px";
        canvas.style.height = "800px";
      }else{
        canvas.style.width = "150px";
        canvas.style.height = "100px";
      }
    }
  },
  created(){
    if(!this.getInstance){
      this.getSubComplete(true)
    }
  },
  mounted(){
    if(!this.getInstance){
      let loader = document.querySelector(".loader__wrap");
      loader.style.zIndex = 2004;
      setTimeout(()=>{
        loader.style.zIndex = 2000;
        
      },5000)
    }
    this.runWebGL()
  },

  methods:{
    ...mapActions('process',['getSubComplete']),
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
      
      script.onload = async () => {
        await window.createUnityInstance(canvas, config, (progress) => {
          progressBarFull.style.width = 100 * progress + "%";
        }).then((unityInstance) => {
          this.instance = unityInstance
          loadingBar.style.display = "none";
          if(this.instance !== undefined) this.instance.SendMessage('GameManager','initNickname',this.nickname);
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
    startUnityMap(){
      
        if(this.instance !== undefined) this.instance.SendMessage('GameManager','initNickname',this.nickname);
        this.$store.commit("process/SET_UNITY_INSTANCE",true);
        this.getSubComplete(true)
        setTimeout(() => {
          this.getSubComplete(false)
        }, 1000);
    },
    goUnityMap(){
        this.$router.push({name: 'UnityMap'})
    },
  }
}
</script>

<style>

</style>