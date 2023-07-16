package cl.awakelab.recyclerviewexample;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cl.awakelab.recyclerviewexample.databinding.ItemBinding;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<Image> images = new ArrayList<>();

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBinding binding = ItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        Image item = images.get(position);
        holder.showInformation(item);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemBinding imageBinding;

        public ViewHolder(@NonNull ItemBinding binding) {
            super(binding.getRoot());
            imageBinding = binding;
        }

        public void showInformation(Image image){
            imageBinding.textView.setText(image.text);
            Glide.with(imageBinding.getRoot()).load(image.url).into(imageBinding.imageView);
            Bundle bundle = new Bundle();
            bundle.putString("url",image.url);
            bundle.putString("nombre",image.text);
            imageBinding.imageView.setOnClickListener(v -> {
                Navigation.findNavController(itemView).navigate(R.id.action_firtsFragment_to_secondFragment, bundle);
            });
        }
    }


    public void setImages(ArrayList<Image> items) {
        this.images = items;
    }
}
