package com.example.juegosgratis.view.gameDetail;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.juegosgratis.R;
import com.example.juegosgratis.databinding.FragmentGamedetailBinding;
import com.example.juegosgratis.model.game.ScreenShot;
import com.example.juegosgratis.repository.network.Interfaces.IGameRepository;
import com.example.juegosgratis.repository.network.implementations.GameRepositoryRetrofit;
import com.example.juegosgratis.util.UtilNavigate;
import com.example.juegosgratis.viewModel.GameDetailsViewModel;
import com.example.juegosgratis.viewModel.GameDetailsViewModelFactory;
import com.example.juegosgratis.model.game.GameDetail;
import com.squareup.picasso.Picasso;
import java.util.List;

public class GameDetailFragment extends Fragment implements View.OnClickListener {

    private GameDetailsViewModel viewModel;
    private FragmentGamedetailBinding binding;
    private boolean controladorDescripcionDesplegada;
    private boolean controladorRequisitosDesplegada;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentGamedetailBinding
                .inflate(getLayoutInflater());

        controladorDescripcionDesplegada = false;
        controladorRequisitosDesplegada = false;
        binding.bottonLaunchDescription.setOnClickListener(this);
        binding.bottonLaunchRequeriments.setOnClickListener(this);
        configViewModel();
        configObservers();
        return binding.getRoot();
    }

    private void configViewModel() {
        IGameRepository repository = new GameRepositoryRetrofit();
        int gameId = requireArguments().getInt(UtilNavigate.GAME_ID);

        GameDetailsViewModelFactory factory =
                new GameDetailsViewModelFactory(repository, gameId);

        viewModel = new ViewModelProvider(this, factory)
                .get(GameDetailsViewModel.class);
    }

    private void configObservers() {
        viewModel.getGameDetail().observe(getViewLifecycleOwner(), gameDetail -> {
            if(gameDetail != null){
                configVistas(gameDetail);
            }
        });

    }

    private void configVistas(GameDetail fullGame) {
        showImage(binding.imageView,
                fullGame.getGame().getThumbnail());
        binding.setGame(fullGame);


        List<ScreenShot> images = fullGame.getImages();
        ImageView[] imagesViews = {
                binding.imageView1,
                binding.imageView2,
                binding.imageView3,
                binding.imageView4
        };

        int size = fullGame.getImages().size();
        for (int i = 0; i < 4 && i < size; i++) {
            imagesViews[i].setVisibility(View.VISIBLE);
            showImage(imagesViews[i],
                    images.get(i).getImage());
        }
        if (size == 0) {
            binding.cardGameImages.setVisibility(View.GONE);
        }
        binding.setGame(fullGame);
    }



    @Override
    public void onClick(View v) {
        if (v.getId() == binding.bottonLaunchDescription.getId()) {
            desplegarEsconderDescripcion();
        } else {
            if (v.getId() == binding.bottonLaunchRequeriments.getId()) {
                desplegarEsconderRequisitos();
            }/*else {
                ArrayList<ImageView> arrayImagenes = new ArrayList<>();
                arrayImagenes.addAll(imagenes);
                for (int i = 0; i < arrayImagenes.size(); i++) {
                    if(v.getId() == arrayImagenes.get(i).getId()){
                        expandirImagen(arrayImagenes, i);
                    }
                }
            }*/
        }
    }

    private void desplegarEsconderDescripcion() {
        if (controladorDescripcionDesplegada) {
            binding.bottonLaunchDescription.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_baseline_arrow_drop_down_24));
            binding.windowDescription.setVisibility(View.GONE);
            controladorDescripcionDesplegada = false;
        } else {
            binding.bottonLaunchDescription.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_baseline_arrow_drop_up_24));
            binding.windowDescription.setVisibility(View.VISIBLE);
            controladorDescripcionDesplegada = true;
        }
    }
//
    private void desplegarEsconderRequisitos() {
        if (controladorRequisitosDesplegada) {
            binding.bottonLaunchRequeriments.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_baseline_arrow_drop_down_24));
            binding.windowMinimRequeriments.setVisibility(View.GONE);
            controladorRequisitosDesplegada = false;
        } else {
            binding.bottonLaunchRequeriments.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_baseline_arrow_drop_up_24));
            binding.windowMinimRequeriments.setVisibility(View.VISIBLE);
            controladorRequisitosDesplegada = true;
        }
    }

    private void showImage(ImageView imageView, String image){
        Picasso.with(getContext())
                .load(image)
                .into(imageView);
    }
}