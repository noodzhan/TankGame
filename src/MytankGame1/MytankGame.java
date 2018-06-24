/**
 * 功能：坦克游戏1.0
 * 利用绘图技术画出敌我双方坦克
 */

package MytankGame1;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.Timer;
public class MytankGame extends JFrame{
	
	MyPanel mp=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MytankGame mytankgame=new MytankGame();
		mytankgame.delay();
	}
	public MytankGame() {
		mp=new MyPanel();
		
		this.add(mp);
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void movetank() {
		for(int x=10;x<500;x+=10)
		{
			mp.hero.setX(x);
			delay();
			System.out.println("test");
		}
		
	
}
	public void delay() {
		Timer timer=new Timer();
		TimerTask task=new TimerTask() {
			public void run() {
				movetank();
			}
		};
		timer.schedule(task, 5000);
	}

//我的面板
class MyPanel extends JPanel{
	//定义一个我的坦克
	Hero hero=null;
	
	public MyPanel() {
		hero=new Hero(10,10);
	}
	
	
	
	
	//重写paint
	public void paint(Graphics g) {
		super.paint(g);
		
		g.fillRect(0, 0, 400, 300);
		g.setColor(Color.CYAN);
		this.drawTank(hero.getX(), hero.getY(), 0, 0, g);
	}
	public void drawTank(int x,int y,int direct,int type,Graphics g) {
		//坦克的类型
		switch(type) {
		case 0:
			g.setColor(Color.cyan);
			break;
		case 1:
			g.setColor(Color.yellow);
			break;
			
		}
		switch(direct) {
		case 0:
			g.setColor(Color.CYAN);
			//画出我的坦克（到时候在封装一个函数）
			//1.画左边的矩形
			//g.drawRect(x, y, 5, 30);
			g.fill3DRect(x, y, 5, 30,false);
			//2.画出右边矩形
			g.fill3DRect(x+15, y, 5, 30,false);
			//3.画出中间矩形
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//g.setColor(Color.yellow);
			//4.画出圆形
			g.fillOval(x+4, y+10, 10, 10);
			//5.画直线（炮筒）
			g.drawLine(x+10, y+10, hero.getX()+10,  y);
		}
	}
}

//坦克类
class Tank{
	//表示坦克的横坐标
	int x=0;
	//纵坐标
	int y=0;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public  Tank(int x,int y) {
		this.x=x;
		this.y=y;
	}
	
	
}
//我的坦克
class Hero extends Tank{
	public Hero(int x,int y) {
		super(x,y);
	}
}

}