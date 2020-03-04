package crossword;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.Polygon;

public class Crossword1 
{
	private final boolean[] square_checker = {true,false,true,false,true,false,true,true,true,true,true,true,false,true,true,
										true,false,false,false,false,false,true,false,false,false,false,false,false,true,false,
										false,false,true,false,false,false,true,false,false,false,false,false,true,false,true,
										true,false,true,false,false,true,true,false,true,false,true,false,false,false,false,
										false,false,false,false,true,false,false,false,false,true,true,false,false,false,false,
										true,true,true,true,true,false,false,false,false,false,true,false,true,false,false,
										true,false,false,false,false,false,true,false,false,false,false,true,false,false,false,
										true,false,false,false,true,false,true,false,false,false,false,true,false,false,false,
										true,false,false,true,false,false,false,false,false,false,true,false,false,false,false,
										true,false,false,false,false,false,true,false,false,false,false,true,false,false,false};
	
	private final char[] answers = {0,'A',0,'O',0,'A',0,0,0,0,0,0,'A',0,0,
									0,'D','E','B','I','L',0,'D','Z','I','W','K','A',0,'Q',
									'D','E',0,'R','N','A',0,'O','A','S','I','S',0,'S',0,
									0,'P',0,'U','K',0,0,'N',0,'O',0,'E','L','Z','A',
									'N','T','F','S',0,'P','A','K','T',0,0,'R','O','M','B',
									0,0,0,0,0,'O','B','I','A','\u0000',0,'O',0,'A','O',
									0,'D','¯','I','N','S',0,'S','P','O','T',0,'P','R','R',
									0,'¯','Y','D',0,'A',0,'Z','E','R','O',0,'A','A','C',
									0,'E','D',0,'I','D','I','O','T','A',0,'A','£','G','J',
									0,'M','A','£','P','A',0,'T','A','T','A',0,'A','D','A'};
	
	private final int[][] words = {{1,16,31,46,61}/*ADEPT*/,{3,18,33,48,63}/*OBRUS*/,{16,17,18,19,20}/*DEBIL*/,
								   {5,20,35}/*ALA*/,{33,34,35}/*RNA*/,{30,31}/*DE*/,{48,49}/*UK*/,{60,61,62,63}/*NTFS*/,
								   {91,106,121,136}/*D¯EM*/,{91,92,93,94,95}/*D¯INS*/,{106,107,108}/*¯YD*/,{121,122}/*ED*/,
								   {92,107,122,137}/*¯YDA*/,{93,108}/*ID*/,{136,137,138,139,140}/*MA£PA*/,{65,80,95,110,125,140}/*POSADA*/,
								   {124,125,126,127,128,129}/*IDIOTA*/,{65,66,67,68}/*PAKT*/,{80,81,82,83,84}/*OBIAD*/,
								   {97,98,99,100}/*SPOT*/,{112,113,114,115}/*ZERO*/,{142,143,144,145}/*TATA*/,
								   {22,37,52,67,82,97,112,127,142}/*DONKISZOT*/,{68,83,98,113,128,143}/*TAPETA*/,{23,38}/*ZA*/,
								   {24,39,54}/*ISO*/,{22,23,24,25,26,27}/*DZIWKA*/,{37,38,39,40,41}/*OASIS*/,{25,40}/*WI*/,
								   {26,41,56,71,86}/*KSERO*/,{84,99,114,129,144}/*BORAT*/,{100,115}/*TO*/,{12,27}/*AA*/,
								   {29}/*Q*/,{56,57,58,59}/*ELZA*/,{71,72,73,74}/*ROMB*/,{43,58,73,88,103,118,133,148}/*SZMARAGD*/,
								   {102,117,132,147}/*PA£A*/,{59,74,89,104,119,134,149}/*ABORCJA*/,{102,103,104}/*PRR*/,
								   {117,118,119}/*AAC*/,{131,132,133,134}/*A£GJ*/,{147,148,149}/*ADA*/,{57,72}/*LO*/,{66,81}/*AB*/};

