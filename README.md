# ☎️ Agenda Telefónica - Java Hackathon

╔══════════════════════════════════════════════╗
║               AGENDA TELEFÓNICA              ║
╚══════════════════════════════════════════════╝
1. Añadir Contacto
2. Listar Contactos
3. Buscar Contacto
4. Eliminar Contacto
5. Modificar Contacto
6. Salir
  /// /// //// //// //// //// /// /// //// //// //// //// /// /// //// //// //// ////

> Proyecto **Hackatón 2 Creacion de una Agenda**  
> Aplicación de consola que implementa un sistema de **gestión de contactos** usando **Programación Orientada a Objetos (POO)** en Java.
---
## 🚀 Funcionalidades principales
✅ **Añadir contacto** con validación de duplicados  
✅ **Listar contactos** con formato visual claro  
✅ **Buscar contacto** por ID, nombre, apellido o teléfono  
✅ **Modificar contacto** mostrando los datos actuales antes de editar  
✅ **Eliminar contacto** con confirmación individual o masiva  
✅ **Validaciones de entrada** (teléfono solo numérico, rangos de opciones)  
✅ **Interfaz visual en consola** con colores y encabezados  
✅ **Búsqueda parcial e insensible a mayúsculas/minúsculas**  
---
## 🧩 Estructura del proyecto

src/
├── Model/
│ └── Contacto.java
├── Service/
│ └── AgendaService.java
├── Util/
│ └── ConsoleStyle.java
└── Main.java

## ⚙️ Tecnologías utilizadas

| Tecnología | Uso principal |
|-------------|----------------|
| **Java 17** | Lenguaje base del proyecto |
| **POO (Programación Orientada a Objetos)** | Modelado de clases y responsabilidades |
| **Streams y Lambdas** | Búsqueda y filtrado eficiente de contactos |
| **ArrayList** | Almacenamiento dinámico de registros |
| **Try/Catch** | Manejo de errores y validaciones |
| **ANSI Colors** | Interfaz visual en consola (azul, verde, rojo, amarillo) |

---

## 🧱 Descripción general del CRUD

| Operación | Descripción | Validaciones principales |
|------------|--------------|---------------------------|
| **Crear** | Añade un contacto nuevo a la agenda. | - Teléfono solo con números.<br>- No se permiten duplicados (nombre, apellido y teléfono idénticos). |
| **Leer** | Lista todos los contactos con formato visual. | - Muestra aviso si la agenda está vacía. |
| **Buscar** | Permite buscar por **ID, nombre, apellido o teléfono**, incluso parcialmente. | - Insensible a mayúsculas/minúsculas.<br>- Opción de reintentar o regresar al menú. |
| **Modificar** | Modifica los datos de un contacto existente. | - Confirma contacto seleccionado.<br>- Muestra datos actuales antes de editar.<br>- Valida formato de teléfono. |
| **Eliminar** | Elimina un contacto por ID o múltiples por coincidencia. | - Confirmación antes de borrar.<br>- Opción “borrar todos los contactos” con doble verificación. |

---

## 🔄 Flujo del programa

MENÚ PRINCIPAL
│
├── 1. Añadir contacto
│ └── Valida duplicados → Añade o muestra error
│
├── 2. Listar contactos
│ └── Muestra todos los registros
│
├── 3. Buscar contacto
│ └── Submenú → ID / Nombre / Apellido / Teléfono
│ └── Búsqueda flexible (may/min, parcial)
│
├── 4. Eliminar contacto
│ └── Submenú → ID / Nombre / Apellido / Teléfono / Todos
│ └── Confirmación antes de borrar
│
├── 5. Modificar contacto
│ └── Submenú → ID / Nombre / Apellido / Teléfono
│ └── Muestra datos actuales → Editar → Confirmar
│
└── 6. Salir

---

## 💬 Validaciones y mensajes interactivos

| Escenario | Respuesta del sistema |
|------------|-----------------------|
| Usuario ingresa texto en campo numérico | ⚠️ “Entrada no válida. Usa solo números.” |
| Se intenta agregar un contacto duplicado | ❌ “Ya existe un contacto con el mismo nombre, apellido y teléfono.” |
| No se encuentran resultados al buscar | ⚠️ “No se encontraron contactos.” |
| Intento de eliminar/modificar ID inexistente | ❌ “No existe un contacto con ese ID.” |
| Opción fuera del rango permitido | ⚠️ “Opción inválida. Elige un número dentro del rango.” |

---
## 🧠 Buenas prácticas implementadas

- **Encapsulamiento:** separación de responsabilidades entre modelo, servicio y vista.  
- **Reutilización de código:** métodos genéricos para búsqueda y validación.  
- **Legibilidad:** uso de nombres descriptivos y mensajes de consola amigables.  
- **Resiliencia:** manejo de errores y reintentos controlados para evitar fallos del flujo principal.

👥 Equipo de desarrollo
| Integrante | Rol / Aportaciones principales |
|-------------|-------------------------------|
| **Omar Albis** | Lógica del programa, Implementación de la clase `Main.java`, validaciones CRUD, estructura del menú principal y documentación general |
| **Joana Barbosa** | Implementación de la clase `Contacto.java`, manejo de atributos y métodos base |
| **Kevin Kirino** | Implementación de la clase `Contacto.java`, sobreescritura de métodos (`equals`, `hashCode`, `toString`) |
| **Daniel Zepahua** | Desarrollo de la clase `AgendaService.java`, lógica interna de gestión y almacenamiento de contactos |
| **Ricardo Saucedo** | Apoyo en la clase `Main.java`, documentación y pruebas finales, validación del flujo de ejecución |

🏁 Estado actual del proyecto
📌 Versión: 1.0 - CRUD Finalizado

📦 Últimos cambios:
Implementación de búsqueda flexible.
Validación de duplicados.
Confirmación visual en eliminación y modificación.

📄 Licencia
Proyecto académico con fines educativos.
Libre para consulta, aprendizaje y mejora del código.

🧠 Créditos adicionales
Generation México - Full Stack Java Bootcamp

Contribuciones y revisión de equipo durante la Hackatón.

