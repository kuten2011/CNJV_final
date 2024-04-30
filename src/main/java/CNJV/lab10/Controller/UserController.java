package CNJV.lab10.Controller;

import CNJV.lab10.Service.ClientService;
import CNJV.lab10.Service.UserService;
import CNJV.lab10.dto.UserDto;
import CNJV.lab10.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import CNJV.lab10.jwtutils.Token;
import CNJV.lab10.jwtutils.TokenManager;
import CNJV.lab10.model.User;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenManager tokenManager;

    @PostMapping(path="/login")
    public ResponseEntity<Token> login(@RequestBody UserDto userDto) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword())
            );
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        } catch (Exception e) {
            throw e;
        }
        final User user = userService.loadUserByUsername(userDto.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(user);
        System.out.println(jwtToken);
        return new ResponseEntity(new Token(jwtToken), HttpStatus.OK);
    }

    @PostMapping(path="/register")
    public ResponseEntity<User> register(@RequestBody UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .name(userDto.getName())
                .phone(userDto.getPhone())
                .email(userDto.getEmail())
                .position(2)
                .enabled(true)
                .build();
        userService.update(user);

        Client client = Client.builder()
                        .name(userDto.getName())
                        .phone(userDto.getPhone())
                        .email(userDto.getEmail())
                        .user(user)
                        .build();
        clientService.saveClient(client);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping({"","/"})
    public Iterable<User> getAll() {
        return userService.getAll();
    }
}