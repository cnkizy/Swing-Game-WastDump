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
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;


/**
 *
 * @author PR
 */
public class GameStore extends javax.swing.JFrame implements  KeyListener,Runnable {
    
    
        //商店购买道具后可以更换BINGO颜色,0默认 灰色    1,红色,   2橙色  3紫色   4暗金色  new Color(184,134,11)
   // Color bgc[]={new Color(204,204,204),new Color(255,0,0),new Color(255,102,0),new Color(102,102,255),new Color(255,200,0),new Color(204,204,204)};
    Color bgc[]={new Color(204,204,204),new Color(255,0,0),new Color(255,102,0),new Color(102,102,255),new Color(255,200,0),new Color(255,200,0),new Color(204,204,204)};

     Rectangle fir;//临时的全局变量 修改 "->"指针的位置
     
     Rectangle inst_JPANEL;//记录商品第一页的位置
   //  int twot_JPANEL=-750;//记录商品第二页X的位置
     
     IOini bbuy=new IOini();
    
    int down_x =0;//用来控制tips
    Timer timer = new Timer();  
    
    int index=0;//当前箭头指向的索引
    int maxindex=2;//最大索引
    
    int Money=200;//当前身上的金钱
    
    int ARMindex=0;//当前被购买武器索引
    int ARMmaxindex=6;//总共武器数目
    
    int ARMmoney[]={50,80,100,-5000,10000,99999};//购买武器所需金币
    String ARMinf[]={
        "每天一个好心情[可更换游戏背景颜色]",
        "补充每天维生素A[可更换游戏背景颜色]",
        "鱼香茄子好好吃[可更换游戏背景颜色]",
        "支持钟越,点个赞[银行倒贴你5000]",
        "呆萌的熊向你问好[增加一点生命]",
         "老板一定疯了，这是谁啊？卖这么贵"
    };
    String ARMnam[]={"樱桃","胡萝卜","茄子","钱","大白","神秘大叔"};
    
    int armx=0;//大白个数
    int arm=0;//背景颜色 代码
    
     int Store_page=0;//商店页数
     
    int Install_Label_y=0;//第一次 “->”的初始Y值
    
    
   
   final Color Select_true = new Color(255,0,0);
   final Color Select_false = new Color(204,204,204);
   
    
    /**
     * Creates new form GameStore
     */
    public GameStore(int width,int height) {   
        initComponents();
        
 
        
        Jlabelindex.setVisible(true);
         inst_JPANEL=jPanel4.getBounds();
         
         this.setBounds(this.getBounds().x,this.getBounds().y , width, height);
         Font Piexfont = Loadfont.loadFont(System.getProperty("user.dir")+"/res/ttf/方正像素15.ttf", 80f);//调用外部像素 ttf
         Font smellPiexfont = Loadfont.loadFont(System.getProperty("user.dir")+"/res/ttf/方正像素15.ttf", 45f);//调用外部像素 ttf
         Font sosmellPiexfont = Loadfont.loadFont(System.getProperty("user.dir")+"/res/ttf/方正像素15.ttf", 35f);//调用外部像素 ttf
         Font sososmellPiexfont = Loadfont.loadFont(System.getProperty("user.dir")+"/res/ttf/方正像素15.ttf", 25f);//调用外部像素 ttf

        
         TITLE.setFont(Piexfont);
         
         //设置字体
         jLabel1.setFont(smellPiexfont);
         jLabel2.setFont(smellPiexfont);
         Jlabelindex.setFont(smellPiexfont);
         jLabelScore.setFont(smellPiexfont);
         jLabelARM_inf.setFont(sosmellPiexfont);
         jLabelbuy.setFont(smellPiexfont);
         
         WAR_1.setFont(smellPiexfont);
         WAR_2.setFont(smellPiexfont);
         WAR_3.setFont(smellPiexfont);
         WAR_4.setFont(smellPiexfont);
         WAR_5.setFont(smellPiexfont);
         WAR_6.setFont(smellPiexfont);
         jLabeltips.setFont(sosmellPiexfont);
         WAR_5INF.setFont(sososmellPiexfont);
         armxlb.setFont(sosmellPiexfont);
         
         //设置颜色    
         Jlabelindex.setForeground(Select_true);
          jLabel1.setForeground(Select_true);
          jLabel2.setForeground(Select_false);

           WAR_1.setForeground(Select_true);
           WAR_2.setForeground(Select_false);
           WAR_3.setForeground(Select_false);
           WAR_4.setForeground(Select_false);
           WAR_5.setForeground(Select_false);
           WAR_6.setForeground(Select_false);
           
          //读取上次游戏的数据
         bbuy.Read();
         arm=bbuy.Arm+1;
         Money=bbuy.Money;
         armx=bbuy.Armx;
         
         
         
         
         if(Money<300){Money=300;}
         
 jLabelSTYKJBS10.setVisible(true);
         LoadRoom();
        arm--;
         
         armxlb.setText("X"+String.valueOf(armx));
         showMoney();
         
         Install_Label_y=Jlabelindex.getBounds().y;
         this.addKeyListener(this);
    }


public void LoadRoom(){
        jPanelcolor.setBackground(bgc[arm]);
}
    
            
  

