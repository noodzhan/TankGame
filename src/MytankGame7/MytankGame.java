/**
 * ���ܣ�̹����Ϸ��7.0
 * 1������KeyEventʵ���ҵ�̹�˵��ƶ�
 * 2�����ÿ����߳�ʵ���ӵ��Զ��ƶ���ֻ�ܷ�5���ӵ���
 * 3���ӵ�����̹�ˣ�̹����ʧ
 * 4��ʵ�ֱ�ը��Ч��
 * 5��ʵ�ֵ���̹��������������ƶ�
 * 6���������̹���ص�����
 * 7����ʾ��ʼ�ؿ�
 * 8��ʵ�ְ���P��ʵ����ͣ
 *
 */

package MytankGame7;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;
public class MytankGame extends JFrame implements ActionListener{
	
	MyPanel mp=null;
	MystartJPanel mystartJpanel=null;
	JMenuBar jmb=null;
	
	//�˵�
	JMenu jm1=null;
	
	//��ʼ��Ϸ
	JMenuItem jmi1=null;
	
	//�˳�
	JMenuItem jmi2=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MytankGame mytankgame=new MytankGame();
		
		
		
	}
	public MytankGame() {
		
		mystartJpanel =new MystartJPanel();
		
		jmb=new JMenuBar();
		jm1=new JMenu("�˵�");
		
		jmi1=new JMenuItem("��ʼ��Ϸ");
		jmi1.addActionListener(this);
		jmi1.setActionCommand("start");
		
		
		jmi2=new JMenuItem("�˳���Ϸ");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("exit");
		
		jm1.add(jmi1);
		jm1.add(jmi2);
		jmb.add(jm1);
		
		this.setJMenuBar(jmb);
		this.add(mystartJpanel);
	
		
		
		
		
		Thread st=new Thread(mystartJpanel);
		st.start();
		
		
		
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	
	
	
class MystartJPanel extends JPanel implements Runnable{

	boolean b=true;	
	boolean life=true;
	public void paint (Graphics  g) {
		super.paint(g);
		g.fillRect(0, 0, 600, 400);
		if(b) {
			g.setColor(Color.red);
			Font f=new Font("������κ",Font.BOLD,30);
			g.setFont(f);
			g.drawString("Stage 1", 250,200);
		}
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
			//System.out.println("start");
			b=!b;
			this.repaint();
			if(!life) {
				break;
			}
		}
	}
	

	
	
	
}
	

//�ҵ�ս�����
class MyPanel extends JPanel implements KeyListener,Runnable {
	//����һ���ҵ�̹��
	Hero hero=null;
	Vector <Hero> herot=new Vector<Hero>();
	//�����ŵ���̹�˼���
	Vector <EnemyTank> etc=new Vector<EnemyTank>();
	//����ը������
	Vector <Bomb> bomb=new Vector<Bomb>();
	Image image1=null;
	Image image2=null;
	Image image3=null;
	
	//����������״̬�����Ϊtrue����stop,
	//�ڿ�����ͣʱ��ʹ��
	 boolean flag =true;
	
