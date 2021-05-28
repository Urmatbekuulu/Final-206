package views;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BarChart extends JPanel {
    private int histogramHeight = 480;
    private int barWidth = 50;
    private int barGap = 10;
    public ColorIcon c;

    public JPanel barPanel;
    public JPanel labelPanel;
    public List<Bar> bars = new ArrayList<Bar>();
    public BarChart(){

     execute();

    }
    public void execute(){
        setBorder( new EmptyBorder(10, 10, 10, 10) );
        setLayout( new BorderLayout() );

        barPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
        Border outer = new MatteBorder(1, 1, 1, 1, Color.BLACK);
        Border inner = new EmptyBorder(10, 10, 0, 10);
        Border compound = new CompoundBorder(outer, inner);
        barPanel.setBorder( compound );

        labelPanel = new JPanel( new GridLayout(1, 0, barGap, 0) );
        labelPanel.setBorder( new EmptyBorder(5, 10, 0, 10) );

        add(barPanel, BorderLayout.CENTER);
        add(labelPanel, BorderLayout.PAGE_END);
    }
    public void addHistogramColumn(String label, int value)
    {
        Bar bar = new Bar(label, value, Color.BLUE);
        bars.add( bar );
    }
    public void layoutHistogram()
    {
        barPanel.removeAll();
        labelPanel.removeAll();







        int maxValue = 10;


        for (Bar bar: bars)
        {
            System.out.println("I am working");

            JLabel label = new JLabel(bar.getValue() + "");
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.TOP);
            label.setVerticalAlignment(JLabel.BOTTOM);
            int barHeight = (bar.getValue() * histogramHeight) / maxValue;
            Icon icon = new ColorIcon(bar.getColor(), barWidth, barHeight);
            label.setIcon( icon );
            barPanel.add( label );

            JLabel barLabel = new JLabel( bar.getLabel() );
            barLabel.setHorizontalAlignment(JLabel.CENTER);
            labelPanel.add( barLabel );
        }






    }


}
class Bar
{
    private String label;
    private int value;
    private Color color;

    public Bar(String label, int value, Color color)
    {
        this.label = label;
        this.value = value;
        this.color = color;
    }

    public String getLabel()
    {
        return label;
    }

    public int getValue()
    {
        return value;
    }

    public Color getColor()
    {
        return color;
    }
}

class ColorIcon implements Icon
{
    private int shadow = 3;

    private Color color;
    private int width;
    private int height;

    public ColorIcon(Color color, int width, int height)
    {
        this.color = color;
        this.width = width;
        this.height = height;
    }

    public int getIconWidth()
    {
        return width;
    }

    public int getIconHeight()
    {
        return height;
    }

    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        g.setColor(color);
        g.fillRect(x, y, width - shadow, height);
        g.setColor(Color.GRAY);
        g.fillRect(x + width - shadow, y + shadow, shadow, height - shadow);
    }
}
