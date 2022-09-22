package com.example.juegosgratis.view.adapters.genericAdapter.holders;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.juegosgratis.databinding.ItemImagesBinding;
import com.example.juegosgratis.view.adapters.genericAdapter.OnclickItemListener;
import com.example.juegosgratis.view.adapters.genericAdapter.ViewData;
import com.squareup.picasso.Picasso;

public class HolderImage implements ViewData<String, ItemImagesBinding> {
    @Override
    public void bind(ItemImagesBinding binding, String item, OnclickItemListener<String> onClickListener) {
        Picasso.with(binding.image.getContext())
                .load(item)
                .into(binding.image);
    }

    @Override
    public ItemImagesBinding instanceBinding(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return ItemImagesBinding.inflate(inflater, parent, false);
    }
}
