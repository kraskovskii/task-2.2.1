package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);


        carService.add(new Car("VAZ", 1));
        carService.add(new Car("Opel", 12));
        carService.add(new Car("Reno", 123));
        carService.add(new Car("Mercedes", 1234));
        List<Car> cars = carService.listCars();


        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        user1.setCar(cars.get(0));
        user2.setCar(cars.get(1));
        user3.setCar(cars.get(2));
        user4.setCar(cars.get(3));

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

        System.out.println(userService.getUsers(cars.get(1)));
        context.close();
    }
}
