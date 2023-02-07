const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      "^/oauth/api/auth": {
        target: "http://10.20.5.52:8080", // http://192.168.156.50:8085/api/products/allProducts
        changeOrigin: true,
        // pathRewrite: { "^/api": "" },
      },
      "^/api/answer": {
        target: "http://10.20.3.153:8087",
        changeOrigin: true,
        pathRewrite: { "^/api": "" },
      },
      "^/api/question": {
        target: "http://10.20.3.153:8087",
        changeOrigin: true,
        pathRewrite: { "^/api": "" },
      },
      "^/api/comment": {
        target: "http://10.20.3.153:8087",
        changeOrigin: true,
        pathRewrite: { "^/api": "" },
      },
      "^/api/User": {
        target: "http://10.20.5.7:9080",
        changeOrigin: true,
        pathRewrite: { "^/api": "" },
      },
      "^/ads": {
        target: "http://10.20.5.47:8080",
        changeOrigin: true,
        // pathRewrite: { "^/api": "" },
      },
    },
  },
});
