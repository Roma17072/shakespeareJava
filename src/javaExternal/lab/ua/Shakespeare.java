package javaExternal.lab.ua;

public class Shakespeare {
    public static void main(String[] args) throws Exception {

        Model shakespeareModel = new Model();
        View shakespeareView = new View();
        Controller shakespeareController = new Controller(shakespeareModel, shakespeareView);
        shakespeareController.search();
    }
}