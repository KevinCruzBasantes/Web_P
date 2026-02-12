<template>
  <div class="containerLogin">
    <h1>Login</h1>
    <form @submit.prevent="login" class="formLogin">
        <p>USUARIO</p>
        <input v-model="usuario" type="text" placeholder="Ingrese su usuario">
        <p>CONTRASEÑA</p>
        <input v-model="contrasena" type="password" placeholder="Ingrese su contraseña">
        <div class="botonLogin">
            <button>LOGIN</button>
        </div>
        <div class="msjError">
            <p v-if="error">Usuario o Contraseña incorrectos</p>
        </div>
    </form>
  </div>
</template>

<script>
import {obtenerTokenFacade} from "../clients/AuthClient.js"
export default {
    data() {
        return {
            usuario: '',
            contrasena: '',
            error:false,
        }
    },
    methods:{
        async login(){
            this.error=false;
            const token = await obtenerTokenFacade(this.usuario, this.contrasena);
            if(token){
                localStorage.setItem("token", token);
                this.$router.push("/home");
            }else{
                this.error=true;
            }
        }
    }
}
</script>

<style>

</style>