	public MyPanel() {
		hero=new Hero(10,10);
		int enSiz=Info.getEnemynumber();
		for(int i=0;i<enSiz;i++) {
			EnemyTank et=new EnemyTank((i+1)*50,0);
			etc.add(et);
			//��ÿ��̹�˵Ķ�����MyPanel�����еĵз�̹��
			et.getMyPanelEnemyset(etc);
		}
		
		
		image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb1.gif"));
		image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb1.gif"));
		image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb1.gif"));
	
	}
	
	
	
	
	//��дpaint
	public void paint(Graphics g) {
		super.paint(g);
		
		g.fillRect(0, 0, 600, 400);
		
		this.showInfo(g);
		g.setColor(Color.CYAN);
		if(hero.islive){
				this.drawTank(hero.getX(), hero.getY(), hero.direct, 1, g);
				herot.add(hero);
			
			}else {
				herot.remove(hero);
			}
		//���з�̹��
		for(int i=0;i<etc.size();i++) {
			EnemyTank temp=etc.get(i);
			//���з��ӵ�
			//System.out.println("etc.get(i).ss.size()="+etc.get(i).ss.size());
			for(int j=0;j<etc.get(i).ss.size();j++) {
				Shot tempshot=etc.get(i).ss.get(j);
				if(tempshot.islive)
					this.drawShot(tempshot.x, tempshot.y, tempshot.direct, 0, g);
				else {
					//�Ƴ��з�̹�˵������ӵ�
					etc.get(i).ss.remove(tempshot);
					//System.out.println("�Ƴ������ӵ��ɹ�"+tempshot.x+" "+tempshot.y);
				}
				//��������̹�˵��ӵ��߳�
				Thread enemyshotThread=new Thread(tempshot);
				enemyshotThread.start();
			}
			if(temp.islive)
				this.drawTank(temp.getX(), temp.getY(), temp.direct, 0, g);
			else {
				etc.remove(temp);
				//�����ȥ�ĵз�̹��
				//System.out.println("�Ƴ�����̹�˳ɹ�"+temp.x+" "+temp.y);
			}
		}
		//���ҵ�Ӣ���ӵ�
		//System.out.println("hero.ss.size()"+this.hero.ss.size());
		for(int i=0;i<this.hero.ss.size();i++) {
			Shot tempShot=this.hero.ss.get(i);
			if(tempShot!=null&&tempShot.islive)
				this.drawShot(tempShot.x, tempShot.y,tempShot.direct, 1, g);
			//�Ƴ�������Ӣ���ӵ�
			if(tempShot.islive==false) {
				this.hero.ss.remove(i);
				//System.out.println("�Ƴ�Ӣ���ӵ��ɹ�"+tempShot.x+" "+tempShot.y);
			}
		}
		
		//��ը��
		for(int i=0;i<bomb.size();i++) {
			Bomb b=bomb.get(i);
			//��ʵ������ΪBomb������������ǿ��Ƽ��ز�ͬͼƬ��ʱ����
			if(b.life>6) {
				g.drawImage(image1, b.x, b.y, 30, 30,this);
			}else if(b.life>3) {
				g.drawImage(image2, b.x, b.y,30, 30,this);
			}else {
				g.drawImage(image3, b.x, b.y, 30, 30, this);
			}
			//������ֵ����
			b.lifeDown();
			//
			if(b.life==0) {
				bomb.remove(b);
			}
		}
		
		
	}
	
