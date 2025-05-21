
import java.util.ArrayList;
import java.util.List;

public class Problema1_EjecutorSistemasVentas {
    public static void main(String[] args) {
        Producto camisa = new Producto("Camisa", 25.99, 10);
        Producto pantalon = new Producto("Pantalon", 45.50, 5);
        Producto zapatos = new Producto("Zapatos", 89.99, 3);

        System.out.println("=== PRODUCTOS DISPONIBLES ===");
        System.out.println(camisa + "\n");
        System.out.println(pantalon + "\n");
        System.out.println(zapatos + "\n");

        CarritoDeCompras carrito = new CarritoDeCompras(100, 0.10);

        System.out.println("Agregando productos al carrito...");
        carrito.agregarProducto(camisa, 2);
        carrito.agregarProducto(pantalon, 1);
        carrito.agregarProducto(zapatos, 1);

        carrito.mostrarDetalleCompra();

        double total = carrito.calcularTotal();
        System.out.println("\nIntentando pagar con $200...");
        System.out.println(carrito.realizarPago(200));

        CarritoDeCompras carritoGrande = new CarritoDeCompras(100, 0.10);
        carritoGrande.agregarProducto(zapatos, 2);
        carritoGrande.agregarProducto(pantalon, 2);

        System.out.println("\n=== COMPRA PROMOCIONAL ===");
        carritoGrande.mostrarDetalleCompra();
        System.out.println("\nPagando con $300...");
        System.out.println(carritoGrande.realizarPago(300));
    }
}
class Producto {
    public String nombre;
    public double precio;
    public int cantidad;

    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return String.format("Producto: %s\nPrecio: $%.2f\nCantidad disponible: %d",
                            nombre, precio, cantidad);
    }
}

class CarritoDeCompras {
    public List<Producto> productos;
    public double montoPromocional;
    public double descuento;

    public CarritoDeCompras(double montoPromocional, double descuento) {
        this.productos = new ArrayList<>();
        this.montoPromocional = montoPromocional;
        this.descuento = descuento;
    }

    public boolean agregarProducto(Producto producto, int cantidad) {
        if (producto == null) {
            System.out.println("El producto no existe en la tienda.");
            return false;
        }
        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor a cero.");
            return false;
        }
        if (producto.getCantidad() < cantidad) {
            System.out.println("No hay suficiente cantidad disponible de " + producto.getNombre());
            return false;
        }

        Producto productoCarrito = new Producto(
            producto.getNombre(),
            producto.getPrecio(),
            cantidad
        );
        productos.add(productoCarrito);
        return true;
    }

    public double calcularTotal() {
        double total = 0;
        for (Producto producto : productos) {
            total += producto.getPrecio() * producto.getCantidad();
        }

        if (total > montoPromocional) {
            total = total * (1 - descuento);
        }
        return total;
    }

    public String realizarPago(double montoPagado) {
        double total = calcularTotal();
        if (montoPagado >= total) {
            for (Producto productoCarrito : productos) {
            }
            
            if (total > montoPromocional) {
                return String.format("Gracias por su compra Se aplico un descuento del %.0f%%. Su cambio es: $%.2f", 
                                   descuento * 100, (montoPagado - total));
            } else {
                return String.format("Gracias por su compra Su cambio es: $%.2f", (montoPagado - total));
            }
        } else {
            return String.format("Pago insuficiente. Faltan: $%.2f", (total - montoPagado));
        }
    }

    public void mostrarDetalleCompra() {
        System.out.println(" DETALLE DE COMPRA ");
        System.out.println(this); // Llama al toString() del carrito
        System.out.println("========================");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Productos en el carrito:\n");
        sb.append("----------------------------------\n");
        
        for (Producto producto : productos) {
            sb.append(producto.toString()) // Usa el toString() de Producto
              .append("\nCantidad comprada: ").append(producto.getCantidad())
              .append("\nSubtotal: $").append(String.format("%.2f", producto.getPrecio() * producto.getCantidad()))
              .append("\n--------------------------------\n");
        }
        
        double subtotal = productos.stream()
                              .mapToDouble(p -> p.getPrecio() * p.getCantidad())
                              .sum();
        
        sb.append(String.format("Subtotal: $%.2f%n", subtotal));
        
        if (subtotal > montoPromocional) {
            double descuentoAplicado = subtotal * descuento;
            sb.append(String.format("Descuento (%.0f%%): -$%.2f%n", descuento * 100, descuentoAplicado));
        }
        
        sb.append(String.format("TOTAL: $%.2f", calcularTotal()));
        
        return sb.toString();
    }
}