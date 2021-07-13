## Preguntas

1. Diferencia entre interfaz y clase abstracta  
   * Una interfaz solo contiene atributos y métodos abstractos, los cuales no contienen ningún cuerpo, mientras que una clase abstracta puede o no contener métodos abstractos.
    * Una interfaz solo puede contener métodos abstractos públicos, mientras que en una clase abstracta los métodos pueden ser públicos o protegidos.
   * Una clase abstracta solo puede heredar de una clase mientras que una interfaz puede extender de más de una.
   * Una clase abstracta puede extender o implementar cualquier clase, mientras que una interfaz solamente puede extender o implementar otras interfaces.  
2. Diferencia entre LinkedList y ArrayList
   * ArrayList internamente utiliza un array dinámico mientras que una LinkedList internamente utiliza una lista doblemente ligada.
   * Un ArrayList es mejor para almacenar y acceder a datos, mientras que LinkedList es mejor para manipular datos.
   * Un ArrayList solo implementa de List, mientras que un LinkedList implementa de List y Deque.
3. Cómo funciona el recolector de basura
   * La recolección de basura es el proceso de recuperar automáticamente la memoria no utilizada en tiempo de ejecución. En otras palabras, es una forma de destruir los objetos no utilizados. El recolector de basura de Java es un proceso automático no es necesario ser ejecutado porque se encuentra en la máquina virtual JVM que lo hace independiente de la plataforma sistema operativo. El funcionamiento básico es liberar la memoria Heap utilizada o desreferenciada de forma implícita o explicita. 
4. Escribe la función de POW sin usar multiplicación/división ni las funciones de las mismas
 ```java
class PowDemo {
   public static void main(String[] args) {

      System.out.println(potencia(2, 3));
   }

   static int potencia(int base, int exponente) {
      if (exponente > 0)
         return producto(base, potencia(base, exponente - 1));
      else
         return 1;
   }

   static int producto(int a, int b) {
      if (b > 0)
         return (a + producto(a, b - 1));
      else
         return 0;
   }
}
 ```
 
## Problema

Tenemos un partner el cual nos manda notificación de pago, el endpoint que
tenemos con este partner no debe de demorar en responder más de 20 ms esto
para cumplir 50 TPS (transaction per seconds) que nos exige el partner, tomando
en cuenta que cuando recibimos la notificación de pago, nosotros tenemos que
usar unos APIs en JSON que demoran en contestarnos 50ms.
¿Cómo podemos solucionar este problema para cumplir con los 20ms?
Explica cómo lo harías, qué tecnologías utilizarías y si es posible muestra la parte crucial de este código.

**Respuesta**

Si la API es de terceros, eso limita de alguna manera el alcance de las posibles optimizaciones. Pero se pueden tomar las siguientes consideraciones:

* A nivel de programación se debe de utilizar alguna técnica de optimización de código, programación reactiva o asincrónica, además de usar algún método de programación paralela o multihilo.

* Realizar la menor cantidad posible de llamadas a la API. Hacer una lectura detenida de la documentación de la API y asegúrese de conocer todos los detalles de las API que necesita. Usar paginación, filtros, búsquedas, cache, etc. 
  
* El almacenamiento en caché es una forma muy eficaz de reducir el número de llamadas a la API. Por lo tanto, los datos recibidos del servidor que se puedan almacenar en caché durante cierto tiempo. Se puede utilizar alguna tecnología como Memcache o Redis.

* Request / Response: Utilizar un serializador JSON y las técnicas de compresión gZip para los datos transmitidos a través de la web.

* En el servido, tener en cuenta los detalles técnicos del servidor tanto de hardware y software, de red y geolocalización del servidor.

## Entregable

Mandar código fuente y scripts necesarios para validar el desarrollo en un zip más las instrucciones

A) Objetivo
Crear un endpoint con JAVA (spring boot java) utilizando SOAP para consumir una
API tipo rest ej. (https://pokeapi.co/api/v2/pokemon/{pokemon}/)
El desarrollo consiste en consumir la API rest de https://pokeapi.co/ y con base en lo que retorna crear un endpoint en SOAP con los siguientes métodos:

* abilities
* base_experience
* held_items
* id
* name
* location_area_encounters

Todos los métodos tienen que aceptar como parámetro como String al pokemon ya que la búsqueda tiene ser en tiempo real de https://pokeapi.co/

B) Se tiene que guardar en una base de datos h2, mysql, etc. Los request de las
peticiones guardando como variables:

* ip de origin
* fecha de request
* método que se ejecuta

Se puede utilizar: mybatis, hibernate, spring jdbc, como extra se puede crear un
pool deconexiones

# Desarrollo

## Ejecución 
El proyecto ejecutar con IntellijIdea o usar maven para construir el proyecto y generar el jar.

Dentro de la carpeta del proyecto
 ```
 cd pakeapi-demo
 ```

Para construir el proyecto ejecutar
```
mvn package
 ```

Ejecutar aplicación 
```
java -jar target/pokeapi-demo-0.0.1-SNAPSHOT.jar
```

Para ejecutar una prueba
```
curl --header "content-type: text/xml" -d @request.xml http://localhost:8080/ws > output.xml
```
