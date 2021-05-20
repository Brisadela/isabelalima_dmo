package br.edu.ifsp.arq.domos5_2021.medias_dmo.controller;



import java.util.List;

import br.edu.ifsp.arq.domos5_2021.medias_dmo.model.MediaAritmetica;
import br.edu.ifsp.arq.domos5_2021.medias_dmo.model.MediaHarmonica;
import br.edu.ifsp.arq.domos5_2021.medias_dmo.model.MediaPonderada;
import br.edu.ifsp.arq.domos5_2021.medias_dmo.model.MediaStrategy;

public class MediaController {

    public static Double mediaPonderada(/*Indicar argumentos, se necessário*/){
        MediaPonderada ponderada = null;
        //TODO implementar o restante do método
        return media(ponderada);
    }

    public static Double mediaArimetica(double n1,double n2,double n3,double n4,double n5){
        MediaAritmetica aritmetica = null;
        //TODO implementar o restante do método

        aritmetica = new MediaAritmetica(n1,n2,n3,n4,n5);
        aritmetica.calcularMedia();
        return media(aritmetica);
    }

    public static Double mediaHarmonica(List<Double> values){
        MediaHarmonica harmonica = null;
        //TODO implementar o restante do método
        harmonica = new MediaHarmonica(values);
        harmonica.calcularMedia();
        return media(harmonica);
    }

    private static Double media(MediaStrategy mediaStrategy){
        return mediaStrategy.calcularMedia();
    }

}
