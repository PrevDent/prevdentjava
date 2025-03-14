package br.com.PrevDent.PrevDent.domain.user;

public enum PacienteUserRole {

    Admin("admin"),

    USER_ROLE("user");

    private final String role;

    PacienteUserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
