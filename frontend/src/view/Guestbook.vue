<template>
  <div>
    <section class="guestbookList">
      <div class="sketch">
        <img src="@/assets/image/sketch.png" alt="">
        <p class="guest_title">방명록</p>
      </div>
      <ul class="list-group overflow-auto mt-2">
        <li class="list-group-item bg-transparent" v-for="(guestitem, index) in guestbooks" :key="index" :guestitem="guestitem">
          <div class="row">
            <div class="pt-2 col-2 txl">{{guestitem.nickname}}</div>
            <div class="pt-2 col-4 txl">{{guestitem.content}}</div>
            <div class="pt-2 col-1 txl"></div>
            <div class="pt-2 col-3 txl">{{register_time(guestitem.createAt)}}</div>
            <div class="col txl">
              <button class="btn-modify" @click="openModal('modify',guestitem)" v-if="guestitem.nickname==nickname"
              data-bs-toggle="modal" data-bs-target="#FormModal">수정</button>
            </div>
          </div>
        </li>
      </ul>
      <div class="justify-content-center">
        <button class="mt-2" @click="openModal('register',null)" data-bs-toggle="modal" data-bs-target="#FormModal">작성</button>
      </div>
      <FormModal :sign="sign" :content="content"></FormModal>
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
        let ob = {
          content:''
        }
        this.selectGuestbook(content!=null ? content:ob);
        
    },
    register_time(val){
        return moment(val).format("yyyy.MM.DD HH:mm")
    },
  },
  created(){
    let today = moment(new Date()).format("yyyy-MM-DD")
    this.getGuestbooks(today)
  }
}
</script>

<style>

</style>