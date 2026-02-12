<template>
  <div class="container">
    <h1>CRUD Doctor</h1>
    
    <div class="botones">
      <button @click="mostrarFormulario('create')">CREATE</button>
      <button @click="mostrarFormulario('read')">READ</button>
      <button @click="mostrarFormulario('update')">UPDATE</button>
      <button @click="mostrarFormulario('delete')">DELETE</button>
    </div>
    
    <!-- Formulario CREATE -->
    <form v-if="mostrarFormularioCreate" @submit.prevent="crearDoctor" class="formulario">
      <p>Nombre</p>
      <input type="text" v-model="nuevoDoctor.nombre" required>
      
      <p>Apellido</p>
      <input type="text" v-model="nuevoDoctor.apellido" required>
      
      <p>Número Licencia Médica</p>
      <input type="text" v-model="nuevoDoctor.numerolicenciamedica" required>
      
      <p>Especialidad</p>
      <input type="text" v-model="nuevoDoctor.especialidad" required>
      
      <p>Estado</p>
      <select v-model="nuevoDoctor.estado" required>
        <option value="">Seleccione un estado</option>
        <option value="ACTIVO">ACTIVO</option>
        <option value="INACTIVO">INACTIVO</option>
      </select>
      
      <button type="submit" class="btn-submit">Guardar Doctor</button>
    </form>

    <!-- READ - Mostrar todos -->
    <div v-if="mostrarFormularioRead" class="formulario">
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Licencia</th>
            <th>Especialidad</th>
            <th>Estado</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="doctor in doctores" :key="doctor.id">
            <td>{{ doctor.id }}</td>
            <td>{{ doctor.nombre }}</td>
            <td>{{ doctor.apellido }}</td>
            <td>{{ doctor.numerolicenciamedica }}</td>
            <td>{{ doctor.especialidad }}</td>
            <td>{{ doctor.estado }}</td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Formulario UPDATE -->
    <div v-if="mostrarFormularioUpdate" class="formulario">
      <div v-if="!doctorCargado">
        <p>ID del Doctor a actualizar</p>
        <input type="number" v-model="doctorUpdate.id" required>
        <button @click="buscarDoctor" class="btn-submit">Buscar Doctor</button>
      </div>

      <form v-if="doctorCargado" @submit.prevent="actualizarDoctor">
        <p>Nombre</p>
        <input type="text" v-model="doctorUpdate.nombre" required>
        
        <p>Apellido</p>
        <input type="text" v-model="doctorUpdate.apellido" required>
        
        <p>Número Licencia Médica</p>
        <input type="text" v-model="doctorUpdate.numerolicenciamedica" required>
        
        <p>Especialidad</p>
        <input type="text" v-model="doctorUpdate.especialidad" required>
        
        <p>Estado</p>
        <select v-model="doctorUpdate.estado" required>
          <option value="">Seleccione un estado</option>
          <option value="ACTIVO">ACTIVO</option>
          <option value="INACTIVO">INACTIVO</option>
        </select>
        
        <button type="submit" class="btn-submit">Actualizar Doctor</button>
      </form>
    </div>

    <!-- Formulario DELETE -->
    <form v-if="mostrarFormularioDelete" @submit.prevent="eliminarDoctor" class="formulario">
      <p>ID del Doctor a eliminar</p>
      <input type="number" v-model="doctorDelete.id" required>
      
      <button type="submit" class="btn-submit btn-delete">Eliminar Doctor</button>
    </form>
  </div>
</template>

<script>
import { 
  consultarTodosDoctoresFacade, 
  registrarDoctorFacade, 
  modificarDoctorFacade, 
  eliminarDoctorFacade 
} from "../clients/CitasMedicasClient.js";

export default {
  data() {
    return {
      mostrarFormularioCreate: false,
      mostrarFormularioRead: false,
      mostrarFormularioUpdate: false,
      mostrarFormularioDelete: false,
      doctorCargado: false,

      nuevoDoctor: {
        nombre: '',
        apellido: '',
        numerolicenciamedica: '',
        especialidad: '',
        estado: ''
      },
      doctorUpdate: {
        id: '',
        nombre: '',
        apellido: '',
        numerolicenciamedica: '',
        especialidad: '',
        estado: ''
      },
      doctorDelete: {
        id: ''
      },
      doctores: []
    };
  },
  methods: {
    mostrarFormulario(tipo) {
      this.mostrarFormularioCreate = false;
      this.mostrarFormularioRead = false;
      this.mostrarFormularioUpdate = false;
      this.mostrarFormularioDelete = false;
      this.doctorCargado = false;
      
      if (tipo === 'create') {
        this.mostrarFormularioCreate = true;
      } else if (tipo === 'read') {
        this.mostrarFormularioRead = true;
        this.cargarDoctores();
      } else if (tipo === 'update') {
        this.mostrarFormularioUpdate = true;
      } else if (tipo === 'delete') {
        this.mostrarFormularioDelete = true;
      }
    },

    async cargarDoctores() {
      this.doctores = await consultarTodosDoctoresFacade();
    },

    async buscarDoctor() {
      // Como no tienes endpoint por ID, cargamos todos y buscamos
      const doctores = await consultarTodosDoctoresFacade();
      const doctor = doctores.find(d => d.id === parseInt(this.doctorUpdate.id));
      
      if (doctor) {
        this.doctorUpdate.nombre = doctor.nombre;
        this.doctorUpdate.apellido = doctor.apellido;
        this.doctorUpdate.numerolicenciamedica = doctor.numerolicenciamedica;
        this.doctorUpdate.especialidad = doctor.especialidad;
        this.doctorUpdate.estado = doctor.estado;
        this.doctorCargado = true;
      }
    },

    async crearDoctor() {
      await registrarDoctorFacade(this.nuevoDoctor);
      
      this.nuevoDoctor = {
        nombre: '',
        apellido: '',
        numerolicenciamedica: '',
        especialidad: '',
        estado: ''
      };
      
      this.mostrarFormularioCreate = false;
    },

    async actualizarDoctor() {
      await modificarDoctorFacade(this.doctorUpdate.id, {
        nombre: this.doctorUpdate.nombre,
        apellido: this.doctorUpdate.apellido,
        numerolicenciamedica: this.doctorUpdate.numerolicenciamedica,
        especialidad: this.doctorUpdate.especialidad,
        estado: this.doctorUpdate.estado
      });
      
      this.doctorUpdate = {
        id: '',
        nombre: '',
        apellido: '',
        numerolicenciamedica: '',
        especialidad: '',
        estado: ''
      };
      
      this.mostrarFormularioUpdate = false;
      this.doctorCargado = false;
    },

    async eliminarDoctor() {
      await eliminarDoctorFacade(this.doctorDelete.id);
      
      this.doctorDelete = {
        id: ''
      };
      
      this.mostrarFormularioDelete = false;
    }
  }
};
</script>

<style scoped>
.container {
  max-width: 800px;
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