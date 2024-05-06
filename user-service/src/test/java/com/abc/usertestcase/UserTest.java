package com.abc.usertestcase;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.abc.userservice.dao.UserDao;
import com.abc.userservice.entity.User;
import com.abc.userservice.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @Mock
    private UserDao userDaoMock;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testAddUser() {
        // Arrange
        User user = new User();
        user.setUserId(1);
        user.setUserName("testUser");
        user.setEmail("test@example.com");

        when(userDaoMock.save(any(User.class))).thenReturn(user);

        // Act
        User createdUser = userService.addUser(user);

        // Assert
        assertEquals(user.getUserId(), createdUser.getUserId());
        assertEquals(user.getUserName(), createdUser.getUserName());
        assertEquals(user.getEmail(), createdUser.getEmail());

        verify(userDaoMock, times(1)).save(any(User.class));
    }
    @Test
    public void testUpdateUser() {
        // Arrange
        User userToUpdate = new User();
        userToUpdate.setUserId(1);
        userToUpdate.setUserName("testUser");
        userToUpdate.setEmail("test@example.com");

        when(userDaoMock.existsById(userToUpdate.getUserId())).thenReturn(true);
        when(userDaoMock.save(userToUpdate)).thenReturn(userToUpdate);

        // Act
        User updatedUser = userService.updateUser(userToUpdate);

        // Assert
        assertEquals(userToUpdate.getUserId(), updatedUser.getUserId());
        assertEquals(userToUpdate.getUserName(), updatedUser.getUserName());
        assertEquals(userToUpdate.getEmail(), updatedUser.getEmail());

        verify(userDaoMock, times(1)).existsById(userToUpdate.getUserId());
        verify(userDaoMock, times(1)).save(userToUpdate);
    }

    @Test
    public void testRemoveUser() {
        // Arrange
        User userToRemove = new User();
        userToRemove.setUserId(1);
        userToRemove.setUserName("testUser");
        userToRemove.setEmail("test@example.com");

        when(userDaoMock.findById(userToRemove.getUserId())).thenReturn(Optional.of(userToRemove));

        // Act
        userService.removeUser(userToRemove);

        // Assert
        verify(userDaoMock, times(1)).findById(userToRemove.getUserId());
        verify(userDaoMock, times(1)).delete(userToRemove);
    }
    @Test
    public void testGetAllUsers() {
        // Arrange
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setUserId(1);
        user1.setUserName("user1");
        user1.setEmail("user1@example.com");
        userList.add(user1);

        User user2 = new User();
        user2.setUserId(2);
        user2.setUserName("user2");
        user2.setEmail("user2@example.com");
        userList.add(user2);

        when(userDaoMock.findAll()).thenReturn(userList);

        // Act
        List<User> fetchedUsers = userService.getAllUsers();

        // Assert
        assertEquals(userList.size(), fetchedUsers.size());
        for (int i = 0; i < userList.size(); i++) {
            assertEquals(userList.get(i).getUserId(), fetchedUsers.get(i).getUserId());
            assertEquals(userList.get(i).getUserName(), fetchedUsers.get(i).getUserName());
            assertEquals(userList.get(i).getEmail(), fetchedUsers.get(i).getEmail());
        }

        verify(userDaoMock, times(1)).findAll();
    }
   
    }



