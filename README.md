# Prueba para Mecalux

Esta prueba consiste en un parte back y otra front.

Para facilitar el desarrollo se ha subido el codigo a los siguientes repositorios de GitHub

```
https://github.com/arvak7/mecalux
https://github.com/arvak7/front-mecalux
```

Ante cualquier duda sobre los repositorios, el codigo o como arrancar, no duden en ponerse en contacto. 

## Backend
Se ha implementado con Java 17.
Para poder compilar es necesario tener configurado el JAVA_HOME:
```
JAVA_HOME=C:\Program Files\java\jdk-17.0.5
```
despues se podra crear el JAR con:
```shell
mvn clean package
```
Al compilar se lanzaran tambien varios tests.

Para solucionar el problema de CORS se ha creado un perfil 'development' que se llama por defecto. 
Por tanto una vez tienes el jar seria suficiente con:

```shell 
mvn spring-boot:run
```

## Frontend

Arrancar el front deberia ser mas sencillo, bastaria con :

```
ng build
ng serve
```

