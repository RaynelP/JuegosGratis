 package com.example.juegosgratis.view.gameDetail;

/*public class Fragment_ImagenExpandida extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        juegosViewModel = new ViewModelProvider(requireActivity()).get(GameListViewModel.class);
        ocultarToolbar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_imagen_expandida, container, false);
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        referenciarViews(v);
        configData();
        configViews();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        int posicionSeleccionada = requireArguments().getInt(Fragment_JuegoEnDetalle.IMAGEN_SELECCIONADA);
        viewPagerImagenesExpandidasJuego.setCurrentItem(posicionSeleccionada);
    }

    private void referenciarViews(View v){
        viewPagerImagenesExpandidasJuego = v.findViewById(R.id.viewPagerImagenesExpandidas);
        botonRetroceso = v.findViewById(R.id.botonRetrocesoImagenExpandida);
    }

    private void configViews(){
        botonRetroceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });
    }

    private void configData(){
        //AdapterPageImages adapterPager = new AdapterPageImages(juegosViewModel.obtenerJuegoActual(), getContext());
        //viewPagerImagenesExpandidasJuego.setAdapter(adapterPager);
    }

    private void ocultarToolbar(){
        JuegosActivity activity = (JuegosActivity) getActivity();
        activity.getSupportActionBar().hide();
    }

    private void desOcultarToolbar(){
        JuegosActivity activity = (JuegosActivity) getActivity();
        activity.getSupportActionBar().show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        desOcultarToolbar();
    }

    private ViewPager2 viewPagerImagenesExpandidasJuego;
    private Button botonRetroceso;
    private GameListViewModel juegosViewModel;
}*/