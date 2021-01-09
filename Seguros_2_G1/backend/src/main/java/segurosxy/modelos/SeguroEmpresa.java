package segurosxy.modelos;

import segurosxy.modelos.interfaces.ICobertura;
import segurosxy.modelos.interfaces.INivelRiesgo;
import segurosxy.modelos.patrones.ICommand;
import segurosxy.seguro.Seguro;
import segurosxy.seguro.vehiculo.model.SeguroVehicular;

public class SeguroEmpresa extends SeguroVehicular implements INivelRiesgo  {

    private ICobertura cobertura;
    private ICommand command;

    public SeguroEmpresa(String marca)    {

        super();
        this.getVehiculo().setMarca(marca);
    }

    @Override
    public String detalleSeguro() {
        return null;
    }

    public void calcularRiesgo() {

        if (this.getVehiculo().getMarca().equals("Minera XYZ")) {
            this.nivelRiesgo = "ALTO";
        }
        else {
            this.nivelRiesgo = "BAJO";
        }

    }


    public void setCommand(ICommand command){
        this.command = command;
    }

    public void ejecutarAccion(Beneficiario beneficiario){
        this.command.ejecutar(beneficiario);
    }

}
