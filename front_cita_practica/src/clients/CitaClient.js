import axios from "axios";

const URL = `http://localhost:8081/citapractica/api/v1.0`;

const registrarDoctor = async (doctor)=>{
    const token = localStorage.getItem("token");
    const datos = axios.post(`${URL}/doctores`,doctor, {
        headers: { Authorization: `Bearer ${token}` }
    }).then(r=>r.data);
    return datos;
}

const registrarPaciente = async (paciente) => {
    const token = localStorage.getItem("token");
    const datos = axios.post(`${URL}/pacientes`,paciente, {
        headers: {Authorization: `Bearer ${token}` }
    }).then(r=>r.data);
    return datos;
}
const registrarCita = async (cita)=>{
    const token = localStorage.getItem("token");
    const datos = axios.post(`${URL}/citas`, cita, {
        headers: {Authorization: `Bearer ${token}` }
    }).then(r=>r.data);
    return datos;

}

const mostrarCitas = async ()=>{
    const token = localStorage.getItem("token");
    const datos = await axios.get(`${URL}/citas`,{
        headers: {Authorization: `Bearer ${token}`}
    }).then(r=>r.data);
    return datos;

}
export const registrarDoctorFacade= async (doctor)=>{
    await registrarDoctor(doctor);
}

export const registrarPacienteFacade = async (paciente)=>{
    await registrarPaciente(paciente);
}

export const registrarCitaFacade = async (cita)=>{
    await registrarCita(cita);
}
export const mostrarCitasFacade = async ()=>{
    return await mostrarCitas();
}