package components;

import javazoom.jl.player.jlp;

public class MusicBackground extends Thread{
        private jlp rep = null; 
        public MusicBackground(String name){
                try{
                    String[] args = new String[1];
                    args[0] = name;
                    rep = jlp.createInstance(args);
                    
                }catch(Exception e){
                    e.printStackTrace();
                }
        }       
	
        public void run(){
            
            try{
                while(true){
                    rep.play();
                    Thread.sleep(MIN_PRIORITY);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        
}
