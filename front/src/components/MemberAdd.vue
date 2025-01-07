<script setup>
import {ref,watch} from "vue";
import {Api} from "../api/index.js";

const props = defineProps({
  projectId: {
    type: Number,
    required: true,
  },
})

const form = ref({
  userId: null,
})

const emit = defineEmits(['success'])

const addSubmit = async () => {
  Api.addMember( props.projectId, form.value.userId).then(() => {
    emit('success')
  })
}
</script>

<template>
  <el-form label-position="top">
    <el-form-item label="用户ID">
      <el-input v-model="form.userId" placeholder="请输入用户ID"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="addSubmit">确定</el-button>
    </el-form-item>
  </el-form>
</template>

<style scoped lang="scss">

</style>