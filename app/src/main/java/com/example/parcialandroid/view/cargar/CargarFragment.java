package com.example.parcialandroid.view.cargar;

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
import com.example.parcialandroid.databinding.FragmentCargarBinding;

public class CargarFragment extends Fragment {
    private FragmentCargarBinding b;
    private CargarViewModel vm;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater i,@Nullable ViewGroup c,@Nullable Bundle s){
        b = FragmentCargarBinding.inflate(i,c,false); return b.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View v,@Nullable Bundle s){
        vm = new ViewModelProvider(this).get(CargarViewModel.class);
        vm.getMensaje().observe(getViewLifecycleOwner(), m -> Toast.makeText(requireContext(), m, Toast.LENGTH_SHORT).show());
        b.btnGuardar.setOnClickListener(x ->
                vm.guardar(b.etCodigo.getText().toString(),
                        b.etDescripcion.getText().toString(),
                        b.etPrecio.getText().toString())
        );
    }
}