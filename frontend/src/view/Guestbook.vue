<template>
  <div>
    <section class="guestbookList">
      <div class="sketch">
        <img src="@/assets/image/sketch.png" alt="">
        <p class="guest_title">방명록</p>
      </div>
      <ul class="list-group mt-2">
        <li class="list-group-item bg-transparent" v-for="(guestitem, index) in guestbooks" :key="index" :guestitem="guestitem">
          <div class="row">
            <div class="pt-2 col-2 txl">{{guestitem.nickname}}</div>
            <div class="pt-2 col-3 txl">{{guestitem.content}}</div>
            <div class="pt-2 col-5 txr">{{register_time(guestitem.createAt)}}</div>
            <div class="pt-1 col txl">
              <button class="btn-modify" @click="openModal('modify',guestitem.content)" v-if="guestitem.nickname==nickname"
              data-bs-toggle="modal" data-bs-target="#FormModal">수정</button>
            </div>
          </div>
        </li>
      </ul>
      <div class="justify-content-center">
        <button class="mt-2" @click="openModal('register','')" data-bs-toggle="modal" data-bs-target="#FormModal">작성</button>
      </div>
      <FormModal :sign="sign" :content="content" @openModal="openModal"></FormModal>
    </section>
  </div>
</template>

<script>
import moment from 'moment';
import FormModal from '@/components/FormModal.vue'
import { mapState, mapGetters,mapActions } from "vuex";
export default {
  components:{
    FormModal
  },
  data(){
    return{
      column : [
        {nickname:'JO',content:'ㅊㅊ',createAt: new Date(),userid:1},
        {nickname:'KWON',content:'뉴비 만반잘부',createAt: new Date(),userid:2},
        {nickname:'KIM',content:'1빠',createAt: new Date(),userid:3}
      ],
      userid : 1,
      sign : '',
      content: ''
    }
  },
  computed:{
    ...mapGetters("guestbook", ["guestbooks"]),
    ...mapState("user", ["nickname"]),
  },
  methods:{
    ...mapActions('guestbook', ['selectGuestbook','getGuestbooks']),
    openModal(sign,content){
        this.sign = sign
        this.content = content;
        
    },
    register_time(val){
        return moment(val).format("yyyy.MM.DD HH:mm")
    },
  },
  created(){
    this.getGuestbooks()
  }
}
</script>

<style>

</style>