     static class Downt extends TimerTask {  
       javax.swing.JLabel d;
       int  down_x=0;
         Downt(javax.swing.JLabel s){
             this.d=s;
         }
         
            @Override
            public void run() {           
         down_x++;  
         d.setBounds(d.getBounds().x, d.getBounds().y+2, d.getBounds().width, d.getBounds().height);
          if(down_x>=45){
              d.setBounds(d.getBounds().x,-50, d.getBounds().width, d.getBounds().height);
            this.cancel();
        }
         
         
         
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

        jPanel1 = new javax.swing.JPanel();
        TITLE = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Jlabelindex = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabelScore = new javax.swing.JLabel();
        jLabelARM_inf = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabeltips = new javax.swing.JLabel();
        jLabelbuy = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabelWAR_1 = new javax.swing.JLabel();
        WAR_1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        WAR_2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        WAR_3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        WAR_5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        WAR_4 = new javax.swing.JLabel();
        WAR_5INF = new javax.swing.JLabel();
        WAR_6 = new javax.swing.JLabel();
        jLabelSTYKJBS10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        armxlb = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanelcolor = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 800));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(1040, 561));
        jPanel1.setLayout(null);

        TITLE.setFont(new java.awt.Font("宋体", 0, 80)); // NOI18N
        TITLE.setForeground(new java.awt.Color(255, 204, 102));
        TITLE.setText("STORE");
        jPanel1.add(TITLE);
        TITLE.setBounds(370, 30, 263, 92);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(null);

        jLabel1.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("BUY");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(70, 20, 130, 50);

        Jlabelindex.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        Jlabelindex.setForeground(new java.awt.Color(204, 0, 255));
        Jlabelindex.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Jlabelindex.setText("->");
        Jlabelindex.setToolTipText("");
        jPanel2.add(Jlabelindex);
        Jlabelindex.setBounds(10, 20, 50, 50);
        Jlabelindex.getAccessibleContext().setAccessibleName("");

        jLabel2.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 102));
        jLabel2.setText("BACK");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(70, 80, 130, 50);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(20, 310, 190, 250);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelScore.setFont(new java.awt.Font("宋体", 0, 30)); // NOI18N
        jLabelScore.setForeground(new java.awt.Color(102, 255, 102));
        jLabelScore.setText("Money：");
        jPanel3.add(jLabelScore, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 700, 60));

        jPanel1.add(jPanel3);
        jPanel3.setBounds(260, 170, 750, 60);

        jLabelARM_inf.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        jLabelARM_inf.setForeground(new java.awt.Color(0, 153, 255));
        jLabelARM_inf.setText("樱桃,每天一个好心情[可更换背景颜色]");
        jPanel1.add(jLabelARM_inf);
        jLabelARM_inf.setBounds(220, 610, 790, 100);

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));
        jPanel5.setLayout(null);

        jLabeltips.setFont(new java.awt.Font("宋体", 0, 30)); // NOI18N
        jLabeltips.setForeground(new java.awt.Color(102, 255, 102));
        jLabeltips.setText("恭喜购买成功!!!!!!!!!!");
        jPanel5.add(jLabeltips);
        jLabeltips.setBounds(70, -40, 480, 60);
        jLabeltips.setVisible(false);

        jPanel1.add(jPanel5);
        jPanel5.setBounds(270, 230, 750, 60);

        jLabelbuy.setFont(new java.awt.Font("宋体", 0, 35)); // NOI18N
        jLabelbuy.setForeground(new java.awt.Color(102, 255, 255));
        jLabelbuy.setText("购买");
        jPanel1.add(jLabelbuy);
        jLabelbuy.setBounds(80, 630, 110, 50);

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel7.setLayout(null);

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setLayout(null);

        jLabelWAR_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/WAR_1.png"))); // NOI18N
        jPanel4.add(jLabelWAR_1);
        jLabelWAR_1.setBounds(50, 40, 180, 130);

        WAR_1.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        WAR_1.setForeground(new java.awt.Color(255, 0, 0));
        WAR_1.setText("$50");
        jPanel4.add(WAR_1);
        WAR_1.setBounds(90, 200, 130, 70);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/WAR_2.png"))); // NOI18N
        jPanel4.add(jLabel3);
        jLabel3.setBounds(300, 40, 160, 140);

        WAR_2.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        WAR_2.setForeground(new java.awt.Color(255, 0, 0));
        WAR_2.setText("$80");
        jPanel4.add(WAR_2);
        WAR_2.setBounds(310, 200, 130, 70);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/WAR_3.png"))); // NOI18N
        jPanel4.add(jLabel4);
        jLabel4.setBounds(530, 50, 140, 120);

        WAR_3.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        WAR_3.setForeground(new java.awt.Color(255, 0, 0));
        WAR_3.setText("$100");
        jPanel4.add(WAR_3);
        WAR_3.setBounds(540, 200, 130, 70);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/money.gif"))); // NOI18N
        jLabel5.setText("jLabel5");
        jPanel4.add(jLabel5);
        jLabel5.setBounds(760, 40, 250, 160);

        WAR_5.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        WAR_5.setForeground(new java.awt.Color(255, 0, 0));
        WAR_5.setText("$10000");
        jPanel4.add(WAR_5);
        WAR_5.setBounds(1240, 200, 180, 70);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/beer.gif"))); // NOI18N
        jLabel6.setText("jLabel6");
        jPanel4.add(jLabel6);
        jLabel6.setBounds(1230, 70, 130, 120);

        WAR_4.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        WAR_4.setForeground(new java.awt.Color(255, 0, 0));
        WAR_4.setText("$+5000");
        jPanel4.add(WAR_4);
        WAR_4.setBounds(840, 200, 170, 70);

        WAR_5INF.setForeground(new java.awt.Color(102, 255, 0));
        WAR_5INF.setText("仅叠加个数");
        jPanel4.add(WAR_5INF);
        WAR_5INF.setBounds(1250, 40, 170, 40);

        WAR_6.setFont(new java.awt.Font("宋体", 0, 40)); // NOI18N
        WAR_6.setForeground(new java.awt.Color(255, 0, 0));
        WAR_6.setText("$99999");
        jPanel4.add(WAR_6);
        WAR_6.setBounds(1750, 230, 180, 70);

        jLabelSTYKJBS10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/1=0.jpg"))); // NOI18N
        jLabelSTYKJBS10.setText("jLabel6");
        jPanel4.add(jLabelSTYKJBS10);
        jLabelSTYKJBS10.setBounds(1640, 20, 330, 220);

        jPanel7.add(jPanel4);
        jPanel4.setBounds(0, 0, 2000, 300);

        jPanel1.add(jPanel7);
        jPanel7.setBounds(210, 310, 740, 320);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/smellbeer.gif"))); // NOI18N
        jPanel1.add(jLabel7);
        jLabel7.setBounds(660, 90, 70, 60);

        armxlb.setForeground(new java.awt.Color(255, 51, 51));
        armxlb.setText("X0");
        jPanel1.add(armxlb);
        armxlb.setBounds(840, 100, 100, 50);

        jLabel8.setForeground(new java.awt.Color(51, 204, 0));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/Color.png"))); // NOI18N
        jLabel8.setToolTipText("");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(660, 40, 140, 40);

        jPanelcolor.setBackground(new java.awt.Color(0, 255, 0));
        jPanelcolor.setLayout(null);
        jPanel1.add(jPanelcolor);
        jPanelcolor.setBounds(840, 40, 60, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Jlabelindex;
    private javax.swing.JLabel TITLE;
    private javax.swing.JLabel WAR_1;
    private javax.swing.JLabel WAR_2;
    private javax.swing.JLabel WAR_3;
    private javax.swing.JLabel WAR_4;
    private javax.swing.JLabel WAR_5;
    private javax.swing.JLabel WAR_5INF;
    private javax.swing.JLabel WAR_6;
    private javax.swing.JLabel armxlb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelARM_inf;
    private javax.swing.JLabel jLabelSTYKJBS10;
    private javax.swing.JLabel jLabelScore;
    private javax.swing.JLabel jLabelWAR_1;
    private javax.swing.JLabel jLabelbuy;
    public javax.swing.JLabel jLabeltips;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanelcolor;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        if(reisOverdh && (e.getKeyCode()==39 ||  e.getKeyCode()==37)){//翻页动画是否完成
            System.out.println("正在处理翻页线程。请不要狂摁,线程被你玩坏了你赔得起吗？");
        return;
        }
        
        
        switch (e.getKeyCode()) {
            case 37: {//左键
                ARMindex--;
                if (ARMindex < 0) {
                    ARMindex = 0;
                }
                // showLabelDown();
                ShowBuy();
                break;
            }
            case 39: {//右键       
                ARMindex++;
                if (ARMindex > ARMmaxindex - 1) {
                    ARMindex = ARMmaxindex-1;
                }
              //  showLabelDown();
                ShowBuy();
                break;
            }
            case 40: {//下键
                ++index;
                if (index > maxindex - 1) {
                    index = 0;
                }
                showLabelDown();
                break;
            }
            case 38: {//上键

                --index;
                if (index < 0) {
                    index = maxindex - 1;
                }
                showLabelDown();
                break;
            }

            case 10: {

                switch (index) {
                    case 0:
                        buy(ARMindex);
                        break;//购买
                    case 1:
                        new MainGameFrame().setVisible(true);
                        this.dispose();
                        break;//返回
                    case 3:
                        System.exit(0);//选择EXIT
                }

                break;

            }

        }
        
    
    
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
    public void buy(int armindex){  
        if(Money<ARMmoney[armindex]){        
            showTips("真可惜,你身上的钱不够");
            return;
        }           
         Money=Money-ARMmoney[armindex];//扣除购买的钱        
        
         switch(armindex){
             case 0:
             case 1:
              case 2:arm=armindex+1;LoadRoom();bbuy.Write(Money, armindex,armx);break;    
             case 3:{           
                 arm=4;
                 LoadRoom();
                 bbuy.Write(Money, armindex,armx);
                 break; 
             }
             case 4:{            
              armx++; 
              armxlb.setText("X"+String.valueOf(armx));
              bbuy.Write(Money, arm,armx);
              break;
              }//写游戏数据到文件，保存游戏数据
         case 5:{     
         arm=5;           //最初没设计好，只有用代码补偿BUG了。诶 
         bbuy.Write(Money, 5,armx);
         LoadRoom();       
         break;   
         }
         
         }
         
     //bbuy.Write(Money, armindex,armx);//写游戏数据到文件，保存游戏数据
                 
         showTips("恭喜，"+ARMnam[armindex]+" X1 购买成功");
         showMoney();
    }
    
       public void showMoney(){
        jLabelScore.setText("Money X"+String.valueOf(Money)); 
    }
    
    
    
    public void showTips(String strtip){
     jLabeltips.setVisible(true);
     jLabeltips.setText(strtip);
     timer.schedule(new Downt(jLabeltips), 1,20);// 两秒后启动任务  

          
    }
    
    boolean reisOverdh=false;
    
    public void ShowBuy(){
                switch(ARMindex){
            case 0://选择第1件装备
            {               
             WAR_1.setForeground(Select_true);
             WAR_2.setForeground(Select_false);   
             break;
            } 
            case 1://选择第2件装备
            {            
             WAR_1.setForeground(Select_false);
             WAR_2.setForeground(Select_true);
             WAR_3.setForeground(Select_false);   
             break;   
            } 
            case 2://选择第3件装备
            {            

                if(isOverdh){
               if(Store_page==1){
                   LR_Page=false;
                 sx= new Thread(this);
                   sx.start();
            }     
              Store_page=0;
                }
              

             WAR_2.setForeground(Select_false);
              WAR_3.setForeground(Select_true);   
              WAR_4.setForeground(Select_false);   

                          
         System.out.println("当前商店第"+Store_page+"页");
              
             break;   
            }
              case 3://选择第4件装备
            {            

                     if(isOverdh){
           if(Store_page==0){
                LR_Page=true;
            sx= new Thread(this);
             sx.start();
            }     
           Store_page=1;
                     }
             
             WAR_3.setForeground(Select_false);
             WAR_4.setForeground(Select_true);   
             WAR_5.setForeground(Select_false);   

              
              
     System.out.println("当前商店第"+Store_page+"页");
             
             break;   
         
            }
            case 4://选择第5件装备
            {            
                
           if(isOverdh){
           if(Store_page==2){
                LR_Page=false;
            sx= new Thread(this);
             sx.start();
            }     
           Store_page=1;
          } 
                
                
                
                
             WAR_4.setForeground(Select_false);
             WAR_5.setForeground(Select_true);   
             WAR_6.setForeground(Select_false);
             break;          
             
            }
            case 5://选择第5件装备
            {         
                
           if(isOverdh){
           if(Store_page==1){
                LR_Page=true;
            sx= new Thread(this);
             sx.start();
            }     
           Store_page=2;
          }                      
             WAR_5.setForeground(Select_false);     
             WAR_6.setForeground(bgc[4]);   //暗金色 独显尊贵         
             break;   
            }

            
        }
        jLabelARM_inf.setText(ARMnam[ARMindex]+","+ARMinf[ARMindex]);     
    }
    
    Thread sx=new Thread(this);
    boolean isOverdh=true;//是否播放完毕
   boolean LR_Page=true;//true 应该往右翻页,false应该往左翻页
    
        @Override
    public void run() {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates. 
        reisOverdh=true;
        isOverdh=false;
        
        if(LR_Page){       
        sx_move_page(1) ;
        }else{       
        sx_move_page(0) ;            
        }
        
        
    
//        switch(Store_page){     
//        case 0:{  
//     sx_move_page(Store_page) ;
//            break;
//        }
//        case 1:{
//             sx_move_page();//左到右
//         
//            
//            break;
//        }
//       }
//    

           //  sx.destroy();
    }
    //inst_JPANEL==java.awt.Rectangle[x=-750,y=0,width=1400,height=300]//第二页
public void sx_move_page(int p){//P=1 往右位移 P=0往左溢
    boolean bpage=true;
    int MOVE_wy=0;
       while(bpage){
                MOVE_wy++;             
                if(MOVE_wy>=375){//JPANEL4的宽度           
                bpage=false;//停止动画              
                }
                
                if(p==1){
                    inst_JPANEL.x-=2;
                }else{
                    inst_JPANEL.x+=2;
                }
            
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameStore.class.getName()).log(Level.SEVERE, null, ex);
                }
                jPanel4.setBounds(inst_JPANEL);
        }
        reisOverdh=false;
       isOverdh=true;
    sx.stop();
    
}

   
     public void showLabelDown(){
         fir=Jlabelindex.getBounds();
       // System.out.println("当前选择项："+index); 
        
        switch(index){
            case 0://选择BUY
            {  
             jLabel1.setForeground(Select_true);
             jLabel2.setForeground(Select_false);                
             jLabelbuy.setText("购买");
             break;
            } 
            case 1://选择BACK
            {            
             jLabel1.setForeground(Select_false);
             jLabel2.setForeground(Select_true);
             jLabelbuy.setText("返回");
             break;   
            }              
        }
       fir.y=Install_Label_y+60*index;     
       Jlabelindex.setBounds(fir);       

}
    
    

    
    
    
    
    
    
    
    
    
    
    
}






