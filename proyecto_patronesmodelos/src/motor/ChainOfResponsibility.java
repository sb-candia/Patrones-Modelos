package motor;
import java.util.HashMap;
import java.util.Map;

public abstract class ProcesadorHandler {
    protected ProcesadorHandler siguiente;
    
    public void setSiguiente(ProcesadorHandler siguiente) {
        this.siguiente = siguiente;
    }
    
    public abstract void procesar(Documento doc);
}

class ValidadorSintaxis extends ProcesadorHandler {
    public void procesar(Documento doc) {
        System.out.println(">> Validando sintaxis...");
        if(doc.contenido.contains("<error>")) {
            System.out.println("Error de sintaxis.");
            return;
        }
        if(siguiente != null) siguiente.procesar(doc);
    }
}

class FiltroPalabras extends ProcesadorHandler {
    public void procesar(Documento doc) {
        System.out.println(">> Filtrando palabras prohibidas...");
        doc.contenido = doc.contenido.replace("secreto", "****");
        if(siguiente != null) siguiente.procesar(doc);
    }
}

class EvaluadorHandler extends ProcesadorHandler {
    public void procesar(Documento doc) {
        System.out.println(">> Evaluando expresiones...");
        if (doc.contenido.contains("#{PRECIO_BASE * 1.19}")) {
            Map<String, Double> ctx = new HashMap<>();
            ctx.put("PRECIO_BASE", 1000.0);
            
            Expresion var = new Variable("PRECIO_BASE");
            Expresion num = new Numero(1.19);
            Expresion mult = new Multiplicacion(var, num);
            
            double res = mult.evaluar(ctx);
            doc.contenido = doc.contenido.replace("#{PRECIO_BASE * 1.19}", String.valueOf(res));
        }
        if(siguiente != null) siguiente.procesar(doc);
    }
}
