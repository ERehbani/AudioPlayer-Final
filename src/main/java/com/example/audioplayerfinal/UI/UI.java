package com.example.audioplayerfinal.UI;

import com.example.audioplayerfinal.Clases.*;
import com.example.audioplayerfinal.ENums.EGenero;
import com.example.audioplayerfinal.Exceptions.*;
import com.example.audioplayerfinal.Gestores.GestorMusic;
import com.example.audioplayerfinal.Clases.ReproductorLista;

import java.util.*;

public class UI {

    private static final Scanner sc = new Scanner(System.in);
    private static final GestorMusic servicio = new GestorMusic();
    private static final ReproductorLista listaPlayer = new ReproductorLista();

    public static void main(String[] args) throws ElementoDuplicadoException, RepositorioNoExisteException {
        PersistenciaDatos.cargarTodo(servicio);
        mostrarMenuPrincipal();

        PersistenciaDatos.guardarTodo(servicio);
    }

    // ======================================================
    // MENU PRINCIPAL
    // ======================================================
    public static void mostrarMenuPrincipal() throws ElementoDuplicadoException, RepositorioNoExisteException {
        int opcion = -1;

        do {
            System.out.println("\n=== 🎧 AUDIO PLAYER ===");
            System.out.println("1. 🎵 Canciones");
            System.out.println("2. 🎤 Artistas y Álbumes");
            System.out.println("3. 🎶 Playlists");
            System.out.println("4. ▶ Reproducción / Controles");
            System.out.println("0. 🚪 Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1 -> menuCanciones();
                case 2 -> menuArtistasYAlbumes();
                case 3 -> menuPlaylists();
                case 4 -> menuReproduccion();
                case 0 -> System.out.println("👋 Cerrando el programa...");
                default -> System.out.println("❌ Opción no válida.");
            }

        } while (opcion != 0);
    }

