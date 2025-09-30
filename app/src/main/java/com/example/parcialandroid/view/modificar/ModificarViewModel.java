package com.example.parcialandroid.view.modificar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.parcialandroid.MainActivity;
import com.example.parcialandroid.model.Producto;

public class ModificarViewModel extends ViewModel {

    private MutableLiveData<Producto> productoEncontrado;
    private MutableLiveData<String> mensaje;

    public LiveData<Producto> getProductoEncontrado() {
        if (productoEncontrado == null) productoEncontrado = new MutableLiveData<>();
        return productoEncontrado;
    }
    public LiveData<String> getMensaje() {
        if (mensaje == null) mensaje = new MutableLiveData<>();
        return mensaje;
    }

    public void buscar(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            ((MutableLiveData<String>) getMensaje()).setValue("Ingrese un código");
            return;
        }
        Producto found = null;
        for (Producto p : MainActivity.productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo.trim())) { found = p; break; }
        }
        if (found != null) {
            ((MutableLiveData<Producto>) getProductoEncontrado()).setValue(found);
        } else {
            ((MutableLiveData<String>) getMensaje()).setValue("No encontrado");
        }
    }
}