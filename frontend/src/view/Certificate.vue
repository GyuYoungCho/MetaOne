<template>
  <div> 
    <div class="eduCer">
      <p class="edu_title">이수증</p>
      <div class="m-contain">
        <img class="trophy mt-3 mb-3" src="@/assets/image/trophy.png" alt="not">
        <p>성명 : {{name}}</p>
        <p>교육 내용 : {{selecteducation.education}} 교육</p>
        <p>교육 날짜 : {{m_pass_day(selecteducation.pass_day)}}</p>
        <br>
        <p>위 교육생은 상기 교육과정을 성실히 이수하였으므로
            교육 이수증을 수여합니다.</p>
        <img class="stamp mt-4" src="@/assets/image/buttonImage.png" alt="not">
      </div>
      <div class="mt-3 justify-content-center">
        <button class="confirm" @click="page_back">확인</button>
      </div>
    </div>
  </div>
</template>

<script>
import moment from "moment";
import { mapGetters } from "vuex";
export default {
    data(){
        return{
          name:"jo",
        }
    },
    computed:{
      ...mapGetters("education", ["selecteducation"]),
    },
    methods:{
        m_pass_day(val){
            return moment(val).format("yyyy.MM.DD")
        },
        page_back(){
          this.$router.back()
        }
    },
    created(){
      console.log(this.selecteducation)
    }
}
</script>

<style>

</style>