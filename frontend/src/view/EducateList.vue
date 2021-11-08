<template>
  <div> 
    <div class="education">
      <p class="edu_title">교육 내역</p>
      <div class="m-contain">
      </div>
      <div class="row edulist">
        <ul class="list-group">
          <li class="list-group-item bg-transparent list-header">
            <div class="row">
              <div class="col">구분</div>
              <div class="col">현황</div>
              <div class="col">통과 시간</div>
              <div class="col">증명서</div>
            </div>
          </li>
          <li class="list-group-item bg-transparent" v-for="(eduitem, index) in educations" :key="index" :eduitem="eduitem">
            <div class="row">
              <div class="pt-2 col">{{eduitem.education}}</div>
              <div class="pt-2 col">{{isEducated(eduitem)}}</div>
              <div class="pt-2 col">{{pass_time(eduitem.passTime)}}</div>
              <div class="pt-2 col">
                <RouterLink v-if="eduitem.authenticated" :to="{name:'Certificate'}" @click.native="goCertificate(eduitem)">
                   발급
                </RouterLink>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters,mapActions } from "vuex";

export default {
  data(){
    return{
      
    }
  },
  computed:{
    ...mapGetters("education", ["educations","selecteducation"]),
  },
  methods:{
    ...mapActions('education', ['getEducation','getEducations']),
    pass_time(val){
      let hour = val/3600 < 10 ? '0'+ parseInt(val/3600) : parseInt(val/3600);
      let min = (val%3600)/60 < 10 ? '0'+ parseInt((val%3600)/60) : parseInt((val%3600)/60)
      let sec = val % 60 < 10 ? '0'+parseInt(val % 60) : parseInt(val % 60);
      return hour +":" + min+":"+sec
    },
    goCertificate(item){
      this.getEducation(item)
    },
    isEducated(val){
      return val?"이수 완료":"미이수"
    }
  },
  created(){
    this.getEducations()
  }
}
</script>

<style>

</style>