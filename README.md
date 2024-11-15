LiterAlura: Catálogo de Libros

Este proyecto es un desafío de programación que consiste en construir un catálogo de libros, llamado LiterAlura (App_literatura), donde se realizan búsquedas y consultas de libros utilizando la API de Gutendex, se manejan datos en formato JSON, se almacenanan en una base de datos PostgreSQL, y se ofrece una interfaz de interacción a través de la consola.

🚀 Objetivo del Proyecto

El objetivo principal es desarrollar un catálogo de libros con interacción textual (vía consola), que permita a los usuarios buscar libros y autores, así como realizar diversas consultas y filtrados de los datos almacenados. El proyecto debe ofrecer al menos 5 opciones de interacción para los usuarios.

📋 Requisitos del Proyecto

El proyecto se desarrollará en varias etapas, detalladas a continuación:

Configuración del Ambiente Java:

Configuración de un entorno de desarrollo con IntelliJ IDEA.
Instalación de Java y configuración de Maven para la gestión de dependencias.
Creación del Proyecto:

Configuración de un proyecto Maven utilizando Spring Boot.
Integración con JPA-Hibernate para manejar la persistencia de datos.
Consumo de la API:

Utilización de la API de Gutendex para obtener datos de libros.
Manejo de solicitudes HTTP y análisis de la respuesta JSON utilizando clases de datos (Records).
Inserción y Consulta en la Base de Datos:

Persistencia de datos en una base de datos PostgreSQL.
Configuración de Spring Data JPA para facilitar la manipulación de datos.
Creación de consultas personalizadas y uso de Streams y Optionals para manejar los resultados.
Exhibición de Resultados a los Usuarios:

Implementación de un menú de interacción textual utilizando CommandLineRunner y una clase principal Principal.
Funcionalidades como búsqueda de libros, listado de autores, filtrado por género, y otras consultas específicas.

🛠️ Tecnologías Utilizadas

Java (JDK 17)
Spring Boot (versión 3.0 o superior)
JPA-Hibernate
PostgreSQL (Base de datos)
IntelliJ IDEA (IDE)
Maven (Gestor de dependencias)

📖 Funcionalidades

El proyecto ofrece las siguientes opciones de interacción:

Buscar libros por titulos utilizando la API de Gutendex.
Listar libros y autores guardados en la base de datos.
Filtrar libros por idioma, o autores vivos en un año determinado.
Top 10 de libros por descargas.

🌐 API Utilizada

El proyecto consume datos de la API de Gutendex, una API pública de libros de dominio público. Para más información, consulta la documentación de Gutendex.

📝 Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.

![Captura de pantalla 2024-11-14 180340](https://github.com/user-attachments/assets/cc17a5e6-39ee-4066-bfdc-7d5b42f2a8fa)

![Captura de pantalla 2024-11-14 194359](https://github.com/user-attachments/assets/93343f34-f299-48f5-9d7f-49f2cab2399f)

![Captura de pantalla 2024-11-14 194445](https://github.com/user-attachments/assets/a5ad299c-28c3-473b-8ff4-5a31536f651a)

![Captura de pantalla 2024-11-14 194509](https://github.com/user-attachments/assets/5396ee75-78f7-4ca8-bf2b-c3673fc0879f)

![Captura de pantalla 2024-11-14 194536](https://github.com/user-attachments/assets/5d358cde-0bdd-47c3-bc99-b89661fd904a)

![Captura de pantalla 2024-11-14 195303](https://github.com/user-attachments/assets/5fd46264-069f-4d74-904b-4ca97fed08a0)

![Captura de pantalla 2024-11-14 194728](https://github.com/user-attachments/assets/4d07ed7a-c6b8-4443-827e-f23478eae5d5)

![Captura de pantalla 2024-11-14 194842](https://github.com/user-attachments/assets/8190de20-63e0-4a38-9b6e-7e34e42f0822)

![Captura de pantalla 2024-11-14 194819](https://github.com/user-attachments/assets/eecbae2a-d521-43ee-b5d1-73c0b6b50933)









