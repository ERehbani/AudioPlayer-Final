package com.example.audioplayerfinal.UI;

import com.example.audioplayerfinal.Clases.*;
import com.example.audioplayerfinal.ENums.EGenero;
import com.example.audioplayerfinal.Exceptions.*;
import com.example.audioplayerfinal.Gestores.GestorMusic;

import java.util.Scanner;

public class UI {

    private static final Scanner sc = new Scanner(System.in);
    private static final GestorMusic servicio = new GestorMusic();

    public static void main(String[] args) {
        mostrarMenuPrincipal();
    }

    public static void mostrarMenuPrincipal() {
        int opcion = -1;

        do {
            System.out.println("\n=== 🎧 AUDIO PLAYER ===");
            System.out.println("1. Buscar canción");
            System.out.println("2. Buscar artista");
            System.out.println("3. Buscar álbum");
            System.out.println("4. Crear nueva canción");
            System.out.println("5. Crear nuevo artista");
            System.out.println("6. Crear nuevo álbum");
            System.out.println("7. Ver playlists");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1 -> buscarCancionConsola();
                case 2 -> buscarArtistaConsola();
                case 3 -> buscarAlbumConsola();
                case 4 -> crearCancionConsola();
                case 5 -> crearArtistaConsola();
                case 6 -> crearAlbumConsola();
                case 7 -> System.out.println("🕹️ (en construcción) Playlists");
                case 0 -> System.out.println("👋 ¡Hasta la próxima, maestro del ritmo!");
                default -> System.out.println("❌ Opción no válida.");
            }

        } while (opcion != 0);
    }

    // ──────────── Submenús básicos ─────────────

    private static void buscarCancionConsola() {
        System.out.print("Ingrese el nombre de la canción: ");
        String nombre = sc.nextLine();
        try {
            Cancion c = servicio.buscarCancionPorNombre(nombre);
            System.out.println("🎵 " + c.datosCancion());
        } catch (ElementoNoExisteException e) {
            System.out.println("❌ " + e.getMessage());
        } catch (ColeccionVaciaException e) {
            throw new RuntimeException(e);
        }
    }

    private static void buscarArtistaConsola() {
        System.out.print("Ingrese el nombre del artista: ");
        String nombre = sc.nextLine();
        try {
            Artista a = servicio.buscarArtistaPorNombre(nombre);
            System.out.println("🎤 " + a.datosArtista());
        } catch (ElementoNoExisteException | ColeccionVaciaException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void buscarAlbumConsola() {
        System.out.print("Ingrese el nombre del álbum: ");
        String nombre = sc.nextLine();
        try {
            Album a = servicio.buscarAlbumPorNombre(nombre);
            System.out.println(a.mostrarDatosAlbum());
        } catch (ElementoNoExisteException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    // ──────────── Creaciones rápidas ─────────────

    private static void crearCancionConsola() {
        try {
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Nombre del artista: ");
            String nombreArtista = sc.nextLine();

            Artista artista;
            try {
                artista = servicio.buscarArtistaPorNombre(nombreArtista);
            } catch (ElementoNoExisteException e) {
                System.out.println("Artista no encontrado, se creará uno nuevo.");
                artista = servicio.crearArtista(nombreArtista);
            }

            System.out.print("Duración (segundos): ");
            int duracion = Integer.parseInt(sc.nextLine());

            System.out.println("Géneros disponibles:");
            for (EGenero g : EGenero.values()) {
                System.out.println("- " + g);
            }
            System.out.print("Seleccione un género: ");
            EGenero genero = EGenero.valueOf(sc.nextLine().toUpperCase());

            System.out.print("Fecha de publicación (YYYY-MM-DD): ");
            String fecha = sc.nextLine();

            Cancion nueva = servicio.crearCancion(nombre, duracion, genero, fecha);
            nueva.agregarArtista(artista);
            System.out.println("✅ Canción creada: " + nueva.getNombre());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private static void crearArtistaConsola() {
        try {
            System.out.print("Nombre del artista: ");
            String nombre = sc.nextLine();
            Artista a = servicio.crearArtista(nombre);
            System.out.println("✅ Artista creado: " + a.getNombre());
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void crearAlbumConsola() {
        try {
            System.out.print("Nombre del álbum: ");
            String nombre = sc.nextLine();
            System.out.print("Fecha de publicación: ");
            String fecha = sc.nextLine();
            System.out.print("Discográfica: ");
            String discografica = sc.nextLine();

            Album a = servicio.crearAlbum(nombre, fecha, discografica);
            System.out.println("✅ Álbum creado: " + a.getNombre());
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }
}
