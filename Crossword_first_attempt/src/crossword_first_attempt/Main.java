package crossword_first_attempt;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.net.URL;
import java.util.*;
import javax.swing.*;


public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(() ->
        {
           SimpleFrame frame = new SimpleFrame();
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setVisible(true);
           frame.setResizable(false);
        });

	}

}

class SimpleFrame extends JFrame
{
	public SimpleFrame()
	{
		JPanel crossword;
		Crossword obiekt= new Crossword();;
		crossword = new JPanel(new BorderLayout());
		crossword.add(obiekt,BorderLayout.CENTER);
		
		JButton startMenu = new JButton("Start");
		startMenu.addActionListener(new java.awt.event.ActionListener() 
		{
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
            	obiekt.setstart(true);
            	repaint();
            }
         });
		JButton restartMenu = new JButton("Restart");
		restartMenu.addActionListener(new java.awt.event.ActionListener() 
		{
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
            	obiekt.restart();
            	repaint();
            }
         });
		JButton exitMenu = new JButton("Exit");
		exitMenu.addActionListener(new java.awt.event.ActionListener() 
		{
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
            	
            	System.exit(0);
             }
         });
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		startMenu.setFocusable(false);
		restartMenu.setFocusable(false);
		exitMenu.setFocusable(false);
		menuBar.add(startMenu);
	    menuBar.add(restartMenu);
	    menuBar.add(exitMenu);
	    obiekt.setFocusable(true);
		add(crossword);
		pack();
	}
}

class Crossword extends JComponent implements KeyListener
{
	private static final int DEFAULT_WIDTH = 1005;
	private static final int DEFAULT_HEIGHT = 600;
	private Point2D coordinates = new Point2D.Double(0,0);
	private static boolean start=false;
	private Point2D[] squares = new Point2D[150];
	private Crossword1 newcrossword = new Crossword1();
	private boolean mouseclicked = false;
	private Point2D clickedsquare = new Point2D.Double(0,0);
	private int counter = 0;
	private char[] letters = new char[150]; //tablica na wprowadzone litery do krzy¿ówki
	private int pointer = 0;
	private int which_triangle=0;
	private char[] errata = new char[2];
	private int[] results=null;
	private boolean win = false;
	private Image pamela;
	

	public Crossword()
	{
		URL url1 = getClass().getResource("Pamela.jpg");
		pamela=new ImageIcon(url1).getImage();
		for(int i = 0; i<10;i++)
		{
			for(int r = 0;r<15;r++)
			{
				squares[r+(i*15)] = new Point2D.Double(15+(r*65),15+(i*57)); //tworzenie tablicy ze wspó³rzêdnymi poszczególnych kratek krzy¿ówki
				
			}
		}
		addMouseListener(new MouseHandler());
	    addMouseMotionListener(new MouseMotionHandler());
	    this.addKeyListener(this);
	}
	
	public void restart() //restartuje ca³¹ krzy¿ówkê czyszcz¹c ca³¹ tablicê wprowadzonych liter
	{
		clickedsquare = new Point2D.Double(0,0);
		mouseclicked = false;
		letters = new char[150];
		errata = new char[2];
	}
	
	public Point2D getcoordinates()
	{
		return coordinates;
	}
	public void setcoordinates(int x,int y)
	{
		coordinates.setLocation(x,y);
	}
	
	public void setstart(boolean value)
	{
		start=value;
	}
	
