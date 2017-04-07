/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package overhomework;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
//import java.awt.PaintContext;
//import java.awt.Rectangle;
//import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.awt.geom.AffineTransform;
//import java.awt.geom.Rectangle2D;
//import java.awt.image.ColorModel;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author PR
 */
public class GameAbout extends javax.swing.JFrame  implements  KeyListener{
    final Color Select_true=new Color(255,51,51);//被选中的颜色
    final Color Select_false=new Color(204,204,204);//没被选中的颜色
    
    int iindex=0;//当前箭头指向的索引
    int maxindex=2;//最大索引
    int Install_Label_y;
    int Install_Label_x;
    int Install_Label_height;
    
  
    
    /**
     * Creates new form GameAbout
     */
    public GameAbout(int width,int height) {
       int Frame_x=0;
       int Frame_y=0;       
        initComponents();
        
        this.setTitle("about-zyGame");//标题禁止更改,否则后果自负
        
        Font Piexfont = Loadfont.loadFont(System.getProperty("user.dir")+"/res/ttf/方正像素15.ttf", 80f);//调用外部像素 ttf
        Font smell_Piexfont = Loadfont.loadFont(System.getProperty("user.dir")+"/res/ttf/方正像素15.ttf", 60f);//调用外部像素 ttf
        Font sosmell_Piexfont = Loadfont.loadFont(System.getProperty("user.dir")+"/res/ttf/方正像素15.ttf", 40f);//调用外部像素 ttf
   //    Font sosomell_Piexfont = Loadfont.loadFont(System.getProperty("user.dir")+"/res/ttf/方正像素15.ttf", 20f);//调用外部像素 ttf
           
        this.setBounds(this.getBounds().x,this.getBounds().y , width, height);
  
          
                
        jPanel1.setBounds(Frame_x,Frame_y, width, height);
        
         TITLE.setFont(Piexfont);     
         
            jLabel1.setFont(smell_Piexfont);
            jLabel2.setFont(sosmell_Piexfont);
            jLabel3.setFont(sosmell_Piexfont);
            jLabel4.setFont(sosmell_Piexfont);
            jLabel5.setFont(sosmell_Piexfont);    
            jLabel6.setFont(sosmell_Piexfont);   
            jLabel8.setFont(sosmell_Piexfont);   
            jLabel9.setFont(sosmell_Piexfont);   
            jLabel10.setFont(sosmell_Piexfont);   
            jLabel11.setFont(sosmell_Piexfont);   
            
            index1.setFont(sosmell_Piexfont);    
            index2.setFont(sosmell_Piexfont);    
            index.setFont(sosmell_Piexfont);    
//              Abotop.setFont(sosmell_Piexfont);    
 //             Abofot.setFont(sosmell_Piexfont);   
//              AboInf1.setFont(sosomell_Piexfont);  
              
           //设置被选中的颜色
              index1.setForeground(Select_true);
              index2.setForeground(Select_false);
             index.setForeground(Select_true);
             
             
               Install_Label_x= index.getBounds().x;
             this.addKeyListener(this);
            
               Timer timer = new Timer();
               timer.schedule(new MyTask(), 0,15);//每15毫秒执行一次 滚动字幕     
      
    }

    @Override
    public void keyTyped(KeyEvent e) {
      // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean isPause=true;
    
    @Override
    public void keyPressed(KeyEvent e) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   
    if(e.getKeyCode()==39){
           iindex++;
            if(iindex>maxindex-1){
            iindex=0;
        }      
           showLabelDown();        
        }else if(e.getKeyCode()==37){                   
            iindex--;
             if(iindex<0){
            iindex=maxindex-1;
        }  
           showLabelDown();        
        }else if(e.getKeyCode()==38){//按上键 加速字幕
    uptext(10);
         
        }else if(e.getKeyCode()==40){//按下键 倒退字幕
    uptext(-20);        
        }else if(e.getKeyCode()==10){
            switch(iindex){        
                case 0:new MainGameFrame().setVisible(true);this.dispose();break;//进入游戏环境界面
                case 1:{
                     if(isPause){isPause=false;}else{isPause=true;}//真 暂停 假 不暂停
       
                }
                    
                    
            }

    
    }
   }
  
