package com.example.parcialandroid.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcialandroid.R;
import com.example.parcialandroid.model.Producto;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolderProducto> {
    private List<Producto> listado;
    private final LayoutInflater inflater;

    public ProductoAdapter(List<Producto> listado, Context context) {
        this.listado = listado;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolderProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_producto, parent, false);
        return new ViewHolderProducto(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProducto holder, int position) {
        Producto p = listado.get(position);
        holder.tvCodigo.setText("Código: " + p.getCodigo());
        holder.tvDescripcion.setText("Descripción: " + p.getDescripcion());
        holder.tvPrecio.setText("Precio: $ " + p.getPrecio());
    }

    @Override
    public int getItemCount() { return listado.size(); }

    public void actualizarLista(List<Producto> nueva) {
        this.listado = nueva;
        notifyDataSetChanged();
    }

    static class ViewHolderProducto extends RecyclerView.ViewHolder {
        TextView tvCodigo, tvDescripcion, tvPrecio;
        ViewHolderProducto(View v) {
            super(v);
            tvCodigo = v.findViewById(R.id.tvCodigo);
            tvDescripcion = v.findViewById(R.id.tvDescripcion);
            tvPrecio = v.findViewById(R.id.tvPrecio);
        }
    }
}