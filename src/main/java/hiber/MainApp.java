package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        user1.setCar(new Car("VAZ", 1));
        user2.setCar(new Car("Mercedes", 12));
        user3.setCar(new Car("Reno", 123));
        user4.setCar(new Car("Opel", 12));

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

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
