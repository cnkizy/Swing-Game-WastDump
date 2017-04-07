 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package overhomework;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author PR
 */
public class GameFrame extends javax.swing.JFrame implements KeyListener,Runnable{

    /**
     * Creates new form GameFrame
     */
    
     IOini bbuy=new IOini();
    
    //商店购买道具后可以更换BINGO颜色,0默认 灰色    1,红色,   2橙色  3紫色  4暗金色 5暗金色
   Color bgc[]={new Color(255,0,0),new Color(255,102,0),new Color(102,102,255),new Color(255,200,0),new Color(255,200,0),new Color(204,204,204)};
 //   Color bgc[]={new Color(204,204,204),new Color(255,0,0),new Color(255,102,0),new Color(102,102,255),new Color(255,200,0),new Color(204,204,204)};

    JLabel [][] map=new JLabel[9][9];

     JLabel [] food;     
     
      ImageIcon maps[];//平地
      ImageIcon maps_obstacle[];//障碍
      ImageIcon maps_exit;//出口
      ImageIcon maps_food[];//食物
      
      int gmlevel=1;
    
    int arm=0;//已经购买的武器
    int Nheal=200;
    
    GameControl g;
            
            
    public GameFrame(int width,int height) {
        initComponents();
         Font BIGPiexfont = Loadfont.loadFont(System.getProperty("user.dir")+"/res/ttf/方正像素15.ttf", 100f);//调用外部像素 ttf
         Font Piexfont = Loadfont.loadFont(System.getProperty("user.dir")+"/res/ttf/方正像素15.ttf", 45f);//调用外部像素 ttf
         Font bPiexfont = Loadfont.loadFont(System.getProperty("user.dir")+"/res/ttf/方正像素15.ttf", 30f);//调用外部像素 ttf
      
        maps=new  ImageIcon[8];//随机地图块(平地)
        maps[0]=new ImageIcon(getClass().getResource("/images/Scavengers_SpriteSheet_33.png"));
        maps[1]=new ImageIcon(getClass().getResource("/images/Scavengers_SpriteSheet_34.png"));
        maps[2]=new ImageIcon(getClass().getResource("/images/Scavengers_SpriteSheet_35.png"));
        maps[3]=new ImageIcon(getClass().getResource("/images/Scavengers_SpriteSheet_36.png"));
        maps[4]=new ImageIcon(getClass().getResource("/images/Scavengers_SpriteSheet_37.png"));
        maps[5]=new ImageIcon(getClass().getResource("/images/Scavengers_SpriteSheet_38.png"));
        maps[6]=new ImageIcon(getClass().getResource("/images/Scavengers_SpriteSheet_39.png"));
        maps[7]=new ImageIcon(getClass().getResource("/images/Scavengers_SpriteSheet_40.png"));
        
        maps_obstacle=new  ImageIcon[7];//随机地图块(障碍)
        maps_obstacle[0]=new ImageIcon(getClass().getResource("/images/Scavengers_SpriteSheet_26.png"));
        maps_obstacle[1]=new ImageIcon(getClass().getResource("/images/Scavengers_SpriteSheet_27.png"));
        maps_obstacle[2]=new ImageIcon(getClass().getResource("/images/Scavengers_SpriteSheet_28.png"));
        maps_obstacle[3]=new ImageIcon(getClass().getResource("/images/Scavengers_SpriteSheet_29.png"));
        maps_obstacle[4]=new ImageIcon(getClass().getResource("/images/Scavengers_SpriteSheet_30.png"));
        maps_obstacle[5]=new ImageIcon(getClass().getResource("/images/Scavengers_SpriteSheet_31.png"));
        maps_obstacle[6]=new ImageIcon(getClass().getResource("/images/Scavengers_SpriteSheet_32.png"));

        maps_food=new  ImageIcon[2];//随机地图块(障碍)
        maps_food[0]=new ImageIcon(getClass().getResource("/images/Scavengers_SpriteSheet_19.png"));
        maps_food[1]=new ImageIcon(getClass().getResource("/images/Scavengers_SpriteSheet_20.png"));

        
        
        maps_exit=new ImageIcon(getClass().getResource("/images/Scavengers_SpriteSheet_21.png"));
        
        
        
        
         //调整窗口大小
        this.setBounds(this.getBounds().x,this.getBounds().y , width, height);

        //设置像素风字体
         TOP1.setFont(Piexfont);
         TOP2.setFont(Piexfont);
         TOP3.setFont(Piexfont);
         TOP4.setFont(Piexfont);
         TOP5.setFont(Piexfont);
         
         Info1.setFont(bPiexfont);
         Info2.setFont(bPiexfont);
         Info3.setFont(bPiexfont);
         Info4.setFont(bPiexfont);
         Info5.setFont(bPiexfont);
         
         jbhealth.setFont(Piexfont);
         jbhealthN.setFont(Piexfont);
         
         
         //读取ini文件,加载上一次游戏的数据
         bbuy.Read();
         arm=bbuy.Arm;
         
             Nheal=bbuy.Armx;
         if(Nheal<=300){//新手的话赏你300生命
             Nheal=300;            
         }
         intstallRoom();//初始化房间
 

        
         
        loadgmlevel();//加载光卡
         this.addKeyListener(this);
        
    }
            int allfood=(int)(gmlevel/8);
    public void loadgmlevel(){
       allfood=(int)(gmlevel/8);
                int tmpd=0;
               System.out.println(allfood);
               if(allfood>=1){ food=new JLabel [allfood];}
               
        g =new GameControl(new GameBored(gmlevel),Man);   //初始难度为10 ,设置主角光环
        //map=g.Gamebored;
         
         
         for(int i=0;i<=8;i++){//y
             for(int u=0;u<=8;u++){//x
                map[u][i] =new JLabel();            
                map[u][i].setIcon(maps[(int)(Math.random()*10%8)]);//从平地块里随便找一个
                map[u][i].setBounds(u*64, i*64, 64, 64);
                               
                
                if(allfood>=1){                         
                     if(u>4 && i >4){
            if(g.Gamebored[u][i]==0){      
                if((int)(Math.random()*10%2)==1){
                    if(tmpd<allfood){         
                 food[tmpd]=new JLabel();
                 food[tmpd].setIcon(maps_food[(int)(Math.random()*10%2)]);
                 food[tmpd].setBounds(u*64, i*64, 64, 64);
                 jPanel2.add(food[tmpd]);
                 
                 g.Gamebored[u][i]=2+tmpd;
                    tmpd++;
                         //     map[u][i].setIcon(maps_food[(int)(Math.random()*10%2)]);
                    }
                    
                }
                     }
                 }
                }
              
                
                        
                
                if(g.Gamebored[u][i]==1){
                  map[u][i].setIcon(maps_obstacle[(int)(Math.random()*10%7)]);//从障碍块里随便找一个                         
                }
                
                   jPanel2.add(map[u][i]);       
                
             }       
         }
         
         map[8][0].setIcon(maps_exit);
         

    }
    
