package com.backend.integrador.integradorClinica.service.impl;

import com.backend.integrador.integradorClinica.dto.entrada.modificacion.OdontologoModificacionEntradaDto;
import com.backend.integrador.integradorClinica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.integrador.integradorClinica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.integrador.integradorClinica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;


    @Test
    @Order(1)
    void deberiaInsertarUnOdontologODeNombreElenaConId1ConMatriculaAB1234()
    {
        OdontologoEntradaDto odontologoEntradaDto =
                new OdontologoEntradaDto("AB-1234","Elena","López");

        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);

        assertEquals("Elena", odontologoSalidaDto.getNombre());
        assertEquals(1, odontologoSalidaDto.getId());
        assertEquals("AB-1234", odontologoSalidaDto.getMatricula());

    }

    @Test
    @Order(2)
    void deberiaRetornarseUnaListaNoVaciaDeOdontologos()
    {
        assertTrue(odontologoService.listarOdontologos().size()>0);
    }

    @Test
    @Order(3)
    void alIntentarActualizarElOdontologoId2_deberiaLanzarseUnaResourceNotFoundException()
    {
        OdontologoModificacionEntradaDto odontologoModificacionEntradaDto = new OdontologoModificacionEntradaDto();
        odontologoModificacionEntradaDto.setId(2L);
        assertThrows(ResourceNotFoundException.class, () ->
                odontologoService.actualizarOdontologo(odontologoModificacionEntradaDto));
    }


    @Test
    @Order(4)
    void alIntentarEliminarUnOdontologoYaEliminado_deberiaLanzarseUnResourceNotFoundException()
    {
        try{
            odontologoService.eliminarOdontologo(1L);
        }catch (Exception e){
            e.printStackTrace();
        }
        assertThrows(ResourceNotFoundException.class, ()->
                odontologoService.eliminarOdontologo(1L));
    }

    @Test
    @Order(5)
    void deberiaEliminarUnOdontologoConId2() throws ResourceNotFoundException {
        OdontologoEntradaDto odontologoEntradaDto =
                new OdontologoEntradaDto("AB-1220","Diana","Vargas");

        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);

        odontologoService.eliminarOdontologo(2L);
    }


}