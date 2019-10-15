import javax.imageio.ImageIO;
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
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Click {

    private static final Random random = new Random();

    private static Color[] COLORS = {Color.BLUE, Color.CYAN, Color.ORANGE, Color.RED, Color.YELLOW, Color.GREEN,
            Color.PINK, Color.MAGENTA, Color.GRAY, Color.WHITE};

    private static String[] IMAGES = {"bus", "duster", "megan", "plane", "tractor", "train", "tram", "trolley",
            "tube", "yacht"};

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

        initialise();
    }

    private void initialise() {
        System.out.println("NEW");

        System.out.println(frame.getComponents().length);
        frame.getContentPane().setBackground(COLORS[random.nextInt(10)]);
        final int value = random.nextInt(10);

        if (random.nextInt(4) == 0) {
            for (int i = 0; i < 5; i++) {
                addCharacter(random.nextInt(4 * width / 5), random.nextInt(4 * height / 5), value);
            }
        } else {
            for (int i = 0; i < 5; i++) {
                addImage(random.nextInt(4 * width / 5), random.nextInt(4 * height / 5), value);
            }
        }

        SwingUtilities.updateComponentTreeUI(frame);
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    private void addCharacter(final int x, final int y, final int value) {
        System.out.println(x + " " + y + " " + value);
        JLabel label = new JLabel(String.valueOf(value), JLabel.CENTER);
        label.setFont(new Font("Times New Roman", Font.BOLD, 200));
        label.setSize(200, 200);
        label.setLocation(x, y);

        addListener(label);
    }

    private void addImage(final int x, final int y, final int value) {
        System.out.println(x + " " + y + " " + value);

        try {
            BufferedImage image = ImageIO.read(getClass().getResource(IMAGES[value] + ".jpg"));
            Image scaledInstance = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledInstance);

            JLabel label = new JLabel();
            label.setIcon(icon);
            label.setSize(imageWidth, imageHeight);
            frame.getContentPane().add(label);
            label.setLocation(x, y);
            addListener(label);

        } catch (IOException e) {
            System.out.println("Could not load image " + IMAGES[value]);
        }
    }

    private void addListener(final JLabel label) {
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
