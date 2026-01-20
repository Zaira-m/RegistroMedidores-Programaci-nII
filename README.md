# Registro de Medidores – Aplicación Android

Aplicación móvil desarrollada en **Kotlin** utilizando **Android Studio** y **Jetpack Compose**, que permite registrar 
y visualizar lecturas de medidores de **agua**, **luz** y **gas**.
La información se almacena localmamnte mediante **Room Database**, siguiendo la arquitectura **MVVM**.

## Funcionalidades principales
- Registrar nuevas lecturas de medidores.
- Seleccionar tipo de medición (Agua, Luz, Gas).
- Visualizar todas las mediciones en una lista ordenada.
- Guardado local con persistencia de datos usando Room.

## Tecnologías utilizadas
- Kotlin
- Jetpack Compose
- Room Database
- ViewModel + StateFlow (MVVM)
- Android Studio

## Estructura del proyecto
- `/data/db` – Entidad, DAO y base de datos.  
- `/ui/screens` – Pantallas principales (Lista y Registro).  
- `/ui/navigation` – Navegación entre pantallas.  
- `/viewmodel` – Lógica y manejo de estados.




