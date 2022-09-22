package com.example.juegosgratis.view.adapters.genericAdapter.holders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.example.juegosgratis.R;
import com.example.juegosgratis.databinding.ItemGenreBottomBinding;
import com.example.juegosgratis.util.UtilNavigate;
import com.example.juegosgratis.view.adapters.genericAdapter.OnclickItemListener;
import com.example.juegosgratis.view.adapters.genericAdapter.ViewData;

public class HolderGenreBottom implements ViewData<String, ItemGenreBottomBinding> {
    @Override
    public void bind(ItemGenreBottomBinding binding, String item, OnclickItemListener<String> onClickListener) {
        binding.setGenre(item);
        binding.setOnClickListener(onClickListener);
    }

    @Override
    public ItemGenreBottomBinding instanceBinding(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return ItemGenreBottomBinding
                .inflate(inflater, parent, false);
    }

    public static class onClick implements OnclickItemListener<String> {

        @Override
        public void onClickItem(View v, String item) {
            NavController navController = Navigation.findNavController(v);

            Bundle bundle = new Bundle();
            bundle.putString(UtilNavigate.TIPO_DE_BUSQUEDA, UtilNavigate.BUSQUEDA_POR_GENERO);
            bundle.putString(UtilNavigate.WORD, item);

            navController.navigate(R.id.action_homeFragment_to_gamesListFragment, bundle);
        }
    }
}
