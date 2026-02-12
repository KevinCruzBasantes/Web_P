<template>
  <nav v-if="estaLogueado">
    <router-link to="/home">Home</router-link> |
    <router-link to="/about">About</router-link> |
    <router-link to="/formularios">Formulario</router-link>|
    <router-link to="/agenda">Agenda</router-link>
    <button @click="logout" class="btn-salir">Salir</button>
  </nav>
  <router-view/>
</template>

<script>
export default {
  data() {
    return {
      // Usamos siempre 'estaLogueado' (con 'u')
      estaLogueado: false,
    }
  },
  watch: {
    // Cada vez que cambias de página, revisamos si el token sigue ahí
    $route() {
      this.verificarSesion();
    }
  },
  mounted() {
    // Al cargar la app por primera vez
    this.verificarSesion();
  },
  methods: {
    verificarSesion() {
      const token = localStorage.getItem("token");
      this.estaLogueado = !!token; // Si existe token es true, si es null es false
      console.log("Estado de sesión:", this.estaLogueado);
    },
    logout() {
      // 1. Limpiamos el almacenamiento
      localStorage.removeItem("token");
      
      // 2. IMPORTANTE: Cambiamos el estado local para que el nav desaparezca de inmediato
      this.estaLogueado = false; 
      
      // 3. Redirigimos al login
      this.$router.push("/login");
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
  text-decoration: none;
}

nav a.router-link-exact-active {
  color: #42b983;
}

.btn-salir {
  background: none;
  border: 1px solid #2c3e50;
  padding: 5px 10px;
  cursor: pointer;
  border-radius: 4px;
  margin-left: 10px;
}

.btn-salir:hover {
  background-color: #f8f8f8;
  color: #e74c3c;
  border-color: #e74c3c;
}
</style>