package com.example.parcialandroid.view.cargar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.parcialandroid.MainActivity;
import com.example.parcialandroid.model.Producto;

public class CargarViewModel extends ViewModel {

    private MutableLiveData<String> mensaje = new MutableLiveData<>();

    public LiveData<String> getMensaje() {
        return mensaje;
    }

    public void guardar(String codigo, String descripcion, String precioTxt) {
        if (isEmpty(codigo) || isEmpty(descripcion) || isEmpty(precioTxt)) {
            mensaje.setValue("Campos obligatorios");
            return;
        }
        double precio;
        try { precio = Double.parseDouble(precioTxt.trim()); }
        catch (NumberFormatException e) { mensaje.setValue("Precio inválido"); return; }

        for (Producto p : MainActivity.productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo.trim())) {
                mensaje.setValue("Código duplicado");
                return;
            }
        }
        MainActivity.productos.add(new Producto(codigo.trim(), descripcion.trim(), precio));
        mensaje.setValue("Producto agregado");
    }

    private boolean isEmpty(String s){ return s==null || s.trim().isEmpty(); }
}