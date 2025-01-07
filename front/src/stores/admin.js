import { defineStore } from 'pinia'
import { reactive } from 'vue'

const useAdmin = defineStore('admin', () => {
    const defaultAdmin = {
        userId: null,
        avatarUrl: null,
        username: null,
        phoneNumber: null,
        fullName: null
    }
    const admin = reactive(Object.assign({}, defaultAdmin))
    const updateAdmin = options => {
      Object.assign(admin, options)
      return admin
    }
    const removeAdmin = () => {
      Object.assign(admin, defaultAdmin)
      return admin
    }
    return { admin, updateAdmin, removeAdmin }
  }, {
    persist: {
      enabled: true,
      strategies: [
        {
          key: 'admin',
          storage: localStorage,
        }
      ]
    }
  }
)
export default useAdmin