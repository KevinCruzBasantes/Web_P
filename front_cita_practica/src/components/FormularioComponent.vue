<template>
  <div class="botonesMenu">
    <button @click="mostrarFormularios('doctor')">DOCTOR</button>
    <button @click="mostrarFormularios('paciente')">PACIENTE</button>
  </div>

  <div>
    <h3>Formulario Registro</h3>
    <form
      v-if="mostrarFormularioDoctor"
      @submit.prevent="crearDoctor"
      class="formDoc"
    >
      <p>Cedula</p>
      <input
        v-model="nuevoDoctor.cedula"
        type="number"
        placeholder="Ingrese los 10 numeros de cedula"
      />
      <p>Nombre</p>
      <input
        v-model="nuevoDoctor.nombre"
        type="text"
        placeholder="Ingrese el nombre"
      />
      <p>Apellido</p>
      <input
        v-model="nuevoDoctor.apellido"
        type="text"
        placeholder="Ingrese la contraseña"
      />
      <p>Genero</p>
      <select v-model="nuevoDoctor.genero">
        <option value="">Selecione el Genero</option>
        <option value="MASCULINO">MASCULINO</option>
        <option value="FEMENINO">FEMENINO</option>
      </select>
      <button>REGISTRAR</button>
    </form>
    <div v-if="mostrarMensaje" class="alerta-exito">
      {{ mensaje }}
    </div>

    <form
      v-if="mostrarFormularioPaciente"
      @submit.prevent="crearPaciente"
      class="formPac"
    >
      <p>CEDULA</p>
      <input v-model="nuevoPaciente.cedula" type="number" placeholder="Ingrese la cedula">
      <p>NOMBRE</p>
      <input v-model="nuevoPaciente.nombre" type="text" placeholder="Ingrese el nombre">
      <p>APELLIDO</p>
      <input v-model="nuevoPaciente.apellido" type="text" placeholder="Ingrese el apellido">
      <p>FECHA NACIMIENTO</p>
      <input v-model="nuevoPaciente.fechanacimiento" type="date">
      <button>REGISTRAR</button>
    </form>
  </div>
</template>

<script>
import { registrarDoctorFacade, registrarPacienteFacade  } from "../clients/CitaClient.js";
export default {
  data() {
    return {
      mostrarFormularioDoctor: false,
      mostrarFormularioPaciente: false,
      mensaje: "",
      mostrarMensaje: false,
      nuevoDoctor: {
        cedula: "",
        nombre: "",
        apellido: "",
        genero: "",
      },
      nuevoPaciente: {
        cedula: "",
        nombre: "",
        apellido: "",
        fechanacimiento: "",
      },
    };
  },
  methods: {
    mostrarFormularios(tipo) {
      if (tipo == "doctor") {
        this.mostrarFormularioDoctor = true;
      } else if (tipo == "paciente") {
        this.mostrarFormularioPaciente = true;
      }
    },
    async crearDoctor() {
      await registrarDoctorFacade(this.nuevoDoctor);
      this.mensaje = "Doctor Registrado";
      this.mostrarMensaje = true;
      this.nuevoDoctor = {
        cedula: "",
        nombre: "",
        apellido: "",
        genero: "",
      };
      setTimeout(() => {
        this.mostrarMensaje = false;
        this.mensaje = "";
      }, 10000); // 10000 milisegundos = 10 segundos
      this.mostrarFormularioDoctor = false;
    },
    async crearPaciente(){
        await registrarPacienteFacade(this.nuevoPaciente);
        this.mostrarMensaje = true;
        this.mensaje="Paciente Registrado";
        this.nuevoPaciente={
            cedula:"",
            nombre:"",
            apellido:"",
            fechanacimiento:"",
        };
        setTimeout(()=>{
            this.mostrarMensaje=false;
            this.mensaje="";
        }, 10000);
        this.mostrarFormularioPaciente=false
    }
  },
};
</script>

<style>
</style>