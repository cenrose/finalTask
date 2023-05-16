import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class App {
    static String fileName = "src/main/resources/listOfCities.csv";
    static List<City> cities = new ArrayList<>();
    public static void main(String[] args) throws FileNotFoundException{
        readCitiesFromFile();
        //CSVinJson();
        //SortCitiesByName();
        //SortCitiesByNameAndDistrict();
        theBiggestPopulation();
    }
        /*теперь вместо метода CSVReader() - readCitiesFromFile(),
        который только читает файл, но ничего не выводит*/
        private static void readCitiesFromFile() throws FileNotFoundException{
            Scanner scanner = new Scanner(new File(fileName));

            //пока в файле есть строки, считываем далее
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] cityData = line.split(";");

                //проверка длины массива (он должен быть не менее 6 элементов)
                if (cityData.length >= 6) {
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
        }
    /*сортировка списка городов по имени
    и вывод в формате JSON*/
        private static void SortCitiesByName(){
            Comparator<City> nameComparator = Comparator.comparing(City::getName);
            cities.sort(nameComparator);

            //выводим отсортированный список объектов в формате JSON
            for (int i = 0; i < cities.size(); i++){
                System.out.print(cities.get(i).toString());
                if (i < cities.size()){
                    System.out.println(",");
                }
            }
        }
    /*сортировка списка городов по имени,
    федеральному округу
    и вывод в формате JSON*/
    private static void SortCitiesByNameAndDistrict(){
        Comparator<City> nameComparator = Comparator.comparing(City::getName);
        Comparator<City> districtComparator = Comparator.comparing(City::getDistrict);

        //снала отсортировали по имени, затем по федеральному округу
        cities.sort(nameComparator);
        cities.sort(districtComparator);

        //выводим отсортированный список объектов в формате JSON
        for (int i = 0; i < cities.size(); i++){
            System.out.print(cities.get(i).toString());
            if (i < cities.size() - 1){
                System.out.println(",");
            }
        }
    }
    //вывод в формате jSON
    private static void CSVinJson() {
        for (int i = 0; i < cities.size(); i++) {
            System.out.println(cities.get(i).toString());
            if (i < cities.size() - 1) {
                System.out.println();
            }
        }
    }

    private static void theBiggestPopulation(){
        Comparator<City> populationComparator = Comparator.comparing(City::getPopulation);
        cities.sort(populationComparator.reversed());

        //выводим отсортированный список объектов в формате JSON
        for (int i = 0; i < cities.size(); i++){
            System.out.print(cities.get(i).toString());
            if (i < cities.size() - 1){
                System.out.println(",");
            }
        }
    }
}
