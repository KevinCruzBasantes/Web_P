<template>
  <div class="container">
    <h1>CRUD Citas</h1>
    
    <div class="botones">
      <button @click="mostrarFormulario('create')">CREATE</button>
      <button @click="mostrarFormulario('update')">UPDATE</button>
      <button @click="mostrarFormulario('delete')">DELETE</button>
    </div>
    
    <!-- Formulario CREATE -->
    <form v-if="mostrarFormularioCreate" @submit.prevent="crearCita" class="formulario">
      <p>Doctor ID</p>
      <input type="number" v-model="nuevaCita.doctorId" required>
      
      <p>Paciente ID</p>
      <input type="number" v-model="nuevaCita.pacienteId" required>
      
      <p>Fecha Cita</p>
      <input type="date" v-model="nuevaCita.fechacita" required>
      
      <p>Hora Cita</p>
      <input type="time" v-model="nuevaCita.horacita" required>
      
      <p>Motivo</p>
      <input type="text" v-model="nuevaCita.motivo" required>
      
      <p>Estado Cita</p>
      <select v-model="nuevaCita.estadocita" required>
        <option value="">Seleccione un estado</option>
        <option value="CREADA">CREADA</option>
        <option value="CANCELADA">CANCELADA</option>
        <option value="COMPLETADA">COMPLETADA</option>
      </select>
      
      <button type="submit" class="btn-submit">Guardar Cita</button>
    </form>

    <!-- Formulario UPDATE -->
    <div v-if="mostrarFormularioUpdate" class="formulario">
      <!-- Primero buscar la cita -->
      <div v-if="!citaCargada">
        <p>ID de la Cita a actualizar</p>
        <input type="number" v-model="citaUpdate.id" required>
        <button @click="buscarCita" class="btn-submit">Buscar Cita</button>
      </div>

      <!-- Luego mostrar el formulario con los datos -->
      <form v-if="citaCargada" @submit.prevent="actualizarCita">
        <p>Doctor ID</p>
        <input type="number" v-model="citaUpdate.doctorId" required>
        
        <p>Paciente ID</p>
        <input type="number" v-model="citaUpdate.pacienteId" required>
        
        <p>Fecha Cita</p>
        <input type="date" v-model="citaUpdate.fechacita" required>
        
        <p>Hora Cita</p>
        <input type="time" v-model="citaUpdate.horacita" required>
        
        <p>Motivo</p>
        <input type="text" v-model="citaUpdate.motivo" required>
        
        <p>Estado Cita</p>
        <select v-model="citaUpdate.estadocita" required>
          <option value="">Seleccione un estado</option>
          <option value="CREADA">CREADA</option>
          <option value="CANCELADA">CANCELADA</option>
          <option value="COMPLETADA">COMPLETADA</option>
        </select>
        
        <button type="submit" class="btn-submit">Actualizar Cita</button>
      </form>
    </div>

    <!-- Formulario DELETE -->
    <form v-if="mostrarFormularioDelete" @submit.prevent="eliminarCita" class="formulario">
      <p>ID de la Cita a eliminar</p>
      <input type="number" v-model="citaDelete.id" required>
      
      <button type="submit" class="btn-submit btn-delete">Eliminar Cita</button>
    </form>
  </div>
</template>

<script>
import { registrarCitaFacade, modificarCitaFacade, eliminarCitaFacade, consultarCitaPorIdFacade } from "../clients/CitasMedicasClient.js";

export default {
  data() {
    return {
      mostrarFormularioCreate: false,
      mostrarFormularioUpdate: false,
      mostrarFormularioDelete: false,
      citaCargada: false,

      nuevaCita: {
        doctorId: '',
        pacienteId: '',
        fechacita: '',
        horacita: '',
        motivo: '',
        estadocita: ''
      },
      citaUpdate: {
        id: '',
        doctorId: '',
        pacienteId: '',
        fechacita: '',
        horacita: '',
        motivo: '',
        estadocita: ''
      },
      citaDelete: {
        id: ''
      }
    };
  },
  methods: {
    mostrarFormulario(tipo) {
      this.mostrarFormularioCreate = false;
      this.mostrarFormularioUpdate = false;
      this.mostrarFormularioDelete = false;
      this.citaCargada = false;
      
      if (tipo === 'create') {
        this.mostrarFormularioCreate = true;
      } else if (tipo === 'update') {
        this.mostrarFormularioUpdate = true;
      } else if (tipo === 'delete') {
        this.mostrarFormularioDelete = true;
      }
    },

    async buscarCita() {
      const cita = await consultarCitaPorIdFacade(this.citaUpdate.id);
      
      this.citaUpdate.doctorId = cita.doctor.id;
      this.citaUpdate.pacienteId = cita.paciente.id;
      this.citaUpdate.fechacita = cita.fechacita;
      this.citaUpdate.horacita = cita.horacita;
      this.citaUpdate.motivo = cita.motivo;
      this.citaUpdate.estadocita = cita.estadocita;
      
      this.citaCargada = true;
    },

    async crearCita() {
      const citaData = {
        doctor: { id: parseInt(this.nuevaCita.doctorId) },
        paciente: { id: parseInt(this.nuevaCita.pacienteId) },
        fechacita: this.nuevaCita.fechacita,
        horacita: this.nuevaCita.horacita,
        motivo: this.nuevaCita.motivo,
        estadocita: this.nuevaCita.estadocita
      };
      
      await registrarCitaFacade(citaData);
      
      this.nuevaCita = {
        doctorId: '',
        pacienteId: '',
        fechacita: '',
        horacita: '',
        motivo: '',
        estadocita: ''
      };
      
      this.mostrarFormularioCreate = false;
    },

    async actualizarCita() {
      const citaData = {
        doctor: { id: parseInt(this.citaUpdate.doctorId) },
        paciente: { id: parseInt(this.citaUpdate.pacienteId) },
        fechacita: this.citaUpdate.fechacita,
        horacita: this.citaUpdate.horacita,
        motivo: this.citaUpdate.motivo,
        estadocita: this.citaUpdate.estadocita
      };
      
      await modificarCitaFacade(this.citaUpdate.id, citaData);
      
      this.citaUpdate = {
        id: '',
        doctorId: '',
        pacienteId: '',
        fechacita: '',
        horacita: '',
        motivo: '',
        estadocita: ''
      };
      
      this.mostrarFormularioUpdate = false;
      this.citaCargada = false;
    },

    async eliminarCita() {
      await eliminarCitaFacade(this.citaDelete.id);
      
      this.citaDelete = {
        id: ''
      };
      
      this.mostrarFormularioDelete = false;
    }
  }
};
</script>

<style scoped>
.container {
  max-width: 600px;
  margin: 20px auto;
  padding: 20px;
}

h1 {
  text-align: center;
  margin-bottom: 20px;
}

.botones {
  display: flex;
  gap: 10px;
  margin-bottom: 30px;
}

.botones button {
  flex: 1;
  padding: 10px;
  border: 1px solid #ccc;
  background-color: #f0f0f0;
  cursor: pointer;
  border-radius: 4px;
}

.botones button:hover {
  background-color: #e0e0e0;
}

.formulario {
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 8px;
  border: 1px solid #ddd;
}

.formulario p {
  margin: 15px 0 5px 0;
  font-weight: bold;
}

.formulario input,
.formulario select {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

.btn-submit {
  width: 100%;
  padding: 12px;
  margin-top: 20px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
}

.btn-submit:hover {
  background-color: #45a049;
}

.btn-delete {
  background-color: #f44336;
}

.btn-delete:hover {
  background-color: #da190b;
}
</style>