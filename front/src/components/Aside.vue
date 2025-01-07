<template>
    <el-row>
        <el-col :span="24">
            <el-menu
                active-text-color="#white"
                class="el-menu-vertical-demo"
                :default-active="active"
                text-color="#333"
            >
<!--                <router-link :to="{ name: 'dashboard' }">-->
<!--                    <el-menu-item index="1">-->
<!--                        <el-icon>-->
<!--                            <HomeFilled />-->
<!--                        </el-icon>-->
<!--                        <span>系统首页</span>-->
<!--                    </el-menu-item>-->
<!--                </router-link>-->
                <!-- 项目列表 -->
                <router-link :to="{ name: 'project' }">
                    <el-menu-item index="2">
                        <el-icon>
                            <List />
                        </el-icon>
                        <span>项目列表</span>
                    </el-menu-item>
                </router-link>
                <!-- 项目计划 -->
                <router-link :to="{ name: 'plan' }">
                    <el-menu-item index="3">
                        <el-icon>
                            <List />
                        </el-icon>
                        <span>项目计划</span>
                    </el-menu-item>
                </router-link>
                <!-- 项目预算 -->
                <router-link :to="{ name: 'budget' }">
                    <el-menu-item index="4">
                        <el-icon>
                            <List />
                        </el-icon>
                        <span>项目预算</span>
                    </el-menu-item>
                </router-link>
                <!-- 成员管理 -->
                <router-link :to="{ name: 'member' }">
                    <el-menu-item index="5">
                        <el-icon>
                            <List />
                        </el-icon>
                        <span>成员管理</span>
                    </el-menu-item>
                </router-link>
                <!-- 个人中心 -->
                <router-link :to="{ name: 'setting' }">
                    <el-menu-item index="6">
                        <el-icon>
                            <Setting />
                        </el-icon>
                        <span>个人中心</span>
                    </el-menu-item>
                </router-link>
            </el-menu>
        </el-col>
    </el-row>
</template>
  
<script setup>
import { HomeFilled, Setting, List } from '@element-plus/icons-vue'
import { ref } from 'vue'
import router from '../router'
const menuIndex = {
    dashboard: '1',
    project: '2',
    plan: '3',
    budget: '4',
    member: '5',
    setting: '6'
}
const active = ref(menuIndex[router.currentRoute.value.name] || '0')
</script>
  
<style lang="scss" scoped>
.el-menu {
    border: 0 !important;
    .is-active {
        background: linear-gradient(90deg, #1493fa, #01c6fa) !important;
    }
    a {
        text-decoration: none;
        color: white;
    }
}
</style>