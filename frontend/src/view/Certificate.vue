<template>
  <div> 
    <div class="eduCer">
      <p class="edu_title">이수증</p>
      <div class="m-contain">
        <img class="menuImg" src="@/assets/image/trophy.png" alt="">
        <p>성명 : {{eduitem.name}}</p>
        <p>교육 내용 : {{eduitem.eduname}} 교육</p>
        <p>교육 날짜 : {{m_pass_day(eduitem.pass_day)}}</p>
        <br>
        <p>위 교육생은 상기 교육과정을 성실히 이수하였으므로
            교육 이수증을 수여합니다.</p>
        <img class="menuImg" src="@/assets/image/buttonImage.png" alt="">
      </div>
      <div class="mt-3 justify-content-center">
        <button >확인</button>
      </div>
    </div>
  </div>
</template>

<script>
import moment from "moment"
export default {
    data(){
        return{
            eduitem:{
                name : "jo",
                eduname : "지진",
                pass_day : new Date(),
            }
        }
    },
    methods:{
        m_pass_day(val){
            return moment(val).format("yyyy.MM.DD")
        }
    }
}
</script>

<style>

</style>