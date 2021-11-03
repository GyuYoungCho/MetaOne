<template>
  <div> 
    <div class="education">
      <p class="edu_title">교육 내역</p>
      <div class="m-contain">
      </div>
      <div class="row">
        <ul class="list-group mt-2">
          <li class="list-group-item bg-transparent" style="border-bottom : 3px solid">
            <div class="row">
              <div class="col">구분</div>
              <div class="col">현황</div>
              <div class="col">통과 시간</div>
              <div class="col">증명서</div>
            </div>
          </li>
          <li class="list-group-item bg-transparent" v-for="(eduitem, index) in column" :key="index" :eduitem="eduitem">
            <div class="row">
              <div class="pt-2 col">{{eduitem.education}}</div>
              <div class="pt-2 col">{{eduitem.complete}}</div>
              <div class="pt-2 col">{{pass_time(eduitem.pass_day)}}</div>
              <div class="pt-2 col" >
                <RouterLink :to="{name:'Certificate'}" @click.native="goCertificate(eduitem)">
                {{eduitem.pass}}
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
import moment from "moment"
import { mapGetters,mapActions } from "vuex";

export default {
  data(){
    return{
      column : [{education:'지진',complete:'이수완료',pass_day : new Date(),pass : '발급'},
      {education:'화재',complete:'이수완료',pass_day : new Date(),pass : '발급'}],
    }
  },
  computed:{
    ...mapGetters("education", ["educations","selecteducation"]),
  },
  methods:{
    ...mapActions('education', ['getEducation','getEducations']),
    pass_time(val){
      return moment(val).format("HH:mm:ss")
    },
    goCertificate(item){
      this.getEducation(item)
    }
  },
  created(){
    this.getEducations()
  }
}
</script>

<style>

</style>