<template>
    <div>
        <div class="row sort">
            <div class="col-md-2">{{title}}</div>
            <div class="col-md-10">
                <input type="email" class="form-control" v-model="dataIn" @keyup="onOmit()" :placeholder="placeholderData">
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "Inputparam",
    props: ["title", "placeholderData"],
    data(){
        return{
            dataIn: "",
        }
    },
    methods:{
        onOmit(){
            this.$emit("data", this.dataIn)
        },
    }, 
    mounted(){

    },
    created(){

    },
    computed:{

    },
    watch:{

    }
}
</script>

<style>
.sort{
    /* float: left; */
    text-align: left;
}
</style>