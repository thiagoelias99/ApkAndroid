package com.example.thiago.app;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import java.util.ArrayList;

public class AnimacaoMario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animacao_mario);

        ImageView imagem = (ImageView) findViewById(R.id.imagemmario);
        AnimationDrawable animacao = (AnimationDrawable) imagem.getDrawable();
        animacao.start();
        animar1(findViewById(R.id.imagemmario));
    }

    public void animar1 (View view){
/*
        TranslateAnimation moverDireita = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,0.0f, Animation.RELATIVE_TO_PARENT,0.65f,// x inicial e final
                Animation.RELATIVE_TO_PARENT,0.0f, Animation.RELATIVE_TO_PARENT,0.0f// y innicial e final
        );
        TranslateAnimation moverBaixo = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,0.65f, Animation.RELATIVE_TO_PARENT,0.65f,// x inicial e final
                Animation.RELATIVE_TO_PARENT,0.0f, Animation.RELATIVE_TO_PARENT,0.75f// y innicial e final
        );
        TranslateAnimation moverEsquerda = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,0.3f, Animation.RELATIVE_TO_PARENT,0.3f,// x inicial e final
                Animation.RELATIVE_TO_PARENT,0.0f, Animation.RELATIVE_TO_PARENT,0.3f// y innicial e final
        );
        TranslateAnimation moverCima = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,0.3f, Animation.RELATIVE_TO_PARENT,0.3f,// x inicial e final
                Animation.RELATIVE_TO_PARENT,0.0f, Animation.RELATIVE_TO_PARENT,0.3f// y innicial e final
        );
        TranslateAnimation moverCentro = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT,0.3f, Animation.RELATIVE_TO_PARENT,0.3f,// x inicial e final
                Animation.RELATIVE_TO_PARENT,0.0f, Animation.RELATIVE_TO_PARENT,0.3f// y innicial e final
        );


        Animation animation1 = moverDireita;
        Animation animation2 = moverBaixo;

        animation1.setDuration(3000);
        animation1.setFillAfter(true);
        view.startAnimation(animation1);

        animation2.setDuration(3000);
        view.startAnimation(animation2);
*/
        ObjectAnimator moverDireita = ObjectAnimator.ofFloat(view, "x",0,550);
        ObjectAnimator moverBaixo = ObjectAnimator.ofFloat(view, "y",0,800);
        ObjectAnimator moverEsquerda = ObjectAnimator.ofFloat(view, "x",550,0);
        ObjectAnimator moverCima = ObjectAnimator.ofFloat(view, "y",800,0);
        ObjectAnimator moverCentroX = ObjectAnimator.ofFloat(view, "x",0,275);
        ObjectAnimator moverCentroY = ObjectAnimator.ofFloat(view, "y",0,400);
        ObjectAnimator rodarBaixo = ObjectAnimator.ofFloat(view, "rotation",0,90);
        ObjectAnimator rodarEsquerda = ObjectAnimator.ofFloat(view, "rotation",90,180);
        ObjectAnimator rodarCima = ObjectAnimator.ofFloat(view, "rotation",180,270);
        ObjectAnimator rodarDireita = ObjectAnimator.ofFloat(view, "rotation",270,360);


        ArrayList<Animator> animatorArrayList = new ArrayList<Animator>();
        AnimatorSet listaAnimacao2 = new AnimatorSet();
        listaAnimacao2.playTogether(moverCentroX,moverCentroY);

        animatorArrayList.add(moverDireita);
        animatorArrayList.add(rodarBaixo);
        animatorArrayList.add(moverBaixo);
        animatorArrayList.add(rodarEsquerda);
        animatorArrayList.add(moverEsquerda);
        animatorArrayList.add(rodarCima);
        animatorArrayList.add(moverCima);
        animatorArrayList.add(rodarDireita);
        animatorArrayList.add(listaAnimacao2);

        AnimatorSet listaAnimacao = new AnimatorSet();
        listaAnimacao.playSequentially(animatorArrayList);

        listaAnimacao.setDuration(3000);
        listaAnimacao.start();
    }

}
