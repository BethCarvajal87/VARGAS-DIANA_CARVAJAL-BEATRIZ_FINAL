package com.backend.integrador.integradorClinica.service.impl;


import com.backend.integrador.integradorClinica.dto.entrada.modificacion.TurnoModificacionEntradaDto;
import com.backend.integrador.integradorClinica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.integrador.integradorClinica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.integrador.integradorClinica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.integrador.integradorClinica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.integrador.integradorClinica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.integrador.integradorClinica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.integrador.integradorClinica.dto.salida.turno.TurnoSalidaDto;

import com.backend.integrador.integradorClinica.exceptions.BadRequestException;
import com.backend.integrador.integradorClinica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void deberiaInsertarUnTurnoConIdPaciente1ConIdOdontologo1() throws BadRequestException {

        //crear el paciente
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Juan","Perez",111111, LocalDate.of(2023,9,12),
                new DomicilioEntradaDto("Calle", 1232, "localidad","provincia"));

        PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);
        Long paciente = pacienteSalidaDto.getId();

        //crear el odontologo
        OdontologoEntradaDto odontologoEntradaDto =
                new OdontologoEntradaDto("AB-1234","Elena","López");

        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);
        Long odontologo = odontologoSalidaDto.getId();


        LocalDateTime fechaCita = LocalDateTime.of(2023, 9, 11, 10, 30);
        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(paciente,odontologo,fechaCita);
        TurnoSalidaDto turnoSalidaDto = turnoService.registrarTurno(turnoEntradaDto);

        assertEquals(1L, turnoSalidaDto.getPacienteTurnoSalidaDto().getId());
        assertEquals(1L, turnoSalidaDto.getOdontologoTurnoSalidaDto().getId());

    }

    @Test
    @Order(2)
    void deberiaRetornarseUnaListaNoVaciaDeTurnos()
    {
        assertTrue(turnoService.listarTurnos().size()>0);
    }

    @Test
    @Order(3)
    void alIntentarActualizarElTurnoConId2_deberiaLanzarseUnaResourceNotFoundException()
    {
        TurnoModificacionEntradaDto turnoModificacionEntradaDto = new TurnoModificacionEntradaDto();
        turnoModificacionEntradaDto.setId(2L);
        assertThrows(ResourceNotFoundException.class, () ->
                turnoService.modificarTurno(turnoModificacionEntradaDto));
    }

    @Test
    @Order(4)
    void alIntentarEliminarUnTurnoYaEliminado_deberiaLanzarseUnResourceNotFoundException()
    {
        try{
            turnoService.eliminarTurno(2L);
        }catch (Exception e){
            e.printStackTrace();
        }
        assertThrows(ResourceNotFoundException.class, ()->
                turnoService.eliminarTurno(2L));
    }


}