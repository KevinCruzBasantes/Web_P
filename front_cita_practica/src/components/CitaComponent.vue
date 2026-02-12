<template>
    <div>
        <h1>CREAR CITAs</h1>
        <form @submit.prevent="crearCita">
            <p>DOCTOR</p>
            <input v-model="nuevaCita.cedulaDoctor" type="number" placeholder="Ingrese la cedula del doctor">
            <p>PACIENTE</p>
            <input v-model="nuevaCita.cedulaPaciente" type="numbre" placeholder="Ingrese la cedula del paciente">
            <P>FECHA CITA</P>
            <input v-model="nuevaCita.fechacita" type="datetime-local">
            <br><br/>
            <button>REGISTRAR</button>
        </form>
    </div>
</template>

<script>
import {registrarCitaFacade} from "../clients/CitaClient.js"
export default {
    data(){
        return{
            nuevaCita:{
                cedulaDoctor:"",
                cedulaPaciente:"",
                fechacita:""
            }
        }
    },
    methods:{
        async crearCita(){
            await registrarCitaFacade(this.nuevaCita);
            this.$emit("cita-registrada");
            this.nuevaCita={
                cedulaDoctor:"",
                cedulaPaciente:"",
                fechacita:"",
            }
        }
    }
}
</script>

<style>

</style>