<template>
  <div class="citasContainer">
    <h1>CitasMedicas</h1>
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Paciente</th>
          <th>Cédula</th>
          <th>Doctor</th>
          <th>Especialidad</th>
          <th>Fecha</th>
          <th>Hora</th>
          <th>Motivo</th>
          <th>Estado</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="cita in citas" :key="cita.id">
          <td>{{ cita.id }}</td>
          <td>{{ cita.paciente.nombre }} {{ cita.paciente.apellido }}</td>
          <td>{{ cita.paciente.cedula }}</td>
          <td>{{ cita.doctor.nombre }} {{ cita.doctor.apellido }}</td>
          <td>{{ cita.doctor.especialidad }}</td>
          <td>{{ cita.fechacita }}</td>
          <td>{{ cita.horacita }}</td>
          <td>{{ cita.motivo }}</td>
          <td>{{ cita.estadocita }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import { consultarTodasCitasFacade } from "../clients/CitasMedicasClient.js";
export default {
  data() {
    return {
      citas: [],
    };
  },
  methods: {
    async cargarCitas() {
      this.citas = await consultarTodasCitasFacade();
    },
    
  },
  mounted(){
    this.cargarCitas();
  }
};
</script>

<style scoped>
.citasContainer {
  padding: 20px;
}

h1 {
  margin-bottom: 20px;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  padding: 12px 15px;
  text-align: left;
  border: 1px solid #ddd;
}

th {
  background-color: #4CAF50;
  color: white;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

tr:hover {
  background-color: #ddd;
}
</style>