import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/home',
    name: 'home',
    component: HomeView,
    meta: { requiresAuth: true } // Ruta protegida
  },
  {
    path: '/about',
    name: 'about',
    component: () => import('../views/AboutView.vue'),
    meta: { requiresAuth: true } // Ruta protegida
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/LoginView.vue')
  },
  {
    path:'/formularios',
    name:'formularios',
    component:()=>import('../views/FormularioView.vue')
  },
  {
    path:'/agenda',
    name:'agenda',
    component:()=>import('../views/AgendarCitaView.vue')
  },
  
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

// --- EL GUARDIÁN ---
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem("token");

  // Caso 1: La ruta requiere autenticación y no hay token
  if (to.matched.some(record => record.meta.requiresAuth) && !token) {
    next('/login');
  } 
  // Caso 2: El usuario ya está logueado e intenta ir al Login
  else if (to.path === '/login' && token) {
    next('/home');
  } 
  // Caso 3: Todo está en orden, permitir navegación
  else {
    next();
  }
});

export default router