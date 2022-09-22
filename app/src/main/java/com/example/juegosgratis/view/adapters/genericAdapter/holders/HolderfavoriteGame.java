package com.example.juegosgratis.view.adapters.genericAdapter.holders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.example.juegosgratis.R;
import com.example.juegosgratis.databinding.ItemFavoriteGameBinding;
import com.example.juegosgratis.model.game.GameFavorite;
import com.example.juegosgratis.util.UtilNavigate;
import com.example.juegosgratis.view.adapters.genericAdapter.OnclickItemListener;
import com.example.juegosgratis.view.adapters.genericAdapter.ViewData;
import com.squareup.picasso.Picasso;

public class HolderfavoriteGame implements ViewData<GameFavorite, ItemFavoriteGameBinding> {
    @Override
    public void bind(ItemFavoriteGameBinding binding, GameFavorite item, OnclickItemListener<GameFavorite> onClickListener) {
        Picasso.with(binding.cimImagenCircular.getContext())
                .load(item.getImagen()).into(binding.cimImagenCircular);
        binding.tvNombreJuegoFavorito.setText(item.getNombre());
        binding.setOnClickListener(onClickListener);
    }

    @Override
    public ItemFavoriteGameBinding instanceBinding(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater
                .from(parent.getContext());

        return ItemFavoriteGameBinding
                .inflate(inflater, parent, false);
    }

    public static class onClick implements OnclickItemListener<GameFavorite>{

        @Override
        public void onClickItem(View v, GameFavorite item) {
            NavController navController =
                    Navigation.findNavController(v);

            Bundle bundle = new Bundle();
            bundle.putInt(UtilNavigate.GAME_ID, item.getId());

            navController.navigate(R.id.action_homeFragment_to_fragment_JuegoEnDetalle, bundle);
        }
    }
}
