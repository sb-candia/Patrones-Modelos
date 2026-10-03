package motor;

public interface RenderizadorEngine {
    void renderizar(String contenido);
}

class PdfRenderEngine implements RenderizadorEngine {
    public void renderizar(String contenido) {
        System.out.println("--- EXPORTANDO A PDF ---");
        System.out.println(contenido);
    }
}

class HtmlRenderEngine implements RenderizadorEngine {
    public void renderizar(String contenido) {
        System.out.println("--- EXPORTANDO A HTML ---");
        System.out.println("<html><body>\n" + contenido + "</body></html>");
    }
}

abstract class Documento {
    protected RenderizadorEngine motor;
    public String contenido = "";
    
    public Documento(RenderizadorEngine motor) {
        this.motor = motor;
    }
    
    public void setMotor(RenderizadorEngine motor) {
        this.motor = motor;
    }
    
    public abstract void exportar();
}

class DocumentoPaginado extends Documento {
    public DocumentoPaginado(RenderizadorEngine motor) { super(motor); }
    public void exportar() { motor.renderizar(contenido); }
}
