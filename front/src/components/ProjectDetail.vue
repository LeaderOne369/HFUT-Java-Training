<script setup>
import {defineProps, ref, watch} from 'vue'
import {Api} from "../api/index.js";

const props = defineProps({
  id: {
    type: Number,
    required: true
  }
})

const detail = ref({
  "id": 13,
  "projectName": "1",
  "englishAbbreviation": "1",
  "projectStage": 1,
  "plannedLaunchDate": "2024-07-11",
  "financialCode": "1",
  "projectCategory": 1,
  "plannedInitialInspectionDate": "2024-07-10",
  "department": 1,
  "projectSubCategory": 1,
  "plannedFinalInspectionDate": "2024-07-11",
  "region": 1,
  "importanceLevel": "不重要",
  "projectStartDate": "2024-07-09",
  "plannedCompletionDate": "2024-07-12",
  "company": 1,
  "status": "在途",
  "createdAt": "2024-07-09 00:00:00",
  "companyName": "科技有限公司",
  "departmentName": "技术部",
  "projectCategoryName": "智慧城市",
  "projectSubCategoryName": "智慧交通",
  "projectStageName": "立项阶段",
  "regionName": "华东地区"
})

watch(() => props.id, (val) => {
  if (val) {
    getDetail()
  }
}, {
  immediate: true
})

function getDetail() {
  Api.getProjectById(props.id).then(res => {
    detail.value = res
  })
}
</script>

<template>
  <div>
    <table>
      <tr>
        <td class="td-header">项目名称</td>
        <td>{{ detail.projectName }}</td>
        <td  class="td-header">英文缩写</td>
        <td>{{ detail.englishAbbreviation }}</td>
        <td  class="td-header">项目阶段</td>
        <td>{{ detail.projectStageName }}</td>
        <td  class="td-header">财务代码</td>
        <td>{{ detail.financialCode }}</td>
        <td  class="td-header">项目类别</td>
        <td>{{ detail.projectCategoryName }}</td>
      </tr>
      <tr>
        <td  class="td-header">项目子类别</td>
        <td>{{ detail.projectSubCategoryName }}</td>
        <td  class="td-header">计划启动日期</td>
        <td>{{ detail.plannedLaunchDate }}</td>
        <td  class="td-header">计划初验日期</td>
        <td>{{ detail.plannedInitialInspectionDate }}</td>
        <td  class="td-header">计划终验日期</td>
        <td>{{ detail.plannedFinalInspectionDate }}</td>
        <td  class="td-header">项目启动日期</td>
        <td>{{ detail.projectStartDate }}</td>
      </tr>
      <tr>
        <td  class="td-header">计划完成日期</td>
        <td>{{ detail.plannedCompletionDate }}</td>
        <td  class="td-header">公司</td>
        <td>{{ detail.companyName }}</td>
        <td  class="td-header">地区</td>
        <td>{{ detail.regionName }}</td>
        <td  class="td-header">重要程度</td>
        <td>{{ detail.importanceLevel }}</td>
        <td  class="td-header">部门</td>
        <td>{{ detail.departmentName }}</td>
      </tr>
      <tr>
        <td  class="td-header">状态</td>
        <td>{{ detail.status }}</td>
        <td  class="td-header">创建时间</td>
        <td>{{ detail.createdAt }}</td>
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
</style>