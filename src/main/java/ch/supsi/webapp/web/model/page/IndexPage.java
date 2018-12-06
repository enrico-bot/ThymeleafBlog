package ch.supsi.webapp.web.model.page;

public class IndexPage extends Page{
    @Override
    public String getFullName() {
        return "Home";
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public boolean isHome() {
        return true;
    }
}
