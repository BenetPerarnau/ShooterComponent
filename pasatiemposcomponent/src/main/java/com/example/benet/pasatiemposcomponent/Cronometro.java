package com.example.benet.pasatiemposcomponent;


/**
 * Created by Benet on 20/03/15.
 */

    public class Cronometro extends Thread {

    public boolean detenido;
    public boolean pausado;
    int centesimas = 0,minutos=0, segundos=0, horas=0;
    String c="00",m="00", s="00", h="00";
    String cron;
    private pasatiemposView context;

    public Cronometro(pasatiemposView v)
    {
        detenido = false;
        pausado = true;
        context=v;
    }


    @SuppressWarnings("static-access")
    public void run()
    {
        while(!detenido)
        {
            while(!pausado)
            {
                try {
                    if(centesimas == 99){
                        centesimas = 0;
                        segundos++;
                    }
                    if (segundos == 59) {
                        segundos = 0;
                        minutos++;
                    }
                    if (minutos == 59) {
                        minutos = 0;
                        horas++;
                    }
                    centesimas++;
                    h=horas+"";
                    m=minutos+"";
                    s=segundos+"";
                    c=centesimas+"";
                    if(h.length()<2){h="0"+h;}
                    if(m.length()<2){m="0"+m;}
                    if(s.length()<2){s="0"+s;}
                    if(c.length()<2){c="0"+c;}
                    cron = h + " : " + m + " : " + s + " : " + c;
                    context.getStatus().refrescarCrono(cron);

                    //handler.act();
                    this.sleep(10);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public boolean isPausado() {return pausado;}
    public boolean isDetenido() {return detenido;}

    public void setDetenido(boolean detenido) {
        this.detenido = detenido;
    }
    public void setPausado(boolean pausado) {
        this.pausado = pausado;
    }
    public void setCentesimas(int centesimas) {
        this.centesimas = centesimas;
    }
    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }
    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }
    public void setHoras(int horas) {
        this.horas = horas;
    }


}

