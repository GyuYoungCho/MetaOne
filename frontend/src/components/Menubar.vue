<template>
  <div>
    <section class="menu">
      <div class="d-flex justify-content-end">
        <div class="row">
          <button class="icon-btn" @click="isShow = !isShow">
            <img class="menuImg" src="@/assets/image/buttonImage.png" alt="">
          </button>
        </div>
        <transition name="slide">
          <div class="row menuSelectMenu" v-if="isShow">
            <ul class="list-group mt-2">
              <RouterLink :to="{name:'UnityMap'}" @click.native="isShow=!isShow">
                <li class="list-group-item bg-transparent">
                  <i class="menuIcon fas fa-map-marker-alt"></i>
                  {{selectors[0]}}
                </li>
              </RouterLink>
              <RouterLink :to="{name:'MessageRecv'}" @click.native="isShow=!isShow">
                <li class="list-group-item bg-transparent">
                  <i class="menuAddIcon fas fa-envelope-square"></i>
                  {{selectors[1]}}
                </li>
              </RouterLink>
              <RouterLink :to="{name:'MyPage'}" @click.native="isShow=!isShow">
                <li class="list-group-item bg-transparent">
                  <i class="menuAddIcon fas fa-user"></i>
                  {{selectors[2]}}
                </li>
              </RouterLink>
              <li class="list-group-item bg-transparent"
                data-bs-toggle="modal" data-bs-target="#HelpModal">
                <i class="menuIcon fas fa-question"></i>
                {{selectors[3]}}
              </li>
              <li class="list-group-item bg-transparent" @click="logoutMethod()">
                <i class="menuAddIcon fas fa-sign-out-alt"></i>
                {{selectors[4]}}
              </li>
            </ul>
          </div>
        </transition>
      </div>
    </section>
    <HelpModal/>
  </div>
</template>

<script>
import HelpModal from '@/components/HelpModal.vue'
import { mapActions , mapState } from 'vuex'

export default {
  components:{
    HelpModal
  },
  data(){
    return{
      selectors : ["기본 맵", "쪽지", "마이페이지", "도움말", "로그아웃"],
      isShow : false,
    }
  },
  methods:{
    ...mapActions('user', ['logout']),
    async logoutMethod(){
      this.$store.commit('user/SET_USER_ISLOGIN', false)

      await this.logout()

      this.$router.push({name: 'Login'})
    },
  }
}
</script>

<style>

</style>