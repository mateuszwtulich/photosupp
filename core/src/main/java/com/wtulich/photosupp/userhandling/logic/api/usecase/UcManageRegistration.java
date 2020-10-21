package com.wtulich.photosupp.userhandling.logic.api.usecase;

import org.springframework.web.servlet.view.RedirectView;
import java.util.Optional;

public interface UcManageRegistration {

    Optional<RedirectView> confirmRegistration(String token);
}
