public class App {
    public static void main(String[] args) throws Exception {
ArrayList<String> listaArray = new ArrayList<>();

// Agregar elementos
System.out.println("agregarElementos");
listaArray.agregarElemento("Carlos");

System.out.println("agregarInicio");
listaArray.agregarInicio("Ana");

System.out.println("agregarFinal");
listaArray.agregarFinal("Luis");

System.out.println("agregarPosicion");
listaArray.agregarPosicion("María", 1);

// Consultar
System.out.println("consultar");
System.out.println("Elemento en la posición 1: "
+ listaArray.consultar(1));

// Número de elementos
System.out.println("numElementos");
System.out.println("Número de elementos: "
+ listaArray.numElementos());

// ¿Está vacía?
System.out.println("esVacia");
System.out.println("¿Está vacía?: "
+ listaArray.esVacia());

// Convertir a arreglo
System.out.println("convertirArreglo");
String[] arreglo = listaArray.convertirArreglo();

System.out.println("Contenido del arreglo:");
for (String s : arreglo) {
System.out.println(s);
}

// Eliminar elementos
System.out.println("eliminarElemento");
System.out.println("Eliminar cualquiera: "
+ listaArray.eliminarElemento());

System.out.println("eliminarElementoInicio");
System.out.println("Eliminar inicio: "
+ listaArray.eliminarElementoInicio());

System.out.println("eliminarElementoFinal");
System.out.println("Eliminar final: "
+ listaArray.eliminarElementoFinal());

// Agregamos más para probar eliminar por posición
System.out.println("agregarFinal");
listaArray.agregarFinal("Pedro");
listaArray.agregarFinal("José");

System.out.println("eliminarElemento");
System.out.println("Eliminar posición 1: "
+ listaArray.eliminarElementoPosicion(1));

// Vaciar la lista
System.out.println("limpiarLista()");
listaArray.limpiarLista();

System.out.println("esVacia");
System.out.println("¿Está vacía al final?: "
+ listaArray.esVacia());
}
}
