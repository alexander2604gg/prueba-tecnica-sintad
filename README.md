# Prueba Técnica

Este documento describe los aspectos principales de la implementación de la prueba técnica, incluyendo los endpoints de la API, manejo de tokens JWT, uso de Spring HATEOAS, paginación de entidades, y más.

## 1. Endpoints en Swagger

A continuación se muestran los diagramas del flujo de los endpoints disponibles en la API:

![Diagrama del flujo de los endpoints 1](imagenes/endpoints-1.jpeg)
![Diagrama del flujo de los endpoints 2](imagenes/endpoints-2.jpeg)

---

## 2. Json Web Token (JWT)

La autenticación se maneja mediante tokens JWT. A continuación se muestra un diagrama que ilustra su funcionamiento:

![Diagrama del flujo de JWT](imagenes/jwt.jpeg)

---

## 3. Respuesta del JSON con Spring HATEOAS

El siguiente diagrama muestra cómo se estructura la respuesta JSON utilizando Spring HATEOAS para agregar enlaces de recursos relacionados:

![Diagrama de Spring HATEOAS](imagenes/hateoas.jpeg)

---

## 4. Paginación para Entidades

Para manejar grandes volúmenes de datos, se implementó la paginación. A continuación se presentan los diagramas que explican cómo se implementa y visualiza la paginación:

![Diagrama de paginación 1](imagenes/paginacion-1.jpeg)
![Diagrama de paginación 2](imagenes/paginacion-3.jpeg)

---

## 5. Eliminar Entidades

Opté por **no utilizar el método eliminar** en las entidades, ya que esta acción podría afectar información sensible. En lugar de ello, se opta por deshabilitar el estado de las entidades. El siguiente diagrama muestra cómo se realiza este proceso:

![Diagrama de eliminación de entidad](imagenes/eliminar.jpeg)

---

## 6. Actualización de Entidades

Para la actualización de las entidades, se sigue un flujo estándar que se muestra en el siguiente diagrama:

![Diagrama de actualización de entidad](imagenes/actualizar.jpeg)
