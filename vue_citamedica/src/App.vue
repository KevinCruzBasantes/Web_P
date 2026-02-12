<template>
  <nav v-if="estaLogueado">
    <router-link to="/home">Home</router-link> |
    <router-link to="/citasReporte">Citas Reporte </router-link> |
    <router-link to="/citas">Citas</router-link> |
    <router-link to="/doctor">Doctor</router-link> |
    <router-link to="/paciente">Paciente</router-link>|
    <button @click="logout">Salir</button>
  </nav>

  <router-view />
</template>

<script>
export default {
  data() {
    return {
      estaLogueado: false
    };
  },
  // Vigila los cambios de ruta para actualizar la visibilidad del menú
  watch: {
    $route() {
      this.verificarSesion();
    }
  },
  mounted() {
    this.verificarSesion();
  },
  methods: {
    verificarSesion() {
      const token = localStorage.getItem("token");
      this.estaLogueado = !!token; // Convierte la existencia del token a booleano
      console.log("Ruta:", this.$route.path, "| Logueado:", this.estaLogueado);
    },
    logout() {
      localStorage.removeItem("token");
      this.$router.push("/login");
    }
  }
}
</script>

<style scoped>
nav { padding: 30px; text-align: center; }
nav a { font-weight: bold; color: #2c3e50; margin: 0 10px; }
nav a.router-link-exact-active { color: #42b983; }
button { cursor: pointer; background: none; border: 1px solid #ccc; padding: 5px; }
</style>