	public Point2D whichsquare()
	{
		for(int i=0;i<150;i++)
		{
			if(newcrossword.getsquare_checker()[i]==false) //sprawdzenie czy kratka wskazywana przez mysz jest bez pytania 
			{
			if(squares[i].getX()<=getcoordinates().getX() && squares[i].getX()+65>=getcoordinates().getX() && squares[i].getY()<=getcoordinates().getY() && squares[i].getY()+57>=getcoordinates().getY()  )  // warunek wybieraj¹cy w³aœciw¹ kratkê do podœwietlenia 
			{
				return squares[i];
			}
			}
		}
		return new Point2D.Double(0,0);
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		if(start==true && win==false)
		{
		
		Rectangle2D rect = new Rectangle2D.Double(10, 10, 985 , 580); //rysowanie bazowego prostok¹ta dla krzy¿ówki
	    g2.setPaint(Color.BLACK);
	    g2.setStroke(new BasicStroke(5));
	    g2.draw(rect);
	    
	    g2.setStroke(new BasicStroke(1));
	    g2.draw(new Line2D.Double(80,15,80,585)); // kolejne linie krzy¿ówki tworz¹ce potem kratki 
	    g2.draw(new Line2D.Double(145,15,145,585));
	    g2.draw(new Line2D.Double(210,15,210,585));
	    g2.draw(new Line2D.Double(275,15,275,585));
	    g2.draw(new Line2D.Double(340,15,340,585));
	    g2.draw(new Line2D.Double(405,15,405,585));
	    g2.draw(new Line2D.Double(470,15,470,585));
	    g2.draw(new Line2D.Double(535,15,535,585));
	    g2.draw(new Line2D.Double(600,15,600,585));
	    g2.draw(new Line2D.Double(665,15,665,585));
	    g2.draw(new Line2D.Double(730,15,730,585));
	    g2.draw(new Line2D.Double(795,15,795,585));
	    g2.draw(new Line2D.Double(860,15,860,585));
	    g2.draw(new Line2D.Double(925,15,925,585));
	    
	    g2.draw(new Line2D.Double(15,72,990,72)); // kolejne linie krzy¿ówki tworz¹ce potem kratki
	    g2.draw(new Line2D.Double(15,129,990,129));
	    g2.draw(new Line2D.Double(15,186,990,186));
	    g2.draw(new Line2D.Double(15,243,990,243));
	    g2.draw(new Line2D.Double(15,300,990,300));
	    g2.draw(new Line2D.Double(15,357,990,357));
	    g2.draw(new Line2D.Double(15,414,990,414));
	    g2.draw(new Line2D.Double(15,471,990,471));
	    g2.draw(new Line2D.Double(15,528,990,528));
	    
	    newcrossword.draw_crossword(g2); //rozrysowanie pytañ do krzy¿ówki 
	    
	    g2.setPaint(Color.BLUE);
	    g2.setStroke(new BasicStroke(3));
	    if(mouseclicked==false)
	    {
	    	double x = whichsquare().getX();
	    	double y = whichsquare().getY();
	    	
	    if(x!=0 && y!=0) // sprawdzenie czy metoda whichsquare nie zwróci³a zer
	    {
	    		if(x!=600 || y!=300)
	    		{
	    		Rectangle2D kwadrat = new Rectangle2D.Double(whichsquare().getX(),whichsquare().getY(),65,57); 
	    		g2.draw(kwadrat); //poœwietlanie kratek pod¹¿aj¹ce za przemieszczanym kursorem myszy
	    		}
	    		else 
	    		{
	    			double tangens = (357-getcoordinates().getY())/(getcoordinates().getX()-600);
	    			double default_tangens=57.0/65.0;
	    			if(tangens < default_tangens)
	    				{
	    					g2.draw(new Line2D.Double(600,357,665,300));
	    					g2.draw(new Line2D.Double(600,357,665,357));
	    					g2.draw(new Line2D.Double(665,357,665,300));
	    					which_triangle = 1;
	    				}
	    			if(tangens > default_tangens)
	    			{
	    				g2.draw(new Line2D.Double(600,357,665,300));
    					g2.draw(new Line2D.Double(600,357,600,300));
    					g2.draw(new Line2D.Double(600,300,665,300));
    					which_triangle = 2;
	    			}
	    			
	    		}
	  
	    
	    }
	    } else if(clickedsquare.getX()!=0 && clickedsquare.getY()!=0)
	    {
	    	if(clickedsquare.getX()!=600 || clickedsquare.getY()!=300){
	    	Rectangle2D kwadrat_beta = new Rectangle2D.Double(clickedsquare.getX(),clickedsquare.getY(),65,57);
	    	g2.draw(kwadrat_beta); //poœwietlenie klikniêtej kratki
	    	counter++; //zmiennna, która odpowiada za mechanizm migaj¹cego kursora myszy
	    	if(counter < 200)
	    	{
	    		g2.setPaint(Color.BLACK);
	    		g2.setStroke(new BasicStroke(1));
	    		g2.draw(new Line2D.Double(clickedsquare.getX() +15,clickedsquare.getY()+10,clickedsquare.getX()+15,clickedsquare.getY()+47)); //mrugaj¹cy kursor do pisania
	    	}else if(counter == 400)
	    	{
	    		counter = 0;
	    	}
	    } else 
	    {
	    	if(which_triangle == 1)
	    	{
	    		g2.draw(new Line2D.Double(600,357,665,300));
				g2.draw(new Line2D.Double(600,357,665,357)); 
				g2.draw(new Line2D.Double(665,357,665,300)); // insturkcje które podœwietlaj¹ jedn¹ z po³ówek podzielonego pola ( w tym przypadku prawa po³ówka )
				counter++;
				if(counter < 200)
		    	{
		    		g2.setPaint(Color.BLACK);
		    		g2.setStroke(new BasicStroke(1)); //kursor w podzielonym polu
		    		g2.draw(new Line2D.Double(665-10,300+20,665-10,300+47));
		    	}else if(counter == 400)
		    	{
		    		counter = 0;
		    	}
	    	}
	    	if(which_triangle == 2)
	    	{
	    		g2.draw(new Line2D.Double(600,357,665,300));
				g2.draw(new Line2D.Double(600,357,600,300));
				g2.draw(new Line2D.Double(600,300,665,300)); // insturkcje które podœwietlaj¹ jedn¹ z po³ówek podzielonego pola ( w tym przypadku prawa po³ówka )
				counter++;
				if(counter < 200)
		    	{
		    		g2.setPaint(Color.BLACK);
		    		g2.setStroke(new BasicStroke(1)); //kursor w podzielonym polu
		    		g2.draw(new Line2D.Double(600+10,300+10,600+10,300+35));
		    	}else if(counter == 400)
		    	{
		    		counter = 0;
		    	}
	    	}
	    }
	    }
	    g2.setFont(new Font(null, Font.PLAIN, 40));
	    g2.setPaint(Color.BLACK);
	    for(int i = 0; i<10;i++)
		{
			for(int r = 0;r<15;r++)
			{
				if(newcrossword.getsquare_checker()[r+(i*15)]==false)
				{
					if(i!=5 || r!=9)
					{
						g2.setFont(new Font(null, Font.PLAIN, 40));
						g2.drawString(""+letters[r+(i*15)],15+(r*65)+20,15+(i*57)+40); //wyrysowanie liter wpisanych do krzy¿ówki 
					}
					else
					{
						
						g2.setFont(new Font(null, Font.PLAIN, 30));
						g2.drawString(""+errata[0],15+(r*65)+5,15+(i*57)+30);
						g2.drawString(""+errata[1],15+(r*65)+35,15+(i*57)+50);
						
					}
				}
			}
		}
	    if(!Arrays.equals(results, null))
		{
			if(!Arrays.equals(results, new int[]{0}))
			{
				for(int i=0;i<results.length;i++)
				{
					g2.setPaint(Color.RED);
				    g2.setStroke(new BasicStroke(3));
				    if(results[i]!=84)
				    {
					g2.draw(new Rectangle2D.Double(squares[results[i]].getX(),squares[results[i]].getY(),65,57));
				    }else if(results[3]==83)
					{
				    	g2.draw(new Line2D.Double(600,357,665,300));
						g2.draw(new Line2D.Double(600,357,600,300));
						g2.draw(new Line2D.Double(600,300,665,300));
					}else if(results[1]==99)
					{
						g2.draw(new Line2D.Double(600,357,665,300));
						g2.draw(new Line2D.Double(600,357,665,357)); 
						g2.draw(new Line2D.Double(665,357,665,300)); 
					}
				}
				counter++;
				if(counter==700)
				{
				for(int i=0;i<results.length;i++)
				{
					if(results[i]!=84)
					{
					letters[results[i]]='\u0000';
					}else if(results[3]==83)
					{
						errata[0]='\u0000';
					}else if(results[1]==99)
					{
						errata[1]='\u0000';
					}
				}
				results=null;
				counter=0;
				}
			}
			else
			{
				win=true;
			}
		}
	    repaint();
		}
		if(win==true)
		{
			 mouseclicked=false;
			 int image1Width = 1005;
			 int image1Height = 600;
			 g2.drawImage(pamela, 0, 0,image1Width,image1Height, null);
			 g2.setFont(new Font(null, Font.PLAIN, 40));
			 g2.setPaint(Color.BLACK);
			 g2.drawString("BRAWO!!!", 50, 200);
			 g2.drawString("Rozwi¹za³eœ krzy¿ówkê!!!", 50, 235);
			 g2.drawString("W nagrodê", 555, 300);
			 counter++;
			 if(counter<150)
			 {
				 g2.setPaint(Color.RED);
				 g2.drawString("GO£A PAMELA ANDERSON", 450, 350);
			 }else
			 {
				 g2.setPaint(Color.BLACK);
				 g2.drawString("GO£A PAMELA ANDERSON", 450, 350);
			 }
			 if(counter==300)
			 {
				 counter=0;
			 }
			 
			 repaint();
		}
		
		
	}
	
