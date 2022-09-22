package com.example.juegosgratis.view.principal;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.juegosgratis.databinding.FragmentHomeBinding;
import com.example.juegosgratis.databinding.ItemGenreBottomBinding;
import com.example.juegosgratis.databinding.ItemImagesBinding;
import com.example.juegosgratis.databinding.ItemMorePopularGameBinding;
import com.example.juegosgratis.util.Filtros;
import com.example.juegosgratis.util.UtilNavigate;
import com.example.juegosgratis.view.adapters.genericAdapter.GenericAdapter;
import com.example.juegosgratis.view.adapters.genericAdapter.holders.HolderGenreBottom;
import com.example.juegosgratis.view.adapters.genericAdapter.holders.HolderImage;
import com.example.juegosgratis.view.adapters.genericAdapter.holders.HolderPopularGame;
import com.example.juegosgratis.viewModel.MainViewModel;
import com.example.juegosgratis.R;
import com.example.juegosgratis.model.game.Game;
import com.google.android.material.snackbar.Snackbar;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private MainViewModel gameViewModel;
    private FragmentHomeBinding binding;
    String TAG = "FRAGMENT INICIO";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        //create dataBinding
        binding = FragmentHomeBinding
                .inflate(inflater, container, false);

        configViewModel();
        configObservers();
        showBottonGenrer();

        return binding.getRoot();
    }

    private void configViewModel() {
        gameViewModel = new ViewModelProvider(this)
                .get(MainViewModel.class);
        binding.setViewModel(gameViewModel);
    }

    private void configObservers() {
        gameViewModel.getAllGamesList().observe(getViewLifecycleOwner(), gameList -> {
            if(gameList != null){
                ArrayList<Game> randomGameList =
                        (ArrayList<Game>) Filtros.filtrasTresJuegosAleatorios(gameList);
                loadImages(randomGameList);

                ArrayList<Game> morePopularGames =
                        (ArrayList<Game>) Filtros.filtrarPrimerosDiezJuegos(gameList);
                loadPopularsGames(morePopularGames);
            }
        });

        gameViewModel.getNavigateToPopularGamesList().observe(getViewLifecycleOwner(), navigate -> {
            if(navigate != null && navigate){
                UtilNavigate.navigate(
                        binding.getRoot(),
                        R.id.action_homeFragment_to_gamesListFragment,
                        UtilNavigate.instanceBundleToGameList(UtilNavigate.BUSQUEDA_POR_ORDEN, UtilNavigate.FAVORITOS));
                gameViewModel.doneNavigateToGamesList();
            }
        });

        gameViewModel.getError().observe(getViewLifecycleOwner(), aBoolean -> {
            if(aBoolean != null){
                if(aBoolean){
                    showErrorMenssage();
                }
            }
        });

    }

    private void loadImages(List<Game> gamesList){
        List<String> images = Arrays.asList(
                gamesList.get(0).getThumbnail(),
                gamesList.get(1).getThumbnail(),
                gamesList.get(2).getThumbnail()
        );

        GenericAdapter<String, ItemImagesBinding> adapterPager =
                new GenericAdapter.Builder<String, ItemImagesBinding>()
                        .addList(images)
                        .addViewData(new HolderImage())
                        .build();

        binding.vpImages.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                paintPoint(position);
            }
        });

        binding.vpImages.setAdapter(adapterPager);
    }

    private void loadPopularsGames(List<Game> gameList){
        GenericAdapter<Game, ItemMorePopularGameBinding> adapter =
                new GenericAdapter.Builder<Game, ItemMorePopularGameBinding>()
                .addList(gameList)
                .addViewData(new HolderPopularGame())
                .addOnClickListener(new HolderPopularGame.onClick())
                .build();

        binding.rvPopularGames.setAdapter(adapter);
    }

    private void showBottonGenrer() {
        List<String> genres = gameViewModel.getListaDeGenerosReducida();
        GenericAdapter<String, ItemGenreBottomBinding> adapter =
                new GenericAdapter.Builder<String, ItemGenreBottomBinding>()
                        .addList(genres)
                        .addViewData(new HolderGenreBottom())
                        .addOnClickListener(new HolderGenreBottom.onClick())
                        .build();;
        binding.rvGenreBottoms.setAdapter(adapter);
    }

    private void paintPoint(int point){
        int ITEM1 = 0;
        binding.point1.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), getColor(ITEM1, point)));
        int ITEM2 = 1;
        binding.point2.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), getColor(ITEM2, point)));
        int ITEM3 = 2;
        binding.point3.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), getColor(ITEM3, point)));
    }

    private int getColor(int actualPoint, int selectPoint){
        if(actualPoint == selectPoint){
            return R.color.purple_500;
        }else{
            return  R.color.gris;
        }
    }

    private void showErrorMenssage(){
        Snackbar errorMensage = Snackbar
                .make(binding.getRoot(),
                        "Ha ocurrido un error",
                        Snackbar.LENGTH_INDEFINITE)
                .setAction("Reintentar", view -> {
                    gameViewModel.retry();
                });
        errorMensage.show();
    }

}