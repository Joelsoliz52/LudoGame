package utilities;
public class NodoSE<V>{
    private V info;
    private NodoSE<V> suce;
    public NodoSE(){
        info = null;
        suce = null;
    }
    
    public NodoSE(V n){
        info = n;
        suce = new NodoSE<V>();
    }

    public NodoSE<V> getSuce(){
        return suce;
    }
    
    public V getInfo(){
        return info;
    }
    
    public void setSuce(NodoSE<V> v){
        suce = v;
    }
    
    public void setInfo(V n){
        info = n;
    }
    
    public boolean Vacio(){
        boolean res = false;
        if(info == null){
            res = true;
        }
        return res;
    }
}
