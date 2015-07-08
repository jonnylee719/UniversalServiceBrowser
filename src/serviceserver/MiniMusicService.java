/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceserver;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Jonathan
 */
class MiniMusicService implements Service{

    MyDrawPanel myPanel;
    Font bigFont = new Font("Comic Sans", Font.BOLD, 30);

    
    public JPanel getGuiPanel(){
        JPanel mainPanel = new JPanel();
        myPanel = new MyDrawPanel();
        JButton playItButton = new JButton("Play It");
        playItButton.setFont(bigFont);
        playItButton.addActionListener(new PlayItListener());
        mainPanel.add(myPanel);
        mainPanel.add(playItButton);
        return mainPanel;
    }
    
    public class PlayItListener implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            try{
                Sequencer sequencer = MidiSystem.getSequencer();
                sequencer.open();
                
                sequencer.addControllerEventListener(myPanel, new int[] {127});
                Sequence seq = new Sequence(Sequence.PPQ, 4);
                Track track = seq.createTrack();
                
                for(int i = 0; i<100; i+=4){
                    int rNum = (int)((Math.random()*50) + 1);
                    if (rNum< 38){
                        track.add(makeEvent(144, 1, rNum, 100, i));
                        track.add(makeEvent(176, 1, 127, 0, i));
                        track.add(makeEvent(128, 1, rNum, 100, i+2));
                    }
                }
                
                sequencer.setSequence(seq);
                sequencer.start();
                sequencer.setTempoInBPM(220);
                
                
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick){
        MidiEvent event = null;
        try{
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            event = new MidiEvent(a, tick);
        }
        catch(Exception ex){}
        return event;
    }
    
    class MyDrawPanel extends JPanel implements ControllerEventListener{
        
        boolean  msg = false;
        
        public void controlChange(ShortMessage event){
            msg = true;
            repaint();
        }
        
        public Dimension getPreferredSize(){
            return new Dimension(500,500);
        }
        
        public void paintComponent(Graphics g){
            if(msg){
                
                int r = (int) (Math.random()*256);
                int gr = (int) (Math.random()* 256);
                int b = (int) (Math.random()*256);
                
                g.setColor(new Color(r,gr,b));
                
                int ht = (int)((Math.random()*300)+10);
                int width = (int)((Math.random()*300)+10);
                
                int x = (int)((Math.random()*70)+10);
                int y = (int)((Math.random()*70)+10);
                
                g.fillRect(x, y, ht, width);
                msg = false;
            }
        }
    }
}
