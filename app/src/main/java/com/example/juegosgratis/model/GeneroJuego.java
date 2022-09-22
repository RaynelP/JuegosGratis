package com.example.juegosgratis.model;

public enum GeneroJuego {
    mmorpg, shooter, strategy, moba, racing, sports, social, sandbox,
    open_world, survival, pvp, pve, pixel, voxel, zombie, turn_based,
    first_person, third_Person, top_down, tank, space, sailing, side_scroller,
    superhero, permadeath, card, battle_royale, mmo, mmofps, mmotps, _3d, _2d,
    anime, fantasy, sci_fi, fighting, action_rpg, action,
    military, martial_arts, flight, low_spec,
    tower_defense, horror, mmorts;

    public String getString(){
        String result = null;
        String s = this.toString();
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if(i == 0 && c[i] == '_'){
                for (int j = 1; j < c.length; j++) {
                    char[] cadenaNueva = new char[c.length - 1];
                    cadenaNueva[j] = c[j];
                    result = String.valueOf(cadenaNueva);
                }
                break;
            }
            if(c[i] == '_'){
                c[i] = '-';
                result = String.valueOf(c);
                break;
            }
        }
        return result;
    }
}