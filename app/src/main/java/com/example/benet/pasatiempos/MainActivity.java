package com.example.benet.pasatiempos;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.benet.controlpasatiempos.ControlerPasatiemposView;
import com.example.benet.pasatiemposcomponent.Puntero;
import com.example.benet.pasatiemposcomponent.Status;
import com.example.benet.pasatiemposcomponent.pasatiemposView;


public class MainActivity extends ActionBarActivity implements Puntero.NotificByPuntero, Status.notificStatusChange {

    private Activity context;

    private pasatiemposView pasatiemposView;
    private ControlerPasatiemposView controlerPasatiemposView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this;

        pasatiemposView =(pasatiemposView)findViewById(R.id.myComponent);

        pasatiemposView.getSound().setActivity(this);
        pasatiemposView.getSound().setPistaFondo(R.raw.pista_a);
        pasatiemposView.getSound().startSoundFondo();

        pasatiemposView.getPuntero().setOnNotificShoot(this);
        pasatiemposView.getStatus().setOnnotificStatusChange(this);

        controlerPasatiemposView=(ControlerPasatiemposView)findViewById(R.id.controler);
        controlerPasatiemposView.setPuntero(pasatiemposView.getPuntero());
        controlerPasatiemposView.setMusic(pasatiemposView.getSound());
        controlerPasatiemposView.setCrono(pasatiemposView.getCronometro());


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void notificShoot(boolean kill) {
        if(kill){
            Toast.makeText(this,"Kill",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Fail",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void statusChange(String kills, int valueKills, String shoots, int valueShoots, String parcentage, float valuePArcentage) {
        if(valueKills==5){
            Toast.makeText(this,"Great you are kill 5 Gonzaliñus",Toast.LENGTH_LONG).show();
        }
    }
}
