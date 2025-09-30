package com.example.parcialandroid.view.detalle;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.parcialandroid.R;
import com.example.parcialandroid.databinding.FragmentDetalleProductoBinding;
import com.example.parcialandroid.model.Producto;

public class DetalleProductoFragment extends Fragment {
    private FragmentDetalleProductoBinding b;
    private DetalleProductoViewModel vm;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater i,@Nullable ViewGroup c,@Nullable Bundle s){
        b = FragmentDetalleProductoBinding.inflate(i,c,false); return b.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View v,@Nullable Bundle s){
        vm = new ViewModelProvider(this).get(DetalleProductoViewModel.class);

        // Recibir el producto
        Bundle args = getArguments();
        if (args != null) {
            Producto recibido = (Producto) args.getSerializable("producto");
            if (recibido != null) vm.initProducto(recibido);
        }

        vm.getEditable().observe(getViewLifecycleOwner(), p -> {
            if (p == null) return;
            b.etCodigo.setText(p.getCodigo());
            b.etDescripcion.setText(p.getDescripcion());
            b.etPrecio.setText(String.valueOf(p.getPrecio()));
        });
        vm.getMensaje().observe(getViewLifecycleOwner(),
                m -> Toast.makeText(requireContext(), m, Toast.LENGTH_SHORT).show());

        b.btnGuardar.setOnClickListener(x ->
                vm.guardarCambios(b.etCodigo.getText().toString(),
                        b.etDescripcion.getText().toString(),
                        b.etPrecio.getText().toString())
        );
    }
}