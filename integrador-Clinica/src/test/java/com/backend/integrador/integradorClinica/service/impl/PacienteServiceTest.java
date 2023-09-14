package com.backend.integrador.integradorClinica.service.impl;

import com.backend.integrador.integradorClinica.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.integrador.integradorClinica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.integrador.integradorClinica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.integrador.integradorClinica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.integrador.integradorClinica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")

class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    void deberiaInsertarUnPacienteDeNombreJuanConId1()
    {
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Juan","Perez",111111, LocalDate.of(2023,9,12),
                new DomicilioEntradaDto("Calle", 1232, "localidad","provincia"));

        PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);

        assertEquals("Juan", pacienteSalidaDto.getNombre());
        assertEquals(1, pacienteSalidaDto.getId());
    }

    @Test
    @Order(2)
    void deberiaRetornarseUnaListaNoVaciaDePacientes()
    {
        assertTrue(pacienteService.listarPacientes().size()>0);
    }

    @Test
    @Order(3)
    void alIntentarActualizarElPacienteId2_deberiaLanzarseUnaResourceNotFoundException()
    {
        PacienteModificacionEntradaDto pacienteModificacionEntradaDto = new PacienteModificacionEntradaDto();
        pacienteModificacionEntradaDto.setId(2L);
        assertThrows(ResourceNotFoundException.class, () ->
                pacienteService.modificarPaciente(pacienteModificacionEntradaDto));

    }

    @Test
    @Order(4)
    void alIntentarEliminarUnPacienteYaEliminado_deberiaLanzarseUnResourceNotFoundException()
    {
        try{
            pacienteService.eliminarPaciente(1L);
        }catch (Exception e){
            e.printStackTrace();
        }
        assertThrows(ResourceNotFoundException.class, ()->
                pacienteService.eliminarPaciente(1L));
    }

    @Test
    @Order(5)
    void deberiaEliminarUnPacienteConId2() throws ResourceNotFoundException {
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Juan","Perez",111111, LocalDate.of(2023,9,12),
                new DomicilioEntradaDto("Calle", 1232, "localidad","provincia"));

        PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);

        pacienteService.eliminarPaciente(2L);
    }


}