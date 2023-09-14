package com.backend.integrador.integradorClinica.service;

import com.backend.integrador.integradorClinica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.integrador.integradorClinica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.integrador.integradorClinica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.integrador.integradorClinica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService {

    List<PacienteSalidaDto> listarPacientes();

    PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente);

    PacienteSalidaDto buscarPacientePorId(Long id);

    void eliminarPaciente(Long id) throws ResourceNotFoundException;

    PacienteSalidaDto modificarPaciente(PacienteModificacionEntradaDto pacienteModificado) throws ResourceNotFoundException;
}
