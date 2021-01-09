package segurosxy.seguro;

import lombok.ToString;
import segurosxy.modelos.Certificado;
import segurosxy.modelos.Poliza;
import segurosxy.modelos.interfaces.ICertificado;
import segurosxy.modelos.interfaces.IPoliza;
import segurosxy.modelos.patrones.IClienteObserver;
import segurosxy.modelos.patrones.ISeguroObservable;

import java.util.List;

@ToString
public abstract class Seguro implements ISeguroObservable {

    protected ICertificado certificado;
    protected Poliza poliza;
    protected String nivelRiesgo = "NINGUNO";
    protected List<IClienteObserver> contratantes;

    public Seguro() {
        this.certificado = new Certificado();
    }

    public ICertificado getCertificado() {
        return certificado;
    }

    public void setCertificado(Certificado certificado) {
        this.certificado = certificado;
    }

    public IPoliza getPoliza() {
        return poliza;
    }

    public void setPoliza(Poliza poliza) {
        this.poliza = poliza;
    }

    public String getNivelRiesgo()  {
        return this.nivelRiesgo;
    }

    public String infoSeguroCsv() {
        return String.join(",","certificado", "poliza", "nivelRiesgo");
    }

    public void setSumaAsegurada(final Double suma) {

        this.poliza.setSumaAsegurada(suma);
        // notify contratante
        //System.out.println("***********************************************************");
        System.out.println("[Seguro] Se modifico la Suma Asegurada, notificando... ");
        //System.out.println("***********************************************************");
        for( IClienteObserver a : this.contratantes) {
            a.notifica();
        }
    }

    public void addObserver(IClienteObserver observer) {
        this.contratantes.add( observer );
    }

    public void removeObserver(IClienteObserver observer) {
        this.contratantes.remove( observer );
    }

    public abstract String detalleSeguro();
    public abstract void calcularRiesgo();

}