	private class MouseHandler extends MouseAdapter
	{
		public void mouseClicked(MouseEvent event)
	      {
			if(start == true)
			{
	         mouseclicked = true;
	         clickedsquare = whichsquare();
	         for(int i=0;i<150;i++)
	         {
	        	 if(clickedsquare == squares[i])
	        		 pointer=i;
	         }
	         double tangens = (357-getcoordinates().getY())/(getcoordinates().getX()-600);
 			 double default_tangens=57.0/65.0;// fragment kodu wychwytuj¹cy 
 			 if(tangens < default_tangens)// który trójk¹t w podzielonym polu
 				 {                                  // zosta³ klikniêty
 					 which_triangle = 1;
 				 }
 			 if(tangens > default_tangens)
 			 {
					 which_triangle = 2;
 			 }
	      }
	      }
	}
	
	private class MouseMotionHandler implements MouseMotionListener
	{
		public void mouseMoved(MouseEvent event)
		{
			int x = event.getX();
			int y = event.getY();
			setcoordinates(x,y);
		}
		
		public void mouseDragged(MouseEvent event)
		{
			
		}
		
	}
	
	public void keyTyped (KeyEvent e)
	{
		if(mouseclicked==true && start == true)
		{
			if(pointer!=84) //warunek który unika przypadku trafienia na pole przeciête na pó³
			{
			letters[pointer]=Character.toUpperCase(e.getKeyChar());
			if(letters[pointer]=='\u0008') // w razie skasowania litery poprzez backspace, warunek if przywraca wartoœæ domyœln¹ pola
			{
				letters[pointer]='\u0000';
			}
			}else // co jeœli trafi siê na pole przeciête na pó³
			{
				if(which_triangle ==1)
				{
					errata[1] = Character.toUpperCase(e.getKeyChar());
					if(errata[1]=='\u0008')// w razie skasowania litery poprzez backspace, warunek if przywraca wartoœæ domyœln¹ pola
					{
						errata[1]='\u0000';
					}
				}
				if(which_triangle == 2)
				{
					errata[0] = Character.toUpperCase(e.getKeyChar());
					if(errata[0]=='\u0008')// w razie skasowania litery poprzez backspace, warunek if przywraca wartoœæ domyœln¹ pola
					{
						errata[0]='\u0000';
					}
				}
			}
			pointer=0;
			results=newcrossword.check_words(letters, errata);
			mouseclicked=false;
		}
	}
	public void keyPressed (KeyEvent e){}
	public void keyReleased (KeyEvent e){}
	
		
	public Dimension getPreferredSize() { return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT); }
}
