package javaExternal.lab.ua;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
    View shakespeareView;
    Model shakespeareModel;

    public Controller(Model shakespeareModel, View shakespeareView) throws Exception {
        this.shakespeareModel = shakespeareModel;
        this.shakespeareView = shakespeareView;
    }
    public void searchWord() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        shakespeareView.printMessage(View.CHOOSE_SONNET);
        shakespeareView.printMessage(View.FROM);
        shakespeareModel.setSonnetFrom(checkInput(br));
        shakespeareView.printMessage(View.TILL);
        shakespeareModel.setSonnetTill(checkInput(br));
        shakespeareModel.analyze();
        shakespeareView.printMessage(View.WORD);
        if(shakespeareModel.searchWord(inputString(br)))
            shakespeareView.printResult(shakespeareModel.getResultList());
        else
            shakespeareView.printMessage(View.WORD_NOT_FOUND);
        searchWord();
    }

    private int checkInput (BufferedReader br) {
        int input = 0;
        do {
            try {
                input = Integer.parseInt(br.readLine());
                if (input > 154 || input < 1){
                    input = 0;
                    shakespeareView.printMessage(View.WRONG);
                }
            } catch (NumberFormatException | IOException e) {
                shakespeareView.printMessage(View.WRONG);
            }
        }while (input == 0);
        return input;
    }

    private String inputString (BufferedReader br){
        String input = null;
        do {
            try {
                input = (br.readLine());
                if (!input.matches("^[а-яА-Яa-zA-Z]{1,}$")) {
                    shakespeareView.printMessage(View.WRONG);
                    input = null;
                }
            } catch (IOException e) {
                shakespeareView.printMessage(View.WRONG);
            }
        }while (input == null);
        return input;
    }
}
