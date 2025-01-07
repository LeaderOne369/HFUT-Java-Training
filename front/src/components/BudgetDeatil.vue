<script setup>
import { ref,watch,defineProps } from 'vue'
import {Api} from "../api/index.js";

const data = ref({
  "budgetId": 6,
  "projectId": 13,
  "name": "1",
  "description": "<p>111</p>",
  "amount": 1,
  "currency": "CNY",
  "status": "UNSETTLED",
  "settledDate": null,
  "createdAt": "2024-07-09 11:09:15",
  "updatedAt": "2024-07-09 11:09:15"
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
  Api.getBudgetById(props.id).then(res => {
    data.value = res
  })
}
</script>

<template>
  <div>
    <table>
      <tr>
        <td class="td-header">预算名称</td>
        <td>{{ data.name }}</td>
        <td class="td-header">状态</td>
        <td v-if="data.status === 'UNSETTLED'">未完成</td>
        <td v-else>已完成</td>
      </tr>
      <tr>
        <td class="td-header">金额</td>
        <td>{{ data.amount }}</td>
        <td class="td-header">币种</td>
        <td>{{ data.currency }}</td>
      </tr>
      <tr>
        <td class="td-header">创建时间</td>
        <td>{{ data.createdAt }}</td>
        <td class="td-header">结算日期</td>
        <td>{{ data.settledDate }}</td>
      </tr>
      <tr>
        <td class="td-header" colspan="4">描述</td>
      </tr>
      <tr>
        <td colspan="4">
          <div class="html" v-html="data.description"></div>
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