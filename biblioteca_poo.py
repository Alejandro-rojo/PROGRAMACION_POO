
# Gestión de Biblioteca 


# --- Clase Libro ---
class Libro:
    def __init__(self, titulo, autor, isbn):
        self.titulo = titulo
        self.autor = autor
        self.isbn = isbn
        self.disponible = True

    def prestar(self):
        if self.disponible:
            self.disponible = False
            print(f" El libro '{self.titulo}' ha sido prestado.")
        else:
            print(f" El libro '{self.titulo}' no está disponible.")

    def devolver(self):
        self.disponible = True
        print(f" El libro '{self.titulo}' ha sido devuelto.")

    def mostrar_info(self):
        estado = "Disponible" if self.disponible else "No disponible"
        print(f"Título: {self.titulo} | Autor: {self.autor} | Estado: {estado}")


# --- Clase Usuario ---
class Usuario:
    def __init__(self, nombre, id_usuario):
        self.nombre = nombre
        self.id_usuario = id_usuario
        self.libros_prestados = []

    def prestar_libro(self, libro):
        if libro.disponible:
            libro.prestar()
            self.libros_prestados.append(libro)
        else:
            print(f" El libro '{libro.titulo}' no está disponible para préstamo.")

    def devolver_libro(self, libro):
        if libro in self.libros_prestados:
            libro.devolver()
            self.libros_prestados.remove(libro)
        else:
            print(f" El usuario {self.nombre} no tiene este libro prestado.")

    def mostrar_info(self):
        print(f" Usuario: {self.nombre}")
        for libro in self.libros_prestados:
            print(f"  - {libro.titulo}")


# --- Clase Prestamo ---
class Prestamo:
    def __init__(self, usuario, libro, fecha_prestamo, fecha_devolucion=None):
        self.usuario = usuario
        self.libro = libro
        self.fecha_prestamo = fecha_prestamo
        self.fecha_devolucion = fecha_devolucion

    def registrar_prestamo(self):
        print(f" Préstamo registrado: {self.usuario.nombre} → '{self.libro.titulo}' el {self.fecha_prestamo}")

    def registrar_devolucion(self, fecha):
        self.fecha_devolucion = fecha
        print(f" Devolución registrada: '{self.libro.titulo}' devuelto el {fecha}")


# --- Clase Biblioteca ---
class Biblioteca:
    def __init__(self, nombre):
        self.nombre = nombre
        self.lista_libros = []
        self.lista_usuarios = []

    def agregar_libro(self, libro):
        self.lista_libros.append(libro)
        print(f" Libro '{libro.titulo}' agregado a la biblioteca.")

    def registrar_usuario(self, usuario):
        self.lista_usuarios.append(usuario)
        print(f" Usuario '{usuario.nombre}' registrado.")

    def buscar_libro(self, titulo):
        for libro in self.lista_libros:
            if libro.titulo == titulo:
                return libro
        print(" Libro no encontrado.")
        return None


# --- Clase Estudiante (herencia de Usuario) ---
class Estudiante(Usuario):
    def __init__(self, nombre, id_usuario, grado):
        super().__init__(nombre, id_usuario)
        self.grado = grado

    def mostrar_info(self):
        print(f" Estudiante: {self.nombre} | Grado: {self.grado}")
        if not self.libros_prestados:
            print("  No tiene libros prestados.")
        else:
            for libro in self.libros_prestados:
                print(f"  - {libro.titulo}")


# --- Ejecución del programa ---
if __name__ == "__main__":
    biblio = Biblioteca("Biblioteca Palmira")
    libro1 = Libro("Cien Años de Soledad", "García Márquez", "12345")
    libro2 = Libro("El Principito", "Antoine de Saint-Exupéry", "67890")

    usuario1 = Usuario("Martín TM", 101)
    estudiante1 = Estudiante("Sara", 102, "11°")

    biblio.agregar_libro(libro1)
    biblio.agregar_libro(libro2)
    biblio.registrar_usuario(usuario1)
    biblio.registrar_usuario(estudiante1)

    usuario1.prestar_libro(libro1)

    prestamo1 = Prestamo(usuario1, libro1, "2025-10-27")
    prestamo1.registrar_prestamo()

    print("\n--- Información de usuarios ---")
    for u in [usuario1, estudiante1]:
        u.mostrar_info()
