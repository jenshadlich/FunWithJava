package de.jeha.fwj.security;


import java.security.Permission;

public class AllowEverythingSecurityManager extends SecurityManager {
    @Override
    public void checkPermission(Permission perm) {
        // do nothing
    }
}
