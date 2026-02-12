<template>
  <div class="container">
    <h1>CRUD Paciente</h1>
    
    <div class="botones">
      <button @click="mostrarFormulario('create')">CREATE</button>
      <button @click="mostrarFormulario('read')">READ</button>
      <button @click="mostrarFormulario('update')">UPDATE</button>
      <button @click="mostrarFormulario('delete')">DELETE</button>
    </div>
    
    <!-- Formulario CREATE -->
    <form v-if="mostrarFormularioCreate" @submit.prevent="crearPaciente" class="formulario">
      <p>Nombre</p>
      <input type="text" v-model="nuevoPaciente.nombre" required>
      
      <p>Apellido</p>
      <input type="text" v-model="nuevoPaciente.apellido" required>
      
      <p>Cédula</p>
      <input type="text" v-model="nuevoPaciente.cedula" maxlength="10" required>
      
      <p>Celular</p>
      <input type="text" v-model="nuevoPaciente.celular" maxlength="10" required>
      
      <p>Fecha Nacimiento</p>
      <input type="date" v-model="nuevoPaciente.fechanacimiento" required>
      
      <p>Dirección</p>
      <input type="text" v-model="nuevoPaciente.direccion" required>
      
      <button type="submit" class="btn-submit">Guardar Paciente</button>
    </form>

    <!-- READ - Mostrar todos -->
    <div v-if="mostrarFormularioRead" class="formulario">
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Cédula</th>
            <th>Celular</th>
            <th>Fecha Nacimiento</th>
            <th>Dirección</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="paciente in pacientes" :key="paciente.id">
            <td>{{ paciente.id }}</td>
            <td>{{ paciente.nombre }}</td>
            <td>{{ paciente.apellido }}</td>
            <td>{{ paciente.cedula }}</td>
            <td>{{ paciente.celular }}</td>
            <td>{{ formatearFecha(paciente.fechanacimiento) }}</td>
            <td>{{ paciente.direccion }}</td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Formulario UPDATE -->
    <div v-if="mostrarFormularioUpdate" class="formulario">
      <div v-if="!pacienteCargado">
        <p>ID del Paciente a actualizar</p>
        <input type="number" v-model="pacienteUpdate.id" required>
        <button @click="buscarPaciente" class="btn-submit">Buscar Paciente</button>
      </div>

      <form v-if="pacienteCargado" @submit.prevent="actualizarPaciente">
        <p>Nombre</p>
        <input type="text" v-model="pacienteUpdate.nombre" required>
        
        <p>Apellido</p>
        <input type="text" v-model="pacienteUpdate.apellido" required>
        
        <p>Cédula</p>
        <input type="text" v-model="pacienteUpdate.cedula" maxlength="10" required>
        
        <p>Celular</p>
        <input type="text" v-model="pacienteUpdate.celular" maxlength="10" required>
        
        <p>Fecha Nacimiento</p>
        <input type="date" v-model="pacienteUpdate.fechanacimiento" required>
        
        <p>Dirección</p>
        <input type="text" v-model="pacienteUpdate.direccion" required>
        
        <button type="submit" class="btn-submit">Actualizar Paciente</button>
      </form>
    </div>

    <!-- Formulario DELETE -->
    <form v-if="mostrarFormularioDelete" @submit.prevent="eliminarPaciente" class="formulario">
      <p>ID del Paciente a eliminar</p>
      <input type="number" v-model="pacienteDelete.id" required>
      
      <button type="submit" class="btn-submit btn-delete">Eliminar Paciente</button>
    </form>
  </div>
</template>

<script>
import { 
  consultarTodosPacientesFacade, 
  registrarPacienteFacade, 
  modificarPacienteFacade, 
  eliminarPacienteFacade 
} from "../clients/CitasMedicasClient.js";

export default {
  data() {
    return {
      mostrarFormularioCreate: false,
      mostrarFormularioRead: false,
      mostrarFormularioUpdate: false,
      mostrarFormularioDelete: false,
      pacienteCargado: false,

      nuevoPaciente: {
        nombre: '',
        apellido: '',
        cedula: '',
        celular: '',
        fechanacimiento: '',
        direccion: ''
      },
      pacienteUpdate: {
        id: '',
        nombre: '',
        apellido: '',
        cedula: '',
        celular: '',
        fechanacimiento: '',
        direccion: ''
      },
      pacienteDelete: {
        id: ''
      },
      pacientes: []
    };
  },
  methods: {
    mostrarFormulario(tipo) {
      this.mostrarFormularioCreate = false;
      this.mostrarFormularioRead = false;
      this.mostrarFormularioUpdate = false;
      this.mostrarFormularioDelete = false;
      this.pacienteCargado = false;
      
      if (tipo === 'create') {
        this.mostrarFormularioCreate = true;
      } else if (tipo === 'read') {
        this.mostrarFormularioRead = true;
        this.cargarPacientes();
      } else if (tipo === 'update') {
        this.mostrarFormularioUpdate = true;
      } else if (tipo === 'delete') {
        this.mostrarFormularioDelete = true;
      }
    },

    async cargarPacientes() {
      this.pacientes = await consultarTodosPacientesFacade();
    },

    async buscarPaciente() {
      const pacientes = await consultarTodosPacientesFacade();
      const paciente = pacientes.find(p => p.id === parseInt(this.pacienteUpdate.id));
      
      if (paciente) {
        this.pacienteUpdate.nombre = paciente.nombre;
        this.pacienteUpdate.apellido = paciente.apellido;
        this.pacienteUpdate.cedula = paciente.cedula;
        this.pacienteUpdate.celular = paciente.celular;
        this.pacienteUpdate.fechanacimiento = paciente.fechanacimiento;
        this.pacienteUpdate.direccion = paciente.direccion;
        this.pacienteCargado = true;
      }
    },

    async crearPaciente() {
      await registrarPacienteFacade(this.nuevoPaciente);
      
      this.nuevoPaciente = {
        nombre: '',
        apellido: '',
        cedula: '',
        celular: '',
        fechanacimiento: '',
        direccion: ''
      };
      
      this.mostrarFormularioCreate = false;
    },

    async actualizarPaciente() {
      await modificarPacienteFacade(this.pacienteUpdate.id, {
        nombre: this.pacienteUpdate.nombre,
        apellido: this.pacienteUpdate.apellido,
        cedula: this.pacienteUpdate.cedula,
        celular: this.pacienteUpdate.celular,
        fechanacimiento: this.pacienteUpdate.fechanacimiento,
        direccion: this.pacienteUpdate.direccion
      });
      
      this.pacienteUpdate = {
        id: '',
        nombre: '',
        apellido: '',
        cedula: '',
        celular: '',
        fechanacimiento: '',
        direccion: ''
      };
      
      this.mostrarFormularioUpdate = false;
      this.pacienteCargado = false;
    },

    async eliminarPaciente() {
      await eliminarPacienteFacade(this.pacienteDelete.id);
      
      this.pacienteDelete = {
        id: ''
      };
      
      this.mostrarFormularioDelete = false;
    },

    formatearFecha(fecha) {
      const [year, month, day] = fecha.split("-");
      return `${day}/${month}/${year}`;
    }
  }
};
</script>

<style scoped>
.container {
  max-width: 900px;
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

.formulario input {
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

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 10px;
}

th, td {
  padding: 12px;
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
</style>