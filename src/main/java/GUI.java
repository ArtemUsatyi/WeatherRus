import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;

public class GUI implements ActionListener {
    private JTextField textFieldCity;                 // Поле ввода города
    private JLabel labelActualTemp = new JLabel();    // Фактическая температура
    private JLabel labelFeltTemp = new JLabel();      // Ощущается температура
    private JLabel labelWind = new JLabel();          // Скорость ветра и напрвление
    private JLabel labelPressure = new JLabel();      // Атмосфорное давление
    private JLabel labelMessageError = new JLabel();  // Ошибка сообщения о неправильном вводе названия города
    private String valueMessageError = " ";      // Название ошибки
    private String valueActualTemp = " ";        // Актуальное значение температуры
    private String valueFeltTemp = " ";          // Значение температуры по ощущению
    private String valueWind = " ";              // Значение скорости ветра
    private String valuePressure = " ";          // Значение атмосфреного давления
    public static String valueNameCity;
    GenerationDataInGUI generationData = new GenerationDataInGUI();

    public void visionPanel() {

        JFrame frame = new JFrame();
        frame.setTitle("Погодник");                           // название приложения
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // приложение можно закрыть через кнопку
        frame.setSize(260, 460);                  // размер окна
        frame.setResizable(false);                            // делаем окно не изменяем по высоте/ширине
        frame.setLocationRelativeTo(null);                    // установить окно по середине экрана
        frame.setLayout(new GridBagLayout());                 // наименование использованного компонента

        JLabel labelText = new JLabel("Погодник");        // Название приложения: Погодник
        JLabel labelTextInfo = new JLabel("Информация");  // Текст: информация

        labelText.setFont(new Font("American Captain Cyrillic", Font.PLAIN, 30));
        labelText.setForeground(Color.white);
        Font font = labelText.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        labelText.setFont(font.deriveFont(attributes));

        labelTextInfo.setFont(new Font("Impact", Font.PLAIN, 24));
        labelTextInfo.setForeground(Color.pink);

        // Текстовое поле ввода, устанавливаем: Размер символов ввода, Шрифт, Размер,
        textFieldCity = new JTextField(14);
        textFieldCity.setFont(new Font("American Captain Cyrillic", Font.PLAIN, 25));

        // Подгружаем остальные JLabel
        panelLabel();

        // Кнопка-узнать погоду
        JButton buttonClickFindWeather = new JButton("УЗНАТЬ ПОГОДУ");
        buttonClickFindWeather.setFont(new Font("Impact", Font.PLAIN, 19));
        buttonClickFindWeather.setBackground(Color.lightGray);
        buttonClickFindWeather.setFocusable(false);
        buttonClickFindWeather.addActionListener(this);

        frame.add(labelText, new GridBagConstraints(0, 0, 2, 1, 0, 1,
                GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(10, 5, 5, 5), 0, 0));
        frame.add(textFieldCity, new GridBagConstraints(0, 1, 2, 1, 0, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));
        frame.add(labelMessageError, new GridBagConstraints(0, 2, 2, 1, 0, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 1, 0));

        frame.add(buttonClickFindWeather, new GridBagConstraints(0, 3, 2, 1, 0, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 3));

        frame.add(labelTextInfo, new GridBagConstraints(0, 4, 2, 1, 0, 1,
                GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(5, 5, 5, 5), 0, 0));
        frame.add(labelActualTemp, new GridBagConstraints(0, 5, 1, 1, 0, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        frame.add(labelFeltTemp, new GridBagConstraints(0, 6, 1, 1, 0, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        frame.add(labelPressure, new GridBagConstraints(0, 7, 1, 1, 0, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
        frame.add(labelWind, new GridBagConstraints(0, 8, 1, 1, 0, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

        frame.getContentPane().setBackground(Color.darkGray);   // фон сделать Серым
        frame.setVisible(true);                                 // сделать окно видимым
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        valueNameCity = textFieldCity.getText();

        generationData.importData();
        valueActualTemp = generationData.getActualTemp();
        valueFeltTemp = generationData.getFeltTemp();
        valueWind = generationData.getWind();
        valuePressure = generationData.getValuePressure();
        valueMessageError = generationData.getMessageError();

        panelLabel();
    }

    public void panelLabel() {
        labelActualTemp.setText("Температура  °C :   " + valueActualTemp);
        labelFeltTemp.setText("Ощущается  °C :  " + valueFeltTemp);
        labelPressure.setText("Давление мм рт.ст. :  " + valuePressure);
        labelWind.setText("Скорость ветра м/с :  " + valueWind);
        labelMessageError.setText(valueMessageError);

        labelActualTemp.setFont(new Font("Impact", Font.PLAIN, 16));
        labelActualTemp.setForeground(Color.white);
        labelFeltTemp.setFont(new Font("Impact", Font.PLAIN, 16));
        labelFeltTemp.setForeground(Color.white);
        labelWind.setFont(new Font("Impact", Font.PLAIN, 16));
        labelWind.setForeground(Color.white);
        labelPressure.setFont(new Font("Impact", Font.PLAIN, 16));
        labelPressure.setForeground(Color.white);
        labelMessageError.setFont(new Font("Impact", Font.PLAIN, 12));
        labelMessageError.setForeground(Color.pink);
    }
}
