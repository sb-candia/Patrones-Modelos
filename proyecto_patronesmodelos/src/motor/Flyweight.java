package motor;
import java.util.HashMap;
import java.util.Map;

// Elemento visual (Flyweight)
public class ElementoVisual {
    private char caracter;
    
    public ElementoVisual(char caracter) {
        this.caracter = caracter;
    }
    
    public void renderizar(int x, int y, String color) {
        System.out.println("Dibujando '" + caracter + "' en (" + x + "," + y + ") color: " + color);
    }
}

class FabricaElementos {
    private Map<Character, ElementoVisual> cache = new HashMap<>();
    
    public ElementoVisual getElemento(char c) {
        if (!cache.containsKey(c)) {
            cache.put(c, new ElementoVisual(c));
        }
        return cache.get(c);
    }
}
