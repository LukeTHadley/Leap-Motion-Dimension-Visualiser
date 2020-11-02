import com.leapmotion.leap.Controller;

public class Main {

    private static Controller controller;
    private static VisualiserFrame vf;

    public static void main(String[] args) {
        controller = new Controller();
        vf = new VisualiserFrame(controller);
        vf.startWindow();

    }

}
