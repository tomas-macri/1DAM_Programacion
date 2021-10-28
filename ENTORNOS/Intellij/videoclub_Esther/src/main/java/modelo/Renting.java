package modelo;

import java.time.LocalDateTime;

public class Renting {
    private LocalDateTime fechaAlquiler;
    private Member member;
    private Producto productoAlquilado;

    public Renting(LocalDateTime fechaAlquiler, Member member, Producto productoAlquilado) {
        this.fechaAlquiler = fechaAlquiler;
        this.member = member;
        this.productoAlquilado = productoAlquilado;
    }

    public LocalDateTime getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(LocalDateTime fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public Member getSocio() {
        return member;
    }

    public void setSocio(Member member) {
        this.member = member;
    }

    public Producto getProductoAlquilado() {
        return productoAlquilado;
    }

    public void setProductoAlquilado(Producto productoAlquilado) {
        this.productoAlquilado = productoAlquilado;
    }
}
