<template>
  <div class="loader__wrap inactive">
    <div class="loader__box">
      <img class="loader__logo" src="@/assets/image/loading.gif" alt="">
      <p class="loader__text">잠시만요...!</p>
    </div>
  </div>
</template>

<script>
export default {
  name:'Loader',
  data(){
    return{
      width:1280,
      height:800,
    }
  },
  props:{
    isLoading: Boolean,
  },
  mounted(){
    this.$nextTick(() => {
        window.addEventListener('resize', this.onResize);
    })
  },
  watch:{
    isLoading: function(){
      const loader = document.querySelector('.loader__wrap');
      if(this.isLoading){
        loader.style.width = `${window.innerWidth}px`;
        loader.style.height = `${window.innerHeight}px`;
        loader.classList.toggle('inactive')
      } else{
        loader.classList.toggle('inactive')
      }
    },
    width(val){
      const loader = document.querySelector('.loader__wrap');
      loader.style.width = `${val}px`;
    },
    height(val){
      const loader = document.querySelector('.loader__wrap');
      loader.style.height = `${val}px`;
    }
  },
  methods:{
    onResize(){
      this.width = window.innerWidth
      this.height = window.innerHeight
    }
  }
}
</script>

<style lang="scss" scoped>
.loader{
  &__wrap{
    position: absolute;
    top : 0%;
    z-index: 2000;
    background-color: rgba(92, 92, 92, 0.726);
    display: flex;
    justify-content: center;
    align-items: center;
  }
  &__logo{
    opacity: 1;
    animation: logo 1.5s ease-in Infinite;
  }
  &__text{
    color: darken(white, 20%);
    text-align: center;
    padding-bottom: 15%;
  }
  @keyframes logo {
    0% {
      opacity: 1;
    }
    50% {
      opacity: 0.7;
    }
    to {
      opacity: 0.3;
    }
  }
}
.inactive{
  display: none;
}
</style>