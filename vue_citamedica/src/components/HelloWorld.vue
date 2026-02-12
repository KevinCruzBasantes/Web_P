<template>
  <div class="home-container">
    <h1>Sistema de Citas Médicas</h1>
    <img src="../assets/citas.png" alt="Citas Médicas">
    <p>Bienvenido al sistema de gestión de citas médicas</p>
  </div>
</template>

<script>
export default {
  
}
</script>

<style scoped>
.home-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 80vh;
  padding: 20px;
}

h1 {
  margin-bottom: 30px;
  color: #333;
}

img {
  max-width: 500px;
  width: 100%;
  height: auto;
  margin-bottom: 20px;
  border-radius: 8px;
}

p {
  font-size: 18px;
  color: #666;
}
</style>