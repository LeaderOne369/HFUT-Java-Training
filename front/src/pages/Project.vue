<template>
  <div>
    <el-row>
      <el-form inline>
        <el-form-item label="模糊搜索">
          <el-input
              style="width: 250px"
              v-model="searchVal"
              placeholder="项目名称/项目分类"
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
          <el-button type="success" @click.stop="getProjectList">
            查询
          </el-button>
          <el-button
              type="primary"
              @click="addRow"
          >新增项目
          </el-button>
        </el-form-item>
      </el-form>
    </el-row>

    <!-- 新增项目的弹出框 -->
    <el-dialog
        v-model="editDialogVisible"
        width="90%"
        :title="id ? '修改项目' : '新增项目'"
        :before-close="handleBeforeClose"
    >
      <CategoryEdit ref="categoryForm" :id="id" @success="editSuccess"/>
    </el-dialog>

    <el-tabs v-model="tab">
      <el-tab-pane
          v-for="item in tabList"
          :key="item.value"
          :label="item.label"
          :name="item.value"
      >
      </el-tab-pane>
    </el-tabs>

    <!-- 分类管理 -->
    <el-table
        @sort-change="sortChange"
        ref="tableRef"
        :data="pageRes.records"
        style="margin-bottom: 20px"
        row-key="projectId"
        border
        :header-cell-style="{ 'text-align': 'center' }"
        :cell-style="{ 'text-align': 'center' }"
    >
      <el-table-column prop="projectName" label="项目名称"/>
      <el-table-column prop="totalBudget" label="总预算(人民币)"/>
      <el-table-column prop="importanceLevel" label="重要程度"/>
      <el-table-column
          prop="progress"
          label="项目进度">
        <template #default="{ row }">
          <el-progress
              :text-inside="true"
              :stroke-width="18"
              :percentage=" Math.floor(row.projectProgress)"
          />
        </template>
      </el-table-column>
      <el-table-column label="分类" width="200">
        <template #default="{ row }">
          <span>{{ row.projectCategoryName }} / {{ row.projectSubcategoryName }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="projectCreationTime" label="创建时间">
        <template #default="{ row }">
          <span>{{ formatDate(row.projectCreationTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态">
        <template #default="{ row }">
          <el-tag
              v-if="row.projectStatus === '在途'"
              type="info"
          >进行中
          </el-tag>
          <el-tag
              v-else
              type="success"
          >已结项
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="350">
        <template #default="{ row }">
          <el-button type="info" @click="viewDialogVisible = true; id = row.projectId">详情</el-button>
          <el-button type="warning" @click="editRow(row)"
          >编辑
          </el-button
          >
          <el-button
              v-if="row.projectStatus !== '已结项'"
              type="primary"
              @click="complete(row)"
          >结项
          </el-button
          >
          <el-button type="danger" @click="delRow(row)"
          >删除
          </el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-pagination :total="pageRes.total" :page-count="pageRes.pages" :current-page="pageReq.page"
                   @current-change="val => {pageReq.page = val; getProjectList()}"/>

    <el-dialog
        v-model="viewDialogVisible"
        width="90%"
        title="项目详情"
    >
      <ProjectDetail :id="id"/>
    </el-dialog>

  </div>
</template>

<script setup>
import {onMounted, ref,watch} from 'vue'
import {dayjs, ElMessage, ElMessageBox} from 'element-plus'
import {Search} from '@element-plus/icons-vue'
import CategoryEdit from '../components/ProjectEdit.vue'
import {Api} from "../api/index.js";
import notification from "../utils/notification.js";
import ProjectDetail from "../components/ProjectDetail.vue";

const searchVal = ref('')
const editDialogVisible = ref(false)
const viewDialogVisible = ref(false)

const id = ref(0)
const categoryForm = ref()
const pageReq = ref({
  page: 1,
  size: 10,
  condition: 'TIME'
})
const pageRes = ref({
  current: 1,
  pages: 1,
  records: [],
  size: 10,
  total: 1
})

const formatDate = (t) => {
  return dayjs(t).format('YYYY-MM-DD')
}

const tab = ref('在途')
const tabList = ref([
  {
    label: '在途',
    value: '在途'
  },
  {
    label: '已结项',
    value: '已结项'
  }
])

const sortMethods = ref([
  {
    label: '时间',
    value: 'TIME'
  },
  {
    label: '名称',
    value: 'NAME'
  },
  {
    label: '进度',
    value: 'PROGRESS'
  },
  {
    label: '预算',
    value: 'Budget'
  }
])

watch(() => tab.value, () => {
  getProjectList()
})

watch(() => pageReq.value.condition, (val) => {
  getProjectList()
})

onMounted(() => {
  getProjectList()
})

function getProjectList() {
  Api.searchProject(pageReq.value.page, pageReq.value.size, searchVal.value, pageReq.value.condition, tab.value).then(res => {
    pageRes.value = res
  })
}

function sortChange(e) {
  console.log(e)
  const property = e.prop
  switch (property) {
    case 'projectCreationTime':
      pageReq.value.condition = 'TIME'
      break
    case 'projectName':
      console.log('projectName')
      pageReq.value.condition = 'NAME'
      break
    case 'progress':
      pageReq.value.condition = 'PROGRESS'
      break
    case 'totalBudget':
      pageReq.value.condition = 'Budget'
      break
  }
  getProjectList()
}

// 新增项目
const addRow = () => {
  if (categoryForm.value) {
    categoryForm.value.resetForm()
  }
  id.value = null
  editDialogVisible.value = true
}

// 结项
const complete = (row) => {
  ElMessageBox.confirm('确定结项 ' + row.projectName + ' 吗?', '告警提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
    draggable: true
  })
      .then(() => {
        Api.completeProject(row.projectId).then(() => {
          getProjectList()
        })
        ElMessage({
          type: 'success',
          message: '结项完成'
        })
      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: '结项取消'
        })
      })
}

// 修改项目
const editRow = (row) => {
  if (categoryForm.value) {
    categoryForm.value.resetForm()
  }
  id.value = row.projectId
  editDialogVisible.value = true
}

// 删除分类
const delRow = (row) => {
  ElMessageBox.confirm('确定删除 ' + row.projectName + ' 吗?', '告警提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
    draggable: true
  })
      .then(() => {
        Api.deleteProject(row.projectId).then(() => {
          notification({
            message: '删除成功',
            type: 'success'
          })
          getProjectList()
        })
      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: '已取消删除'
        })
      })
}

const editSuccess = () => {
  getProjectList()
  editDialogVisible.value = false
  id.value = null
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
        editDialogVisible.value = false
        id.value = null
      })
      .catch(() => {
      })
}
</script>
