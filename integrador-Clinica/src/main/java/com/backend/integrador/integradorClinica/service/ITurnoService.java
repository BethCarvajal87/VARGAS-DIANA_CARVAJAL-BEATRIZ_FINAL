package com.backend.integrador.integradorClinica.service;

import com.backend.integrador.integradorClinica.dto.entrada.modificacion.TurnoModificacionEntradaDto;
import com.backend.integrador.integradorClinica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.integrador.integradorClinica.dto.salida.turno.TurnoSalidaDto;
import com.backend.integrador.integradorClinica.exceptions.BadRequestException;
import com.backend.integrador.integradorClinica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {
    TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) throws BadRequestException;
    List<TurnoSalidaDto> listarTurnos();
    TurnoSalidaDto  buscarTurnoPorId(Long id);

    TurnoSalidaDto  modificarTurno(TurnoModificacionEntradaDto turnoModificacionEntradaDto) throws ResourceNotFoundException;;
    void eliminarTurno(Long id) throws ResourceNotFoundException;


}
