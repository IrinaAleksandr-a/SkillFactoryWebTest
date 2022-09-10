package lib;

public class DirectionsСonstant {

    public static String[] getContainer(String nameDirection) {
        return switch (nameDirection) {
            case "ВСЕ КУРСЫ" ->
                    new String[]{"//*[@id='rec458319732']", "//*[@id='rec464047906']", "//*[@id='rec468520559']", "//*[@id='rec468550113']", "//*[@id='rec468548184']"};
            case "Data Science" -> new String[]{"//*[@id='rec473884391']"};
            case "Аналитика данных", "Аналитика" -> new String[]{"//*[@id='rec474223664']"};
            case "Программирование" ->
                    new String[]{"//*[@id='rec456402581']", "//*[@id='rec456482983']", "//*[@id='rec456566093']"};
            case "Веб-разработка" -> new String[]{"//*[@id='rec456375093']"};
            case "Backend-разработка" -> new String[]{"//*[@id='rec455827771']"};
            case "Тестирование" -> new String[]{"//*[@id='rec447705966']"};
            case "Разработка приложений" -> new String[]{"//*[@id='rec456364345']"};
            case "Кибербезопасность", "Безопасность" -> new String[]{"//*[@id='rec456270707']"};
            case "Инфраструктура" -> new String[]{"//*[@id='rec456336730']"};
            case "Разработка игр" -> new String[]{"//*[@id='rec456357999']"};
            case "Маркетинг" -> new String[]{"//*[@id='rec473855418']"};
            case "Дизайн" -> new String[]{"//*[@id='rec483944933']"};
            case "Менеджмент" -> new String[]{"//*[@id='rec456304301']"};
            case "Создание сайтов" -> new String[]{"//*[@id='rec456663382']"};
            case "Высшее образование" -> new String[]{"//*[@id='rec456891768']"};
            default -> {
                System.out.println("Cannot find container direction " + nameDirection);
                yield new String[0];
            }
        };
    }

    public static String getHref(String nameDirection) {
        return switch (nameDirection) {
            case "ВСЕ КУРСЫ" -> "catalogue";
            case "Data Science" -> "data-science";
            case "Аналитика данных", "Аналитика" -> "analitika-dannyh";
            case "Программирование" -> "programmirovanie";
            case "Веб-разработка" -> "web-razrabotka";
            case "Backend-разработка" -> "backend-razrabotka";
            case "Тестирование" -> "testirovanie";
            case "Разработка приложений" -> "razrabotka-mobilnyh-prilozheniy";
            case "Кибербезопасность", "Безопасность" -> "informacionnaya-bezopasnost";
            case "Инфраструктура" -> "network-infrastructure";
            case "Разработка игр" -> "razrabotka-igr";
            case "Маркетинг" -> "marketing";
            case "Дизайн" -> "design";
            case "Менеджмент" -> "management-i-upravlenie";
            case "Создание сайтов" -> "sozdanie-saytov";
            case "Высшее образование" -> "vyssheye-obrazovaniye";
            default -> {
                System.out.println("Cannot find href direction " + nameDirection);
                yield "";
            }
        };
    }
}
