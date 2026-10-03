package motor;
import java.util.Map;

public interface Expresion {
    double evaluar(Map<String, Double> contexto);
}

class Numero implements Expresion {
    private double valor;
    public Numero(double valor) { this.valor = valor; }
    public double evaluar(Map<String, Double> contexto) { return valor; }
}

class Variable implements Expresion {
    private String nombre;
    public Variable(String nombre) { this.nombre = nombre; }
    public double evaluar(Map<String, Double> contexto) { 
        return contexto.getOrDefault(nombre, 0.0); 
    }
}

class Multiplicacion implements Expresion {
    private Expresion izq, der;
    public Multiplicacion(Expresion izq, Expresion der) {
        this.izq = izq; this.der = der;
    }
    public double evaluar(Map<String, Double> contexto) {
        return izq.evaluar(contexto) * der.evaluar(contexto);
    }
}
