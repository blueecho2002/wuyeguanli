import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import ChargeDetailView from '../views/ChargeDetailView.vue'
import ChargeItemView from '../views/ChargeItemView.vue'
import PrePaymentView from '../views/PrePaymentView.vue'
import ParkingView from '../views/ParkingView.vue'
import RepairView from '../views/RepairView.vue'
import PersonnelView from '../views/PersonnelView.vue'
import ComplaintView from '../views/ComplaintView.vue'
import PayView from '../views/PayView.vue'
import MyRepairView from '../views/MyRepairView.vue'

import PersonnelComplainView from '../views/PersonnelComplainView.vue'

import Personnel from '../views/Personnel.vue'
import Meter from '../views/Meter.vue'
import Owner from '../views/Owner.vue'
import ZiLiao from '../views/ZiLiao.vue'
import Complain from '../views/Complain.vue'

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
    },
    {
      path: '/parking',
      component: ParkingView,
    },
    {
      path: '/repair',
      component: RepairView,
    },
    {
      path: '/personnel',
      component: PersonnelView,
    },
    {
      path: '/complaint',
      component: ComplaintView,
    },
    {
      path: '/pay',
      component: PayView,
    },
    {
      path: '/my-repair',
      component: MyRepairView,
    },{
      path: '/personnel-complaint',
      component: PersonnelComplainView,
    },

  {
    //物业抄表
      path: '/personnel-meter',
      component: Personnel,
    },{
      path: '/owner',
      component: Owner,
    },{
      path: '/owner-complain',
      component: Complain,
    },{
      path: '/ziliao',
      component: ZiLiao,
    },{
      path: '/meter',
      component: Meter,
    },
  
  ]
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
