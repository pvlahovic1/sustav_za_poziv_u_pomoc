package hr.foi.airprojekt.web.service;

import hr.foi.airprojekt.web.exception.UsernameExistsException;
import hr.foi.airprojekt.web.model.Dispatcher;
import hr.foi.airprojekt.web.model.wrappers.User;
import hr.foi.airprojekt.web.repository.DispatcherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DispatcherServiceImpl implements DispatcherService {

    private final DispatcherRepository dispatcherRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerNewDispatcher(User user) {

        if (usernameExists(user.getUsername())) {
            throw new UsernameExistsException("Username already exists");
        }

        Dispatcher dispatcher = new Dispatcher();

        dispatcher.setIme(user.getFirstName());
        dispatcher.setPrezime(user.getLastName());
        dispatcher.setUsername(user.getUsername());
        dispatcher.setPassword(passwordEncoder.encode(user.getPassword()));
        dispatcher.setActive(true);

        dispatcherRepository.save(dispatcher);
    }

    private boolean usernameExists(String username) {
        return dispatcherRepository.findByUsername(username) != null;
    }
}
