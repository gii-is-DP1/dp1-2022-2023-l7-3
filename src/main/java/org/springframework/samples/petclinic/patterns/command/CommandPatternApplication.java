package org.springframework.samples.petclinic.patterns.command;

import org.jpatterns.gof.CommandPattern;


public class CommandPatternApplication {
    @CommandPattern.Command
    interface ActionListenerCommand {
        public void execute();
    }

    @CommandPattern.Receiver
    static class Document {
        public void open() {
            System.out.println("Document Opened");
        }

        public void save() {
            System.out.println("Document Saved");
        }
    }

    @CommandPattern.ConcreteCommand
    static class ActionOpen implements ActionListenerCommand {
        private Document doc;

        public ActionOpen(Document doc) {
            this.doc = doc;
        }

        @Override
        public void execute() {
            doc.open();
        }
    }

    @CommandPattern.ConcreteCommand
    static class ActionSave implements ActionListenerCommand {
        private Document doc;

        public ActionSave(Document doc) {
            this.doc = doc;
        }

        @Override
        public void execute() {
            doc.save();
        }
    }

    @CommandPattern.Invoker
    static class MenuOptions {

        private ActionListenerCommand openCommand;
        private ActionListenerCommand saveCommand;

        public MenuOptions(ActionListenerCommand open, ActionListenerCommand save) {
            this.openCommand = open;
            this.saveCommand = save;
        }

        public void clickOpen() {
            openCommand.execute();
        }

        public void clickSave() {
            saveCommand.execute();
        }

    }

    @CommandPattern.Client
    static class CommandPatternClient {
        public static void main(String[] args) {
            Document doc = new Document();

            ActionListenerCommand clickOpen = new ActionOpen(doc);
            ActionListenerCommand clickSave = new ActionSave(doc);

            MenuOptions menu = new MenuOptions(clickOpen, clickSave);

            menu.clickOpen();
            menu.clickSave();
        }
    }

}
