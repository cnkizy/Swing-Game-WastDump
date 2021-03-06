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
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;

public class Loadfont
{
	public static Font loadFont(String fontFileName, float fontSize)  //第一个参数是外部字体名，第二个是字体大小
	{
		try
		{
			File file = new File(fontFileName);
			FileInputStream aixing = new FileInputStream(file);
			Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, aixing);
			Font dynamicFontPt = dynamicFont.deriveFont(fontSize);
			aixing.close();
			return dynamicFontPt;
		}
		catch(Exception e)//异常处理
		{
			e.printStackTrace();
			return new java.awt.Font("宋体", Font.PLAIN, 14);
		}
	}
	public static java.awt.Font Font(){
		String root=System.getProperty("user.dir");//项目根目录路径
		Font font = Loadfont.loadFont(root+"/data/PRISTINA.ttf", 18f);//调用
		return font;//返回字体
	}
	public static java.awt.Font Font2(){
		String root=System.getProperty("user.dir");//项目根目录路径
		Font font = Loadfont.loadFont(root+"/data/XXXX.ttf", 18f);
		return font;//返回字体
	}
}