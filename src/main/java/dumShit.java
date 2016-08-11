/**
 * Started by Shaun on 10/08/2016.
 */
public class dumShit {
    public static void main(String args[]) {
        new dumShit();
    }
    private dumShit(){
        lamp(0);
    }
    private int lamp(int i){
        i++;
        System.out.println(""+i);
        return lamp(i);
    }
}
