import axios from "axios";

const URL = `http://localhost:8081/citamedica/api/v1.0`

// ============ CITAS ============
const consultarTodasCitas = async () => {
    const token = localStorage.getItem('token');
    const data = await axios.get(`${URL}/citas`, {
        headers: { Authorization: `Bearer ${token}` }
    }).then(r => r.data);
    return data;
}

const consultarCitaPorId = async (id) => {
    const token = localStorage.getItem('token');
    const data = await axios.get(`${URL}/citas/${id}`, {
        headers: { Authorization: `Bearer ${token}` }
    }).then(r => r.data);
    return data;
}

const registrarCita = async (cita) => {
    const token = localStorage.getItem('token');
    const datos = await axios.post(`${URL}/citas`, cita, {
        headers: { Authorization: `Bearer ${token}` }
    }).then(r => r.data);
    return datos;
}

const modificarCita = async (id, cita) => {
    const token = localStorage.getItem('token');
    const data = await axios.put(`${URL}/citas/${id}`, cita, {
        headers: { Authorization: `Bearer ${token}` }
    }).then(r => r.data);
    return data;
}

const eliminarCita = async (id) => {
    const token = localStorage.getItem('token');
    await axios.delete(`${URL}/citas/${id}`, {
        headers: { Authorization: `Bearer ${token}` }
    });
}

// ============ DOCTORES ============
const consultarTodosDoctores = async () => {
    const token = localStorage.getItem('token');
    const data = await axios.get(`${URL}/doctores`, {
        headers: { Authorization: `Bearer ${token}` }
    }).then(r => r.data);
    return data;
}

const registrarDoctor = async (doctor) => {
    const token = localStorage.getItem('token');
    const data = await axios.post(`${URL}/doctores`, doctor, {
        headers: { Authorization: `Bearer ${token}` }
    }).then(r => r.data);
    return data;
}

const modificarDoctor = async (id, doctor) => {
    const token = localStorage.getItem('token');
    const data = await axios.put(`${URL}/doctores/${id}`, doctor, {
        headers: { Authorization: `Bearer ${token}` }
    }).then(r => r.data);
    return data;
}

const eliminarDoctor = async (id) => {
    const token = localStorage.getItem('token');
    await axios.delete(`${URL}/doctores/${id}`, {
        headers: { Authorization: `Bearer ${token}` }
    });
}

// ============ PACIENTES ============
const consultarTodosPacientes = async () => {
    const token = localStorage.getItem('token');
    const data = await axios.get(`${URL}/pacientes`, {
        headers: { Authorization: `Bearer ${token}` }
    }).then(r => r.data);
    return data;
}

const registrarPaciente = async (paciente) => {
    const token = localStorage.getItem('token');
    const data = await axios.post(`${URL}/pacientes`, paciente, {
        headers: { Authorization: `Bearer ${token}` }
    }).then(r => r.data);
    return data;
}

const modificarPaciente = async (id, paciente) => {
    const token = localStorage.getItem('token');
    const data = await axios.put(`${URL}/pacientes/${id}`, paciente, {
        headers: { Authorization: `Bearer ${token}` }
    }).then(r => r.data);
    return data;
}

const eliminarPaciente = async (id) => {
    const token = localStorage.getItem('token');
    await axios.delete(`${URL}/pacientes/${id}`, {
        headers: { Authorization: `Bearer ${token}` }
    });
}

// ============ FACADES CITAS ============
export const consultarTodasCitasFacade = async () => await consultarTodasCitas();
export const consultarCitaPorIdFacade = async (id) => await consultarCitaPorId(id);
export const registrarCitaFacade = async (cita) => await registrarCita(cita);
export const modificarCitaFacade = async (id, cita) => await modificarCita(id, cita);
export const eliminarCitaFacade = async (id) => await eliminarCita(id);

// ============ FACADES DOCTORES ============
export const consultarTodosDoctoresFacade = async () => await consultarTodosDoctores();
export const registrarDoctorFacade = async (doctor) => await registrarDoctor(doctor);
export const modificarDoctorFacade = async (id, doctor) => await modificarDoctor(id, doctor);
export const eliminarDoctorFacade = async (id) => await eliminarDoctor(id);

// ============ FACADES PACIENTES ============
export const consultarTodosPacientesFacade = async () => await consultarTodosPacientes();
export const registrarPacienteFacade = async (paciente) => await registrarPaciente(paciente);
export const modificarPacienteFacade = async (id, paciente) => await modificarPaciente(id, paciente);
export const eliminarPacienteFacade = async (id) => await eliminarPaciente(id);