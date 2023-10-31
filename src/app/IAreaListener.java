package app;

import java.awt.Point;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

public interface IAreaListener extends MouseListener, KeyListener, MouseMotionListener, MouseWheelListener {
    boolean contains(Point p);
}
