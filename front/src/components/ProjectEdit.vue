<template>
  <el-form ref="formRef" :model="form" label-width="120px" :rules="formRules">
    <!-- 项目名称  -->
    <el-form-item prop="projectName" label="项目名称" style="width: 95%" required>
      <el-input v-model="form.projectName" placeholder="请填写项目名称"/>
    </el-form-item>

    <div style="display: flex">
      <el-form-item prop="englishAbbreviation" label="英文简称" style="width: 23%">
        <el-input
            v-model="form.englishAbbreviation"
            placeholder="请填写项目英文简称"
        />
      </el-form-item>
      <el-form-item
          prop="projectCategory"
          label="项目大类"
          style="width: 23%"
          required
      >
        <el-select v-model="form.projectCategory" placeholder="请选择项目大类">
          <el-option
              v-for="item in categoryList"
              :key="item.categoryId"
              :label="item.categoryName"
              :value="item.categoryId"
          />
        </el-select>
      </el-form-item>
      <el-form-item
          prop="projectSubCategory"
          label="项目小类"
          style="width: 23%"
          required
      >
        <el-select v-model="form.projectSubCategory" placeholder="请选择项目小类">
          <el-option
              v-for="item in subCategoryList"
              :key="item.subcategoryId"
              :label="item.subcategoryName"
              :value="item.subcategoryId"
          />
        </el-select>
      </el-form-item>
      <el-form-item
          prop="importanceLevel"
          label="重要程度"
          style="width: 23%"
          required
      >
        <el-select v-model="form.importanceLevel" placeholder="请选择重要程度">
          <el-option
              v-for="item in priorityList"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
      </el-form-item>
    </div>

    <div style="display: flex">
      <el-form-item
          prop="projectStage"
          label="项目阶段"
          style="width: 23%"
          required
      >
        <el-select v-model="form.projectStage" placeholder="请选择项目阶段">
          <el-option
              v-for="item in projectStageList"
              :key="item.stageId"
              :label="item.stageName"
              :value="item.stageId"
          />
        </el-select>
      </el-form-item>
      <el-form-item
          prop="projectStartDate"
          label="启动日期"
          style="width: 23%"
          value-format="YYYY-MM-DD"
          required
      >
        <el-date-picker
            v-model="form.projectStartDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="选择日期"
        />
      </el-form-item>
      <el-form-item
          prop="plannedLaunchDate"
          label="计划上线日期"
          style="width: 23%"
          required
      >
        <el-date-picker
            v-model="form.plannedLaunchDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="选择日期"
            :disabled-date="(t) => {
            return t.getTime() < new Date(form.projectStartDate || null).getTime()
          }"
        />
      </el-form-item>
      <el-form-item
          prop="plannedInitialInspectionDate"
          label="计划初验日期"
          style="width: 23%"
          required
      >
        <el-date-picker
            v-model="form.plannedInitialInspectionDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="选择日期"
            :disabled-date="(t) => {
            return t.getTime() < new Date(form.projectStartDate || null).getTime()
          }"
        />
      </el-form-item>
    </div>

    <div style="display: flex">
      <el-form-item
          prop="plannedFinalInspectionDate"
          label="计划终验日期"
          value-format="YYYY-MM-DD"
          style="width: 23%"
          required
      >
        <el-date-picker
            v-model="form.plannedFinalInspectionDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="选择日期"
            :disabled-date="(t) => {
            return t.getTime() < new Date(form.plannedInitialInspectionDate || null).getTime()
          }"
        />
      </el-form-item>
      <el-form-item
          prop="plannedCompletionDate"
          label="计划完成日期"
          value-format="YYYY-MM-DD"
          style="width: 23%"
          required
          >
        <el-date-picker
            v-model="form.plannedCompletionDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="选择日期"
            :disabled-date="(t) => {
            return t.getTime() < new Date(form.plannedFinalInspectionDate || null).getTime()
          }"></el-date-picker>
      </el-form-item>
      <el-form-item prop="financialCode" label="财务编码" style="width: 23%">
        <el-input v-model="form.financialCode" placeholder="请填写财务编码"/>
      </el-form-item>
      <el-form-item
          prop="department"
          label="所属部门"
          style="width: 23%"
          required
      >
        <el-select v-model="form.department" placeholder="请选择所属部门">
          <el-option
              v-for="item in departmentList"
              :key="item.departmentId"
              :label="item.departmentName"
              :value="item.departmentId"
          />
        </el-select>
      </el-form-item>
    </div>

    <div style="display: flex">
      <el-form-item
          prop="region"
          label="所属区域"
          style="width: 23%"
          required
      >
        <el-select v-model="form.region" placeholder="请选择所属区域">
          <el-option
              v-for="item in regionList"
              :key="item.regionId"
              :label="item.regionName"
              :value="item.regionId"
          />
        </el-select>
      </el-form-item>
      <el-form-item
          prop="company"
          label="所属公司"
          style="width: 23%"
          required
      >
        <el-select v-model="form.company" placeholder="请选择所属公司">
          <el-option
              v-for="item in companyList"
              :key="item.companyId"
              :label="item.companyName"
              :value="item.companyId"
          />
        </el-select>
      </el-form-item>
    </div>

    <el-form-item>
      <el-button type="primary" @click="editSubmit" v-if="id"
      >修改
      </el-button
      >
      <el-button type="primary" @click="addSubmit" v-else>新增</el-button>
      <el-button @click="btnCancel">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>
