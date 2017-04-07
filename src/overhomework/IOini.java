

package overhomework;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

//本文件数据格式如下

//10000
//0

//想写ini 但是没有时间了
//[GameMain]
//Money=10000
//Arm=0;                            房间主题颜色 1 2 3 目前就三个 (购买的道具)
//Armx=1                                                                     1是个数   抽奖80% (特殊加成道具)





public class IOini{
    
    int Money=0;
    int Arm=0;
    int Armx=0;
    
    
    String root=System.getProperty("user.dir");//项目根目录路径
    
    final String path=root+"/src/ini/Game.ini";
      
    
    public void Write(int money,int arm,int armx){
       RandomAccessFile raf;       
        try {
            raf = new RandomAccessFile(path,"rw");
             raf.seek(0);               
            raf.write(String.valueOf(money).getBytes("gb2312"));        
             raf.writeBytes("\r\n");
             raf.write(String.valueOf(arm).getBytes("gb2312"));    
              raf.writeBytes("\r\n");
              raf.write(String.valueOf(armx).getBytes("gb2312"));    
              raf.writeBytes("\r\n");
              raf.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IOini.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IOini.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       public void Read(){
         RandomAccessFile raf;
        try {
            raf = new RandomAccessFile(path,"r");
            raf.seek(0);
             String strTemp = raf.readLine();
            strTemp = new String(strTemp.getBytes("ISO-8859-1"),"gb2312");    
            this.Money=Integer.valueOf(strTemp);
            
            strTemp = raf.readLine();
            strTemp = new String(strTemp.getBytes("ISO-8859-1"),"gb2312");    
            this.Arm=Integer.valueOf(strTemp);
            
            strTemp = raf.readLine();
            strTemp = new String(strTemp.getBytes("ISO-8859-1"),"gb2312");    
            this.Armx=Integer.valueOf(strTemp);
            
            System.out.println("加载游戏数据如下");
            System.out.println("Money"+Money+"      Arm"+Arm+"         Armx"+Armx);
            
            raf.close();          
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IOini.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IOini.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    
    
    
    
}
        
        