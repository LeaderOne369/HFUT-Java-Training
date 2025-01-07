import request from '../utils/request'
import config from '../config'

export const Api = {
    checkalive() {
        return request.get('/checkalive')
    },

    getUserInfo() {
        return request.get('/userinfo/getinfo')
    },

    editUserInfo(form) {
        return request.post('/userinfo/edit', form)
    },

    // 登录接口
    login(form, type, captcha) {
        return request.post('/user/login?type=' + type + '&captcha=' + captcha, form)
    },

    register(form, captcha) {
        return request.post('/user/register?captcha=' + captcha, form)
    },

    // 发送验证码
    sendCaptcha(phone) {
        return request.get('/sendcaptcha?phone=' + phone)
    },

    // 忘记密码验证
    forgetPasswordValidate(phone, captcha) {
        return request.post('/user/forgetvalidate?phone=' + phone + '&captcha=' + captcha)
    },

    // 忘记密码
    forgetPassword(phoneNumber, password) {
        return request.post('/user/reset?phoneNumber=' + phoneNumber, password, {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
    },

    /**
     * 项目列表
     * @param pageSize 每页数量
     * @param pageNum 页码
     * @param condition TIME\NAME\PROGRESS\Budget
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    getProjectList(pageNum, pageSize, condition) {
        return request.get('/projectlist/getall?pageSize=' + pageSize + '&pageNum=' + pageNum + '&condition=' + condition)
    },

    /**
     * 添加项目
     * @param form 项目表单
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    addProject(form) {
        return request.post('/projectlist/addnewproject', form)
    },

    /**
     * 编辑项目
     * @param id 项目id
     * @param form 项目表单
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    editProject(id, form) {
        return request.put('/projectlist/edit?projectId=' + id, form)
    },

    /**
     * 根据id获取项目
     * @param id 项目id
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    getProjectById(id) {
        return request.get('/projectlist/getbyid?projectId=' + id)
    },

    /**
     * 删除项目
     * @param id 项目id
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    deleteProject(id) {
        return request.delete('/projectlist/delete?projectId=' + id)
    },

    /**
     * 获取所有项目阶段
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    getAllProjectStage() {
        return request.get('/projectlist/getallstages')
    },

    /**
     * 获取所有项目分类
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    getProjectCategory() {
        return request.get('/projectlist/getallcategories')
    },

    /**
     * 获取所有项目子分类
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    getProjectSubCategory(id) {
        return request.get('/projectlist/getallsubcategories?categoryId='+id)
    },

    /**
     * 结项项目
     * @param id
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    completeProject(id) {
        return request.put('/projectlist/complete?projectId=' + id)
    },

    /**
     * 搜索项目
     * @param pagenum 页码
     * @param pagesize 每页数量
     * @param reqirement 模糊参数
     * @param condition TIME/NAME/PROGRESS/Budget
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    searchProject(pagenum, pagesize, reqirement, condition, status) {
        return request.get('/projectlist/searchbyrequirement?pagesize=' + pagesize + '&pagenum=' + pagenum + '&requirement=' + reqirement + '&condition=' + condition + "&status=" + status)
    },

    /**
     * 获取所有公司
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    getCompanyList() {
        return request.get('/projectlist/getallcompanies')
    },

    /**
     * 获取所有地区
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    getRegionList() {
        return request.get('/projectlist/getallregions')
    },

    /**
     * 获取所有部门
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    getDepartmentList() {
        return request.get('/projectlist/getalldepartments')
    },

    // 添加计划
    addPlan(form, projectId) {
        return request.post('/plans/addnewplan?projectId=' + projectId, form)
    },

    /**
     * 删除计划
     * @param planId 计划id
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    deletePlan(planId) {
        return request.delete('/plans/deleteplan?planId=' + planId)
    },

    /**
     * 完成计划
     * @param planId 计划id
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    completePlan(planId) {
        return request.put('/plans/completeplan?planId=' + planId)
    },

    /**
     * 编辑计划
     * @param form
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    editPlan(form) {
        return request.put('/plans/editplan', form)
    },

    /**
     * 搜索计划
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param projectId 项目id
     * @param requirement 模糊参数
     * @param condition CREATION_TIME/NAME/PRIORITY
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    searchPlan(pageNum, pageSize, projectId, requirement, condition, status) {
        return request.get('/plans/search?pageSize=' + pageSize + '&pageNum=' + pageNum + '&projectId=' + projectId + '&requirement=' + requirement + '&condition=' + condition + "&status=" + status)
    },

    /**
     * 获取计划
     * @param planId 计划id
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    getPlanById(planId) {
        return request.get('/plans/getplanbyid?planId=' + planId)
    },

    /**
     * 根据项目id获取计划列表
     * @param projectId 项目id
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param condition CREATION_TIME/NAME/PRIORITY
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    getPlansOfProject(projectId, pageNum, pageSize, condition) {
        return request.get('/plans/getplansofproject?projectId=' + projectId + '&pageNum=' + pageNum + '&pageSize=' + pageSize + '&condition=' + condition)
    },

    getPlansTopoList(projectId, pageNum, pageSize, status) {
        return request.get('/plans/gettopolist?projectId=' + projectId + '&pageSize=' + pageSize + '&pageNum=' + pageNum + "&status=" + status)
    },

    /**
     * 添加预算
     * @param form
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    addBudget(form) {
        return request.post('/budgets/addnewbudget', form)
    },

    /**
     * 搜索预算
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param projectId 项目id
     * @param requirement 模糊参数
     * @param condition CREATION_TIME/NAME/AMOUNT
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    searchBudget(pageNum, pageSize, projectId, requirement, condition = 'CREATION_TIME', status) {
        return request.get('/budgets/search?pageSize=' + pageSize + '&pageNum=' + pageNum + '&projectId=' + projectId + '&requirement=' + requirement + '&condition=' + condition + '&status=' + status)
    },

    /**
     * 获取预算
     * @param budgetId 预算id
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    getBudgetById(budgetId) {
        return request.get('/budgets/getbudgetbyid?budgetId=' + budgetId)
    },

    /**
     * 删除预算
     * @param budgetId 预算Id
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    deleteBudget(budgetId) {
        return request.delete('/budgets/deletebudget?budgetId=' + budgetId)
    },

    /**
     * 编辑预算
     * @param budget
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    editBudget(budget) {
        return request.put('/budgets/editbudget', budget)
    },

    /**
     * 结算预算
     * @param budgetId
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    settleBudget(budgetId) {
        return request.put('/budgets/settlebudget?budgetId=' + budgetId)
    },

    /**
     * 获取项目成员列表
     * @param projectId 项目id
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    getProjectMemberList(projectId) {
        return request.get('/projectmembers/getmembersofproj?projectId=' + projectId)
    },

    /**
     * 删除项目成员
     * @param id 项目成员id
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    deleteProjectMember(id) {
        return request.delete('/projectmembers/deletemember?memberId=' + id)
    },

    /**
     * 编辑项目成员权限
     * @param userId 用户id
     * @param projectId 项目id
     * @param form 表单
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    editPermission(userId, projectId, form) {
        return request.put(`/projectmembers/authorizemember?projectId=${projectId}&userId=${userId}`, form)
    },

    /**
     * 添加项目成员
     * @param projectId 项目id
     * @param userId 用户id
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    addMember(projectId, userId) {
        return request.post(`/projectmembers/addnewmember?projectId=${projectId}&userId=${userId}`)
    },

    /**
     * 重置密码
     * @param oldPassword
     * @param newPassword
     * @return {Promise<axios.AxiosResponse<any>>}
     */
    resetPassword(oldPassword, newPassword) {
        return request.post('/userinfo/resetpassword', {
            oldPassword,
            newPassword
        })
    },

    quitProject(projectId) {
        return request.delete('/projectmembers/quitproject?projectId=' + projectId)
    }
}