	public boolean[] getsquare_checker()
	{
		return square_checker;
	}
	public int[] check_words(char[] letters,char[] errata) //metoda sprawdzaj¹ca czy zosta³y wprowadzone prawid³owe litery do krzy¿ówki 
	{
		int whether_empty=0;
		int correct = 0;
		int victory_rate=0;
		for(int i=0;i<45;i++)
		{
			for(int k=0;k<words[i].length;k++)
			{
				if(letters[words[i][k]]=='\u0000')//w warunku jest sprawdzane czy dana kratka jest pusta 
				{
					if((i!=18 || k!= 4) && (i!=30 || k!=0))
					{
						whether_empty++;
					}else if(i==18 && k==4)
					{
						if(errata[0]=='\u0000')
						{
							whether_empty++;
						}
					}else if(i==30 && k==0)
					{
						if(errata[1]=='\u0000')
						{
							whether_empty++;
						}
					}
					 
				}
			}
			for(int k=0;k<words[i].length;k++)
			{
				if(whether_empty==0)
				{
					if(letters[words[i][k]]==answers[words[i][k]]) // w warunku jest sprawdzane czy wprowadzona litera do kratki zgadza siê z prawid³owymi odpowiedziami 
					{
						if((i!=18 || k!= 4) && (i!=30 || k!=0))
						{
							correct++;
							victory_rate++;
						}else if(i==18 && k==4)
						{
							if(errata[0]=='D')
							{
								correct++;
								victory_rate++;
							}
						}else if(i==30 && k==0)
						{
							if(errata[1]=='B')
							{
								correct++;
								victory_rate++;
							}
						}
					}
					}
				}
			if(correct!=words[i].length && whether_empty==0)
			{
				return words[i];
			}
			whether_empty=0;
			correct=0;
		}
		if(victory_rate==179)
		{
			return new int[]{0};
		}
		victory_rate=0;
		return null;
	}
	public void draw_crossword(Graphics2D g2)
	{
		//wypisywanie pól z zapytaniami od 1 pola do 150 
		g2.setFont(new Font(null, Font.PLAIN, 11));
		g2.drawString("Trener", 15, 28);
		g2.drawString("podrywania", 15, 42);
		g2.drawString("w Polsce", 15, 56);
		
		g2.drawString("Rozk³adasz", 150, 28);
		g2.drawString("to na stole", 150, 42);
		
		g2.drawString("Atrament", 280, 28);
		g2.drawString("po", 280, 42);
		g2.drawString("angielsku", 280, 56);
		
		g2.drawString("Mia³a", 410, 28);
		g2.drawString("kota", 410, 42);
		
		g2.drawString("Walczy³ z", 475, 28);
		g2.drawString("wiatrakami", 475, 42);
		
		g2.drawString("Skrótowe", 540, 25);
		g2.drawString("okreœlenie", 540, 39);
		g2.drawString("dla pizzy", 540, 53);
		g2.drawString("po ang", 540, 65);
		
		g2.drawString("Format", 605, 25);
		g2.drawString("zapisu", 605, 39);
		g2.drawString("danych p³yt", 605, 53);
		g2.drawString("CD/DVD", 605, 65);
		
		g2.drawString("...-Fi, czyli", 670, 25);
		g2.drawString("bezprze-", 670, 39);
		g2.drawString("wodowa", 670, 53);
		g2.drawString("sieæ", 670, 65);
		
		g2.drawString("Maszyna", 735, 25);
		g2.drawString("s³u¿¹ca do", 735, 39);
		g2.drawString("ksero-", 735, 53);
		g2.drawString("wania", 735, 65);
		
		g2.drawString("Klub, do", 865, 25);
		g2.drawString("którego", 865, 39);
		g2.drawString("chodz¹", 865, 53);
		g2.drawString("alkoholicy", 865, 65);
		
		g2.drawString("Zaopatruje", 930, 25);
		g2.drawString("Jamesa", 930, 39);
		g2.drawString("Bonda w", 930, 53);
		g2.drawString("gad¿ety", 930, 65);
		
		g2.drawString("Kierowca", 15, 85);
		g2.drawString("skrêcaj¹cy", 15, 99);
		g2.drawString("na rondzie", 15, 113);
		g2.drawString("w lewo", 15, 127);
		
		g2.drawString("Kobieta", 410, 85);
		g2.drawString("lekkich", 410, 99);
		g2.drawString("obyczajów", 410, 113);
		
		g2.drawString("Kamieñ", 865, 85);
		g2.drawString("szlachetny", 865, 99);
		
		g2.drawString("Kwasy", 150, 142);
		g2.drawString("rybo-", 150, 156);
		g2.drawString("nukleinowe", 150, 170);
		
		g2.drawString("Mapa w", 410, 142);
		g2.drawString("Over-", 410, 156);
		g2.drawString("watchu", 410, 170);
		
		g2.drawString("Liceum", 800, 140);
		g2.drawString("ogólno-", 800, 154);
		g2.drawString("kszta³c¹ce", 800, 168);
		g2.drawString("w skrócie", 800, 182);
		
		g2.drawString("Zakazana", 930, 140);
		g2.drawString("w Polsce", 930, 154);
		g2.drawString("gdy pêknie", 930, 168);
		g2.drawString("guma", 930, 182);
		
		g2.drawString("Domena", 15, 199);
		g2.drawString("internetowa", 15, 213);
		g2.drawString("Niemców", 15, 227);
		
		g2.drawString("Wielka", 150, 199);
		g2.drawString("Brytania", 150, 213);
		g2.drawString("w skrócie", 150, 227);
		
		g2.drawString("Mo¿esz j¹", 345, 199);
		g2.drawString("straciæ", 345, 213);
		g2.drawString("w robocie", 345, 227);
		
		g2.drawString("Dwie", 410, 197);
		g2.drawString("pierwsze", 410, 211);
		g2.drawString("litery", 410, 225);
		g2.drawString("alfabetu", 410, 239);
		
		g2.drawString("Nakleja", 540, 199);
		g2.drawString("siê", 540, 213);
		g2.drawString("na œciany", 540, 227);
		
		g2.drawString("Ksiê¿niczka", 670, 197);
		g2.drawString("z bajki", 670, 211);
		g2.drawString("\"Kraina", 670, 225);
		g2.drawString("Lodu\"", 670, 239);
		
		g2.drawString("Z diab³em", 280, 256);
		g2.drawString("mo¿na", 280, 270);
		g2.drawString("podpisaæ", 280, 284);
		
		g2.drawString("Dziennikarz", 605, 256);
		g2.drawString("z Kazach-", 605, 270);
		g2.drawString("stanu", 605, 284);
		
		g2.drawString("Kopniêty", 670, 256);
		g2.drawString("kwadrat", 670, 270);
		
		g2.drawString("System", 15, 313);
		g2.drawString("plików w", 15, 327);
		g2.drawString("Windowsie", 15, 341);
		
		g2.drawString("Utwór", 85, 313);
		g2.drawString("Majkela", 85, 327);
		g2.drawString("D¿eksona", 85, 341);
		
		g2.drawString("Pali³ Hitler", 150, 313);
		g2.drawString("w piecu", 150, 327);
		
		g2.drawString("Dowód", 215, 311);
		g2.drawString("osobisty w", 215, 325);
		g2.drawString("dwóch", 215, 339);
		g2.drawString("literach", 215, 353);
		
		g2.drawString("Na co", 280, 311);
		g2.drawString("najczêœciej", 280, 325);
		g2.drawString("wo³a ciê", 280, 339);
		g2.drawString("matka", 280, 353);
		
		g2.drawString("Horror na", 670, 311);
		g2.drawString("podst.", 670, 325);
		g2.drawString("powieœci S.", 670, 339);
		g2.drawString("Kinga", 670, 353);
		
		g2.drawString("Za brak", 800, 313);
		g2.drawString("zadania w", 800, 327);
		g2.drawString("dzienniku", 800, 341);
		
		g2.drawString("Popularny", 15, 370);
		g2.drawString("materia³ na", 15, 384);
		g2.drawString("spodnie", 15, 398);
		
		g2.drawString("Reklamowy", 410, 370);
		g2.drawString("b¹dŸ", 410, 384);
		g2.drawString("wyborczy", 410, 398);
		
		g2.drawString("Komenda", 735, 370);
		g2.drawString("dla konia", 735, 384);
		
		g2.drawString("Ziomek,", 15, 427);
		g2.drawString("który nigdy", 15, 441);
		g2.drawString("nie ma kasy", 15, 455);
		
		g2.drawString("Adresy w", 280, 425);
		g2.drawString("sieci to", 280, 439);
		g2.drawString("inaczej", 280, 453);
		g2.drawString("adresy ...", 280, 467);
		
		g2.drawString("\"Mniej ni¿", 410, 427);
		g2.drawString("...\" tekst", 410, 441);
		g2.drawString("piosenki", 410, 455);
		
		g2.drawString("Format", 735, 425);
		g2.drawString("audio", 735, 439);
		g2.drawString("nastêpny", 735, 453);
		g2.drawString("po MP3", 735, 467);
		
		g2.drawString("... Sheeran,", 15, 484);
		g2.drawString("rudzielec z", 15, 498);
		g2.drawString("gitar¹", 15, 512);
		
		g2.drawString("Kierowca", 213, 482);
		g2.drawString("zawracaj¹cy", 212, 496);
		g2.drawString("na jedno-", 213, 510);
		g2.drawString("kierunkowej", 213, 524);
		
		g2.drawString("\"Stajnia", 670, 484);
		g2.drawString("...asza\" z", 670, 498);
		g2.drawString("mitologii", 670, 512);
		
		g2.drawString("Zwierzê", 15, 541);
		g2.drawString("bujaj¹ce siê", 15, 555);
		g2.drawString("na gumie", 15, 569);
		
		g2.drawString("Nazywasz", 410, 541);
		g2.drawString("tak swojego", 410, 555);
		g2.drawString("starego", 410, 569);
		
		g2.drawString("..., to nie", 735, 541);
		g2.drawString("wypada", 735, 555);
		
		g2.setStroke(new BasicStroke(1));
		g2.draw(new Line2D.Double(600,357,665,300)); //linia przecinaj¹ca jedn¹ kratkê na pó³ aby umo¿liwiæ wprowadzenie dwóch liter do hase³
		
		//rysowanie strza³ek do pytañ
		g2.draw(new Line2D.Double(78,15,93,15));
		g2.draw(new Line2D.Double(93,15,93,20));
		Polygon arrowhead1 = new Polygon(new int[]{90,96,93},new int[]{20,20,26},3);
		g2.drawPolygon(arrowhead1);
		g2.fillPolygon(arrowhead1);
		
		g2.draw(new Line2D.Double(78,99,83,99));
		Polygon arrowhead2 = new Polygon(new int[]{83,83,89},new int[]{96,102,99},3);
		g2.drawPolygon(arrowhead2);
		g2.fillPolygon(arrowhead2);
		
		g2.draw(new Line2D.Double(15,172,15,187));
		g2.draw(new Line2D.Double(15,172,20,172));
		Polygon arrowhead3 = new Polygon(new int[]{20,20,26},new int[]{169,175,172},3);
		g2.drawPolygon(arrowhead3);
		g2.fillPolygon(arrowhead3);
		
		g2.draw(new Line2D.Double(15,286,15,301));
		g2.draw(new Line2D.Double(15,286,20,286));
		Polygon arrowhead4 = new Polygon(new int[]{20,20,26},new int[]{283,289,286},3);
		g2.drawPolygon(arrowhead4);
		g2.fillPolygon(arrowhead4);
		
		g2.draw(new Line2D.Double(78,384,83,384));
		Polygon arrowhead5 = new Polygon(new int[]{83,83,89},new int[]{381,387,384},3);
		g2.drawPolygon(arrowhead5);
		g2.fillPolygon(arrowhead5);
		
		g2.draw(new Line2D.Double(78,441,83,441));
		Polygon arrowhead6 = new Polygon(new int[]{83,83,89},new int[]{438,444,441},3);
		g2.drawPolygon(arrowhead6);
		g2.fillPolygon(arrowhead6);
		
		g2.draw(new Line2D.Double(78,498,83,498));
		Polygon arrowhead7 = new Polygon(new int[]{83,83,89},new int[]{495,501,498},3);
		g2.drawPolygon(arrowhead7);
		g2.fillPolygon(arrowhead7);
		
		g2.draw(new Line2D.Double(78,555,83,555));
		Polygon arrowhead8 = new Polygon(new int[]{83,83,89},new int[]{552,558,555},3);
		g2.drawPolygon(arrowhead8);
		g2.fillPolygon(arrowhead8);
		
		g2.draw(new Line2D.Double(112,355,112,360));
		Polygon arrowhead9 = new Polygon(new int[]{109,115,112},new int[]{360,360,366},3);
		g2.drawPolygon(arrowhead9);
		g2.fillPolygon(arrowhead9);
		
		g2.draw(new Line2D.Double(208,15,223,15));
		g2.draw(new Line2D.Double(223,15,223,20));
		Polygon arrowhead10 = new Polygon(new int[]{220,226,223},new int[]{20,20,26},3);
		g2.drawPolygon(arrowhead10);
		g2.fillPolygon(arrowhead10);
		
		g2.draw(new Line2D.Double(208,156,213,156));
		Polygon arrowhead11 = new Polygon(new int[]{213,213,219},new int[]{153,159,156},3);
		g2.drawPolygon(arrowhead11);
		g2.fillPolygon(arrowhead11);
		
		g2.draw(new Line2D.Double(208,213,213,213));
		Polygon arrowhead12 = new Polygon(new int[]{213,213,219},new int[]{210,216,213},3);
		g2.drawPolygon(arrowhead12);
		g2.fillPolygon(arrowhead12);
		
		g2.draw(new Line2D.Double(177,355,177,360));
		Polygon arrowhead13 = new Polygon(new int[]{174,180,177},new int[]{360,360,366},3);
		g2.drawPolygon(arrowhead13);
		g2.fillPolygon(arrowhead13);
		
		g2.draw(new Line2D.Double(177,355,177,360));
		Polygon arrowhead14 = new Polygon(new int[]{174,180,177},new int[]{360,360,366},3);
		g2.drawPolygon(arrowhead14);
		g2.fillPolygon(arrowhead14);
		
		g2.draw(new Line2D.Double(242,355,242,360));
		Polygon arrowhead15 = new Polygon(new int[]{239,245,242},new int[]{360,360,366},3);
		g2.drawPolygon(arrowhead15);
		g2.fillPolygon(arrowhead15);
		
		g2.draw(new Line2D.Double(273,498,278,498));
		Polygon arrowhead16 = new Polygon(new int[]{278,278,284},new int[]{495,501,498},3);
		g2.drawPolygon(arrowhead16);
		g2.fillPolygon(arrowhead16);
		
		g2.draw(new Line2D.Double(307,70,307,75));
		Polygon arrowhead17 = new Polygon(new int[]{304,310,307},new int[]{75,75,81},3);
		g2.drawPolygon(arrowhead17);
		g2.fillPolygon(arrowhead17);
		
		g2.draw(new Line2D.Double(338,270,343,270));
		Polygon arrowhead18 = new Polygon(new int[]{343,343,349},new int[]{267,273,270},3);
		g2.drawPolygon(arrowhead18);
		g2.fillPolygon(arrowhead18);
		
		g2.draw(new Line2D.Double(338,327,343,327));
		Polygon arrowhead19 = new Polygon(new int[]{343,343,349},new int[]{324,330,327},3);
		g2.drawPolygon(arrowhead19);
		g2.fillPolygon(arrowhead19);
		
		g2.draw(new Line2D.Double(307,469,307,474));
		Polygon arrowhead20 = new Polygon(new int[]{304,310,307},new int[]{474,474,480},3);
		g2.drawPolygon(arrowhead20);
		g2.fillPolygon(arrowhead20);
		
		g2.draw(new Line2D.Double(372,241,372,246));
		Polygon arrowhead21 = new Polygon(new int[]{369,375,372},new int[]{246,246,252},3);
		g2.drawPolygon(arrowhead21);
		g2.fillPolygon(arrowhead21);
		
		g2.draw(new Line2D.Double(392,15,407,15));
		g2.draw(new Line2D.Double(392,15,392,20));
		Polygon arrowhead22 = new Polygon(new int[]{389,395,392},new int[]{20,20,26},3);
		g2.drawPolygon(arrowhead22);
		g2.fillPolygon(arrowhead22);
		
		g2.draw(new Line2D.Double(468,99,473,99));
		Polygon arrowhead23 = new Polygon(new int[]{473,473,479},new int[]{96,102,99},3);
		g2.drawPolygon(arrowhead23);
		g2.fillPolygon(arrowhead23);
		
		g2.draw(new Line2D.Double(468,156,473,156));
		Polygon arrowhead24 = new Polygon(new int[]{473,473,479},new int[]{153,159,156},3);
		g2.drawPolygon(arrowhead24);
		g2.fillPolygon(arrowhead24);
		
		g2.draw(new Line2D.Double(437,241,437,246));
		Polygon arrowhead25 = new Polygon(new int[]{434,440,437},new int[]{246,246,252},3);
		g2.drawPolygon(arrowhead25);
		g2.fillPolygon(arrowhead25);
		
		g2.draw(new Line2D.Double(468,384,473,384));
		Polygon arrowhead26 = new Polygon(new int[]{473,473,479},new int[]{381,387,384},3);
		g2.drawPolygon(arrowhead26);
		g2.fillPolygon(arrowhead26);
		
		g2.draw(new Line2D.Double(468,441,473,441));
		Polygon arrowhead27 = new Polygon(new int[]{473,473,479},new int[]{438,444,441},3);
		g2.drawPolygon(arrowhead27);
		g2.fillPolygon(arrowhead27);
		
		g2.draw(new Line2D.Double(468,555,473,555));
		Polygon arrowhead28 = new Polygon(new int[]{473,473,479},new int[]{552,558,555},3);
		g2.drawPolygon(arrowhead28);
		g2.fillPolygon(arrowhead28);
		
		g2.draw(new Line2D.Double(502,70,502,75));
		Polygon arrowhead29 = new Polygon(new int[]{499,505,502},new int[]{75,75,81},3);
		g2.drawPolygon(arrowhead29);
		g2.fillPolygon(arrowhead29);
		
		g2.draw(new Line2D.Double(567,70,567,75));
		Polygon arrowhead30 = new Polygon(new int[]{564,570,567},new int[]{75,75,81},3);
		g2.drawPolygon(arrowhead30);
		g2.fillPolygon(arrowhead30);
		
		g2.draw(new Line2D.Double(567,241,567,246));
		Polygon arrowhead31 = new Polygon(new int[]{564,570,567},new int[]{246,246,252},3);
		g2.drawPolygon(arrowhead31);
		g2.fillPolygon(arrowhead31);
		
		g2.draw(new Line2D.Double(632,70,632,75));
		Polygon arrowhead32 = new Polygon(new int[]{629,635,632},new int[]{75,75,81},3);
		g2.drawPolygon(arrowhead32);
		g2.fillPolygon(arrowhead32);
		
		g2.draw(new Line2D.Double(658,298,658,313));
		Polygon arrowhead33 = new Polygon(new int[]{655,661,658},new int[]{313,313,319},3);
		g2.drawPolygon(arrowhead33);
		g2.fillPolygon(arrowhead33);
		
		g2.draw(new Line2D.Double(697,70,697,75));
		Polygon arrowhead34 = new Polygon(new int[]{694,700,697},new int[]{75,75,81},3);
		g2.drawPolygon(arrowhead34);
		g2.fillPolygon(arrowhead34);
		
		g2.draw(new Line2D.Double(728,213,733,213));
		Polygon arrowhead35 = new Polygon(new int[]{733,733,739},new int[]{210,216,213},3);
		g2.drawPolygon(arrowhead35);
		g2.fillPolygon(arrowhead35);
		
		g2.draw(new Line2D.Double(728,270,733,270));
		Polygon arrowhead36 = new Polygon(new int[]{733,733,739},new int[]{267,273,270},3);
		g2.drawPolygon(arrowhead36);
		g2.fillPolygon(arrowhead36);
		
		g2.draw(new Line2D.Double(697,355,697,360));
		Polygon arrowhead37 = new Polygon(new int[]{694,700,697},new int[]{360,360,366},3);
		g2.drawPolygon(arrowhead37);
		g2.fillPolygon(arrowhead37);
		
		g2.draw(new Line2D.Double(728,498,733,498));
		Polygon arrowhead38 = new Polygon(new int[]{733,733,739},new int[]{495,501,498},3);
		g2.drawPolygon(arrowhead38);
		g2.fillPolygon(arrowhead38);
		
		g2.draw(new Line2D.Double(762,70,762,75));
		Polygon arrowhead39 = new Polygon(new int[]{759,765,762},new int[]{75,75,81},3);
		g2.drawPolygon(arrowhead39);
		g2.fillPolygon(arrowhead39);
		
		g2.draw(new Line2D.Double(793,384,798,384));
		Polygon arrowhead40 = new Polygon(new int[]{798,798,804},new int[]{381,387,384},3);
		g2.drawPolygon(arrowhead40);
		g2.fillPolygon(arrowhead40);
		
		g2.draw(new Line2D.Double(793,441,798,441));
		Polygon arrowhead41 = new Polygon(new int[]{798,798,804},new int[]{438,444,441},3);
		g2.drawPolygon(arrowhead41);
		g2.fillPolygon(arrowhead41);
		
		g2.draw(new Line2D.Double(793,555,798,555));
		Polygon arrowhead42 = new Polygon(new int[]{798,798,804},new int[]{552,558,555},3);
		g2.drawPolygon(arrowhead42);
		g2.fillPolygon(arrowhead42);
		
		g2.draw(new Line2D.Double(827,184,827,189));
		Polygon arrowhead43 = new Polygon(new int[]{824,830,827},new int[]{189,189,195},3);
		g2.drawPolygon(arrowhead43);
		g2.fillPolygon(arrowhead43);
		
		g2.draw(new Line2D.Double(827,355,827,360));
		Polygon arrowhead44 = new Polygon(new int[]{824,830,827},new int[]{360,360,366},3);
		g2.drawPolygon(arrowhead44);
		g2.fillPolygon(arrowhead44);
		
		g2.draw(new Line2D.Double(847,15,862,15));
		g2.draw(new Line2D.Double(847,15,847,20));
		Polygon arrowhead45 = new Polygon(new int[]{844,850,847},new int[]{20,20,26},3);
		g2.drawPolygon(arrowhead45);
		g2.fillPolygon(arrowhead45);
		
		g2.draw(new Line2D.Double(892,127,892,132));
		Polygon arrowhead46 = new Polygon(new int[]{889,895,892},new int[]{132,132,138},3);
		g2.drawPolygon(arrowhead46);
		g2.fillPolygon(arrowhead46);
		
		g2.draw(new Line2D.Double(957,70,957,75));
		Polygon arrowhead47 = new Polygon(new int[]{954,960,957},new int[]{75,75,81},3);
		g2.drawPolygon(arrowhead47);
		g2.fillPolygon(arrowhead47);
		
		g2.draw(new Line2D.Double(957,184,957,189));
		Polygon arrowhead48 = new Polygon(new int[]{954,960,957},new int[]{189,189,195},3);
		g2.drawPolygon(arrowhead48);
		g2.fillPolygon(arrowhead48);
	}
}
