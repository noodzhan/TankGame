/**
 * 功能：坦克游戏的2.0
 * 利用KeyEvent实现坦克的移动
 * 利用开发线程实现子弹自动移动（但是只能发出一颗子弹）
 */

package MytankGame3;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
public class MytankGame extends JFrame{
	
	MyPanel mp=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MytankGame mytankgame=new MytankGame();
		
		
		
	}
	public MytankGame() {
		mp=new MyPanel();
		
		
		
		this.add(mp);
		this.addKeyListener(mp);
		Thread t=new Thread(mp);
		t.start();
		
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	

//我的面板
class MyPanel extends JPanel implements KeyListener,Runnable 
{
	//定义一个我的坦克
	Hero hero=null;
	Vector <EnemyTank> etc=new Vector<EnemyTank>();
	public MyPanel() {
		hero=new Hero(10,10);
		int enSiz=3;
		for(int i=0;i<enSiz;i++) {
			EnemyTank et=new EnemyTank((i+1)*50,0);
			etc.add(et);
		}
	}
	
	
	
	
	//重写paint
	public void paint(Graphics g) {
		super.paint(g);
		
		g.fillRect(0, 0, 400, 300);
		g.setColor(Color.CYAN);
		this.drawTank(hero.getX(), hero.getY(), hero.direct, 1, g);
		for(int i=0;i<etc.size();i++) {
			EnemyTank temp=etc.get(i);
			this.drawTank(temp.getX(), temp.getY(), temp.direct, 0, g);
			
			
		}
		if(hero.shot!=null)
			this.drawShot(hero.shot.x, hero.shot.y,hero.shot.direct, 0, g);
		
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
		//所画坦克的方向指的是炮筒的方向，向上
		case 0:
			
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
			g.drawLine(x+10, y+10, x+10,  y);
			
			break;
		case 1://下
			
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
			g.drawLine(x+10, y+20, x+10,  y+30);
			break;
		case 2://左
			
			//画出我的坦克（到时候在封装一个函数）
			//1.画左边的矩形
			//g.drawRect(x, y, 5, 30);
			g.fill3DRect(x, y, 30, 5,false);
			//2.画出右边矩形
			g.fill3DRect(x, y+15,30, 5,false);
			//3.画出中间矩形
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//g.setColor(Color.yellow);
			//4.画出圆形
			g.fillOval(x+10, y+5, 10, 10);
			//5.画直线（炮筒）
			g.drawLine(x, y+10, x+10,  y+10);
			break;
		case 3://右
			
			//画出我的坦克（到时候在封装一个函数）
			//1.画左边的矩形
			//g.drawRect(x, y, 5, 30);
			g.fill3DRect(x, y, 30, 5,false);
			//2.画出右边矩形
			g.fill3DRect(x, y+15,30, 5,false);
			//3.画出中间矩形
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//g.setColor(Color.yellow);
			//4.画出圆形
			g.fillOval(x+10, y+5, 10, 10);
			//5.画直线（炮筒）
			g.drawLine(x+30, y+10, x+20,  y+10);
			break;
		}
	}
	public void drawShot(int x,int y,int direct,int type,Graphics g) {
		switch(direct) {
		case 0:
			g.fill3DRect(x, y,1, 1, false);
			System.out.println("drawing 0 shot");
			break;
		case 1:
			g.fill3DRect(x, y,1, 1, false);
			System.out.println("drawing 1 shot");
			break;
		case 2:
			g.fill3DRect(x, y,1, 1, false);
			System.out.println("drawing 2 shot");
			break;
		case 3:
			g.fill3DRect(x, y,1, 1, false);
			System.out.println("drawing 3 shot");
			break;
		}
	}



	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==KeyEvent.VK_UP)
		{
			this.hero.direct=0;
			this.hero.moveUp();
		}else if(arg0.getKeyCode()==KeyEvent.VK_DOWN){
			this.hero.direct=1;
			this.hero.moveDown();
			
		}else if(arg0.getKeyCode()==KeyEvent.VK_LEFT) {
			this.hero.direct=2;
			this.hero.moveLeft();
			
		}else if(arg0.getKeyCode()==KeyEvent.VK_RIGHT) {
			
			this.hero.direct=3;
			this.hero.moveRight();
		}
		if(arg0.getKeyCode()==KeyEvent.VK_J) {
			
			hero.fireEnemy();
			System.out.println("press J");
		}
		this.repaint();
	}




	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.repaint();
			System.out.println("MyPanel running");
		}
	
	}
}








}