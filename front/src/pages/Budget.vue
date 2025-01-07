<template>
  <div>
    <el-form inline>
      <el-form-item label="所属项目">
        <el-select v-model="currentProjectId" placeholder="请选择项目">
          <el-option
              v-for="item in projectList"
              :key="item.projectId"
              :label="item.projectName"
              :value="item.projectId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="预算名称">
        <el-input
            v-model="searchVal"
            placeholder="请输入预算名称"
            :prefix-icon="Search"
            @change="getBudgetList"
        />
      </el-form-item>
      <el-form-item label="排序方式">
        <el-select v-model="pageReq.condition">
          <el-option
              v-for="item in sortMethods"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="success" @click="getBudgetList">查询</el-button>
        <el-button type="primary" @click="addRow">新增预算</el-button>
      </el-form-item>
    </el-form>

    <el-dialog
        v-model="editDialogVisible"
        :title="id ? '修改预算' : '新增预算'"
        :before-close="handleBeforeClose"
    >
      <BudgetEdit ref="budgetForm" :id="id" @success="editSuccess"/>
    </el-dialog>

    <el-tabs v-model="selectedStatus">
      <el-tab-pane
          v-for="item in statusList"
          :key="item.value"
          :label="item.label"
          :name="item.value"
      >
      </el-tab-pane>
    </el-tabs>

    <!-- 预算列表 -->
    <el-table
        :data="pageRes.records"
        style="width: 100%; margin-bottom: 20px"
        row-key="budgetId"
        border
        :header-cell-style="{ 'text-align': 'center' }"
        :cell-style="{ 'text-align': 'center' }"
    >
      <el-table-column prop="name" label="预算名称" align="center"/>
      <el-table-column prop="currency" label="预算额度" align="center">
        <template #default="{ row }">
          <span v-if="row.currency === 'CNY'">￥(CNY)</span>
          <span v-if="row.currency === 'USD'">$</span>
          <span v-if="row.currency === 'EUR'">€</span> 
          <span v-if="row.currency === 'JPY'">¥(JPY)</span>
          <span v-if="row.currency === 'GBP'">£</span>
          <span>{{ row.amount }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="createdAt" label="创建时间" align="center">
        <template #default="{row}">
          {{ timestampFormat(row.createdAt) }}
        </template>
      </el-table-column>
      <el-table-column prop="updatedAt" label="更新时间" align="center">
        <template #default="{row}">
          {{ timestampFormat(row.updatedAt) }}
        </template>
      </el-table-column>
      <el-table-column prop="status" label="状态" align="center">
        <template #default="{ row }">
          <el-tag v-if="row.status === 'SETTLED'" type="success">已完成</el-tag>
          <el-tag v-else type="info">未完成</el-tag>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" align="center" width="350">
        <template #default="{ row }">
          <el-button type="info" @click="viewDialogVisible=true;id=row.budgetId">查看</el-button>
          <el-button type="warning" @click="editRow(row)"
          >编辑
          </el-button
          >
          <el-button type="success" @click="done(row)" v-if="row.status !== 'SETTLED'">完成</el-button>
          <el-button type="danger" @click="delRow(row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination :total="pageRes.total" :page-count="pageRes.pages" :current-page="pageReq.page"
                   @current-change="val => {pageReq.page = val; getBudgetList()}"/>

    <el-dialog
        v-model="viewDialogVisible"
        title="查看预算"
    >
      <BudgetDeatil :id="id"/>
    </el-dialog>
  </div>
</template>

<script setup>
import {onMounted, ref, watch} from 'vue'
import {Search} from '@element-plus/icons-vue'
import BudgetEdit from '../components/BudgetEdit.vue'
import {ElMessage, ElMessageBox} from 'element-plus'
import {Api} from "../api/index.js";
import {timestampFormat} from "../utils/time.js";
import BudgetDeatil from "../components/BudgetDeatil.vue";

const id = ref()
const searchVal = ref('')
const editDialogVisible = ref(false)
const viewDialogVisible = ref(false)
const budgetForm = ref()
const currentProjectId = ref();
const projectList = ref([])

const pageReq = ref({
  page: 1,
  pageSize: 10,
  condition: 'CREATION_TIME',
})

const pageRes = ref({
  current: 1,
  pages: 1,
  records: [],
  size: 10,
  total: 1
})

onMounted(() => {
  getProjectList()
})

const statusList = [
  {label: '未完成', value: 'UNSETTLED'},
  {label: '已完成', value: 'SETTLED'}
]
const selectedStatus = ref('UNSETTLED')

const sortMethods = [
  {label: '创建时间', value: 'CREATION_TIME'},
  {label: '金额（换算为人民币）', value: 'AMOUNT'},
  {label: '名称', value: 'NAME'}
]

watch(() => currentProjectId.value, (val) => {
  if (val) {
    pageReq.value.page = 1
    getBudgetList()
  }
}, {
  immediate: true
})

watch(selectedStatus, () => {
  getBudgetList()
})

watch(() => pageReq.value.condition, () => {
  getBudgetList()
})

function getProjectList() {
  Api.getProjectList(1, 1000, 'TIME').then(res => {
    projectList.value = res.records
    if (res.records.length > 0) {
      currentProjectId.value = res.records[0].projectId
    }
  })
}

function getBudgetList() {
  Api.searchBudget(pageReq.value.page, pageReq.value.pageSize, currentProjectId.value, searchVal.value, pageReq.value.condition, selectedStatus.value).then(res => {
    pageRes.value = res
  })
}

// 结项
const done = (row) => {
  ElMessageBox.confirm('确定完成 ' + row.name + ' 吗?', '告警提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
    draggable: true
  })
      .then(() => {
        Api.settleBudget(row.budgetId).then(() => {
          ElMessage({
            type: 'success',
            message: '完成成功'
          })
          getBudgetList()
        })
      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: '完成取消'
        })
      })
}

// 去掉标签，仅显示文字
const removeTags = (str) => {
  return str.replace(/<[^>]+>/g, '')
}

// 新增预算
const addRow = () => {
  if (budgetForm.value) {
    budgetForm.value.resetForm(0)
  }
  id.value = null
  editDialogVisible.value = true
}

// 修改预算
const editRow = (row) => {
  if (budgetForm.value) {
    budgetForm.value.resetForm(row.id)
  }
  id.value = row.budgetId
  editDialogVisible.value = true
}

// 删除预算
const delRow = (row) => {
  ElMessageBox.confirm('确定要删除此预算吗？', {
    closeOnClickModal: false,
    confirmButtonText: '确定',
    cancelButtonText: '取消'
  })
      .then(async () => {
        Api.deleteBudget(row.budgetId).then(() => {
          ElMessage({
            type: 'success',
            message: '删除成功'
          })
          getBudgetList()
        })
      })
      .catch(() => {
      })
}

// 编辑完成
const editSuccess = () => {
  id.value = null
  editDialogVisible.value = false
  getBudgetList()
}

// 关闭弹出框前
const handleBeforeClose = () => {
  ElMessageBox.confirm('确定关闭对话框吗？', {
    showClose: false,
    closeOnClickModal: false,
    confirmButtonText: '确定',
    cancelButtonText: '取消'
  })
      .then(() => {
        id.value = null
        editDialogVisible.value = false
        setTimeout(() => {
          budgetForm.value.resetForm()
        }, 500)
      })
      .catch(() => {
      })
}
</script>
