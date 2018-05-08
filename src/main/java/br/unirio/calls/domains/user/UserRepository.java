package br.unirio.calls.domains.user;

import java.util.Optional;

public interface UserRepository {

    User findById(int id);

    User findByEmailAddress(String email);

}