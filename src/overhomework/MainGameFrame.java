/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package overhomework;

import java.util.TimerTask;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author 钟越zy
 */
public class MainGameFrame extends javax.swing.JFrame implements  KeyListener{
    
    
    int index=0;//当前箭头指向的索引
    int maxindex=4;//最大索引
    int Install_Label_y;
    int Install_Label_x;
    int Install_Label_height;
    
    final Color Select_true=new Color(255,0,0);//被选中的颜色
    final Color Select_false=new Color(255,255,0);//没被选中的颜色
    private Container NewJPanel_STORE;
    
      int Image_x;
 
    /**
     * Creates new form MainGameFrame
     */
    public MainGameFrame() {
    initComponents();

    this.setTitle("menu-zyGame");//标题禁止更改,否则后果自负
    
    this.setBounds(0, 0, 1000, 800);
    
 
    
    Font BIGPiexfont = Loadfont.loadFont(System.getProperty("user.dir")+"/res/ttf/方正像素15.ttf",90f);//调用外部像素 ttf
    Font TIPiexfont = Loadfont.loadFont(System.getProperty("user.dir")+"/res/ttf/方正像素15.ttf",60f);//调用外部像素 ttf 
    Font Piexfont = Loadfont.loadFont(System.getProperty("user.dir")+"/res/ttf/方正像素15.ttf", 45f);//调用外部像素 ttf
    Font smellPiexfont = Loadfont.loadFont(System.getProperty("user.dir")+"/res/ttf/方正像素15.ttf", 30f);//调用外部像素 ttf
    
    
    //设置游戏初始标签的属性，长宽高
   Install_Label_y= jLabelindex.getBounds().y;
   Install_Label_x= jLabelindex.getBounds().x; 
   Install_Label_height=jLabelindex.getBounds().height; 
   
   //设置游戏为像素风格
   jLabel1.setFont(Piexfont);
   jLabel2.setFont(Piexfont);
   jLabel3.setFont(Piexfont);
   jLabel4.setFont(Piexfont);
   
   jLabel5.setFont(Piexfont);
   jLabel6.setFont(Piexfont);
   jLabel7.setFont(Piexfont);
   jLabel8.setFont(Piexfont);
   jLabel9.setFont(Piexfont);
   
   
   TITLE.setFont(BIGPiexfont);
   jLabelindex.setFont(Piexfont);
   
   Randtop.setFont(TIPiexfont);
   Randmid1.setFont(Piexfont);
   Randmid2.setFont(Piexfont);
   Randmid3.setFont(Piexfont);
   CPURand.setFont(Piexfont);
   CPU.setFont(Piexfont);
  // jLabel9.setFont(Piexfont);
   
   //设置被选中的颜色
   jLabel1.setForeground(Select_true);
   jLabel2.setForeground(Select_false);
   
   //微调组件，让它看起来很整齐 若有布局，则此处代码无意义
   //jLabelindex.setBounds(jLabel1.getBounds().x-20,jLabel1.getBounds().y-20,jLabelindex.getBounds().width,jLabelindex.getBounds().height);
   //jLabel2.setBounds(jLabel1.getBounds().x, jLabel1.getBounds().y+60,jLabel2.getBounds().width, jLabel2.getBounds().height);
   //jLabel3.setBounds(jLabel1.getBounds().x, jLabel2.getBounds().y+62,jLabel3.getBounds().width, jLabel3.getBounds().height);
   //jLabel4.setBounds(jLabel1.getBounds().x, jLabel3.getBounds().y+65,jLabel4.getBounds().width, jLabel4.getBounds().height);
   
   
  // ImageLabel.setVisible(false);
   
  Image_x= ImageLabel.getBounds().x;
      
  ImageLabel.setVisible(true);
  // CoolChange(jLabel1.getBounds().x, jLabel1.getBounds().y,jLabel1.getBounds().width, jLabel1.getBounds().height);
 
Timer timer = new Timer();
timer.schedule(new MyTask(), 500,250);//每250毫秒执行一次 抖动动画
   

    
   //监视整个窗口的键盘事件
   this.addKeyListener(this);
    
    
    }
class MyTask extends TimerTask {

@Override
          public void run() {
             //System.out.println("执行抖动动画");
             LeftMOVE();
            // ff();
        }

} 
// public void ff(){
//     String d="";
//     for(int y=0;y<39;y++){      
//            d+=String.valueOf((int)(Math.random()*10%2));
//     }     
//     jLabel9.setText(d);
// }


int isbackindex=-1;
public void LeftMOVE(){//执行抖动动画


      int x= TITLE.getBounds().x;
      int y= TITLE.getBounds().y;
      int w= TITLE.getBounds().width;
      int h= TITLE.getBounds().height;
        
      int r=(int)(Math.random()*100%8);
      
      
      if(isbackindex !=-1){
          
         switch(isbackindex){
          case 0:isbackindex=-1;x-=20;break;
           case 1:isbackindex=-1;y-=20;break;
           case 2:isbackindex=-1;x+=20;break;
           case 3:isbackindex=-1;y+=20;break;          
           case 4:isbackindex=-1;y-=20;x-=20;break;
           case 5:isbackindex=-1;y-=20;x+=20;break;
           case 6:isbackindex=-1;y+=20;x+=20;break;
           case 7:isbackindex=-1;y+=20;x-=20;break;
      }
           
     }else{

      switch(r){
          case 0:isbackindex=0;x+=20;break;
          case 1:isbackindex=1;y+=20;break;
          case 2:isbackindex=2;x-=20;break;
          case 3:isbackindex=3;y-=20;break;                
          case 4:isbackindex=4;y+=20;x+=20;break;
          case 5:isbackindex=5;y+=20;x-=20;break;
          case 6:isbackindex=6;y-=20;x-=20;break;
          case 7:isbackindex=7;y-=20;x+=20;break;
      }
     }
      
      
    TITLE.setBounds(x, y, w, h);
    if(titlefs==true)TITLE.setForeground( randC());         
    
}




    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        CPU = new javax.swing.JLabel();
        CPURand = new javax.swing.JLabel();
        Randmid3 = new javax.swing.JLabel();
        Randmid2 = new javax.swing.JLabel();
        Randmid1 = new javax.swing.JLabel();
        Randtop = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabelNote = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelindex = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ImageLabel = new javax.swing.JLabel();
        TITLE = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setLayout(null);

