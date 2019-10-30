import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Random;

public class Click {

    private static final Random random = new Random();

    private static Color[] COLORS = {Color.BLUE, Color.CYAN, Color.ORANGE, Color.RED, Color.YELLOW, Color.GREEN,
            Color.PINK, Color.MAGENTA, Color.GRAY, Color.WHITE};

    private static String[] AUDIO = {"Choco", "Ponga", "Ram Sam Sam", "Waka Waka"};

    private static String[] IMAGES = {"bus", "duster", "megane", "plane", "tractor", "train", "tram", "trolley",
            "tube", "yacht", "school", "alf1", "alf2", "bee", "bike", "bottle", "chess",
            "christmas tree", "cook", "corn", "crown", "dog husky", "dog2", "elephant", "fish", "fish1", "hen",
            "horse", "house", "jacket", "mcdonalds", "mcdonalds1", "ndk", "phone", "policeman", "rooster",
            "santa claus", "sea", "shoes", "shrimp", "smile", "snow", "snowflake", "snowman", "sydney",
            "train1", "t-shirt", "tv", "umbrella", "worker"};

    private JFrame frame = new JFrame("Click");
    private Clip clip;

    private static int width;
    private static int height;
    private static int imageWidth = 250;
    private static int imageHeight = 160;
    private static int objects = 0;
    private static int song = -1;

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

        for (int i = 0; i < 20; i++) {
            if (random.nextInt(20) == 0) {
                addCharacter(random.nextInt(4 * width / 5), random.nextInt(4 * height / 5),
                        random.nextInt(10));
            } else {
                addImage(random.nextInt(4 * width / 5), random.nextInt(4 * height / 5),
                        random.nextInt(IMAGES.length));
            }
        }

        SwingUtilities.updateComponentTreeUI(frame);
        frame.invalidate();
        frame.validate();
        frame.repaint();
        playMusic();
    }

    private void playMusic() {
        int r;
        do {
            r = new Random().nextInt(AUDIO.length);
        } while (r == song);

        song = r;
        final String fileName = AUDIO[song] + ".wav";
        try {
            final URL url = getClass().getResource(fileName);
            final AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);

            if (clip != null) clip.close();
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.setFramePosition(clip.getFrameLength() / 4);
            clip.start();

        } catch (final Exception e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
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

        } catch (final Exception e) {
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
