<script setup>
import {Api} from "../api/index.js";
import {ref, defineEmits} from "vue";

const form = ref({
  oldPassword: '',
  newPassword: '',
  confirmPassword: ''
})

const formRef = ref(null)

const rules = ref({
  oldPassword: [
    {required: true, message: '请输入原密码', trigger: 'blur'}
  ],
  newPassword: [
    {required: true, message: '请输入新密码', trigger: 'blur'}
  ],
  confirmPassword: [
    {required: true, message: '请输入确认密码', trigger: 'blur'},
    {
      validator: (rule, value, callback) => {
        if (value === form.value.newPassword) {
          callback()
        } else {
          callback(new Error('两次输入密码不一致'))
        }
      }, trigger: 'blur'
    }
  ]
})

const emit = defineEmits(['success'])

const changePassword = () => {
  formRef.value.validate((valid) => {
    if (valid) {
      Api.resetPassword(form.value.oldPassword, form.value.newPassword).then(() => {
        form.oldPassword = ''
        form.newPassword = ''
        form.confirmPassword = ''
        emit('success')
      })
    }
  })
}
</script>

<template>
  <div class="change-password">
    <el-form ref="formRef" :model="form" label-width="80px" :rules="rules" >
      <el-form-item label="原密码" prop="oldPassword">
        <el-input v-model="form.oldPassword" show-password></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="newPassword">
        <el-input v-model="form.newPassword" show-password></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="confirmPassword">
        <el-input v-model="form.confirmPassword" show-password></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="changePassword">修改密码</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<style scoped lang="scss">

</style>