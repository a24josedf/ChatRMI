# Chat RMI P2P

Proyecto de comunicacion distribuida mediante Java RMI.

El proyecto contiene una sola aplicacion. Para cumplir el enunciado, esta aplicacion se ejecuta dos veces en dos JVM distintas. Cada instancia actua a la vez como:

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

### Nodo 1

Ejecuta la aplicacion con estos argumentos:

```text
Usuario1 1099 ChatUsuario1 localhost 1100 ChatUsuario2
```

### Nodo 2

Ejecuta otra vez la misma aplicacion con estos argumentos:

```text
Usuario2 1100 ChatUsuario2 localhost 1099 ChatUsuario1
```

## Uso

1. Arranca las dos instancias.
2. Cada una publica automaticamente su servicio local.
3. Pulsa `Conectar` en las dos ventanas.
4. Escribe mensajes desde cualquiera de las dos ventanas.

Si no se pasan argumentos, la aplicacion arranca como `Usuario1`.
