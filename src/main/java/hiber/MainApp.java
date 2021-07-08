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

        userService.add(new User("User1", "Lastname1", "user1@mail.ru",
                new Car("VAZ", 1)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru",
                new Car("Opel", 12)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru",
                new Car("Reno", 123)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru",
                new Car("Mercedes", 1234)));

        List<User> users = userService.listUsers();

        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("auto " + user.getCar());
            System.out.println();
        }

//        5. В сервис добавьте метод, который с помощью hql-запроса будет доставать
//        юзера, владеющего машиной по ее модели и серии.
      System.out.println(userService.getUser(new Car("VAZ", 1)));

        context.close();
    }
}
