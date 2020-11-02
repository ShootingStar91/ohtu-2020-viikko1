package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void kahdenParametrinKonstruktorinSaldoToimii() {
        varasto = new Varasto(10, 5);
        assertEquals(5, varasto.getSaldo(), 0.0);
        
    }

    @Test
    public void kahdenParametrinKonstruktorinSaldoEiNegatiivinen() {
        varasto = new Varasto(10, -5);
        assertEquals(0, varasto.getSaldo(), 0.0);
        
    }
    @Test
    public void kahdenParametrinKonstruktorinSaldoEiTilavuuttaIsompi() {
        varasto = new Varasto(10, 15);
        assertEquals(10, varasto.getSaldo(), 0.0);
        
    }
    
    @Test
    public void eiVoiLisataVarastoonLiikaa() {
        varasto.lisaaVarastoon(100.0);
        assertEquals(10.0, varasto.getSaldo(), 0.0);
    }
    @Test
    public void eiVoiLisataVarastoonNegatiivista() {
        varasto.lisaaVarastoon(-100.0);
        assertEquals(0.0, varasto.getSaldo(), 0.0);
    }
    @Test
    public void eiVoiOttaaVarastostaLiikaa() {
        double palautus = varasto.otaVarastosta(100.0);
        assertEquals(0.0, palautus, 0.0);
        assertEquals(0.0, varasto.getSaldo(), 0.0);
    }    @Test
    public void eiVoiOttaaVarastostaNegatiivista() {
        double palautus = varasto.otaVarastosta(-100.0);
        assertEquals(0.0, palautus, 0.0);
        assertEquals(0.0, varasto.getSaldo(), 0.0);
    }
    @Test
    public void konstruktoriTilavuusVahintaanNolla() {
        varasto = new Varasto(-0.1);
        assertEquals(0.0, varasto.getTilavuus(), 0.0);
    }
    
    @Test
    public void toStringToimiiOikein() {
        assertTrue(varasto.toString().equals("saldo = " + varasto.getSaldo() + 
                ", vielä tilaa " + varasto.paljonkoMahtuu()));
    }
    @Test
    public void kahdenParametrinKonstruktorinTilavuusVahintaanNolla() {
        varasto = new Varasto(-1, 0);
        assertTrue(varasto.getTilavuus() == 1.0);
    }
    
}