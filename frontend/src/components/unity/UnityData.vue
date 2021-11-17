<template>
    <div id="unity-data">
        <input id="unity-character" style="display:none" v-model="unityCharacter" >
        <input id="unity-room" style="display:none" v-model="unityRoom">
        <input id="unity-education-name" style="display:none" v-model="unityEducationName">
        <input id="unity-education-time" style="display:none" v-model="unityEducationTime">
        <input id="unity-education-auth" style="display:none" v-model="unityEducationAuth">
        <input type="text" id="unity-object" style="display:none" v-model="unityObject">
    </div>

</template>

<script>
import { mapActions, mapState ,mapGetters} from 'vuex'
import educationAPI from "@/api/education.js";

export default {
    name: "UnityData",
    data(){
        return{
            unityObject: "",
            unityCharacter: "",
            unityRoom: "",
            unityEducationName: "",
            unityEducationTime: "",
            unityEducationAuth: "",
            education:"",
            isFireEducated:0,
            isEarthEducated:0,
            
        }
    },
    props:{
        instance :Object
    },
    async created(){
        this.$store.commit('unity/SET_UNITY_CHARACTER', "") 
        this.$store.commit('unity/SET_UNITY_ROOM', "")
        this.$store.commit('unity/SET_UNITY_EDUCATIONNAME', "")
        this.$store.commit('unity/SET_UNITY_EDUCATIONTIME', 0)
        this.$store.commit('unity/SET_UNITY_EDUCATIONAUTH', false)
        this.$store.commit('unity/SET_UNITY_OBJECT','')

        await this.getEducations()

        if(this.educations){
            this.educations.forEach((item) => {
                if(item.education=="화재") this.isFireEducated = 1
                if(item.education=="지진") this.isEarthEducated = 1
            });
        }
    },
    async mounted(){                                // 테스트를 위해 페이지 로딩 시 axois 수행해보기
        this.interval = setInterval(()=>{
            this.unityObject = document.getElementById("unity-object").value
            this.unityCharacter = document.getElementById("unity-character").value
            this.unityRoom = document.getElementById("unity-room").value
            this.unityEducationName = document.getElementById("unity-education-name").value    // fire, earthquake, corona, typhoon
            this.unityEducationTime = document.getElementById("unity-education-time").value
            this.unityEducationAuth = document.getElementById("unity-education-auth").value

        },1000)
        
    },
    destroyed(){
        clearInterval(this.interval);
    },
    methods:{
        ...mapActions('unity', ['setCharacter', 'setRoom',  'setEducationTime', 'setEducationAuth']),
        ...mapActions('education',['getEdunum','getEducations']),
        async setCharacterMethod(){
            await this.$store.commit('unity/SET_UNITY_CHARACTER', this.unityCharacter)        // 파일명 저장
            await this.setCharacter()
            this.instance.SendMessage('MainCamera','initPlayerNickName',this.nickname);
            this.instance.SendMessage('MainCamera','isFireEducated',this.isFireEducated);
            this.instance.SendMessage('MainCamera','isEarthquakeEducated',this.isEarthEducated);
        },
        async setRoomMethod(){
            await this.$store.commit('unity/SET_UNITY_ROOM', this.unityRoom)
        },
        async setEducationTimeMethod(){
            console.log("time...")
            await this.$store.commit('unity/SET_UNITY_EDUCATIONTIME', this.unityEducationTime)
            await this.setEducationTime()
            await this.getEducaitons()
            document.getElementById("unity-education-time").value= "";
            this.unityEducationTime=""
            
        },
        async setEducationAuthMethod(){
            await educationAPI.registerAttendance(this.education)
                .then((res) => {
                    console.log(res)
                })
                .catch((error) => {
                alert("못가져옴");
                console.log(error);
                });

            document.getElementById("unity-education-auth").value= "";
            this.unityEducationAuth=""
            await this.getEducaitons()
        },
    },
    computed:{
        ...mapState('user', ['nickname', 'email']),
        ...mapGetters("education", ["educations"]),
        ...mapState('unity',['unityEduName']),
        // educated_list(){
        //     let elist=[]
        //     if(this.educations){
        //         this.educations.forEach((item) => {
        //             if(item.education=="화재") this.isFireEducated = 1
        //             if(item.education=="지진") this.isEarthEducated = 1
        //         });
        //     }
        //     return elist
        // }
    },
    watch:{
        unityObject(val){
            console.log(val)
            switch (val) {
                case "guestbook":
                    document.getElementById("unity-object").value= "";
                    this.unityObject=""
                    this.$router.push({name : 'Guestbook'});
                    break;
                case "rank1":
                    this.getEdunum(1);
                    document.getElementById("unity-object").value= "";
                    this.unityObject=""
                    this.$router.push({name : 'Rank'});
                    break;
                case "rank2":
                    this.getEdunum(2);
                    document.getElementById("unity-object").value= "";
                    this.unityObject=""
                    this.$router.push({name : 'Rank'});
                    break;
                case "roomOut":
                    this.$store.commit('unity/SET_UNITY_ENTERROOM', false)
                    document.getElementById("unity-object").value= "";
                    this.unityObject=""
                    break;
                
                default:
                    break;
            }
        },
        unityCharacter(val){
            if(val) {
                this.setCharacterMethod()
                document.getElementById("unity-character").value= "";
                this.unityRoom = ""
            }
        },
        unityRoom(val){               // 방 이름
            if(val) {
                this.setRoomMethod()
                document.getElementById("unity-room").value= "";
                this.unityRoom = ""
                this.$store.commit('unity/SET_UNITY_ENTERROOM', true)
               
            }
        },
        unityEducationName(val){           // 교육 명 : Vuex 저장
            if(val){
                if(val=='fire') this.education = "화재"
                if(val=='earthquake') this.education = "지진"
                this.$store.commit('unity/SET_UNITY_EDUCATIONNAME', this.education)
                document.getElementById("unity-education-name").value= "";
                this.unityEducationName=""
            }
        },
        unityEducationTime(val){           // 시험 통과 시간
            if(val) this.setEducationTimeMethod()
        },
        unityEducationAuth(val){           // 교육 수강 여부
            if(val) this.setEducationAuthMethod()
        },
        educations(val){
            if(val){
                val.forEach((item) => {
                    if(item.education=="화재") this.isFireEducated = 1
                    if(item.education=="지진") this.isEarthEducated = 1
                });
            }
        }
        
    }
}
</script>

<style>

</style>