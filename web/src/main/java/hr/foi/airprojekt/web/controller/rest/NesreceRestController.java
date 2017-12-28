package hr.foi.airprojekt.web.controller.rest;


import hr.foi.airprojekt.web.service.PozivService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NesreceRestController {

    private final PozivService pozivService;

    @GetMapping("/nesrece/push_notifikacija/{id}")
    public String sendPushNotification(@PathVariable("id") int id) {
        return pozivService.sendPushNotification(id);
    }

}
