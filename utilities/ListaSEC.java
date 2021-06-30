package utilities;

import java.io.Serializable;

public class ListaSEC <V> implements Serializable {
    private NodoSE<V> prin;
    private int prop;
    public ListaSEC(){
        prin = new NodoSE<V>();
        prin.setSuce(prin);
        prop = 0;
    }
    
    public boolean Lleno(){
        return false;
    }
    
    public int prop(){
        int concl = 0;
        if(!prin.Vacio()){
            NodoSE<V> pres = prin;
            while(pres != null){
                concl = concl+1;
                pres = pres.getSuce();
            }
        }
        return concl;
    }
    public boolean buscar(V val){
        boolean test = false;
        NodoSE<V> pres = prin;
        if(prop != 0){
            if(pres.getInfo() == val){
                test = true;
            }else{
                pres.setInfo(val);
                pres.getSuce();
            }
        }
        return test;
    }
    public int indiceDe(V val){
        int est = 0;
        if(prop != 0){
            if(!buscar(val)){
                est++;
            }else{
                return est;
            }
        }
        return est;
    }
   
    public void Incorporar(V val){
        if(prop == 0){
            prin.setInfo(val);
        }else{
            NodoSE<V> reciente = prin;
            while(reciente.getSuce() != prin){
                reciente = reciente.getSuce();
            }
            NodoSE<V> nuevo = new NodoSE<> (val);
            reciente.setSuce(nuevo);
            nuevo.setSuce(prin);
        }
        prop++;
    }
    
    public V mostrar(int est){
        V concl = null;
        if(est >= 0){
            NodoSE<V> pres = prin;
            while(est > 0){
                pres = pres.getSuce();
                est--;
            }
            concl = pres.getInfo();
        }
        return concl;
    }
   
}
