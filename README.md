# ChatRMI 

Aplicación de chat punto a punto desarrollada en Java usando RMI (*Remote Method Invocation*).

El proyecto contiene una única aplicación que se ejecuta dos veces en JVM distintas. Cada instancia actúa simultáneamente como servidor y cliente RMI: publica su propio objeto remoto para recibir mensajes y localiza el objeto remoto de la otra instancia para enviar mensajes.

## Funcionamiento

Cada nodo del chat:

- Crea o utiliza un `RMI Registry`.
- Publica un objeto remoto con `rebind`.
- Localiza el objeto remoto del otro nodo con `lookup`.
- Envía mensajes llamando al método remoto `receiveMessage`.
- Recibe mensajes y los muestra en la interfaz gráfica.

## Método remoto

```java
void receiveMessage(String from, String message) throws RemoteException;
```

## Ejecución

Ejecutar la misma aplicación dos veces.

### Nodo 1

```text
Usuario: Usuario1
Puerto local: 1099
Nombre local: ChatUsuario1
Host remoto: localhost
Puerto remoto: 1100
Nombre remoto: ChatUsuario2
```

### Nodo 2

```text
Usuario: Usuario2
Puerto local: 1100
Nombre local: ChatUsuario2
Host remoto: localhost
Puerto remoto: 1099
Nombre remoto: ChatUsuario1
```

## Uso

1. Abrir dos instancias de la aplicación.
2. Introducir los datos de cada nodo.
3. Pulsar `Publicar` en ambas ventanas.
4. Pulsar `Conectar` en ambas ventanas.
5. Escribir y enviar mensajes desde cualquiera de las dos instancias.

## Estructura del proyecto

```text
src/main/java/com/mycompany/chatrmi
├── ChatRMI.java
├── controller
│   └── ChatController.java
├── model
│   └── ChatConfig.java
├── rmi
│   ├── ChatService.java
│   └── ChatServiceImpl.java
└── view
    └── ChatJFrame.java
```

## Clases principales

- `ChatRMI`: clase principal de la aplicación.
- `ChatService`: interfaz remota que extiende `Remote`.
- `ChatServiceImpl`: implementación del objeto remoto.
- `ChatController`: gestiona la publicación, conexión y envío de mensajes.
- `ChatJFrame`: interfaz gráfica Swing.
- `ChatConfig`: almacena la configuración de cada nodo.
