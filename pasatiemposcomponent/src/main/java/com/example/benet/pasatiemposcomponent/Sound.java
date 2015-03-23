package com.example.benet.pasatiemposcomponent;

import android.app.Activity;
import android.media.MediaPlayer;
import android.util.Log;

/**
 * Created by Benet on 21/03/15.
 */
public class Sound{

    private int pista_fondo=-1, pista_disparo=-1, pista_risa=-1, pista_muerte=-1;
    private pasatiemposView context;
    private Activity activity;
    private MediaPlayer mediaPlayerFondo, mediaPlayerDisparo, mediaPlayerRisa, mediaPlayerMuerte;
    private boolean activate=true;

    //Constructores
    public Sound(pasatiemposView v){this.context=v;}

    public Sound(pasatiemposView v,Activity activity,int sound){
        this.pista_fondo=sound;
        this.context=v;
        this.activity=activity;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    //methods play and stop
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public void startSoundFondo(){
        if(activity!=null && pista_fondo!=-1 && activate) {
            mediaPlayerFondo = MediaPlayer.create(activity, pista_fondo);
            mediaPlayerFondo.start();
            Log.e("SOUND", "START");
        }else{
            Log.e("SOUND","not found activity or pista or silence presed");
        }

    }

    public void stopSoundFondo(){if(mediaPlayerFondo.isPlaying())mediaPlayerFondo.stop();}

    public void startSoundDisparo(){
        if(activity!=null && pista_disparo!=-1 && activate) {
            mediaPlayerDisparo = MediaPlayer.create(activity, pista_disparo);
            try {
                mediaPlayerDisparo.start();
                Log.e("SOUND", "START");
            }catch(NullPointerException e){

            }
        }else{
            Log.e("SOUND","not found activity or pista or silence presed");
        }

    }

    public void stopSoundDisparo(){if(mediaPlayerDisparo.isPlaying())mediaPlayerDisparo.stop();}

    public void startSoundMuerte(){
        if(activity!=null && pista_muerte!=-1 && activate) {
            mediaPlayerMuerte = MediaPlayer.create(activity, pista_muerte);
            try {
                mediaPlayerMuerte.start();
                Log.e("SOUND", "START");
            }catch(NullPointerException e){

            }
        }else{
            Log.e("SOUND","not found activity or pista or silence presed");
        }

    }

    public void stopSoundMuerte(){if(mediaPlayerMuerte.isPlaying())mediaPlayerMuerte.stop();}

    public void startSoundRisa(){
        if(activity!=null && pista_risa!=-1 && activate) {
            mediaPlayerRisa = new MediaPlayer();
            mediaPlayerRisa=MediaPlayer.create(activity, pista_risa);
            try {
                mediaPlayerRisa.start();
                Log.e("SOUND", "START");
            }catch(NullPointerException e){

            }
        }else{
            Log.e("SOUND","not found activity or pista or silence presed");
        }

    }
    public void stopSoundRisa(){if(mediaPlayerRisa.isPlaying())mediaPlayerRisa.stop();}
    public void muteAllSounds(){
        activate=false;
        if(mediaPlayerDisparo!=null)stopSoundDisparo();
        if(mediaPlayerFondo!=null)stopSoundFondo();
        if(mediaPlayerMuerte!=null)stopSoundMuerte();
        if(mediaPlayerRisa!=null)stopSoundRisa();
    }
    public void activeAllSounds(){
        activate=true;
        startSoundFondo();
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    //Seters and geters
    ///////////////////////////////////////////////////////////////////////////////////////////////

    public void setActivity(Activity activity){this.activity=activity;}
    public Activity getActivity(){return activity;}

    public void setPistaFondo(int pista){this.pista_fondo=pista;}
    public int getPistaFondo(){return pista_fondo;}

    public MediaPlayer getMediaPlayerFondo(){return mediaPlayerFondo;}
    public MediaPlayer getMediaPlayerDisparo(){return mediaPlayerDisparo;}
    public MediaPlayer getMediaPlayerRisa(){return mediaPlayerRisa;}
    public MediaPlayer getMediaPlayerMuerte(){return mediaPlayerMuerte;}

    public int getPista_disparo() {return pista_disparo;}
    public void setPista_disparo(int pista_disparo) {this.pista_disparo = pista_disparo;}

    public int getPista_risa() {return pista_risa;}
    public void setPista_risa(int pista_risa) {this.pista_risa = pista_risa;}

    public int getPista_muerte() {return pista_muerte;}
    public void setPista_muerte(int pista_muerte) {this.pista_muerte = pista_muerte;}

    public void setPista_fondo(int pista_fondo){this.pista_fondo=pista_fondo;}
    public int getPista_fondo(){return pista_fondo;}
}
