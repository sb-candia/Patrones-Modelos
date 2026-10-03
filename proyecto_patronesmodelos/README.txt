# Actividad: Integración de patrones

Para compilar y ejecutar el proyecto, ubícate en la carpeta "src" y ejecuta:
1. javac motor/*.java
2. java motor.Main

DIAGRAMA UML (Estructura de Clases Resumida):
- Bridge: Documento <-> RenderizadorEngine (HtmlRenderEngine, PdfRenderEngine)
- Builder: DocumentBuilder -> ReporteBuilder
- Flyweight: FabricaElementos -> ElementoVisual (compartido por carácter)
- Chain of Responsibility: ProcesadorHandler (Validador, Filtro, Evaluador)
- Interpreter: Expresion (Numero, Variable, Multiplicacion) - Llamado dentro del Chain.
- Mediator: DocumentEditorMediator coordina SelectorDeFormato y determina el Renderizador.
