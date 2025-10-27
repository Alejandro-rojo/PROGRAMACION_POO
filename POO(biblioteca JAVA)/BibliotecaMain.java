
// (POO en Java)


import java.util.ArrayList;

/*
 * Clase Libro
 * Representa un libro dentro de la biblioteca.
 * Contiene atributos básicos y métodos para prestar y devolver.
 */
class Libro {
    String titulo;
    String autor;
    String isbn;
    boolean disponible;

    // Constructor de la clase Libro
    public Libro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponible = true; // Por defecto, el libro está disponible
    }

    // Método para prestar el libro
    public void prestar() {
        if (disponible) {
            disponible = false;
            System.out.println("El libro '" + titulo + "' ha sido prestado.");
        } else {
            System.out.println("El libro '" + titulo + "' no está disponible.");
        }
    }

    // Método para devolver el libro
    public void devolver() {
        disponible = true;
        System.out.println("El libro '" + titulo + "' ha sido devuelto.");
    }

    // Muestra la información del libro
    public void mostrarInfo() {
        String estado = disponible ? "Disponible" : "No disponible";
        System.out.println("Título: " + titulo + " | Autor: " + autor + " | Estado: " + estado);
    }
}

/*
 * Clase Usuario
 * Representa a una persona registrada en la biblioteca.
 * Contiene información personal y los libros prestados.
 */
class Usuario {
    String nombre;
    int idUsuario;
    ArrayList<Libro> librosPrestados;

    // Constructor
    public Usuario(String nombre, int idUsuario) {
        this.nombre = nombre;
        this.idUsuario = idUsuario;
        this.librosPrestados = new ArrayList<>();
    }

    // Método para prestar un libro
    public void prestarLibro(Libro libro) {
        if (libro.disponible) {
            libro.prestar();
            librosPrestados.add(libro);
        } else {
            System.out.println("El libro '" + libro.titulo + "' no está disponible para préstamo.");
        }
    }

    // Método para devolver un libro
    public void devolverLibro(Libro libro) {
        if (librosPrestados.contains(libro)) {
            libro.devolver();
            librosPrestados.remove(libro);
        } else {
            System.out.println("El usuario " + nombre + " no tiene este libro prestado.");
        }
    }

    // Muestra la información del usuario
    public void mostrarInfo() {
        System.out.println("Usuario: " + nombre);
        for (Libro libro : librosPrestados) {
            System.out.println("  - " + libro.titulo);
        }
    }
}

/*
 * Clase Estudiante (Hereda de Usuario)
 * Demuestra el concepto de herencia en POO.
 * Un estudiante es un tipo de usuario con un atributo adicional (grado).
 */
class Estudiante extends Usuario {
    String grado;

    // Constructor con super para heredar de Usuario
    public Estudiante(String nombre, int idUsuario, String grado) {
        super(nombre, idUsuario);
        this.grado = grado;
    }

    // Polimorfismo: se sobrescribe el método mostrarInfo()
    @Override
    public void mostrarInfo() {
        System.out.println("Estudiante: " + nombre + " | Grado: " + grado);
        if (librosPrestados.isEmpty()) {
            System.out.println("  No tiene libros prestados.");
        } else {
            for (Libro libro : librosPrestados) {
                System.out.println("  - " + libro.titulo);
            }
        }
    }
}

/*
 * Clase Prestamo
 * Representa el registro de un préstamo realizado por un usuario.
 */
class Prestamo {
    Usuario usuario;
    Libro libro;
    String fechaPrestamo;
    String fechaDevolucion;

    // Constructor
    public Prestamo(Usuario usuario, Libro libro, String fechaPrestamo) {
        this.usuario = usuario;
        this.libro = libro;
        this.fechaPrestamo = fechaPrestamo;
    }

    // Registrar un nuevo préstamo
    public void registrarPrestamo() {
        System.out.println("Préstamo registrado: " + usuario.nombre + " -> '" + libro.titulo + "' el " + fechaPrestamo);
    }

    // Registrar la devolución del libro
    public void registrarDevolucion(String fecha) {
        this.fechaDevolucion = fecha;
        System.out.println("Devolución registrada: '" + libro.titulo + "' devuelto el " + fecha);
    }
}

/*
 * Clase Biblioteca
 * Contiene listas de libros y usuarios, y permite administrar ambos.
 */
class Biblioteca {
    String nombre;
    ArrayList<Libro> listaLibros;
    ArrayList<Usuario> listaUsuarios;

    // Constructor
    public Biblioteca(String nombre) {
        this.nombre = nombre;
        this.listaLibros = new ArrayList<>();
        this.listaUsuarios = new ArrayList<>();
    }

    // Agregar un libro a la biblioteca
    public void agregarLibro(Libro libro) {
        listaLibros.add(libro);
        System.out.println("Libro '" + libro.titulo + "' agregado a la biblioteca.");
    }

    // Registrar un nuevo usuario
    public void registrarUsuario(Usuario usuario) {
        listaUsuarios.add(usuario);
        System.out.println("Usuario '" + usuario.nombre + "' registrado.");
    }
}

/*
 * Clase principal: BibliotecaMain
 * Contiene el método main() que ejecuta el programa.
 */
public class BibliotecaMain {
    public static void main(String[] args) {
        // Crear una biblioteca
        Biblioteca biblio = new Biblioteca("Biblioteca Palmira");

        // Crear libros
        Libro libro1 = new Libro("Cien Años de Soledad", "García Márquez", "12345");
        Libro libro2 = new Libro("El Principito", "Antoine de Saint-Exupéry", "67890");

        // Crear usuarios y estudiante (herencia)
        Usuario usuario1 = new Usuario("Martín TM", 101);
        Estudiante estudiante1 = new Estudiante("Sara", 102, "11°");

        // Registrar libros y usuarios
        biblio.agregarLibro(libro1);
        biblio.agregarLibro(libro2);
        biblio.registrarUsuario(usuario1);
        biblio.registrarUsuario(estudiante1);

        // Realizar un préstamo
        usuario1.prestarLibro(libro1);

        // Registrar el préstamo
        Prestamo prestamo1 = new Prestamo(usuario1, libro1, "2025-10-27");
        prestamo1.registrarPrestamo();

        // Mostrar información de los usuarios (polimorfismo en acción)
        System.out.println("\n--- Información de usuarios ---");
        Usuario[] usuarios = {usuario1, estudiante1};
        for (Usuario u : usuarios) {
            u.mostrarInfo(); // Llama al método correspondiente según el tipo del objeto
        }
    }
}
