<template>
  <div class="loader__wrap inactive">
    <div class="loader__box">
      <img class="loader__logo" src="@/../public/pavicon.png" alt="">
    </div>
  </div>
</template>

<script>
export default {
  name:'Loader',
  props:{
    isLoading: Boolean,
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
  @keyframes logo {
    0% {
      opacity: 1;
    }
    50% {
      opacity: 0.5;
    }
    to {
      opacity: 0;
    }
  }
}
.inactive{
  display: none;
}
</style>