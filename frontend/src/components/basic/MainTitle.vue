<template>
    <div class="main_title">
        {{title}}
    </div>
</template>

<script>
export default {
    name: "MainTitle",
    props: ["title"],
}
</script>

<style>

.main_title{
    font-size: 5em;
    text-align: center;
    float: center;
    color: white;
    padding-bottom: 5%;
}

</style>