<template>
  <div>
    <h1>MOSTRAR CITAS</h1>
    <table border="1">
      <thead>
        <tr>
          <th>Paciente</th>
          <th>Cédula</th>
          <th>Fecha y Hora</th>
          <th>Doctor</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="cita in citas" :key="cita.id">
          <td>{{ cita.nombrePaciente }}</td>
          <td>{{ cita.cedulaPaciente }}</td>
          <td>{{ cita.fechacita }}</td>
          <td>{{ cita.nombreDoctor }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  // Definimos la Prop que el padre nos va a enviar
  props: {
    citas: {
      type: Array,
      required: true
    }
  }
}
</script>