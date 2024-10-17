package com.wipro.hma.us.controller;

import com.wipro.hma.us.dto.UserDTO;
import com.wipro.hma.us.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/private/users")
public class PrivateUserController {
    @Autowired
    private UserService userService;


    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(userId, userDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        UserDTO userDTO = userService.getUserById(userId);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(@RequestParam(value="pageNumber", defaultValue="0", required =false)Integer pageNumber,
                                                     @RequestParam(value="pageSize", defaultValue="20", required =false)Integer pageSize,
                                                     @RequestParam(value="sortBy", defaultValue="rollNo", required =false)String sortBy,
                                                     @RequestParam(value="sortDir", defaultValue = "asc", required = false) String sortDir) {
        List<UserDTO> userDTOs = userService.getAllUsers(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }
}