import {onMounted, ref, watch} from 'vue'
import {Api} from "../api/index.js";
import notification from "../utils/notification.js";

const props = defineProps({
  id: {
    type: Number
  }
})

const categoryList = ref([])
const subCategoryList = ref([])
const projectStageList = ref([])
const departmentList = ref([])
const regionList = ref([])
const companyList = ref([])
const priorityList = ref([
  {
    label: "不重要",
    value: "不重要"
  },
  {
    label: "中等",
    value: "中等"
  },
  {
    label: "重要",
    value: "重要"
  }
])

const form = ref({
  id: props.id,
  projectName: '',
  englishAbbreviation: '',
  projectStage: null,
  plannedLaunchDate: null,
  financialCode: '',
  projectCategory: null,
  plannedInitialInspectionDate: null,
  department: null,
  projectSubCategory: null,
  plannedFinalInspectionDate: null,
  region: null,
  importanceLevel: '',
  projectStartDate: null,
  plannedCompletionDate: null,
  company: null,
})

const formRef = ref()

const formRules = ref({
  projectName: [
    {required: true, message: '请输入项目名称', trigger: 'blur'}
  ],
  englishAbbreviation: [
    {required: true, message: '请输入英文简称', trigger: 'blur'}
  ],
  projectCategory: [
    {required: true, message: '请选择项目大类', trigger: 'blur'}
  ],
  projectSubCategory: [
    {required: true, message: '请选择项目小类', trigger: 'blur'}
  ],
  projectStage: [
    {required: true, message: '请选择项目阶段', trigger: 'blur'}
  ],
  importanceLevel: [
    {required: true, message: '请输入重要程度', trigger: 'blur'}
  ],
  projectStartDate: [
    {required: true, message: '请选择启动日期', trigger: 'blur'}
  ],
  plannedLaunchDate: [
    {required: true, message: '请选择计划上线日期', trigger: 'blur'}
  ],
  plannedInitialInspectionDate: [
    {required: true, message: '请选择计划初验日期', trigger: 'blur'}
  ],
  plannedFinalInspectionDate: [
    {required: true, message: '请选择计划终验日期', trigger: 'blur'}
  ],
  plannedCompletionDate: [
    {required: true, message: '请选择计划结项日期', trigger: 'blur'}
  ],
  financialCode: [
    {required: true, message: '请输入财务编码', trigger: 'blur'}
  ],
  department: [
    {required: true, message: '请选择所属部门', trigger: 'blur'}
  ],
  region: [
    {required: true, message: '请选择所属区域', trigger: 'blur'}
  ],
  company: [
    {required: true, message: '请选择所属公司', trigger: 'blur'}
  ]
})


onMounted(() => {
  getCategoryList()
  //getSubCategoryList()
  getProjectStageList()
  getDepartmentList()
  getRegionList()
  getCompanyList()
  form.value.projectSubCategory=null
})

const getProjectDetail = () => {
  Api.getProjectById(props.id).then(res => {
    form.value = res
  })
}



const getCategoryList = () => {
  Api.getProjectCategory().then(res => {
    categoryList.value = res
  })
}

const getSubCategoryList = (id) => {
  Api.getProjectSubCategory(id).then(res => {
    subCategoryList.value = res
  })
}

const getProjectStageList = () => {
  Api.getAllProjectStage().then(res => {
    projectStageList.value = res
  })
}

const getDepartmentList = () => {
  Api.getDepartmentList().then(res => {
    departmentList.value = res
  })
}

const getRegionList = () => {
  Api.getRegionList().then(res => {
    regionList.value = res
  })
}

const getCompanyList = () => {
  Api.getCompanyList().then(res => {
    companyList.value = res
  })
}

const resetForm = (id) => {
  form.id = id
  btnCancel()
}

defineExpose({resetForm})
const emit = defineEmits(['success'])
// 新增操作
const addSubmit = async () => {
  Api.addProject(form.value).then(res => {
    formRef.value.resetFields()
    notification({
      message: '添加成功',
      type: 'success'
    })
    emit('success')
  })
}

// 修改操作
const editSubmit = async () => {
  Api.editProject(props.id, form.value).then(res => {
    formRef.value.resetFields()
    notification({
      message: '修改成功',
      type: 'success'
    })
    emit('success')
  })
}


// 重置表单
const btnCancel = () => {
  formRef.value.resetFields();
}

// 监听项目大类变化
watch(() => form.value.projectCategory, (newVal) => {
  if (newVal) {
    getSubCategoryList(newVal)
  }
})

watch(() => props.id, (newVal) => {
  if (newVal) {
    getProjectDetail()
    getCategoryList()
    getSubCategoryList(form.value.projectCategory)
    getProjectStageList()
    getDepartmentList()
    getRegionList()
    getCompanyList()
  }
  form.value.id = props.id
}, {
  immediate: true
})
</script>

<style scoped>
.upload-demo {
  text-align: left;
  width: 91%;
}
</style>