    // ======================================================
    //SUBMENU CANCIONES
    // ======================================================
    private static void menuCanciones() {
        int opcion;
        do {
            System.out.println("\n=== 🎵 MENÚ CANCIONES ===");
            System.out.println("1. Buscar canción");
            System.out.println("2. Crear nueva canción");
            System.out.println("3. Listar todas las canciones");
            System.out.println("4. Eliminar canción");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> submenuBuscarCancion();
                case 2 -> crearCancionConsola();
                case 3 -> listarCancionesConsola();
                case 4 -> eliminarCancionConsola();
                case 0 -> System.out.println("↩ Volviendo al menú principal...");
                default -> System.out.println("❌ Opción inválida.");
            }

        } while (opcion != 0);
    }

    private static void submenuBuscarCancion() {
        System.out.print("Ingrese el nombre de la canción: ");
        String nombre = sc.nextLine();
        try {
            Cancion c = servicio.buscarCancionPorNombre(nombre);
            System.out.println("🎵 " + c.datosCancion());

            // submenu de opciones canción
            int opcion;
            do {
                System.out.println("\n1. ▶ Reproducir");
                System.out.println("2. 📥 Agregar a playlist");
                System.out.println("0. Volver");
                System.out.print("Seleccione: ");
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1 -> {
                        List<Cancion> unica = new ArrayList<>();
                        unica.add(c);
                        listaPlayer.setLista(unica);
                        listaPlayer.reproducirActual();
                    }
                    case 2 -> agregarCancionAPlaylistExistente(c);
                    case 0 -> System.out.println("↩ Volviendo...");
                    default -> System.out.println("❌ Opción inválida.");
                }
            } while (opcion != 0);

        } catch (ElementoNoExisteException | ColeccionVaciaException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    // ======================================================
    // SUBMENÚ ARTISTAS Y ÁLBUMES
    // ======================================================
    private static void menuArtistasYAlbumes() {
        int opcion;
        do {
            System.out.println("\n=== 🎤 MENÚ ARTISTAS / ÁLBUMES ===");
            System.out.println("1. Buscar artista");
            System.out.println("2. Buscar álbum");
            System.out.println("3. Crear nuevo artista");
            System.out.println("4. Crear nuevo álbum");
            System.out.println("5. Eliminar artista");
            System.out.println("6. Eliminar álbum");
            System.out.println("7. ▶ Reproducir álbum");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> buscarArtistaConsola();
                case 2 -> buscarAlbumConsola();
                case 3 -> crearArtistaConsola();
                case 4 -> crearAlbumConsola();
                case 5 -> eliminarArtistaConsola();
                case 6 -> eliminarAlbumConsola();
                case 7 -> reproducirAlbumConsola();
                case 0 -> System.out.println("↩ Volviendo...");
                default -> System.out.println("❌ Opción inválida.");
            }
        } while (opcion != 0);
    }

    // ======================================================
    // SUBMENU PLAYLISTS
    // ======================================================
    private static void menuPlaylists() throws ElementoDuplicadoException, RepositorioNoExisteException {
        int opcion;
        do {
            System.out.println("\n=== 🎶 MENÚ PLAYLISTS ===");
            System.out.println("1. Crear nueva playlist");
            System.out.println("2. Ver playlists");
            System.out.println("3. Agregar canción a playlist");
            System.out.println("4. Reproducir playlist");
            System.out.println("5. Eliminar playlist");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> crearPlaylistConsola();
                case 2 -> listarPlaylistsConsola();
                case 3 -> agregarCancionAPlaylistConsola();
                case 4 -> submenuReproducirPlaylist();
                case 5 -> eliminarPlaylistConsola();
                case 0 -> System.out.println("↩ Volviendo...");
                default -> System.out.println("❌ Opción inválida.");
            }
        } while (opcion != 0);
    }

    private static void submenuReproducirPlaylist() {
        System.out.print("Ingrese el nombre de la playlist: ");
        String nombre = sc.nextLine();
        try {
            Playlist p = servicio.buscarPlaylistPorNombre(nombre);
            List<Cancion> canciones = new ArrayList<>(p.getCanciones());

            listaPlayer.setLista(canciones);
            listaPlayer.reproducirActual();

            int opcion;
            do {
                System.out.println("\n🎶 Reproduciendo playlist: " + p.getNombre());
                System.out.println("1. ⏭ Siguiente");
                System.out.println("2. ⏮ Anterior");
                System.out.println("3. ⏸ Pausar");
                System.out.println("4. ▶ Continuar");
                System.out.println("5. ⏹ Detener");
                System.out.println("0. Volver");
                System.out.print("Seleccione: ");
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1 -> listaPlayer.siguiente();
                    case 2 -> listaPlayer.anterior();
                    case 3 -> listaPlayer.pausar();
                    case 4 -> listaPlayer.continuar();
                    case 5 -> listaPlayer.detener();
                    case 0 -> System.out.println("↩ Volviendo al menú playlists...");
                    default -> System.out.println("❌ Opción inválida.");
                }
            } while (opcion != 0);

        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    // ======================================================
    //SUBMENU DE CONTROLES GENERALES
    // ======================================================
    private static void menuReproduccion() {
        int opcion;
        do {
            System.out.println("\n=== ▶ CONTROLES DE REPRODUCCIÓN ===");
            System.out.println("1. Reproducir todas las canciones");
            System.out.println("2. Siguiente");
            System.out.println("3. Anterior");
            System.out.println("4. Pausar");
            System.out.println("5. Continuar");
            System.out.println("6. Detener");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> reproducirTodasLasCanciones();
                case 2 -> listaPlayer.siguiente();
                case 3 -> listaPlayer.anterior();
                case 4 -> listaPlayer.pausar();
                case 5 -> listaPlayer.continuar();
                case 6 -> listaPlayer.detener();
                case 0 -> System.out.println("↩ Volviendo...");
                default -> System.out.println("❌ Opción inválida.");
            }
        } while (opcion != 0);
    }

    // ======================================================
    // METODOS AUXILIARES
    // ======================================================

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

    private static void crearCancionConsola() {
        try {
            System.out.print("Nombre de la canción: ");
            String nombre = sc.nextLine();

            System.out.print("Nombre del artista: ");
            String nombreArtista = sc.nextLine();

            Artista artista;
            try {
                artista = servicio.buscarArtistaPorNombre(nombreArtista);
            } catch (ElementoNoExisteException e) {
                System.out.println("🎤 Artista no encontrado, creando uno nuevo...");
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

            System.out.print("Ruta del archivo: ");
            String ruta = nombre + ".mp3";
            System.out.println(ruta);

            System.out.println("Cantidad de reproducciones");
            int cantReproducciones = sc.nextInt();
            sc.nextLine();

            Cancion nueva = servicio.crearCancion(nombre, duracion, genero, ruta, cantReproducciones, fecha);
            nueva.agregarArtista(artista);

            System.out.print("¿Tiene colaboradores? (S/N): ");
            String respuestaColab = sc.nextLine().trim().toUpperCase();

            if (respuestaColab.equals("S")) {
                boolean agregarMas = true;
                while (agregarMas) {
                    System.out.print("Ingrese el nombre del colaborador: ");
                    String nombreColab = sc.nextLine();

                    Artista colaborador;
                    try {
                        colaborador = servicio.buscarArtistaPorNombre(nombreColab);
                    } catch (ElementoNoExisteException e) {
                        System.out.println("🎤 Colaborador no encontrado, creando uno nuevo...");
                        colaborador = servicio.crearArtista(nombreColab);
                    }

                    nueva.agregarArtista(colaborador);
                    colaborador.agregarGenero(genero);

                    System.out.print("¿Agregar otro colaborador? (S/N): ");
                    agregarMas = sc.nextLine().trim().equalsIgnoreCase("S");
                }
            }

            System.out.print("¿Pertenece a un álbum existente? (S/N): ");
            String respuesta = sc.nextLine().trim().toUpperCase();

            if (respuesta.equals("S")) {
                System.out.print("Ingrese el nombre del álbum: ");
                String nombreAlbum = sc.nextLine();
                try {
                    Album album = servicio.buscarAlbumPorNombre(nombreAlbum);
                    album.agregarCancion(nueva);
                    nueva.setAlbum(album);
                    artista.agregarAlbum(album);

                    if (!album.getArtistas().containsValue(artista)) {
                        album.agregarArtista(artista);
                    }

                    for (Artista colab : nueva.getColaboradores()) {
                        if (!album.getArtistas().containsValue(colab)) {
                            album.agregarArtista(colab);
                        }
                    }
                    System.out.println("📀 Canción agregada al álbum " + album.getNombre());
                } catch (ElementoNoExisteException e) {
                    System.out.println("Album no encontrado, creando uno nuevo...");
                    Album albumCreado = servicio.crearAlbum(nombreAlbum, fecha, " ");
                    albumCreado.agregarCancion(nueva);
                    nueva.setAlbum(albumCreado);
                    artista.agregarAlbum(albumCreado);

                    if (!albumCreado.getArtistas().containsValue(artista)) {
                        albumCreado.agregarArtista(artista);
                    }

                    // Relaciona los colaboradores
                    for (Artista colab : nueva.getColaboradores()) {
                        if (!albumCreado.getArtistas().containsKey(artista.getId())) {
                            albumCreado.agregarArtista(colab);
                        }
                    }
                    System.out.println("📀 Canción agregada al álbum " + albumCreado.getNombre());
                }
            } else {
                artista.agregarCancion(nueva, servicio);
                System.out.println("🎵 Canción registrada como single.");
            }

            System.out.println("✅ Canción creada: " + nueva.getNombre());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }


    private static void listarCancionesConsola() {
        try {
            List<Cancion> canciones = servicio.getCanciones();
            if (canciones.isEmpty()) {
                System.out.println("🎧 No hay canciones registradas.");
                return;
            }

            System.out.println("\n=== LISTA DE CANCIONES ===");
            for (Cancion c : canciones) {
                System.out.println("- " + c.getNombre() + " (" + c.getGenero() + ")");
            }
        } catch (Exception e) {
            System.out.println("❌ Error al listar canciones: " + e.getMessage());
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

            System.out.print("Artista al que pertenece: ");
            String nombreArtista = sc.nextLine();

            Artista artista;
            try {
                artista = servicio.buscarArtistaPorNombre(nombreArtista);
            } catch (ElementoNoExisteException e) {
                System.out.println("🎤 Artista no encontrado, creando uno nuevo...");
                artista = servicio.crearArtista(nombreArtista);
            }

            Map<String, Artista> map = new HashMap<>();
            map.put(artista.getNombre(), artista);

            System.out.print("Fecha de publicación: ");
            String fecha = sc.nextLine();

            System.out.print("Discográfica: ");
            String discografica = sc.nextLine();

            Album a = servicio.crearAlbum(nombre, fecha, discografica);
            a.setArtistas(map);

            artista.agregarAlbum(a);

            System.out.println("✅ Álbum creado: " + a.getNombre());
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }


    private static void crearPlaylistConsola() throws ElementoDuplicadoException, RepositorioNoExisteException {
        System.out.print("Nombre de la playlist: ");
        String nombre = sc.nextLine();
        Playlist p = servicio.crearPlaylist(nombre);
        System.out.println("🎶 Playlist creada: " + p.getNombre());
    }

    private static void agregarCancionAPlaylistConsola() {
        try {
            System.out.print("Nombre de la playlist: ");
            String nombrePlaylist = sc.nextLine();
            Playlist p = servicio.buscarPlaylistPorNombre(nombrePlaylist);

            System.out.print("Nombre de la canción: ");
            String nombreCancion = sc.nextLine();
            Cancion c = servicio.buscarCancionPorNombre(nombreCancion);

            p.agregarCancion(c);
            System.out.println("✅ Canción agregada a " + p.getNombre());
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void agregarCancionAPlaylistExistente(Cancion c) {
        try {
            System.out.print("Nombre de la playlist: ");
            String nombrePlaylist = sc.nextLine();
            Playlist p = servicio.buscarPlaylistPorNombre(nombrePlaylist);
            if(p == null) {
                Playlist newP = servicio.crearPlaylist(nombrePlaylist);
                newP.agregarCancion(c);
                System.out.println("✅ Canción agregada a " + newP.getNombre());
                return;
            }
            p.agregarCancion(c);
            System.out.println("✅ Canción agregada a " + p.getNombre());
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void listarPlaylistsConsola() {
        var playlists = servicio.getPlaylist();
        if (playlists.isEmpty()) {
            System.out.println("📂 No hay playlists creadas.");
            return;
        }
        System.out.println("\n=== PLAYLISTS ===");
        for (Playlist p : playlists) {
            System.out.println("- " + p.getNombre()+"- "+ p.mostrarCancion());
        }
    }

    private static void reproducirTodasLasCanciones() {
        try {
            var canciones = servicio.getCanciones();
            if (canciones.isEmpty()) {
                System.out.println("🎧 No hay canciones registradas.");
                return;
            }
            listaPlayer.setLista(canciones);
            listaPlayer.reproducirActual();
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }



    private static void eliminarCancionConsola() {
        System.out.print("Ingrese el nombre de la canción a eliminar: ");
        String nombre = sc.nextLine();
        try {
            servicio.eliminarCancion(nombre);
            System.out.println("✅ Canción eliminada correctamente.");
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void eliminarArtistaConsola() {
        System.out.print("Ingrese el nombre del artista a eliminar: ");
        String nombre = sc.nextLine();
        try {
            servicio.eliminarArtista(nombre);
            System.out.println("✅ Artista eliminado correctamente.");
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void eliminarAlbumConsola() {
        System.out.print("Ingrese el nombre del álbum a eliminar: ");
        String nombre = sc.nextLine();
        try {
            servicio.eliminarAlbum(nombre);
            System.out.println("✅ Álbum eliminado correctamente.");
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void eliminarPlaylistConsola() {
        System.out.print("Ingrese el nombre de la playlist a eliminar: ");
        String nombre = sc.nextLine();
        try {
            servicio.eliminarPlaylist(nombre);
            System.out.println("✅ Playlist eliminada correctamente.");
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void reproducirAlbumConsola() {
        System.out.print("Ingrese el nombre del álbum a reproducir: ");
        String nombre = sc.nextLine();

        try {
            Album album = servicio.buscarAlbumPorNombre(nombre);

            if (album == null || album.cantidadDeCanciones() == 0) {
                System.out.println("🎧 El álbum no tiene canciones para reproducir.");
                return;
            }

            List<Cancion> cancionesAlbum = new ArrayList<>(album.getListaDeCanciones().values());

            listaPlayer.setLista(cancionesAlbum);
            listaPlayer.reproducirActual();

            int opcion;
            do {
                System.out.println("\n🎶 Reproduciendo álbum: " + album.getNombre());
                System.out.println("1. ⏭ Siguiente");
                System.out.println("2. ⏮ Anterior");
                System.out.println("3. ⏸ Pausar");
                System.out.println("4. ▶ Continuar");
                System.out.println("5. ⏹ Detener");
                System.out.println("0. Volver");
                System.out.print("Seleccione: ");
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1 -> listaPlayer.siguiente();
                    case 2 -> listaPlayer.anterior();
                    case 3 -> listaPlayer.pausar();
                    case 4 -> listaPlayer.continuar();
                    case 5 -> listaPlayer.detener();
                    case 0 -> System.out.println("↩ Volviendo al menú álbumes...");
                    default -> System.out.println("❌ Opción inválida.");
                }
            } while (opcion != 0);

        } catch (ElementoNoExisteException e) {
            System.out.println("❌ No se encontró el álbum: " + nombre);
        } catch (Exception e) {
            System.out.println("❌ Error al reproducir el álbum: " + e.getMessage());
        }
    }


}