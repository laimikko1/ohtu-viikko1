package ohtu.ohtuvarasto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VarastoTest {

    private static final Double largerTestnumber = 10.0;
    private static final Double negativeNumber = -5.0;
    private static final Double positiveTestnumber = 5.0;
    private static final Double veryLargeTestnumber = 20.0;
    private static final Double vertailuTarkkuus = 0.0001;
    Varasto varasto;
 
    @Before
    public void setUp() {
        varasto = new Varasto(largerTestnumber);
    }

    @Test
    public void konstruktoriLuoTyhjanVaikkaTilavuusNegatiivinen() {
        varasto = new Varasto(negativeNumber);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoTyhjanVaikkaTilavuusJaSaldoNegativiinen() {
        varasto = new Varasto(negativeNumber, negativeNumber);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriAsettaaSaldonldonKunYhtasuuriKuinTilavuus() {
        varasto = new Varasto(positiveTestnumber, positiveTestnumber);
        assertEquals(positiveTestnumber, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void saldoOnTilavuusKunSaldoIsompi() {
        varasto = new Varasto(positiveTestnumber, largerTestnumber);
        assertEquals(positiveTestnumber, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(largerTestnumber, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(positiveTestnumber);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(positiveTestnumber, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void eiLisaaJosLisaysNegatiivinen() {
        varasto.lisaaVarastoon(negativeNumber);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void suuriLisaysTayttaaSaldon() {
        varasto.lisaaVarastoon(veryLargeTestnumber);
        assertEquals(largerTestnumber, varasto.getSaldo(), vertailuTarkkuus);

    }

    @Test
    public void negatiivinenOttoEiMuutaSaldoa() {
        varasto.otaVarastosta(negativeNumber);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void IsoOttoNollaaSaldon() {
        varasto.otaVarastosta(veryLargeTestnumber);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(positiveTestnumber);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(positiveTestnumber, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(largerTestnumber);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(positiveTestnumber);

        varasto.otaVarastosta(positiveTestnumber);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(largerTestnumber, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void toStringOikein() {
        String testi = varasto.toString();

        assertEquals(testi, "saldo = " + varasto.getSaldo() + ", vielä tilaa " + varasto.getTilavuus());
    }

}
