package ch.supsi.webapp.web.model.page;

public abstract class Page {
    public boolean isNew() {
        return false;
    }
    public boolean isHome() {
        return false;
    }
    public abstract String getFullName();
    public abstract int getID();
}
