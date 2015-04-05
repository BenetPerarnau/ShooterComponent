package com.example.benet.pasatiemposcomponent;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;


public class pasatiemposView extends View {

    private final String TAG_COMPONENT=getResources().getString(R.string.app_name);
    private boolean init;
    private int contentWidth, contentHeight;

    //Variables para el pincel
    private Paint paintTitle;
    //Variables Atributos Componente

    //0)Atribute Status
    private Status status;
    private Cronometro cronometro;
    //1)Puntero
    private Puntero puntero;
    //2)Diana
    private Diana diana;
    //3)Sounds app
    private Sound sound;

    //1)Ttile
    private String titleComponent;
    private int size, color;


    public pasatiemposView(Context context) {
        super(context);
        init(null, 0);
    }

    public pasatiemposView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public pasatiemposView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {


        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.pasatiemposView, defStyle, 0);

        //CharSequence c=a.getString(R.styleable.GraficBenet_textTitle);
        //if(c!=null){
        //titleComponent=c.toString();
/*
            int c1=a.getDimensionPixelOffset(R.styleable.GraficBenet_textTitleSize, 100);
            if(c1>0){
                size=c1;
            }
            color=a.getColor(R.styleable.GraficBenet_textTitleColor,Color.BLUE);
*/
        // }else{
        //   Log.e(TAG_COMPONENT,"Title is null");
        //}

        status=new Status(this);
        cronometro=new Cronometro(this);
        cronometro.start();

        puntero=new Puntero(this);
        diana=new Diana(this);

        sound=new Sound(this);
        sound.setPista_fondo(R.raw.pista_a);
        sound.startSoundFondo();


        //colorText_status =Color.BLACK;

            //findViewById()
        init=true;

        a.recycle();

        initPaints();

    }

    private void initPaints(){

        //paintTitle title
        if(titleComponent!=null) {
            paintTitle = new Paint();
            paintTitle.setAntiAlias(true);
            paintTitle.setTextSize(size);
            paintTitle.setColor(color);
        }
        //Status
        status.initPaintsStatus();
        //Puntero
        puntero.initPaints();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode=MeasureSpec.getMode(widthMeasureSpec);//Exactly, At_Most,
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);//Exactly, At_Most,
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);

        int width=getWidth()-getPaddingLeft()-getPaddingRight();//total disponible width
        int height=getHeight()-getPaddingBottom()-getPaddingTop();//total disponible height

        switch(widthMode){
            case MeasureSpec.EXACTLY:
                width=widthSize;
                break;
            case MeasureSpec.AT_MOST:
                if(width>widthSize){width=widthSize;}
                break;
        }
        switch(heightMode){
            case MeasureSpec.EXACTLY:
                height=heightSize;
                break;
            case MeasureSpec.AT_MOST:
                if(height>heightSize){height=heightSize;}
                break;
        }


        this.setMeasuredDimension(width, height);



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
           int paddingLeft = getPaddingLeft(); Log.i(TAG_COMPONENT, "Padding left => " + paddingLeft);
           int paddingTop = getPaddingTop(); Log.i(TAG_COMPONENT, "Padding top => " + paddingTop);
           int paddingRight = getPaddingRight();Log.i(TAG_COMPONENT, "Pading Right => " + paddingRight);
           int paddingBottom = getPaddingBottom();Log.i(TAG_COMPONENT, "Pading Bottom => " + paddingBottom);

           contentWidth = getWidth() - paddingLeft - paddingRight;Log.i(TAG_COMPONENT, "ContentWidth => " + contentWidth);
           contentHeight = getHeight() - paddingTop - paddingBottom;Log.i(TAG_COMPONENT, "ContentHeight => " + contentHeight);

           int center_width = contentWidth / 2; Log.i(TAG_COMPONENT, "Center width => " + center_width);
           int center_height = contentHeight / 2; Log.i(TAG_COMPONENT, "Center Height => " + center_height);


           //TODO mirar de imprimir en dos veces para poder cambiar el pincel
           canvas.drawText(status.getKILLS() + status.getValue_kills_status(), contentWidth - status.getPaintTextStatus().measureText(status.getKILLS() + status.getValue_kills_status()) - status.getMarginRight(), 30, status.getPaintTextStatus());
           canvas.drawText(status.getSHOOT() + status.getValue_shoots_status(), contentWidth - status.getPaintTextStatus().measureText(status.getSHOOT() + status.getValue_shoots_status()) - status.getMarginRight(), 60, status.getPaintTextStatus());
           canvas.drawText(status.getENCERTS() + status.getValue_encerts_status() + "%", contentWidth - status.getPaintTextStatus().measureText(status.getENCERTS() + status.getValue_encerts_status() + "%") - status.getMarginRight(), 90, status.getPaintTextStatus());
           canvas.drawText(status.getCrono(), contentWidth / 2 - status.getPaintTextStatus().measureText(status.getCrono()) / 2, 50, status.getPaintTextCrono());

           //Paint Diana
           if (diana.getcXB() == -1) {//donde se va a poner al iniciar
               diana.setcXB(center_width - diana.getImg_diana().getWidth() / 2);
               diana.setcYB(center_height - diana.getImg_diana().getHeight() / 2);
           }
           canvas.drawBitmap(diana.getImg_diana(), diana.getcXB(), diana.getcYB(), null);

           //Paint puntero
           if (puntero.getcXC() == -1) {//donde se va a poner al iniciar
               puntero.setcXC(contentWidth / 2);
               puntero.setcYC(contentHeight / 2);
               // puntero.setRadio((int)((float)contentWidth/(float)6));
           }
           canvas.drawCircle(puntero.getcXC(), puntero.getcYC(), puntero.getRadio(), puntero.getPaintCercle());
           canvas.drawPoint(puntero.getcXC(), puntero.getcYC(), puntero.getPaintPunto());
        //TODO esto esta mal ya lo se pero no consigo refrescar el corno desde un thread diferente
        invalidate();//Unica forma de que funcione el crono
    }
    /*
           SETERS and get TITLE COMPONENT
     */
    public String  getTitleComponent(){
        return titleComponent;
    }
    public void setTitleComponent(String titleComponent){
        this.titleComponent=titleComponent;
        repaintComponent();
    }
    public void setSizeTextComponent(int size){
        //this.size=size;
        this.paintTitle.setTextSize(size);
        repaintComponent();
    }
    public void setColorTextComponent(int color){
        //this.color=color;
        this.paintTitle.setColor(color);
        repaintComponent();
    }
/*
        Seters and Geters Status
 */
    public int getContentWidth() {
        return contentWidth;
    }
    public int getContentHeight() {
        return contentHeight;
    }
    public Puntero getPuntero() {
        return puntero;
    }
    public Status getStatus(){
        return status;
    }
    public Cronometro getCronometro(){
        return cronometro;
    }
    public Diana getDiana(){
        return diana;
    }
    public Sound getSound(){
        return sound;
    }
    public void  repaintComponent(){
        requestLayout();
        //invalidate();
    }

}
