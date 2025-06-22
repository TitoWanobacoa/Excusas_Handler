package servicios;

public class EmailSenderFake implements IEmailSender {
    @Override
    public void enviarEmail(String destinatario, String remitente, String asunto, String cuerpo) {
        System.out.println("[Email FAKE]");
        System.out.println("De: " + remitente);
        System.out.println("Para: " + destinatario);
        System.out.println("Asunto: " + asunto);
        System.out.println("Mensaje: " + cuerpo);
        System.out.println("-----------------------------------");
    }
}
