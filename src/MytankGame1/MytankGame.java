/**
 * ���ܣ�̹����Ϸ1.0
 * ���û�ͼ������������˫��̹��
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

//�ҵ����
class MyPanel extends JPanel{
	//����һ���ҵ�̹��
	Hero hero=null;
	
	public MyPanel() {
		hero=new Hero(10,10);
	}
	
	
	
	
	//��дpaint
	public void paint(Graphics g) {
		super.paint(g);
		
		g.fillRect(0, 0, 400, 300);
		g.setColor(Color.CYAN);
		this.drawTank(hero.getX(), hero.getY(), 0, 0, g);
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
		case 0:
			g.setColor(Color.CYAN);
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
			g.drawLine(x+10, y+10, hero.getX()+10,  y);
		}
	}
}

//̹����
class Tank{
	//��ʾ̹�˵ĺ�����
	int x=0;
	//������
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
//�ҵ�̹��
class Hero extends Tank{
	public Hero(int x,int y) {
		super(x,y);
	}
}

}