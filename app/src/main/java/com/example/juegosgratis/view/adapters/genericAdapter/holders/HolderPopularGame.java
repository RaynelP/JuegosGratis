package com.example.juegosgratis.view.adapters.genericAdapter.holders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.example.juegosgratis.R;
import com.example.juegosgratis.databinding.ItemMorePopularGameBinding;
import com.example.juegosgratis.model.game.Game;
import com.example.juegosgratis.util.UtilNavigate;
import com.example.juegosgratis.view.adapters.genericAdapter.OnclickItemListener;
import com.example.juegosgratis.view.adapters.genericAdapter.ViewData;
import com.squareup.picasso.Picasso;

public class HolderPopularGame implements ViewData<Game, ItemMorePopularGameBinding> {

    @Override
    public void bind(ItemMorePopularGameBinding binding, Game item, OnclickItemListener<Game> onClickListener) {
        Picasso.with(binding.image.getContext())
                .load(item.getThumbnail())
                .into(binding.image);
        binding.setItem(item);
        binding.setOnClickListener(onClickListener);
    }

    @Override
    public ItemMorePopularGameBinding instanceBinding(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return ItemMorePopularGameBinding
                .inflate(inflater, parent, false);
    }

    public static class onClick implements OnclickItemListener<Game>{

        @Override
        public void onClickItem(View v, Game item) {
            NavController navController =
                    Navigation.findNavController(v);

            Bundle bundle = new Bundle();
            bundle.putInt(UtilNavigate.GAME_ID, item.getId());

            navController.navigate(R.id.action_homeFragment_to_fragment_JuegoEnDetalle, bundle);
        }
    }
}



