package econovation.moodtracker.controller;

import econovation.moodtracker.domain.User;
import econovation.moodtracker.domain.dto.Request.UserCreateRequestDTO;
import econovation.moodtracker.domain.dto.Request.UserLoginRequestDTO;
import econovation.moodtracker.domain.dto.Response.UserResponseDTO;
import econovation.moodtracker.domain.dto.common.ApiResponse;
import econovation.moodtracker.domain.dto.common.ApiResponseGenerator;
import econovation.moodtracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user/join")
    public ApiResponse.Result<?> join(@Validated @RequestBody UserCreateRequestDTO userCreateRequestDTO){
        Long userPK = userService.join(userCreateRequestDTO);
        return ApiResponseGenerator.success(UserResponseDTO.of(userPK), "회원가입이 완료되었습니다.");
    }

    @PostMapping("/user/login")
    public ApiResponse.Result<?> login(@RequestBody UserLoginRequestDTO userLoginRequestDTO){
        Long userPK = userService.login(userLoginRequestDTO.getUserId(), userLoginRequestDTO.getPassword());
        return ApiResponseGenerator.success(UserResponseDTO.of(userPK), "로그인되었습니다.");
    }
}
