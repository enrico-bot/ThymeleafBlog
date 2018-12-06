package ch.supsi.webapp.web.model.page;

public class NewPage extends Page{

    @Override
    public String getFullName() {
        return "Create a new post";
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
