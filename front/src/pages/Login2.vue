<template>
  <div class="login-register">
    <div class="contain">
      <div class="big-box" :class="{ active: type === 'login' }">
        <div class="big-contain" v-if="type === 'login'">
          <div class="btitle">账户登录</div>
          <div class="bform">
            <el-form
                :model="loginForm"
                :rules="loginFormRules"
                ref="loginFormRef"
                label-width="100px"
            >
              <el-form-item
                  v-if="usernameLogin"
                  :label="'用户名'"
                  prop="username"
              >
                <el-input
                    v-model="loginForm.username"
                    :placeholder="'请输入用户名'"
                ></el-input
                >
              </el-form-item>

              <el-form-item v-if="!usernameLogin" label="手机号" prop="phoneNumber">
                <el-input
                    v-model="loginForm.phoneNumber"
                    placeholder="请输入手机号"
                    key="loginMobile"
                ></el-input>
              </el-form-item>

              <el-form-item
                  v-if="usernameLogin || phoneLoginType === 'password'"
                  :label="'密码'"
                  prop="passwordHash"
              >
                <el-input
                    v-model="loginForm.passwordHash"
                    type="password"
                    :placeholder="'请输入密码'"
                    key="loginPwd"
                ></el-input
                >
              </el-form-item>

              <el-form-item
                  v-if="!usernameLogin && phoneLoginType === 'captcha'"
                  :label="'验证码'"
                  prop="captcha"
              >
                <el-input
                    v-model="loginForm.captcha"
                    type="password"
                    :placeholder="'请输入验证码'"
                    key="loginPwd"
                ></el-input>
                <el-button
                    v-if="!usernameLogin"
                    style="display: inline-block; margin-left:10px"
                    type="info"
                    :icon="Message"
                    @click="sendCaptcha"
                    circle
                />
              </el-form-item>
              <el-form-item label="登录方式" v-if="!usernameLogin">
                <el-select v-model="phoneLoginType" placeholder="请选择登录方式">
                  <el-option
                      v-for="item in phoneLoginTypeList"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value"
                  />
                </el-select>
              </el-form-item>
            </el-form>
          </div>
          <el-row>
            <button class="bbutton" @click="login">登录</button>
            <button class="bbutton" @click="resetLoginForm">
              重置
            </button>
          </el-row>
          <el-row>
            <button class="bbutton" v-if="!usernameLogin" @click="usernameLogin = true">
              账号密码登陆
            </button>
            <button class="bbutton" v-else @click="usernameLogin = false">
              手机号登陆
            </button>
            <button class="bbutton" @click="toForget">
              忘记密码
            </button>
          </el-row>
        </div>

        <div class="big-contain" v-else-if="type === 'register'">
          <div class="btitle">创建账户</div>
          <div class="bform">
            <el-form
                :model="registerForm"
                :rules="registerFormRules"
                ref="registerFormRef"
                label-width="100px"
            >
              <el-form-item
                  label="姓名"
                  prop="fullName"
                  key="fullName"
              >
                <el-input
                    v-model="registerForm.fullName"
                    placeholder="请输入姓名"
                ></el-input>
              </el-form-item>
              <el-form-item
                  label="用户名"
                  prop="username"
                  key="regUsername"
              >
                <el-input
                    v-model="registerForm.username"
                    placeholder="请输入用户名"
                ></el-input>
              </el-form-item>
              <el-form-item
                  label="密码"
                  prop="passwordHash"
                  key="passwordHash"
              >
                <el-input
                    v-model="registerForm.passwordHash"
                    type="password"
                    placeholder="密码"
                ></el-input>
              </el-form-item>
              <el-form-item
                  label="确认密码"
                  prop="rePassword"
                  key="rePassword"
              >
                <el-input
                    v-model="registerForm.rePassword"
                    type="password"
                    placeholder="确认密码"
                ></el-input>
              </el-form-item>
              <el-form-item
                  label="手机号"
                  prop="phoneNumber"
                  key="phoneNumber"
              >
                <el-input
                    v-model="registerForm.phoneNumber"
                    placeholder="请输入手机号"
                ></el-input>
                <el-button
                    style="display: inline-block; margin-left:10px"
                    type="info"
                    :icon="Message"
                    @click="sendCaptcha"
                    circle/>
              </el-form-item>
              <el-form-item
                  label="验证码"
                  prop="captcha"
                  key="captcha"
              >
                <el-input
                    v-model="registerForm.captcha"
                    type="password"
                    placeholder="验证码"
                ></el-input>
              </el-form-item>
            </el-form>
          </div>
          <button class="bbutton" @click="register">注册</button>
          <button class="bbutton" @click="resetRegisterForm">
            重置
          </button>
        </div>

        <div class="big-contain" v-else-if="type === 'forget'">
          <div class="btitle">重置密码</div>
          <div class="bform">
            <el-form
                :model="forgetForm"
                :rules="forgetFormRules"
                ref="forgetFormRef"
                label-width="100px"
            >
              <el-form-item
                  label="手机号"
                  prop="phoneNumber"
                  key="phoneNumber"
              >
                <el-input
                    v-model="forgetForm.phoneNumber"
                ></el-input>
              </el-form-item>
              <el-form-item
                  label="密码"
                  prop="password"
                  key="password"
              >
                <el-input
                    v-model="forgetForm.password"
                    type="password"
                    placeholder="密码"
                ></el-input>
              </el-form-item>
              <el-form-item
                  label="确认密码"
                  prop="rePassword"
                  key="rePassword"
              >
                <el-input
                    v-model="forgetForm.rePassword"
                    type="password"
                    placeholder="确认密码"
                ></el-input>
              </el-form-item>
              <el-form-item
                  label="验证码"
                  prop="captcha"
                  key="captcha"
              >
                <el-input
                    v-model="forgetForm.captcha"
                    type="password"
                    placeholder="验证码"
                ></el-input>
                <el-button
                    style="display: inline-block; margin-left:10px"
                    type="info"
                    :icon="Message"
                    @click="sendCaptcha"
                    circle/>
              </el-form-item>
            </el-form>
          </div>
          <button class="bbutton" @click="forget">确定</button>
          <button class="bbutton" @click="resetForgetForm">
            重置
          </button>
        </div>

      </div>
      <div class="small-box" :class="{ active: type === 'login' }">
        <div class="small-contain" v-if="type === 'login'">
          <div class="stitle">还没有账号？</div>
          <p class="scontent">开始注册，和我们一起。</p>
          <button class="sbutton" @click="toRegister">
            立即注册
          </button>
        </div>
        <div class="small-contain" v-if="type === 'register'">
          <div class="stitle">欢迎回来!</div>
          <p class="scontent">与我们保持联系，请登录你的账户</p>
          <button class="sbutton" @click="toLogin">
            立即登录
          </button>
        </div>
        <div class="small-contain" v-if="type === 'forget'">
          <div class="stitle">忘记密码?</div>
          <p class="scontent">
            通过手机号找回密码，输入手机号获取验证码
          </p>
          <button class="sbutton" @click="toLogin">
            返回登录
          </button>
        </div>
      </div>
    </div>
  </div>
