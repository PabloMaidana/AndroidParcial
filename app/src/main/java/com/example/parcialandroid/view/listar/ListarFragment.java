package com.example.parcialandroid.view.listar;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.parcialandroid.MainActivity;
import com.example.parcialandroid.R;
import com.example.parcialandroid.databinding.FragmentListarBinding;
import com.example.parcialandroid.view.adapter.ProductoAdapter;

public class ListarFragment extends Fragment {
    private FragmentListarBinding b;
    private ProductoAdapter adapter;
    private ListarViewModel vm;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater i,@Nullable ViewGroup c,@Nullable Bundle s){
        b = FragmentListarBinding.inflate(i,c,false); return b.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View v,@Nullable Bundle s){
        vm = new ViewModelProvider(this).get(ListarViewModel.class);
        adapter = new ProductoAdapter(MainActivity.productos, requireContext());
        b.recycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        b.recycler.setAdapter(adapter);
        vm.getProductos().observe(getViewLifecycleOwner(), adapter::actualizarLista);
    }
    @Override public void onResume(){ super.onResume(); vm.refrescar(); }
}

