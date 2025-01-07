<script setup>
import {ref, watch, defineProps} from 'vue'
import {Api} from "../api/index.js";

const data = ref({
  "planId": 16,
  "projectId": 13,
  "name": "2",
  "description": "",
  "startDate": "2024-07-09",
  "dueDate": "2024-07-10",
  "priority": "MEDIUM",
  "status": "NOT_COMPLETED",
  "assignedTo": 240720,
  "predecessorPlanId": null,
  "completionDate": null,
  "createdAt": "2024-07-09 10:29:26",
  "updatedAt": "2024-07-09 10:29:26",
  "projectName": "1",
  "predecessorPlanName": null
})

const props = defineProps({
  id: {
    type: Number,
    required: true
  }
})

watch(() => props.id, (val) => {
  if (val) {
    getDetail()
  }
}, {
  immediate: true
})

function getDetail() {
  Api.getPlanById(props.id).then(res => {
    data.value = res
  })
}
</script>

<template>
  <div>
    <table>
      <tr>
        <td class="td-header">计划名称</td>
        <td>{{ data.name }}</td>
        <td class="td-header">项目名称</td>
        <td>{{ data.projectName }}</td>
      </tr>
      <tr>
        <td class="td-header">开始时间</td>
        <td>{{ data.startDate }}</td>
        <td class="td-header">结束时间</td>
        <td>{{ data.dueDate }}</td>
      </tr>
      <tr>
        <td class="td-header">优先级</td>
        <td v-if="data.priority === 'LOW'">低</td>
        <td v-else-if="data.priority === 'MEDIUM'">中</td>
        <td v-else-if="data.priority === 'HIGH'">高</td>
        <td class="td-header">状态</td>
        <td v-if="data.status === 'NOT_COMPLETED'">未完成</td>
        <td v-else>已完成</td>
      </tr>
      <tr>
        <td class="td-header">创建时间</td>
        <td>{{ data.createdAt }}</td>
        <td class="td-header">更新时间</td>
        <td>{{ data.updatedAt }}</td>
      </tr>
      <tr>
        <td class="td-header">完成时间</td>
        <td>{{ data.completionDate }}</td>
        <td class="td-header">前置计划名称</td>
        <td>{{ data.predecessorPlanName }}</td>
      
      </tr>
      <tr>
        <td class="td-header">负责人id</td>
        <td>{{data.assignedTo}}</td>
      </tr>
      <tr>
        <td colspan="4" class="td-header">计划描述</td>
      </tr>
      <tr>
        <td colspan="4">
          <div class="html" v-html="data.description">
          </div>
        </td>
      </tr>
    </table>
  </div>
</template>

<style scoped lang="scss">
table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;

  td {
    border: 1px solid #ccc;
    padding: 10px;
    text-align: center;
  }

  .td-header {
    font-weight: bold;
    background-color: #f9f9f9;
  }
}
.html {
  text-align: left;
  overflow-y: auto;
}
</style>