// package com.example.demo.controller.api;
//
// import com.example.demo.dto.UserDto;
// import com.example.demo.service.UserService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.core.Authentication;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
//
//@RestController()
//@RequiredArgsConstructor
//@RequestMapping("api/users")
//public class UserController {
//    private final UserService userService;
//
//    @GetMapping("email/{email}")
//    public ResponseEntity<UserDto> getUserByEmail(Authentication authentication, @PathVariable String email) {
//        return ResponseEntity.ok(userService.getUserByEmail(email));
//    }
//
//    @GetMapping("phone/{phoneNumber}")
//    public ResponseEntity<UserDto> getUserByPhoneNumber(@PathVariable String phoneNumber) {
//        return ResponseEntity.ok(userService.getUserByPhoneNumber(phoneNumber));
//    }
//
//}
