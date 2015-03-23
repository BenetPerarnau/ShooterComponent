package com.example.benet.pasatiemposcomponent;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.text.DecimalFormat;

/**
 * Created by Benet on 19/03/15.
 */
public class Status {

    private notificStatusChange mListener;

    private pasatiemposView context;
    //0)Status
    private DecimalFormat f=new DecimalFormat("0.00");
    private Paint paintTextKills, paintValuesKills, paintTextCrono;
    //0.1 Texts
    private String crono;
    private  String KILLS;
    private  String SHOOT;
    private  String ENCERTS;
    //0.2 Values
    private int value_kills_status;
    private int value_shoots_status;
    private float value_encerts_status;
    //0.3 Sizes
    private int size_texts_status, size_values_status, size_text_crono;
    //0.4 Colors
    private int colorText_status, colorValues_status, colorText_crono;
    //Margins
    private int marginRight;

    public Status(pasatiemposView v) {
        this.context=v;
        KILLS=v.getResources().getString(R.string.kills_text_status);
        SHOOT=v.getResources().getString(R.string.shoots_text_status);
        ENCERTS=v.getResources().getString(R.string.prp_text_status);
        initComponents();
    }

    public void initComponents(){
        crono="00:00:00";
        marginRight=16;
        value_kills_status =0;
        value_shoots_status =0;
        value_encerts_status =100;
        size_texts_status=25;
        size_values_status=30;
        size_text_crono=35;
        colorText_status= Color.BLACK;
        colorValues_status=Color.BLACK;
        colorText_crono=Color.BLACK;
        initPaintsStatus();
    }

    public void initPaintsStatus(){
        paintTextKills=new Paint();
        paintValuesKills=new Paint();
        paintTextCrono=new Paint();

        paintTextKills.setAntiAlias(true);
        paintTextKills.setTextSize(size_texts_status);
        paintTextKills.setColor(colorText_status);

        paintValuesKills.setAntiAlias(true);
        paintValuesKills.setTextSize(size_values_status);
        paintValuesKills.setColor(colorText_status);

        paintTextCrono.setAntiAlias(true);
        paintTextCrono.setTextSize(size_text_crono);
        paintTextCrono.setColor(colorText_crono);
    }

    public void addValueKill(){
        value_kills_status++;
    }
    public void addValueShoot(){
        value_shoots_status++;
    }
    public void refeshPPEncerts(){
        value_encerts_status =(((float) value_kills_status /(float) value_shoots_status)*100);
        try {
            value_encerts_status = Float.parseFloat(f.format(value_encerts_status));
        }catch(NumberFormatException e){
            Log.e("Format % encerts", "" + e.getMessage());
        }
        if(mListener!=null){
            mListener.statusChange(getKILLS(),getValue_kills_status(),getSHOOT(),getValue_shoots_status(),getENCERTS(),getValue_encerts_status());
        }else{
            Log.e("STATUS","Status class don't have mListener. Call method setOnnotificStatusChange().");
        }
    }

    public Paint getPaintTextStatus(){return  paintTextKills;}
    public Paint getPaintValueTextStatus(){return paintValuesKills;}
    public Paint getPaintTextCrono() {return paintTextCrono;}
    public String getCrono() {return crono;}
    public int getColorText_crono() {return colorText_crono;}

    public String getKILLS() {return KILLS;}
    public String getSHOOT() {return SHOOT;}
    public String getENCERTS() {return ENCERTS;}
    public int getColorText_status() {return colorText_status;}
    public float getValue_encerts_status() {return value_encerts_status;}
    public int getValue_shoots_status() {return value_shoots_status;}
    public int getValue_kills_status() {return value_kills_status;}
    public int getSize_text_crono() {return size_text_crono;}


    public void setKILLS(String KILLS) {
        this.KILLS = KILLS;
    }
    public void setSHOOT(String SHOOT) {
        this.SHOOT = SHOOT;
    }
    public void setENCERTS(String ENCERTS) {
        this.ENCERTS = ENCERTS;
    }
    public void setCrono(String crono) {
        this.crono = crono;
    }
    public void setColorText_status(int colorText_status) {this.colorText_status = colorText_status;}
    public void setValue_encerts_status(float value_encerts_status) {this.value_encerts_status = value_encerts_status;}
    public void setValue_shoots_status(int value_shoots_status) {this.value_shoots_status = value_shoots_status;}
    public void setValue_kills_status(int value_kills_status) {this.value_kills_status = value_kills_status;}
    public void setPaintTextCrono(Paint paintTextCrono) {this.paintTextCrono = paintTextCrono;}
    public void setColorText_crono(int colorText_crono) {this.colorText_crono = colorText_crono;}
    public void setSize_text_crono(int size_text_crono) {this.size_text_crono = size_text_crono;}

    public int getMarginRight() {return marginRight;}
    public void setMarginRight(int marginRight) {this.marginRight = marginRight;}

    public void refrescarCrono(String crono){
        this.crono=crono;
        //context.onMeasure(context.getContentWidth(), context.getContentHeight());
        //context.onDraw(new Canvas());
        context.repaintComponent();
    }

    public void setOnnotificStatusChange(Activity activity){
        mListener=(notificStatusChange)activity;
    }

    public interface notificStatusChange{

        public void statusChange(String kills, int valueKills, String shoots, int valueShoots, String parcentage, float valuePArcentage);
    }
}
