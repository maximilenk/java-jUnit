package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

public class UsersServiceImpl {
    public UsersRepository ur;
    public UsersServiceImpl(UsersRepository ur) {
        this.ur = ur;
    }
    public boolean authenticate(String login, String password) {
        User u = ur.findByLogin(login);
        if (u.getStatus()) {
            throw new AlreadyAuthenticatedException("already authenticated");
        } else {
            if (u.getPassword().equals(password)) {
                u.setStatus(true);
                ur.update(u);
                return true;
            } else {
                return false;
            }
        }
    }
    public static int retTwo() {
        return 2;
    }
}
