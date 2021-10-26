<template>
  <div>
    <section class="guestbookList">
      <div class="sketch">
        <img src="@/assets/image/sketch.png" alt="">
        <div class="writer justify-content-center">
          <h4>방명록</h4>
          
        </div>
      </div>
      <div class="justify-content-center">
        <button @click="registerGuestBook">작성</button>
      </div>
    </section>
  </div>
</template>

<script>
import moment from 'moment';
import { mapGetters,mapActions } from "vuex";
export default {
  data(){
    return{
      column : ['nickname','content','createAt'],
    }
  },
  computed:{
    ...mapGetters("guestbook", ["guestbooks"]),
  },
  methods:{
    ...mapActions('guestbook', ['selectGuestbook','getGuestbooks']),
    openModal(val){
        this.selectGuestbook(this.guestbook)
        this.$emit('openRegisterModal', val)
    },
    register_time(val){
        return moment(val).format("yyyy.MM.DD HH:mm")
    },
  }
}
</script>

<style>

</style>