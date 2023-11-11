package sia.tacocloud.tacos.constant;

public enum UserRoles {

    USER("USER");

    private String role;

    UserRoles(String role) {
        this.role = role;
    }

    public String getRoleWithPrefix() {
        return "ROLE_" + role;
    }

    public String getRoleWithoutPrefix() {
        return role;
    }
}