</template>


<script setup>
import {Message} from '@element-plus/icons-vue'
import {ref, onMounted} from 'vue'
import {ElMessage} from 'element-plus'
import {useRouter} from 'vue-router'
import useToken from '../stores/token'
import notification from '../utils/notification'
import {Api} from "../api/index.js";

const router = useRouter()
const {updateToken, removeToken} = useToken()

// 自定义校验规则校验手机号
var checkMobile = (rule, value, cb) => {
  // 验证手机号的正则表达式
  const regMobile =
      /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/

  if (regMobile.test(value)) {
    // 合法的邮箱
    return cb()
  }
  cb(new Error('请输入合法手机号'))
}

var checkMobileValue = (value) => {
  // 验证手机号的正则表达式
  const regMobile =
      /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/

  if (regMobile.test(value)) {
    // 合法的邮箱
    return true
  }
  return false
}

const usernameLogin = ref(true)
const phoneLoginType = ref('password')
const phoneLoginTypeList = ref([
  {label: '密码', value: 'password'},
  {label: '验证码', value: 'captcha'}
])

const type = ref('login')

const loginForm = ref({
  username: '',
  passwordHash: '',
  phoneNumber: '',
  captcha: ''
})
const registerForm = ref({
  fullName: '',
  username: '',
  phoneNumber: '',
  passwordHash: '',
  rePassword: '',
  captcha: ''
})
const forgetForm = ref({
  phoneNumber: '',
  password: '',
  rePassword: '',
  captcha: ''
})

