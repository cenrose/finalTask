import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class App {
    public static void main(String[] args) throws FileNotFoundException {
        App.CSVReader();
    }
    private static void CSVReader() throws FileNotFoundException {
            //открыли csv файл для чтения
            Scanner scanner = new Scanner(new File("src/main/resources/listOfCities.csv"));

            //создали список с городами
            List<City> cities = new ArrayList<>();

            //пока в сканнере есть строки, считываем далее
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                //разделяем строки на части, используя разделитель
                String[] cityData = line.split(";");

                //проверка длины массива (он должен быть не менее 6 элементов)
                if(cityData.length >= 6){
                    //парсим поля из строки
                    String name = cityData[1];
                    String region = cityData[2];
                    String district = cityData[3];
                    int population = Integer.parseInt(cityData[4]);
                    String foundation = cityData[5];

                    //создаем объект City и добавляем его в список
                    City city = new City(name, region, district, population, foundation);
                    cities.add(city);
                }
            }
            //выводим список объектов в формате JSON
            for(int i = 0; i < cities.size(); i++){
                System.out.println(cities.get(i).toString());
                if(i< cities.size() - 1){
                    System.out.println();
                }
            }
    }
}
