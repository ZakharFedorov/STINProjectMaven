package com.stin.stinprojectmaven.backend.Service;

import org.springframework.ui.Model;

import java.security.Principal;

public interface BankModelService {
    void createModel(Model model, Principal principal);
}
