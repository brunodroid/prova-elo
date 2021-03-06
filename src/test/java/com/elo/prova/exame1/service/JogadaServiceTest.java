package com.elo.prova.exame1.service;

import com.elo.prova.exame1.model.JogadaModel;
import com.elo.prova.exame1.enums.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class JogadaServiceTest {

    @Autowired
    private JogadaService jogadaService;

    @Test
    public void iniciarTest() {

        JogadaModel jogadaModel = jogadaService.iniciar(5);

        assertEquals(jogadaModel.getId().longValue(), 1L);
        assertEquals(jogadaModel.getLetrasInformadas(), "");
        assertEquals(jogadaModel.getQtdVidas(), 5);
        assertEquals(jogadaModel.getQtdTentativas(), 0);
        assertEquals(jogadaModel.getStatus(), Status.START);
    }

    @Test
    public void tentarLetraTest() {

        jogadaService.iniciar(5);

        JogadaModel jogadaModel = jogadaService.tentarLetra(1L, "T");

        assertEquals(jogadaModel.getLetrasInformadas(), "T");
        assertEquals(jogadaModel.getQtdTentativas(), 1);
        assertEquals(jogadaModel.getStatus(), Status.RUNNING);
    }

    @Test
    public void cancelarTest() {

        jogadaService.iniciar(5);

        JogadaModel jogadaModel = jogadaService.cancelar(1L);

        assertEquals(jogadaModel.getStatus(), Status.CANCELLED);
    }
}