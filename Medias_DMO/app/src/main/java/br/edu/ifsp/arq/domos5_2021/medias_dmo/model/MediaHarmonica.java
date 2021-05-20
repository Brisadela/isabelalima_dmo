package br.edu.ifsp.arq.domos5_2021.medias_dmo.model;

import java.util.List;

public class MediaHarmonica implements MediaStrategy{

    private List<Double> values;

    public MediaHarmonica(List<Double> values) {
        this.values = values;
    }

    @Override
    public Double calcularMedia() {
        //TODO implementar o método.
        double mh = 0D;
        for(Double d : values){
            mh += 1/d;
        }
        return values.size()/mh;
    }
}
