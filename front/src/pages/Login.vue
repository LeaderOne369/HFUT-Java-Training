<template>
  <div class="container">
    <div class="site-title">项目管理系统</div>
    <div class="outside-box">
<!--      <div class="title" v-if="operation === 'login'">登录</div>-->
<!--      <div class="title" v-if="operation === 'register'">注册</div>-->
<!--      <div class="title" v-if="operation === 'forgetPassword'">忘记密码</div>-->
      <div v-if="operation === 'login'" class="box">
        <el-form :model="loginForm" ref="loginFormRef" :rules="loginFormRules" label-width="80" label-position="top">
          <el-form-item v-if="loginType === 'username/password'" label="用户名" prop="username">
            <el-input v-model="loginForm.username" placeholder="请输入用户名"></el-input>
          </el-form-item>
          <el-form-item v-if="loginType === 'phone/password' || loginType === 'phone/captcha'" label="手机号" prop="phoneNumber">
            <el-input v-model="loginForm.phoneNumber" placeholder="请输入手机号"></el-input>
          </el-form-item>
          <el-form-item v-if="loginType === 'username/password' || loginType === 'phone/password'" label="密码" prop="passwordHash">
            <el-input v-model="loginForm.passwordHash" placeholder="请输入密码" show-password></el-input>
          </el-form-item>
          <el-form-item v-if="loginType === 'phone/captcha'" label="验证码" prop="captcha">
            <el-input v-model="loginForm.captcha" placeholder="请输入验证码">
              <template v-slot:append>
                <el-button type="primary" @click="sendCaptcha">{{
                    captchaTimer ? `重新发送(${captchaSecond})` : '获取验证码'
                  }}</el-button>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item label="登录方式" prop="loginType">
            <el-select v-model="loginType" placeholder="请选择登录方式">
              <el-option v-for="item in loginTypeList" :key="item.value" :label="item.label"
                         :value="item.value"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </div>
      <div v-if="operation === 'register'" class="box">
        <el-form :model="registerForm" ref="registerFormRef" :rules="registerFormRules" label-width="80" label-position="top">
          <el-form-item label="姓名" prop="fullName">
            <el-input v-model="registerForm.fullName" placeholder="请输入姓名"></el-input>
          </el-form-item>
          <el-form-item label="用户名" prop="username">
            <el-input v-model="registerForm.username" placeholder="请输入用户名"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="passwordHash">
            <el-input v-model="registerForm.passwordHash" placeholder="请输入密码" show-password></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="rePassword">
            <el-input v-model="registerForm.rePassword" placeholder="请再次输入密码" show-password></el-input>
          </el-form-item>
          <el-form-item label="手机号" prop="phoneNumber">
            <el-input v-model="registerForm.phoneNumber" placeholder="请输入手机号"></el-input>
          </el-form-item>
          <el-form-item label="验证码" prop="captcha">
            <el-input v-model="registerForm.captcha" placeholder="请输入验证码">
              <template v-slot:append>
                <el-button type="primary" @click="sendCaptcha">{{
                    captchaTimer ? `重新发送(${captchaSecond})` : '获取验证码'
                  }}</el-button>
              </template>
            </el-input>
          </el-form-item>
        </el-form>
      </div>
      <div v-if="operation === 'forgetPassword'" class="box">
        <el-form :model="forgetPasswordForm" ref="forgetPasswordFormRef" :rules="forgetPasswordFormRules" label-width="80" label-position="top">
          <el-form-item label="手机号" prop="phoneNumber">
            <el-input v-model="forgetPasswordForm.phoneNumber" placeholder="请输入手机号"></el-input>
          </el-form-item>
          <el-form-item label="验证码" prop="captcha">
            <el-input v-model="forgetPasswordForm.captcha" placeholder="请输入验证码">
              <template v-slot:append>
                <el-button type="primary" @click="sendCaptcha">{{
                    captchaTimer ? `重新发送(${captchaSecond})` : '获取验证码'
                  }}</el-button>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item label="新密码" prop="password">
            <el-input v-model="forgetPasswordForm.password" placeholder="请输入新密码" show-password></el-input>
          </el-form-item>
        </el-form>
      </div>
      <el-form-item class="action">
        <div class="action">
          <el-button :type="operation === 'login' ? 'primary' : 'default'" @click="login">登录</el-button>
          <el-button :type="operation === 'register' ? 'primary' : 'default'" @click="register">注册</el-button>
          <el-button :type="operation === 'forgetPassword' ? 'primary' : 'default'" @click="forgetPassword">忘记密码</el-button>
        </div>
      </el-form-item>
    </div>
  </div>
</template>

<script setup>
import {ref,onMounted} from "vue"
import {Api} from "../api/index.js";
import useToken from "../stores/token.js";
import {useRouter} from "vue-router";
import notification from "../utils/notification.js";

const operation = ref('login')

const router = useRouter()

const loginType = ref('username/password')
const loginTypeList = ref([
  {label: '账号密码登录', value: 'username/password'},
  {label: '手机号密码登录', value: 'phone/password'},
  {label: '验证码登录', value: 'phone/captcha'}
])
const loginForm = ref({
  username: '',
  passwordHash: '',
  phoneNumber: '',
  captcha: ''
})

