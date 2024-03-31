package ru.job4j.generics;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {
    @Test
    void whenAddAndFindThenRoleIsUser() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("User");
    }

    @Test
    void whenAddAndFindThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleDeveloper() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        store.add(new Role("1", "Developer"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("User");
    }

    @Test
    void whenReplaceThenRoleIsDeveloper() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        store.replace("1", new Role("1", "Developer"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("Developer");
    }

    @Test
    void whenNoReplaceUserThenNoChangeRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        store.replace("10", new Role("10", "Developer"));
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("User");
    }

    @Test
    void whenDeleteUserThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteUserThenRoleIsUser() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRole()).isEqualTo("User");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        boolean result = store.replace("1", new Role("1", "Developer"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        boolean result = store.replace("10", new Role("10", "Developer"));
        assertThat(result).isFalse();
    }
}