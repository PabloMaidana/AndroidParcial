package com.example.parcialandroid.view.listar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.parcialandroid.MainActivity;
import com.example.parcialandroid.model.Producto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListarViewModel extends ViewModel {

    private MutableLiveData<List<Producto>> productos;

    public LiveData<List<Producto>> getProductos() {
        if (productos == null) productos = new MutableLiveData<>();
        return productos;
    }

    public void refrescar() {
        ArrayList<Producto> copia = new ArrayList<>(MainActivity.productos);
        Collections.sort(copia, Comparator.comparing(Producto::getDescripcion, String::compareToIgnoreCase));
        ((MutableLiveData<List<Producto>>) getProductos()).setValue(copia);
    }
}