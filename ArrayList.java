import java.util.Iterator; 
public class ArrayList<E> implements Lista<E> {
    // Tamanio maximo inicial por defecto del arreglo
    private static final int MAX = 5;
    // Indica cuantos elementos tiene actualmente la lista
    private int indice = 0;
    // Arreglo donde se almacenan los datos
    private Object[] datos = null; 
    // Constructor por defecto.
    // Crea una lista con tamanio inicial MAX.
    public ArrayList() {
        this(MAX);
    }
    // Constructor que permite indicar el tamanio inicial del arreglo.
    public ArrayList(int tam) {
        // Si el tamanio es negativo, se lanza una excepcion.
        if (tam < 0) {
            throw new IllegalArgumentException();
        }
        // Se crea el arreglo con el tamanio indicado.
        datos = new Object[tam];
    }
    // Metodo privado que limpia las referencias del arreglo.
    // Esto ayuda a que los objetos puedan ser eliminados por el Garbage Collector.
    private void asegurarGC() {
        for (int i = 0; i < datos.length; i++) {
            datos[i] = null;
        }
    }


/////////////////////////////////////////////////////////////////////////

    @Override
    public void agregarFinal(E e) {
        Object[] aux = null;
        // Si el arreglo ya casi esta lleno, se crea uno mas grande.
        if (!(indice < datos.length - 1)) {
            // Nuevo arreglo con crecimiento del 50%.
            aux = new Object[datos.length + datos.length / 2];
            // Copia los datos del arreglo viejo al nuevo.
            System.arraycopy(datos, 0, aux, 0, datos.length);
//arreglo original,desde que posicion empieza a copiar, arreglo nuevo,en que posicion del nuevo empieza a pegar, cuantos elementos copia.
            // Limpia las referencias del arreglo anterior.
            asegurarGC();
            // El arreglo principal ahora apunta al arreglo nuevo.
            datos = aux;
        }
        // Se guarda el elemento en la posicion actual.
        datos[indice] = e;
        // Se incrementa el numero de elementos.
        indice++;
    }

/////////////////////////////////////////////////////////////////////////

    // Agrega un elemento al inicio de la lista.
    @Override
    public void agregarInicio(E e) {
        Object[] auxobj = null;
        // Imprime el numero actual de elementos.
        System.out.println(indice);
        // Si todavia hay espacio, recorre los elementos una posicion a la derecha.
        if (indice < datos.length - 1) {
            System.arraycopy(datos, 0, datos, 1, indice + 1);
        } else {
            // Si no hay espacio, crea un arreglo mas grande.
            auxobj = new Object[datos.length + datos.length / 2];
            // Copia los elementos al nuevo arreglo desde la posicion 1.
            System.arraycopy(datos, 0, auxobj, 1, datos.length);
            // Limpia las referencias del arreglo anterior.
            asegurarGC();
            // El arreglo principal ahora apunta al arreglo nuevo.
            datos = auxobj;
        }
        // Coloca el nuevo elemento al inicio.
        datos[0] = e;
        // Aumenta el contador de elementos.
        indice++;
    }

////////////////////////////////////////////////////////////////////////

