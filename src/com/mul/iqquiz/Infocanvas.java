package com.mul.iqquiz;

import java.awt.*;
import java.lang.String.*;
import java.awt.Image.*;
// De Tijdlijn, commentaar en X-V indicator	

public class Infocanvas extends Canvas {
	Screen sc;
	Iqquiz frame;
	int answered =0;
        
                
		
	public Infocanvas(Screen sc,Iqquiz frame) {
		this.sc = sc;
		this.frame = frame;
                this.setBackground(new Color(102,102,0));
}
// Thread action
	
	
	public void paint (Graphics g) {
                g.setColor(Color.white);
		g.drawString("Question " + (frame.question),10,20);
		g.drawString("Questions left:",10,40);
		g.drawString(""+ + (14-frame.question),20,60);
		g.drawString("Answered:",10,80);
		answered = 0;
		for (int a=1;a<15;a++) {
			
			if (!frame.answer[a].equals("") && !frame.answer[a].equals("0")) 
				answered++;
		}
		g.drawString(""+answered,20,100);
		}
// resets timeline variables


	}