const loginFormRules = ref({
  username: [
    {required: true, message: '请输入用户名', trigger: 'blur'}
  ],
  passwordHash: [
    {required: true, message: '请输入密码', trigger: 'blur'}
  ],
  phoneNumber: [
    {required: true, message: '请输入手机号', trigger: 'blur'}
  ],
  captcha: [
    {required: true, message: '请输入验证码', trigger: 'blur'}
  ]
})

const registerForm = ref({
  fullName: '',
  username: '',
  phoneNumber: '',
  passwordHash: '',
  rePassword: '',
  captcha: ''
})

const registerFormRules = ref({
  fullName: [
    {required: true, message: '请输入姓名', trigger: 'blur'}
  ],
  username: [
    {required: true, message: '请输入用户名', trigger: 'blur'}
  ],
  phoneNumber: [
    {required: true, message: '请输入手机号', trigger: 'blur'}
  ],
  passwordHash: [
    {required: true, message: '请输入密码', trigger: 'blur'}
  ],
  rePassword: [
    {required: true, message: '请再次输入密码', trigger: 'blur'},
    {validator: (rule, value, callback) => {
      if (value !== registerForm.value.passwordHash) {
        callback(new Error('两次输入密码不一致'))
      } else {
        callback()
      }
    }, trigger: 'blur'}
  ],
  captcha: [
    {required: true, message: '请输入验证码', trigger: 'blur'}
  ]
})

const forgetPasswordForm = ref({
  phoneNumber: '',
  captcha: '',
  password: ''
})

const forgetPasswordFormRules = ref({
  phoneNumber: [
    {required: true, message: '请输入手机号', trigger: 'blur'}
  ],
  captcha: [
    {required: true, message: '请输入验证码', trigger: 'blur'}
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'}
  ]
})

const loginFormRef = ref(null)
const registerFormRef = ref(null)
const forgetPasswordFormRef = ref(null)

const { removeToken } = useToken()

onMounted(() => {
  removeToken();
})

const login = () => {
  if (operation.value !== 'login') {
    operation.value = 'login'
    return
  }
  loginFormRef.value.validate((valid) => {
    if (valid) {
      Api.login(loginForm.value, loginType.value, loginForm.value.captcha).then(res => {
        useToken().updateToken(res.token)
        router.push({path: '/'})
      })
    } else {
      return false
    }
  })
}

const register = () => {
  if (operation.value !== 'register') {
    operation.value = 'register'
    return
  }
  registerFormRef.value.validate((valid) => {
    if (valid) {
      Api.register(registerForm.value, registerForm.value.captcha).then(res => {
        operation.value = 'login'
      })
    } else {
      return false
    }
  })
}

const forgetPassword = () => {
  if (operation.value !== 'forgetPassword') {
    operation.value = 'forgetPassword'
    return
  }
  forgetPasswordFormRef.value.validate((valid) => {
    if (valid) {
      Api.forgetPasswordValidate(forgetPasswordForm.value.phoneNumber, forgetPasswordForm.value.captcha).then(res => {
       Api.forgetPassword(forgetPasswordForm.value.phoneNumber, forgetPasswordForm.value.password).then(res => {
         operation.value = 'login'
       })
      })
    } else {
      return false
    }
  })
}

const captchaTimer = ref(null)
const captchaSecond = ref(0)

const sendCaptcha = () => {
  if (captchaTimer.value) {
    return
  }
  let phone = ''
  switch (operation.value) {
    case 'login':
      phone = loginForm.value.phoneNumber
      break
    case 'register':
      phone = registerForm.value.phoneNumber
      break
    case 'forgetPassword':
      phone = forgetPasswordForm.value.phoneNumber
      break
  }
  if (!phone) {
    notification({
      message: '请输入手机号',
      type: 'warning'
    })
    return
  }
  Api.sendCaptcha(phone).then(res => {
    captchaSecond.value = 60;
    captchaTimer.value = setInterval(() => {
      captchaSecond.value--
      if (captchaSecond.value === 0) {
        clearInterval(captchaTimer.value)
        captchaTimer.value = null
      }
    }, 1000)
  })
}
</script>

<style lang="scss" scoped>
.container {
  background-image: url('/images/loginBg1.jpg');
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100%;
}
.site-title {
  font-size: 28px;
  color: #fff;
  font-weight: 500;
  margin-bottom: 30px;
}
.outside-box {
  background-color: #fff;
  justify-content: center;
  align-content: center;
  display: flex;
  flex-direction: column;
  box-sizing: border-box;
  width: 400px;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  border-radius: 15px;
}

.box {
  box-sizing: border-box;
}

.title {
  font-size: 24px;
  padding-bottom: 10px;
  margin-bottom: 20px;
  border-bottom: 5px solid var(--el-color-primary);
}

.action {
  width: 100%;
  display: flex;
  margin-top: 10px;

  .el-button {
    flex: 1;
  }
}
</style>