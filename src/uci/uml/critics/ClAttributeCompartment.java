// Copyright (c) 1996-98 The Regents of the University of California. All
// Rights Reserved. Permission to use, copy, modify, and distribute this
// software and its documentation for educational, research and non-profit
// purposes, without fee, and without a written agreement is hereby granted,
// provided that the above copyright notice and this paragraph appear in all
// copies. Permission to incorporate this software into commercial products may
// be obtained by contacting the University of California. David F. Redmiles
// Department of Information and Computer Science (ICS) University of
// California Irvine, California 92697-3425 Phone: 714-824-3823. This software
// program and documentation are copyrighted by The Regents of the University
// of California. The software program and documentation are supplied "as is",
// without any accompanying services from The Regents. The Regents do not
// warrant that the operation of the program will be uninterrupted or
// error-free. The end-user understands that the program was developed for
// research purposes and is advised not to rely exclusively on the program for
// any reason. IN NO EVENT SHALL THE UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY
// PARTY FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES,
// INCLUDING LOST PROFITS, ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS
// DOCUMENTATION, EVEN IF THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE
// POSSIBILITY OF SUCH DAMAGE. THE UNIVERSITY OF CALIFORNIA SPECIFICALLY
// DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
// WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE
// SOFTWARE PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
// CALIFORNIA HAS NO OBLIGATIONS TO PROVIDE MAINTENANCE, SUPPORT, UPDATES,
// ENHANCEMENTS, OR MODIFICATIONS.


package uci.uml.critics;

import java.awt.*;
import com.sun.java.swing.*;

import uci.argo.kernel.*;
import uci.gef.*;
import uci.uml.visual.*;

public class ClAttributeCompartment implements Clarifier {
  public static ClAttributeCompartment TheInstance = new ClAttributeCompartment();
  public static int WAVE_LENGTH = 4;
  public static int WAVE_HEIGHT = 2;

  ////////////////////////////////////////////////////////////////
  // instance variables
  Fig _fig;

  public void setFig(Fig f) { _fig = f; }
  public void setToDoItem(ToDoItem i) { }

  public void paintIcon(Component c, Graphics g, int x, int y) {
    if (_fig instanceof FigClass) {
      FigClass fc = (FigClass) _fig;
      FigText ft = fc.getAttributeFig();
      int left  = ft.getX() + 10;
      int height = ft.getY() + ft.getHeight() - 7;
      int right = ft.getX() + ft.getWidth() - 10;
      g.setColor(Color.red);
      int i = left;
      while (true) {
	g.drawLine(i, height, i + WAVE_LENGTH, height + WAVE_HEIGHT);
	i += WAVE_LENGTH;
	if (i >= right) break;
	g.drawLine(i, height + WAVE_HEIGHT, i + WAVE_LENGTH, height);
	i += WAVE_LENGTH;
	if (i >= right) break;
	g.drawLine(i, height, i + WAVE_LENGTH, height + WAVE_HEIGHT/2);
	i += WAVE_LENGTH;
	if (i >= right) break;
	g.drawLine(i, height + WAVE_HEIGHT/2, i + WAVE_LENGTH, height);
	i += WAVE_LENGTH;
	if (i >= right) break;
      }
      _fig = null;
    }
  }

  public int getIconWidth() { return 0; }
  public int getIconHeight() { return 0; }

  public boolean hit(int x, int y) {
    if (!(_fig instanceof FigClass)) {
      System.out.println("not a FigClass");
      return false;
    }
    FigClass fc = (FigClass) _fig;
    FigText ft = fc.getAttributeFig();
    boolean res = ft.contains(x, y);
    _fig = null;
    return res;
  }

} /* end class ClAttributeCompartment */


