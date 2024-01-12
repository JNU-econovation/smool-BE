package econovation.moodtracker.controller;

import econovation.moodtracker.domain.User;
import econovation.moodtracker.domain.dto.Request.UserCreateRequestDTO;
import econovation.moodtracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users/join")
    public ResponseEntity<Long> join(@RequestBody UserCreateRequestDTO userCreateRequestDTO){
        Long userPK = userService.join(userCreateRequestDTO);
        return new ResponseEntity<>(userPK, HttpStatus.CREATED);
    }
}
