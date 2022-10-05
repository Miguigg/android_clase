package org.esei.dm.listacompra.mode;
import java.text.SimpleDateFormat;
import java.util.Date;

public class productos {
    private  String nombre;
    private double cantidad;
    private Date fecha;

    public productos(String nombre, double cantidad, Date fecha) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString(){
        return getNombre() + " " + getCantidad();
    }
}
