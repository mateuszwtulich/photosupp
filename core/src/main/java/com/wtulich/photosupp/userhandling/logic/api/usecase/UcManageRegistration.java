package com.wtulich.photosupp.userhandling.logic.api.usecase;

import com.wtulich.photosupp.userhandling.logic.api.to.AccountEto;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

public interface UcManageRegistration {

    RedirectView confirmRegistration(String token);
}
