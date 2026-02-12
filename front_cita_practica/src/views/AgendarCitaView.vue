<template>
  <div>
    <CitaComponent @cita-registrada="actualizarDatos" />
    <hr />
    <MostrarCitasComponent :citas="listaCitas" />
  </div>
</template>

<script>
import CitaComponent from "../components/CitaComponent.vue"
import MostrarCitasComponent from "../components/MostrarCitasComponent.vue"
import { mostrarCitasFacade } from "../clients/CitaClient.js"

export default {
  components: { CitaComponent, MostrarCitasComponent },
  data() {
    return {
      listaCitas: [] // Los datos viven aquí
    }
  },
  methods: {
    async actualizarDatos() {
      // El padre hace la petición
      this.listaCitas = await mostrarCitasFacade();
    }
  },
  mounted() {
    // Carga inicial
    this.actualizarDatos();
  }
}
</script>