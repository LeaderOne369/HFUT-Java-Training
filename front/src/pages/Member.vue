<template>
  <div>
    <el-form inline>
      <el-form-item label="所属项目">
        <el-select v-model="currentProjectId">
          <el-option v-for="item in projectList" :key="item.projectId" :label="item.projectName"
                     :value="item.projectId"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="success" @click="addMemberDialogVisible = true">添加成员</el-button>
        <el-button type="primary" @click="quitProject">退出项目</el-button>
      </el-form-item>
    </el-form>

    <!-- 成员管理 -->
    <el-table
        ref="tableRef"
        :data="memberList"
        style="margin-bottom: 20px"
        row-key="id"
        border
        default-expand-all
        :header-cell-style="{ 'text-align': 'center' }"
        :cell-style="{ 'text-align': 'center' }"
    >
      <el-table-column prop="user.fullName" label="成员全名"/>
      <el-table-column prop="user.username" label="用户名"/>
      <el-table-column prop="user.phoneNumber" label="电话"/>
      <el-table-column prop="projectMember.joinedAt" label="加入时间">
        <template #default="{ row }">
          {{ timestampFormat(row.projectMember.joinedAt) }}
        </template>
      </el-table-column>
      <el-table-column prop="projectMember.role" label="角色">
        <template #default="{ row }">
          <el-tag type="success" v-if="row.projectMember.role === 'MANAGER'">项目经理</el-tag>
          <el-tag type="info" v-else>项目成员</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="projectMember.permissions" label="访问权限">
        <template #default="{ row }">
          <div class="tag-group">
            <el-tag v-if="row.projectMember.permissions.add">添加</el-tag>
            <el-tag v-if="row.projectMember.permissions.delete">删除</el-tag>
            <el-tag v-if="row.projectMember.permissions.view">访问</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="250">
        <template #default="{ row }">
          <el-button :disabled="row.projectMember.role === 'MANAGER'" type="danger" @click="delRow(row)">移除
          </el-button>
          <el-button :disabled="row.projectMember.role === 'MANAGER'" type="primary" @click="editPermissions(row)">
            编辑权限
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog
        v-if="editPermissionsDialogVisible"
        v-model="editPermissionsDialogVisible"
        width="30%"
        :before-close="() => {
          editPermissionsDialogVisible = false
             permissionsForm = null
        }"
        title="权限编辑">
      <MemberEdit ref="memberForm" @success="editPermissionsSuccess" :user-id="currentUserId"
                  :project-id="currentProjectId"
                  :permissions="permissionsForm"/>
    </el-dialog>

    <el-dialog
        v-if="addMemberDialogVisible"
        v-model="addMemberDialogVisible"
        width="30%"
        title="添加成员">
      <MemberAdd ref="memberForm" :project-id="currentProjectId"
                 @success="() => {addMemberDialogVisible = false; getProjectMemberList()}"/>
    </el-dialog>
  </div>
</template>

<script setup>
import {onMounted, ref, watch} from 'vue'
import {ElMessage, ElMessageBox} from 'element-plus'
import MemberEdit from '../components/MemberEdit.vue'
import {Api} from "../api/index.js";
import MemberAdd from "../components/MemberAdd.vue";
import {timestampFormat} from "../utils/time.js";
import useAdmin from "../stores/admin.js";

const searchVal = ref('')
const editPermissionsDialogVisible = ref(false)
const addMemberDialogVisible = ref(false)
const id = ref(0)
const permissionsForm = ref()

const projectList = ref([])
const currentUserId = ref(null)
const currentProjectId = ref(null)

const memberList = ref([])

onMounted(() => {
  getProjectList()
})

watch(() => currentProjectId.value, () => {
  getProjectMemberList()
})

function getProjectList() {
  Api.getProjectList(1, 1000, 'TIME').then((res) => {
    projectList.value = res.records
    currentProjectId.value = res.records[0].projectId
  })
}

function getProjectMemberList() {
  Api.getProjectMemberList(currentProjectId.value).then((res) => {
    memberList.value = res
  })
}

// 删除
const delRow = (row) => {
  ElMessageBox.confirm('确定要移除' + (row.user.fullName || row.user.username) + "吗?", {
    closeOnClickModal: false,
    confirmButtonText: '确定',
    cancelButtonText: '取消'
  })
      .then(async () => {
        Api.deleteProjectMember(row.projectMember.memberId).then(() => {
          ElMessage({
            type: 'success',
            message: '删除成功'
          })
        })
      })
      .catch(() => {
      })
}

function editPermissions(row) {
  currentUserId.value = row.user.userId
  permissionsForm.value = row.projectMember.permissions
  editPermissionsDialogVisible.value = true
}

function editPermissionsSuccess() {
  editPermissionsDialogVisible.value = false
  permissionsForm.value = null
  getProjectMemberList()
}

function quitProject() {
  ElMessageBox.confirm('确定要退出项目吗?', {
    closeOnClickModal: false,
    confirmButtonText: '确定',
    cancelButtonText: '取消'
  })
      .then(async () => {
        Api.quitProject(currentProjectId.value).then(() => {
          ElMessage({
            type: 'success',
            message: '退出成功'
          })
          getProjectMemberList()
        })
      })
      .catch(() => {
          }
      )
}
</script>
<style lang="scss" scoped>
.tag-group {
  justify-content: center;
  display: flex;
  flex-wrap: wrap;
  gap: 15px;
}
</style>
