/**
 * 功能：坦克游戏的6.0
 * 1、利用KeyEvent实现我的坦克的移动
 * 2、利用开发线程实现子弹自动移动（只能发5颗子弹）
 * 3、子弹击中坦克，坦克消失
 * 4、实现爆炸的效果
 * 5、实现敌人坦克随机上下左右移动
 */

package MytankGame6;
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
		
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	

//我的面板
class MyPanel extends JPanel implements KeyListener,Runnable {
	//定义一个我的坦克
	Hero hero=null;
	Vector <Hero> herot=new Vector<Hero>();
	//定义存放敌人坦克集合
	Vector <EnemyTank> etc=new Vector<EnemyTank>();
	//定义炸弹集合
	Vector <Bomb> bomb=new Vector<Bomb>();
	Image image1=null;
	Image image2=null;
	Image image3=null;
	
	public MyPanel() {
		hero=new Hero(10,10);
		int enSiz=3;
		for(int i=0;i<enSiz;i++) {
			EnemyTank et=new EnemyTank((i+1)*50,0);
			etc.add(et);
			
		}
		
		
		image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb1.gif"));
		image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb1.gif"));
		image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb1.gif"));
	
	}
	
	
	
	
	//重写paint
	public void paint(Graphics g) {
		super.paint(g);
		
		g.fillRect(0, 0, 400, 300);
		g.setColor(Color.CYAN);
		if(hero.islive){
				this.drawTank(hero.getX(), hero.getY(), hero.direct, 1, g);
				herot.add(hero);
			
			}else {
				herot.remove(hero);
			}
		//画敌方坦克
		for(int i=0;i<etc.size();i++) {
			EnemyTank temp=etc.get(i);
			//画敌方子弹
			//System.out.println("etc.get(i).ss.size()="+etc.get(i).ss.size());
			for(int j=0;j<etc.get(i).ss.size();j++) {
				Shot tempshot=etc.get(i).ss.get(j);
				if(tempshot.islive)
					this.drawShot(tempshot.x, tempshot.y, tempshot.direct, 0, g);
				else {
					//移除敌方坦克的死亡子弹
					etc.get(i).ss.remove(tempshot);
					System.out.println("移除敌人子弹成功"+tempshot.x+" "+tempshot.y);
				}
				//开启敌人坦克的子弹线程
				Thread enemyshotThread=new Thread(tempshot);
				enemyshotThread.start();
			}
			if(temp.islive)
				this.drawTank(temp.getX(), temp.getY(), temp.direct, 0, g);
			else {
				etc.remove(temp);
				//清除死去的敌方坦克
				System.out.println("移除敌人坦克成功"+temp.x+" "+temp.y);
			}
		}
		//画我的英雄子弹
		System.out.println("hero.ss.size()"+this.hero.ss.size());
		for(int i=0;i<this.hero.ss.size();i++) {
			Shot tempShot=this.hero.ss.get(i);
			if(tempShot!=null&&tempShot.islive)
				this.drawShot(tempShot.x, tempShot.y,tempShot.direct, 1, g);
			//移除死亡的英雄子弹
			if(tempShot.islive==false) {
				this.hero.ss.remove(i);
				System.out.println("移除英雄子弹成功"+tempShot.x+" "+tempShot.y);
			}
		}
		
		//画炸弹
		for(int i=0;i<bomb.size();i++) {
			Bomb b=bomb.get(i);
			//其实在我认为Bomb里面的生命就是控制加载不同图片的时间间隔
			if(b.life>6) {
				g.drawImage(image1, b.x, b.y, 30, 30,this);
			}else if(b.life>3) {
				g.drawImage(image2, b.x, b.y,30, 30,this);
			}else {
				g.drawImage(image3, b.x, b.y, 30, 30, this);
			}
			//让生命值减少
			b.lifeDown();
			//
			if(b.life==0) {
				bomb.remove(b);
			}
		}
		
		
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
		
		//坦克的子弹的类型
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
			g.fill3DRect(x, y,2, 2, false);
			//System.out.println("drawing 0 shot");
			break;
		case 1:
			g.fill3DRect(x, y,2, 2, false);
			//System.out.println("drawing 1 shot");
			break;
		case 2:
			g.fill3DRect(x, y,2, 2, false);
			//System.out.println("drawing 2 shot");
			break;
		case 3:
			g.fill3DRect(x, y,2, 2, false);
			//System.out.println("drawing 3 shot");
			break;
		}
	}
	//判断子弹是否与坦克接触，若接触并显示爆炸效果
	public void hitTank(Tank t,Shot s) {
		switch(t.direct) {
		case 0:
		case 1:
			if(s.x>t.x&&s.x<t.x+20&&s.y>t.y&&s.y<t.y+30) {
				//击中
				//子弹死亡
				s.islive=false;
				t.islive=false;
				//创建炸弹
				Bomb bm=new Bomb(t.x,t.y);
				bomb.add(bm);
				
			}break;
		case 2:
		case 3:
			if(s.x>t.x&&s.x<t.x+30&&s.y>t.y&&s.y<t.y+20) {
				s.islive=false;
				t.islive=false;
				//创建炸弹,并放入集合bomb
				Bomb bm=new Bomb(t.x,t.y);
				bomb.add(bm);
			}break;
		}
	}
	//判断我方坦克是否击中敌人坦克
	public void hitEnemytank(){
		
		for(int i=0;i<this.hero.ss.size();i++) {
			Shot s=this.hero.ss.get(i);
			if(s.islive) {
				for(int j=0;j<this.etc.size();j++) {
					EnemyTank et=this.etc.get(j);
					if(et.islive) {
						hitTank(et,s);
					}
				}
			}
		}
	}
	public void hitMytank() {
		for(int i=0;i<this.etc.size();i++) {
			EnemyTank t=etc.get(i);
			if(t.islive) {
				for(int j=0;j<t.ss.size();j++) {
					Shot s1=t.ss.get(j);
					if(s1.islive) {
						hitTank(this.hero,s1);
					}
				}
			}
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
		if(arg0.getKeyCode()==KeyEvent.VK_SPACE) {
			
			if(this.hero.ss.size()<5)
				hero.fireEnemy();
			System.out.println("press J");
		}
		//this.repaint();
		//因为我JPanel也做成线程，每个200ms  repaint
		//每一帧大概200ms，所以不要每捕获到一次按键事件，重绘JPanel一次。可以注释自此重绘
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
			//时刻判断子弹是否击中敌人坦克
			this.hitEnemytank();
			//时刻判断敌人子弹是否击中我方坦克
			for(int i=0;i<this.herot.size();i++) {
				if(this.herot.get(i).islive)
					this.hitMytank();
			}
			this.repaint();
			//System.out.println("MyPanel running");
		}
	
	}


}


}