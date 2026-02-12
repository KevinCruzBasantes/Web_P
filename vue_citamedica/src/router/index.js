import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/home',
    name: 'home',
    component: ()=>import('../views/HomeView.vue')
  },
  
  {
    path:'/login',
    name: 'login',
    component: ()=>import('../views/LoginView.vue')
  },
  {
    path:'/citasReporte',
    name:'citasReporte',
    component: ()=>import('../views/CitasMedicasView.vue')
  },
  {
    path:'/citas',
    name:'citas',
    component:()=>import('../views/CitasView.vue')
  },
  {
    path:'/doctor',
    name:'doctor',
    component: ()=>import('../views/DoctorView.vue')
  },
  {
    path:'/paciente',
    name:'paciente',
    component:()=>import('../views/PacienteView.vue')
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
