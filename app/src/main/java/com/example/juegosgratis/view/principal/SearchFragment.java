package com.example.juegosgratis.view.principal;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.juegosgratis.databinding.FragmentSearchBinding;

public class SearchFragment extends Fragment{
    
    private FragmentSearchBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding
                .inflate(inflater, container, false);

        configObservables();
        configViewModel();
        
        return binding.getRoot();
    }

    private void configViewModel() {
    }

    private void configObservables() {
    }

}