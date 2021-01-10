package segurosxy.cliente.model;

import segurosxy.modelos.patrones.CorreoMediator;

public class Agente extends Persona {

    public Agente(String nombre) {
        super(nombre);
    }

    public Agente(String nombre, CorreoMediator correoMediator) {
        super(nombre, correoMediator);
    }

    @Override
    public void notifica() {
        try {
            System.out.println("[Agente] Notificando al Agente " + getNombre());
        }
        catch(Throwable t) {
            System.out.println("[Agente] Notificacion con error" + t.getMessage() );
        }
        enviarCorreo();
    }
    public void enviarCorreo(){
        correoMediator.setServidorCorreoSMTP("smtp.gmail.com");
        correoMediator.enviaCorreoSMTP(this);
    }
}
