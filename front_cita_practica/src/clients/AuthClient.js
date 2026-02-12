import axios from "axios";

const URL = `http://localhost:8082/auth/api/v1.0/usuarios/token`;

const obtenerToken = async (usuario, password)=>{
    const urlCompleta = `${URL}?user=${usuario}&password=${password}`;
    const token = await axios.get(urlCompleta).then(response => response.data).catch(()=>null);
    return token ? token.accessToken : null;
}

export const obtenerTokenFacade = async (usuario, password) =>{
 return await obtenerToken(usuario, password);  
} 