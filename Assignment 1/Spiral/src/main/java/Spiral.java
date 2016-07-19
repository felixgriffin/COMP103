import ecs100.UI;

/**
 * Started by Shaun on 19/07/2016.
 */
public class Spiral {

    public Spiral(){
        UI.addButton("",this::Line);
        UI.addButton("",this::Square);
        UI.addButton("",this::Spiral);
        UI.addButton("Clear",UI::clearGraphics);
        UI.addButton("Exit",UI::quit);
    }

    public void Line(){

    }

    public void Square(){

    }

    public void Spiral(){




    public static void main(String[] arguments){
        new Spiral();
    }
}
