package com.example.parcialandroid.view.detalle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.parcialandroid.MainActivity;
import com.example.parcialandroid.model.Producto;

public class DetalleProductoViewModel extends ViewModel {

    private MutableLiveData<Producto> editable;
    private MutableLiveData<String> mensaje;
    private String codigoOriginal;

    public LiveData<Producto> getEditable() {
        if (editable == null) editable = new MutableLiveData<>();
        return editable;
    }
    public LiveData<String> getMensaje() {
        if (mensaje == null) mensaje = new MutableLiveData<>();
        return mensaje;
    }


    /** Inicializa con el producto recibido desde el Bundle */
    public void initProducto(Producto recibido) {
        if (recibido != null) {
            codigoOriginal = recibido.getCodigo();
            ((MutableLiveData<Producto>) getEditable()).setValue(
                    new Producto(recibido.getCodigo(),
                            recibido.getDescripcion(),
                            recibido.getPrecio())
            );
        }
    }

    /** Guarda los cambios en la lista estática de MainActivity */
    public void guardarCambios(String nuevoCod, String descripcion, String precioTxt) {
        if (isEmpty(nuevoCod) || isEmpty(descripcion) || isEmpty(precioTxt)) {
            ((MutableLiveData<String>) getMensaje()).setValue("Campos obligatorios");
            return;
        }
        double precio;
        try { precio = Double.parseDouble(precioTxt.trim()); }
        catch (NumberFormatException e) {((MutableLiveData<String>) getMensaje()).setValue("Precio inválido"); return; }

        Producto original = null;
        for (Producto p : MainActivity.productos) {
            if (p.getCodigo().equalsIgnoreCase(codigoOriginal)) { original = p; break; }
        }
        if (original == null) { ((MutableLiveData<String>) getMensaje()).setValue("Producto no encontrado"); return; }

        if (!codigoOriginal.equalsIgnoreCase(nuevoCod)) {
            for (Producto p : MainActivity.productos) {
                if (p.getCodigo().equalsIgnoreCase(nuevoCod.trim())) {
                    ((MutableLiveData<String>) getMensaje()).setValue("Código ya existe");
                    return;
                }
            }
        }

        original.setCodigo(nuevoCod.trim());
        original.setDescripcion(descripcion.trim());
        original.setPrecio(precio);

        ((MutableLiveData<Producto>) getEditable()).setValue(
                new Producto(original.getCodigo(),
                        original.getDescripcion(),
                        original.getPrecio())
        );
        codigoOriginal = original.getCodigo();

        ((MutableLiveData<String>) getMensaje()).setValue("Producto actualizado");
    }

    private boolean isEmpty(String s){ return s==null || s.trim().isEmpty(); }
}