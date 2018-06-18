package br.unirio.calls.domains.user;

public interface UserRepository {

    public User findById(int id);

    public User findByEmailAddress(String email);

    public boolean save(User user);
    
    public boolean registerSuccessfulLogin(User user);
    
    public boolean registerFailedLogin(User user);

    public boolean changePassword(User user, String password);
    
    public boolean attachResetPasswordToken(User user, String token);

    public boolean detachResetPasswordToken(User user);

    public boolean unblock(User user);

    public User findByResetPasswordToken(String token);
}