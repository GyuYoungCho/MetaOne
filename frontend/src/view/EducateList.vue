<template>
  <div> 
    <div class="eduCer">
      <p class="edu_title">교육 내역</p>
      <div class="m-contain">
        <div>
          <table class="table table-borderless table-responsive mt-3">
            <thead>
              <tr class="row-3">
                <th scope="col">구분</th>
                <th scope="col">현황</th>
                <th scope="col">통과 시간</th>
                <th scope="col">증명서</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(guestitem, index) in column" :key="index" :guestitem="guestitem">
                <td class="pt-2 col-3">{{guestitem[0]}}</td>
                <td class="pt-2 col-3">{{guestitem[1]}}</td>
                <td class="pt-2 col-3">{{pass_time(new Date())}}</td>
                <RouterLink :to="{name:'Certificate'}">
                <td class="pt-2 col-3">{{guestitem[3]}}</td>
                </RouterLink>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import moment from "moment"
export default {
  data(){
    return{
      column : [['지진','이수완료','1:19:52','발급'],
      ['지진','이수완료','1:19:52','발급']],
    }
  },
  computed:{

  },
  methods:{
    pass_time(val){
        return moment(val).format("HH:mm:ss")
    },
  }
}
</script>

<style>

</style>