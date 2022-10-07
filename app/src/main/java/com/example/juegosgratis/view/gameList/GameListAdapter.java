package com.example.juegosgratis.view.gameList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.databinding.ViewDataBinding;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juegosgratis.R;
import com.example.juegosgratis.databinding.ItemGameBinding;
import com.example.juegosgratis.databinding.ItemTextCuantityGamesBinding;
import com.example.juegosgratis.model.game.Game;
import com.example.juegosgratis.model.game.GameFavorite;
import com.example.juegosgratis.repository.localDataBase.FuenteDeDatosFavoritosYVistos;
import com.example.juegosgratis.util.Busqueda;
import com.example.juegosgratis.util.UtilNavigate;
import com.example.juegosgratis.view.adapters.genericAdapter.OnclickItemListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.GameHolder> {

    private final int TITTLE_VIEW = 0;
    private final int ELEMENT_VIEW = 1;
    private List<GameFavorite> favoriteList;
    private List<Game> gamesList;


    public GameListAdapter(List<GameFavorite> favoriteList) {
        this.favoriteList = favoriteList;
    }

    @Override
    public GameHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding;
        if (viewType == TITTLE_VIEW) {
            binding = ItemTextCuantityGamesBinding
                    .inflate(inflater, parent, false);
        } else {
            binding = ItemGameBinding
                    .inflate(inflater, parent, false);
        }
        return new GameHolder(binding);
    }

    @Override
    public void onBindViewHolder(GameHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TITTLE_VIEW) {
            holder.bind();
        } else if (viewType == ELEMENT_VIEW) {
            Game game = gamesList.get(position - 1);
            holder.bind(game);
        }

    }

    @Override
    public int getItemCount() {
        return gamesList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TITTLE_VIEW;
        } else {
            return ELEMENT_VIEW;
        }
    }

    public void setGamesList(List<Game> gamesList) {
        this.gamesList = gamesList;
    }




    class GameHolder extends RecyclerView.ViewHolder {

        private ItemGameBinding bindingItem;
        private ItemTextCuantityGamesBinding bindingCount;
        OnclickItemListener<Game> onClick = new OnclickItemListener<Game>() {
            @Override
            public void onClickItem(View v, Game item) {
                if(v.getId() == R.id.bottomFavorite){
                    AppCompatImageButton favoriteButton = (AppCompatImageButton) v;
                    if(bindingItem.getIsFavorite()){
                        FuenteDeDatosFavoritosYVistos
                                .instanciar(favoriteButton.getContext()).deleteGame(item.getId());
                        favoriteButton.setBackground(v.getResources().getDrawable(R.drawable.favorito_off));
                    }else{
                        FuenteDeDatosFavoritosYVistos
                                .instanciar(favoriteButton.getContext())
                                        .addGame(
                                                new GameFavorite(
                                                        item.getThumbnail(),
                                                        item.getTitle(),
                                                        item.getId()
                                                )
                                        );
                        favoriteButton.setBackground(v.getResources().getDrawable(R.drawable.favorito_on));
                    }
                }else{
                    NavController navController =
                            Navigation.findNavController(v);

                    Bundle bundle = new Bundle();
                    bundle.putInt(UtilNavigate.GAME_ID, item.getId());

                    navController
                            .navigate(R.id.action_gamesListFragment_to_fragment_JuegoEnDetalle, bundle);
                }
            }
        };

        public GameHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            if(binding instanceof ItemTextCuantityGamesBinding){
                bindingCount = (ItemTextCuantityGamesBinding) binding;
            }else{
                bindingItem = (ItemGameBinding) binding;
            }
        }

        public void bind(Game item) {
            Picasso.with(bindingItem.image.getContext())
                    .load(item.getThumbnail()).into(bindingItem.image);
            bindingItem.setGame(item);
            bindingItem.setOnClickListener(onClick);

            int index = Busqueda.busquedaLineal(favoriteList, item.getId());
            AppCompatImageButton favoriteButton = bindingItem.bottomFavorite;
            if(index != -1){
                bindingItem.setIsFavorite(true);
                favoriteButton.setBackground(favoriteButton.getResources().getDrawable(R.drawable.favorito_on));
            }else{
                bindingItem.setIsFavorite(false);
                favoriteButton.setBackground(favoriteButton.getResources().getDrawable(R.drawable.favorito_off));
            }
        }

        public void bind() {
            String count = String.valueOf(gamesList.size()) + " elementos encontrados";
            bindingCount.tv.setText(count);
        }

    }

}

