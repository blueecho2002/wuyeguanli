import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import ChargeDetailView from '../views/ChargeDetailView.vue'
import ChargeItemView from '../views/ChargeItemView.vue'
import PrePaymentView from '../views/PrePaymentView.vue'

const routes = [
  {
    path: '/',
    redirect: '/login',
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/home',
    name: 'home',
    component: HomeView,
    children: [{
      path: '/charge-detail',
      component: ChargeDetailView,
    },
    {
      path: '/charge-item',
      component: ChargeItemView,
    },
    {
      path: '/pre-payment',
      component: PrePaymentView,
    }]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  if (to.path === '/login') return next();
  //获取token
  const tokenStr = localStorage.getItem('token')
  if (!tokenStr)
    return next('/login')
  next()
  
})

export default router
