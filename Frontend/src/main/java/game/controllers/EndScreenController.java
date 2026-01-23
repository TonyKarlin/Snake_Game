package game.controllers;

import utils.context.AppContext;

public class EndScreenController {
    private AppContext context;


    public AppContext getContext() {
        return context;
    }
    
    public void setMediator(AppContext context) {
        this.context = context;
    }
}
