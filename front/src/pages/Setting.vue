<template>
  <el-row :gutter="20">
<!--    头像-->
    <el-col :span="8">
      <el-card class="box-card">
        <template #header>头像</template>
        <div class="avatar-box">
          <el-avatar class="avatar-img" shape="circle" size="large" :src="config.baseURL + '/avatars/' + form.avatarUrl"></el-avatar>
          <el-upload
              class="upload-demo"
              :action="config.baseURL + '/userinfo/upload/avatar'"
              :show-file-list="false"
              type="image"
              accept="image/jpeg,image/jpg,image/png,image/gif"
              :on-success="handleAvatarSuccess"
              :auto-upload="true"
              :headers="{
                token: useToken().token
              }"
              :before-upload="beforeAvatarUpload">
            <template #trigger>
              <el-button size="small" type="primary">点击上传</el-button>
            </template>
          </el-upload>
        </div>
      </el-card>
    </el-col>
    <el-col :span="16">
      <el-card class="box-card">
        <template #header>个人信息</template>
        <div class="change-password-box">
          <el-form ref="formRef" status-icon :model="form" :rules="rules" label-width="140px">
            <el-form-item label="用户id" prop="userId">
              <tr>{{form.userId}}</tr>
            </el-form-item>
            <el-form-item label="用户名" prop="username">
              <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item label="手机号" prop="phoneNumber">
              <el-input v-model="form.phoneNumber" placeholder="请输入手机号"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="fullName">
              <el-input v-model="form.fullName" placeholder="请输入姓名"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="save">保存</el-button>
              <el-button type="default" @click="changePasswordDialog = true">修改密码</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-card>
    </el-col>
  </el-row>

  <el-dialog
      title="修改密码"
      v-model="changePasswordDialog"
      width="30%"
      center>
    <ChangePassword @success="changePasswordSuccess"></ChangePassword>
  </el-dialog>
</template>

<script setup>
import {onMounted, ref} from "vue"
import {Api} from "../api/index.js";
import notification from "../utils/notification.js";
import useToken from "../stores/token.js";
import config from "../config.js";
import router from "../router/index.js";
import ChangePassword from "../components/ChangePassword.vue";
import useAdmin from "../stores/admin.js";

const formRef = ref()
const form = ref({
  userId: null,
  avatarUrl: null,
  username: null,
  phoneNumber: null,
  fullName: null
})
const rules = ref({
  username: [
    {required: true, message: "请输入用户名", trigger: "blur"}
  ],
  phoneNumber: [
    {required: true, message: "请输入手机号", trigger: "blur"},
    {pattern: /^1[34578]\d{9}$/, message: "手机号格式错误", trigger: "blur"}
  ],
  fullName: [
    {required: true, message: "请输入姓名", trigger: "blur"}
  ]
})

const changePasswordDialog = ref(false)

onMounted(() => {
  getInfo()
})

async function getInfo() {
  Api.getUserInfo().then((res) => {
    form.value.userId = res.userId
    form.value.avatarUrl = res.avatarUrl
    form.value.username = res.username
    form.value.phoneNumber = res.phoneNumber
    form.value.fullName = res.fullName
    form.value.userId=res.userId
  })
}

function save() {
  formRef.value.validate((valid) => {
    if (valid) {
      const newForm = JSON.parse(JSON.stringify(form.value))
      newForm.userId = form.value.userId
      Api.editUserInfo(newForm).then(() => {
        getInfo()
        notification({
          message: "保存成功",
          type: "success"
        })
      })
    }
  })
}

function handleAvatarSuccess(res, file) {
 if (res.code === 200) {
    notification({
      message: "上传成功",
      type: "success"
    })
   getInfo().then(() => {
     useAdmin().updateAdmin(form.value)
   })
  } else {
    notification({
      message: "上传失败",
      type: "error"
    })
 }
}

function beforeAvatarUpload(file) {
  const isImage = file.type === 'image/jpeg' || file.type === 'image/png' || file.type === 'image/jpg' || file.type === 'image/gif'
  const isLt2M = file.size / 1024 / 1024 < 2

  if (!isImage) {
    notification({
      message: "上传头像图片只能是图片格式!",
      type: "error"
    })
  }
  if (!isLt2M) {
    notification({
      message: "上传头像图片大小不能超过 2MB!",
      type: "error"
    })
  }
  return isImage && isLt2M
}

function changePasswordSuccess() {
  changePasswordDialog.value = false
  notification({
    message: "修改成功",
    type: "success"
  })
  router.push("/login")
}
</script>

<style lang="scss" scoped>
.avatar-box {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  gap: 50px;
  padding: 20px;
}
.avatar-img {
  width: 80px;
  height: 80px;
}
.upload-demo {
  text-align: center;
}

.box-card {
  height: 316px;
}
</style>