    public void updatamap(){
          for(int i=0;i<=8;i++){//y
          for(int u=0;u<=8;u++){//x
        jPanel2.remove(map[u][i]);                 
                    }
             }
          if(allfood>=1){
          for(int o=0;o<allfood;o++){
                jPanel2.remove(food[o]);                
          }
      }            
          
          
    }
    
    
    Thread ksthread =new Thread(this);
    public void intstallRoom(){
       
       
       LoadCSS(arm);//根据商店购买的道具进行CSS加成
       
       ksthread.start();
        
    }
      //  @Override
         int sk_i=0;
    public void run() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          boolean brun=true;
          while(brun){
          sk_i++;         
          if(sk_i>91){              
            ksthread.stop();
          }
              sk();            //散开B I N G O             
              this.sleep(10);//延时10毫秒 有动画效果

          }
          
    
    }
    public void LoadCSS(int indexcss){
        
        //加载 标题 的颜色
        TOP1.setForeground(bgc[indexcss]);
        TOP2.setForeground(bgc[indexcss]);
        TOP3.setForeground(bgc[indexcss]);
        TOP4.setForeground(bgc[indexcss]);
        TOP5.setForeground(bgc[indexcss]);       
         //加载 房间的主题颜色
        jbhealth.setForeground(bgc[indexcss]);       
        jbhealthN.setForeground(bgc[indexcss]);       
        
         Info1.setForeground(bgc[indexcss]);       
         Info2.setForeground(bgc[indexcss]);       
         Info3.setForeground(bgc[indexcss]);       
         Info5.setForeground(bgc[indexcss]);       
         Info4.setForeground(bgc[indexcss]);       
         //加载 剩余生命数
         jbhealthN.setText(String.valueOf(Nheal));
        
        //加载关卡标题       
        refshTitle();
                


        
    }
    
    public void refshTitle(){

        
        
        
            switch(gmlevel){
            case 1: TOP4.setText("一");break;
            case 2: TOP4.setText("二");break;
            case 3: TOP4.setText("三");break;
            case 4: TOP4.setText("四");break;
            case 5: TOP4.setText("五");break;
            case 6: TOP4.setText("六");break;
            case 7: TOP4.setText("七");break;   
            case 8: TOP4.setText("八");break;       
            case 9: TOP4.setText("九");break;       
           case 10: TOP4.setText("十");break;      
            default :  TOP4.setText(String.valueOf(gmlevel));
        }
        
        
    }
    
    
    
    Rectangle d;
  
    public void sk(){//BINGO散开
        d=TOP1.getBounds();
        d.x--;                
        TOP1.setBounds(d);
        
        if(sk_i<46){
            d=TOP2.getBounds();
            d.x--;
            TOP2.setBounds(d);
            
        d=TOP4.getBounds();
        d.x++;                
        TOP4.setBounds(d);
        }
       
        d=TOP5.getBounds();
        d.x++;                
        TOP5.setBounds(d);
    
    }
    class MyTask extends TimerTask {

@Override
          public void run() {
         //    LeftMOVE();
        }

}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionPane1 = new javax.swing.JOptionPane();
        jPanel1 = new javax.swing.JPanel();
        TOP1 = new javax.swing.JLabel();
        TOP4 = new javax.swing.JLabel();
        TOP3 = new javax.swing.JLabel();
        TOP2 = new javax.swing.JLabel();
        TOP5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        Man = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jbhealth = new javax.swing.JLabel();
        jbhealthN = new javax.swing.JLabel();
        Info5 = new javax.swing.JLabel();
        Info1 = new javax.swing.JLabel();
        Info3 = new javax.swing.JLabel();
        Info2 = new javax.swing.JLabel();
        Info4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setFocusCycleRoot(false);
        setMinimumSize(new java.awt.Dimension(1000, 800));
        setPreferredSize(new java.awt.Dimension(1000, 800));
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(null);

        TOP1.setFont(new java.awt.Font("宋体", 0, 45)); // NOI18N
        TOP1.setForeground(new java.awt.Color(255, 102, 0));
        TOP1.setText("荒");
        jPanel1.add(TOP1);
        TOP1.setBounds(260, 0, 60, 50);

        TOP4.setFont(new java.awt.Font("宋体", 0, 45)); // NOI18N
        TOP4.setForeground(new java.awt.Color(102, 102, 255));
        TOP4.setText("一");
        jPanel1.add(TOP4);
        TOP4.setBounds(440, 0, 60, 50);

        TOP3.setFont(new java.awt.Font("宋体", 0, 45)); // NOI18N
        TOP3.setForeground(new java.awt.Color(255, 102, 0));
        TOP3.setText("山");
        jPanel1.add(TOP3);
        TOP3.setBounds(380, 0, 60, 50);

        TOP2.setFont(new java.awt.Font("宋体", 0, 45)); // NOI18N
        TOP2.setForeground(new java.awt.Color(204, 204, 204));
        TOP2.setText("石");
        jPanel1.add(TOP2);
        TOP2.setBounds(320, 0, 60, 50);

        TOP5.setFont(new java.awt.Font("宋体", 0, 45)); // NOI18N
        TOP5.setForeground(new java.awt.Color(255, 102, 0));
        TOP5.setText("关");
        jPanel1.add(TOP5);
        TOP5.setBounds(500, 0, 60, 50);

        jPanel2.setLayout(null);

        Man.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Scavengers_SpriteSheet_01.png"))); // NOI18N
        jPanel2.add(Man);
        Man.setBounds(0, 515, 60, 60);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(84, 124, 576, 576);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/map.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 60, 710, 704);

        jbhealth.setFont(new java.awt.Font("宋体", 0, 45)); // NOI18N
        jbhealth.setForeground(new java.awt.Color(255, 51, 51));
        jbhealth.setText("Health:");
        jPanel1.add(jbhealth);
        jbhealth.setBounds(730, 120, 180, 50);

        jbhealthN.setFont(new java.awt.Font("宋体", 0, 45)); // NOI18N
        jbhealthN.setForeground(new java.awt.Color(255, 0, 0));
        jbhealthN.setText("0");
        jPanel1.add(jbhealthN);
        jbhealthN.setBounds(730, 170, 180, 50);

        Info5.setFont(new java.awt.Font("宋体", 0, 30)); // NOI18N
        Info5.setForeground(new java.awt.Color(255, 0, 51));
        Info5.setText("商店可更换背景颜色");
        jPanel1.add(Info5);
        Info5.setBounds(730, 480, 270, 40);

        Info1.setFont(new java.awt.Font("宋体", 0, 30)); // NOI18N
        Info1.setForeground(new java.awt.Color(255, 0, 51));
        Info1.setText("荒石山共13关");
        jPanel1.add(Info1);
        Info1.setBounds(730, 310, 240, 50);

        Info3.setFont(new java.awt.Font("宋体", 0, 30)); // NOI18N
        Info3.setForeground(new java.awt.Color(255, 0, 51));
        Info3.setText("草莓+21生命");
        jPanel1.add(Info3);
        Info3.setBounds(730, 400, 260, 50);

        Info2.setFont(new java.awt.Font("宋体", 0, 30)); // NOI18N
        Info2.setForeground(new java.awt.Color(255, 0, 51));
        Info2.setText("小键盘控制人物");
        jPanel1.add(Info2);
        Info2.setBounds(730, 360, 240, 50);

        Info4.setFont(new java.awt.Font("宋体", 0, 30)); // NOI18N
        Info4.setForeground(new java.awt.Color(255, 0, 51));
        Info4.setText("啤酒、罐头+21生命");
        jPanel1.add(Info4);
        Info4.setBounds(730, 440, 260, 40);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1000, 810);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Info1;
    private javax.swing.JLabel Info2;
    private javax.swing.JLabel Info3;
    private javax.swing.JLabel Info4;
    private javax.swing.JLabel Info5;
    private javax.swing.JLabel Man;
    private javax.swing.JLabel TOP1;
    private javax.swing.JLabel TOP2;
    private javax.swing.JLabel TOP3;
    private javax.swing.JLabel TOP4;
    private javax.swing.JLabel TOP5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jbhealth;
    private javax.swing.JLabel jbhealthN;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
 
    
    
    }

    boolean isok=false;
    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    if(isok && (e.getKeyCode()==39 || e.getKeyCode()==38)){  
        System.out.println("ture");
        return;
    }
    
    jbhealthN.setText(String.valueOf(Nheal));
        if(e.getKeyCode()==37){               
            g.move(1);         
            Nheal--;
        }
          if(e.getKeyCode()==38){               
            g.move(2);         
            Nheal--;
        }
             if(e.getKeyCode()==39){               
            g.move(3);         
            Nheal--;
        }
        
          if(e.getKeyCode()==40){               
            g.move(4);         
            Nheal--;
        }
           if(e.getKeyCode()==27){               
                 new MainGameFrame().setVisible(true);this.dispose();
        }
       
           if(Nheal==0){
                  jOptionPane1.showMessageDialog(null, "生命耗尽,游戏结束");        
                  new MainGameFrame().setVisible(true);this.dispose();
           }
           
           
           
           
           if(g.Gamebored[g.x][g.y]>1){                      
              
           jPanel2.remove(food[g.Gamebored[g.x][g.y]-2]);         
            g.Gamebored[g.x][g.y]=0;
           Nheal+=22;
           jbhealthN.setText(String.valueOf(Nheal));
           System.out.println("你捡到我吃剩的了，哈哈");          
           }
           
           
           
           
           
           if( g.x==8 && g.y==0){
               isok=true;
             if(gmlevel>=13){
             jOptionPane1.showMessageDialog(null, "恭喜通关,你的通关分数:"+String.valueOf(Nheal));  
            new MainGameFrame().setVisible(true);this.dispose();
             }
               
              updatamap();
               gmlevel++;//游戏难度增加
               refshTitle();
               
              Man.setBounds(0, 515, 60, 60);
              loadgmlevel();

              //sleep(100);
              
              isok=false;
          }
           
             bbuy.Write(bbuy.Money, arm, Nheal);
        //  System.out.println(Man.getBounds().y);
          
 
    }

    @Override
    public void keyReleased(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     
    public void sleep(int _time){
        try {
            Thread.sleep(_time);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }


}
