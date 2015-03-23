package com.example.benet.pasatiemposcomponent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.View;

/**
 * Created by Benet on 19/03/15.
 */
public class Diana {

    private Bitmap img_diana;
    private int cXB, cYB;
    private Bitmap img_sangre;
    private Boolean sang;

    private pasatiemposView v;

    public Diana(pasatiemposView v){
        this.v=v;
        initComponents();
    }

    private void initComponents(){
        cXB=-1;
        cYB=-1;
        sang=false;
        img_diana= BitmapFactory.decodeResource(v.getResources(), R.mipmap.img_gon);
        img_sangre=BitmapFactory.decodeResource(v.getResources(),R.mipmap.img_sangre);
        img_diana=getResizedBitmap(img_diana,200,200);
        img_sangre=getResizedBitmap(img_sangre,100,100);
    }

    public Bitmap getImg_diana() {return img_diana;}
    public int getcYB() {return cYB;}
    public int getcXB() {return cXB;}
    public Bitmap getImg_sangre() {return img_sangre;}
    public Boolean getSang() {return sang;}
    public pasatiemposView getV() {return v;}

    public void setImg_diana(Bitmap img_diana) {
        this.img_diana = img_diana;
    }
    public void setcXB(int cXB) {
        this.cXB = cXB;
    }
    public void setcYB(int cYB) {
        this.cYB = cYB;
    }
    public void setImg_sangre(Bitmap img_sangre) {
        this.img_sangre = img_sangre;
    }
    public void setSang(Boolean sang) {
        this.sang = sang;
    }
    public void setV(pasatiemposView v) {
        this.v = v;
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);
        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }

}
