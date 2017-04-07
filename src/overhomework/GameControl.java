/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package overhomework;

import java.awt.Rectangle;
import javax.swing.JLabel;

/**
 *
 * @author CnEuy Ghonz
 */
public class GameControl {
    GameBored gm;
    JLabel man;
    int x=0;//人物在地图上的X坐标
    int y=8;//人物在地图上的Y坐标
    Rectangle tmp;
   int [][] Gamebored; 
   
    public GameControl(GameBored gm,JLabel man){
        this.gm=gm;    
        this.man=man;
        tmp= this.man.getBounds();
       Gamebored=gm.getbored();     
    }
     public void move(int x,int y){//直接位移到指定坐标
         this.x=x;
         this.y=y;
     tmp.x=this.x;
     tmp.y=this.y;
     man.setBounds(tmp);
     }
    
    public void move(int Dirction){//往 1 左  2上  3右  4下 位移一格
        switch(Dirction){
            case 1:{
                
                
                
            if(x==0){
            break;      
            }                 
            x-=1;                       
            if(gm.Gamebored[x][y]==1){
                 x+=1; 
                break;               
            }        
             tmp.x-=64;
            break;
            
            
            
            
            }  
            case 2:{
                if(y==0){
            break;      
            }
            y-=1;
           if(gm.Gamebored[x][y]==1){
                 y+=1; 
                break;               
            }   
           tmp.y-=64;
           
            break;
            }case 3:{
                
                
            if(x==8){
            break;      
            }
            x+=1;
            if(gm.Gamebored[x][y]==1){
                 x-=1; 
                break;               
            }   
            tmp.x+=64;
            break;
            
            
            
            
            }case 4:{
                
           if(y==8){
            break;      
            }
            y+=1;
            if(gm.Gamebored[x][y]==1){
                 y--; 
                break;               
            }   
            tmp.y+=64;
            break;       
        }
            
            
            
        }
     this.x=x;
     this.y=y;
     
    
// man.setBounds(tmp);
        System.out.println("当前X"+x+"    当前Y"+y);
        
  

        
        man.setBounds(tmp);
    }
            
    public void manMove(){
        
        
        
        
    }
    
    
}
