package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.Services.Authentication;
import org.academiadecodigo.javabank.view.View;

/**
 * A generic controller to be used as a base for concrete controller implementations
 * @see Controller
 */
public abstract class AbstractController implements Controller {

    protected View view;
    protected Prompt prompt;

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    protected Authentication authentication;

    /**
     * Sets the controller view
     *
     * @param view the view to be set
     */
    public void setView(View view) {
        this.view = view;
    }
    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }

    /**
     * @see Controller#init()
     */
    @Override
    public void init() {
        view.show();
    }
}
