package hr.foi.airprojekt.web.service;

import hr.foi.airprojekt.web.repository.DispatcherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirUserDetailService implements UserDetailsService {

    private final DispatcherRepository dispatcherRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return dispatcherRepository.findByUsername(s);
    }
}
