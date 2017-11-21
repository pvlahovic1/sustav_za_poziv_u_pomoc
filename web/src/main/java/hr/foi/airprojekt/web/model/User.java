package hr.foi.airprojekt.web.model;

import hr.foi.airprojekt.web.validator.PasswordMatches;
import lombok.Data;
import lombok.Value;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@PasswordMatches
public class User {

    @NotNull(message = "Ne smije biti prazno")
    @NotEmpty(message = "Ne smije biti prazno")
    @Size(message = "Korisničko ime mora biti dugačko [2,10]", min=2, max=10)
    private String username;
    @NotNull(message = "Ne smije biti prazno")
    @NotEmpty(message = "Ne smije biti prazno")
    @Size(message = "Lozinka mora biti dugačka [5,30]", min=5, max=30)
    private String password;
    private String matchingPassword;
    @NotNull(message = "Ne smije biti prazno")
    @NotEmpty(message = "Ne smije biti prazno")
    private String firstName;
    @NotNull(message = "Ne smije biti prazno")
    @NotEmpty(message = "Ne smije biti prazno")
    private String lastName;

}