	//��P��ʵ����Ϸֹͣ��
	//˼·���ǰ�̹�˵��ٶȺ��ӵ����ٶ�����Ϊ0��
	public void StopMygame() {
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	//����ʾ��Ϣ
	public void showInfo(Graphics g) {
		//���������²�����ʾ��Ϣ
		this.drawTank(50, 450, 0, 0, g);
		g.setColor(Color.black);
		Font f1=new Font("����",Font.BOLD,14);
		g.setFont(f1);
		g.drawString("ʣ�����", 60, 430);
		g.drawString(":", 80, 470);
		g.drawString(Info.getEnemynumber()+"", 95, 470);
		
		this.drawTank(450, 450, 0, 1, g);
		g.setColor(Color.black);
		g.drawString("����ֵ", 460, 430);
		g.drawString(":", 480, 470);
		g.drawString(Info.getMyherolife()+"", 495, 470);
		
		
		//�����������ϲ�����ʾ��Ϣ
		
		g.drawString("�����ܳɼ���", 630, 20);
		this.drawTank(630, 50, 0, 1, g);
		g.setColor(Color.black);
		g.drawString(":", 660, 70);
		g.drawString(Info.getAllscore()+"",675,70);
		
		
		
		
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
		
		//̹�˵��ӵ�������
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
	//�ж��ӵ��Ƿ���̹�˽Ӵ������Ӵ�����ʾ��ըЧ��
	public void hitTank(Tank t,Shot s) {
		switch(t.direct) {
		case 0:
		case 1:
			if(s.x>t.x&&s.x<t.x+20&&s.y>t.y&&s.y<t.y+30) {
				//����
				//�ӵ�����
				s.islive=false;
				t.islive=false;
				
				if(t instanceof EnemyTank) {
					Info.downenemynumber();
				}else {
					Info.downmyherolife();
				}
				
				
				
				//����ը��
				Bomb bm=new Bomb(t.x,t.y);
				bomb.add(bm);
				
			}break;
		case 2:
		case 3:
			if(s.x>t.x&&s.x<t.x+30&&s.y>t.y&&s.y<t.y+20) {
				s.islive=false;
				t.islive=false;
				
				if(t instanceof EnemyTank) {
					Info.downenemynumber();
				}else {
					Info.downmyherolife();
				}
				
				
				//����ը��,�����뼯��bomb
				Bomb bm=new Bomb(t.x,t.y);
				bomb.add(bm);
			}break;
		}
	}
	//�ж��ҷ�̹���Ƿ���е���̹��
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
		}else if(arg0.getKeyCode()==KeyEvent.VK_P) {
			int hero_ss_size=this.hero.ss.size();
			//����Hero�ӵ����ٶ�
			
			if(flag) {
				System.out.println("��ͣ");
				
				
				
				//��¼�ҷ�̹���ӵ����ٶȣ�������
				for(int i=0;i<hero_ss_size;i++) {
					
					this.hero.ss.get(i).setSpeed(0);
				}
				
				
				//System.out.println("����̹�˵�����"+this.etc.size());
				//��¼�з�̹�˵��ٶȣ�������
				for(int i=0;i<this.etc.size();i++) {
					this.etc.get(i).stopmode=false;
					//System.out.println("��"+i+"������̹�˵��ӵ�����"+this.etc.get(i).ss.size());
					for(int j=0;j<this.etc.get(i).ss.size();j++) {
						this.etc.get(i).ss.get(j).setSpeed(0);
					}
					this.etc.get(i).setSpeed(0);
				}
				
				
				//��Hero̹���ٶ�����
				this.hero.setSpeed(0);
				this.hero.stopmode=false;
				
			}else {
				
				
				/*this.hero.setSpeed(1);
				this.hero.stopmode=true;
				
				
				for(int i=0;i<this.etc.size();i++) {
					this.etc.get(i).stopmode=true;
					//System.out.println("��"+i+"������̹�˵��ӵ�����"+this.etc.get(i).ss.size());
					for(int j=0;j<this.etc.get(i).ss.size();j++) {
						this.etc.get(i).ss.get(j).setSpeed(1);
					}
					this.etc.get(i).setSpeed(1);
				}
				*/
				System.out.println("��ʼ");
			}
			
			
			flag=!flag;
			
		}else if(arg0.getKeyCode()==KeyEvent.VK_Q) {
			
			
			
			
			
			
			
			
			
		}
		//this.repaint();
		//��Ϊ��JPanelҲ�����̣߳�ÿ��200ms  repaint
		//ÿһ֡���200ms�����Բ�Ҫÿ����һ�ΰ����¼����ػ�JPanelһ�Ρ�����ע���Դ��ػ�
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
			//ʱ���ж��ӵ��Ƿ���е���̹��
			this.hitEnemytank();
			//ʱ���жϵ����ӵ��Ƿ�����ҷ�̹��
			for(int i=0;i<this.herot.size();i++) {
				if(this.herot.get(i).islive)
					this.hitMytank();
			}
			this.repaint();
			//System.out.println("MyPanel running");
		}
	
	}


}


@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	if(arg0.getActionCommand().equals("start")) {
		this.mystartJpanel.life=false;
		this.remove(mystartJpanel);
		
		mp=new MyPanel();
		
		this.add(mp);
		this.addKeyListener(mp);
		Thread t=new Thread(mp);
		t.start();
		this.setVisible(true);
		
	}else if(arg0.getActionCommand().equals("exit")) {
		File f=null;
		BufferedWriter bw=null;
		
		try {
			f=new File("C:\\Users\\zhihu\\desktop\\myTankInfo.txt");
			bw=new BufferedWriter(new FileWriter(f));
			bw.write("�ҵ�����ֵ��"+Info.getMyherolife()+"\r\n");
			bw.write("����̹��ʣ��������"+Info.getEnemynumber()+"\r\n");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		System.exit(0);
	}
}


}