        CPU.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        CPU.setForeground(new java.awt.Color(51, 255, 0));
        CPU.setText("CPU");
        jPanel3.add(CPU);
        CPU.setBounds(10, 220, 80, 50);

        CPURand.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        CPURand.setForeground(new java.awt.Color(0, 204, 0));
        CPURand.setText("19  20   22   23   25");
        jPanel3.add(CPURand);
        CPURand.setBounds(90, 220, 490, 50);

        Randmid3.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        Randmid3.setForeground(new java.awt.Color(0, 204, 0));
        Randmid3.setText("19  20   22   23   25");
        jPanel3.add(Randmid3);
        Randmid3.setBounds(90, 160, 490, 50);

        Randmid2.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        Randmid2.setForeground(new java.awt.Color(0, 204, 0));
        Randmid2.setText("19  20   22   23   25");
        jPanel3.add(Randmid2);
        Randmid2.setBounds(90, 110, 490, 50);

        Randmid1.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        Randmid1.setForeground(new java.awt.Color(0, 204, 0));
        Randmid1.setText("19  20   22   23   25");
        jPanel3.add(Randmid1);
        Randmid1.setBounds(90, 60, 490, 50);

        Randtop.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        Randtop.setForeground(new java.awt.Color(0, 255, 102));
        Randtop.setText("B  I   N   G   O");
        jPanel3.add(Randtop);
        Randtop.setBounds(90, 0, 500, 70);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(java.awt.Color.white);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(null);

        jLabelNote.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/TRAG.gif"))); // NOI18N
        jLabelNote.setText("jLabel3");
        jPanel1.add(jLabelNote);
        jLabelNote.setBounds(-50, 30, 570, 200);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setToolTipText("");
        jPanel2.setLayout(null);

        jLabelindex.setFont(new java.awt.Font("宋体", 0, 45)); // NOI18N
        jLabelindex.setForeground(new java.awt.Color(255, 0, 0));
        jLabelindex.setText("->");
        jPanel2.add(jLabelindex);
        jLabelindex.setBounds(50, 20, 58, 60);

        jLabel1.setFont(new java.awt.Font("宋体", 0, 45)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("GAME");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(120, 20, 200, 60);

        jLabel2.setFont(new java.awt.Font("宋体", 0, 45)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 0));
        jLabel2.setText("STORE");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(120, 100, 200, 52);

