/**
 * ���ܣ�̹����Ϸ��5.0
 * ����KeyEventʵ��̹�˵��ƶ�
 * ���ÿ����߳�ʵ���ӵ��Զ��ƶ���ֻ�ܷ�5���ӵ���
 * �ӵ�����̹�ˣ�̹����ʧ
 */

package MytankGame5;
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
	
	

//�ҵ����
class MyPanel extends JPanel implements KeyListener,Runnable {
	//����һ���ҵ�̹��
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
	
	
	
	
	//��дpaint
	public void paint(Graphics g) {
		super.paint(g);
		
		g.fillRect(0, 0, 400, 300);
		g.setColor(Color.CYAN);
		this.drawTank(hero.getX(), hero.getY(), hero.direct, 1, g);
		for(int i=0;i<etc.size();i++) {
			EnemyTank temp=etc.get(i);
			if(temp.islive)
				this.drawTank(temp.getX(), temp.getY(), temp.direct, 0, g);
		}
		for(int i=0;i<this.hero.ss.size();i++) {
			Shot tempShot=this.hero.ss.get(i);
			if(tempShot!=null&&tempShot.islive)
				this.drawShot(tempShot.x, tempShot.y,tempShot.direct, 0, g);
			if(!tempShot.islive) {
				this.hero.ss.remove(i);
			}
		}
		
	}
	
	public void drawTank(int x,int y,int direct,int type,Graphics g) {
		//̹�˵�����
		switch(type) {
		case 0:
			g.setColor(Color.cyan);
			break;
		case 1:
			g.setColor(Color.yellow);
			break;
			
		}
		switch(direct) {
		//����̹�˵ķ���ָ������Ͳ�ķ�������
		case 0:
			
			//�����ҵ�̹�ˣ���ʱ���ڷ�װһ��������
			//1.����ߵľ���
			//g.drawRect(x, y, 5, 30);
			g.fill3DRect(x, y, 5, 30,false);
			//2.�����ұ߾���
			g.fill3DRect(x+15, y, 5, 30,false);
			//3.�����м����
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//g.setColor(Color.yellow);
			//4.����Բ��
			g.fillOval(x+4, y+10, 10, 10);
			//5.��ֱ�ߣ���Ͳ��
			g.drawLine(x+10, y+10, x+10,  y);
			
			break;
		case 1://��
			
			//�����ҵ�̹�ˣ���ʱ���ڷ�װһ��������
			//1.����ߵľ���
			//g.drawRect(x, y, 5, 30);
			g.fill3DRect(x, y, 5, 30,false);
			//2.�����ұ߾���
			g.fill3DRect(x+15, y, 5, 30,false);
			//3.�����м����
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//g.setColor(Color.yellow);
			//4.����Բ��
			g.fillOval(x+4, y+10, 10, 10);
			//5.��ֱ�ߣ���Ͳ��
			g.drawLine(x+10, y+20, x+10,  y+30);
			break;
		case 2://��
			
			//�����ҵ�̹�ˣ���ʱ���ڷ�װһ��������
			//1.����ߵľ���
			//g.drawRect(x, y, 5, 30);
			g.fill3DRect(x, y, 30, 5,false);
			//2.�����ұ߾���
			g.fill3DRect(x, y+15,30, 5,false);
			//3.�����м����
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//g.setColor(Color.yellow);
			//4.����Բ��
			g.fillOval(x+10, y+5, 10, 10);
			//5.��ֱ�ߣ���Ͳ��
			g.drawLine(x, y+10, x+10,  y+10);
			break;
		case 3://��
			
			//�����ҵ�̹�ˣ���ʱ���ڷ�װһ��������
			//1.����ߵľ���
			//g.drawRect(x, y, 5, 30);
			g.fill3DRect(x, y, 30, 5,false);
			//2.�����ұ߾���
			g.fill3DRect(x, y+15,30, 5,false);
			//3.�����м����
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//g.setColor(Color.yellow);
			//4.����Բ��
			g.fillOval(x+10, y+5, 10, 10);
			//5.��ֱ�ߣ���Ͳ��
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
	public void hitTank(EnemyTank t,Shot s) {
		switch(t.direct) {
		case 0:
		case 1:
			if(s.x>t.x&&s.x<t.x+20&&s.y>t.y&&s.y<t.y+30) {
				s.islive=false;
				t.islive=false;
				
			}break;
		case 2:
		case 3:
			if(s.x>t.x&&s.x<t.x+30&&s.y>t.y&&s.y<t.y+20) {
				s.islive=false;
				
				t.islive=false;
				
			}break;
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
			
			if(this.hero.ss.size()<5)
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
			this.repaint();
			System.out.println("MyPanel running");
		}
	
	}
}








}