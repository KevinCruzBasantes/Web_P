import axios from "axios";

const URL= "http://localhost:8082/auth/api/v1.0/usuarios/token"

const obToken= async (usuario,password) => {
    const urlCompleta=`${URL}?user=${usuario}&password=${password}`
    const token = await axios.get(urlCompleta).then(r => r.data).catch(()=>null);
    return token ? token.accessToken : null;
}

export const obTokenFacade= async (usuario,password)=>{
    return await obToken(usuario,password);
}