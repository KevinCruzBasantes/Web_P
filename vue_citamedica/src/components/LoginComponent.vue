<template>
  <div class="containerLogin">
    <form @submit.prevent="login" class="formuLogin">
      <p>USUARIO</p>
      <input v-model="username" type="text" placeholder="Ingrese su Usuario" />
      <p>CONTRASEÑA</p>
      <input
        v-model="password"
        type="password"
        placeholder="Ingrese su Contraseña"
      />
      <div class="botonesLogin">
        <button type="submit">Login</button>
      </div>
      <div class="mensajesLogin">
        <p v-if="error" class="mensajeError">Usuario o Contraseña incorrecto</p>
      </div>
    </form>
  </div>
</template>

<script>
import { obTokenFacade } from "../clients/AuthClient.js";
export default {
  data() {
    return {
      username: "",
      password: "",
      error: false,
    };
  },
  methods: {
    async login() {
      this.error = false;
      const token = await obTokenFacade(this.username, this.password);
      if (token) {
        localStorage.setItem("token", token);
        this.$router.push("/home");
      } else {
        this.error = true;
      }
    },
  },
};
</script>

<style scoped>
.formuLogin {
  border: 1px solid #ccc;
  padding: 20px;
  width: 300px;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  gap: 10px;

}
.mensajeError{
    color: red;
}
</style>