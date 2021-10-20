
// Vue3 관련 설정 파일
module.exports = {
  devServer: {
    compress: true,
    disableHostCheck: true,
    port: 8081,
    open: true,

    proxy: {
      "/api": {
        target: "http://localhost:8080/",
      },
    },
    headers: {
      "Access-Control-Allow-Origin": "*",
      "Access-Control-Allow-Headers":
        "Origin, X-Requested-With, Content-Type, Accept",
    },
    historyApiFallback: true,
    hot: true,
  },

  transpileDependencies: ["vuex-persist"],
  lintOnSave: false,
  outputDir: "../backend/src/main/resources/dist",
};