// 这是表单的验证规则对象
const loginFormRules = ref({
  username: [
    {required: true, message: '请输入用户名', trigger: 'blur'}
  ],
  passwordHash: [
    {required: true, message: '请输入密码', trigger: 'blur'}
  ],
  phoneNumber: [
    {required: true, message: '请输入手机号', trigger: 'blur'},
    {validator: checkMobile, trigger: 'blur'}
  ],
  captcha: [
    {required: true, message: '请输入验证码', trigger: 'blur'}
  ]
})
// 注册表单的验证规则对象
const registerFormRules = ref({
  fullName: [
    {required: true, message: '请输入姓名', trigger: 'blur'}
  ],
  username: [
    {required: true, message: '请输入用户名', trigger: 'blur'}
  ],
  phoneNumber: [
    {required: true, message: '请输入手机号', trigger: 'blur'},
    {validator: checkMobile, trigger: 'blur'}
  ],
  passwordHash: [
    {required: true, message: '请输入密码', trigger: 'blur'}
  ],
  rePassword: [
    {required: true, message: '请再次输入密码', trigger: 'blur'},
    {
      validator: (rule, value, callback) => {
        if (value !== registerForm.value.passwordHash) {
          callback(new Error('两次输入密码不一致'))
        } else {
          callback()
        }
      }, trigger: 'blur'
    }
  ],
  captcha: [
    {required: true, message: '请输入验证码', trigger: 'blur'}
  ]
})

const loginFormRef = ref(null)
// 重置登录表单
const resetLoginForm = () => {
  loginFormRef.value.resetFields()
}
const registerFormRef = ref(null)
// 重置注册表单
const resetRegisterForm = () => {
  registerFormRef.value.resetFields()
}
const forgetFormRef = ref()
// 忘记密码表单的验证规则对象
const forgetFormRules = ref({
  phoneNumber: [
    {required: true, message: '请输入手机号', trigger: 'blur'},
    {validator: checkMobile, trigger: 'blur'}
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'}
  ],
  rePassword: [
    {required: true, message: '请再次输入密码', trigger: 'blur'},
    {
      validator: (rule, value, callback) => {
        if (value !== forgetForm.value.password) {
          callback(new Error('两次输入密码不一致'))
        } else {
          callback()
        }
      }, trigger: 'blur'
    }
  ],
  captcha: [
    {required: true, message: '请输入验证码', trigger: 'blur'}
  ]
})
// 重置忘记密码表单
const resetForgetForm = () => {
  forgetFormRef.value.resetFields()
}

onMounted(() => {
  removeToken()
})

function toRegister() {
  type.value = 'register'
  registerForm.value.fullName = ''
  registerForm.value.username = ''
  registerForm.value.passwordHash = ''
  registerForm.value.phoneNumber = ''
  registerForm.value.rePassword = ''
  registerForm.value.captcha = ''
}

function toLogin() {
  type.value = 'login'
  loginForm.value.phoneNumber = ''
  loginForm.value.password = ''
}

function toForget() {
  type.value = 'forget'
  forgetForm.value.phoneNumber = ''
  forgetForm.value.passwordHash = ''
  forgetForm.value.rePassword = ''
  forgetForm.value.captcha = ''
}

const login = () => {
  loginFormRef.value.validate(async (valid) => {
    if (!valid) return
    if (usernameLogin.value) {
      Api.login(loginForm.value, 'username/password', '').then((res) => {
        updateToken(res.token)
        router.push('/')
      })
    } else {
      if (phoneLoginType.value === 'password') {
        Api.login(loginForm.value, 'phone/password', '').then((res) => {
          updateToken(res.token)
          router.push('/')
        })
      } else {
        Api.login(loginForm.value, 'phone/captcha', loginForm.value.captcha).then((res) => {
          updateToken(res.token)
          router.push('/')
        })
      }
    }
  })
}
const register = () => {
  registerFormRef.value.validate(async (valid) => {
    if (valid) {
      Api.register(registerForm.value, registerForm.value.captcha).then(() => {
        ElMessage.success('注册成功')
        toLogin()
      })
    }
  })
}