    @Override
    public void keyReleased(KeyEvent e) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
class MyTask extends TimerTask {
@Override
          public void run() {
             //System.out.println("执行抖动动画");
              if(isPause){
                uptext(1);//每次上移一个像素
              }
      
        }
} 




//int x=0;


public void uptext(int pix){//向上滚动字幕
   // x=x+2;
    Rectangle tmp=TextGroup.getBounds();
    
    tmp.y-=pix;
    TextGroup.setBounds(tmp);
    
    if(tmp.y< -tmp.height){
        tmp.y=500;   
        
        TextGroup.setBounds(tmp);      
        
    }else if(tmp.y>500){
        tmp.y=-tmp.height+10;        
         TextGroup.setBounds(tmp);         
    }
    
  //  if(x==200){
  //      x=0;
  //      randInf();
  //  }
}



public void showLabelDown(){
        System.out.println("当前选择项："+iindex); 
        
        switch(iindex){
            case 0://选择BACK
            {  
             index1.setForeground(Select_true);
             index2.setForeground(Select_false);  
             break;
            } 
            case 1://选择EXIT
            {           
             index1.setForeground(Select_false);
             index2.setForeground(Select_true);
             break;   
            }   
           
        }
       int x=Install_Label_x+110*iindex;
       index.setBounds(x, index.getBounds().y,  index.getBounds().width, index.getBounds().height);


}
//public void randInf(){

    //int rand=(int)(Math.random()*1000%361);
   // String randN=String.valueOf(rand); 
   
    //AboInf1.setText("N:"+randN);
//}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        TITLE = new javax.swing.JLabel();
        TextGroupFrame = new javax.swing.JPanel();
        TextGroup = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        index1 = new javax.swing.JLabel();
        index = new javax.swing.JLabel();
        index2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(null);

        TITLE.setFont(new java.awt.Font("宋体", 0, 80)); // NOI18N
        TITLE.setForeground(new java.awt.Color(255, 153, 204));
        TITLE.setText("ABOUT");
        jPanel1.add(TITLE);
        TITLE.setBounds(300, 20, 250, 71);

        TextGroupFrame.setBackground(new java.awt.Color(0, 0, 0));
        TextGroupFrame.setLayout(null);

        TextGroup.setBackground(new java.awt.Color(0, 0, 0));
        TextGroup.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("宋体", 0, 60)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("[Behind]");
        TextGroup.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 380, -1));

        jLabel2.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 255, 153));
        jLabel2.setText("Programmer:zy");
        TextGroup.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 310, -1));

        jLabel3.setBackground(new java.awt.Color(0, 102, 255));
        jLabel3.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 255));
        jLabel3.setText("Sound:Not supported");
        TextGroup.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 400, -1));

        jLabel4.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 51, 51));
        jLabel4.setText("UI:zy");
        TextGroup.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 310, -1));

        jLabel5.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 255, 0));
        jLabel5.setText("Art:zy");
        TextGroup.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, 310, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/heihei.jpg"))); // NOI18N
        jLabel7.setText("Image_Me");
        TextGroup.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 350, 200, -1));

        jLabel6.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 102, 102));
        jLabel6.setText("More game Goto");
        TextGroup.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 720, 460, 50));

        jLabel8.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 51, 0));
        jLabel8.setText("http://java.cnkizy.com");
        TextGroup.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 770, 540, 50));

        jLabel9.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 51));
        jLabel9.setText("This procedure uses the");
        TextGroup.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 830, 470, 40));

        jLabel11.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 51));
        jLabel11.setText("       Very much Technology");
        TextGroup.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 880, 560, 40));

        jLabel10.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 102, 255));
        jLabel10.setText("       And Thank Mr.Liu");
        TextGroup.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 930, 470, 40));

        TextGroupFrame.add(TextGroup);
        TextGroup.setBounds(20, 410, 690, 970);

        jPanel1.add(TextGroupFrame);
        TextGroupFrame.setBounds(150, 100, 750, 520);

        index1.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        index1.setForeground(new java.awt.Color(255, 153, 51));
        index1.setText("BACK");
        jPanel1.add(index1);
        index1.setBounds(40, 640, 110, 40);

        index.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        index.setForeground(new java.awt.Color(255, 153, 51));
        index.setText("↑");
        jPanel1.add(index);
        index.setBounds(40, 690, 60, 40);

        index2.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        index2.setForeground(new java.awt.Color(255, 51, 51));
        index2.setText("Great");
        jPanel1.add(index2);
        index2.setBounds(150, 640, 130, 40);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1000, 800);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TITLE;
    private javax.swing.JPanel TextGroup;
    private javax.swing.JPanel TextGroupFrame;
    private javax.swing.JLabel index;
    private javax.swing.JLabel index1;
    private javax.swing.JLabel index2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
