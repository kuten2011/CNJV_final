package CNJV.lab10.config;

import java.security.Permission;

public class CustomSecurityManager extends SecurityManager {
    @Override
    public void checkPermission(Permission perm) {
        // Kiểm tra xem quyền được yêu cầu có phải là một quyền kết nối không?
        if (perm.getName().startsWith("connect")) {
            // Kiểm tra xem URL có là localhost:8080/index không
            String requestedURL = perm.getName();
            if (requestedURL.equals("connect://localhost:8080/index")) {
                // Ném ngoại lệ SecurityException nếu URL là localhost:8080/index
                throw new SecurityException("Access to localhost:8080/index is not allowed.");
            }
        }
    }
}