<template>
  <el-form ref="formRef" :model="form">
    <el-form-item prop="add" label="添加权限">
      <el-switch v-model="form.add" :active-value="true" :inactive-value="false"/>
    </el-form-item>
    <el-form-item prop="delete" label="删除权限">
      <el-switch v-model="form.delete"/>
    </el-form-item>
    <el-form-item prop="view" label="查看权限">
      <el-switch v-model="form.view"/>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="editSubmit">确定</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>
import {onMounted, ref, watch} from 'vue'
import {Api} from "../api/index.js";

const props = defineProps({
  userId: {
    type: Number,
    required: true,
  },
  projectId: {
    type: Number,
    required: true,
  },
  permissions: {
    type: Object,
    required: true,
  },
})

const formRef = ref()
const form = ref({
  add: false,
  delete: false,
  view: false,
})

watch(() => props.permissions, (val) => {
  if (val) {
    form.value.add = val.add
    form.value.delete = val.delete
    form.value.view = val.view
  } else {
    form.value.add = false
    form.value.delete = false
    form.value.view = false
  }
}, {
  immediate: true,
})

const emit = defineEmits(['success'])

// 新增成员
const editSubmit = async () => {
  Api.editPermission(props.userId, props.projectId, form.value).then(() => {
    emit('success')
  })
}

onMounted(() => {
})
</script>

<style scoped>
.el-descriptions {
  margin-top: 20px;
}

.cell-item {
  display: flex;
  align-items: center;
}

.margin-top {
  margin-top: 20px;
}
</style>
