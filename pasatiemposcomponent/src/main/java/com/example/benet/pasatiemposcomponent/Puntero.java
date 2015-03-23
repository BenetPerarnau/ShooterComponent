package com.example.benet.pasatiemposcomponent;

import android.app.Activity;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Benet on 19/03/15.
 */
public class Puntero {

    private final static String TAG_CLASS="Puntero";

    private int cXC=-1, cYC=-1, radio=100;
    private Paint paintCercle, paintPunto;
    private NotificByPuntero mListener;
    private pasatiemposView view;
    private Cronometro cronometro;


    public Puntero(pasatiemposView view){
        this.view=view;
        initComponents();
    }

    public void initComponents(){
        cronometro=view.getCronometro();
    }


    public void setOnNotificShoot(Activity activity){mListener=(NotificByPuntero)activity;}
    /*
           MOVE CERCLE
    */
    public void moveCercleCordenadaX(int x){
        if(cronometro.isPausado()){cronometro.setPausado(false);}
        Log.i(TAG_CLASS, "Valor en moveCercle X=> " + x);
        if(x<0){
            //intent move left
            if((cXC+x)>radio){
                cXC=cXC+x;
                view.repaintComponent();
            }else{
                Log.e(TAG_CLASS,"Error el desplazamiento deseado sale del tamaño de la pantalla.");
            }
        }else{
            //intent move right
            if((cXC+x)<(view.getContentWidth()-radio)){
                cXC=cXC+x;
                view.repaintComponent();
            }else{
                Log.e(TAG_CLASS,"Error el desplazamiento deseado sale del tamaño de la pantalla.");
            }
        }
    }

    public void moveCercleCordenadaY(int y){
        if(cronometro.isPausado()){cronometro.setPausado(false);}
        Log.i(TAG_CLASS,"Valor en moveCercleY => "+y);
        if(y<0){
            //up
            if((cYC+y)>radio){
                cYC=cYC+y;
                view.repaintComponent();
            }else{
                Log.e(TAG_CLASS,"Error el desplazamiento deseado sale del tamaño de la pantalla.");
            }
        }else{
            //low
            if((cYC+y)<view.getContentHeight()-radio){
                cYC=cYC+y;
                view.repaintComponent();
            }else{
                Log.e(TAG_CLASS,"Error el desplazamiento deseado sale del tamaño de la pantalla.");
            }
        }
    }


    /*
    Method mirar si al pulsar disparar esta dendro de los margenes de error del centro de la imagen.
 */

    public void isKill(){
        if(cronometro.isPausado()){cronometro.setPausado(false);}
        //Log.i(TAG_CLASS,"isKill? cXC:"+cXC+" cYC:"+cYC+" cXB:"+cXB+bitmap.getWidth()/2+" cYB:"+cYB+bitmap.getHeight()/2);

        //Sound disparo
        view.getSound().setPista_disparo(R.raw.pista_fusil);
        view.getSound().startSoundDisparo();

        view.getStatus().addValueShoot();

        if(cXC<=view.getDiana().getcXB()+view.getDiana().getImg_diana().getWidth()/2+10 &&
           cXC>=view.getDiana().getcXB()+view.getDiana().getImg_diana().getWidth()/2-10 &&
           cYC<=view.getDiana().getcYB()+view.getDiana().getImg_diana().getHeight()/2+10 &&
           cYC>=view.getDiana().getcYB()+view.getDiana().getImg_diana().getHeight()/2-10 ){

            //Sound muerte
            view.getSound().setPista_muerte(R.raw.pista_muerto);
            view.getSound().startSoundMuerte();

            //sang=true;
            view.repaintComponent();

            //sang=false;

            view.getDiana().setcXB((int)(Math.random()*view.getContentWidth()));
            view.getDiana().setcYB((int)(Math.random()*view.getContentHeight()));

            if(view.getDiana().getcXB()+view.getDiana().getImg_diana().getWidth()>view.getContentWidth()){
                view.getDiana().setcXB(view.getContentWidth()-view.getDiana().getImg_diana().getWidth()-20);
            }
            if(view.getDiana().getcYB()+view.getDiana().getImg_diana().getHeight()>view.getContentHeight()){
                view.getDiana().setcYB(view.getContentHeight()-view.getDiana().getImg_diana().getHeight()-20);
            }

            view.getStatus().addValueKill();

            if(mListener!=null)mListener.notificShoot(true);

        }else{
            //Sound risa
            view.getSound().setPista_risa(R.raw.pista_risas);
            view.getSound().startSoundRisa();

            if(mListener!=null){mListener.notificShoot(false);}
        }
        view.getStatus().refeshPPEncerts();
        view.repaintComponent();
    }


    public void initPaints(){

        BlurMaskFilter filter=new BlurMaskFilter(1, BlurMaskFilter.Blur.OUTER);
        paintCercle =new Paint();
        paintCercle.setAntiAlias(true);
        paintCercle.setColor(Color.BLUE);
        //paintCercle.setMaskFilter(filter);
        paintCercle.setStyle(Paint.Style.STROKE);
        paintCercle.setStrokeWidth(10);

        paintPunto=new Paint();
        paintPunto.setAntiAlias(true);
        paintPunto.setColor(Color.RED);
        paintPunto.setStrokeWidth(10);

    }


    //GETERS AND SETERS
    public int getcXC() {return cXC;}
    public int getcYC() {return cYC;}
    public int getRadio() {return radio;}
    public Paint getPaintCercle() {return paintCercle;}
    public Paint getPaintPunto() {return paintPunto;}

    public void setcXC(int cXC) {
        this.cXC = cXC;
    }
    public void setcYC(int cYC) {
        this.cYC = cYC;
    }
    public void setRadio(int radio) {
        this.radio = radio;
    }
    public void setPaintCercle(Paint paintCercle) {
        this.paintCercle = paintCercle;
    }
    public void setPaintPunto(Paint paintPunto) {
        this.paintPunto = paintPunto;
    }

    //Interface
    public interface NotificByPuntero{
        public void notificShoot(boolean kill);
    }
}
