import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Click {

    private static final Random random = new Random();

    private static Color[] COLORS = {Color.BLUE, Color.CYAN, Color.ORANGE, Color.RED, Color.YELLOW, Color.GREEN,
            Color.PINK, Color.MAGENTA, Color.GRAY, Color.WHITE};

    private JFrame frame = new JFrame("Click");
    private static int width;
    private static int height;
    private static int imageWidth = 320;
    private static int imageHeight = 200;
    private static int objects = 0;

    private Click() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        width = frame.getWidth();
        height = frame.getHeight();

        ImageIcon image = new ImageIcon("images/tram.jpg");
        Image image2 = image.getImage();
        Image newimg = image2.getScaledInstance(imageWidth, imageHeight,  Image.SCALE_SMOOTH);
        image = new ImageIcon(newimg);

        System.out.println("tram coming!");
        JLabel label = new JLabel();
        label.setIcon(image);
        label.setSize(imageWidth, imageHeight);
        frame.getContentPane().add(label);

//        initialise();
    }

    private void initialise() {
        System.out.println("NEW");

        System.out.println(frame.getComponents().length);
        frame.getContentPane().setBackground(COLORS[random.nextInt(10)]);
        final int value = random.nextInt(10);

        for (int i = 0; i < 3; i++) {
            add(random.nextInt(4 * width / 5), random.nextInt(4 * height / 5), value);
        }

        SwingUtilities.updateComponentTreeUI(frame);
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    private void add(final int x, final int y, final int value) {
        System.out.println(x + " " + y + " " + value);
        JLabel label = new JLabel(String.valueOf(value), JLabel.CENTER);
        label.setFont(new Font("Times New Roman", Font.BOLD, 200));
        label.setSize(200, 200);
        label.setLocation(x, y);

        label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                frame.remove(label);
                frame.revalidate();
                frame.repaint();

                if (--objects == 0) initialise();
            }

            @Override
            public void mousePressed(final MouseEvent e) {
            }

            @Override
            public void mouseReleased(final MouseEvent e) {
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
            }

            @Override
            public void mouseExited(final MouseEvent e) {
            }
        });

        frame.add(label);
        System.out.println(++objects);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(Click::new);
    }
}
