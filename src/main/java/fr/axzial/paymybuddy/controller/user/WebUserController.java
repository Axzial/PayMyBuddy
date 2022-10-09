package fr.axzial.paymybuddy.controller.user;

import fr.axzial.paymybuddy.dto.user.PublicWebUserDTO;
import fr.axzial.paymybuddy.dto.user.WebUserDTO;
import fr.axzial.paymybuddy.service.user.WebUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class WebUserController {

    private final WebUserService webUserService;

    @GetMapping
    public WebUserDTO getSelfUser() {
        return webUserService.getSelfUserDTO();
    }

    @GetMapping("/search")
    public Page<PublicWebUserDTO> searchContacts(@RequestParam String query, @RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "12", required = false) int size){
        return webUserService.searchContacts(query, page, size);
    }


}