const forget = () => {
  forgetFormRef.value.validate(async (valid) => {
    if (valid) {
      Api.forgetPasswordValidate(forgetForm.value.phoneNumber, forgetForm.value.captcha).then(() => {
        Api.forgetPassword(forgetForm.value.phoneNumber, forgetForm.value.password).then(() => {
          ElMessage.success('修改成功')
          toLogin()
        })
      })
    }
  })
}

const captchaTimer = ref(null)
const captchaTime = ref(60)

function sendCaptcha() {
  let phone = ''
  switch (type.value) {
    case 'login':
      phone = loginForm.value.phoneNumber
      break
    case 'register':
      phone = registerForm.value.phoneNumber
      break
    case 'forget':
      phone = forgetForm.value.phoneNumber
      break
  }
  if (!checkMobileValue(phone)) {
    notification({
      message: '请输入正确的手机号',
      type: 'error'
    })
    return
  }
  if (captchaTimer.value) {
    notification({
      message: `请${captchaTime.value}秒后再试`,
      type: 'error'
    })
    return
  }
  Api.sendCaptcha(phone).then(() => {
    notification({
      message: '验证码发送成功',
      type: 'success'
    })
    captchaTimer.value = setInterval(() => {
      captchaTime.value--
      if (captchaTime.value <= 0) {
        clearInterval(captchaTimer.value)
        captchaTimer.value = null
        captchaTime.value = 60
      }
    }, 1000)
  })
}
</script>

<style scoped="scoped">
.el-input {
  width: 220px;
}

.login-register {
  width: 100vw;
  height: 100vh;
  box-sizing: border-box;
  background: linear-gradient(to bottom right, rgba(64, 158, 255, 0.73), rgb(218, 218, 218) 50%, rgb(255, 206, 221), rgba(83, 155, 255, 0.7) 50%);
}

.contain {
  width: 70%;
  height: 70%;
  position: relative;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;
  border-radius: 20px;
  //box-shadow: 0 0 3px #e5b3b3, 0 0 6px #eee;
}

.big-box {
  width: 70%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 30%;
  transform: translateX(0%);
  transition: all 1s;
}

.big-contain {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.btitle {
  font-size: 1.5em;
  font-weight: bold;
  color: rgb(57, 167, 176);
}

.bform {
  width: 100%;
  height: 40%;
  padding: 2em 0;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
}

.bform .errTips {
  display: block;
  width: 50%;
  text-align: left;
  color: red;
  font-size: 0.7em;
  margin-left: 1em;
}

.bform input {
  width: 50%;
  height: 30px;
  border: none;
  outline: none;
  border-radius: 10px;
  padding-left: 2em;
  background-color: #f0f0f0;
}

.bbutton {
  width: 150px;
  height: 40px;
  border-radius: 24px;
  border: none;
  outline: none;
  background-color: rgb(57, 167, 176);
  color: #fff;
  font-size: 0.9em;
  cursor: pointer;
  margin-top: 10px;
  margin-right: 10px;
}

.small-box {
  width: 30%;
  height: 100%;
  background: linear-gradient(135deg, rgb(57, 167, 176), rgb(56, 183, 145));
  position: absolute;
  top: 0;
  left: 0;
  transform: translateX(0%);
  transition: all 1s;
  border-top-left-radius: inherit;
  border-bottom-left-radius: inherit;
}

.small-contain {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.stitle {
  font-size: 1.5em;
  font-weight: bold;
  color: #fff;
}

.scontent {
  font-size: 0.8em;
  color: #fff;
  text-align: center;
  padding: 2em 4em;
  line-height: 1.7em;
}

.sbutton {
  width: 60%;
  height: 40px;
  border-radius: 24px;
  border: 1px solid #fff;
  outline: none;
  background-color: transparent;
  color: #fff;
  font-size: 0.9em;
  cursor: pointer;
}

.big-box.active {
  left: 0;
  transition: all 0.5s;
}

.small-box.active {
  left: 100%;
  border-top-left-radius: 0;
  border-bottom-left-radius: 0;
  border-top-right-radius: inherit;
  border-bottom-right-radius: inherit;
  transform: translateX(-100%);
  transition: all 1s;
}
</style>

