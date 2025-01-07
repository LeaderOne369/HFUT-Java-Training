<template>
  <el-form ref="formRef" :model="form" label-width="120px" :rules="formRules">
    <el-form-item label="所属项目" prop="projectId" style="width: 92%">
      <el-select v-model="form.projectId">
        <el-option v-for="item in projectList" :label="item.projectName" :value="item.projectId"/>
      </el-select>
    </el-form-item>

    <el-form-item label="预算名称" prop="name" style="width: 92%">
      <el-input v-model="form.name" placeholder="请填写预算名称"/>
    </el-form-item>

    <el-form-item label="预算额度" prop="amount" style="width: 92%">
      <el-input v-model="form.amount" placeholder="请填写预算额度"/>
    </el-form-item>

    <el-form-item label="预算币种" prop="currency" style="width: 92%">
      <el-select v-model="form.currency">
        <el-option v-for="item in currencyList" :label="item.label" :value="item.value"/>
      </el-select>
    </el-form-item>

    <el-form-item
        label="预算简介"
        prop="description"
        style="width: 92%"
        class="desc"
    >
      <Editor :init="initEditor" v-model="form.description"></Editor>
    </el-form-item>
    <!-- 操作按钮 -->
    <el-form-item>
      <el-button type="primary" @click="editSubmit()" v-if="form.budgetId"
      >修改
      </el-button
      >
      <el-button type="primary" @click="addSubmit()" v-else
      >新增
      </el-button
      >
      <el-button @click="btnCancel">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>
import {onMounted, watch, ref} from 'vue'
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

const emit = defineEmits(['success'])

const form = ref({
  budgetId: props.id,
  name: '',
  projectId: null,
  amount: 0.00,
  currency: '',
  description: ''
})

const currencyList = ref([
  {label: '人民币 1:1', value: 'CNY'},
  {label: '美元 1:7.2', value: 'USD'},
  {label: '欧元 1:7.8', value: 'EUR'},
  {label: '日元 1:0.05', value: 'JPY'},
  {label: '英镑 1:9.3', value: 'GBP'}
])

const formRef = ref()

const formRules = ref({
  name: [{required: true, message: '请输入预算名称', trigger: 'blur'}],
  projectId: [{required: true, message: '请选择所属项目', trigger: 'change'}],
  amount: [{required: true, message: '请输入预算额度', trigger: 'blur'},{
    validator: (rule, value, callback) => {
      if (value <= 0) {
        callback(new Error('预算额度必须大于0'))
      } else {
        callback()
      }
    },
    trigger: 'blur'
  }],
  currency: [{required: true, message: '请输入预算币种', trigger: 'blur'}],
  description: [{required: true, message: '请输入预算简介', trigger: 'blur'}]
})

const projectList = ref([])

onMounted(() => {
  getProjectList()
})

watch(() => props.id, (val) => {
  if (val) {
    Api.getBudgetById(val).then(res => {
      form.value = res
    })
  }
  getProjectList()
}, {
  immediate: true
})

function getProjectList() {
  Api.getProjectList(1, 1000, 'TIME').then(res => {
    projectList.value = res.records
    if (props.id) {
      return
    }
    form.value.projectId = res.records[0]?.projectId
  })
}

const resetForm = (id) => {
  form.budgetId = id
  btnCancel()
}

defineExpose({resetForm})

// 新增商品
const addSubmit = async () => {
  formRef.value.validate((valid) => {
    if (!valid) return;
    Api.addBudget(form.value).then(() => {
      notification({
        message: '新增成功',
        type: 'success'
      })
      emit('success')
    })
  })
}

// 修改商品
const editSubmit = async () => {
  formRef.value.validate((valid) => {
    if (!valid) return;
    Api.editBudget(form.value).then(() => {
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
  plugins: 'image'
}
</script>

<style scoped>
.upload-demo {
  text-align: left;
  width: 91%;
}
</style>
  