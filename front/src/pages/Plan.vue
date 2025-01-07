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
      <el-form-item label="计划名称">
        <el-input
            v-model="searchVal"
            placeholder="请输入计划名称"
            :prefix-icon="Search"
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
        <el-button type="success" @click="getPlanList">搜索</el-button>
        <el-button type="primary" @click="addRow">新增计划</el-button>
      </el-form-item>
    </el-form>
    <!-- 新增计划的弹出框 -->
    <el-dialog
        v-model="editDialogVisible"
        :title="id ? '修改计划' : '新增计划'"
        :before-close="handleBeforeClose"
    >
      <PlanEdit ref="planForm" :id="id" @success="editSuccess"/>
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

    <!-- 计划列表 -->
    <el-table
        :data="pageRes.records"
        style="width: 100%; margin-bottom: 20px"
        row-key="planId"
        border
        :header-cell-style="{ 'text-align': 'center' }"
        :cell-style="{ 'text-align': 'center' }"
    >
      <el-table-column prop="name" label="计划名称" align="center"/>
      <el-table-column prop="priority" label="优先级" align="center" >
        <template #default="{ row }">
          <el-tag v-if="row.priority === 'HIGH'" type="danger">高</el-tag>
          <el-tag v-else-if="row.priority === 'MEDIUM'" type="warning">中</el-tag>
          <el-tag v-else type="success">低</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="description" label="描述" align="center" show-overflow-tooltip>
        <template #default="{ row }">
          {{ removeTages(row.description) }}
        </template>
      </el-table-column>
      <el-table-column label="状态">
        <template #default="{ row }">
          <el-tag v-if="row.status === 'NOT_COMPLETED'" type="info">未完成</el-tag>
          <el-tag v-else type="success">已完成</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="开始日期" prop="startDate"></el-table-column>
      <el-table-column label="截止日期" prop="dueDate">
        <template #default="{row}">
          <span v-if="row.status === 'COMPLETED'">{{row.dueDate}}</span>
          <span v-else>
            <span style="color: red" v-if="calcDays(row.dueDate, new Date()) < 0">逾期  {{ calcDays(row.dueDate, new Date()) < 0 ? -calcDays(row.dueDate, new Date()) : calcDays(row.dueDate, new Date()) }}天</span>
            <span v-else>剩余  {{ calcDays(row.dueDate, new Date()) < 0 ? -calcDays(row.dueDate, new Date()) : calcDays(row.dueDate, new Date()) }}天</span>
          </span>
        </template>
      </el-table-column>
      <el-table-column v-if="selectedStatus === 'COMPLETED'" label="完成日期" prop="completionDate"></el-table-column>
      <el-table-column
          prop="createdAt"
          label="创建时间"
          align="center"
      >
        <template #default="{ row }">
          {{ timestampFormat(row.createdAt) }}
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" align="center" width="350">
        <template #default="{ row }">
          <el-button type="info" @click="viewDialogVisible = true; id = row.planId">详情</el-button>
          <el-button type="warning" @click="editRow(row)">编辑</el-button>
          <el-button type="success" @click="complete(row)" v-if="row.status !== 'COMPLETED'">完成</el-button>
          <el-button type="danger" @click="delRow(row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination :total="pageRes.total" :page-count="pageRes.pages" :current-page="pageReq.page"
                   @current-change="handlePageChange"/>

    <el-dialog
        v-model="viewDialogVisible"
        title="计划详情"
        width="50%"
    >
      <PlanDetail :id="id"/>
    </el-dialog>
  </div>
</template>

<script setup>
import {onMounted, ref, watch} from 'vue'
import {Search} from '@element-plus/icons-vue'
import PlanEdit from '../components/PlanEdit.vue'
import {dayjs, ElMessageBox} from 'element-plus'
import {Api} from "../api/index.js";
import notification from "../utils/notification.js";
import {calcDays, timestampFormat} from "../utils/time.js";
import PlanDetail from "../components/PlanDetail.vue";

const id = ref()
const searchVal = ref('')
const editDialogVisible = ref(false)
const viewDialogVisible = ref(false)


const projectList = ref([])

const currentProjectId = ref()

const pageReq = ref({
  page: 1,
  size: 10,
  condition: 'CREATION_TIME'
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

const planForm = ref()

const getProjectList = () => {
  Api.getProjectList(1, 1000, 'TIME').then(res => {
    projectList.value = res.records
    currentProjectId.value = projectList.value[0].projectId
  })
}

const statusList = [
  {label: '未完成', value: 'NOT_COMPLETED'},
  {label: '已完成', value: 'COMPLETED'}
]
const selectedStatus = ref('NOT_COMPLETED')

const sortMethods = [
  {label: '创建时间', value: 'CREATION_TIME'},
  {label: '优先级', value: 'PRIORITY'},
  {label: '名称', value: 'NAME'},
  {label: '拓扑排序', value: 'TOPO'}
]

const getPlanList = () => {
  if (pageReq.value.condition === 'TOPO') {
    Api.getPlansTopoList(currentProjectId.value, pageReq.value.page, pageReq.value.size, selectedStatus.value).then(res => {
      pageRes.value = res
    })
  } else {
    Api.searchPlan(pageReq.value.page, pageReq.value.size, currentProjectId.value, searchVal.value, pageReq.value.condition, selectedStatus.value).then(res => {
      pageRes.value = res
    })
  }
}

watch(
    currentProjectId
    , (val) => {
      if (val) {
        pageReq.value.page = 1
        pageReq.value.size = 10
        getPlanList()
      }
    }, {
      immediate: true
    })

watch(selectedStatus, () => {
  getPlanList()
})

watch(() => pageReq.value.condition, () => {
  getPlanList()
})

const formatDate = (t) => {
  return dayjs(t).format('YYYY-MM-DD')
}

// 去掉标签，仅显示文字
const removeTages = (str) => {
  return str?.replace(/<[^>]+>/g, '')
}

// 新增计划
const addRow = () => {
  if (planForm.value) {
    planForm.value.resetForm(0)
  }
  id.value = 0
  editDialogVisible.value = true
}

// 修改计划
const editRow = (row) => {
  if (planForm.value) {
    planForm.value.resetForm(row.planId)
  }
  id.value = row.planId
  editDialogVisible.value = true
}

// 删除计划
const delRow = (row) => {
  ElMessageBox.confirm('确定要删除此计划吗？', {
    closeOnClickModal: false,
    confirmButtonText: '确定',
    cancelButtonText: '取消'
  })
      .then(async () => {
        Api.deletePlan(row.planId).then(() => {
          notification({
            message: '删除成功',
            type: 'success'
          })
          getPlanList()
        })
      })
      .catch(() => {
      })
}

// 编辑完成
const editSuccess = () => {
  editDialogVisible.value = false
  id.value = null
  getPlanList()
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
          planForm.value.resetForm()
        }, 500)
      })
      .catch(() => {
      })
}

const handlePageChange = (val) => {
  pageReq.value.page = val
  getPlanList()
}

const complete = (row) => {
  ElMessageBox.confirm('确定完成 ' + row.name + ' 吗?', '告警提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
    draggable: true
  })
      .then(() => {
        Api.completePlan(row.planId).then(() => {
          notification({
            type: 'success',
            message: '计划完成'
          })
          getPlanList()
        })
      })
      .catch(() => {
        notification({
          type: 'info',
          message: '完成取消'
        })
      })
}
</script>
