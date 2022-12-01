import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GenerationDataInGUI {

    private static final float Translation_hPa_mmHg = 0.750063755419211f;
    private String keyApi = "4a7c7d606e31b1889014d8e40a9662a8";
    private String nameCity;
    private float pressure;
    private String line = null;
    private String actualTemp = " ";
    private String feltTemp = " ";
    private String valuePressure = " ";
    private String wind = " ";
    private String directionWindStr = " ";
    private String messageError = " ";
    private double directionWindInt;

    public String getActualTemp() {
        return actualTemp;
    }

    public String getFeltTemp() {
        return feltTemp;
    }

    public String getValuePressure() {
        return valuePressure;
    }

    public String getWind() {
        return wind;
    }

    public String getMessageError() {
        return messageError;
    }

    public void importData() {

        nameCity = GUI.valueNameCity;

        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + nameCity + "&units=metric&appid=" + keyApi + "&lang=ru");
            URLConnection request = url.openConnection();

            InputStreamReader stream = new InputStreamReader(request.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(stream);

            line = bufferedReader.readLine();

            JSONObject jObj = new JSONObject(line); // вызывает данные с сервера

            actualTemp = (String.valueOf((int) Math.round(jObj.getJSONObject("main").getDouble("temp")))); // парсируем через JSON данные с сервера и отправляем в GUI
            feltTemp = (String.valueOf((int) Math.round(jObj.getJSONObject("main").getDouble("feels_like"))));// получение данных на ощущение температуры и отправляем в GUi

            pressure = (float) jObj.getJSONObject("main").getDouble("pressure"); // получение данных на Давления и отправляем в GUi
            pressure = Math.round(pressure * Translation_hPa_mmHg);
            valuePressure = (String.valueOf(Math.round(pressure)));

            messageError = " ";

            wind = (String.valueOf((int) Math.round(jObj.getJSONObject("wind").getDouble("speed")))); // получение данных по Скорости ветра и отправляем в GUi

            // Перевод из градусов в название направления
            directionWindInt = jObj.getJSONObject("wind").getDouble("deg");
            if (directionWindInt > 349 || directionWindInt < 11) directionWindStr = "C";
            if (directionWindInt > 11 && directionWindInt < 34) directionWindStr = "CCB";
            if (directionWindInt > 34 && directionWindInt < 56) directionWindStr = "CB";
            if (directionWindInt > 56 && directionWindInt < 79) directionWindStr = "ВCB";
            if (directionWindInt > 79 && directionWindInt < 101) directionWindStr = "B";
            if (directionWindInt > 101 && directionWindInt < 124) directionWindStr = "ВЮB";
            if (directionWindInt > 124 && directionWindInt < 146) directionWindStr = "ЮB";
            if (directionWindInt > 146 && directionWindInt < 169) directionWindStr = "ЮЮB";
            if (directionWindInt > 169 && directionWindInt < 191) directionWindStr = "Ю";
            if (directionWindInt > 191 && directionWindInt < 214) directionWindStr = "ЮЮЗ";
            if (directionWindInt > 214 && directionWindInt < 236) directionWindStr = "ЮЗ";
            if (directionWindInt > 236 && directionWindInt < 259) directionWindStr = "ЗЮЗ";
            if (directionWindInt > 259 && directionWindInt < 282) directionWindStr = "З";
            if (directionWindInt > 282 && directionWindInt < 304) directionWindStr = "ЗСЗ";
            if (directionWindInt > 304 && directionWindInt < 326) directionWindStr = "СЗ";
            if (directionWindInt > 326 && directionWindInt < 349) directionWindStr = "ССЗ";

            wind = wind + ", " + directionWindStr;

            // Вывод данных в консоль
            System.out.println(line);
        } catch (IOException e) {
            actualTemp = " ";
            feltTemp = " ";
            valuePressure = " ";
            wind = " ";
            directionWindStr = " ";

            if (nameCity.isEmpty()) {
                messageError = "Введите название города";
            } else {
                messageError = "Название введено не корректно";
            }
        }
    }
}
