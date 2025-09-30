package com.example.parcialandroid.view.modificar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.parcialandroid.R;
import com.example.parcialandroid.databinding.FragmentModificarBinding;

public class ModificarFragment extends Fragment {

    private FragmentModificarBinding b;
    private ModificarViewModel vm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        b = FragmentModificarBinding.inflate(inflater, container, false);
        return b.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {

        vm = new ViewModelProvider(this).get(ModificarViewModel.class);


        vm.getMensaje().observe(getViewLifecycleOwner(), msg -> {
            if (msg != null && !msg.isEmpty()) {
                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });


        vm.getProductoEncontrado().observe(getViewLifecycleOwner(), producto -> {
            if (producto != null) {
                Bundle args = new Bundle();
                args.putSerializable("producto", producto);
                Navigation.findNavController(view)
                        .navigate(R.id.action_modificar_to_detalle, args);
            }
        });


        b.btnBuscar.setOnClickListener(v ->
                vm.buscar(b.etCodigo.getText().toString())
        );
    }
}