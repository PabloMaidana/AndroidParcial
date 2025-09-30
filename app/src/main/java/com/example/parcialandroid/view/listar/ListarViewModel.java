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

    private final MutableLiveData<List<Producto>> productos = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<Producto>> getProductos() { return productos; }

    public void refrescar() {
        ArrayList<Producto> copia = new ArrayList<>(MainActivity.productos);
        Collections.sort(copia, Comparator.comparing(Producto::getDescripcion, String::compareToIgnoreCase));
        productos.setValue(copia);
    }
}