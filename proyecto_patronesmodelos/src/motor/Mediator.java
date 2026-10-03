package motor;

public interface Mediator {
    void notificar(Componente emisor, String evento);
}

class DocumentEditorMediator implements Mediator {
    private SelectorDeFormato selector;
    private RenderizadorEngine motorActual;
    
    public void setSelector(SelectorDeFormato selector) { this.selector = selector; }
    
    public RenderizadorEngine getMotorActual() {
        return this.motorActual != null ? this.motorActual : new PdfRenderEngine();
    }
    
    public void notificar(Componente emisor, String evento) {
        if (emisor == selector && evento.equals("cambio")) {
            if(selector.getFormato().equals("HTML")) {
                motorActual = new HtmlRenderEngine();
            } else {
                motorActual = new PdfRenderEngine();
            }
            System.out.println("[Mediator] Formato cambiado a " + selector.getFormato());
        }
    }
}

abstract class Componente {
    protected Mediator mediator;
    public Componente(Mediator m) { this.mediator = m; }
}

class SelectorDeFormato extends Componente {
    private String formato = "PDF";
    public SelectorDeFormato(Mediator m) { super(m); }
    
    public void seleccionar(String nuevoFormato) {
        this.formato = nuevoFormato;
        mediator.notificar(this, "cambio");
    }
    public String getFormato() { return formato; }
}
