# projectmocha

Proyecto de un editor de codigo hecho en javaFX, para que se tenga una 
opcion nativa y de codigo abierto para la comunidad

### Change Log

# Mocha Editor 1.10.0 (onDevelop) 15/09/204

Se esta implementando un nuevo componente para dibujar el codigo del archivo sin la ayuda de un text area ya que la misma no soporta cosas como coloreado de sintaxis, auto completado y cosas asi, por eso se esta implementando un componente custom para trabajar con el codigo.

La base es usar un elemento textFlow de javafx para que se llene con elementos text que presentan el codigo, se implemento una lista ligada para almacenar el codigo de un archivo la cual es la clase CodeLinkedList y se tiene la clase CodeNode, esto deberia mejorar el almacenamiento del archivo. 

Actualmente el componente codeArea esta muy basico ya que apenas deja abrir el archivo, obtener el codigo del archivo y escribir pero solo en la primera linea de texto, aun falta mucho para que funcione como una texteArea.

# Mocha Editor 1.5.0 (onDevelop) 18/08/2024

Se agrego la posibilidad de abrir diferentes archivos desde el editor, ahora se muestran los archivos 
en forma de tabs que se aparecen mas a los editores profecionales como vs code o atom, el guardado de archivos se asocia a la tab que esta siendo interactuada o esta seleccionada en ese momento. 

Se cambio la estructura de la representacion del archivo abierto, ahora se tiene un objeto que almacena la informacion del archivo y su estado, se tiene una clase llamada FileWindow que es la encargada de mantener el estado del archivo que se abrio y la realcion de la tab y el archivo que 
se tiene abierto en ese tab.

Se agrego un manejo de erroes mas robusto y ahora se propaga la exception que ocurre al abrir o cerrar un archivo, esto se implemento en la clase FileManager que es la encargada de manejar la apertura 
y escritura de archivos y contiene una funcion para mostrar un error al momento de manejar archivos.

## Mocha Editor 1.0.0

El editor cuenta con la funcionalidad sencilla de un block de notas (incluso mas reducido) 
pero se espera que continue evolucionando para volverse un editor mas completo, esta version cuenta 
con la posibilidad de abrir un archivo existen o crear uno nuevo, editar el contenido del 
archivo y guardarlo.

Aun no se cuenta con un ejecutable o un empaquetado por lo que se puede 
probar el proyecto compilando y ejecutando la aplicacion por comandos.

comandos necesarios: 

### compilar
javac --module-path /usr/share/openjfx/lib --add-modules javafx.controls ProjectMocha.java

### ejecutar
java --module-path /usr/share/openjfx/lib --add-modules javafx.controls ProjectMocha

### Requerimientos en el sistema:

tener instalado el jdk de java
tener instalado jdkfx

## Proximas actualizaciones 

El proyecto tiene como objetivo volverse un editor mas funcional como lo es notePad++ o 
mas parecido a vs code, por lo que se espera que poco a poco tome la forma de estos editores 
por el momento estas son algunas de las actualizaciones que se piensan agregar:

- Poder abrir carpetas/proyectos y ver los archivos disponibles
- Tener multiples archivos abiertos en el editor
- Inclusion de comandos
