package motor;

public class Main {
    public static void main(String[] args) {
        // 1. Mediator coordina la interfaz
        DocumentEditorMediator mediator = new DocumentEditorMediator();
        SelectorDeFormato selector = new SelectorDeFormato(mediator);
        mediator.setSelector(selector);
        
        // Simula usuario eligiendo HTML
        selector.seleccionar("HTML");
        
        // 2. Builder + Bridge
        DocumentBuilder builder = new ReporteBuilder(mediator.getMotorActual());
        builder.addHeader("Reporte General");
        builder.addParagraph("Doc secreto con datos calculados.");
        builder.addParagraph("Total: #{PRECIO_BASE * 1.19}"); 
        Documento doc = builder.getDocumento();
        
        // 3. Chain of Responsibility (con Interpreter integrado)
        ProcesadorHandler v = new ValidadorSintaxis();
        ProcesadorHandler f = new FiltroPalabras();
        ProcesadorHandler e = new EvaluadorHandler();
        
        v.setSiguiente(f);
        f.setSiguiente(e);
        
        // Ejecuta validaciones y traducciones
        v.procesar(doc);
        
        // 4. Exportar el HTML
        doc.exportar();
        
        // Cambiamos el formato desde el mediator
        System.out.println("\nCambiando formato desde la interfaz...");
        selector.seleccionar("PDF");
        doc.setMotor(mediator.getMotorActual());
        doc.exportar();
        
        // 5. Flyweight 
        System.out.println("\n--- Uso de Flyweight para caracteres ---");
        FabricaElementos fabrica = new FabricaElementos();
        String palabra = "HOLA";
        for (int i = 0; i < palabra.length(); i++) {
            ElementoVisual ev = fabrica.getElemento(palabra.charAt(i));
            ev.renderizar(i * 10, 5, "negro");
        }
    }
}
