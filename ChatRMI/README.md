# Chat RMI P2P

Proyecto de comunicacion distribuida mediante Java RMI.

El proyecto contiene una sola aplicacion. Para cumplir el enunciado, esta aplicacion se ejecuta dos veces en dos JVM distintas. En cada ventana el usuario introduce manualmente sus datos. Cada instancia actua a la vez como:

- servidor RMI: publica su propio objeto remoto para recibir mensajes;
- cliente RMI: localiza el objeto remoto de la otra instancia y le envia mensajes.

No hay un servidor central pasivo. Los dos programas son nodos autonomos.

## Clases principales

- `ChatRMI`: arranca la aplicacion.
- `ChatService`: interfaz remota que extiende `Remote`.
- `ChatServiceImpl`: objeto remoto que hereda de `UnicastRemoteObject`.
- `ChatController`: crea el registry, publica el servicio, busca el otro nodo y envia mensajes.
- `ChatJFrame`: interfaz grafica Swing hecha con estructura tipo editor grafico de NetBeans.
- `ChatConfig`: configuracion de cada nodo.

## Metodo remoto

```java
void receiveMessage(String from, String message) throws RemoteException;
```

## Ejecutar en NetBeans

Abre el proyecto:

```text
C:\Users\carlo\Documents\NetBeansProjects\ChatRMI
```

La clase principal es:

```text
com.mycompany.chatrmi.ChatRMI
```

### Datos para el nodo 1

En la primera ventana escribe:

```text
Usuario: Usuario1
Puerto local: 1099
Nombre local: ChatUsuario1
Host remoto: localhost
Puerto remoto: 1100
Nombre remoto: ChatUsuario2
```

### Datos para el nodo 2

En la segunda ventana escribe:

```text
Usuario: Usuario2
Puerto local: 1100
Nombre local: ChatUsuario2
Host remoto: localhost
Puerto remoto: 1099
Nombre remoto: ChatUsuario1
```

## Uso

1. Arranca las dos instancias.
2. Rellena los datos de cada ventana.
3. Pulsa `Publicar` en las dos ventanas.
4. Pulsa `Conectar` en las dos ventanas.
5. Escribe mensajes desde cualquiera de las dos ventanas.

Tambien se pueden seguir usando argumentos de ejecucion si se quiere, pero por defecto la ventana aparece vacia para que el usuario introduzca todos los datos.