        jLabel3.setFont(new java.awt.Font("宋体", 0, 45)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 0));
        jLabel3.setText("ABOUT");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(120, 180, 200, 52);

        jLabel4.setFont(new java.awt.Font("宋体", 0, 45)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 0));
        jLabel4.setText("EXIT");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(120, 260, 200, 52);

        ImageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/Select_sd.gif"))); // NOI18N
        ImageLabel.setText("jLabel5");
        jPanel2.add(ImageLabel);
        ImageLabel.setBounds(289, 291, 27, 15);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(30, 260, 300, 330);

        TITLE.setFont(new java.awt.Font("宋体", 0, 90)); // NOI18N
        TITLE.setForeground(new java.awt.Color(255, 204, 51));
        TITLE.setText("WasteDump");
        TITLE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TITLEMousePressed(evt);
            }
        });
        jPanel1.add(TITLE);
        TITLE.setBounds(550, 90, 430, 110);

        jLabel5.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Control");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(480, 290, 310, 60);

        jLabel6.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("[↑]....UP");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(480, 340, 360, 50);

        jLabel7.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("[↓]....Down");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(480, 390, 430, 50);

        jLabel8.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("[Enter].Confirm");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(480, 440, 430, 50);

        jLabel9.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("[Esc]...back");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(480, 490, 430, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    boolean titlefs=false;
    private void TITLEMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TITLEMousePressed
        // TODO add your handling code here:
        if(titlefs){
            titlefs=false;         
             TITLE.setText("LUCKY_ZY");
        }else{
             titlefs=true;
           TITLE.setText("彩蛋");           
        }
        
        
    }//GEN-LAST:event_TITLEMousePressed

    public Color randC(){
             int r =(int)(Math.random()*1000%255);
            int g =(int)(Math.random()*1000%255);
            int b =(int)(Math.random()*1000%255);
      
        return new Color(r,g,b);
        
    }
    
    
    
    /**
     * @param args the command line arguments
     */
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CPU;
    private javax.swing.JLabel CPURand;
    private javax.swing.JLabel ImageLabel;
    private javax.swing.JLabel Randmid1;
    private javax.swing.JLabel Randmid2;
    private javax.swing.JLabel Randmid3;
    private javax.swing.JLabel Randtop;
    private javax.swing.JLabel TITLE;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelNote;
    private javax.swing.JLabel jLabelindex;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {//键盘上某个键被按下；
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                  //
               
       // if(e.getKeyCode()==40){
                    
       //  }
   
     //   System.out.println(e.getKeyCode());
    
    }

    @Override
    public void keyPressed(KeyEvent e) {//键盘上某个键被按下，又释放；
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     //ZUO  37
        //UP 38
        //YOU 39
        //XIA 40
        
        if(e.getKeyCode()==40){
           index++;
            if(index>maxindex-1){
            index=0;
        }      
           showLabelDown();        
        }else if(e.getKeyCode()==38){                   
            index--;
             if(index<0){
            index=maxindex-1;
        }  
           showLabelDown();        
        }else if(e.getKeyCode()==10){
              //this.setVisible(false);//设置主窗口暂时不可见
            switch(index){        
                case 0:new GameFrame(this.getBounds().width, this.getBounds().height).setVisible(true);this.dispose();break;//进入游戏环境界面
                case 1:new GameStore(this.getBounds().width, this.getBounds().height).setVisible(true);this.dispose();break;//进入商店
                case 2:new GameAbout(this.getBounds().width, this.getBounds().height).setVisible(true);this.dispose();break;//进入幕后黑手制作表
                case 3:System.exit(0);
            }
            
         
        }
        
       
        System.out.println(e.getKeyCode());
    
    
    }

    @Override
    public void keyReleased(KeyEvent e) {//keyPressed和keyReleased两个方法的组合。
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      // System.out.println(e.getKeyCode());
    
    
    }
    
      
    public void showLabelDown(){
        System.out.println("当前选择项："+index); 
        
        switch(index){
            case 0://选择GAME
            {  
             jLabel1.setForeground(Select_true);
             jLabel2.setForeground(Select_false);
             jLabel3.setForeground(Select_false);
             jLabel4.setForeground(Select_false);
              CoolChange(jLabel1.getBounds().x,jLabel1.getBounds().y,jLabel1.getBounds().width,jLabel1.getBounds().height);
             break;
            } 
            case 1://选择STORE
            {
             
             jLabel1.setForeground(Select_false);
             jLabel2.setForeground(Select_true);
             jLabel3.setForeground(Select_false);  
             jLabel4.setForeground(Select_false);
               CoolChange(jLabel2.getBounds().x,jLabel2.getBounds().y,jLabel2.getBounds().width,jLabel2.getBounds().height);
             break;   
            }   
             case 2://选择ABOUT
            {
             
            jLabel1.setForeground(Select_false);
            jLabel2.setForeground(Select_false);
            jLabel3.setForeground(Select_true); 
            jLabel4.setForeground(Select_false);
              CoolChange(jLabel3.getBounds().x,jLabel3.getBounds().y,jLabel3.getBounds().width,jLabel3.getBounds().height);
             break;   
            }   
                 case 3://选择EXIT
            {
             
            jLabel1.setForeground(Select_false);
            jLabel2.setForeground(Select_false);
            jLabel3.setForeground(Select_false); 
            jLabel4.setForeground(Select_true);
              CoolChange(jLabel4.getBounds().x,jLabel4.getBounds().y,jLabel4.getBounds().width,jLabel4.getBounds().height);
             break;   
            }      
        }
       int y=Install_Label_y+78*index;
        jLabelindex.setBounds(jLabelindex.getBounds().x, y,  jLabelindex.getBounds().width, jLabelindex.getBounds().height);


}
    
    
    
    public void sleep(int time){//time单位毫秒
        try {
        Thread.sleep(time);//括号里面的5000代表5000毫秒，也就是5秒，可以该成你需要的时间
} catch (InterruptedException e) {
        e.printStackTrace();
}
    
    }
    
    public void CoolChange(int xt,int y,int width,int height){  
         ImageLabel.setBounds(xt-50,y, width+50, height);  
    }
    
    
    
}
