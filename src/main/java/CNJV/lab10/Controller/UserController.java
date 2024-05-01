package CNJV.lab10.Controller;

import CNJV.lab10.Service.ClientService;
import CNJV.lab10.Service.UserService;
import CNJV.lab10.dto.UserDto;
import CNJV.lab10.model.Client;
import CNJV.lab10.model.Product;
import jakarta.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
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

    @PostMapping("/api/login")
    public ModelAndView login(HttpServletRequest httpServletRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(httpServletRequest.getParameter("email"), httpServletRequest.getParameter("password"))
            );
        } catch (DisabledException e) {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("error", "USER_DISABLED");
            return modelAndView;
        } catch (BadCredentialsException e) {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("error", "INVALID_CREDENTIALS");
            return modelAndView;
        } catch (Exception e) {
            throw e;
        }

        final User user = userService.loadUserByUsername(httpServletRequest.getParameter("email"));
        final String jwtToken = tokenManager.generateJwtToken(user);

        // Redirect to index page
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("token", jwtToken);
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @GetMapping("/index")
    public ModelAndView showIndexPage() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView showRegisterPage() {
        ModelAndView modelAndView = new ModelAndView("register");
        return modelAndView;
    }

    @PostMapping(path="/api/register")
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

    @GetMapping("/api")
    public Iterable<User> getAll() {
        return userService.getAll();
    }
}