package com.jyotibroto.journalApp.Controller;

import com.jyotibroto.journalApp.Repository.UserRepository;
import com.jyotibroto.journalApp.Service.JournalEntryService;
import com.jyotibroto.journalApp.Service.UserService;
import com.jyotibroto.journalApp.Service.WeatherService;
import com.jyotibroto.journalApp.api.response.WeatherResponse;
import com.jyotibroto.journalApp.entity.JournalEntry;
import com.jyotibroto.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUsername(userName);
        if(userInDb != null) {
            userInDb.setPassword(user.getPassword());
            userInDb.setUserName(user.getUserName());
            userService.saveNewUser(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> DeleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Bengaluru");
        String greeting = "";
        if(weatherResponse != null) {
            greeting = ", Today's weather feels like " + weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("hi " + authentication.getName() + greeting , HttpStatus.OK);
    }
}
