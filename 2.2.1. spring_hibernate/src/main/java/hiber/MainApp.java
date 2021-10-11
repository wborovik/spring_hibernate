package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User vladimir = new User("Vladimir", "Borovik", "borovik@mail.ru");
        Car bmw = new Car("BMW", 8);
        User sergey = new User("Sergey", "Malinin", "malinin@yandex.ru");
        Car lada = new Car("Lada", 2107);
        User andrey = new User("Andrey", "Kulikov", "kulikov@gmail.ru");
        Car mazda = new Car("Mazda", 5);
        User artem = new User("Artem", "Bugaevsky", "bugaevsky@mail.ru");
        Car moskvich = new Car("Moskvich", 412);

        userService.add(vladimir.setCar(bmw).setUser(vladimir));
        userService.add(sergey.setCar(lada).setUser(sergey));
        userService.add(andrey.setCar(mazda).setUser(andrey));
        userService.add(artem.setCar(moskvich).setUser(artem));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println(user.getCar());
            System.out.println();
        }

        User user = userService.getUserByCar("Moskvich", 412);
        System.out.println();
        System.out.println(user.getCar() + " - принадлежит User: " + user.getFirstName() + " " + user.getLastName());
        System.out.println();

        User user1 = userService.getUserByCar("kk", 21);
    }
}
