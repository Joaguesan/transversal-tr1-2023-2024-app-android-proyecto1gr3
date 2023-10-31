package com.example.prtakeaway;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    public interface OnPedidoItemClickListener {
        void onPedidoItemClick(Pedidos.Pedido pedido);
    }

    private OnPedidoItemClickListener listener;

    // Constructor del adaptador que recibe el listener
    public CustomAdapter(OnPedidoItemClickListener listener) {
        this.listener = listener;
    }
    private List<Pedidos.Pedido> localDataSet;


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView, textView2,editText ;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            textView = view.findViewById(R.id.textView);
            editText = view.findViewById(R.id.editText);;
            textView2 = view.findViewById(R.id.textView2);;

        }

        public TextView getTextView() {
            return textView;
        }
        public TextView getTextView2() {
            return textView2;
        }
        public TextView getEditView() {
            return editText;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public CustomAdapter(List<Pedidos.Pedido> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(view);
    }
    public void actualizarPedidos(List<Pedidos.Pedido> nuevopedido) {
        localDataSet.clear(); // Limpia la lista actual
        localDataSet.addAll(nuevopedido); // Agrega los nuevos productos
        notifyDataSetChanged(); // Notifica al adaptador que los datos han cambiado
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Pedidos.Pedido pedido = localDataSet.get(position);
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextView().setText(String.valueOf(pedido.getIDPedido()));
        switch (pedido.getEstado()){
            case "Tancades":
                viewHolder.getTextView2().setBackgroundResource(R.color.cerrado);
                break;
            case "Acceptades":
                viewHolder.getTextView2().setBackgroundResource(R.color.aceptado);
                break;
            case "Pendents":
                viewHolder.getTextView2().setBackgroundResource(R.color.pendiente);
                break;
            case "Rebutjats":
                viewHolder.getTextView2().setBackgroundResource(R.color.rechazado);
        }
        viewHolder.getEditView().setText(pedido.getComentario());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onPedidoItemClick(pedido);
                }
            }
        });
    }




    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
