package com.estudos.restfullwebservices.model;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    public static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(2, "Eve", LocalDate.now().minusYears(25)));
        users.add(new User(3, "Eve", LocalDate.now().minusYears(25)));
        users.add(new User(4, "Jack", LocalDate.now().minusYears(20)));

    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(Integer id) {

        Predicate<? super User> predicate = user -> user
                .getId()
                .equals(id);
        return users.stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(users.size() + 1);
        }

        //Formatando a data para o padrão yyy-MMM-dd
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = user.getBirthDate().format(formatter);


        LocalDate date = LocalDate.parse(formattedDate, formatter);
        user.setBirthDate(date);

        users.add(user);
        return user;
    }

    public User update(User user) {
        User userFound = this.findOne(user.getId());
        if (userFound != null) {
            users.remove(userFound);
            users.add(user);
        }
        return userFound;
    }

    public void deleteById(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }
}
