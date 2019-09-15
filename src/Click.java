import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Click {

    private static final Random random = new Random();

    private JFrame frame = new JFrame("Click");
    private static int width;
    private static int height;
    private static int objects = 0;

    private Click() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
//        frame.setUndecorated(true);
//        frame.pack();
        width = frame.getWidth();
        height = frame.getHeight();
        frame.getContentPane().setBackground(Color.getHSBColor(0.6f, 0.9f, 0.95f));

        initialise();
    }

    private void initialise() {
        System.out.println("NEW");

        System.out.println(frame.getComponents().length);
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
                if (!label.getText().equals("")) {
                    label.setText("");
                    objects--;
                }

                if (objects == 0) initialise();
//                frame.remove(label);
//                frame.revalidate();
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
        objects++;
        System.out.println(objects);
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
