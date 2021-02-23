package com.example.vinoteca.views;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vinoteca.R;
import com.example.vinoteca.models.WineEntity;

import java.util.ArrayList;

public class WineAdapter extends RecyclerView.Adapter<WineAdapter.WineViewHolder>
        implements View.OnClickListener{

    private ArrayList<WineEntity> items;
    private View.OnClickListener listener;

    // Clase interna:
    // Se implementa el ViewHolder que se encargarÃ¡
    // de almacenar la vista del elemento y sus datos
    public static class WineViewHolder
            extends RecyclerView.ViewHolder {
        private ImageView Image_profile;
        private TextView TextName;
        private TextView TextDenomination;

        public WineViewHolder(View itemView) {
            super(itemView);
            Image_profile=(ImageView) itemView.findViewById(R.id.image_profile);
            TextName = (TextView) itemView.findViewById(R.id.textView1);
            TextDenomination = (TextView) itemView.findViewById(R.id.textView2);
        }

        public void WineBind(WineEntity item) {
            if(item.getImage()!=null && item.getImage()!="") {
                byte[] decodedString = Base64.decode(item.getImage(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                Image_profile.setImageBitmap(decodedByte);
                Image_profile.setBackground(null);
            }
            TextName.setText(item.getName());
            TextDenomination.setText(item.getDenomination());
        }
    }

    // Contruye el objeto adaptador recibiendo la lista de datos
    public WineAdapter(@NonNull ArrayList<WineEntity> items) {
        this.items = items;
    }

    // Se encarga de crear los nuevos objetos ViewHolder necesarios
    // para los elementos de la colecciÃ³n.
    // Infla la vista del layout, crea y devuelve el objeto ViewHolder
    @Override
    public WineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        row.setOnClickListener(this);

        WineViewHolder wineviewholder = new WineViewHolder(row);
        return wineviewholder;
    }

    // Se encarga de actualizar los datos de un ViewHolder ya existente.
    @Override
    public void onBindViewHolder(WineViewHolder viewHolder, int position) {
        WineEntity item = items.get(position);
        viewHolder.WineBind(item);
    }

    // Indica el nÃºmero de elementos de la colecciÃ³n de datos.
    @Override
    public int getItemCount() {
        if(items!=null) {
            return items.size();
        }
        return 0;
    }

    // Asigna un listener al elemento
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }
}
