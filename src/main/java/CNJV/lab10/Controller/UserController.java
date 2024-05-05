package CNJV.lab10.Controller;

import CNJV.lab10.Service.ClientService;
import CNJV.lab10.Service.LoginService;
import CNJV.lab10.Service.StaffService;
import CNJV.lab10.Service.UserService;
import CNJV.lab10.dto.UserDto;
import CNJV.lab10.model.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import CNJV.lab10.jwtutils.Token;
import CNJV.lab10.jwtutils.TokenManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;

    @Autowired
    StaffService staffService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenManager tokenManager;

    @PostMapping(path="/api/login")
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
        loginService.update(new Login(userDto.getUsername(), userDto.getPassword(),user.getPosition()));

        return new ResponseEntity(new Token(jwtToken), HttpStatus.OK);
    }

    @GetMapping("/logout")
    public RedirectView logOut() {
        try {
            String name = loginService.get(1L).getUsername();
            if (name != null && !name.isEmpty()) {
                 loginService.remove(1l);
            }
        } catch (Exception ex) {
            // Xử lý exception, ví dụ như ghi log
            ex.printStackTrace(); // Thông tin lỗi sẽ được in ra console
        }
        return new RedirectView("/index");
    }

    @PostMapping(path="/api/register/staff")
    public ResponseEntity<User> registerStaff(@RequestBody UserDto userDto) {
        String name = "";
        String pass = "";
        try {
            name = loginService.get(1l).getUsername();
            pass = loginService.get(1l).getPassword();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(name, pass)
            );
        } catch (Exception e) {
            throw e;
        }

        if (loginService.get(1l).getRole() == 0) {
            User user = User.builder()
                    .username(userDto.getUsername())
                    .password(passwordEncoder.encode(userDto.getPassword()))
                    .name(userDto.getName())
                    .phone(userDto.getPhone())
                    .email(userDto.getEmail())
                    .position(1)
                    .enabled(true)
                    .build();
            userService.update(user);

            Staff staff = Staff.builder()
                    .name(userDto.getName())
                    .phone(userDto.getPhone())
                    .email(userDto.getEmail())
                    .user(user)
                    .build();
            staffService.saveStaff(staff);

            user.setStaff(staff);
            userService.update(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }
    }

    @PostMapping(path="/api/register/admin")
    public ResponseEntity<User> registerAdmin(@RequestBody UserDto userDto) {
        String name = "";
        String pass = "";
        try {
            name = loginService.get(1l).getUsername();
            pass = loginService.get(1l).getPassword();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(name, pass)
            );
        } catch (Exception e) {
            throw e;
        }

        if (loginService.get(1l).getRole() == 0) {
            User user = User.builder()
                    .username(userDto.getUsername())
                    .password(passwordEncoder.encode(userDto.getPassword()))
                    .name(userDto.getName())
                    .phone(userDto.getPhone())
                    .email(userDto.getEmail())
                    .position(0)
                    .enabled(true)
                    .build();
            userService.update(user);

            Staff staff = Staff.builder()
                    .name(userDto.getName())
                    .phone(userDto.getPhone())
                    .email(userDto.getEmail())
                    .user(user)
                    .build();
            staffService.saveStaff(staff);

            user.setStaff(staff);
            userService.update(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }
    }

    @PostMapping("/changepass")
    public ModelAndView changePass(HttpServletRequest http, Model model) {
        String message= "";
        ModelAndView modelAndView = new ModelAndView("changepass");
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginService.get(1l).getUsername(), http.getParameter("cp")));
        } catch (Exception e) {
            message = "Information not correct";
            modelAndView.addObject("message", message);
        }
        System.out.println("aaaaaaaaa" + message);

        if (message.equals("")) {
            User user = userService.loadUserByUsername(loginService.get(1l).getUsername());
            user.setPassword(passwordEncoder.encode(http.getParameter("password")));
            userService.update(user);
            return new ModelAndView(new RedirectView("/index"));
        } else {
            return modelAndView;
        }
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

        user.setClient(client);
        userService.update(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/api")
    public Iterable<User> getAll() {
        return userService.getAll();
    }
}