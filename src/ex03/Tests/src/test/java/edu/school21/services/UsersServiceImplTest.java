package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UsersServiceImplTest {
    UsersRepository ur = Mockito.mock(UsersRepository.class);
    UsersServiceImpl usi = new UsersServiceImpl(ur);

    @Test
    void correctLoginAndPasswordTest() {
        Mockito.doReturn(new User(1L,
                "name", "password",
                false)).when(ur).findByLogin("name");
        Assertions.assertTrue(usi.authenticate("name", "password"));
        Mockito.verify(ur).findByLogin("name");
    }

    @Test
    void incorrectLoginTest() {
        Mockito.doThrow(EntityNotFoundException.class).when(ur).findByLogin("Incorrect");
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> usi.authenticate("Incorrect", "password"));
        Mockito.verify(ur).findByLogin("Incorrect");
    }

    @Test
    void incorrectPasswordTest() {
        Mockito.doReturn(new User(1L,
                        "name", "password",
                        false))
                .when(ur)
                .findByLogin("name");
        Assertions.assertFalse(usi.authenticate("name", "Incorrect"));
        Mockito.verify(ur).findByLogin("name");
    }

    @Test
    void alreadyAuthTest() {
        User u = new User(1L,
                "name", "password",
                false);
        Mockito.doReturn(u)
                .when(ur)
                .findByLogin("name");
        Assertions.assertTrue(usi.authenticate("name", "password"));
        Assertions.assertThrows(AlreadyAuthenticatedException.class,
                () -> usi.authenticate("name", "password"));
    }
}