    // Indica si la lista esta vacia.
    @Override
    public boolean esVacia() {
        return indice == 0;
    }
    // Devuelve cuantos elementos tiene la lista.
    @Override
    public int numElementos() {
        return indice;
    }
    // Limpia la lista.
    @Override
    public void limpiarLista() {
        // Reinicia el contador de elementos.
        indice = 0;
        // Borra las referencias de los objetos almacenados.
        asegurarGC();
    }

////////////////////////////////////////////////////////////////////////

@Override
public void agregarElemento(E e) {
agregarFinal(e);
}

///////////////////////////////////////////////////////////////////////

@Override
public void agregarPosicion(E e, int pos) {
Object[] aux = null;

if(pos>=0 && pos<=indice){
if(indice==datos.length){
aux=new Object[(int)(1.5*datos.length)];
System.arraycopy(datos, 0, aux, 0, pos);
System.arraycopy(datos, pos, aux,  pos+1, indice-pos);
datos=aux;
} else {
System.arraycopy(datos, pos, datos, pos+1, indice-pos);
}
datos[pos]=e;
indice++;
} else {
throw new IndexOutOfBoundsException();
}
}

/////////////////////////////////////////////////////////////////////////

@Override
public E eliminarElementoFinal() {
if (indice == 0) {
throw new IndexOutOfBoundsException();
}
E eliminado = (E) datos[indice - 1];
datos[indice-1] = null;
indice--;
return eliminado;
}

////////////////////////////////////////////////////////////////////////

@Override
public E eliminarElemento() {
return eliminarElementoFinal();
}

///////////////////////////////////////////////////////////////////////

@Override
public E eliminarElementoInicio() {
if (indice == 0) {
throw new IndexOutOfBoundsException();
}
E eliminado = (E) datos[0];
for (int i=0; i<indice-1; i++) {
datos[i] = datos[i+1];
}

datos[indice-1] = null;
indice--;
return eliminado;
}

//////////////////////////////////////////////////////////////////////

@Override
public E eliminarElementoPosicion(int posicion) {
if (indice == 0) {
throw new IndexOutOfBoundsException();
}
if (posicion < 0 || posicion >= indice) {
throw new IndexOutOfBoundsException();
}
if (posicion == 0) {
return eliminarElementoInicio();
}
if (posicion == indice - 1) {
return eliminarElementoFinal();
}
E eliminado = (E) datos[posicion];
for (int i = posicion; i < indice - 1; i++) {
datos[i] = datos[i + 1];
}
datos[indice - 1] = null;
indice--;
return eliminado;
}

///////////////////////////////////////////////////////////////////////
@Override
public E[] convertirArreglo() {
Object[] aux = new Object[indice];

for (int i = 0; i < indice; i++) {
aux[i] = datos[i];
}
return (E[]) aux;
}

/////////////////////////////////////////////////////////////////////

@Override
public E consultar(int posicion) {
if (posicion < 0 || posicion >= indice) {
throw new IndexOutOfBoundsException();
}

return (E) datos[posicion];
}


/////////////////////////////////////////////////////////////////////
    //Un Iterator : Permite recorrer la lista usando un Iterator.
        // Un Iterator es un objeto que permite recorrer una colección
    // elemento por elemento, sin necesidad de acceder directamente
    // a las posiciones del arreglo.
    // 
    // En este caso, el Iterator permitirá recorrer los elementos
    // guardados en nuestra lista ArrayList usando un ciclo for-each,
    // por ejemplo:
    //
    // for(E elemento : lista) {
    //     System.out.println(elemento);
    // }
    //
    // Para que esto funcione, la clase debe proporcionar el método iterator().

    // Este método permite recorrer la lista usando un Iterator.
    @Override
    public Iterator<E> iterator() {

        // Se crea y se regresa un nuevo Iterator.
        // Este Iterator sabe cómo recorrer los datos guardados
        // dentro del arreglo interno llamado datos.
        //
        // new Iterator<E>() { ... }
        // significa que estamos creando una clase anónima,
        // es decir, una clase sin nombre que implementa Iterator.
        return new Iterator<E>() {

            // Esta variable indica la posición actual del recorrido.
            // Inicia en 0 porque los arreglos en Java comienzan
            // en la posición 0.
            //
            // Ejemplo:
            // datos[0], datos[1], datos[2], ...
            int i = 0;

            // Este método pregunta si todavía quedan elementos
            // por recorrer en la lista.
            @Override
            public boolean hasNext() {
                // indice representa la cantidad de elementos reales
                // que tiene la lista.
                //
                // i representa la posición actual del Iterator.
                //
                // Mientras i sea menor que indice, significa que
                // todavía hay elementos disponibles.
                //
                // Ejemplo:
                // Si indice = 3, hay elementos en:
                // datos[0], datos[1] y datos[2]
                //
                // Si i = 0: 0 < 3 → true
                // Si i = 1: 1 < 3 → true
                // Si i = 2: 2 < 3 → true
                // Si i = 3: 3 < 3 → false
                //
                // Cuando devuelve false, el recorrido termina.
                return i < indice;
            }
            // Este método devuelve el siguiente elemento de la lista.
            @Override
            public E next() {
                // Como el arreglo datos fue declarado como Object[],
                // puede guardar objetos de cualquier tipo.
                //
                // Sin embargo, la lista es genérica: ArrayList<E>.
                // Eso significa que debe regresar elementos del tipo E.
                //
                // Por eso se hace una conversión:
                // de Object a E.
                //
                // Ejemplo:
                // Si la lista es ArrayList<String>,
                // entonces E representa String.
                @SuppressWarnings("unchecked")
                E aux = (E) datos[i];
                // Después de guardar el elemento actual en aux,
                // se aumenta i para avanzar a la siguiente posición.
                //
                // Ejemplo:
                // Si i valía 0, ahora valdrá 1.
                // Si i valía 1, ahora valdrá 2.
                i++;

                // Se regresa el elemento que se obtuvo antes de avanzar.
                //
                // Aunque i ya cambió, aux conserva el dato anterior.
                //
                // Ejemplo:
                // Si datos[0] era "Ana", se regresa "Ana".
                // En la siguiente llamada a next(), se regresará datos[1].
                return aux;
            }
        };
    }
}
