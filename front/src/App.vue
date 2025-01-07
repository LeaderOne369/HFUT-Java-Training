<template>
  <el-config-provider :locale="zhCn">
    <router-view></router-view>
  </el-config-provider>
</template>

<script setup>
import zhCn from 'element-plus/lib/locale/lang/zh-cn'
import {Api} from "./api/index.js";
import {useRoute, useRouter} from "vue-router";
import useToken from "./stores/token.js";

const route = useRoute()
if (route.name !== 'login') {
  Api.checkalive().catch(() => {
    useRouter().push('/login')
    useToken().removeToken()
  })
}
</script>
