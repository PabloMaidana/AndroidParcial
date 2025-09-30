package com.example.parcialandroid.view.modificar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.parcialandroid.MainActivity;
import com.example.parcialandroid.model.Producto;

public class ModificarViewModel extends ViewModel {

    private final MutableLiveData<Producto> productoEncontrado = new MutableLiveData<>();
    private final MutableLiveData<String> mensaje = new MutableLiveData<>();

    public LiveData<Producto> getProductoEncontrado() { return productoEncontrado; }
    public LiveData<String> getMensaje() { return mensaje; }

    public void buscar(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            mensaje.setValue("Ingrese un código");
            return;
        }
        Producto found = null;
        for (Producto p : MainActivity.productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo.trim())) { found = p; break; }
        }
        if (found != null) {
            productoEncontrado.setValue(found);
        } else {
            mensaje.setValue("No encontrado");
        }
    }
}