package org.example;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static Scanner teclado = new Scanner(System.in);
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jueguito2");
    private static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        insertarDatosIniciales();
        boolean exit = false;
        while (!exit) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Obtener todos los juegos");
            System.out.println("2. Obtener todos los desarrolladores");
            System.out.println("3. Obtener juegos por desarrollador");
            System.out.println("4. Obtener desarrolladores por juego");
            System.out.println("5. Actualizar título de un juego");
            System.out.println("6. Eliminar un juego");
            System.out.println("7. Obtener juegos lanzados antes de una fecha");
            System.out.println("8. Obtener juegos lanzados después de una fecha");
            System.out.println("9. Obtener juegos en un rango de fechas");
            System.out.println("10. Obtener juegos por plataforma");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    obtenerTodosLosJuegos();
                    break;
                case 2:
                    obtenerTodosLosDesarrolladores();
                    break;
                case 3:
                    System.out.print("Ingrese el nombre del desarrollador: ");
                    String nombreDesarrollador = teclado.nextLine();
                    obtenerJuegosPorDesarrollador(nombreDesarrollador);
                    break;
                case 4:
                    System.out.print("Ingrese el nombre del juego: ");
                    String nombreJuego = teclado.nextLine();
                    obtenerDesarrolladoresPorJuego(nombreJuego);
                    break;
                case 5:
                    System.out.print("Ingrese el ID del juego a actualizar: ");
                    int idJuegoActualizar = teclado.nextInt();
                    teclado.nextLine();
                    System.out.print("Ingrese el nuevo título: ");
                    String nuevoTitulo = teclado.nextLine();
                    actualizarTituloJuego(idJuegoActualizar, nuevoTitulo);
                    break;
                case 6:
                    borrarJuegoPorTitulo();
                    break;
                case 7:
                    encontrarJuegosAntesDeFechas();
                    break;
                case 8:
                    encontrarJuegosDespuesDeFechas();
                    break;
                case 9:
                    encontrarJuegosEntreFechas();
                    break;
                case 10:
                    encontrarJuegosPorPlataforma();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        em.close();
        emf.close();
    }

    /**
     * Metodo para insertar los datos iniciales en la base de datos
     */
    private static void insertarDatosIniciales() {

        em.getTransaction().begin();

        //Desarrolladoras
        Desarrollador dev1 = new Desarrollador();
        dev1.setNombre("Bungie");
        dev1.setPais("EEUU");
        Desarrollador dev2 = new Desarrollador();
        dev2.setNombre("FromSoftware");
        dev2.setPais("Japon");
        Desarrollador dev3 = new Desarrollador();
        dev3.setNombre("Rockstar");
        dev3.setPais("EEUU");
        Desarrollador dev4 = new Desarrollador();
        dev4.setNombre("CD Projekt");
        dev4.setPais("Polonia");
        Desarrollador dev5 = new Desarrollador();
        dev5.setNombre("The Game Kitchen");
        dev5.setPais("España");

        em.persist(dev1);
        em.persist(dev2);
        em.persist(dev3);
        em.persist(dev4);
        em.persist(dev5);

        //Juegos
        Juego j1 = new Juego();
        j1.setNombreJuego("Destiny2");
        j1.setFechaJuego(Date.valueOf("2017-09-06"));
        j1.setPlataforma("PC/Consola");
        j1.getDesarrolladores().add(dev1);

        Juego j2 = new Juego();
        j2.setNombreJuego("Dark Souls");
        j2.setFechaJuego(Date.valueOf("2011-09-22"));
        j2.setPlataforma("Consola");
        j2.getDesarrolladores().add(dev2);

        Juego j3 = new Juego();
        j3.setNombreJuego("Grand Theft Auto V");
        j3.setFechaJuego(Date.valueOf("2013-09-17"));
        j3.setPlataforma("Consola");
        j3.getDesarrolladores().add(dev3);

        Juego j4 = new Juego();
        j4.setNombreJuego("Cyberpunk 2077");
        j4.setFechaJuego(Date.valueOf("2020-12-10"));
        j4.setPlataforma("PC");
        j4.getDesarrolladores().add(dev4);

        Juego j5 = new Juego();
        j5.setNombreJuego("Blasphemous");
        j5.setFechaJuego(Date.valueOf("2019-09-10"));
        j5.setPlataforma("PC");
        j5.getDesarrolladores().add(dev5);

        Juego j6 = new Juego();
        j6.setNombreJuego("Red Dead Redemption 2");
        j6.setFechaJuego(Date.valueOf("2019-09-10"));
        j6.setPlataforma("Consola");
        j6.getDesarrolladores().add(dev3);

        Juego j7 = new Juego();
        j7.setNombreJuego("Halo");
        j7.setFechaJuego(Date.valueOf("2001-11-15"));
        j7.setPlataforma("Consola");
        j7.getDesarrolladores().add(dev1);

        Juego j8 = new Juego();
        j8.setNombreJuego("Elden Ring");
        j8.setFechaJuego(Date.valueOf("2022-02-25"));
        j8.setPlataforma("Consola");
        j8.getDesarrolladores().add(dev2);

        Juego j9 = new Juego();
        j9.setNombreJuego("The Witcher 3");
        j9.setFechaJuego(Date.valueOf("2015-05-18"));
        j9.setPlataforma("Consola");
        j9.getDesarrolladores().add(dev4);

        Juego j10 = new Juego();
        j10.setNombreJuego("Blasphemous 2");
        j10.setFechaJuego(Date.valueOf("2024-08-24"));
        j10.setPlataforma("Consola");
        j10.getDesarrolladores().add(dev5);

        em.persist(j1);
        em.persist(j2);
        em.persist(j3);
        em.persist(j4);
        em.persist(j5);
        em.persist(j6);
        em.persist(j7);
        em.persist(j8);
        em.persist(j9);
        em.persist(j10);

        dev1.getJuegos().add(j1);
        dev1.getJuegos().add(j7);
        dev2.getJuegos().add(j2);
        dev2.getJuegos().add(j8);
        dev3.getJuegos().add(j3);
        dev3.getJuegos().add(j6);
        dev4.getJuegos().add(j4);
        dev4.getJuegos().add(j9);
        dev5.getJuegos().add(j5);
        dev5.getJuegos().add(j10);

        em.getTransaction().commit();
    }

    /**
     *  Metodo para obtener y mostrar todos los juegos
     */
    public static void obtenerTodosLosJuegos() {
        List<Juego> juegos = em.createNamedQuery("Juego.findAll", Juego.class).getResultList();
        juegos.forEach(j -> System.out.println(j.getNombreJuego()));
    }

    /**
     * Metodo para obtener y mostrar todos los desarrolladores
     */
    public static void obtenerTodosLosDesarrolladores() {
        List<Desarrollador> desarrolladores = em.createNamedQuery("Desarrollador.findAll", Desarrollador.class).getResultList();
        desarrolladores.forEach(d -> System.out.println(d.getNombre()));
    }

    /**
     * Metodo para obtener y mostrar juegos de un desarrollador en específico
     * @param nombreDesarrollador
     */
    public static void obtenerJuegosPorDesarrollador(String nombreDesarrollador) {
        Query query = em.createQuery(
                "SELECT j FROM Juego j JOIN j.desarrolladores d WHERE d.nombre = :nombreDesarrollador"
        );
        query.setParameter("nombreDesarrollador", nombreDesarrollador);
        List<Juego> juegos = query.getResultList();
        juegos.forEach(j -> System.out.println(j.getNombreJuego()));
    }

    /**
     * Metodo para obtener y mostrar la desarrolladora de un juego en específico
     * @param nombreJuego
     */
    public static void obtenerDesarrolladoresPorJuego(String nombreJuego) {
        Query query = em.createQuery(
                "SELECT d FROM Desarrollador d JOIN d.juegos j WHERE j.nombreJuego = :nombreJuego"
        );
        query.setParameter("nombreJuego", nombreJuego);
        List<Desarrollador> desarrolladores = query.getResultList();
        desarrolladores.forEach(d -> System.out.println(d.getNombre()));
    }

    /**
     * Metodo para actualizar el título de un juego
     * @param idJuego
     * @param nuevoNombre
     */

    public static void actualizarTituloJuego(int idJuego, String nuevoNombre) {
        em.getTransaction().begin();
        em.createQuery("UPDATE Juego j SET j.nombreJuego = :nuevoNombre WHERE j.idJuego = :id")
                .setParameter("nuevoNombre", nuevoNombre)
                .setParameter("id", idJuego)
                .executeUpdate();
        em.getTransaction().commit();
        System.out.println("Título actualizado.");
    }

    /**
     * Metodo para encontrar y mostrar juegos por plataforma
     */
    public static void encontrarJuegosPorPlataforma() {
        System.out.print("Ingrese la plataforma: ");
        String plataforma = teclado.nextLine();
        Query query = em.createQuery(
                "SELECT j FROM Juego j WHERE j.plataforma = :plataforma"
        );
        query.setParameter("plataforma", plataforma);
        List<Juego> juegos = query.getResultList();
        juegos.forEach(j -> System.out.println(j.getNombreJuego()));
    }

    public static void encontrarJuegosAntesDeFechas() {
        System.out.print("Ingrese la fecha (formato: yyyy-MM-dd): ");
        Date fecha = Date.valueOf(teclado.nextLine());
        Query query = em.createQuery(
                "SELECT j FROM Juego j WHERE j.fechaJuego < :fecha"
        );
        query.setParameter("fecha", fecha);
        List<Juego> juegos = query.getResultList();
        juegos.forEach(j -> System.out.println(j.getNombreJuego()));
    }

    public static void encontrarJuegosDespuesDeFechas() {
        System.out.print("Ingrese la fecha (formato: yyyy-MM-dd): ");
        Date fecha = Date.valueOf(teclado.nextLine());
        Query query = em.createQuery(
                "SELECT j FROM Juego j WHERE j.fechaJuego > :fecha"
        );
        query.setParameter("fecha", fecha);
        List<Juego> juegos = query.getResultList();
        juegos.forEach(j -> System.out.println(j.getNombreJuego()));
    }

    public static void encontrarJuegosEntreFechas() {
        System.out.print("Ingrese la fecha de inicio (formato: yyyy-MM-dd): ");
        Date fechaInicio = Date.valueOf(teclado.nextLine());
        System.out.print("Ingrese la fecha de fin (formato: yyyy-MM-dd): ");
        Date fechaFin = Date.valueOf(teclado.nextLine());
        Query query = em.createQuery(
                "SELECT j FROM Juego j WHERE j.fechaJuego BETWEEN :fechaInicio AND :fechaFin"
        );
        query.setParameter("fechaInicio", fechaInicio);
        query.setParameter("fechaFin", fechaFin);
        List<Juego> juegos = query.getResultList();
        juegos.forEach(j -> System.out.println(j.getNombreJuego()));
    }

    /**
     * Método para eliminar un juego por su título
     */
    public static void borrarJuegoPorTitulo() {
        System.out.print("Ingrese el título del juego a eliminar: ");
        String titulo = teclado.nextLine();
        em.getTransaction().begin();
        int deletedCount = em.createQuery("DELETE FROM Juego j WHERE j.nombreJuego = :nombreJuego")
                .setParameter("nombreJuego", titulo)
                .executeUpdate();
        em.getTransaction().commit();
        System.out.println(deletedCount + " juego(s) eliminado(s).");
    }
}
