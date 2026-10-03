package motor;

public interface DocumentBuilder {
    void addHeader(String header);
    void addParagraph(String p);
    void addTable(String table);
    void addFooter(String footer);
    Documento getDocumento();
}

class ReporteBuilder implements DocumentBuilder {
    private Documento doc;
    
    public ReporteBuilder(RenderizadorEngine motor) {
        this.doc = new DocumentoPaginado(motor);
    }
    
    public void addHeader(String header) { doc.contenido += "# HEADER: " + header + "\n"; }
    public void addParagraph(String p) { doc.contenido += "Parrafo: " + p + "\n"; }
    public void addTable(String table) { doc.contenido += "Tabla: " + table + "\n"; }
    public void addFooter(String footer) { doc.contenido += "Footer: " + footer + "\n"; }
    
    public Documento getDocumento() {
        return this.doc;
    }
}
