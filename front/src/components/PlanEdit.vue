<template>
  <el-form ref="formRef" :model="form" label-width="120px" :rules="formRules">
    <!-- 计划名称  -->
    <el-form-item label="计划名称" prop="name" style="width: 92%">
      <el-input v-model="form.name" placeholder="请填写计划名称"/>
    </el-form-item>
    <!-- 项目名称 -->
    <el-form-item label="所属项目" prop="projectId">
      <el-select v-model="form.projectId" placeholder="请选择所属项目">
        <el-option
            v-for="item in projectList"
            :key="item.projectId"
            :label="item.projectName"
            :value="item.projectId"
        ></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="起始日期" prop="startDate">
      <el-date-picker
          v-model="form.startDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="选择日期"
          style="width: 92%"
      ></el-date-picker>
    </el-form-item>
    <el-form-item label="截止日期" prop="dueDate">
      <el-date-picker
          v-model="form.dueDate"
          type="date"
          value-format="YYYY-MM-DD"
          :disabled-date="(t) => {
            return t.getTime() < new Date(form.startDate || null).getTime()
          }"
          placeholder="选择日期"
          style="width: 92%"
      ></el-date-picker>
    </el-form-item>
    <el-form-item label="优先级" prop="priority">
      <el-select v-model="form.priority" placeholder="请选择优先级">
        <el-option
            v-for="item in priorityList"
            :key="item.value"
            :label="item.label"
            :value="item.value"
        ></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="前置计划" prop="predecessorPlanId">
      <el-select v-model="form.predecessorPlanId" placeholder="请选择前置计划">
        <el-option
            v-for="item in planList"
            :key="item.planId"
            :label="item.name"
            :value="item.planId"
        ></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="计划简介" prop="description" style="width: 92%" class="desc">
      <Editor :init="initEditor" v-model="form.description"></Editor>
    </el-form-item>
    <!-- 操作按钮 -->
    <el-form-item>
      <el-button type="primary" @click="editSubmit()" v-if="form.planId">修改</el-button>
      <el-button type="primary" @click="addSubmit()" v-else>新增</el-button>
      <el-button @click="btnCancel">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>
import {ref, watch, onMounted} from 'vue'
import Editor from '@tinymce/tinymce-vue'

import 'tinymce/tinymce'
import 'tinymce/models/dom'
import 'tinymce/themes/silver'
import 'tinymce/icons/default'
import 'tinymce/plugins/image'
import {Api} from "../api/index.js";
import notification from "../utils/notification.js";


const props = defineProps({
  id: {
    type: Number
  }
})

const projectList = ref([])
const planList = ref([])
const priorityList = ref([
  {
    label: "低",
    value: "LOW"
  },
  {
    label: "中",
    value: "MEDIUM"
  },
  {
    label: "高",
    value: "HIGH"
  }
])

const emit = defineEmits(['success'])

const form = ref({
  planId: props.id,
  projectId: null,
  name: '',
  description: '',
  startDate: null,
  dueDate: null,
  priority: 'MEDIUM',
  predecessorPlanId: null,
  // completionDate: null
})
const formRef = ref()

const formRules = ref({
  name: [
    {required: true, message: '请输入计划名称', trigger: 'blur'}
  ],
  projectId: [
    {required: true, message: '请选择所属项目', trigger: 'change'}
  ],
  startDate: [
    {required: true, message: '请选择起始日期', trigger: 'change'}
  ],
  dueDate: [
    {required: true, message: '请选择截止日期', trigger: 'change'}
  ],
  priority: [
    {required: true, message: '请选择优先级', trigger: 'change'}
  ],
  // completionDate: [
  //   {required: true, message: '请选择完成日期', trigger: 'change'}
  // ]
})

const resetForm = id => {
  form.planId = id
  btnCancel()
}

defineExpose({resetForm})

onMounted(() => {
  getProjectList()
})

const getPlan = () => {
  Api.getPlanById(props.id).then(res => {
    form.value = res
  })
}

watch(() => form.value.projectId, (val) => {
  if (val) {
    getPlanList()
  }
}, {
  immediate: true
})

watch(() => props.id, (val) => {
  if (val) {
    getPlan()
  }
  getProjectList()
  form.value.planId = props.id
}, {
  immediate: true
})



function getProjectList() {
  Api.getProjectList(1, 1000, 'TIME').then(res => {
    projectList.value = res.records
    if (props.id) {
      return
    }
    form.value.projectId = projectList.value[0].id
  })
}

function getPlanList() {
  Api.getPlansOfProject(form.value.projectId, 1, 1000, 'CREATION_TIME').then(res => {
    planList.value = res.records
  })
}

// 新增
const addSubmit = async () => {
  formRef.value.validate(async valid => {
    if (!valid) {
      return
    }
    Api.addPlan(form.value, form.value.projectId).then(() => {
      notification({
        message: '添加成功',
        type: 'success'
      })
      emit('success')
    })
  })
}

// 修改
const editSubmit = async () => {
  formRef.value.validate(async valid => {
    if (!valid) {
      return
    }
    Api.editPlan(form.value).then(() => {
      notification({
        message: '修改成功',
        type: 'success'
      })
      emit('success')
    })
  })
}

// 重置表单
const btnCancel = () => {
  formRef.value.resetFields()
}

// 编辑器配置
let initEditor = {
  width: '100%',
  skin_url: '/tinymce/skins/ui/oxide',
  content_css: '/tinymce/skins/content/default/content.css',
  language_url: '/tinymce/langs/zh-Hans.js',
  language: 'zh-Hans',
  menubar: false,
  statusbar: false,
  toolbar: 'bold underline italic strikethrough image undo redo',
  plugins: 'image',
}

</script>

<style scoped>
.upload-demo {
  text-align: left;
  width: 91%;
}
</style>
