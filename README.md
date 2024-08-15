# projectmocha

Proyecto de un editor de codigo hecho en javaFX, para que se tenga una 
opcion nativa y de codigo abierto para la comunidad

##Mocha Editor 1.0.0

El editor cuenta con la funcionalidad senciolla de un block de notas (incluso mas reducido) 
pero se espera que continue evolucionando para volverse un editor mas completo, esta version cuenta 
con la posibilidad de abrir un archivo existen o crear uno nuevo, editar el contenido del 
archivo y guardarlo.

Aun no se cuenta con un ejecutable o un empaquetado por lo que se puede 
probar el proyecto de la siguiente manera.

comandos necesarios: 

### compilar
javac --module-path /usr/share/openjfx/lib --add-modules javafx.controls ProjectMocha.java

### ejecutar
java --module-path /usr/share/openjfx/lib --add-modules javafx.controls ProjectMocha

requerimientos en el sistema:

tener instalado el jdk de java
tener instalado fx
