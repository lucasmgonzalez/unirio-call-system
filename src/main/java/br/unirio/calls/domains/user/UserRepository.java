package br.unirio.calls.domains.user;

public interface UserRepository {

    public User findById(int id);

    public User findByEmailAddress(String email);

}