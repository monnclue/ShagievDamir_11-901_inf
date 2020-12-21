package ru.itis.view;

import ru.itis.application.MainApplication;

public abstract class View {
    private static MainApplication application;

    public static void setApplication(MainApplication application) {
        View.application = application;
    }

    public static MainApplication getApplication() throws Exception {
        if (application != null) {
            return application;
        }
        throw new Exception("No application in View");
    }
}
