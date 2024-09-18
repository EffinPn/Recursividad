import java.io.File;
import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {


        // 1 -> Obtener todos los nombre de los ficheros del directorio llamado directorio

        //Primero creamos un objeto File al que le pasamos la ruta del directorio.
        File fichero = new File(".\\directorio");

        //A continuación, utilizamos el método listFiles para obtener la lista de ficheros,
        //la variable es de tipo File[] porque este método devuelve un array.
        File[] listado = fichero.listFiles();

        // Y finalmente recorremos dicha lista con un for, imprimiendo los nombres con getName().
        for (int i = 0; i < listado.length; i++) {
            System.out.println(listado[i].getName());
        }


        // 2 -> Crear una subcarpeta en el directorio llamado directorio y crear en ella un fichero.

        // subc le está indicando la ruta a mkdir, ficheroEnSub se la indica a createNewFile.
        File subc = new File(".\\directorio\\subcarpeta");
        File ficheroEnSub = new File(".\\directorio\\subcarpeta\\fichero.txt");

        //Primero crearemos la subcarpeta, utilizando para ello mkdir.
        if (subc.mkdir()) {
            System.out.println("Subirectorio creado con éxito");
        } else {
            System.out.println("Fallo al crear el subdirectorio");
        }

        //Y después crearemos el fichero con createNewFile, el cual va en un try&catch porque requiere manejo de excepciones.
        try {
            if (ficheroEnSub.createNewFile()) {
                System.out.println("Fichero creado con éxito.");
            }
        } catch (Exception e) {
            System.out.println(e + " Fallo al crear el fichero");
        }


        // 2.1 -> Obtener todos los nombre de los ficheros del directorio llamado directorio y el subdirectorio creado.
        //Este paso lo omito porque sólo sería repetir los pasos de la parte 1.

        // 3 -> Listar el nombre de todos los ficheros del SISTEMA ( C:/User o /Users )
        //Para este he hecho dos métodos separados, no quería meter tanto código en el main:

        String ruta = "C:\\Users";
        listaSis(ruta);

    }

    public static void listaSis(String ruta){
        //Creamos un objeto File al cual le pasamos de argumento el String de este método.
        File carpeta = new File(ruta);
        /*Comprobamos que esa ruta sea válida, si lo es, llamamos a recursivo, que es el que hará todo el trabajo,
          este método está para asegurarse de que la ruta sea una carpeta existente:*/
        if(carpeta.exists() && carpeta.isDirectory()){
            recursivo(carpeta);
        } else {
            System.out.println("Carpeta no válida");
        }
    }

    public static void recursivo(File carpeta){

        File[] archivos = carpeta.listFiles();
        //Este if se asegura de que el objeto File sea válido, si lo es, comienza un for each para imprimir la lista:
        if(carpeta != null){
            for(File fichero : archivos){
                /*Este if comprueba si fichero apunta a un directorio, si lo hace, imprime el nombre del directorio
                  con getName y el método procede a llamarse a sí mismo para ese directorio*/
                if(fichero.isDirectory()){
                    System.out.println("Carpeta: " + fichero.getName());
                    recursivo(fichero);
                }
                //De lo contrario simplemente se imprime el nombre del archivo antes de pasar al siguiente.
                else{
                    System.out.println("Archivo: " + fichero.getName());
                }
            }
        }
    }
}