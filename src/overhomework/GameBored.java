/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package overhomework;

/**
 *
 * @author CnEuy Ghonz
 */
public class GameBored {
    
  public  int [][] Gamebored;//地图平路和障碍二维数组
  public  int level;//当前关卡
    
  
  public int [][] getbored(){  
      return Gamebored;
  }
  
  
  
  
  
  
    
    public  GameBored(int level){//初始化游戏关卡资源,创建 level*5 个障碍块        
        this.level=level*5;
        this.Gamebored =new int[9][9];
        int tplevel=0;
        
        for(int i=0;i<=8;i++){
            for(int u=0;u<=8;u++){
                this.Gamebored[u][i]=(int)(Math.random()*10)%2;         
                
                if(this.Gamebored[u][i]==1){
                     if(tplevel<this.level){                
                         tplevel++;                      
                    }else{
                         this.Gamebored[u][i]=0;
                     }
                     
                     if(i==8 || i==0){                    
                          this.Gamebored[u][i]=0;//人物出现起始处不允许出现障碍                
                     }
                     
                     if(u==8 || u==0){
                         this.Gamebored[u][i]=0;//人物出现起始处不允许出现障碍                            
                     }
                     
                     
                     
                }
 System.out.print(this.Gamebored[u][i]+"  ");
             }  
   System.out.println("");
        }
        
       
        
        
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
}
