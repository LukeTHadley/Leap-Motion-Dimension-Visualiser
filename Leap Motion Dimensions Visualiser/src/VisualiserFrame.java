import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Listener;
import javax.swing.*;
import java.awt.*;

/**
 * VisualiserFrame takes an instance of a Leap Motion Controller, creates a
 * frame and displays the X/Y/Z values the Controller has on a graph.
 */
public class VisualiserFrame {

    private Controller controller;
    private ControllerListener listener;
    private Frame frame;

    public VisualiserFrame(Controller leapMotionController){
        controller = leapMotionController;
        frame = new Frame();
    }

    /**
     * Starts a VisualiserFrame window.
     * Starts the listener for the device, used for window content refreshing.
     */
    public void startWindow(){
        listener = new ControllerListener();
        controller.addListener(listener);
        frame.createFrame();
    }

    /**
     * Closes a VisualiserFrame window.
     * Removes controller listener to release device.
     */
    public void closeWindow(){
        controller.removeListener(listener);
        frame.dispose();
    }

    /**
     * Private class that creates and manages the JFrame Object
     */
    private class Frame extends JFrame {

        private final int FRAME_SIZE = 600;
        private JLabel XValue, YValue, ZValue;
        private Font dataFont;

        /**
         * Contstructor for Visualiser Frame
         */
        public Frame() {

        }

        /**
         * Function that creates and sets out the JFrame
         */
        private void createFrame() {
            setTitle("Leap Motion Position Statistics");
            setSize(750, 626);
            setResizable(false);
            dataFont = new Font("Arial", Font.PLAIN, 30);
            XValue = new JLabel("X");
            XValue.setBounds(10, 10, 100, 30);
            XValue.setFont(dataFont);
            YValue = new JLabel("Y");
            YValue.setBounds(10, 40, 100, 30);
            YValue.setFont(dataFont);
            ZValue = new JLabel("Z");
            ZValue.setBounds(10, 70, 100, 30);
            ZValue.setFont(dataFont);
            add(XValue);
            add(YValue);
            add(ZValue);
            getContentPane().add(new Canvas());
            setVisible(true);
        }

        /**
         * JComponent section of the JFrame responsible for drawing on the frame.
         */
        private class Canvas extends JComponent {

            public void paint(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;

                //Set X/Z Graph Lines
                g2.setStroke(new BasicStroke(6));
                g.setColor(Color.BLUE);
                g.drawLine(FRAME_SIZE / 2, 10, FRAME_SIZE / 2, 600 - 10); //VERTICAL STRUCT
                g.drawLine(FRAME_SIZE / 2 - 10, 10, FRAME_SIZE / 2 + 10, 10); //TOP CAP
                g.drawLine(FRAME_SIZE / 2 - 10, 600 - 10, FRAME_SIZE / 2 + 10, 600 - 10); //BOTTOM CAP
                g.drawLine(10, FRAME_SIZE / 2, 600 - 10, FRAME_SIZE / 2); //HORIZONTAL STRUT
                g.drawLine(10, FRAME_SIZE / 2 - 10, 10, FRAME_SIZE / 2 + 10); //LEFT CAP
                g.drawLine(600 - 10, FRAME_SIZE / 2 - 10, 600 - 10, FRAME_SIZE / 2 + 10); //RIGHT CAP

                //X/Z Setup and Positioning
                g2.setStroke(new BasicStroke(8));
                g.setColor(Color.RED);
                int currentHandID = controller.frame().hands().leftmost().id();
                int x = Math.round(controller.frame().hand(currentHandID).palmPosition().getX());
                int y = Math.round(controller.frame().hand(currentHandID).palmPosition().getY());
                int z = Math.round(controller.frame().hand(currentHandID).palmPosition().getZ());
                g.drawOval((x - 4) + (FRAME_SIZE / 2), (z - 4) + (FRAME_SIZE / 2), 8, 8);

                //Set Y Graph Lines
                g2.setStroke(new BasicStroke(6));
                g.setColor(Color.BLUE);
                g.drawLine(675, 10, 675, 600 - 10); //VERTICAL STRUCT
                g.drawLine(675 - 10, 10, 675 + 10, 10); //TOP CAP
                g.drawLine(675 - 10, 600 - 10, 675 + 10, 600 - 10); //BOTTOM CAP

                //Y Dot Setup and Positioning
                g2.setStroke(new BasicStroke(8));
                g.setColor(Color.RED);
                g.drawOval(675 - 4, (600 - 14) - (y), 8, 8);

                //Reset Text Values
                XValue.setText("X: " + x);
                YValue.setText("Y: " + y);
                ZValue.setText("Z: " + (z * -1));
            }
        }
    }

    /**
     * Responsible for refreshing the values on the screen every 'frame' of the
     * Leap Motion controller.
     */
    private class ControllerListener extends Listener {

        public void onFrame(Controller controller) {
            frame.repaint();
        }
    }

}



