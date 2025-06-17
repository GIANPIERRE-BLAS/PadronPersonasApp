
# 🧑‍💼 PadronPersonasApp
<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-green?style=for-the-badge&logo=android" alt="Android">
  <img src="https://img.shields.io/badge/Language-Java%20%2F%20Kotlin-orange?style=for-the-badge&logo=kotlin" alt="JavaKotlin">
  <img src="https://img.shields.io/badge/Database-SQLite-blue?style=for-the-badge&logo=sqlite" alt="SQLite">
  <img src="https://img.shields.io/badge/API-21+-lightblue?style=for-the-badge&logo=android" alt="API">
  <img src="https://img.shields.io/badge/License-MIT-blue?style=for-the-badge" alt="License">
</p>

<p align="center">
  <strong>Gestión local de personas usando SQLite en Android</strong>
</p>

<p align="center">
  <a href="#📖-descripción">Descripción</a> •
  <a href="#📱-características">Características</a> •
  <a href="#🛠️-tecnologías">Tecnologías</a> •
  <a href="#🚀-instalación">Instalación</a> •
  <a href="#🏗️-estructura-del-proyecto">Estructura</a> •
  <a href="#🤝-contribución">Contribución</a>
</p>

---

## 📖 Descripción

**PadronPersonasApp** es una aplicación Android nativa desarrollada en Java, que permite gestionar un padrón de personas (registro, edición, eliminación y visualización) usando **SQLite** como base de datos local. Es ideal para aprender CRUD local, persistencia de datos y manejo de interfaces modernas con `RecyclerView`.

---

## 📱 Características

* 📋 Registro de nuevas personas con datos básicos
* 🔍 Búsqueda en tiempo real de registros
* 📝 Edición y eliminación de registros existentes
* 🧾 Visualización lista con diseño profesional en tarjetas (CardView)
* 📊 Contador de registros encontrados
* ✅ Validaciones y notificaciones (Snackbars, AlertDialogs)
* 🔄 Barra de progreso con animación al guardar registros

---

## 🛠️ Tecnologías

* **Lenguaje:** Java
* **SDK mínimo:** Android API 21 (Lollipop)
* **Base de Datos:** SQLite
* **Diseño:** ConstraintLayout, CardView, Material Design
* **Componentes:** RecyclerView, AlertDialog, Snackbar, ProgressBar
* **Persistencia:** SQLiteOpenHelper
* **Compatibilidad:** Dispositivos Android 5.0+

---

## 🚀 Instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/GIANPIERRE-BLAS/PadronPersonasApp.git
cd PadronPersonasApp
```

### 2. Abrir en Android Studio

* Ir a `File > Open` y seleccionar la carpeta del proyecto
* Esperar que se sincronice Gradle

### 3. Ejecutar la App

* Conectar un dispositivo Android o iniciar un emulador
* Presionar ▶️ `Run` (Shift + F10)

---

## 🏗️ Estructura del Proyecto

```
PadronPersonasApp/
├── java/cibertec/edu/pe/
│   ├── MainActivity.java           # Pantalla principal
│   ├── Persona.java                # Modelo de datos Persona
│   ├── PersonaAdapter.java         # Adaptador RecyclerView
│   ├── DBHelper.java               # Clase SQLiteOpenHelper
│   └── RegistroActivity.java       # Formulario de nueva persona
├── res/layout/
│   ├── activity_main.xml           # Lista de personas
│   ├── activity_registro.xml       # Formulario de registro
│   └── item_persona.xml            # CardView de persona
└── AndroidManifest.xml
```

---

## 🤝 Contribución

### ¿Quieres colaborar?

1. 🍴 Haz un Fork del proyecto
2. 🛠️ Crea tu branch (`git checkout -b feature/mi-funcion`)
3. 📦 Haz tus cambios y commitea (`git commit -m 'Agrega nueva funcionalidad'`)
4. 🚀 Sube tu rama (`git push origin feature/mi-funcion`)
5. 🔄 Abre un Pull Request

---

## 👨‍💻 Desarrollador

<p align="center">
  <img src="https://github.com/GIANPIERRE-BLAS.png?size=140" alt="Gianpierre Blas Flores" width="140"/><br>
  <strong>Gianpierre Blas Flores</strong><br>
  <em>Desarrollador Android | Computación e Informática</em><br><br>

  <a href="https://github.com/GIANPIERRE-BLAS">
    <img src="https://img.shields.io/badge/GitHub-@GIANPIERRE--BLAS-black?style=flat-square&logo=github" alt="GitHub" />
  </a>
  <a href="https://www.linkedin.com/in/justo-gianpierre-blas-flores-5ba671302/">
    <img src="https://img.shields.io/badge/LinkedIn-Gianpierre%20Blas-blue?style=flat-square&logo=linkedin" alt="LinkedIn" />
  </a>
  <a href="mailto:gianpierreblasflores235@gmail.com">
    <img src="https://img.shields.io/badge/Email-gianpierreblasflores235@gmail.com-red?style=flat-square&logo=gmail" alt="Email" />
  </a>
</p>

---

## 📄 Licencia

Este proyecto está bajo la licencia **MIT**. Consulta el archivo [LICENSE](LICENSE) para más detalles.

---

<div align="center">

### 🌟 ¡Dale una estrella si este proyecto te ayudó!

[![Stargazers](https://img.shields.io/github/stars/GIANPIERRE-BLAS/PadronPersonasApp?style=social)](https://github.com/GIANPIERRE-BLAS/PadronPersonasApp/stargazers)

**Hecho con ❤️ en Trujillo - Perú**

</div>


