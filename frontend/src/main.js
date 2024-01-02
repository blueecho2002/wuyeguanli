import { createApp } from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

import axios from 'axios'

const app = createApp(App).use(store).use(router).use(ElementPlus)

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

app.provide('$axios',axios)
//axios.defaults.headers.common['Accept'] = '/*/';
//axios.defaults.headers.common['Content-Type'] = 'application/json';
//每次请求在请求头中附带jwt令牌
axios.defaults.headers['token'] = localStorage.getItem('token');
app.mount('#app')