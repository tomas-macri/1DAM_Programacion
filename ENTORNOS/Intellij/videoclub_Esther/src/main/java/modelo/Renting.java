package modelo;

import java.time.LocalDateTime;

public class Renting {
    private LocalDateTime fechaAlquiler;
    private Member member;
    private Product productoAlquilado;

    public Renting(LocalDateTime fechaAlquiler, Member member, Product productoAlquilado) {
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

    public Product getProductoAlquilado() {
        return productoAlquilado;
    }

    public void setProductoAlquilado(Product productoAlquilado) {
        this.productoAlquilado = productoAlquilado;
    }
}
