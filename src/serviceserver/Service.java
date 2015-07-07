/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceserver;

import java.io.Serializable;
import javax.swing.JPanel;

/**
 *
 * @author Jonathan
 */
public interface Service extends Serializable {
    public JPanel getGuiPanel();
}
