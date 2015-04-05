package com.example.benet.controlpasatiempos;

import android.content.Context;
import android.content.res.TypedArray;

import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.example.benet.pasatiemposcomponent.Cronometro;
import com.example.benet.pasatiemposcomponent.Puntero;
import com.example.benet.pasatiemposcomponent.Sound;

import java.util.logging.Handler;


/**
 * TODO: document your custom view class.
 */
public class ControlerPasatiemposView extends LinearLayout implements View.OnClickListener{

    private final static int COD_STATE_BUTTON_MIUSIC=123;

    private ImageButton left, right, top, button, shoot, sound_on_of;
    private Button start_pause_resum;
    private Puntero puntero;
    private Sound sound;
    private Cronometro crono;


    public ControlerPasatiemposView(Context context) {
        super(context);
        init(null, 0);
    }

    public ControlerPasatiemposView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ControlerPasatiemposView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sample_controler_pasatiempos_view, this);

        left=(ImageButton)findViewById(R.id.btnLeft);
        left.setOnClickListener(this);
        right=(ImageButton)findViewById(R.id.btnRight);
        right.setOnClickListener(this);
        top=(ImageButton)findViewById(R.id.btnUp);
        top.setOnClickListener(this);
        button=(ImageButton)findViewById(R.id.btnLow);
        button.setOnClickListener(this);
        shoot=(ImageButton)findViewById(R.id.shoot);
        shoot.setOnClickListener(this);
        sound_on_of=(ImageButton)findViewById(R.id.btn_audio_on_of);
        sound_on_of.setOnClickListener(this);
        sound_on_of.setTag(true);
        start_pause_resum=(Button)findViewById(R.id.btn_start_pause_resum);
        start_pause_resum.setOnClickListener(this);

        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.ControlerPasatiemposView, defStyle, 0);

        //set img button left
        int c=a.getResourceId(R.styleable.ControlerPasatiemposView_imageLeft,-1);
        if(c!=-1){left.setImageResource(c);}
        //set img button top
        c=a.getResourceId(R.styleable.ControlerPasatiemposView_imageTop,-1);
        if(c!=-1){top.setImageResource(c);}
        //set img button right
        c=a.getResourceId(R.styleable.ControlerPasatiemposView_imageRight,-1);
        if(c!=-1){right.setImageResource(c);}
        //set img button button
        c=a.getResourceId(R.styleable.ControlerPasatiemposView_imageButton,-1);
        if(c!=-1){button.setImageResource(c);}
        //set img button button
        c=a.getResourceId(R.styleable.ControlerPasatiemposView_imageShoot,-1);
        if(c!=-1){shoot.setImageResource(c);}


        a.recycle();

        left.setOnTouchListener(new RepeatListener(400, 25, new OnClickListener() {
            @Override
            public void onClick(View view) {
                // the code to execute repeatedly
                puntero.moveCercleCordenadaX(-10);
                modCrono();
            }
        }));
        top.setOnTouchListener(new RepeatListener(400, 25, new OnClickListener() {
            @Override
            public void onClick(View view) {
                // the code to execute repeatedly
                puntero.moveCercleCordenadaY(-10);
                modCrono();
            }
        }));
        right.setOnTouchListener(new RepeatListener(400, 25, new OnClickListener() {
            @Override
            public void onClick(View view) {
                // the code to execute repeatedly
                puntero.moveCercleCordenadaX(10);
                modCrono();
            }
        }));
        button.setOnTouchListener(new RepeatListener(400, 25, new OnClickListener() {
            @Override
            public void onClick(View view) {
                // the code to execute repeatedly
                puntero.moveCercleCordenadaY(10);
                modCrono();
            }
        }));

    }
    //Atribute action buttons
    public void setPuntero(Puntero puntero){
        this.puntero=puntero;
    }
    //Atribute on off sound in app
    public void setMusic(Sound sound){this.sound=sound;}
    //Atribute on control Crono app
    public void setCrono(Cronometro cronometro){this.crono=cronometro;}
    //Seters img
    public void setImageButtonLeft(int imageButtonLeftt){left.setImageResource(imageButtonLeftt);}
    public void setImageButtonUp(int imageButtonUp){top.setImageResource(imageButtonUp);}
    public void setImageButtonRight(int imageButtonRight){right.setImageResource(imageButtonRight);}
    public void setImageButtonButton(int imageButtonButton){button.setImageResource(imageButtonButton);}
    //re-size all = buttons
    public void resizeAllButtons(int l, int t, int r, int b){
        left.layout(l,t,r,b);
        top.layout(l,t,r,b);
        right.layout(l,t,r,b);
        button.layout(l,t,r,b);
    }
    //re-size button left
    public void resizeButtonLeft(int l, int t, int r, int b){left.layout(l,t,r,b);}
    //re-size button top
    public void resizeButtonTop(int l, int t, int r, int b){top.layout(l,t,r,b);}
    //re-size button right
    public void resizeButtonRight(int l, int t, int r, int b){right.layout(l,t,r,b);}
    //re-size button top
    public void resizeButtonButton(int l, int t, int r, int b){button.layout(l,t,r,b);}
    //re-size button shoot
    public void resizeButtonShoot(int l, int t, int r, int b){shoot.layout(l,t,r,b);}
    //actions
    @Override
    public void onClick(View v) {

        if(puntero!=null) {

            if (v.getId() == R.id.shoot) {
                puntero.isKill();
                modCrono();
            }else if(v.getId()==R.id.btn_start_pause_resum){
                if(crono!=null) {
                    if (start_pause_resum.getText().toString().equalsIgnoreCase(getResources().getString(R.string.btn_start))) {
                        start_pause_resum.setText(getResources().getString(R.string.btn_pause));
                        crono.setPausado(false);
                    } else if (start_pause_resum.getText().toString().equalsIgnoreCase(getResources().getString(R.string.btn_pause))) {
                        start_pause_resum.setText(getResources().getString(R.string.btn_resum));
                        crono.setPausado(true);
                    } else if (start_pause_resum.getText().toString().equalsIgnoreCase(getResources().getString(R.string.btn_resum))) {
                        start_pause_resum.setText(getResources().getString(R.string.btn_pause));
                        crono.setPausado(false);
                    }
                }else{
                    Log.e("","This class don't have inizializate atribute crono. Call setCrono()");
                }

            }else if(v.getId()==R.id.btn_audio_on_of){
                if(sound!=null) {
                    if ((boolean)sound_on_of.getTag()) {//stop miusic
                        sound_on_of.setTag(false);
                        sound_on_of.setImageResource(R.drawable.ic_silencio);
                        sound.muteAllSounds();

                    } else {
                        sound_on_of.setTag(true);
                        sound_on_of.setImageResource(R.drawable.ic_sound);
                        sound.activeAllSounds();
                    }
                }else{
                    Log.e("","This class don't have inizializate atribute sound. Call setMusic()");
                }
            }
        }else{
            Log.e("", "ControlerPasatiemposView don't have inizialitzate puntero. Call setPuntero().");
        }

    }

    public void modCrono(){
        if(crono!=null &&
           start_pause_resum.getText().toString().equalsIgnoreCase(getResources().getString(R.string.btn_start)) ||
           start_pause_resum.getText().toString().equalsIgnoreCase(getResources().getString(R.string.btn_resum)) )
        {

            if (start_pause_resum.getText().toString().equalsIgnoreCase(getResources().getString(R.string.btn_start))) {
                start_pause_resum.setText(getResources().getString(R.string.btn_pause));
                crono.setPausado(false);
            } else if (start_pause_resum.getText().toString().equalsIgnoreCase(getResources().getString(R.string.btn_pause))) {
                start_pause_resum.setText(getResources().getString(R.string.btn_resum));
                crono.setPausado(true);
            } else if (start_pause_resum.getText().toString().equalsIgnoreCase(getResources().getString(R.string.btn_resum))) {
                start_pause_resum.setText(getResources().getString(R.string.btn_pause));
                crono.setPausado(false);
            }
        }else{
            Log.e("","This class don't have inizializate atribute crono. Call setCrono()");
        }
    }



}
